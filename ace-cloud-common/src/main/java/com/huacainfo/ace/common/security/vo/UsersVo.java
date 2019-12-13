package com.huacainfo.ace.common.security.vo;

import com.huacainfo.ace.common.security.model.Users;

public class UsersVo extends Users {
    /**
     * 验证码
     */
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
