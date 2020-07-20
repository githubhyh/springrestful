package com.dm.pattern.memento;

/**
 * @author hu.yuhao
 * 被记录类
 * */
public class Originator {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**创建备忘录对象*/
    public Memento saveToMemento() {
        return new Memento(msg);
    }

    public void getFromMemento(Memento memento) {
        this.msg = (String)memento.getTarget();
    }
}
