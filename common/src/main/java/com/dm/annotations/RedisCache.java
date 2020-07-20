package com.dm.annotations;

import com.dm.enums.BusinessType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author hu.yuhao
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {
    @AliasFor("key")
    String name() default "redis_cache_default_name";

    @AliasFor("name")
    String key() default "redis_cache_default_name";

    BusinessType type() default BusinessType.SELECT;

    long expire() default 30L;

    TimeUnit timeUnit() default TimeUnit.MINUTES;

    boolean isFromCache() default true;
}
