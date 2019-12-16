package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 表名：serve_cultivate_schedule
 * 注释：培训提升日程表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class ServeCultivateSchedule extends BaseModel {
    /**
     * 字段名：serve_cultivate_schedule.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_cultivate_schedule.serveCultivateId
     * 注释：培训提升基础表ID（关联serve_cultivate表id）
     */
    private String serveCultivateId;

    /**
     * 字段名：serve_cultivate_schedule.title
     * 注释：标题名称
     */
    private String title;

    /**
     * 字段名：serve_cultivate_schedule.lecturerId
     * 注释：讲师ID
     */
    private String lecturerId;

    /**
     * 字段名：serve_cultivate_schedule.lecturerName
     * 注释：讲师姓名
     */
    private String lecturerName;

    /**
     * 字段名：serve_cultivate_schedule.startLng
     * 注释：地点经度
     */
    private BigDecimal startLng;

    /**
     * 字段名：serve_cultivate_schedule.startLat
     * 注释：地点纬度
     */
    private BigDecimal startLat;

    /**
     * 字段名：serve_cultivate_schedule.detailedAddress
     * 注释：详细地址
     */
    private String detailedAddress;

    /**
     * 字段名：serve_cultivate_schedule.startDate
     * 注释：开始时间
     */
    private Date startDate;

    /**
     * 字段名：serve_cultivate_schedule.endDate
     * 注释：结束时间
     */
    private Date endDate;

    /**
     * 字段名：serve_cultivate_schedule.content
     * 注释：内容简介
     */
    private String content;

    /**
     * 字段名：serve_cultivate_schedule.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_cultivate_schedule.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_cultivate_schedule.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：serve_cultivate_schedule.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_cultivate_schedule.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_cultivate_schedule.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_cultivate_schedule.status
     * 注释：状态  1-生效 0-失效
     */
    private String status;

    private List<BasicAnnex> basicAnnexes ;

    public List<BasicAnnex> getBasicAnnexes() {
        return basicAnnexes;
    }

    public void setBasicAnnexes(List<BasicAnnex> basicAnnexes) {
        this.basicAnnexes = basicAnnexes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getServeCultivateId() {
        return serveCultivateId;
    }

    public void setServeCultivateId(String serveCultivateId) {
        this.serveCultivateId = serveCultivateId == null ? null : serveCultivateId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId == null ? null : lecturerId.trim();
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName == null ? null : lecturerName.trim();
    }

    public BigDecimal getStartLng() {
        return startLng;
    }

    public void setStartLng(BigDecimal startLng) {
        this.startLng = startLng;
    }

    public BigDecimal getStartLat() {
        return startLat;
    }

    public void setStartLat(BigDecimal startLat) {
        this.startLat = startLat;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress == null ? null : detailedAddress.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName == null ? null : modifyUserName.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}