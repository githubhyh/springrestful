package com.dm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class, DataSourceAutoConfiguration.class})
@MapperScan({"com.dm.**.mapper"})
public class SpringApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringApplication.class);

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        logger.info("启动成功！");
    }
}
