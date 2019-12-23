package com.huacainfo.ace.wxms.wechat.util;

import com.huacainfo.ace.wxms.wechat.entity.pojo.base.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 消息封装类
 *
 * @author Stephen
 */
public class MessageUtil {

    public static final String MESSAGE_TEXT = "text";//  文本消息
    public static final String MESSAGE_NEWS = "news";//  图文消息
    public static final String MESSAGE_IMAGE = "image";//  图片消息
    public static final String MESSAGE_VOICE = "voice";//  语音消息
    public static final String MESSAGE_VIDEO = "video";//  视频消息
    public static final String MESSAGE_SHORTVIDEO = "shortvideo";//  小视屏消息
    public static final String MESSAGE_LOCATION = "location";//  地理位置消息
    public static final String MESSAGE_LINK = "link";//  链接消息
    public static final String MESSAGE_EVNET = "event";//  关注/取消关注事件
    public static final String MESSAGE_CLICK = "CLICK";//  Click按钮
    public static final String MESSAGE_VIEW = "VIEW";//  VIEW按钮

    /**
     * 请求消息类型：事件
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 事件类型：subscribe(关注)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 事件类型：unsubscribe(取消关注)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * xml转为map集合
     *
     * @param ins
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, Object> xmlToMap(InputStream ins) throws IOException, DocumentException {
        Map<String, Object> map = new HashMap<>();
        SAXReader reader = new SAXReader();

        //InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        //解析成map集合
        MessageUtil.elementListToMap(list, map);

        ins.close();
        return map;
    }

    /**
     * @param list
     * @param map
     */
    private static void elementListToMap(List<Element> list, Map<String, Object> map) {
        if (list == null)
            return;
        for (Element e : list) {
            int childCount = e.elements().size();
            if (childCount > 0) {
                Map<String, Object> temmap = new HashMap<>();
                map.put(e.getName(), temmap);
                MessageUtil.elementListToMap(e.elements(), temmap);
            } else {
                map.put(e.getName(), e.getText());
            }
        }
    }

    /**
     * 将文本消息对象转为xml
     *
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     *
     */
    public static String PayBeanToXml(PayBean payBen) {
        XStream xstream = new XStream();
        xstream.alias("xml", payBen.getClass());
        return xstream.toXML(payBen);
    }

    /**
     * 组装文本消息
     *
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return textMessageToXml(text);
    }

    /**
     * 组装文本菜单消息
     *
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initMenuText(String toUserName, String fromUserName) {
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        String content = "亲爱的用户，你好，感谢您关注邮包铺子。请按以下菜单操作                                      \n" +
                "回复1：我告诉你我在想什么！                                                                 \n" +
                "回复2：我猜猜你在想什么！                                                                   \n" +
                "回复3：你猜猜我在想什么 ！                                                                 \n" +
                "回复？重新进入此菜单！";
        text.setContent(content);
        return textMessageToXml(text);
    }

    /**
     * 图文消息转为xml
     *
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 图片消息转为xml
     *
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {
        XStream xstream = new XStream();
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 图文消息的组装
     *
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName, String fromUserName) {
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        String url = "";// PropUtils.getString("wx.path.base")+"/wechat/toRegister?openId="+fromUserName;
        News news = new News();
        news.setTitle("微信包子铺");
        news.setDescription("首次使用请先注册(点击此进行注册)");
        String picUrl = "";// PropUtils.getString("wx.path.base")+"/resources/img/welcome.jpg";
        news.setPicUrl(picUrl);
        news.setUrl(url);
        newsList.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXml(newsMessage);
        return message;
    }

    /**
     * 图文消息的组装
     *
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String oldUserMessage(String toUserName, String fromUserName) {
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();
        String url = "";
        News news = new News();
        news.setTitle("微信包子铺");
        news.setDescription("尊敬的用户,欢迎回来");
        String picUrl = "";
        news.setPicUrl(picUrl);
        news.setUrl(url);

        newsList.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXml(newsMessage);
        return message;
    }

    /**
     * 组装图片消息
     *
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImageMessage(String toUserName, String fromUserName) {
        String message = null;
        Image image = new Image();
        image.setMediaId("JTH8vBl0zDRlrrn2bBnMleySuHjVbMhyAo0U2x7kQyd1ciydhhsVPONbnRrKGp8m");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(System.currentTimeMillis());
        imageMessage.setImage(image);
        message = imageMessageToXml(imageMessage);
        return message;
    }


    /**
     * 得到统一下单的URL接口XML
     *
     * @param appId    appId
     * @param attach   附加数据
     * @param info     商品信息描述
     * @param mchId    商户ID
     * @param nonceStr 随机字符串
     * @param url      通知地址
     * @param openid   Openid
     * @param tradeNo  订单号
     * @param ip       终端IP
     * @param fee      总金额
     * @param sign     签名
     * @return
     */
    public static String getPayBeanToXml(String appId, String attach, String info, String mchId, String nonceStr, String url, String openid, String tradeNo, String ip, double fee, String sign) {
        String payBenXml = "";
        PayBean PayBean = new PayBean();
        PayBean.setAppid(appId);
        PayBean.setAttach(attach);
        PayBean.setBody(info);
        PayBean.setMch_id(mchId);
        PayBean.setNonce_str(nonceStr);
        PayBean.setNotify_url(url);
        PayBean.setOpenid(openid);
        PayBean.setOut_trade_no(tradeNo);
        PayBean.setSpbill_create_ip(ip);
        PayBean.setTotal_fee(fee);
        PayBean.setTrade_type("JSAPI");//JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
        PayBean.setSign(sign);
        payBenXml = PayBeanToXml(PayBean);
        return payBenXml;
    }

