package com.jaclon.spring.project.example.aop2;

import com.jaclon.spring.project.example.bean.Order;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/4 下午12:30
 */
public interface OrderService {
  Order createOrder(String username, String product);

  Order queryOrder(String username);
}


