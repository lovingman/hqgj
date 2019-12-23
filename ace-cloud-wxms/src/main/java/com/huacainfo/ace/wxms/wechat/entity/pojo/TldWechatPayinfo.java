package com.huacainfo.ace.wxms.wechat.entity.pojo;


import java.math.BigDecimal;

public class TldWechatPayinfo {
    /**
     * TLD_WECHAT_PAYINFO.ID
     * id:主键ID
     */
    private String id;

    /**
     * TLD_WECHAT_PAYINFO.FIRST_ID
     * firstId:顶层ID
     */
    private String firstId;

    /**
     * TLD_WECHAT_PAYINFO.SYS_ACCOUNT
     * sysAccount:帐套号
     */
    private String sysAccount;

    private String returnCode;

    private String returnMsg;

    /**
     * TLD_WECHAT_PAYINFO.ID
     * id:主键ID
     */
    /**
     * TLD_WECHAT_PAYINFO.APPID
     * appid:微信分配的公众账号ID（企业号corpid即为此appId）
     */
    private String appid;

    /**
     * TLD_WECHAT_PAYINFO.MCH_ID
     * mchId:微信支付分配的商户号
     */
    private String mchId;

    /**
     * TLD_WECHAT_PAYINFO.DEVICE_INFO
     * deviceInfo:微信支付分配的终端设备号
     */
    private String deviceInfo;

    /**
     * TLD_WECHAT_PAYINFO.NONCE_STR
     * nonceStr:随机字符串，不长于32位
     */
    private String nonceStr;

    /**
     * TLD_WECHAT_PAYINFO.SIGN
     * sign:签名
     */
    private String sign;

    /**
     * TLD_WECHAT_PAYINFO.RESULT_CODE
     * resultCode:SUCCESS/FAIL【交易是否成功标示】
     */
    private String resultCode;

    /**
     * TLD_WECHAT_PAYINFO.ERR_CODE
     * errCode:错误代码
     */
    private String errCode;

    /**
     * TLD_WECHAT_PAYINFO.ERR_CODE_DES
     * errCodeDes:错误返回的信息描述
     */
    private String errCodeDes;

    /**
     * TLD_WECHAT_PAYINFO.ID
     * id:主键ID
     */
    /**
     * TLD_WECHAT_PAYINFO.OPENID
     * openid:用户在商户appid下的唯一标识
     */
    private String openid;

    /**
     * TLD_WECHAT_PAYINFO.IS_SUBSCRIBE
     * isSubscribe:用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     */
    private String isSubscribe;

    /**
     * TLD_WECHAT_PAYINFO.TRADE_TYPE
     * tradeType:交易类型:JSAPI、NATIVE、APP
     */
    private String tradeType;

    /**
     * TLD_WECHAT_PAYINFO.BANK_TYPE
     * bankType:银行类型，采用字符串类型的银行标识
     */
    private String bankType;

    /**
     * TLD_WECHAT_PAYINFO.TOTAL_FEE
     * totalFee:订单总金额，单位为分
     */
    private BigDecimal totalFee;

    /**
     * TLD_WECHAT_PAYINFO.FEE_TYPE
     * feeType:货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    private String feeType;

    /**
     * TLD_WECHAT_PAYINFO.CASH_FEE
     * cashFee:现金支付金额订单现金支付金额
     */
    private BigDecimal cashFee;

    /**
     * TLD_WECHAT_PAYINFO.CASH_FEE_TYPE
     * cashFeeType:货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
     */
    private String cashFeeType;

    /**
     * TLD_WECHAT_PAYINFO.COUPON_FEE
     * couponFee:代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额
     */
    private BigDecimal couponFee;

    /**
     * TLD_WECHAT_PAYINFO.COUPON_COUNT
     * couponCount:代金券或立减优惠使用数量
     */
    private BigDecimal couponCount;

    /**
     * TLD_WECHAT_PAYINFO.COUPON_ID_0
     * couponId0:代金券或立减优惠ID【第一个】
     */
    private String couponId0;

    /**
     * TLD_WECHAT_PAYINFO.COUPON_FEE_0
     * couponFee0:单个代金券或立减优惠支付金额【第一个】
     */
    private BigDecimal couponFee0;

