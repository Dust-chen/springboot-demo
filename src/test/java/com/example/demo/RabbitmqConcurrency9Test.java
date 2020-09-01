package com.example.demo;

import com.example.demo.rabbitmq.producer.ConcurrencyProducerDemo9;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class RabbitmqConcurrency9Test {
    @Autowired
    private ConcurrencyProducerDemo9 concurrencyProducer;

    @Test
    public void test() throws InterruptedException {
        Integer id = ((int)System.currentTimeMillis()) / 1000;
        concurrencyProducer.syncSend(id);

        new CountDownLatch(1).await();
    }

}
