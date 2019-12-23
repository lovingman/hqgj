package com.huacainfo.ace.wxms.wechat.entity.pojo;


public class TldWechatMenu {
    /**
     * TLD_WECHAT_MENU.ID
     * id:主键ID
     */
    private String id;

    /**
     * TLD_WECHAT_MENU.FIRST_ID
     * firstId:顶层ID
     */
    private String firstId;

    /**
     * TLD_WECHAT_MENU.SYS_ACCOUNT
     * sysAccount:帐套号
     */
    private String sysAccount;

    /**
     * TLD_WECHAT_MENU.MENU_NAME
     * menuName:菜单名称
     */
    private String menuName;

    /**
     * TLD_WECHAT_MENU.MENU_TYPE
     * menuType:菜单类型(view,click等)
     */
    private String menuType;

    /**
     * TLD_WECHAT_MENU.MENU_URL_CODE
     * menuUrlCode:菜单的CODE，用于微信跳转时区分是哪个菜单
     */
    private Integer menuUrlCode;

    /**
     * TLD_WECHAT_MENU.MENU_URL
     * menuUrl:菜单URL
     */
    private String menuUrl;

    /**
     * TLD_WECHAT_MENU.REDIRECT_URL
     * redirectUrl:菜单重定向URL
     */
    private String redirectUrl;

    /**
     * TLD_WECHAT_MENU.MENU_LEVEL
     * menuLevel:菜单层级【1 一级 2 二级 3 三级 4 四级  5 五级】
     */
    private Short menuLevel;

    /**
     * TLD_WECHAT_MENU.MENU_ORDER
     * menuOrder:菜单排序【同一层级内排序】
     */
    private Long menuOrder;

    /**
     * TLD_WECHAT_MENU.ID
     * id:主键ID
     */
    /**
     * TLD_WECHAT_MENU.MENU_PID
     * menuPid:上级菜单ID
     */
    private String menuPid;

    /**
     * TLD_WECHAT_MENU.ENABLED
     * enabled:数据有效性 0,失效，1有效
     */
    private Short enabled;

    /**
     * TLD_WECHAT_MENU.VERSION
     * version:版本号
     */
    private Integer version;

    /**
     * TLD_WECHAT_MENU.STATUS
     * status:业务状态
     */
    private Short status;

    /**
     * TLD_WECHAT_MENU.REMARK
     * remark:业务备注字段
     */
    private String remark;

    /**
     * TLD_WECHAT_MENU.MEMO
     * memo:非业务备注字段
     */
    private String memo;

    /**
     * TLD_WECHAT_MENU.CREATE_BY
     * createBy:创建人
     */
    private String createBy;

    /**
     * TLD_WECHAT_MENU.CREATE_DT
     * createDt:创建日期
     */
    private String createDt;

    /**
     * TLD_WECHAT_MENU.UPDATE_DT
     * updateDt:更新日期
     */
    private String updateDt;

    /**
     * TLD_WECHAT_MENU.EMOJI_CODE
     * emojiCode:emoji表情代码
     */
    private Integer emojiCode;

    /**
     * TLD_WECHAT_MENU.EMOJI_KEY
     * emojiKey:emoji表情内容
     */
    private String emojiKey;

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public Integer getMenuUrlCode() {
        return menuUrlCode;
    }

    public void setMenuUrlCode(Integer menuUrlCode) {
        this.menuUrlCode = menuUrlCode;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    public Short getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Short menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Long getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Long menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        this.menuPid = menuPid == null ? null : menuPid.trim();
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

    public Integer getEmojiCode() {
        return emojiCode;
    }

    public void setEmojiCode(Integer emojiCode) {
        this.emojiCode = emojiCode;
    }

    public String getEmojiKey() {
        return emojiKey;
    }

    public void setEmojiKey(String emojiKey) {
        this.emojiKey = emojiKey;
    }
}
