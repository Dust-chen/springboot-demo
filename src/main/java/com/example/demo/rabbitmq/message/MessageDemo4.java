package com.example.demo.rabbitmq.message;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDemo4 implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_04";
    public static final String DEAD_QUEUE = "DEAD_QUEUE_DEMO_04";// 死信队列

    public static final String EXCHANGE = "EXCHANGE_DEMO_04";

    public static final String ROUTING_KEY = "ROUTING_DEMO_04";
    public static final String DEAD_ROUTING_KEY = "DEAD_ROUTING_DEMO_04";// 死信路由键

    private Integer id;
}
