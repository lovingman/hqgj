package com.huacainfo.ace.wxms.wechat.util;

import java.security.MessageDigest;

/**
 * PackageName:com.cdkj.common.util<br/>
 * Descript:SHA1加密算法 <br/>
 * Date: 2016-04-20 <br/>
 * User: Bovine
 * version 1.0
 */
public class Sha1Util {
    /**
     * Sha1加密方法
     *
     * @param str
     * @return
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String str = "jsapi_ticket=kgt8ON7yVITDhtdwci0qefheBoA_OYrLAOR5TzL64uoOiqxFET7Z9bW7sn-wc_2raCBltuD6YG0S5DGNGVx1WA&nonceStr=lkse5fr51mfvhczl4voyvszc68zwq6zl&timestamp=1476841118&url=http://www.hubeta.com/wechat-api/resources/TYZY/html/order/present.html";


        String digest = Sha1Util.getSha1(str);
        System.out.println(digest);

        // System.out.println( ToMD5.convertSHA1(data).toUpperCase());
    }
}
