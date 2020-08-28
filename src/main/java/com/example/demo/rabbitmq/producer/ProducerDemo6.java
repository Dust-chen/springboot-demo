package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.MessageDemo6;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ProducerDemo6 {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:"+sdf.format(new Date()));
        rabbitTemplate.convertAndSend(MessageDemo6.EXCHANGE, MessageDemo6.ROUTING_KEY, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay",3000);
                return message;
            }
        });
    }
}
