package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.MessageDemo5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MessageDemo5.DELAY_QUEUE)
public class CustomerDemo5 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(MessageDemo5 message){
        logger.info("[onDelayMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
