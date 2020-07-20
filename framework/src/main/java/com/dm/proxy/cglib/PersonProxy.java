package com.dm.proxy.cglib;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class PersonProxy<T> implements MethodInterceptor {

    private T target;

    public PersonProxy(T target) {
        this.target = target;
    }

    public T getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallbackFilter(new PersonFilter("eat"));
        //NoOp.INSTANCE，直接跳过方法，调用原方法，不然报错
        enhancer.setCallbacks(new Callback[] {this, NoOp.INSTANCE});
        //enhancer.set
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return invokeSuper;
    }
}
