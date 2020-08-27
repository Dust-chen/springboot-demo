package com.example.demo;

import com.example.demo.rabbitmq.producer.ProducerDemo4;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
class RabbitmqRetry4Test {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProducerDemo4 producerDemo4;

    @Test
    public void testRetry() throws InterruptedException {
        int id = ((int)System.currentTimeMillis()) / 1000;
        producerDemo4.syncSend(id);
        logger.info("[testSyncSendMore][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
