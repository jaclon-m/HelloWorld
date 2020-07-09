/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.message.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Classname SinkMessageListener
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/2/12
 */
//@Component
@Slf4j
public class SinkMessageListener implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // 指定topics，可以指定多个，最好每个topic对应一个
    @KafkaListener(id = "test-consumer", topics = {"realtime.analytics_h5_source"})
    public void listen(ConsumerRecord<String, String> cr) throws Exception {
        log.debug(Thread.currentThread().getName() + "  {}", cr.value());
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("start producer 10000 message ");
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("realtime.analytics_h5_source", UUID.randomUUID().toString(), "i" + i);
        }
    }


}