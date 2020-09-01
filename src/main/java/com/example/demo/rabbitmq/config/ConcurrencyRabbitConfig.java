package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.ConcurrencyMessageDemo9;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcurrencyRabbitConfig {

    @Bean
    public Queue queueDemo09() {
        return new Queue(ConcurrencyMessageDemo9.QUEUE_NAME, true, false, false);
    }

    @Bean
    public DirectExchange exchangeDemo09(){
        return new DirectExchange(ConcurrencyMessageDemo9.EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding bindingDemo09(){
        return BindingBuilder.bind(queueDemo09()).to(exchangeDemo09()).with(ConcurrencyMessageDemo9.ROUTING_KEY);
    }
}
