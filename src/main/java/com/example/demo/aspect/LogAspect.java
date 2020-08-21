package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LogAspect {

    // 拦截注解
    @Pointcut("@annotation(com.example.demo.annotation.LogAnno)")
    // 拦截表达式 第一个为方法返回类型，第二个为方法名（参数值）
    // @Pointcut("execution(* com.example.demo.controller..*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求参数
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (String parameterName : parameterNames) {
            System.out.println(parameterName);
        }
        // 进入方法核心代码
        return joinPoint.proceed();
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
    }

    public Map<String, Object> getRequestParams(ProceedingJoinPoint joinPoint){
        Map<String, Object> requestMap = new HashMap<>();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i< parameterNames.length ; i++) {
            Object value = args[i];
            requestMap.put(parameterNames[i], value);
        }

        return requestMap;
    }
}
