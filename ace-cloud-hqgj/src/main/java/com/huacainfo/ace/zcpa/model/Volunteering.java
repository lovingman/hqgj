package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;


public class Volunteering extends BaseModel {
private static final long serialVersionUID = 1L;
    private String vType;
    private String total;

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
