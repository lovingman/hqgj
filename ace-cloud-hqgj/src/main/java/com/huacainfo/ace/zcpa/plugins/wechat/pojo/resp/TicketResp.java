package com.huacainfo.ace.zcpa.plugins.wechat.pojo.resp;

import com.huacainfo.ace.common.model.BaseModel;

/**
 * @ClassName TicketResp
 * @Description 授权页ticket接口返回类
 * @Author Arvin Zou
 * @Date 2019/9/17 14:51
 */
public class TicketResp extends BaseModel {
    /**
     * Int	是	错误码，含义见错误码
     */
    private int errcode;
    /**
     * String	是	错误信息，含义见错误码
     */
    private String errmsg;
    /**
     * String	是	临时票据，用于在获取授权链接时作为参数传入
     */
    private String ticket;
    /**
     * Int	是	ticket 的有效期，一般为 7200 秒
     */
    private int expires_in;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
