package com.jaclon.spring.project.example.web;

import com.jaclon.spring.project.example.bean.Order;
import com.jaclon.spring.project.example.bean.Person;
import com.jaclon.spring.project.example.bean.User;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/17 下午6:49
 */
@RestController
@RequestMapping("/test")
public class HelloController {

  @GetMapping("/hello")
  public User HelloUrl(){
    System.out.println("=============Hello World==============");
    Order order = new Order("ll","x");
    Order order2 = new Order("ll","xxx");
    List<Order> list = new ArrayList<>();
    list.add(order);
    list.add(order2);
    User user = new User();
    user.setFirstName("ll");
    user.setOrders(list);
    return user;
  }

  @PostMapping("/mvc")
  public String HelloMVC(@RequestBody Person person){
    System.out.println("person : "  + person.toString());
    return "Hello MVC";
  }

  @PostMapping("/param")
  public String strParameter(Integer id){
    System.out.println("id:" + id);
    return "hello parameter";
  }
}
