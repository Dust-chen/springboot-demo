package com.example.demo.rabbitmq.message;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageDemo3 implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_03";
    public static final String EXCHANGE = "EXCHANGE_DEMO_03";
    public static final String ROUTING_KEY = "ROUTING_DEMO_03";

    private Integer id;
}
