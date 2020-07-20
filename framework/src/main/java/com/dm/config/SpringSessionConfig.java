package com.dm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class SpringSessionConfig {
    private static final Logger logger = LoggerFactory.getLogger(SpringSessionConfig.class);

    @ConfigurationProperties(prefix = "spring.redis")
    @Bean("redisSessionCache")
    public LettuceConnectionFactory connectionFactory() {
        logger.info("init redis session cache pool");
        return new LettuceConnectionFactory();
    }
}
