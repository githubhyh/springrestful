package com.dm.proxy.cglib;

public class TestCglib {
    public static void main(String[] args) {
        PersonProxy<Person> personProxy = new PersonProxy<Person>(new Person());
        Person person = personProxy.getProxy();
        person.sayHello();
        person.eat();
    }
}