    /**
     * TLD_WECHAT_PAYINFO.TRANSACTION_ID
     * transactionId:微信支付订单号
     */
    private String transactionId;

    /**
     * TLD_WECHAT_PAYINFO.OUT_TRADE_NO
     * outTradeNo:商户系统的订单号，与请求一致。
     */
    private String outTradeNo;

    /**
     * TLD_WECHAT_PAYINFO.ATTACH
     * attach:商家数据包，原样返回
     */
    private String attach;

    /**
     * TLD_WECHAT_PAYINFO.TIME_END
     * timeEnd:支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
     */
    private String timeEnd;

    /**
     * TLD_WECHAT_PAYINFO.COUPON_ID_1
     * couponId1:代金券或立减优惠ID【第二个】
     */
    private String couponId1;

    /**
     * TLD_WECHAT_PAYINFO.COUPON_FEE_1
     * couponFee1:单个代金券或立减优惠支付金额【第二个】
     */
    private BigDecimal couponFee1;

    /**
     * TLD_WECHAT_PAYINFO.COUPON_ID_2
     * couponId2:代金券或立减优惠ID【第三个】
     */
    private String couponId2;

    /**
     * TLD_WECHAT_PAYINFO.COUPON_FEE_2
     * couponFee2:单个代金券或立减优惠支付金额【第三个】
     */
    private BigDecimal couponFee2;

    /**
     * TLD_WECHAT_PAYINFO.ENABLED
     * enabled:数据有效性 0,失效，1有效
     */
    private Short enabled;

    /**
     * TLD_WECHAT_PAYINFO.VERSION
     * version:版本号
     */
    private Integer version;

    /**
     * TLD_WECHAT_PAYINFO.STATUS
     * status:业务状态
     */
    private Short status;

    /**
     * TLD_WECHAT_PAYINFO.REMARK
     * remark:业务备注字段
     */
    private String remark;

    /**
     * TLD_WECHAT_PAYINFO.MEMO
     * memo:非业务备注字段
     */
    private String memo;

    /**
     * TLD_WECHAT_PAYINFO.CREATE_BY
     * createBy:创建人
     */
    private String createBy;

    /**
     * TLD_WECHAT_PAYINFO.CREATE_DT
     * createDt:创建日期
     */
    private String createDt;

    /**
     * TLD_WECHAT_PAYINFO.UPDATE_DT
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

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg == null ? null : returnMsg.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo == null ? null : deviceInfo.trim();
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr == null ? null : nonceStr.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode == null ? null : resultCode.trim();
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode == null ? null : errCode.trim();
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes == null ? null : errCodeDes.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe == null ? null : isSubscribe.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType == null ? null : cashFeeType.trim();
    }

    public BigDecimal getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(BigDecimal couponFee) {
        this.couponFee = couponFee;
    }

    public BigDecimal getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(BigDecimal couponCount) {
        this.couponCount = couponCount;
    }

    public String getCouponId0() {
        return couponId0;
    }

    public void setCouponId0(String couponId0) {
        this.couponId0 = couponId0 == null ? null : couponId0.trim();
    }

    public BigDecimal getCouponFee0() {
        return couponFee0;
    }

    public void setCouponFee0(BigDecimal couponFee0) {
        this.couponFee0 = couponFee0;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach == null ? null : attach.trim();
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd == null ? null : timeEnd.trim();
    }

    public String getCouponId1() {
        return couponId1;
    }

    public void setCouponId1(String couponId1) {
        this.couponId1 = couponId1 == null ? null : couponId1.trim();
    }

    public BigDecimal getCouponFee1() {
        return couponFee1;
    }

    public void setCouponFee1(BigDecimal couponFee1) {
        this.couponFee1 = couponFee1;
    }

    public String getCouponId2() {
        return couponId2;
    }

    public void setCouponId2(String couponId2) {
        this.couponId2 = couponId2 == null ? null : couponId2.trim();
    }

    public BigDecimal getCouponFee2() {
        return couponFee2;
    }

    public void setCouponFee2(BigDecimal couponFee2) {
        this.couponFee2 = couponFee2;
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
