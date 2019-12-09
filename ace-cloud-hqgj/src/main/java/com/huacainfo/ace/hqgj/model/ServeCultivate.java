package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：serve_cultivate
 * 注释：培训提升基础表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class ServeCultivate extends BaseModel {
    /**
     * 字段名：serve_cultivate.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：serve_cultivate.title
     * 注释：标题
     */
    private String title;

    /**
     * 字段名：serve_cultivate.maxPersonNumber
     * 注释：最大报名人数
     */
    private Integer maxPersonNumber;

    /**
     * 字段名：serve_cultivate.enrollPersopnNumber
     * 注释：报名人数
     */
    private Integer enrollPersopnNumber;

    /**
     * 字段名：serve_cultivate.cultivatePersonNumber
     * 注释：培训人数
     */
    private Integer cultivatePersonNumber;

    /**
     * 字段名：serve_cultivate.startDate
     * 注释：开始时间
     */
    private Date startDate;

    /**
     * 字段名：serve_cultivate.endDate
     * 注释：结束时间
     */
    private Date endDate;

    /**
     * 字段名：serve_cultivate.startLng
     * 注释：地点经度
     */
    private BigDecimal startLng;

    /**
     * 字段名：serve_cultivate.startLat
     * 注释：地点纬度
     */
    private BigDecimal startLat;

    /**
     * 字段名：serve_cultivate.detailedAddress
     * 注释：详细地址
     */
    private String detailedAddress;

    /**
     * 字段名：serve_cultivate.fmUrl
     * 注释：封面URL地址
     */
    private String fmUrl;

    /**
     * 字段名：serve_cultivate.content
     * 注释：内容
     */
    private String content;

    /**
     * 字段名：serve_cultivate.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：serve_cultivate.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：serve_cultivate.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：serve_cultivate.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：serve_cultivate.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：serve_cultivate.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：serve_cultivate.status
     * 注释：状态  0-待审核  1-进行中 2-未通过 3-已结束
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getMaxPersonNumber() {
        return maxPersonNumber;
    }

    public void setMaxPersonNumber(Integer maxPersonNumber) {
        this.maxPersonNumber = maxPersonNumber;
    }

    public Integer getEnrollPersopnNumber() {
        return enrollPersopnNumber;
    }

    public void setEnrollPersopnNumber(Integer enrollPersopnNumber) {
        this.enrollPersopnNumber = enrollPersopnNumber;
    }

    public Integer getCultivatePersonNumber() {
        return cultivatePersonNumber;
    }

    public void setCultivatePersonNumber(Integer cultivatePersonNumber) {
        this.cultivatePersonNumber = cultivatePersonNumber;
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

    public String getFmUrl() {
        return fmUrl;
    }

    public void setFmUrl(String fmUrl) {
        this.fmUrl = fmUrl == null ? null : fmUrl.trim();
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