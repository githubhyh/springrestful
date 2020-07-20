package com.dm.proxy.cglib;

import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @author hu.yuhao
 * 过滤规则，可以使用方法签名过滤
 * */
public class PersonFilter implements CallbackFilter {
    //private Signature
    private String name;

    public PersonFilter(String name) {
        this.name = name == null ? "":name;
    }
    @Override
    public int accept(Method method) {
        if (this.name.equals(method.getName())) {
            System.out.println("拦截了该方法--"+method.getName());
            return 1;
        }
        return 0;
    }
}
