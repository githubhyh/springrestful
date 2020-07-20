package com.dm.annotations;

import java.lang.annotation.*;

/**
 * @author hu.yuhao
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    long sec() default 5L;

    int limit() default 10;
}
