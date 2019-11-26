package com.huacainfo.ace.zcpa.synchronization.model;

import java.util.Date;

public class Unit {
    private String id;

    /**
     * 注释：单位名称
     */
    private String name;

    /**
     * 注释：单位地址
     */
    private String address;

    /**
     * 注释：联系方式
     */
    private String telephone;

    /**
     * 部门编号， 对应行政区划
     */
    private String  departmentNo;

    /**
     * 部门code
     */
    private String  orgInternalCode;
    /**
     * 部门id
     */
    private String  orgId;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getOrgInternalCode() {
        return orgInternalCode;
    }

    public void setOrgInternalCode(String orgInternalCode) {
        this.orgInternalCode = orgInternalCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
