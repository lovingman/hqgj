package com.huacainfo.ace.wxms.wechat.entity.pojo.base;

public class AccessToken {
    private String token;
    private int expiresIn;//失效小时
    private int expiresTime;//失效时间  单位：s
    private String ticket;

    /**
     * 最后一次调用接口获取 AccessToken 的时间点
     */
    private String lastTime;

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Integer expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
