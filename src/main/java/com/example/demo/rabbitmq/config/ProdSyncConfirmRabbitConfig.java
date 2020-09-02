package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.ProdSyncConfirmMessageDemo13;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdSyncConfirmRabbitConfig {
    // 创建 Queue
    @Bean
    public Queue demo13Queue() {
        return new Queue(ProdSyncConfirmMessageDemo13.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }
    // 创建 Direct Exchange
    @Bean
    public DirectExchange demo13Exchange() {
        return new DirectExchange(ProdSyncConfirmMessageDemo13.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }

    @Bean
    public Binding demo13Binding() {
        return BindingBuilder.bind(demo13Queue()).to(demo13Exchange()).with(ProdSyncConfirmMessageDemo13.ROUTING_KEY);
    }
}
