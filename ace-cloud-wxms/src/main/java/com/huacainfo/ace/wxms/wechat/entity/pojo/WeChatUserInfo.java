/**
 * project name:distribution
 * file name:WeChatUserInfo
 * package name:com.arvin.wechat.model.pojo
 * date:2017-05-10 10:50
 * author:Arvin
 * Copyright (c) CD Technology Co.,Ltd. All rights reserved.
 */
package com.huacainfo.ace.wxms.wechat.entity.pojo;


import com.huacainfo.ace.common.tools.JsonUtil;

import java.util.List;

/**
 * description: 微信用户信息DTO
 * date: 2017-05-10 10:50
 *
 * @author ArvinZou
 * @version 1.0
 * @since JDK 1.8
 */
public class WeChatUserInfo {
    /*获取用户基本信息(UnionID机制)*/
//    {
//        "subscribe": 1,
//            "openid": "oBEX_wk52bj4WAHKHpsp8v2hq-Q8",
//            "nickname": "TJ雨林",
//            "sex": 1,
//            "language": "zh_CN",
//            "city": "东莞",
//            "province": "广东",
//            "country": "中国",
//            "headimgurl": "http:\/\/wx.qlogo.cn\/mmopen\/ajNVdqHZLLBRsdvuuHGD3Z3WkmJrKb6X4rNEibL89H3ibz4fkfdLKT0l5NaFPTXBYyQRtMOxeuA2vR9OUfaMZ9Lw\/0",
//            "subscribe_time": 1489648728,
//            "remark": "",
//            "groupid": 0,
//            "tagid_list": []
//    }
    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private int subscribe;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    private String subscribe_time;
    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;
    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private int groupid;
    /**
     * 用户被打上的标签ID列表
     */
    private List<String> tagid_list;

    /*微信网页授权机制 scope = snsapi_userinfo*/
//{
//    "openid":" OPENID",
//    " nickname": NICKNAME,
//    "sex":"1",
//    "province":"PROVINCE"
//    "city":"CITY",
//    "country":"COUNTRY",
//    "headimgurl":    "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
//    "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
//    "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
//}
    /**
     * 用户的唯一标识
     */
    private String openid;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private int sex;
    /**
     * 用户个人资料填写的省份
     */
    private String province;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;
    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(String subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public List<String> getTagid_list() {
        return tagid_list;
    }

    public void setTagid_list(List<String> tagid_list) {
        this.tagid_list = tagid_list;
    }


    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
