package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.ErrorHandlerMessageDemo16;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorHandlerRabbitConfig {
    // 创建 Queue
    @Bean
    public Queue demo16Queue() {
        return new Queue(ErrorHandlerMessageDemo16.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }
    // 创建 Direct Exchange
    @Bean
    public DirectExchange demo16Exchange() {
        return new DirectExchange(ErrorHandlerMessageDemo16.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }
    // 创建 Binding
    @Bean
    public Binding demo16Binding() {
        return BindingBuilder.bind(demo16Queue()).to(demo16Exchange()).with(ErrorHandlerMessageDemo16.ROUTING_KEY);
    }
}
