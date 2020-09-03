package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ErrorHandlerMessageDemo16 implements Serializable {
    public static final String QUEUE = "QUEUE_DEMO_16";
    public static final String EXCHANGE = "EXCHANGE_DEMO_16";
    public static final String ROUTING_KEY = "ROUTING_DEMO_16";

    private Integer id;
}
