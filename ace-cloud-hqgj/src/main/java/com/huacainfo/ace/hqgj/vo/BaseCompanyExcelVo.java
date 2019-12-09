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
}
