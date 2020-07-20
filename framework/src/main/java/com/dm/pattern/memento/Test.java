package com.dm.pattern.memento;

/**
 * @author hu.yuhao
 * 记录者
 * 被记录者
 * 记录维护者
 * */
public class Test {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setMsg("msg #0");
        originator.setMsg("msg #1");
        careTaker.add(originator.saveToMemento());
        originator.setMsg("msg #2");
        careTaker.add(originator.saveToMemento());
        originator.setMsg("msg #3");

        System.out.println("current msg :"+originator.getMsg());
        originator.getFromMemento(careTaker.get(0));
        System.out.println("first msg :"+originator.getMsg());
    }
}
