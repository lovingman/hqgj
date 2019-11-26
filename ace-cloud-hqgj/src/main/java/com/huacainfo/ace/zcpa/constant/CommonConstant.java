package com.huacainfo.ace.zcpa.constant;

/**
 * @ClassName CommonConstant
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 17:22
 */
public interface CommonConstant {
    /**
     * 系统标识
     */
    String SYS_ID = "zcpa";
    /**
     * 短信签名
     */
    String SMS_TAG = "【常德市众创平安志愿服务平台】";
    /**
     * 注册默认单位ID
     */
    String CORP_ID = "0002";
    /**
     * 账户状态 - 有效，可登陆
     */
    String User_State_VALID = "1";
    /**
     * 账户状态 - 无效，不可登陆
     */
    String User_State_INVALID = "0";

    /**
     * 用户类型- 志愿者
     */
    String User_Type_Volunteer = "volunteer";
    /**
     * 用户类型- 群众
     */
    String User_Type_Masses = "masses";
}
