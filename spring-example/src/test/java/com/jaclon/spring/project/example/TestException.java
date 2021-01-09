package com.jaclon.spring.project.example;

import org.junit.jupiter.api.Test;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/15 下午6:57
 */
public class TestException {

  @Test
  public void test(){
    System.out.println("=============123");
    try {
      int i = 10/0;
    }catch (Exception e){
      e.printStackTrace();
    }
    System.out.println("=========");
  }
}
