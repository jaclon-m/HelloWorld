package com.jaclon.spring.project.example.ioc;

import com.jaclon.spring.project.example.bean.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO Description
 * @author jaclon
 * @since 2020/12/1 上午11:23
 */
public class HelloWorld {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "applicationContext.xml");
    Person person = (Person)applicationContext.getBean("zhangsan");
    System.out.println(person);
    System.out.println(person);
  }
}
