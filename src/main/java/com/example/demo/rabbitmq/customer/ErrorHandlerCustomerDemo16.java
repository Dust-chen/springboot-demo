package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.ErrorHandlerMessageDemo16;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ErrorHandlerMessageDemo16.QUEUE, errorHandler = "rabbitListenerErrorHandler")
public class ErrorHandlerCustomerDemo16 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(ErrorHandlerMessageDemo16 message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // 模拟消费异常
        throw new RuntimeException("模拟异常");
    }
}
