package com.dm.enums;

/**
 * @author hu.yuhao
 * 质量单位
 * */
public enum MassUnit {
    T("T"),
    KG("KG"),
    G("G"),
    MG("MG"),
    μG("μG");

    MassUnit(String massUnit) {
        this.massUnit = massUnit;
    }

    private String massUnit;

    public String getMassUnit() {
        return massUnit;
    }
}
