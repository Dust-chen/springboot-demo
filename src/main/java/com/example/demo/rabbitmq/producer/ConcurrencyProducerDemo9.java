package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.ConcurrencyMessageDemo9;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConcurrencyProducerDemo9 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        ConcurrencyMessageDemo9 message = new ConcurrencyMessageDemo9();
        message.setId(id);

        rabbitTemplate.convertAndSend(ConcurrencyMessageDemo9.EXCHANGE_NAME, ConcurrencyMessageDemo9.ROUTING_KEY,message);
    }
}
