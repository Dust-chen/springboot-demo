package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.ProdAsyncConfirmMessageDemo14;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdAsyncConfirmRabbitConfig {
    // 创建 Queue
    @Bean
    public Queue demo14Queue() {
        return new Queue(ProdAsyncConfirmMessageDemo14.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }
    // 创建 Direct Exchange
    @Bean
    public DirectExchange demo14Exchange() {
        return new DirectExchange(ProdAsyncConfirmMessageDemo14.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }

    @Bean
    public Binding demo14Binding() {
        return BindingBuilder.bind(demo14Queue()).to(demo14Exchange()).with(ProdAsyncConfirmMessageDemo14.ROUTING_KEY);
    }
}
