package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.ConverterMessageDemo15;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConverterMessageDemo15.QUEUE)
public class ConverterCustomerDemo15 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler(isDefault = true)
    public void onMessage(Message message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(),
                message.getBody());
    }
}
