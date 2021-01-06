package com.jaclon.helloworld.redis;

import com.jaclon.helloworld.redis.r2.RedisKeyUtil;
import com.jaclon.helloworld.redis.r2.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author jaclon
 * @date 2019/9/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Resource
    private RedisService redisService;

    @Test
    public void testObj(){
        UserVO userVO = new UserVO("zhangsan", 15, "beijing");
        ValueOperations ops = redisTemplate.opsForValue();
        redisService.expireKey("name",20, TimeUnit.SECONDS);
        String key = RedisKeyUtil.getKey(UserVO.Table,"name",userVO.getName());
        //this.valueOperations.set(key,userVO);
        UserVO vo = (UserVO) ops.get(key);
        System.out.println("=============" + vo);
    }

    @Test
    public void testSet(){
        UserVO user1 = new UserVO("lisi", 18, "shanghai");
        UserVO user2 = new UserVO("wangwu", 80, "America");
        setOperations.add("user:test",user1,user2);
        Set <Object> members = setOperations.members("user:test");
        System.out.println(members);
    }

    @Test
    public void testHashOperations(){
        UserVO userVO = new UserVO("lijia", 22, "tokoyo");
        hashOperations.put("hash:user",userVO.hashCode() + "",userVO);
        System.out.println(hashOperations.get("hash:user",userVO.hashCode() + ""));
    }

    @Test
    public void testListOperations(){
        ListOperations listOperations = redisTemplate.opsForList();
        UserVO userVO = new UserVO("mimi", 90, "sichuan");
        this.listOperations.leftPush("list:user",userVO);
        System.out.println(this.listOperations.leftPop("list:user"));
        System.out.println("============================");
        System.out.println(this.listOperations.leftPop("list:user"));
    }
}
