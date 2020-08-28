package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.ClusteringMessageDemo7;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClusteringProducerDemo7 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        ClusteringMessageDemo7 message = new ClusteringMessageDemo7();
        message.setId(id);

        rabbitTemplate.convertAndSend(ClusteringMessageDemo7.EXCHANGE, null, message);
    }
}
