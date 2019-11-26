package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 表名：act_family_volunteer
 * 注释：项目志愿者列表<限情暖家庭>
 *
 * @author heshuang
 * @date 2019-07-26
 */
@ApiModel(description = "请求/返回参数模型")
public class ActFamilyVolunteer extends BaseModel {
    /**
     * 字段名：act_family_volunteer.id
     * 注释：主键
     */
    @ApiModelProperty(value = "唯一标识")
    private String id;

    /**
     * 字段名：act_family_volunteer.areaCode
     * 注释：所属辖区
     */
    @ApiModelProperty(value = "所属辖区")
    private String areaCode;

    /**
     * 字段名：act_family_volunteer.projectId
     * 注释：项目主键
     */
    @ApiModelProperty(value = "项目主键")
    private String projectId;

    /**
     * 字段名：act_family_volunteer.personId
     * 注释：帮扶对象主键
     */
    @ApiModelProperty(value = "帮扶对象主键")
    private String targetId;

    /**
     * 字段名：act_family_volunteer.volunteerId
     * 注释：志愿者主键
     */
    @ApiModelProperty(value = "志愿者主键")
    private String volunteerId;

    /**
     * 字段名：act_family_volunteer.volName
     * 注释：志愿者姓名
     */
    @ApiModelProperty(value = "志愿者姓名")
    private String volName;

    /**
     * 字段名：act_family_volunteer.idCardNo
     * 注释：志原者身份证号
     */
    @ApiModelProperty(value = "志原者身份证号")
    private String idCardNo;

    /**
     * 字段名：act_family_volunteer.telephone
     * 注释：志愿者联系方式
     */
    @ApiModelProperty(value = "志愿者联系方式")
    private String telephone;

    /**
     * 字段名：act_family_volunteer.volType
     * 注释：志愿者类型
     */
    @ApiModelProperty(value = "志愿者类型")
    private String volType;

    /**
     * 字段名：act_family_volunteer.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_family_volunteer.status
     * 注释：状态 1-有效 0-失效
     */
    private String status;

    /**
     * 字段名：act_family_volunteer.createUserId
     * 注释：创建人编号
     */
    @ApiModelProperty(value = "唯一标识")
    private String createUserId;

    /**
     * 字段名：act_family_volunteer.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：act_family_volunteer.createDate
     * 注释：创建日期
     */
    private Date createDate;

    /**
     * 帮扶对象所需志愿者类型
     */
    private String aidTargetType;


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

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId == null ? null : volunteerId.trim();
    }

    public String getVolName() {
        return volName;
    }

    public void setVolName(String volName) {
        this.volName = volName == null ? null : volName.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getVolType() {
        return volType;
    }

    public void setVolType(String volType) {
        this.volType = volType == null ? null : volType.trim();
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

    public String getAidTargetType() {
        return aidTargetType;
    }

    public void setAidTargetType(String aidTargetType) {
        this.aidTargetType = aidTargetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}