package com.example.demo.service;

import ch.qos.logback.core.util.TimeUtil;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class DemoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 异步任务
    private void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer excuteLog1(){
        sleep(10);
        logger.info("10秒任务执行");
        return 1;
    }

    public Integer excuteLog2(){
        sleep(5);
        logger.info("5秒任务执行");
        return 2;
    }

    @Async
    public Integer excuteLogForAsync1(){
        return excuteLog1();
    }

    @Async
    public Integer excuteLogForAsync2(){
        return excuteLog2();
    }

    // 阻塞等待结果
    @Async
    public Future<Integer> executeAsyncWithFuture1(){
        return AsyncResult.forValue(excuteLog1());
    }

    @Async
    public Future<Integer> executeAsyncWithFuture2(){
        return AsyncResult.forValue(excuteLog2());
    }
}
