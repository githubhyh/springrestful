package com.dm.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hu.yuhao
 * 主体，目标对象，发生改变时，触发所有观察者，执行特定方法
 * */
public class Subject {
    private List<Observer> observers = new ArrayList<>();

    private int state;

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        observers.stream().forEach((observer)->{observer.update();});
    }
}
