package com.jaclon.spring.project.example.web;

import com.jaclon.spring.project.example.bean.Person;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("/mvc")
  public String HelloMVC(Person person){
    System.out.println("person : "  + person.toString());
    return "Hello MVC";
  }
}
