package com.dm.pattern.decorator;

public class Ice extends Decorator {
    private Tea tea;

    public Ice(Tea tea) {
        this.tea = tea;
    }

    @Override
    String describe() {
        return tea.describe() + "+ 冰";
    }

    @Override
    double sell() {
        return tea.sell() + 1;
    }
}
