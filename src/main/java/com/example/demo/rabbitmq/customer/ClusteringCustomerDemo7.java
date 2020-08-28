package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.ClusteringMessageDemo7;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings =
@QueueBinding(
        value = @Queue(
                name = ClusteringMessageDemo7.QUEUE + "-GROUP-01"
        ),
        exchange = @Exchange(
                name = ClusteringMessageDemo7.EXCHANGE,
                type = ExchangeTypes.TOPIC,
                declare = "false"
        ),
        key = "#"
))
public class ClusteringCustomerDemo7 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(ClusteringMessageDemo7 message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
