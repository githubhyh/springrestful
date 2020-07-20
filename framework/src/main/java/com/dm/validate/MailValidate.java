package com.dm.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hu.yuhao
 * 自定义验证注解
 * */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = JavaMailValidate.class)
public @interface MailValidate {
    String message() default "params error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
