package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.ConverterMessageDemo15;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterRabbitConfig {
    // 创建 Queue
    @Bean
    public Queue demo15Queue() {
        return new Queue(ConverterMessageDemo15.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }
    // 创建 Direct Exchange
    @Bean
    public DirectExchange demo15Exchange() {
        return new DirectExchange(ConverterMessageDemo15.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }
    // 创建 Binding
    @Bean
    public Binding demo15Binding() {
        return BindingBuilder.bind(demo15Queue()).to(demo15Exchange()).with(ConverterMessageDemo15.ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
