package com.dm.pattern.decorator;

public class RedTea extends Tea {
    @Override
    String describe() {
        return "red tea";
    }

    @Override
    double sell() {
        return 10;
    }
}
