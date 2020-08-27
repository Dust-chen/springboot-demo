package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.MessageDemo2;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.rabbitmq.message.MessageDemo1;

@Component
public class ProducerDemo2 {
    @Autowired
    private BatchingRabbitTemplate rabbitTemplate;

    // 同步发送消息
    public void syncSend(Integer id){
        MessageDemo1 message = new MessageDemo1();
        message.setId(id);

        rabbitTemplate.convertAndSend(MessageDemo2.EXCHANGE, MessageDemo2.ROUTING_KEY, message);
    }
}
