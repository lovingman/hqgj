package com.huacainfo.ace.zcpa.synchronization.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class School extends BaseModel {

     private  String id;
    /**
     *学校名称
     */
    private  String chineseName;
    /**
     *部门code
     */
    private  String orgInternalCode;
    /**
     *学校地址
     */
    private  String address;
    /**
     *联系方式
     */
    private  String mobileNumber;
    /**
     *部门编号 --对应库的areaCode
     */
    private  String departmentNo;
    /**
     *部门id
     */
    private  String orgId;

    private Date updateDate;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getOrgInternalCode() {
        return orgInternalCode;
    }

    public void setOrgInternalCode(String orgInternalCode) {
        this.orgInternalCode = orgInternalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
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
