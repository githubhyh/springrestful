package com.dm.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ManProxy<T> implements InvocationHandler {
    private Object object;

    public ManProxy(Object o) {
        this.object = o;
    }

    public T getProxy() {
        return (T) Proxy.newProxyInstance(this.object.getClass().getClassLoader(), this.object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before do something");
        Object invoke = method.invoke(object, args);
        System.out.println("after do something");
        return invoke;
    }
}
