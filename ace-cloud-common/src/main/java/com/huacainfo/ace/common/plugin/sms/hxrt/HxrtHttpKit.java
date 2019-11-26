package com.huacainfo.ace.common.plugin.sms.hxrt;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName HxrtHttpKit
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/8/5 20:02
 */
public class HxrtHttpKit {

    public static String getSend(String strUrl, String param) {
        URL url = null;
        HttpURLConnection connection = null;

        try {
            url = new URL(strUrl + "?" + param);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static String postSend(String strUrl, String param) {
        Logger logger = Logger.getLogger(HxrtHttpKit.class);

        URL url = null;
        HttpURLConnection connection = null;

        try {
            url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.connect();

            //POST����ʱʹ��
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            logger.error(e);
            return e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }


    }

    /**
     * תΪ16���Ʒ���
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String paraTo16(String str) throws UnsupportedEncodingException {
        String hs = "";

        byte[] byStr = str.getBytes("UTF-8");
        for (int i = 0; i < byStr.length; i++) {
            String temp = "";
            temp = (Integer.toHexString(byStr[i] & 0xFF));
            if (temp.length() == 1) {
                temp = "%0" + temp;
            } else {
                temp = "%" + temp;
            }
            hs = hs + temp;
        }
        return hs.toUpperCase();


    }

}
