package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.ServeFinanceOrder;


public class ServeFinanceOrderVo extends ServeFinanceOrder {
    private static final long serialVersionUID = 1L;
    //机构logo
    private String fmUrl;
    //项目名称
    private String itemName;
    //项目价格
    private String price;
    //专家问诊名字
    private String mName;
    //专家照片
    private String imagePhoto;
    @Override
    public String getFmUrl() {
        return fmUrl;
    }

    @Override
    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getImagePhoto() {
        return imagePhoto;
    }

    public void setImagePhoto(String imagePhoto) {
        this.imagePhoto = imagePhoto;
    }
}
