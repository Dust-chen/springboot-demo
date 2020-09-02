package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.ProdAsyncConfirmMessageDemo14;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdAsyncConfirmProducerDemo14 {
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        ProdAsyncConfirmMessageDemo14 message = new ProdAsyncConfirmMessageDemo14();
        message.setId(id);

        rabbitTemplate.convertAndSend(ProdAsyncConfirmMessageDemo14.EXCHANGE, ProdAsyncConfirmMessageDemo14.ROUTING_KEY, message);
    }

    public void syncErrorSend(Integer id){
        ProdAsyncConfirmMessageDemo14 message = new ProdAsyncConfirmMessageDemo14();
        message.setId(id);

        // 发送无法匹配的消息，触发returnCallback
        rabbitTemplate.convertAndSend(ProdAsyncConfirmMessageDemo14.EXCHANGE, "error", message);
    }
}
