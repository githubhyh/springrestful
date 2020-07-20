package com.dm.config;

import com.dm.interceptor.RateLimitInterceptor;
import com.dm.interceptor.RedisCacheInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hu.yuhao
 * */
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(SpringMVCConfig.class);

    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("add rate limit interceptor");
        //registry.addInterceptor(new RateLimitInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(new RedisCacheInterceptor()).addPathPatterns("/**");
    }
}
