package com.jaclon.helloworld.redis.r2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.*;

/**
 * spring-redis对redis的五种数据类型也有支持
 * HashOperations：对hash类型的数据操作
 * ValueOperations：对redis字符串类型数据操作
 * ListOperations：对链表类型的数据操作
 * SetOperations：对无序集合类型的数据操作
 * ZSetOperations：对有序集合类型的数据操作
 *
 * 通过使用spring对redis的操作（operation）简化工具类的编写
 * 可以看到RedisUtil中有很多方法，使用对应的operation就能搞定。
 * 具体可以参见RedisUtil2
 *
 * @author jaclon
 * @date 2019/9/17
 */
@Configuration
public class RedisOperationsConfig {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 对hash类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * 对redis字符串类型数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * 对链表类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * 对无序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型的数据操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
