package com.example.demo;

import com.example.demo.rabbitmq.producer.ErrorHandlerProducerDemo16;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class ErrorHandlerRabbitTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ErrorHandlerProducerDemo16 producer;

    @Test
    public void test() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
