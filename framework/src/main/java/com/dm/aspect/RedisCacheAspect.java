package com.dm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class RedisCacheAspect {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);

    //配置织入点
    //@Pointcut("@annotation(com.dm.annotations.RedisCache)")
    public void redisCachePointCut() {}

    //@Around("redisCachePointCut()")
    public void around(ProceedingJoinPoint joinPoint) {
        logger.info("do not process aspect");
    }
}
