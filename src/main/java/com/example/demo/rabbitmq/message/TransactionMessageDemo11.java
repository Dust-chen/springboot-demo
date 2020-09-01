package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TransactionMessageDemo11 implements Serializable {

    public static final String QUEUE_NAME = "QUEUE_DEMO_11";
    public static final String EXCHANGE_NAME = "EXCHANGE_NAME_11";
    public static final String ROUTING_KEY_NAME = "ROUTING_KEY_DEMO_11";

    public Integer id;
}
