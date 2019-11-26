package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 表名：act_person
 * 注释：项目被服务对象-特殊人群资料
 *
 * @author heshuang
 * @date 2019-07-26
 */
@ApiModel(description = "请求/返回参数模型")
public class ActPerson extends BaseModel {
    /**
     * 字段名：act_person.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_person.personName
     * 注释：姓名
     */
    @ApiModelProperty(value = "姓名")
    private String personName;

    /**
     * 字段名：act_person.sex
     * 注释：性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 字段名：act_person.idCard
     * 注释：身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    /**
     * 字段名：act_person.cateogry
     * 注释：类型
     */
    @ApiModelProperty(value = "类型")
    private String cateogry;

    /**
     * 字段名：act_person.phone
     * 注释：联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String phone;

    /**
     * 字段名：act_person.areaCode
     * 注释：归属区划
     */
    @ApiModelProperty(value = "归属区划")
    private String areaCode;

    /**
     * 字段名：act_person.nowAddr
     * 注释：现居住地址
     */
    @ApiModelProperty(value = "现居住地址")
    private String nowAddr;

    /**
     * 字段名：act_person.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_person.status
     * 注释：状态 
     */
    private String status;

    /**
     * 字段名：act_person.createUserId
     * 注释：创建人编号
     */
    private String createUserId;

    /**
     * 字段名：act_person.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：act_person.createDate
     * 注释：创建日期
     */
    private Date createDate;

    /**
     * 字段名：act_person.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：act_person.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：act_person.lastModifyDate
     * 注释：更新日期
     */
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getCateogry() {
        return cateogry;
    }

    public void setCateogry(String cateogry) {
        this.cateogry = cateogry == null ? null : cateogry.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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
}