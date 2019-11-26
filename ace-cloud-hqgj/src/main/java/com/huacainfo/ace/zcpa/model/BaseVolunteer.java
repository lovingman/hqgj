package com.huacainfo.ace.zcpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 表名：base_volunteer
 * 注释：群众/志愿者
 *
 * @author heshuang
 * @date 2019-07-26
 */
@ApiModel(description = "请求/返回参数模型")
public class BaseVolunteer extends BaseModel {
    /**
     * 字段名：base_volunteer.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    @ApiModelProperty(value = "微信唯一标识")
    private String unionid;

    /**
     * 字段名：base_volunteer.name
     * 注释：姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 字段名：base_volunteer.sex
     * 注释：性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 字段名：base_volunteer.mobile
     * 注释：手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 字段名：base_volunteer.idCard
     * 注释：身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private String idCard;

    /**
     * 字段名：base_volunteer.userType
     * 注释：用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private String userType;

    /**
     * 字段名：base_volunteer.orgId
     * 注释：所属组织
     */
    @ApiModelProperty(value = "所属组织")
    private String orgId;

    /**
     * 字段名：base_volunteer.areaCode
     * 注释：归属区划
     */
    @ApiModelProperty(value = "归属区划")
    private String areaCode;

    /**
     * 字段名：base_volunteer.homeAreaCode
     * 注释：户籍区划
     */
    @ApiModelProperty(value = "户籍区划")
    private String homeAreaCode;

    /**
     * 字段名：base_volunteer.homeAddr
     * 注释：户籍详细地址
     */
    @ApiModelProperty(value = "户籍详细地址")
    private String homeAddr;

    /**
     * 字段名：base_volunteer.nowAreaCode
     * 注释：现居地区划
     */
    @ApiModelProperty(value = "现居地区划")
    private String nowAreaCode;

    /**
     * 字段名：base_volunteer.nowAddr
     * 注释：现居详细地址
     */
    @ApiModelProperty(value = "现居详细地址")
    private String nowAddr;


    /**
     * 出生日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd" )
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌")
    private String politicalAppearance;
    /**
     * 字段名：base_volunteer.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：base_volunteer.status
     * 注释：状态 
     */
    private String status;

    /**
     * 字段名：base_volunteer.createUserId
     * 注释：创建人编号
     */
    private String createUserId;

    /**
     * 字段名：base_volunteer.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：base_volunteer.createDate
     * 注释：创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    /**
     * 字段名：base_volunteer.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：base_volunteer.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：base_volunteer.lastModifyDate
     * 注释：更新日期
     */
    private Date lastModifyDate;
    /**
     * 所属单位
     */
    private String deptId;
    /**
     * 志愿者类型
     */
    private String volType;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getVolType() {
        return volType;
    }

    public void setVolType(String volType) {
        this.volType = volType;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getHomeAreaCode() {
        return homeAreaCode;
    }

    public void setHomeAreaCode(String homeAreaCode) {
        this.homeAreaCode = homeAreaCode == null ? null : homeAreaCode.trim();
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr == null ? null : homeAddr.trim();
    }

    public String getNowAreaCode() {
        return nowAreaCode;
    }

    public void setNowAreaCode(String nowAreaCode) {
        this.nowAreaCode = nowAreaCode == null ? null : nowAreaCode.trim();
    }

    public String getNowAddr() {
        return nowAddr;
    }

    public void setNowAddr(String nowAddr) {
        this.nowAddr = nowAddr == null ? null : nowAddr.trim();
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPoliticalAppearance() {
        return politicalAppearance;
    }

    public void setPoliticalAppearance(String politicalAppearance) {
        this.politicalAppearance = politicalAppearance;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
