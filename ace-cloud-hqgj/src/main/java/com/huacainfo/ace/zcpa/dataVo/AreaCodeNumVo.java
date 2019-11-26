package com.huacainfo.ace.zcpa.dataVo;




public class AreaCodeNumVo {
private static final long serialVersionUID = 1L;

    //地区编码
    private String  areaCode;
    //人数
    private int num;
   //地区值
    private String areaName;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
