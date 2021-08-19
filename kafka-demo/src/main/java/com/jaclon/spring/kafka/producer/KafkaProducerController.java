package com.jaclon.spring.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/19 14:34
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {
    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;

    @GetMapping("/send")
    public Boolean send(@RequestParam String message){
        try {
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("testTopic", message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true ;
    }

}
