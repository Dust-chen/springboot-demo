package com.example.demo;

import com.example.demo.rabbitmq.producer.ConverterProducerDemo15;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class ConverterRabbitTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConverterProducerDemo15 producer;

    @Test
    public void test() throws InterruptedException {
        Integer id = (int)System.currentTimeMillis() / 1000;
        producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        new CountDownLatch(1).await();
    }
}
