package com.huacainfo.ace.zcpa.plugins.wechat.pojo;


import java.io.Serializable;

public class DataItem implements Serializable {
    private Object value;
    private String color;

    public DataItem() {
    }

    public DataItem(Object value, String color) {
        this.value = value;
        this.color = color;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
