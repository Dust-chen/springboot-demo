package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClusteringMessageDemo7 implements Serializable {
    public static final String QUEUE = "QUEUE_CLUSTERING";

    public static final String EXCHANGE = "EXCHANGE_CLUSTERING";

    private Integer id;
}
