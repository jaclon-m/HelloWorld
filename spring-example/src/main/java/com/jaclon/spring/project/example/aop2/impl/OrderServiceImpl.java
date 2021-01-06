package com.jaclon.spring.project.example.aop2.impl;

import com.jaclon.spring.project.example.aop2.OrderService;
import com.jaclon.spring.project.example.bean.Order;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/4 下午12:34
 */
public class OrderServiceImpl implements OrderService {
  @Override
  public Order createOrder(String username, String product) {
    Order order = new Order();
    order.setUsername(username);
    order.setProduct(product);
    return order;
  }

  @Override
  public Order queryOrder(String username) {
    Order order = new Order();
    order.setUsername("test");
    order.setProduct("test");
    return order;
  }
}
