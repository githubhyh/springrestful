package com.dm.interceptor;

import com.dm.annotations.RateLimit;
import com.dm.utils.redis.RedisUtils;
import com.dm.utils.web.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class RateLimitInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RateLimitInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (!method.isAnnotationPresent(RateLimit.class)) {
            return true;
        }
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);
        if (rateLimit == null) return true;
        long sec = rateLimit.sec();
        int limit = rateLimit.limit();

        String key = request.getRequestURL().toString();
        Integer current = (Integer)RedisUtils.getObj(key);

        if (current == null) {
            logger.info("first request");
            RedisUtils.set(key, 1, sec, TimeUnit.SECONDS);
        }else if (current < limit) {
            RedisUtils.set(key, current+1, sec, TimeUnit.SECONDS);
        }else {
            ServletUtils.renderString(response, "请求过于频繁，请稍后重试");
            return false;
        }
        return true;
    }
}
