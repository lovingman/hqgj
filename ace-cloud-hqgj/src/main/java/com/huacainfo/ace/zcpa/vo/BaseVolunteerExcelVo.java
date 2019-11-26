package com.huacainfo.ace.zcpa.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

public class BaseVolunteerExcelVo extends BaseRowModel implements java.io.Serializable{

    @ExcelProperty(value ={"姓名"},index=0)
    private String name;
    /**
     * 字段名：sex
     * 注释：性别
     */
    @ExcelProperty(value ={"性别"},index=1)
    private String sexName;

    /**
     * idCard
     * 身份证
     */
    @ExcelProperty(value ={"身份证"},index=1)
    private String idCard;
    /**
     * 字段名：mobile
     * 注释：手机号
     */
    @ExcelProperty(value ={"手机号"},index=2)
    private String mobile;
    /**
     * 区域值
     */
    @ExcelProperty(value ={"区域"},index=3)
    private  String areaName;

    /**
     * 所属类型
     */
    @ExcelProperty(value ={"所属类型"},index=4)
    private String userTypeName;

    /**
     * 创建日期
     */
    @ExcelProperty(value ={"创建日期"},index=4)
    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
