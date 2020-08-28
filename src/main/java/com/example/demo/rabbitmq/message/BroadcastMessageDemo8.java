package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BroadcastMessageDemo8 implements Serializable {
    public static final String QUEUE = "QUEUE_BROADCASTING";

    public static final String EXCHANGE = "EXCHANGE_BROADCASTING";

    private Integer id;
}
