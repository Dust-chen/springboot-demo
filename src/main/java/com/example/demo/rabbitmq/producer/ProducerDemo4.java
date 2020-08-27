package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.MessageDemo3;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerDemo3 {
    @Autowired
    private BatchingRabbitTemplate rabbitTemplate;

    // 同步发送消息
    public void syncSend(Integer id){
        MessageDemo3 message = new MessageDemo3();
        message.setId(id);

        rabbitTemplate.convertAndSend(MessageDemo3.EXCHANGE, MessageDemo3.ROUTING_KEY, message);
    }
}
