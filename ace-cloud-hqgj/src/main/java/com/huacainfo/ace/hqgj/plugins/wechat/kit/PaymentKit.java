package com.huacainfo.ace.hqgj.plugins.wechat.kit;


import com.huacainfo.ace.common.tools.StringKit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


/**
 * 微信支付的统一下单工具类
 *
 * @author
 */
public class PaymentKit {

    private static final String CHARSET = "UTF-8";

    /**
     * 组装签名的字段
     *
     * @param params 参数
     * @return String
     */
    public static String packageSign(Map<String, String> params) {
        // 先将参数以其参数名的字典序升序进行排序
        TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Entry<String, String> param : sortedParams.entrySet()) {
            String value = param.getValue();
            if (StringKit.isEmpty(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=");

            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * 生成签名
     *
     * @return
     */
    public static String createSign(Map<String, String> params, String paternerKey) {
        // 生成签名前先去除sign
        params.remove("sign");
        String stringA = packageSign(params);
        String stringSignTemp = stringA + "&key=" + paternerKey;

        return HashUtil.md5(stringSignTemp).toUpperCase();
    }

    /**
     * 支付异步通知时校验sign
     *
     * @param params
     * @param paternerKey
     * @return
     */
    public static boolean verifyNotify(Map<String, String> params, String paternerKey) {
        String sign = params.get("sign");
        String localSign = PaymentKit.createSign(params, paternerKey);
        return sign.equals(localSign);
    }

    /**
     * 微信下单，map to xml
     *
     * @param params 参数
     * @return String
     */
    public static String toXml(Map<String, String> params) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 略过空值
            if (StringKit.isEmpty(value)) {
                continue;
            }
            xml.append("<").append(key).append(">");
            xml.append(entry.getValue());
            xml.append("</").append(key).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 针对支付的xml，没有嵌套节点的简单处理
     *
     * @param xmlStr xml字符串
     * @return Map<String, String> map集合
     */
    public static Map<String, String> xmlToMap(String xmlStr) {
        Document document = XmlKit.parse(xmlStr);
        Element root = document.getDocumentElement();
        Map<String, String> params = new HashMap<>();

        // 将节点封装成map形式
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            params.put(node.getNodeName(), node.getTextContent());
        }
        // 含有空白符会生成一个#text参数
        params.remove("#text");
        return params;
    }
}
