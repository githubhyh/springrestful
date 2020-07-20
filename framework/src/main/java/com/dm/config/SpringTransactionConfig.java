package com.dm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * @author hu.yuhao
 * 开启事务
 * 可以手动配置事务管理器
 * */
@Configuration
@EnableTransactionManagement
public class SpringTransactionConfig {

    /*@ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        System.out.println(druidDataSource.getConnection());
        return druidDataSource;
    }*/
}
