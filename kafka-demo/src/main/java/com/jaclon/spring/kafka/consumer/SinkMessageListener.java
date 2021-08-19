package com.jaclon.spring.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/19 17:55
 */
@Component
@Slf4j
public class SinkMessageListener implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 指定topics，可以指定多个，最好每个topic对应一个
     * id: 填写的时候会替换groupId，除非设置idIsGroup属性；kafka多线程情况下会打印线程名，没有id会自动生成，有id则用id+线程
     * @param cr
     * @throws Exception
     */
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

    /**
     * 非手动提交方式，需要进行确认
     *  java.lang.IllegalStateException: Consumer cannot be configured for auto commit for ackMode MANUAL_IMMEDIATE
     *  则 props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
     * @param cr
     * @param acknowledgment
     * @throws Exception
     */
    @KafkaListener(id = "test-consumer", topics = {"realtime.analytics_h5_source"})
    public void listen(ConsumerRecord<String, String> cr, Acknowledgment acknowledgment) throws Exception {
        log.debug(Thread.currentThread().getName() + "  {}", cr.value());
        // 注意: 比 quickstart 读了一个Acknowledgment参数
        acknowledgment.acknowledge();
    }
}