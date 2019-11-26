package com.huacainfo.ace.common.tools;

import com.huacainfo.ace.common.vo.UserProp;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;

public class WebUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @SuppressWarnings("static-access")
    public static int getDayOfWeek() {
        TimeZone zone = TimeZone.getTimeZone("Asia/Beijing");
        Calendar cal = Calendar.getInstance(zone);
        int c = cal.get(Calendar.WEEK_OF_YEAR);
        System.out.println(c);
        return c;
    }




    public static void downloadByApacheCommonIO(String url, String saveDir, String fileName) {
        try {
            FileUtils.copyURLToFile(new java.net.URL(url), new File(saveDir, fileName));
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }
    }


}
