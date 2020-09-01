package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.OrderMessageDemo10;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitConfig {

    @Bean
    public Queue queueDemo10Queue0(){
        return new Queue(OrderMessageDemo10.QUEUE_0);
    }

    @Bean
    public Queue queueDemo10Queue1(){
        return new Queue(OrderMessageDemo10.QUEUE_1);
    }

    @Bean
    public Queue queueDemo10Queue2(){
        return new Queue(OrderMessageDemo10.QUEUE_2);
    }

    @Bean
    public Queue queueDemo10Queue3(){
        return new Queue(OrderMessageDemo10.QUEUE_3);
    }

    @Bean
    public DirectExchange exchangeDemo10(){
        return new DirectExchange(OrderMessageDemo10.EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding bindingDemo10Queue0(){
        return BindingBuilder.bind(queueDemo10Queue0()).to(exchangeDemo10()).with("0");
    }

    @Bean
    public Binding bindingDemo10Queue1(){
        return BindingBuilder.bind(queueDemo10Queue1()).to(exchangeDemo10()).with("1");
    }

    @Bean
    public Binding bindingDemo10Queue2(){
        return BindingBuilder.bind(queueDemo10Queue2()).to(exchangeDemo10()).with("2");
    }

    @Bean
    public Binding bindingDemo10Queue3(){
        return BindingBuilder.bind(queueDemo10Queue3()).to(exchangeDemo10()).with("3");
    }
}
