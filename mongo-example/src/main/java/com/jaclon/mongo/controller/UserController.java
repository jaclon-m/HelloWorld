package com.jaclon.mongo.controller;

import com.jaclon.mongo.dao.UserDao;
import com.jaclon.mongo.model.MongoUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/6/2 15:41
 */
@RestController
public class UserController {
    @Resource
    private UserDao userDao;

    @GetMapping("/save")
    public String save(){
        MongoUser mongoUser = new MongoUser();
        mongoUser.setAge(30);
        mongoUser.setId(11);
        mongoUser.setName("zhangsan");
        userDao.save(mongoUser);
        return "OK";
    }

    @GetMapping(value="/find")
    public MongoUser findTestByName(){
        MongoUser mgtest= userDao.findByName("zhangsan");
        System.out.println("mgtest is "+mgtest);
        return mgtest;
    }

    @GetMapping(value="/update")
    public void updateTest(){
        MongoUser mgtest=new MongoUser();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName("lisi");
        userDao.updateUser(mgtest);
    }

    @GetMapping(value="/delete")
    public void deleteTestById() {
        userDao.deleteById(11);
    }
}
