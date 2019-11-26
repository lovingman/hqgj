package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：base_unit
 * 注释：平安单位
 *
 * @author heshuang
 * @date 2019-09-27
 */
public class BaseUnit extends BaseModel {
    /**
     * 字段名：base_unit.id
     * 注释：主鍵
     */
    private String id;

    /**
     * 字段名：base_unit.name
     * 注释：单位名称
     */
    private String name;

    /**
     * 字段名：base_unit.address
     * 注释：单位地址
     */
    private String address;

    /**
     * 字段名：base_unit.telephone
     * 注释：联系方式
     */
    private String telephone;

    /**
     * 字段名：base_unit.areaCode
     * 注释：区域编码
     */
    private String areaCode;

    /**
     * 字段名：base_unit.createUserId
     * 注释：创建人id
     */
    private String createUserId;

    /**
     * 字段名：base_unit.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：base_unit.createDate
     * 注释：创建日期
     */
    private Date createDate;

    private String lastModifyUserId;

    /**
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 注释：更新日期
     */
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}