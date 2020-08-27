package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.MessageDemo5;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelayRabbitConfig {
    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建queue
        @Bean
        public Queue demo05Queue() {
            return QueueBuilder.durable(MessageDemo5.QUEUE) // durable: 是否持久化
                    .exclusive() // exclusive: 是否排它
                    .autoDelete() // autoDelete: 是否自动删除
                    .ttl(10 * 1000) // 设置队列里的默认过期时间为 10 秒
                    .deadLetterExchange(MessageDemo5.EXCHANGE).deadLetterRoutingKey(MessageDemo5.DELAY_ROUTING_KEY).build();
        }

        // 创建delayqueue
        @Bean
        public Queue demo05DelayQueue() {
            return new Queue(MessageDemo5.DELAY_QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建exchange
        @Bean
        public DirectExchange demo05Exchange() {
            return new DirectExchange(MessageDemo5.EXCHANGE,
                    true,
                    false);
        }

        // 创建binding
        @Bean
        public Binding demo05Binding(){
            return BindingBuilder.bind(demo05Queue()).to(demo05Exchange()).with(MessageDemo5.ROUTING_KEY);
        }

        // 创建延时binding
        @Bean
        public Binding demo05DelayBinding(){
            return BindingBuilder.bind(demo05DelayQueue()).to(demo05Exchange()).with(MessageDemo5.DELAY_ROUTING_KEY);
        }
    }
}
