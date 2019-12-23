package com.huacainfo.ace.wxms.wechat.entity.pojo.base;

/**
 * Created by Administrator on 2016/4/13.
 */
public class PayBean {
    /**
     * AppID
     */
    private String appid;
    /**
     * 附加数据
     */
    private String attach;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 通知地址
     */
    private String notify_url;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 终端IP
     */
    private String spbill_create_ip;
    /**
     * 总金额
     */
    private double total_fee;
    /**
     * 交易类型
     */
    private String trade_type;
    /**
     * 签名
     */
    private String sign;

    /************************微信支付动作实体**************************/
    private String bank_type;
    private String cash_fee;
    private String device_info;
    private String fee_type;
    private String is_subscribe;
    private String result_code;
    private String return_code;
    private String time_end;
    private String transaction_id;

    /****************************************************************/


    public PayBean(String appid, String attach, String body, String mch_id, String nonce_str, String notify_url, String openid, String out_trade_no, String spbill_create_ip, double total_fee, String trade_type, String sign) {
        this.appid = appid;
        this.attach = attach;
        this.body = body;
        this.mch_id = mch_id;
        this.nonce_str = nonce_str;
        this.notify_url = notify_url;
        this.openid = openid;
        this.out_trade_no = out_trade_no;
        this.spbill_create_ip = spbill_create_ip;
        this.total_fee = total_fee;
        this.trade_type = trade_type;
        this.sign = sign;
    }

    public PayBean() {
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
