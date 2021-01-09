package com.jaclon.spring.project.example.aop2.impl;

import com.jaclon.spring.project.example.aop2.UserService;
import com.jaclon.spring.project.example.bean.User;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/4 下午12:35
 */
public class UserServiceImpl implements UserService {
  @Override
  public User createUser(String firstName, String lastName, int age) {
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setAge(age);
    return user;
  }

  @Override
  public User queryUser() {
    User user = new User();
    user.setFirstName("test");
    user.setLastName("test");
    user.setAge(20);
    return user;
  }

}
