package com.huacainfo.ace.zcpa.synchronization.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class UserInfo extends BaseModel {
  private String id;
    /**
     * 部门编号
     */
  private String departmentNo;
    /**
     * 手机号
     */
  private String  mobile;
    /**
     * 姓名
     */
  private String  name;
    /**
     * 部门code
     */
  private String  orgInternalCode;
    /**
     * 部门id
     */
  private String  organizationId;
    /**
     * 密码
     */
  private String  password;
    /**
     * 帐号
     */
  private String  userName;

  private Date updateDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgInternalCode() {
        return orgInternalCode;
    }

    public void setOrgInternalCode(String orgInternalCode) {
        this.orgInternalCode = orgInternalCode;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
