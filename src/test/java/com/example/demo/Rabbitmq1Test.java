package com.example.demo;

import java.util.concurrent.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.rabbitmq.producer.ProducerDemo1;

@SpringBootTest(classes = DemoApplication.class)
class Rabbitmq1Test {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProducerDemo1 producerDemo1;

    @Test
    public void testSyncRabbitMqSend() throws InterruptedException {
        int id = 10;
        producerDemo1.syncSend(id);
        logger.info("同步发送：{}", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
