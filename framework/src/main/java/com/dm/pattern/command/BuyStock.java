package com.dm.pattern.command;

/**
 * @author hu.yuhao
 * 具体命令
 * 维护请求者，拆分出单个命令（解耦）
 * */
public class BuyStock implements Order {
    private Stock stock;

    public BuyStock (Stock stock) {
        this.stock = stock;
    }

    /**
     * 执行具体命令
     */
    @Override
    public void exec() {
        stock.buy();
    }
}
