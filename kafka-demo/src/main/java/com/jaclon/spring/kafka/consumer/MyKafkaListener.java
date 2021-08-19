package com.jaclon.spring.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/19 14:40
 */
@Component
public class MyKafkaListener {

    @KafkaListener(topics = "testTopic")
    public void onMessage(String message){
        System.out.println("message: " + message);
    }
}
