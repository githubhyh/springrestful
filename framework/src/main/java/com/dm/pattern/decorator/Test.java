package com.dm.pattern.decorator;

public class Test {
    public static void main(String[] args) {
        System.out.println("来一杯加糖加冰加牛奶的红茶");
        Tea tea = new Sugar(new Ice(new Milk(new RedTea())));
        System.out.println(tea.describe()+"价格为："+tea.sell()+"元");
    }
}
