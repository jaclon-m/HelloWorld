package com.jaclon.spring.project.example.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/1 上午11:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  private String name;
  private Integer age;
  private Integer sex;
}
