package com.example.demo;

import com.example.demo.rabbitmq.producer.OrderProducerDemo10;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoApplication.class)
public class OrderRabbitTest {
    @Autowired
    private OrderProducerDemo10 orderProducer;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            for (int id = 0; id < 4; id++) {
                orderProducer.syncSend(id);
//            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            }
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
