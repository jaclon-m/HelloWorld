package com.jaclon.mistakesOfBuz.connectionpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * @author jaclon
 * @date 2021/5/15
 */
@Slf4j
public class JedisTest {
    private static JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    public static void init(){
        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
            Assert.isTrue("OK".equals(jedis.set("a", "1")), "set a = 1 return OK");
            Assert.isTrue("OK".equals(jedis.set("b", "2")), "set b = 2 return OK");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jedisPool.close();
        }));
    }
    public static void main(String[] args) throws InterruptedException {
        init();
//        new JedisTest().wrong();
        new JedisTest().right();
    }
    public void right() throws InterruptedException {
        new Thread(()->{
            try (Jedis jedis = jedisPool.getResource()) {
                for (int i = 0; i < 1000; i++) {
                    String result = jedis.get("a");
                    if (!"1".equals(result)) {
                        log.warn("Expect a to be 1 but found {}", result);
                        return;
                    }
                }
            }
        }).start();
        new Thread(()->{
            try (Jedis jedis = jedisPool.getResource()) {
                for (int i = 0; i < 1000; i++) {
                    String result = jedis.get("b");
                    if (!"2".equals(result)) {
                        log.warn("Expect b to be 2 but found {}", result);
                        return;
                    }
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(5);
    }
    public void wrong() throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        new Thread(()->{
            for(int i = 0;i<100;i++){
                String result = jedis.get("a");
                if(!result.equals("1")){
                    log.warn("Expect to be 1 but found: {}",result);
                    return;
                }
            }
        }).start();
        new Thread(()->{
            for(int i = 0;i<100;i++){
                String result = jedis.get("b");
                if(!result.equals("2")){
                    log.warn("Expect to be 2 but found: {}",result);
                    return;
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(5);
    }
}
