/**
 * 使用 RedisTemplate<String, Long> 能否存取 Value 是 Long 的数据呢?这其中有什么坑吗?
 *
 *  Long序列化的时候，Redis会认为是int，因此是获取不到的Long数据的，需要处理；
 *  在Integer区间内返回的是Integer，超过这个区间返回Long
 * @author jaclon
 * @since 2021/6/7 21:02
 */
package com.jaclon.mistakesOfBuz.serialization.redis;