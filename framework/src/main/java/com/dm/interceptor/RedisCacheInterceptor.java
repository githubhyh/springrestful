package com.dm.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.dm.annotations.RedisCache;
import com.dm.enums.BusinessType;
import com.dm.utils.redis.RedisUtils;
import com.dm.utils.web.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author hu.yuhao
 * */
public class RedisCacheInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(RedisCache.class)) {
            RedisCache redisCache = method.getAnnotation(RedisCache.class);

            if (redisCache == null) return false;

            //logger.info(JSONObject.toJSONString(redisCache));

            if (redisCache.isFromCache()) {
                if (RedisUtils.hasKey(redisCache.key())) {
                    logger.info("拦截请求。。。。。。");
                    Object obj = RedisUtils.getObj(redisCache.key());
                    ServletUtils.renderString(response, JSONObject.toJSONString(obj));
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(RedisCache.class)) {
            RedisCache redisCache = method.getAnnotation(RedisCache.class);

            if (redisCache.type().equals(BusinessType.UPDATE) || redisCache.type().equals(BusinessType.INSERT) || redisCache.type().equals(BusinessType.DELETE)) {
                RedisUtils.remove(redisCache.key());
            }else if (redisCache.type().equals(BusinessType.SELECT)) {
                if (!RedisUtils.hasKey(redisCache.key())) {
                    Object requestAttribute = request.getAttribute("response");
                    logger.info("update or set redis cache, value is {}, expire is {}", JSONObject.toJSONString(requestAttribute), redisCache.expire());
                    RedisUtils.set(redisCache.key(), requestAttribute, redisCache.expire(), redisCache.timeUnit());
                    //logger.info(JSONObject.toJSONString(RedisUtils.keys("*")));
                }
            }
        }
    }
}
