package com.huacainfo.ace.wxms.wechat.util;


import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.wxms.wechat.entity.pojo.base.Button;
import com.huacainfo.ace.wxms.wechat.entity.pojo.base.Menu;
import com.huacainfo.ace.wxms.wechat.entity.pojo.base.ViewButton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 创建菜单
 * Created by liuhaiyin on 2016/6/22.
 */
public class WeChatMenuUtil {

    /**
     * 服务器起来之后，为防止创建菜单的时候获取token导致服务器token不可用，通过本地执行得到menu的json串，然后调用服务器接口创建或者更新菜单
     *
     * @param args
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String sysAccount = "TRGF";
        String url = "http://localhost:8081/wechat-api/wechat/inner/intiMenu";
        Menu menu = initVillageMenu(sysAccount);
        StringBuffer data = new StringBuffer();
        data.append("menuJson=").append(URLEncoder.encode(JsonUtil.toJson(menu), "utf-8"));
        data.append("&").append("sysAccount=").append(sysAccount);
        System.out.println("req=" + data.toString());
        String result = HttpKit.post(url, data.toString());
        System.out.println("result=" + result);
    }

    /*
     * 初始化菜单
     *
     * url:wechat/info/getCode/1/TRGF
     * 第一个1表示是第一个页面   TRGF表示帐套号
     * @return
     */
    public static Menu initVillageMenu(String sysAccount) {
        //-------------------------------------------------------------
        //        菜单信息
        //-------------------------------------------------------------
        Menu menu = new Menu();
        ViewButton button1 = new ViewButton();
        button1.setName("竹纤维纸");
        button1.setType("view");
        button1.setUrl("http://www.hubeta.com/wechat-api/wechat/info/getCode" + "/1" + "/" + sysAccount);
        ViewButton button2 = new ViewButton();
        button2.setName("我的二维码");
        button2.setType("view");
        button2.setUrl("http://www.hubeta.com/wechat-api/wechat/info/getCode" + "/2" + "/" + sysAccount);


        ViewButton button3 = new ViewButton();
        button3.setName("服务中心");
        button3.setType("view");
        button3.setUrl("http://www.hubeta.com/wechat-api/wechat/info/getCode" + "/8" + "/" + sysAccount);
        ViewButton button3_1 = new ViewButton();
        button3_1.setName("老会员找密码");
        button3_1.setType("view");
        button3_1.setUrl("http://www.hubeta.com/wechat-api/wechat/info/getCode" + "/4" + "/" + sysAccount);
        ViewButton button3_2 = new ViewButton();
        button3_2.setName("投诉");
        button3_2.setType("view");
        button3_2.setUrl("http://www.hubeta.com/wechat-api/wechat/info/getCode" + "/5" + "/" + sysAccount);
        ViewButton button3_3 = new ViewButton();
        button3_3.setName("资质认证");
        button3_3.setType("view");
        button3_3.setUrl("http://www.hubeta.com/wechat-api/wechat/info/getCode" + "/6" + "/" + sysAccount);
        ViewButton button3_4 = new ViewButton();
        button3_4.setName("公司简介");
        button3_4.setType("view");
        button3_4.setUrl("http://www.hubeta.com/wechat-api/wechat/info/getCode" + "/7" + "/" + sysAccount);
        ViewButton button3_5 = new ViewButton();
        button3_5.setName("个人中心");
        button3_5.setType("view");
        button3_5.setUrl("http://www.hubeta.com/wechat-api/wechat/info/getCode" + "/3" + "/" + sysAccount);


        button3.setSub_button(new Button[]{button3_1, button3_2, button3_3, button3_4, button3_5});


        menu.setButton(new Button[]{button1, button2, button3});
        return menu;
    }
}
