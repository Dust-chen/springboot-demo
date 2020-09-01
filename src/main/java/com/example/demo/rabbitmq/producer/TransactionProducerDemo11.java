package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.TransactionMessageDemo11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionProducerDemo11 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 声明事务
    @Transactional
    public void syncSend(Integer id) throws InterruptedException {
        TransactionMessageDemo11 message = new TransactionMessageDemo11();
        message.setId(id);

        rabbitTemplate.convertAndSend(TransactionMessageDemo11.EXCHANGE_NAME, TransactionMessageDemo11.ROUTING_KEY_NAME, message);
        logger.info("[syncSend][发送编号：[{}] 发送成功]", id);

        // 等待10秒
        Thread.sleep(10*1000L);
    }
}
