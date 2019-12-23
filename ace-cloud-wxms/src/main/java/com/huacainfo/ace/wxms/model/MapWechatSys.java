package com.huacainfo.ace.wxms.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

/**
 * 表名：map_wechat_sys
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-10-09
 */
public class MapWechatSys extends BaseModel {
    /**
     * 字段名：map_wechat_sys.id
     * 注释：唯一标识
     */
    private String id;

    /**
     * 字段名：map_wechat_sys.appType
     * 注释：应用类型;
     * 公众号-app;小程序-mini
     */
    private String appType;

    /**
     * 字段名：map_wechat_sys.unionId
     * 注释：微信unionid
     */
    private String unionId;

    /**
     * 字段名：map_wechat_sys.userId
     * 注释：系统账户唯一id
     */
    private String userId;

    /**
     * 字段名：map_wechat_sys.status
     * 注释：状态码,默认1
     */
    private Integer status;

    /**
     * 字段名：map_wechat_sys.remark
     * 注释：备注信息
     */
    private String remark;

    /**
     * 字段名：map_wechat_sys.createDate
     * 注释：数据入库时间
     */
    private Date createDate;

    /**
     * 字段名：map_wechat_sys.appId
     * 注释：应用唯一标识
     */
    private String appId;

    /**
     * 字段名：map_wechat_sys.sysId
     * 注释：对应系统标识
     */
    private String sysId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }
}
