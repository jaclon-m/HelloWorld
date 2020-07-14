package com.jaclon.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jaclon
 * @date 2020/7/14
 */
@SpringBootApplication
@EnableDubbo
public class UserServiceApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication9001.class,args);
    }
}
