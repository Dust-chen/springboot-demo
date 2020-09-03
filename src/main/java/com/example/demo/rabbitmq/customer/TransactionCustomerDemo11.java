package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.TransactionMessageDemo11;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = TransactionMessageDemo11.QUEUE_NAME)
public class TransactionCustomerDemo11 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(TransactionMessageDemo11 message){
        logger.info("[onTransactionMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
