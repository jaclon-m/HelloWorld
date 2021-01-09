package com.jaclon.spring.project.example.aop2;

import com.jaclon.spring.project.example.bean.User;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/4 下午12:30
 */
public interface UserService {
  User createUser(String firstName, String lastName, int age);

  User queryUser();
}
