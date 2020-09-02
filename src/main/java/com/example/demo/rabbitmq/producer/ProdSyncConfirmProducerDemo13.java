package com.example.demo.rabbitmq.producer;

import com.example.demo.rabbitmq.message.ProdSyncConfirmMessageDemo13;
import com.rabbitmq.client.ConfirmCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProdSyncConfirmProducerDemo13 {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        ProdSyncConfirmMessageDemo13 message = new ProdSyncConfirmMessageDemo13();
        message.setId(id);

        // 同步发送消息
        rabbitTemplate.invoke(new RabbitOperations.OperationsCallback<Object>() {
            @Override
            public Object doInRabbit(RabbitOperations rabbitOperations) {
                // 同步发送
                rabbitOperations.convertAndSend(ProdSyncConfirmMessageDemo13.EXCHANGE, ProdSyncConfirmMessageDemo13.ROUTING_KEY, message);
                logger.info("[doInRabbit][发送消息完成]");
                // 等待确认
                rabbitOperations.waitForConfirms(0);// timeout 参数，如果传递 0 ，表示无限等待
                logger.info("[doInRabbit][等待 Confirm 完成]");
                return null;
            }
        }, new ConfirmCallback() {
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {
                logger.info("[handle][Confirm 成功]");
            }
        }, new ConfirmCallback() {
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {
                logger.info("[handle][Confirm 失败]");
            }
        });
    }
}
