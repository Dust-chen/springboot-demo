package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.TransactionMessageDemo11;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// 开启spring事务支持
@EnableTransactionManagement
public class TrasactionRabbitConfig {

    @Bean
    public Queue queueDemo11(){
        return new Queue(TransactionMessageDemo11.QUEUE_NAME, true, false, false);
    }

    @Bean
    public DirectExchange exchangeDemo11(){
        return new DirectExchange(TransactionMessageDemo11.EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding bindingDemo11(){
        return BindingBuilder.bind(queueDemo11()).to(exchangeDemo11()).with(TransactionMessageDemo11.ROUTING_KEY_NAME);
    }

//    @Bean
//    public RabbitTransactionManager rabbitTransactionManager(ConnectionFactory connectionFactory, RabbitTemplate rabbitTemplate){
//        // <Y> 设置 RabbitTemplate 支持事务
//        rabbitTemplate.setChannelTransacted(true);
//
//        // 创建 RabbitTransactionManager 对象
//        return new RabbitTransactionManager(connectionFactory);
//    }
}
