package com.jaclon.consumer;

import com.jaclon.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = RabbitMQConfig.RABBITMQ_DEMO_TOPIC)
public class RabbitDemoConsumer {
    @RabbitHandler
    public void process(Map map){
        System.out.println("rabbitmq消费消息: " + map.toString());
    }
}
