package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.BroadcastMessageDemo8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(name = BroadcastMessageDemo8.QUEUE + "-" + "#{T(java.util.UUID).randomUUID()}",
                autoDelete = "true"),
                exchange = @Exchange(
                        name = BroadcastMessageDemo8.EXCHANGE,
                        type = ExchangeTypes.TOPIC,
                        declare = "false"
                )
        ))
public class BroadcastConsumerDemo8 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(BroadcastMessageDemo8 message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
