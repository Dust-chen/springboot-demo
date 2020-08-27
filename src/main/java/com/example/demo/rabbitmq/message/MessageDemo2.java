package com.example.demo.rabbitmq.message;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDemo2 implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_02";
    public static final String EXCHANGE = "EXCHANGE_DEMO_02";
    public static final String ROUTING_KEY = "ROUTING_DEMO_02";

    private Integer id;
}
