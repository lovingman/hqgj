package com.huacainfo.ace.common.plugin.sms.hxrt;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * @ClassName HxrtApi
 * @Description 华兴软通，sdk接口调用demo;短信发送应用
 * @Author Arvin Zou
 * @Date 2019/8/5 20:00
 */
public class HxrtApi {

    /**
     * @param strUrl 请求地址
     * @param param  参数字符串
     * @return 返回字符串
     * @throws Exception
     */
    public static String requestGet(String strUrl, String param) {
        String returnStr = null; // 返回结果定义
        URL url = null;
        HttpsURLConnection httpsURLConnection = null;

        try {
            url = new URL(strUrl + "?" + param);
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            MyX509TrustManager myX509TrustManager = new MyX509TrustManager();
            TrustManager[] tm = {myX509TrustManager};        //信任证书管理器
            sslcontext.init(null, tm, new java.security.SecureRandom());
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(sslcontext.getSocketFactory()); // 设置套接工厂
            httpsURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setRequestMethod("GET"); // get方式
            httpsURLConnection.setUseCaches(false); // 不用缓存
            httpsURLConnection.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(httpsURLConnection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
            returnStr = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
        }
        return returnStr;
    }

    public static String requestPost(String strUrl, String param) {
        String returnStr = null; // 返回结果定义
        URL url = null;
        HttpsURLConnection httpsURLConnection = null;

        try {
            url = new URL(strUrl);
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            MyX509TrustManager myX509TrustManager = new MyX509TrustManager();
            TrustManager[] tm = {myX509TrustManager};        //信任证书管理器
            sslcontext.init(null, tm, new java.security.SecureRandom());
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(sslcontext.getSocketFactory()); // 设置套接工厂
            httpsURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpsURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setRequestMethod("POST"); // post方式
            httpsURLConnection.connect();

            //POST方法时使用
            DataOutputStream out = new DataOutputStream(httpsURLConnection.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(httpsURLConnection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
            returnStr = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
        }
        return returnStr;
    }

    /**
     * 获取余额的方法
     *
     * @param type 请求方式,1为get,2为post
     */
    public static void getBalance(int type) {
        String url = "https://www.stongnet.com/sdkhttp/getbalance.aspx";
        String regCode = "101100-WEB-HUAX-111111"; // 华兴软通注册码，请在这里填写您从客服那得到的注册码
        String regPasswod = "11111111"; // 华兴软通注册码对应的密码，请在这里填写您从客服那得到的注册码
        String param = "reg=" + regCode + "&pwd=" + regPasswod;

        String returnStr = null;
        if (type == 1) {
            returnStr = requestGet(url, param);
        } else if (type == 2) {
            returnStr = requestPost(url, param);
        }
        System.out.println(returnStr);
    }


    /**
     * 发送短信的方法
     *
     * @param type 请求方式,1为get,2为post
     */
    public static void sendSms(int type) {
        String url = "https://www.stongnet.com/sdkhttp/sendsms.aspx";


        String regCode = "101100-WEB-HUAX-135086"; // 华兴软通注册码，请在这里填写您从客服那得到的注册码
        String regPasswod = "YTHYNYZA"; // 华兴软通注册码对应的密码，请在这里填写您从客服那得到的注册码
        String sourceAdd = null;        //子通道号（最长10位，可为空
        String phone = "18570629027";        //手机号码（最多1000个），多个用英文逗号(,)隔开，不可为空
        String content = "本次投票验证码为168691，请及时输入。【众创平安】";    //短信内容
        String param = null;
        try {
            param = "reg=" + regCode + "&pwd=" + regPasswod + "&sourceadd=" + sourceAdd + "&phone=" + phone + "&content=" + HxrtHttpKit.paraTo16(content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String returnStr = null;
        if (type == 1) {
            returnStr = requestGet(url, param);
        } else if (type == 2) {
            returnStr = requestPost(url, param);
        }
        System.out.println(returnStr);
        System.out.println(new java.util.Date());
    }

    public static void main(String args[]) {
        HxrtApi.sendSms(1);
    }
}
