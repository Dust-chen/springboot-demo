package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.ErrorHandlerMessageDemo16;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandlerProducerDemo16 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        ErrorHandlerMessageDemo16 message = new ErrorHandlerMessageDemo16();
        message.setId(id);

        rabbitTemplate.convertAndSend(ErrorHandlerMessageDemo16.EXCHANGE, ErrorHandlerMessageDemo16.ROUTING_KEY, message);
    }
}
