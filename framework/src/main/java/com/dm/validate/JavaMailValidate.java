package com.dm.validate;

import com.alibaba.fastjson.JSONObject;
import com.dm.config.properties.JavaMailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hu.yuhao
 * 验证逻辑实现，无需注入ApplicationContext
 * */
public class JavaMailValidate implements ConstraintValidator<MailValidate, JavaMailProperties> {
    private static final Logger logger = LoggerFactory.getLogger(JavaMailValidate.class);

    @Override
    public void initialize(MailValidate constraintAnnotation) {
        logger.info("initialize java mail validator");
    }

    @Override
    public boolean isValid(JavaMailProperties javaMailProperties, ConstraintValidatorContext constraintValidatorContext) {
        logger.info("验证错误");
        logger.info(JSONObject.toJSONString(javaMailProperties));
        return false;
    }
}
