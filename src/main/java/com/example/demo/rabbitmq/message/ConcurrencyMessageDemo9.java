package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ConcurrencyMessageDemo9 implements Serializable {
    public static final String QUEUE_NAME = "QUEUE_DEMO_09";
    public static final String EXCHANGE_NAME = "EXCHANGE_NAME_DEMO_09";
    public static final String ROUTING_KEY = "ROUTING_KEY_DEMO_09";

    public Integer id;
}
