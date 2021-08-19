package com.jaclon.spring.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 官方文档 https://docs.spring.io/spring-kafka/docs/2.2.0.RELEASE/reference/html/_reference.html#message-listeners
 *
 * @author jaclon
 * @since 2021/8/19 14:23
 */
@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class,args);
    }
}
