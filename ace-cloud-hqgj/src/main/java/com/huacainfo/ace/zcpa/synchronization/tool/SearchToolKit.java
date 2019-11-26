package com.huacainfo.ace.zcpa.synchronization.tool;


import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.zcpa.constant.synchro.KitUrl;
import com.huacainfo.ace.zcpa.plugins.wechat.kit.HttpKit;
import com.huacainfo.ace.zcpa.synchronization.model.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SearchToolKit {
    /**
     * 学校
     * @return
     */

    public static List<School> getSchoolData(String start) {

        StringBuilder path = new StringBuilder();
        path.append(KitUrl.SEARCHSCHOOL_URL);
        path.append("?school.startTime=").append(startTime(start));
      //  path.append("&school.endTime=").append(endTime(end));

        String rstJson = HttpKit.get(path.toString());
        if(rstJson.contains("没有查询到相关学校信息")){
            return null;
        }
        return JsonUtil.toList(rstJson, School.class);
    }

    /**
     * 特殊人群
     * @return
     */
    public static List<Person> getPersonData(String start) {
        StringBuilder path = new StringBuilder();
        path.append(KitUrl.SPECIALPERSON_URL);
        path.append("?specialPersonVO.startTime=").append(startTime(start));
      //  path.append("&specialPersonVO.endTime=").append(endTime(end));

        String rstJson = HttpKit.get(path.toString());
        if(rstJson.equals("[]\n")){
            return null;
        }
        return JsonUtil.toList(rstJson, Person.class);
    }

    /**
     * 社会组织
     * @return
     */
    public  static List<SocietyOrganization> getSocietyOrganizationData(String start) {
        StringBuilder path = new StringBuilder();
        path.append(KitUrl.NEWSOCIETYORGANIZATION_URL);
        path.append("?newSocietyOrganizations.startTime=").append(startTime(start));
     //   path.append("&newSocietyOrganizations.endTime=").append(endTime(end));

        String rstJson = HttpKit.get(path.toString());
        if(CommonUtils.isBlank(rstJson)){
            return null;
        }
        return JsonUtil.toList(rstJson, SocietyOrganization.class);
    }

    /**
     * 用户信息
     * @return
     */
    public static List<UserInfo> getUserData(String start){
        StringBuilder path = new StringBuilder();
        path.append(KitUrl.SEARCHUSER_URl);
        path.append("?user.startTime=").append(startTime(start));
     //   path.append("&user.endTime=").append(endTime(end));

        String rstJson = HttpKit.get(path.toString());
        if(CommonUtils.isBlank(rstJson)){
            return null;
        }
        return JsonUtil.toList(rstJson, UserInfo.class);
    }

    /**
     * 平安单位
     */
    public static List<Unit> getUnitData(String start){
        StringBuilder path = new StringBuilder();
        path.append(KitUrl.SAFETYUNIT_URL);
        path.append("?safetyUnit.startTime=").append(startTime(start));
      //  path.append("&safetyUnit.endTime=").append(endTime(end));

        String rstJson = HttpKit.get(path.toString());
        if(CommonUtils.isBlank(rstJson)){
            return null;
        }
        return JsonUtil.toList(rstJson, Unit.class);
    }

    //开始时间
    private static String startTime(String start){
        String yesterday="";
        if(!CommonUtils.isBlank(start)){
            yesterday=start;
        }else {
            //获取昨天
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            Date d = cal.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            yesterday = sdf.format(d);
        }
        return yesterday;
    }

   /* private static String endTime(String end){
        String now="";
        if(!CommonUtils.isBlank(end)){
         now=end;
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            now = sdf.format(new Date());
        }
        return now;
    }*/
}
