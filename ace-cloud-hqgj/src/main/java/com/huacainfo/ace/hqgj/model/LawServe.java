package com.huacainfo.ace.hqgj.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * 表名：law_serve
 * 注释：法律服务表
 *
 * @author admin
 * @date 2019-12-10
 */
public class LawServe extends BaseModel {
    /**
     * 字段名：law_serve.id
     * 注释：主键ID
     */
    private String id;

    /**
     * 字段名：law_serve.title
     * 注释：标题名称
     */
    private String title;

    /**
     * 字段名：law_serve.createUserId
     * 注释：创建人ID
     */
    private String createUserId;

    /**
     * 字段名：law_serve.createUserName
     * 注释：创建人姓名
     */
    private String createUserName;

    /**
     * 字段名：law_serve.createDate
     * 注释：创建时间
     */
    private Date createDate;

    /**
     * 字段名：law_serve.modifyUserId
     * 注释：更新人ID
     */
    private String modifyUserId;

    /**
     * 字段名：law_serve.modifyUserName
     * 注释：更新人姓名
     */
    private String modifyUserName;

    /**
     * 字段名：law_serve.modifyDate
     * 注释：修改时间
     */
    private Date modifyDate;

    /**
     * 字段名：law_serve.status
     * 注释：状态  1-生效  0-失效
     */
    private String status;
    private List<BasicAnnex> basicAnnexes ;

    public List<BasicAnnex> getBasicAnnexes() {
        return basicAnnexes;
    }
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
