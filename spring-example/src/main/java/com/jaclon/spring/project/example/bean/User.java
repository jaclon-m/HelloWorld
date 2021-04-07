package com.jaclon.spring.project.example.bean;

import java.util.List;
import lombok.Data;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/4 下午12:31
 */
@Data
public class User {
  private String firstName;
  private String lastName;
  private Integer age;
  List<Order> orders;
}
