package com.jaclon.spring.project.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/4 下午12:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private String username;
  private String product;
}
