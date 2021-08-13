package com.jaclon.mistakesOfBuz.cachedesign;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 缓存穿透
 *
 * 缓存穿透是指，缓存没有起到压力缓冲的作用;而缓存击穿是指，缓存失效时瞬时的并发打到数据库。
 *
 * @author jaclon
 * @since 2021/8/9 12:34
 */
@Slf4j
@RequestMapping("cache/penetration")
@RestController
public class CachePenetrationController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BloomFilter<Integer> bloomFilter;

    //@PostConstruct
    public void init() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("DB QPS : {}", atomicInteger.getAndSet(0));
        }, 0, 1, TimeUnit.SECONDS);
        //创建布隆过滤器，元素数量10000，期望误判率1%
        bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 10000, 0.01);
        //填充布隆过滤器
        IntStream.rangeClosed(1, 10000).forEach(bloomFilter::put);
    }

    @GetMapping("wrong")
    public String wrong(@RequestParam("id") int id) {
        String key = "user" + id;
        String data = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(data)) {
            data = getCityFromDb(id);
            stringRedisTemplate.opsForValue().set(key, data, 30, TimeUnit.SECONDS);
        }
        return data;
    }

    @GetMapping("right")
    public String right(@RequestParam("id") int id) {
        String key = "user" + id;
        String data = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(data)) {
            data = getCityFromDb(id);
            if (!StringUtils.isEmpty(data)) {
                stringRedisTemplate.opsForValue().set(key, data, 30, TimeUnit.SECONDS);
            } else {
                stringRedisTemplate.opsForValue().set(key, "NODATA", 30, TimeUnit.SECONDS);
            }

        }
        return data;
    }

    @GetMapping("right2")
    public String right2(@RequestParam("id") int id) {
        String data = "";
        if (bloomFilter.mightContain(id)) {
            String key = "user" + id;
            data = stringRedisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(data)) {
                data = getCityFromDb(id);
                stringRedisTemplate.opsForValue().set(key, data, 30, TimeUnit.SECONDS);
            }
        }
        return data;
    }

    private String getCityFromDb(int id) {
        atomicInteger.incrementAndGet();
        if (id > 0 && id <= 10000) return "userdata";
        return "";
    }
}
