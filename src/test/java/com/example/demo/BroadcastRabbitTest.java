package com.example.demo;

import com.example.demo.rabbitmq.producer.BroadcastProducerDemo8;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class BroadcastRabbitTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BroadcastProducerDemo8 broadcastProducerDemo8;

    @Test
    public void mock() throws InterruptedException {
        new CountDownLatch(1).await();
    }

    @Test
    public void testSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        broadcastProducerDemo8.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
