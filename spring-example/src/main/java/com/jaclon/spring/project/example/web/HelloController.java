package com.jaclon.spring.project.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/17 下午6:49
 */
@Controller
public class HelloController {

  @RequestMapping("/hello")
  @ResponseBody
  public String HelloUrl(){
    System.out.println("=============Hello World==============");
    return "Hello World";
  }
}
