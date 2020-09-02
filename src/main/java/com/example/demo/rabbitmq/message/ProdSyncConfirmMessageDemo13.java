package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProdSyncConfirmMessageDemo13 implements Serializable {
    public static final String QUEUE = "QUEUE_DEMO_13";
    public static final String EXCHANGE = "EXCHANGE_DEMO_13";
    public static final String ROUTING_KEY = "ROUTING_DEMO_13";

    private Integer id;
}
