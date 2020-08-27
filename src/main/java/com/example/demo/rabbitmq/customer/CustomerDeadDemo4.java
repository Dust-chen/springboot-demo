package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.MessageDemo4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MessageDemo4.DEAD_QUEUE)
public class CustomerDeadDemo4 {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 批量消费
    @RabbitHandler
    public void onMessage(MessageDemo4 message){
        logger.info("[onDeadMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
