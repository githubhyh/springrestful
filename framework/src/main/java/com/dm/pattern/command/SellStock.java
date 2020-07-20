package com.dm.pattern.command;

public class SellStock implements Order {
    private Stock stock;

    public SellStock (Stock stock) {
        this.stock = stock;
    }

    /**
     * 执行具体命令
     */
    @Override
    public void exec() {
        stock.sell();
    }
}
