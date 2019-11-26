package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 表名：act_school
 * 注释：项目被服务对象-校园资料
 *
 * @author heshuang
 * @date 2019-07-26
 */
@ApiModel(description = "请求/返回参数模型")
public class ActSchool extends BaseModel {
    /**
     * 字段名：act_school.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_school.schoolName
     * 注释：校园名称
     */
    @ApiModelProperty(value = "校园名称")
    private String schoolName;

    /**
     * 字段名：act_school.unicode
     * 注释：社会统一信用代码
     */
    @ApiModelProperty(value = "社会统一信用代码")
    private String unicode;

    /**
     * 字段名：act_school.areaCode
     * 注释：归属区划
     */
    @ApiModelProperty(value = "归属区划")
    private String areaCode;

    /**
     * 字段名：act_school.detailAddr
     * 注释：详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String detailAddr;

    /**
     * 字段名：act_school.dutyName
     * 注释：联系人
     */
    @ApiModelProperty(value = "联系人")
    private String dutyName;

    /**
     * 字段名：act_school.phone
     * 注释：联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String phone;

    /**
     * 字段名：act_school.nowAddr
     * 注释：现居住地址
     */
    @ApiModelProperty(value = "现居住地址")
    private String nowAddr;

    /**
     * 字段名：act_school.remark
     * 注释：备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 字段名：act_school.status
     * 注释：状态 
     */
    private String status;

    /**
     * 字段名：act_school.createUserId
     * 注释：创建人编号
     */
    private String createUserId;

    /**
     * 字段名：act_school.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：act_school.createDate
     * 注释：创建日期
     */
    private Date createDate;

    /**
     * 字段名：act_school.lastModifyUserId
     * 注释：更新人编号
     */
    private String lastModifyUserId;

    /**
     * 字段名：act_school.lastModifyUserName
     * 注释：更新人名称
     */
    private String lastModifyUserName;

    /**
     * 字段名：act_school.lastModifyDate
     * 注释：更新日期
     */
    private Date lastModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode == null ? null : unicode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr == null ? null : detailAddr.trim();
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName == null ? null : dutyName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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