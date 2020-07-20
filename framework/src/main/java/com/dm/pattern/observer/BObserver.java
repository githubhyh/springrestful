package com.dm.pattern.observer;

public class BObserver extends Observer {

    public BObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    void update() {
        System.out.println("B observer update, subject's state has changed");
    }
}
