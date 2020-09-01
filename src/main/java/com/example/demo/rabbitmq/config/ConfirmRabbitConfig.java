package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.ConfirmMessageDemo12;
import com.example.demo.rabbitmq.message.TransactionMessageDemo11;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfirmRabbitConfig {
    @Bean
    public Queue queueDemo12(){
        return new Queue(ConfirmMessageDemo12.QUEUE, true, false, false);
    }

    @Bean
    public DirectExchange exchangeDemo12(){
        return new DirectExchange(ConfirmMessageDemo12.EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingDemo12(){
        return BindingBuilder.bind(queueDemo12()).to(exchangeDemo12()).with(ConfirmMessageDemo12.ROUTING_KEY);
    }
}
