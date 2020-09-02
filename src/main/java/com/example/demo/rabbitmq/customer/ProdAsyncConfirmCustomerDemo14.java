package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.ProdAsyncConfirmMessageDemo14;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ProdAsyncConfirmMessageDemo14.QUEUE)
public class ProdAsyncConfirmCustomerDemo14 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(ProdAsyncConfirmMessageDemo14 message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
