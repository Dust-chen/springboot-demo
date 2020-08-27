package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.MessageDemo2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.rabbitmq.message.MessageDemo1;

import java.util.List;

@Component
@RabbitListener(queues = MessageDemo2.QUEUE
        // 申明批量消费消息
        ,containerFactory = "consumerBatchContainerFactory")
public class CustomerDemo2 {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(MessageDemo1 message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

    // 批量消费
    @RabbitHandler
    public void onBatchMessage(List<MessageDemo2> messages){
        logger.info("[onMessage][线程编号:{} 消息数量：{}]", Thread.currentThread().getId(), messages.size());
    }
}
