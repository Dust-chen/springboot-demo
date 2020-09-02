package com.example.demo.rabbitmq.customer;

import com.example.demo.rabbitmq.message.ProdSyncConfirmMessageDemo13;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ProdSyncConfirmMessageDemo13.QUEUE)
public class ProdSyncConfirmCustomerDemo13 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(ProdSyncConfirmMessageDemo13 message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
