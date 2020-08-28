package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.MessageDemo6;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayPluginRabbitConfig {
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(MessageDemo6.EXCHANGE, "x-delayed-message",true, false,args);
    }

    @Bean
    public Queue queue() {
        Queue queue = new Queue(MessageDemo6.QUEUE, true);
        return queue;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(delayExchange()).with(MessageDemo6.ROUTING_KEY).noargs();
    }
}
