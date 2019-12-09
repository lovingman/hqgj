package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：basic_annex
 * 注释：附件表
 *
 * @author heshuang
 * @date 2019-12-09
 */
public class BasicAnnex extends BaseModel {
    /**
     * 字段名：basic_annex.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：basic_annex.relationId
     * 注释：关联主键（关联相关模块的主键ID）
     */
    private String relationId;

    /**
     * 字段名：basic_annex.fileType
     * 注释：附件类型
     */
    private String fileType;

    /**
     * 字段名：basic_annex.fileName
     * 注释：附件名称
     */
    private String fileName;

    /**
     * 字段名：basic_annex.fileSize
     * 注释：附件大小
     */
    private BigDecimal fileSize;

    /**
     * 字段名：basic_annex.fileURL
     * 注释：附件URL地址
     */
    private String fileURL;

    /**
     * 字段名：basic_annex.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：basic_annex.type
     * 注释：类型 (1-培训提升日程安排附件，关联serve_cultivate_schedule表ID；2-法律服务附件，关联law_serve表ID; 3-创业服务资料清单人员附件，关联serve_business_member表ID; 4-创业服务其它附件，关联serve_business_other表ID)
     */
    private String type;

    /**
     * 字段名：basic_annex.createDate
     * 注释：创建日期
     */
    private Date createDate;

    /**
     * 字段名：basic_annex.status
     * 注释：状态 1-生效 0-失效
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL == null ? null : fileURL.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}