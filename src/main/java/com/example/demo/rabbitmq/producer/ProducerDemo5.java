package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.MessageDemo5;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerDemo05 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, Integer delay){
        MessageDemo5 message = new MessageDemo5();
        message.setId(id);

        rabbitTemplate.convertAndSend(MessageDemo5.EXCHANGE, MessageDemo5.ROUTING_KEY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设置消息过期时间
                if(delay != null && delay > 0){
                    message.getMessageProperties().setExpiration(String.valueOf(delay));
                }
                return message;
            }
        });
    }
}
