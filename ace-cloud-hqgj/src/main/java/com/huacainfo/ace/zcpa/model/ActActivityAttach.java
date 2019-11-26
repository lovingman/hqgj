package com.huacainfo.ace.zcpa.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名：act_activity_attach
 * 注释：项目志愿者活动详情附件
 *
 * @author admin
 * @date 2019-07-31
 */
public class ActActivityAttach extends BaseModel {
    /**
     * 字段名：act_activity_attach.id
     * 注释：主键
     */
    private String id;

    /**
     * 字段名：act_activity_attach.actId
     * 注释：活动主键
     */
    private String actid;

    /**
     * 字段名：act_activity_attach.fileType
     * 注释：附件类型
     */
    private String filetype;

    /**
     * 字段名：act_activity_attach.fileName
     * 注释：附件名称
     */
    private String filename;

    /**
     * 字段名：act_activity_attach.fileSize
     * 注释：附件大小
     */
    private BigDecimal filesize;

    /**
     * 字段名：act_activity_attach.fileURL
     * 注释：附件URL地址
     */
    private String fileurl;

    /**
     * 字段名：act_activity_attach.remark
     * 注释：备注
     */
    private String remark;

    /**
     * 字段名：act_activity_attach.status
     * 注释：状态
     */
    private String status;

    /**
     * 字段名：act_activity_attach.createDate
     * 注释：创建日期
     */
    private Date createdate;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getActid() {
        return actid;
    }

    public void setActid(String actid) {
        this.actid = actid == null ? null : actid.trim();
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype == null ? null : filetype.trim();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public BigDecimal getFilesize() {
        return filesize;
    }

    public void setFilesize(BigDecimal filesize) {
        this.filesize = filesize;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null : fileurl.trim();
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
