package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MessageDemo5 implements Serializable {
    public static final String QUEUE = "QUEUE_DEMO_05";
    public static final String DELAY_QUEUE = "DELAY_QUEUE_DEMO_05";// 延迟队列（死信队列）

    public static final String EXCHANGE = "EXCHANGE_DEMO_05";

    public static final String ROUTING_KEY = "ROUTING_DEMO_05";
    public static final String DELAY_ROUTING_KEY = "DELAY_ROUTING_DEMO_05";// 延迟路由键（死信路由键）

    private Integer id;
}
