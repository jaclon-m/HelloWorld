package com.jaclon.spring.project.example;

import com.jaclon.spring.project.example.aop.BuyService;
import com.jaclon.spring.project.example.aop.ChatService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringProjectExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringProjectExampleApplication.class, args);
    //testAOP();
  }

  public static void testAOP(){
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringProjectExampleApplication.class);
    BuyService buyService = applicationContext.getBean(BuyService.class);
    buyService.buyItem(1);

    ChatService chatService = applicationContext.getBean(ChatService.class);
    chatService.chat(123);
  }


}
