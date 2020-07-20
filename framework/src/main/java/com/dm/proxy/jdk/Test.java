package com.dm.proxy.jdk;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        ManProxy<Person> manProxy = new ManProxy<>(new Man());
        Person person = manProxy.getProxy();
        person.eat();
    }
}
