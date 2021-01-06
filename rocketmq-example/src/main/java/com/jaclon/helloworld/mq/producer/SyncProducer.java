/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.helloworld.mq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Classname SyncProducer
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/6/29
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my_mq_group");
        producer.setNamesrvAddr("192.168.8.2:9876");
        //producer.setNamesrvAddr("10.1.2.21:9876");
        producer.start();
        //producer.createTopic();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest", "TagA",
                    ("Hello MQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n",sendResult);
            //Thread.sleep(1000);
        }
        producer.shutdown();
    }
}
