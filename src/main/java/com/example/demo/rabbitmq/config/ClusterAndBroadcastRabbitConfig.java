package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.BroadcastMessageDemo8;
import com.example.demo.rabbitmq.message.ClusteringMessageDemo7;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClusterAndBroadcastRabbitConfig {

    public static class ClusteringConfiguration {
        @Bean
        public TopicExchange clusteringExchange() {
            return new TopicExchange(ClusteringMessageDemo7.EXCHANGE,
                    true, // durable: 是否持久化
                    false); // exclusive: 是否排它
        }
    }

    public static class BroadcastingConfiguration{
        // 创建 Topic Exchange
        @Bean
        public TopicExchange broadcastingExchange() {
            return new TopicExchange(BroadcastMessageDemo8.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
    }
}
