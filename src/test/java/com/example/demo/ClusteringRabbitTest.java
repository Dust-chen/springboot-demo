package com.example.demo;

import com.example.demo.rabbitmq.producer.ClusteringProducerDemo7;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class ClusteringRabbitTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClusteringProducerDemo7 clusteringProducerDemo7;

    @Test
    public void mock() throws InterruptedException {
        new CountDownLatch(1).await();
    }

    @Test
    public void testSyncSend() throws InterruptedException {
        // 发送 3 条消息
        for (int i = 0; i < 3; i++) {
            int id = (int) (System.currentTimeMillis() / 1000);
            clusteringProducerDemo7.syncSend(id);
            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
