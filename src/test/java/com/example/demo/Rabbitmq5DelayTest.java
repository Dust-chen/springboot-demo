package com.example.demo;

import com.example.demo.rabbitmq.producer.ProducerDemo5;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class Rabbitmq5DelayTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProducerDemo5 producerDemo5;

    @Test
    public void delayTest() throws InterruptedException {
        int id = ((int) System.currentTimeMillis()) / 1000;
        producerDemo5.syncSend(id);

        logger.info("[testDelayMessage][发送编号：[{}] 发送成功]", id);

        new CountDownLatch(1).await();
    }
}
