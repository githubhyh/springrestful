package com.dm.config;

import com.dm.convert.properties.StringWeightConvert;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hu.yuhao
 * @date 2020-7-2
 * */
@Configuration
public class SpringConvertConfig {

    @Bean
    @ConfigurationPropertiesBinding
    public StringWeightConvert stringWeightConvert() {
        return new StringWeightConvert();
    }
}
