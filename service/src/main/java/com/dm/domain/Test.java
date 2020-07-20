package com.dm.domain;

import java.io.Serializable;
/**
 * @author hu.yuhao
 * */
public class Test implements Serializable {
    private Integer id;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
