package com.dm.pattern.memento;

/**
 * @author hu.yuhao
 * 状态存储类
 * */
public class Memento<T> {
    private T target;

    public Memento(T target) {
        this.target = target;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }
}
