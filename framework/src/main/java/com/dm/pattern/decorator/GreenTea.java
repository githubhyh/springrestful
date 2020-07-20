package com.dm.pattern.decorator;

public class GreenTea extends Tea {
    @Override
    String describe() {
        return "green tea";
    }

    @Override
    double sell() {
        return 9;
    }
}
