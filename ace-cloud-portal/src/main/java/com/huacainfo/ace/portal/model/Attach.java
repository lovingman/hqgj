package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：attach
 * 注释：附件
 *
 * @author HuaCai008
 * @date 2019-05-17
 */
public class Attach extends BaseModel {
    /**
     * 字段名：attach.attach_id
     * 注释：序号
     */
    private Integer attachId;

    /**
     * 字段名：attach.category
     * 注释：类别
     */
    private String category;

    /**
     * 字段名：attach.notice_id
     * 注释：公告编号
     */
    private String noticeId;

    /**
     * 字段名：attach.file_url
     * 注释：地址
     */
    private String fileUrl;

    /**
     * 字段名：attach.file_name
     * 注释：
     */
    private String fileName;

    /**
     * 字段名：attach.file_size
     * 注释：附件大小
     */
    private String fileSize;

    /**
     * 字段名：attach.create_time
     * 注释：入库时间
     */
    private Date createTime;

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
