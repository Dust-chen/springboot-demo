package com.example.demo;

import com.example.demo.rabbitmq.producer.ConfirmProducerDemo12;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class ConfirmRabbitTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConfirmProducerDemo12 producer;

    @Test
    public void test() throws InterruptedException {
        for (int id = 1; id <= 2; id++) {
            producer.syncSend(id);
            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
