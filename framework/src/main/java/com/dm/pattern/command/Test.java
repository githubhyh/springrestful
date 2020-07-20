package com.dm.pattern.command;

public class Test {
    public static void main(String[] args) {
        //请求者
        Stock byd = new Stock("BYD", 20);

        //具体命令
        BuyStock buyStock = new BuyStock(byd);
        SellStock sellStock = new SellStock(new Stock("HUAWEI", 10));

        //执行具体命令
        Broker broker = new Broker();
        broker.addOrder(buyStock);
        broker.addOrder(sellStock);
        broker.exec();
    }
}
