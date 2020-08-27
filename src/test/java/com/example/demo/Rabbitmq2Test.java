package com.example.demo;

import com.example.demo.rabbitmq.message.MessageDemo1;
import com.example.demo.rabbitmq.producer.ProducerDemo1;
import com.example.demo.rabbitmq.producer.ProducerDemo2;
import com.example.demo.service.DemoService;
import com.rabbitmq.client.*;
import org.apache.juli.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.*;

@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoService demoService;
    @Autowired
    private ProducerDemo1 producerDemo1;
    @Autowired
    private ProducerDemo2 producerDemo2;

    private static final String IP_ADDRESS = "47.98.46.185";
    private static final Integer PORT = 5672;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private static final String EXCHANGE_NAME_1 = "exchange_demo_topic_1";
    private static final String EXCHANGE_NAME_2 = "exchange_demo_topic_2";
    private static final String ROUTING_KEY_1 = "routingkey_demo.add";
    private static final String ROUTING_KEY_2 = "routingkey_demo.#";
    public static final String QUEUE_NAME_1 = "queue_demo_1";
    public static final String QUEUE_NAME_2 = "queue_demo_2";

    private static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        return factory.newConnection();
    }

    // 创建 RabbitMQ Exchange 和 Queue ，然后使用 ROUTING_KEY 路由键将两者绑定。
    // 该步骤，其实可以在 RabbitMQ Management 上操作，并不一定需要在代码中
    private static void initExchangeAndQueue(Channel channel) throws IOException {
        // 创建交换器：direct、持久化、不自动删除
        channel.exchangeDeclare(EXCHANGE_NAME_1, "topic", true, false, null);
//        channel.exchangeDeclare(EXCHANGE_NAME_2, "topic", true, false, null);

        // 创建队列：持久化、非排他、非自动删除的队列
        channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);

        // 将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME_1, ROUTING_KEY_1);
//        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME_2, ROUTING_KEY_2);
        channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME_1, ROUTING_KEY_2);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void rabbitmqProducterTest() throws IOException, TimeoutException {
        // 创建连接
        Connection connection = getConnection();

        // 创建信道
        Channel channel = connection.createChannel();

        // 初始化测试用的 Exchange 和 Queue
        initExchangeAndQueue(channel);

        // 发送 3 条消息
        for (int i = 0; i < 3; i++) {
            String message = "Hello World" + i;
            channel.basicPublish(EXCHANGE_NAME_1, ROUTING_KEY_1, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
//            channel.basicPublish(EXCHANGE_NAME_2, ROUTING_KEY_2, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        }

        // 关闭
        channel.close();
        connection.close();
    }

    @Test
    void rabbitmqCuntomer1Test() throws IOException, TimeoutException {
        // 创建连接
        Connection connection = getConnection();

        // 创建信道
        final Channel channel = connection.createChannel();
        channel.basicQos(64); // 设置客户端最多接收未被 ack 的消息数量为 64 。

        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 打印日志
                System.out.println(String.format("[线程：%s][路由键：%s][消息内容：%s]", Thread.currentThread(), envelope.getRoutingKey(), new String(body)));
                // ack 消息已经消费
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        };
        // 订阅消费 QUEUE_NAME 队列
        channel.basicConsume(QUEUE_NAME_1, consumer);

        // 关闭
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        channel.close();
        connection.close();
    }

    @Test
    public void rabbitmqCustomer2Test() throws IOException, TimeoutException{
        // 创建连接
        Connection connection = getConnection();

        // 创建信道
        Channel channel = connection.createChannel();
        channel.basicQos(64); // 设置客户端最多接收未被 ack 的消息数量为 64 。

        // 创建消费者
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 打印日志
                System.out.println(String.format("[线程：%s][路由键：%s][消息内容：%s]", Thread.currentThread(), envelope.getRoutingKey(), new String(body)));
                // ack 消息已经消费
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        };
        // 订阅消费 QUEUE_NAME 队列
        channel.basicConsume(QUEUE_NAME_2, consumer);

        // 关闭
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        channel.close();
        connection.close();
    }

    @Test
    public void testAsync1(){
        // 同步任务
        long now = System.currentTimeMillis();
        demoService.excuteLog1();
        demoService.excuteLog2();

        System.out.println("任务1执行时间：" + (System.currentTimeMillis() - now));
    }

    @Test
    public void testAsync2(){
        long now = System.currentTimeMillis();
        // 发起异步执行任务，但执行未完成
        demoService.excuteLogForAsync1();
        demoService.excuteLogForAsync2();

        System.out.println("任务2执行时间：" + (System.currentTimeMillis() - now));
    }

    @Test
    public void testAsync3() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        // 发起异步任务
        Future<Integer> integerFutureResult1 = demoService.executeAsyncWithFuture1();
        Future<Integer> integerFutureResult2 = demoService.executeAsyncWithFuture2();
        // 阻塞等待结果
        integerFutureResult1.get();
        integerFutureResult2.get();

        System.out.println("任务3执行时间：" + (System.currentTimeMillis() - now));
    }

    @Test
    public void testSyncRabbitMqSend() throws InterruptedException {
        int id = 10;
        producerDemo1.syncSend(id);
        logger.info("同步发送：{}", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testBatchingRabbitSend() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            // 同步发送消息
            int id = (int) (System.currentTimeMillis() / 1000);
            producerDemo2.syncSend(id);

            // 故意每条消息之间，隔离 10 秒
            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            Thread.sleep(10 * 1000L);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
