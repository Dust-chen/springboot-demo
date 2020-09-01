package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.OrderMessageDemo10;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = OrderMessageDemo10.QUEUE_0)
@RabbitListener(queues = OrderMessageDemo10.QUEUE_1)
@RabbitListener(queues = OrderMessageDemo10.QUEUE_2)
@RabbitListener(queues = OrderMessageDemo10.QUEUE_3)
public class OrderConsumerDemo10 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler(isDefault = true)
    public void onMessage(Message<OrderMessageDemo10> message){
        logger.info("[onMessage][线程编号:{} Queue:{} 消息编号：{}]", Thread.currentThread().getId(), getQueue(message),
                message.getPayload().getId());
    }

    private static String getQueue(Message<OrderMessageDemo10> message) {
        return message.getHeaders().get("amqp_consumerQueue", String.class);
    }
}
