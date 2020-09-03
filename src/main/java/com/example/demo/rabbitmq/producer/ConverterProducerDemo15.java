package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.ConverterMessageDemo15;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterProducerDemo15 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        ConverterMessageDemo15 message = new ConverterMessageDemo15();
        message.setId(id);
        rabbitTemplate.convertAndSend(ConverterMessageDemo15.EXCHANGE, ConverterMessageDemo15.ROUTING_KEY, message);
    }
}
