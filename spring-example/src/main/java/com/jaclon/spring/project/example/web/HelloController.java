package com.jaclon.spring.project.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/17 下午6:49
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String HelloUrl(){
    System.out.println("=============Hello World==============");
    return "Hello World";
  }

  @RequestMapping("/helloMVC")
  public String HelloMVC(){
    System.out.println("=============Hello World==============");
    return "Hello World2";
  }
}
