package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.OrderMessageDemo10;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProducerDemo10 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        OrderMessageDemo10 message = new OrderMessageDemo10();
        message.setId(id);
        rabbitTemplate.convertAndSend(OrderMessageDemo10.EXCHANGE_NAME, getRoutingKey(id), message);
    }

    private String getRoutingKey(Integer id){
        return String.valueOf(id % OrderMessageDemo10.QUEUE_COUNT);
    }
}
