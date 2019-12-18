package com.huacainfo.ace.hqgj.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class BaseOrganizationExcelVo  extends BaseRowModel implements java.io.Serializable {
    @ExcelProperty(value ={"机构名称"},index=0)
    private String orgName;

    /**
     * 字段名：creditCode
     * 注释：统一社会信用代码
     */
    @ExcelProperty(value ={"统一社会信用代码"},index=1)
    private String creditCode;

    /**
     * 字段名：contactPersonName
     * 注释：联系人姓名
     */
    @ExcelProperty(value ={"联系人姓名"},index=1)
    private String contactPersonName;

    /**
     * 字段名：contactPersonTel
     * 注释：联系人电话
     */
    @ExcelProperty(value ={"联系人电话"},index=1)
    private String contactPersonTel;

    /**
     * 字段名：orgAddress
     * 注释：机构详细地址
     */
    @ExcelProperty(value ={"机构详细地址"},index=1)
    private String orgAddress;

    /**
     * 字段名：content
     * 注释：内容简介
     */
    @ExcelProperty(value ={"内容简介"},index=1)
    private String content;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
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

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
