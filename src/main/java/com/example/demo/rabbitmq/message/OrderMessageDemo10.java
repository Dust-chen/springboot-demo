package com.example.demo.rabbitmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderMessageDemo10 implements Serializable {
    public static final String QUEUE_BASIC = "QUEUE_NAME_DEMO_10-";
    public static final String QUEUE_0 = QUEUE_BASIC + "0";
    public static final String QUEUE_1 = QUEUE_BASIC + "1";
    public static final String QUEUE_2 = QUEUE_BASIC + "2";
    public static final String QUEUE_3 = QUEUE_BASIC + "3";

    public static final Integer QUEUE_COUNT = 4;

    public static final String EXCHANGE_NAME = "EXCHANGE_NAME_DMEO_10";

    public Integer id;
}
