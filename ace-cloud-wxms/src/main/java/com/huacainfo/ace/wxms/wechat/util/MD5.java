
package com.huacainfo.ace.wxms.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MD5 {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    protected static Logger logger = LoggerFactory.getLogger(MD5.class);

    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte psw[] = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(psw);
            return toHex(md.digest());
        } catch (IllegalStateException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer();
        String s = null;
        for (int i = 0; i < buffer.length; i++) {
            s = Integer.toHexString((int) buffer[i] & 0xff);
            if (s.length() < 2) {
                sb.append('0');
            }
            sb.append(s);
        }
        return sb.toString().toUpperCase();
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    /**
     * 用于推广的密码处理操作
     *
     * @param password
     * @return
     */
    public static String md54s(String password) {
        MessageDigest md5 = null;
        String mdpass = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("run error", e);
        }
        try {
            mdpass = ByteUtil.bytesToHexString(md5.digest(password.getBytes("utf-8")));
        } catch (Exception e) {
            logger.error("run error", e);
        }
        return mdpass;
    }

    /**
     * 盐值加密
     *
     * @param id       盐值主字段
     * @param password 密码
     * @return 摘要结果
     */
    public static String passwordEncrypt(String id, String password) {
        return md5(password + md5(id + "id") + "pw");
    }

    /**
     * 自定义盐值加密
     *
     * @param password 密码
     * @param salt     盐值
     * @return 摘要结果
     */
    public static String passwordWithSalt(String password, String salt) {
        return md5(password + salt);
    }

    /**
     * 签名校验
     *
     * @param params    参数列表
     * @param sign      校验签串
     * @param saltValue md5加密盐值
     * @return true /false
     */
    public static boolean validSign(Map<String, Object> params, String sign, String saltValue) {
        Map<String, Object> treeMap = null;
        if (null != params && (params instanceof TreeMap)) {
            treeMap = params;
        } else if (null != params) {
            treeMap = new TreeMap<>();
            treeMap.putAll(params);
        }
        try {
            String key = "";
            String value = null;
            StringBuilder sb = new StringBuilder();
            for (Iterator it = treeMap.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = (String) treeMap.get(key);
                sb.append(key + "=" + URLEncoder.encode(value, "utf-8")).append("&");
            }

            /*盐值*/
            sb.append(saltValue);
            String signTemp = MD5.MD5Encode(sb.toString(), "utf-8");
            /*比较结果*/
            if (sign.equals(signTemp))
                return true;
        } catch (Exception e) {
            logger.error("MD5.validSign: {}" + e);
        }
        return false;
    }

    /**
     * 签名校验
     *
     * @param params    参数列表
     * @param saltValue md5加密盐值
     * @return true /false
     */
    public static String sign(Map<String, Object> params, String saltValue) {
        Map<String, Object> treeMap = null;
        if (null != params && (params instanceof TreeMap)) {
            treeMap = params;
        } else if (null != params) {
            treeMap = new TreeMap<>();
            treeMap.putAll(params);
        }
        try {
            String key = "";
            String value = null;
            StringBuilder sb = new StringBuilder();
            for (Iterator it = treeMap.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = (String) treeMap.get(key);
                sb.append(key + "=" + URLEncoder.encode(value, "utf-8")).append("&");
            }

            /*盐值*/
            sb.append(saltValue);
            String sign = MD5.MD5Encode(sb.toString(), "utf-8");

            return sign;
        } catch (Exception e) {
            logger.error("MD5.sign: {}" + e);
        }
        return "";
    }
}
