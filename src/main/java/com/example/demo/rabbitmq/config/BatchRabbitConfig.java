package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.MessageDemo1;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
public class RabbitConfig {
    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {
        // 创建 Queue
        @Bean
        public Queue demo01Queue() {
            return new Queue(MessageDemo1.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo01Exchange() {
            return new DirectExchange(MessageDemo1.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        // 创建 Binding
        // Exchange：Demo01Message.EXCHANGE
        // Routing key：Demo01Message.ROUTING_KEY
        // Queue：Demo01Message.QUEUE
        @Bean
        public Binding demo01Binding() {
            return BindingBuilder.bind(demo01Queue()).to(demo01Exchange()).with(MessageDemo1.ROUTING_KEY);
        }
    }

    @Bean
    public BatchingRabbitTemplate batchingRabbitTemplate(ConnectionFactory connectionFactory){
        // 创建BatchingStrategy 对象，代表批量策略
        int batchSize = 16384; // 超过收集的消息数量的最大条数。
        int bufferLimit = 33554432; // 每次批量发送消息的最大内存
        int timeout = 30000; // 超过收集的时间的最大等待时长，单位：毫秒
        BatchingStrategy batchingStrategy = new SimpleBatchingStrategy(batchSize, bufferLimit, timeout);

        // 创建 TaskScheduler 对象，用于实现超时发送的定时器
        TaskScheduler taskScheduler = new ConcurrentTaskScheduler();

        // 创建 BatchingRabbitTemplate 对象
        BatchingRabbitTemplate batchTemplate = new BatchingRabbitTemplate(batchingStrategy, taskScheduler);
        batchTemplate.setConnectionFactory(connectionFactory);
        return batchTemplate;
    }
}