package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.BroadcastMessageDemo8;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BroadcastProducerDemo8 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        BroadcastMessageDemo8 message = new BroadcastMessageDemo8();
        message.setId(id);

        rabbitTemplate.convertAndSend(BroadcastMessageDemo8.EXCHANGE, null, message);
    }
}
