package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MessageDemo1 implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_01";
    public static final String EXCHANGE = "EXCHANGE_DEMO_01";
    public static final String ROUTING_KEY = "ROUTING_DEMO_01";

    private Integer id;
}
