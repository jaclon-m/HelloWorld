package com.jaclon.spring.project.example.aop;

import org.springframework.stereotype.Service;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/2 下午12:26
 */
@Service
public class ChatService {
  @AuthPermission
  public void chat(int userId){
    System.out.println("i want to chat ,from " + userId);
  }
}
