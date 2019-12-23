package com.huacainfo.ace.wxms.wechat.entity.pojo.base;

import com.huacainfo.ace.common.model.BaseModel;

public class Menu extends BaseModel {

    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
