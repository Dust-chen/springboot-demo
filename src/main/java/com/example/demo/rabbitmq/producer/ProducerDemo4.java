package com.example.demo.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.rabbitmq.message.MessageDemo4;

@Component
public class ProducerDemo4 {
//    @Autowired
//    private BatchingRabbitTemplate rabbitTemplate;

    // 同步发送消息
    public void syncSend(Integer id){
        MessageDemo4 message = new MessageDemo4();
        message.setId(id);

//        rabbitTemplate.convertAndSend(MessageDemo4.EXCHANGE, MessageDemo4.ROUTING_KEY, message);
    }
}
