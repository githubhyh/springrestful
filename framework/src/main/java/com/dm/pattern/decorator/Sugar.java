package com.dm.pattern.decorator;

public class Sugar extends Decorator {
    private Tea tea;

    public Sugar (Tea tea) {
        this.tea = tea;
    }

    @Override
    String describe() {
        return tea.describe() + "+ 糖";
    }

    @Override
    double sell() {
        return tea.sell() + 1;
    }
}
