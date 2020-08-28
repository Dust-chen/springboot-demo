package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.MessageDemo4;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryRabbitConfig {

    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {
        // 创建queue
        @Bean
        public Queue demo04Queue() {
            return QueueBuilder.durable(MessageDemo4.QUEUE) // durable: 是否持久化
                    //.exclusive() // exclusive: 是否排它
                    .autoDelete() // autoDelete: 是否自动删除
                    .deadLetterExchange(MessageDemo4.EXCHANGE).deadLetterRoutingKey(MessageDemo4.DEAD_ROUTING_KEY).build();
        }

        // 创建死信队列
        @Bean
        public Queue demo04DeadQueue() {
            return new Queue(MessageDemo4.DEAD_QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建direct交换器
        @Bean
        public DirectExchange demo04Exchange() {
            return new DirectExchange(MessageDemo4.EXCHANGE,
                    true, // durable: 是否持久化
                    false); // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo04Message.EXCHANGE
        // Routing key：Demo04Message.ROUTING_KEY
        // Queue：Demo04Message.QUEUE
        @Bean
        public Binding demo04Binding() {
            return BindingBuilder.bind(demo04Queue()).to(demo04Exchange()).with(MessageDemo4.ROUTING_KEY);
        }

        // 创建 Dead Binding
        // Exchange：Demo04Message.EXCHANGE
        // Routing key：Demo04Message.DEAD_ROUTING_KEY
        // Queue：Demo04Message.DEAD_QUEUE
        @Bean
        public Binding demo04DeadBinding() {
            return BindingBuilder.bind(demo04DeadQueue()).to(demo04Exchange()).with(MessageDemo4.DEAD_ROUTING_KEY);
        }
    }
}
