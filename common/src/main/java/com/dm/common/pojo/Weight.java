package com.dm.common.pojo;

import com.dm.enums.MassUnit;

public class Weight {
    private double weight;

    private MassUnit massUnit;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public MassUnit getMassUnit() {
        return massUnit;
    }

    public void setMassUnit(MassUnit massUnit) {
        this.massUnit = massUnit;
    }
}