    /**
     * 提醒用户有新的请求订单，链接带用户去支付页面
     *
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initPayMessage(String toUserName, String fromUserName, String url) {
        String message = null;
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("邮包铺子");
        news.setDescription("您有一个新的订单待支付(点击此进入支付页面)");
        news.setPicUrl("");
        news.setUrl(url);
        newsList.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXml(newsMessage);
        return message;
    }

    /**
     * * 到件通知消息模板-到达网点,无柜子信息
     *
     * @param openId     用户的Open_id 用户的唯一表示
     * @param templateId 模板ID
     * @param first      头信息
     * @param keyword1   运单号
     * @param keyword2   快递站点名称
     * @param keyword3   联系人
     * @param keyword4   联系人手机号
     * @param keyword5   取件地点
     * @return 模板信息Json字符串
     */
    public static String getArrivedMessageString(String openId, String templateId, String first, String keyword1, String keyword2, String keyword3
            , String keyword4, String keyword5) {
        /**
         * {{first.DATA}}
         运单号：{{keyword1.DATA}}
         快递站点名称：{{keyword2.DATA}}
         联系人：{{keyword3.DATA}}
         联系人手机号：{{keyword4.DATA}}
         取件地点：{{keyword5.DATA}}
         {{remark.DATA}}
         */
        String str = " {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"url\":\"\",\n" +
                "           \"topcolor\":\"#FF0000\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"" + first + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"" + keyword1 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\"" + keyword2 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\":{\n" +
                "                       \"value\":\"" + keyword3 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword4\":{\n" +
                "                       \"value\":\"" + keyword4 + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword5\":{\n" +
                "                       \"value\":\"" + keyword5 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"请您及时提取,祝您生活愉快\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        return str;
    }

    /**
     * * 到件通知消息模板-到达柜子
     *
     * @param openId     用户的Open_id 用户的唯一表示
     * @param templateId 模板ID
     * @param first      头信息
     * @param keyword1   运单号
     * @param keyword2   取件地点
     * @param keyword3   取件密码
     * @param keyword4   联系人手机号
     * @return 模板信息Json字符串
     */
    public static String getArrivedCupBoardMessageString(String openId, String templateId, String first, String keyword1, String keyword2, String keyword3
            , String keyword4) {
        /**
         * {{first.DATA}}
         运单号：{{keyword1.DATA}}
         取件地点：{{keyword2.DATA}}
         取件密码：{{keyword3.DATA}}
         联系人手机号：{{keyword4.DATA}}
         {{remark.DATA}}
         */
        String str = " {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"url\":\"\",\n" +
                "           \"topcolor\":\"#FF0000\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"" + first + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"" + keyword1 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\"" + keyword2 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\":{\n" +
                "                       \"value\":\"" + keyword3 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword4\":{\n" +
                "                       \"value\":\"" + keyword4 + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"请您及时提取,祝您生活愉快\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        return str;
    }

    /**
     * * 支付成功消息模板
     *
     * @param openId     用户的Open_id 用户的唯一表示
     * @param templateId 模板ID
     * @param str1       第一条数据
     * @param str2       第二条数据
     * @param str3       第三条数据
     * @param str4       第四条数据
     * @param str5       第五条数据
     * @return 模板信息Json字符串
     */
    public static String getPaySuccerString(String openId, String templateId, String str1, String str2, String str3, String str4
            , String str5) {
        String str = " {\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"" + templateId + "\",\n" +
                "           \"url\":\"\",\n" +
                "           \"topcolor\":\"#FF0000\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"" + str1 + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"" + str2 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\":{\n" +
                "                       \"value\":\"" + str3 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword3\":{\n" +
                "                       \"value\":\"" + str4 + "\",\n" +
                "                      \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword4\":{\n" +
                "                       \"value\":\"" + str5 + "\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"感谢您的惠顾\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        return str;
    }
}
