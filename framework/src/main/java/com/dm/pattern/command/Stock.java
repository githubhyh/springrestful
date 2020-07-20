package com.dm.pattern.command;

/**
 * @author hu.yuhao
 * 请求者，包含具体实现方法
 * */
public class Stock {
    private String name;

    private int quantity;

    public Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void buy() {
        System.out.println("购买股票名称：" + this.name + "  数量：" + this.quantity);
    }

    public void sell() {
        System.out.println("抛售股票名称：" + this.name + "  数量：" + this.quantity);
    }
}
