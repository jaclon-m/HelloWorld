package com.jaclon.spring.project.example.aop;

import org.springframework.stereotype.Service;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/2 下午12:25
 */
@Service
public class BuyService {
  @AuthPermission
  public void buyItem(int userId){
    System.out.println("我要买东西，来自: " + userId);
  }
}
