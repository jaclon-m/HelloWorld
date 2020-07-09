/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.message.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Classname SyncTest
 * @Description TODO
 *
 * @author jaclon
 * @date 2019/11/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SyncTest {

    @Test
    public void test01() throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("mymq_group");
        producer.setNamesrvAddr("10.1.5.184:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            producer.getCreateTopicKey();
            Message message = new Message("TopicTest", "TagA", ("Hello MQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.printf("%s%n",sendResult);
        }

        producer.shutdown();
    }

}
