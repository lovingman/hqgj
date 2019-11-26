package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 表名：act_family_target
 * 注释：项目被帮扶对象列表<限情暖家庭>
 *
 * @author heshuang
 * @date 2019-07-26
 */
@ApiModel(description = "请求/返回参数模型")
public class ActFamilyTarget extends BaseModel {
    /**
     * 字段名：act_family_target.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_family_target.areaCode
     * 注释：所属辖区
     */
    @ApiModelProperty(value = "唯一标识")
    private String areaCode;

    /**
     * 字段名：act_family_target.projectId
     * 注释：项目主键
     */
    @ApiModelProperty(value = "项目主键")
    private String projectId;

    /**
     * 字段名：act_family_target.personId
     * 注释：被帮扶对象主键
     */
    @ApiModelProperty(value = "被帮扶对象主键")
    private String personId;

    /**
     * 字段名：act_family_target.personName
     * 注释：被帮扶对象姓名
     */
    @ApiModelProperty(value = "被帮扶对象姓名")
    private String personName;

    /**
     * 字段名：act_family_target.personGender
     * 注释：被帮扶对象性别
     */
    @ApiModelProperty(value = "被帮扶对象性别")
    private String personGender;

    /**
     * 字段名：act_family_target.idCardNo
     * 注释：被帮扶对象身份证号码
     */
    @ApiModelProperty(value = "被帮扶对象身份证号码")
    private String idCardNo;

    /**
     * 字段名：act_family_target.category
     * 注释：被帮扶对象类型
     */
    @ApiModelProperty(value = "被帮扶对象类型")
    private String category;

    /**
     * 字段名：act_family_target.telephone
     * 注释：被帮扶对象联系方式
     */
    @ApiModelProperty(value = "被帮扶对象联系方式")
    private String telephone;

    /**
     * 字段名：act_family_target.address
     * 注释：被帮扶对象的现居地址
     */
    @ApiModelProperty(value = "被帮扶对象的现居地址")
    private String address;

    /**
     * 字段名：act_family_target.volType1
     * 注释：需求志愿者类型1
     */
    @ApiModelProperty(value = "需求志愿者类型1")
    private String volType1;

    /**
     * 字段名：act_family_target.volType2
     * 注释：需求志愿者类型2
     */
    @ApiModelProperty(value = "需求志愿者类型2")
    private String volType2;

    /**
     * 字段名：act_family_target.volType3
     * 注释：需求志愿者类型3
     */
    @ApiModelProperty(value = "需求志愿者类型3")
    private String volType3;

    /**
     * 字段名：act_family_target.volType4
     * 注释：需求志愿者类型4
     */
    @ApiModelProperty(value = "需求志愿者类型4")
    private String volType4;

    /**
     * 字段名：act_family_target.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_family_target.status
     * 注释：状态 1-有效 0-失效
     */
    private String status;

    /**
     * 字段名：act_family_target.createUserId
     * 注释：创建人编号
     */
    private String createUserId;

    /**
     * 字段名：act_family_target.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：act_family_target.createDate
     * 注释：创建日期
     */
    private Date createDate;

    /**
     * 字段名：act_family_target.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：act_family_target.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：act_family_target.lastModifyDate
     * 注释：更新日期
     */
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId == null ? null : personId.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getPersonGender() {
        return personGender;
    }

    public void setPersonGender(String personGender) {
        this.personGender = personGender == null ? null : personGender.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getVolType1() {
        return volType1;
    }

    public void setVolType1(String volType1) {
        this.volType1 = volType1 == null ? null : volType1.trim();
    }

    public String getVolType2() {
        return volType2;
    }

    public void setVolType2(String volType2) {
        this.volType2 = volType2 == null ? null : volType2.trim();
    }

    public String getVolType3() {
        return volType3;
    }

    public void setVolType3(String volType3) {
        this.volType3 = volType3 == null ? null : volType3.trim();
    }

    public String getVolType4() {
        return volType4;
    }

    public void setVolType4(String volType4) {
        this.volType4 = volType4 == null ? null : volType4.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}