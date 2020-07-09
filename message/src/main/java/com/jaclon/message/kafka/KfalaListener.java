/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.message.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Classname KfalaListener
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/2/11
 */
//@Component
@Slf4j
public class KfalaListener {

    @KafkaListener(groupId = "variable_hand_out",topics = {"zhisheng"})
    public void consumer(ConsumerRecord<?,?> consumerRecord){
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        log.info(">>>>>>>>>> record =" + kafkaMessage);
        if(kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();

            log.info("----------------- record =" + consumerRecord);
            log.info("------------------ message =" + message);

        }

    }
}
