package com.huacainfo.ace.zcpa.plugins.wechat.pojo;

public class Button {
//
//    参数	    	是否必须	    		说明
//    button		是	        								一级菜单数组，个数应为1~3个
//    sub_button	否	    									二级菜单数组，个数应为1~5个
//    type		    是											菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
//    name		    是											菜单标题，不超过16个字节，子菜单不超过60个字节
//    key			click等点击类型必须							菜单KEY值，用于消息接口推送，不超过128字节
//    url			view、miniprogram类型必须					    网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
//    media_id	    media_id类型和view_limited类型必须			调用新增永久素材接口返回的合法media_id
//    appid		    miniprogram类型必须							小程序的appid（仅认证公众号可配置）
//    pagepath	    miniprogram类型必须							小程序的页面路径

//    {
//        "type":"miniprogram",
//            "name":"wxa",
//            "url":"http://mp.weixin.qq.com",
//            "appid":"wx286b93c14bbf93aa",
//            "pagepath":"pages/lunar/index"
//    },


    private String type;
    private String name;
    private Button[] sub_button;
    private String key;
    private String url;
    private String media_id;
    private String appid;
    private String pagepath;

    public Button() {
    }

    /**
     * 父级菜单
     *
     * @param name
     * @param sub_button
     */
    public Button(String name, Button[] sub_button) {
        this.name = name;
        this.sub_button = sub_button;
    }

    /**
     * 纯网页链接型菜单
     *
     * @param type view
     * @param name
     * @param url
     */
    public Button(String type, String name, String url) {
        this.type = type;
        this.name = name;
        this.url = url;
    }

    /**
     * 跳转小程序
     *
     * @param type
     * @param name
     * @param url
     * @param appId
     * @param pagePath
     */
    public Button(String type, String name, String url, String appId, String pagePath) {
        this.type = type;
        this.name = name;
        this.url = url;
        this.appid = appId;
        this.pagepath = pagePath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
