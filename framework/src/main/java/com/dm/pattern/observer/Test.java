package com.dm.pattern.observer;

/**
 * 观察者模式测试入口
 * */
public class Test {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new AObserver(subject);
        new BObserver(subject);

        subject.setState(10);
    }
}
