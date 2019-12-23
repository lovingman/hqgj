package com.huacainfo.ace.wxms.wechat.entity;

import java.io.Serializable;
import java.util.Map;

public class ConfigWechat implements Serializable {
    private String id;

    private String jdbcId;

    /**
     * 开发者ID
     */
    private String wxAppId;

    /**
     * 开发者密码
     */
    private String wxSecretKey;

    /**
     * 商户秘钥
     */
    private String wxApiKey;

    /**
     * 商户号
     */
    private String wxPartner;

    /**
     * token令牌
     */
    private String wxToken;

    private String wxDeviceInfo;

    private String wxOriginalId;

    /**
     * 系统识别ID
     */
    private String sysId;

    private Integer flag;

    private Integer enabled;

    private Integer version;

    private Integer status;

    private String remark;

    private String memo;

    private String createBy;

    private String createDt;

    private String updateDt;

    /**
     * 扩展属性
     */
    private Map<String, String> extendMap;

    public Map<String, String> getExtendMap() {
        return extendMap;
    }

    public void setExtendMap(Map<String, String> extendMap) {
        this.extendMap = extendMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getJdbcId() {
        return jdbcId;
    }

    public void setJdbcId(String jdbcId) {
        this.jdbcId = jdbcId == null ? null : jdbcId.trim();
    }

    public String getWxAppId() {
        return wxAppId;
    }

    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId == null ? null : wxAppId.trim();
    }

    public String getWxSecretKey() {
        return wxSecretKey;
    }

    public void setWxSecretKey(String wxSecretKey) {
        this.wxSecretKey = wxSecretKey == null ? null : wxSecretKey.trim();
    }

    public String getWxApiKey() {
        return wxApiKey;
    }

    public void setWxApiKey(String wxApiKey) {
        this.wxApiKey = wxApiKey == null ? null : wxApiKey.trim();
    }

    public String getWxToken() {
        return wxToken;
    }

    public void setWxToken(String wxToken) {
        this.wxToken = wxToken == null ? null : wxToken.trim();
    }

    public String getWxPartner() {
        return wxPartner;
    }

    public void setWxPartner(String wxPartner) {
        this.wxPartner = wxPartner == null ? null : wxPartner.trim();
    }

    public String getWxDeviceInfo() {
        return wxDeviceInfo;
    }

    public void setWxDeviceInfo(String wxDeviceInfo) {
        this.wxDeviceInfo = wxDeviceInfo == null ? null : wxDeviceInfo.trim();
    }

    public String getWxOriginalId() {
        return wxOriginalId;
    }

    public void setWxOriginalId(String wxOriginalId) {
        this.wxOriginalId = wxOriginalId == null ? null : wxOriginalId.trim();
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt == null ? null : createDt.trim();
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt == null ? null : updateDt.trim();
    }
}
