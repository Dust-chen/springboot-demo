package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProdAsyncConfirmMessageDemo14 implements Serializable {
    public static final String QUEUE = "QUEUE_DEMO_14";
    public static final String EXCHANGE = "EXCHANGE_DEMO_14";
    public static final String ROUTING_KEY = "ROUTING_DEMO_14";

    private Integer id;
}
