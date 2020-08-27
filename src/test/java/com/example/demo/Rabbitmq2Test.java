package com.example.demo;

import java.util.concurrent.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.rabbitmq.producer.ProducerDemo2;

@SpringBootTest(classes = DemoApplication.class)
class Rabbitmq2Test {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProducerDemo2 producerDemo2;

    @Test
    public void testBatchingRabbitSend() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            // 同步发送消息
            int id = (int) (System.currentTimeMillis() / 1000);
            producerDemo2.syncSend(id);

            // 故意每条消息之间，隔离 10 秒
            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            Thread.sleep(10 * 1000L);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
