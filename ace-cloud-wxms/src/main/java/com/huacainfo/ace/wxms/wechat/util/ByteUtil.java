package com.huacainfo.ace.wxms.wechat.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ArvinZou
 */
public class ByteUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ByteUtil.class.getName());

    /**
     * 取Request中的数据对象
     *
     * @param valueType
     * @return
     * @throws Exception
     */
    public static <T> T getRequestObject(HttpServletRequest request, Class<T> valueType) throws Exception {
        int len = request.getContentLength();
        if (len > 0) {
            ServletInputStream inputStream = request.getInputStream();
            byte[] json = new byte[len];
            inputStream.read(json, 0, len);
            inputStream.close();
            //System.out.println("param: " + new String(json));
            LOGGER.info("param: " + new String(json));
            return JSONObject.parseObject(json, valueType);
        } else {
            LOGGER.info("param: " + "{}");
            return JSONObject.parseObject("{}", valueType);
        }
    }

    public static byte[] getRequestBytes(HttpServletRequest request) throws Exception {
        int len = request.getContentLength();
        if (len > 0) {
            ServletInputStream inputStream = request.getInputStream();
            byte[] json = new byte[len];
            inputStream.read(json, 0, len);
            inputStream.close();
            return json;
        } else {
            LOGGER.info("param: " + "{}");
            return new byte[0];
        }
    }


    /**
     * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src) {
        return bytesToHexString(src, "");
    }

    /**
     * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src, String splitChar) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv + splitChar);
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
