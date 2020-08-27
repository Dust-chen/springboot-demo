package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.MessageDemo1;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProducerDemo1 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 同步发送消息
    public void syncSend(Integer id){
        MessageDemo1 message = new MessageDemo1();
        message.setId(id);

        rabbitTemplate.convertAndSend(MessageDemo1.EXCHANGE, MessageDemo1.ROUTING_KEY, message);
    }
}
