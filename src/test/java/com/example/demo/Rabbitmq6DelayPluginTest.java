package com.example.demo;

import com.example.demo.rabbitmq.producer.ProducerDemo6;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class Rabbitmq6DelayPluginTest {
    @Autowired
    private ProducerDemo6 producerDemo6;

    @Test
    public void delayTest() throws InterruptedException {
        producerDemo6.sendMsg("延迟消息发送");

        new CountDownLatch(1).await();
    }
}
