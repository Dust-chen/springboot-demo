package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.ConfirmMessageDemo12;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmProducerDemo12 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        ConfirmMessageDemo12 message = new ConfirmMessageDemo12();
        message.setId(id);
        rabbitTemplate.convertAndSend(ConfirmMessageDemo12.EXCHANGE, ConfirmMessageDemo12.ROUTING_KEY, message);
    }
}
