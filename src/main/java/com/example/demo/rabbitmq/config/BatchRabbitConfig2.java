package com.example.demo.rabbitmq.config;

import com.example.demo.rabbitmq.message.MessageDemo3;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchRabbitConfig2 {
    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {
        // 创建 Queue
        @Bean
        public Queue demo03Queue() {
            return new Queue(MessageDemo3.QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }
        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo03Exchange() {
            return new DirectExchange(MessageDemo3.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }
        // 创建 Binding
        // Exchange：Demo01Message.EXCHANGE
        // Routing key：Demo01Message.ROUTING_KEY
        // Queue：Demo01Message.QUEUE
        @Bean
        public Binding demo03Binding() {
            return BindingBuilder.bind(demo03Queue()).to(demo03Exchange()).with(MessageDemo3.ROUTING_KEY);
        }
    }

    @Bean(name = "consumerBatchContainerFactory2")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        // 创建 SimpleRabbitListenerContainerFactory 对象
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        // 额外添加批量消费的属性
        factory.setBatchListener(true);
        // <X> 批量处理数量
        factory.setBatchSize(10);
        // 超时时间
        factory.setReceiveTimeout(30 * 1000L);
        factory.setConsumerBatchEnabled(true);
        return factory;
    }
}
