package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.MessageDemo6;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RabbitListener(queues = MessageDemo6.QUEUE)
public class CustomerDemo6 {

    @RabbitHandler
    public void onMessage(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息接收时间:" + sdf.format(new Date()));
        System.out.println("接收到的消息:" + message);
    }
}
