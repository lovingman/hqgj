package com.huacainfo.ace.zcpa.synchronization.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class Person  extends BaseModel {
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
   private int gender;
    /**
     * 身份证
     */

    private String idCardNo;

    /**
     * 联系方式
     */
    private String mobileNumber;
    /**
     * 居住地址
     */
    private String currentAddress;
    /**
     * 类型
     */
    private String type;

    /**
     *部门编号
     */
    private String departmentNo;

    /**
     * 部门code
     */
    private String orgInternalCode;

    /**
     * 部门id
     */
    private String orgId;


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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }
}
