package com.dm.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hu.yuhao
 * 命令执行者
 * */
public class Broker {
    private List<Order> orders = new ArrayList<>();

    public void addOrder (Order order) {
        orders.add(order);
    }

    public void exec () {
        orders.stream().forEach((order)->{order.exec();});
    }
}
