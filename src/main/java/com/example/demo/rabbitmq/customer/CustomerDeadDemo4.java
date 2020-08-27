package com.example.demo.rabbitmq.customer;

import java.util.List;

import com.example.demo.rabbitmq.message.MessageDemo3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MessageDemo3.QUEUE
        // 申明批量消费消息
        ,containerFactory = "consumerBatchContainerFactory2")
public class CustomerDemo3 {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 批量消费
    @RabbitHandler
    public void onBatchMessage(List<MessageDemo3> messages){
        logger.info("[onMessage][线程编号:{} 消息数量：{}]", Thread.currentThread().getId(), messages.size());
    }
}
