package com.jaclon.mistakesOfBuz.cachedesign;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 缓存雪崩/失效问题
 *
 * 程序初始化的时候放入 1000 条城市数据到 Redis 缓存中，过期时间是 30 秒;
 * 数据过期后 从数据库获取数据然后写入缓存，每次从数据库获取数据后计数器 +1;
 * 在程序启动的同 时，启动一个定时任务线程每隔一秒输出计数器的值，并把计数器归零。
 *
 * @author jaclon
 * @since 2021/8/9 10:30
 */
@RestController
@RequestMapping("/cache/invalid")
@Slf4j
public class
CacheInvalidController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private AtomicInteger atomicInteger = new AtomicInteger();

//    @PostConstruct
    public void wrongInit(){
        //初始化1000个城市数据到Redis，所有缓存数据有效期30秒
        IntStream.rangeClosed(1,1000).forEach(i-> stringRedisTemplate.opsForValue().set("city" + i,getCityFromDb(i),
                30, TimeUnit.SECONDS));
        //每秒一次，输出数据库访问的QPS
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->{
            log.info("DB QPS: {}",atomicInteger.getAndSet(0));
        },0,1,TimeUnit.SECONDS);
    }

    /**
     * 方案一和方案二是截然不同的两种缓存方式，如果无法全量缓存所有数据，那么只能使用方案一;
     *  即使使用了方案二，缓存永不过期，同样需要在查询的时候，确保有回源的逻辑。正如之前所说，我们无法确保缓存系统中的数据永不丢失。
     *  不管是方案一还是方案二，在把数据从数据库加入缓存的时候，都需要判断来自数据库的数据是否合法，比如进行最基本的判空检查。
     */
//    @PostConstruct
    public void rightInit1() {
        IntStream.rangeClosed(1, 1000).forEach(i -> stringRedisTemplate.opsForValue().set("city" + i, getCityFromDb(i), 30 + ThreadLocalRandom.current().nextInt(10), TimeUnit.SECONDS));
        log.info("Cache init finished");
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
        }, 0, 1, TimeUnit.SECONDS);
    }

//    @PostConstruct
    public void rightInit2() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //每隔30秒全量更新一次缓存
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            IntStream.rangeClosed(1, 1000).forEach(i -> {
                String data = getCityFromDb(i);
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                }
                if (!StringUtils.isEmpty(data)) {
                    stringRedisTemplate.opsForValue().set("city" + i, data);
                }
            });
            log.info("Cache update finished");
            //启动程序的时候需要等待首次更新缓存完成
            countDownLatch.countDown();
        }, 0, 30, TimeUnit.SECONDS);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
        }, 0, 1, TimeUnit.SECONDS);

        countDownLatch.await();
    }


    @GetMapping("/city")
    public String city(){
        //随机查询一个城市
        int id = ThreadLocalRandom.current().nextInt(1000) + 1;
        String key = "city" + id;
        String data = stringRedisTemplate.opsForValue().get(key);
        if(data == null){
            //回源到数据库查询
            data = getCityFromDb(id);
            if(!StringUtils.isEmpty(data)){
                stringRedisTemplate.opsForValue().set(key,data,30,TimeUnit.SECONDS);
            }
        }
        return data;
    }


    private String getCityFromDb(int cityId) {
        //模拟查询数据库，查一次增加计数器加一
        atomicInteger.incrementAndGet();
        return "citydata" + System.currentTimeMillis();
    }
}
