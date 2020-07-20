package com.dm.pattern.observer;

public class AObserver extends Observer {

    public AObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    void update() {
        System.out.println("A observer update, subject's state has changed");
    }
}
