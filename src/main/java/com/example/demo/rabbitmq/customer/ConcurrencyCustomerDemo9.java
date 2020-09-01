package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.ConcurrencyMessageDemo9;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// 开启两个线程消费
@RabbitListener(queues = ConcurrencyMessageDemo9.QUEUE_NAME, concurrency = "2")
public class ConcurrencyCustomerDemo9 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(ConcurrencyMessageDemo9 message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
