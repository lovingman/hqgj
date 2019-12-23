package com.huacainfo.ace.wxms.wechat.entity.pojo;


public class TldWechatReply {
    /**
     * TLD_WECHAT_REPLY.ID
     * id:主键ID
     */
    private String id;

    /**
     * TLD_WECHAT_REPLY.FIRST_ID
     * firstId:顶层ID
     */
    private String firstId;

    /**
     * TLD_WECHAT_REPLY.SYS_ACCOUNT
     * sysAccount:帐套号
     */
    private String sysAccount;

    /**
     * TLD_WECHAT_REPLY.KEY_WORDS
     * keyWords:关键字
     */
    private String keyWords;

    /**
     * TLD_WECHAT_REPLY.REPLY_CONTENT
     * replyContent:回复文本
     */
    private String replyContent;

    /**
     * TLD_WECHAT_REPLY.ENABLED
     * enabled:数据有效性 0,失效，1有效
     */
    private Short enabled;

    /**
     * TLD_WECHAT_REPLY.VERSION
     * version:版本号
     */
    private Integer version;

    /**
     * TLD_WECHAT_REPLY.STATUS
     * status:业务状态
     */
    private Short status;

    /**
     * TLD_WECHAT_REPLY.REMARK
     * remark:业务备注字段
     */
    private String remark;

    /**
     * TLD_WECHAT_REPLY.MEMO
     * memo:非业务备注字段
     */
    private String memo;

    /**
     * TLD_WECHAT_REPLY.CREATE_BY
     * createBy:创建人
     */
    private String createBy;

    /**
     * TLD_WECHAT_REPLY.CREATE_DT
     * createDt:创建日期
     */
    private String createDt;

    /**
     * TLD_WECHAT_REPLY.UPDATE_DT
     * updateDt:更新日期
     */
    private String updateDt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFirstId() {
        return firstId;
    }

    public void setFirstId(String firstId) {
        this.firstId = firstId == null ? null : firstId.trim();
    }

    public String getSysAccount() {
        return sysAccount;
    }

    public void setSysAccount(String sysAccount) {
        this.sysAccount = sysAccount == null ? null : sysAccount.trim();
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords == null ? null : keyWords.trim();
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Short getEnabled() {
        return enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
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
