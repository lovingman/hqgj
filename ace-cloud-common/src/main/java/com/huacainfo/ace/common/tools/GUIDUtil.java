package com.huacainfo.ace.common.tools;

import java.util.UUID;

/**
 * UUID处理
 */
public final class GUIDUtil {

    /**
     * 获取标准GUID
     * 如：B00C2BD3-036E-4BBD-9B38-30D00610190E
     */
    private static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();// 标准UUID
    }

    /**
     * 获取32位大写的GUID
     * 如：B00C2BD3036E4BBD9B3830D00610190E
     */
    public static String getGUID() {
        String str = getUUID();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }

    /**
     * 获取指定数量的GUID
     */
    public static String[] getGUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getGUID();
        }
        return ss;
    }

    /**
     * 获取指定数量的UUID
     */
    private static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static void main(String args[]){
       System.out.println( GUIDUtil.getGUID());

    }
}
