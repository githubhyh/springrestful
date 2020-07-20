package com.dm.advice;

import com.dm.utils.redis.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

//@ControllerAdvice
public class ResponseBodyAnalysis implements ResponseBodyAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ResponseBodyAnalysis.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        logger.info("set response body to http servlet request");
        ServletServerHttpRequest req = (ServletServerHttpRequest)serverHttpRequest;
        HttpServletRequest httpServletRequest = req.getServletRequest();
        httpServletRequest.setAttribute("response", o);
        return RedisUtils.getObj("test-redis-cache");
    }
}
