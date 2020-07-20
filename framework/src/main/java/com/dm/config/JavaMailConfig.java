package com.dm.config;

import com.dm.config.properties.JavaMailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hu.yuhao
 * 两种方式注入bean
 * */
@Configuration
@EnableConfigurationProperties(JavaMailProperties.class)
public class JavaMailConfig {
    //@Bean
    public JavaMailProperties javaMailProperties() {
        return new JavaMailProperties();
    }
}
