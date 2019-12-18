package com.huacainfo.ace.hqgj.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class BaseCompanyExcelVo extends BaseRowModel implements java.io.Serializable{

    @ExcelProperty(value ={"企业名称"},index=0)
    private String companyName;


    /**
     * 字段名：creditCode
     * 注释：统一社会信用代码
     */
    @ExcelProperty(value ={"统一社会信用代码"},index=1)
    private String creditCode;


    /**
     * 字段名：legalPerson
     * 注释：法定代表人
     */
    @ExcelProperty(value ={"法定代表人"},index=2)
    private String legalPerson;

    /**
     * 字段名：contactPersonName
     * 注释：联系人姓名
     */
    @ExcelProperty(value ={"联系人姓名"},index=2)
    private String contactPersonName;

    /**
     * 字段名：contactPersonTel
     * 注释：联系人电话
     */
    @ExcelProperty(value ={"联系人电话"},index=2)
    private String contactPersonTel;

    /**
     * 字段名：companyAddress
     * 注释：企业详细地址
     */
    @ExcelProperty(value ={"企业详细地址"},index=2)
    private String companyAddress;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonTel() {
        return contactPersonTel;
    }

    public void setContactPersonTel(String contactPersonTel) {
        this.contactPersonTel = contactPersonTel;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
