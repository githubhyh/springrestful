package com.dm.pattern.decorator;

public class Milk extends Decorator {
    private Tea tea;

    public Milk(Tea tea) {
        this.tea = tea;
    }

    @Override
    String describe() {
        return tea.describe() + "+ 牛奶";
    }

    @Override
    double sell() {
        return tea.sell() + 2;
    }
}
