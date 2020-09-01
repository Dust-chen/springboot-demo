package com.example.demo;

import com.example.demo.rabbitmq.producer.TransactionProducerDemo11;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class TransactionRabbitTest {
    @Autowired
    private TransactionProducerDemo11 producer;

    @Test
    public void test() throws InterruptedException {
        Integer id = ((int)System.currentTimeMillis()) / 1000;
        producer.syncSend(id);

        new CountDownLatch(1).await();
    }
}
