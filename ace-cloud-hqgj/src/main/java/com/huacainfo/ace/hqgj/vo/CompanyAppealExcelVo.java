package com.huacainfo.ace.hqgj.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CompanyAppealExcelVo extends BaseRowModel implements java.io.Serializable {
    @ExcelProperty(value ={"序号"},index=0)
    private Integer order;

    @ExcelProperty(value ={"企业名称"},index=1)
    private String companyName;

    @ExcelProperty(value ={"企业人数"},index=2)
    private Integer companyNumber;

    @ExcelProperty(value ={"联系人"},index=3)
    private String personName;

    @ExcelProperty(value ={"联系方式"},index=4)
    private String personTel;

    @ExcelProperty(value ={"物资名称"},index=5)
    private String categoryName;

    @ExcelProperty(value ={"物资数量"},index=6)
    private Integer number;

    @ExcelProperty(value ={"上报时间"},index=7)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(Integer companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonTel() {
        return personTel;
    }

    public void setPersonTel(String personTel) {
        this.personTel = personTel;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
