package com.jaclon.mistakesOfBuz.cachedesign;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 缓存击穿/缓存并发
 *  方案一，使用进程内的锁进行限制，这样每一个节点都可以以一个并发回源数据库;
 * 方案二，不使用锁进行限制，而是使用类似 Semaphore 的工具限制并发数，比如限制 为 10，这样既限制了回源并发数不至于太大，又能使得一定量的线程可以同时回源。
 *
 *
 *
 * @author jaclon
 * @since 2021/8/9 12:21
 */
@Slf4j
@RequestMapping("cache/concurrent")
@RestController
public class CacheConcurrentController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger();
    @Autowired
    private RedissonClient redissonClient;

//    @PostConstruct
    public void init() {
        stringRedisTemplate.opsForValue().set("hotspot", getExpensiveData(), 5, TimeUnit.SECONDS);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
        }, 0, 1, TimeUnit.SECONDS);
    }

    @GetMapping("/wrong")
    public String wrong() {
        String data = stringRedisTemplate.opsForValue().get("hotspot");
        if (StringUtils.isEmpty(data)) {
            data = getExpensiveData();
            stringRedisTemplate.opsForValue().set("hotspot", data, 5, TimeUnit.SECONDS);
        }
        return data;
    }

    @GetMapping("/right")
    public String right(){
        String data = stringRedisTemplate.opsForValue().get("hotspot");
        if(StringUtils.isEmpty(data)){
            RLock locker = redissonClient.getLock("locker");
            if(locker.tryLock()){
                try {
                    data = stringRedisTemplate.opsForValue().get("hotspot");
                    if(StringUtils.isEmpty(data)){
                        data = getExpensiveData();
                        stringRedisTemplate.opsForValue().set("hotspot",data,5,TimeUnit.SECONDS);
                    }
                }finally {
                    locker.unlock();
                }

            }
        }
        return data;
    }

    private String getExpensiveData() {
        atomicInteger.incrementAndGet();
        return "important data";
    }
}
