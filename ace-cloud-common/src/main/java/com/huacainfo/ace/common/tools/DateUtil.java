/*******************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 ******************************************************************************/

package com.huacainfo.ace.common.tools;


import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * DateKit.
 */
public final class DateUtil {

    /**
     * 默认的日期格式 .
     */
    public static final String DEFAULT_REGEX_YYYYMMDD = "yyyyMMdd";
    public static final int DIFF_UNIT_DAY = 1;
    public static final int DIFF_UNIT_HOUR = 2;
    public static final int DIFF_UNIT_MIN = 3;
    public static final int DIFF_UNIT_SS = 4;
    public static final Pattern TIME_PATTERN = Pattern.compile("\\b\\d{2}[.-]\\d{1,2}([.-]\\d{1,2}){0,1}\\b");
    /**
     * 默认的DateFormat 实例
     */
    private static final EPNDateFormat DEFAULT_FORMAT_YYYYMMDD = new EPNDateFormat(DEFAULT_REGEX_YYYYMMDD);
    /**
     * 默认的日期格式 .
     */
    public static String DEFAULT_DATE_REGEX = "yyyy-MM-dd";
    /**
     * 默认的DateFormat 实例
     */
    private static final EPNDateFormat DEFAULT_FORMAT = new EPNDateFormat(DEFAULT_DATE_REGEX);
    /**
     * 默认的日期格式 .
     */
    public static String DEFAULT_DATE_TIME_REGEX = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认的DateFormat 实例
     */
    private static final EPNDateFormat DEFAULT_FORMAT_YYYY_MM_DD_HH_MIN_SS = new EPNDateFormat(DEFAULT_DATE_TIME_REGEX);
    protected static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static Map<String, EPNDateFormat> formatMap = new HashMap<>();

    static {
        formatMap.put(DEFAULT_DATE_REGEX, DEFAULT_FORMAT);
        formatMap.put(DEFAULT_DATE_TIME_REGEX, DEFAULT_FORMAT_YYYY_MM_DD_HH_MIN_SS);
        formatMap.put(DEFAULT_REGEX_YYYYMMDD, DEFAULT_FORMAT_YYYYMMDD);
    }

    private DateUtil() {

    }

    public static void setDateFromat(String dateFormat) {
        if (dateFormat.isEmpty())
            throw new IllegalArgumentException("dateFormat can not be blank.");
        DEFAULT_DATE_REGEX = dateFormat;
    }

    public static void setTimeFromat(String timeFormat) {
        if (timeFormat.isEmpty())
            throw new IllegalArgumentException("timeFormat can not be blank.");
        DEFAULT_DATE_TIME_REGEX = timeFormat;
    }

    public static Date toDate(String dateStr, String regex) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(regex);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("run error", e);
        }
        return date;
    }

    public static Date toDate(String dateStr) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME_REGEX);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("run error", e);
        }
        return date;
    }

    public static String toStr(Date date) {
        return toStr(date, DEFAULT_DATE_REGEX);
    }

    public static String toStr(Date date, String format) {
        return getDateFormat(format).format(date);
    }

    /**
     * 获取2个时间的时间差
     *
     * @param begin    开始时间
     * @param end      结尾时间
     * @param diffUnit 1-day 2-hour 3-min 4-ss
     * @return
     */
    public static long getTimeDiff(String begin, String end, int diffUnit) {
        Date d1 = toDate(begin);
        Date d2 = toDate(end);
        long between = (d1.getTime() - d2.getTime()) / 1000;//除以1000是为了转换成秒

        switch (diffUnit) {
            case DateUtil.DIFF_UNIT_DAY:
                return between / (24 * 60 * 60);
            case DateUtil.DIFF_UNIT_HOUR:
                return between / (60 * 60);
            case DateUtil.DIFF_UNIT_MIN:
                return between / 60;
            case DateUtil.DIFF_UNIT_SS:
                return between;
        }

        return Long.MIN_VALUE;
    }

    /**
     * 时间对象格式化成String ,等同于java.text.DateFormat.format();
     *
     * @param date 需要格式化的时间对象
     *             <p>
     *             2014年5月5日 下午12:00:00 flyfox 330627517@qq.com
     * @return 转化结果
     */
    public static String format(Date date) {
        return DEFAULT_FORMAT.format(date);
    }

    public static Date format(String date, String regex) {
        SimpleDateFormat sdf = new SimpleDateFormat(regex);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            logger.error("run error", e);
        }
        return getDate();
    }

    /**
     * 时间对象格式化成String ,等同于java.text.SimpleDateFormat.format();
     *
     * @param date  需要格式化的时间对象
     * @param regex 定义格式的字符串
     *              <p>
     *              2014年5月5日 下午12:00:00 flyfox 330627517@qq.com
     * @return 转化结果
     */
    public static String format(Date date, String regex) {
        return getDateFormat(regex).format(date);
    }

    private static EPNDateFormat getDateFormat(String regex) {
        EPNDateFormat fmt = formatMap.get(regex);
        if (fmt == null) {
            fmt = new EPNDateFormat(regex);
            formatMap.put(regex, fmt);
        }
        return fmt;
    }

    /**
     * 主要是给milano使用，数据库只认java.sql.*
     *
     * @param date
     * @return
     */
    public static Timestamp getSqlTimestamp(Date date) {
        if (null == date) {
            date = new Date();
        }
        return getSqlTimestamp(date.getTime());
    }

    /**
     * 主要是给milano使用，数据库只认java.sql.*
     *
     * @param time
     * @return
     */
    public static Timestamp getSqlTimestamp(long time) {
        return new Timestamp(time);
    }

    /**
     * 尝试解析时间字符串 ,if failed return null;
     *
     * @param time 2014年5月5日 下午12:00:00
     * @return
     */
    public static Date parseByAll(String time) {
        Date stamp = null;
        if (time == null || "".equals(time)) {
            return null;
        }
        if (TIME_PATTERN.matcher(time).matches()) {
            time = (time.charAt(0) == '1' || time.charAt(0) == '0' ? "20" : "19") + time;
        }

        stamp = parse(time, "yyyy-MM-ddHH:mm:ss");
        if (stamp == null) {
            stamp = parse(time, "yyyy-MM-dd");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy.MM.dd");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy-MM");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy.MM");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy-MM-dd");
        }
        if (stamp == null) {
            stamp = parse(time, "yy-MM-dd");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy.MM.dd");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy-MM.dd");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy.MM-dd");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyyMMdd");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy年MM月dd日");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyyMM");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy年MM月");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy");
        }
        if (stamp == null) {
            stamp = parse(time, "yyyy年");
        }
        return stamp;
    }

    /**
     * 解析字符串成时间 ,遇到错误返回null不抛异常
     *
     * @param source 2014年5月5日 下午12:00:00 flyfox 330627517@qq.com
     * @return 解析结果
     */
    public static Date parse(String source) {
        try {
            return DEFAULT_FORMAT.parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析字符串成时间 ,遇到错误返回null不抛异常
     *
     * @param source
     * @param regex  2014年5月5日 下午12:00:00 flyfox 330627517@qq.com
     * @return 解析结果
     */
    public static Date parse(String source, String regex) {
        try {
            EPNDateFormat fmt = getDateFormat(regex);
            return fmt.parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取得当前时间的Date对象 ;
     * <p>
     * 2014年5月5日 下午12:00:00 flyfox 330627517@qq.com
     *
     * @return
     */
    public static Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前时间字符串
     *
     * @return
     */
    public static String getNow() {
        return getNow(DEFAULT_DATE_TIME_REGEX);
    }

    /**
     * 获取当前日期字符串
     *
     * @return
     */
    public static String today() {
        return toStr(new Date());
    }

    /**
     * 获取当前时间的时间戳，单位 毫秒
     *
     * @return
     */
    public static long getDateByTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间的时间戳，单位 秒
     *
     * @return
     */
    public static long getDateTime() {
        long time = System.currentTimeMillis();
        String timeStr = String.valueOf(time);
        timeStr = timeStr.substring(0, timeStr.length() - 3);
        return Long.parseLong(timeStr);
    }

    /**
     * 获取当前时间字符串
     * <p>
     * 2014年7月4日 下午11:47:10 flyfox 330627517@qq.com
     *
     * @param regex 格式表达式
     * @return
     */
    public static String getNow(String regex) {
        return format(getNowDate(), regex);
    }

    /**
     * 两个日期的时间差，返回"X天X小时X分X秒"
     *
     * @param begin
     * @param end
     * @return
     */
    public static String getBetween(Date begin, Date end) {
        long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
        long day = between / (24 * 3600);
        long hour = between % (24 * 3600) / 3600;
        long minute = between % 3600 / 60;
        long second = between % 60 / 60;

        StringBuilder sb = new StringBuilder();
        sb.append(day);
        sb.append("天");
        sb.append(hour);
        sb.append("小时");
        sb.append(minute);
        sb.append("分");
        sb.append(second);
        sb.append("秒");

        return sb.toString();
    }

    /**
     * 返回两个日期之间隔了多少小时
     *
     * @param end
     * @return
     */
    public static int getDateHourSpace(Date start, Date end) {
        int hour = (int) ((start.getTime() - end.getTime()) / 3600 / 1000);
        return hour;
    }

    /**
     * 返回两个日期之间隔了多少天
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDateDaySpace(Date start, Date end) {
        int day = getDateHourSpace(start, end) / 24;
        return day;
    }

    /**
     * 得到某一天是星期几
     *
     * @param date 日期字符串
     * @return String 星期几
     */
    @SuppressWarnings("static-access")
    public static String getDateInWeek(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        if (dayIndex < 0) {
            dayIndex = 0;
        }
        return weekDays[dayIndex];
    }

    /**
     * 日期减去多少个小时
     *
     * @param date
     * @param hourCount 多少个小时
     * @return
     */
    public static Date getDateReduceHour(Date date, long hourCount) {
        long time = date.getTime() - 3600 * 1000 * hourCount;
        Date dateTemp = new Date();
        dateTemp.setTime(time);
        return dateTemp;
    }

    /**
     * 日期区间分割
     *
     * @param start
     * @param end
     * @param splitCount
     * @return
     */
    public static List<Date> getDateSplit(Date start, Date end, long splitCount) {
        long startTime = start.getTime();
        long endTime = end.getTime();
        long between = endTime - startTime;

        long count = splitCount - 1l;
        long section = between / count;

        List<Date> list = new ArrayList<Date>();
        list.add(start);

        for (long i = 1l; i < count; i++) {
            long time = startTime + section * i;
            Date date = new Date();
            date.setTime(time);
            list.add(date);
        }

        list.add(end);

        return list;
    }

    /**
     * 返回两个日期之间隔了多少天，包含开始、结束时间
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getDaySpaceDate(Date start, Date end) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(start);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(end);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        List<String> dateList = new LinkedList<String>();

        long dayCount = (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
        if (dayCount < 0) {
            return dateList;
        }

        dateList.add(format(fromCalendar.getTime(), DEFAULT_DATE_REGEX));

        for (int i = 0; i < dayCount; i++) {
            fromCalendar.add(Calendar.DATE, 1);// 增加一天
            dateList.add(format(fromCalendar.getTime(), DEFAULT_DATE_REGEX));
        }

        return dateList;
    }

    /**
     * 获取开始时间
     *
     * @param start
     * @param end
     * @return
     */
    public static Date getDateByDay(Date start, int end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.DATE, end);// 明天1，昨天-1
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取结束时间
     *
     * @param start
     * @return
     */
    public static Date endDateByDay(Date start) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取开始时间
     *
     * @param start
     * @param end
     * @return
     */
    public static Date startDateByHour(Date start, int end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.MINUTE, end);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 获取结束时间
     *
     * @param end
     * @return
     */
    public static Date endDateByHour(Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 根据年份和周得到周的开始和结束日期
     *
     * @param year
     * @param week
     * @return
     */
    public static Map<String, Date> getStartEndDateByWeek(int year, int week) {
        Calendar weekCalendar = new GregorianCalendar();
        weekCalendar.set(Calendar.YEAR, year);
        weekCalendar.set(Calendar.WEEK_OF_YEAR, week);
        weekCalendar.set(Calendar.DAY_OF_WEEK, weekCalendar.getFirstDayOfWeek());

        Date startDate = weekCalendar.getTime(); // 得到周的开始日期

        weekCalendar.roll(Calendar.DAY_OF_WEEK, 6);
        Date endDate = weekCalendar.getTime(); // 得到周的结束日期

        // 开始日期往前推一天
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        startCalendar.add(Calendar.DATE, 1);// 明天1，昨天-1
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        startDate = startCalendar.getTime();

        // 结束日期往前推一天
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.add(Calendar.DATE, 1);// 明天1，昨天-1
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);
        endDate = endCalendar.getTime();

        Map<String, Date> map = new HashMap<>();
        map.put("start", startDate);
        map.put("end", endDate);
        return map;
    }

    /**
     * 根据日期月份，获取月份的开始和结束日期
     *
     * @param date
     * @return
     */
    public static Map<String, Date> getMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // 得到前一个月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date start = calendar.getTime();

        // 得到前一个月的最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date end = calendar.getTime();

        Map<String, Date> map = new HashMap<>();
        map.put("start", start);
        map.put("end", end);
        return map;
    }

    /**
     * 获取 sec 秒之后的系统时间
     *
     * @param sec
     * @return
     */
    public static Date getNowAfterSecTime(int sec) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, +sec);
        Date time = cal.getTime();


        return time;
//        SimpleDateFormat formatter;
//        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String ctime = formatter.format(time);
//        return ctime;
    }

    /**
     * 判断 dateTime  是否在开始/结束时间之间
     *
     * @param dateTime  目标时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param regex     时间格式
     * @return true-两者之间，false-不是
     */
    public static boolean isBetween(String dateTime, String startTime, String endTime, String regex) {
        DateFormat df = null;
        if (StringKit.isEmpty(regex)) {
            df = new SimpleDateFormat(DEFAULT_DATE_TIME_REGEX);
        } else {
            df = new SimpleDateFormat(regex);
        }
        try {
            Date dateDt = df.parse(dateTime);
            Date startDt = df.parse(startTime);
            Date endDt = df.parse(endTime);
            if (dateDt.after(startDt) && dateDt.before(endDt)) {
                return true;
            }

            return false;
        } catch (ParseException e) {
            logger.error("DateUtil.isTween.error:{}", e);
            return false;
        }
    }

    /**
     * 两个时间比较  string, string  "yyyy-MM-dd HH:mm:ss"
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 0-表示2个时间相等；小于0-表示d1在d2之前；大于0表示d1在d2之后
     */
    public static int dateCompare(String date1, String date2) {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME_REGEX);

        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            return d1.compareTo(d2);
        } catch (Exception e) {
            logger.error("DateUtil.dateCompare.error:{}", e);
            return Integer.MAX_VALUE;
        }
    }


    /**
     * 计算2个时间差   --  (date1 - date2)
     *
     * @param date1 时间1
     * @param date2 时间2
     * @param type  差值类型 Days-天，Hours-小时，Minutes-分钟，Seconds-秒
     * @return long值
     */
    public static long getBetween(Date date1, Date date2, String type) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff;
        diff = time1 - time2;
        //天
        if ("Days".equals(type)) {
            return diff / (1000 * 60 * 60 * 24);
        }
        //小时
        if ("Hours".equals(type)) {
            return diff / (1000 * 60 * 60);
        }
        //分
        if ("Minutes".equals(type)) {
            return diff / (1000 * 60);
        }
        //秒
        if ("Seconds".equals(type)) {
            return diff / 1000;
        }

        return -1;
    }

    /**
     * 获取指定时间
     *
     * @param date      标准日期
     * @param type      差值类型
     * @param diffValue 差值  负数：标准时间之前；正数：标准时间之后
     * @return 指定时间
     */
    public static String getTime(String date, int type, int diffValue) throws ParseException {
        //含时分秒
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME_REGEX);
        Date date1 = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);

        switch (type) {
            case Calendar.HOUR:
                calendar.add(Calendar.HOUR, diffValue);
                return sdf.format(calendar.getTime());
            case Calendar.MINUTE:
                calendar.add(Calendar.MINUTE, diffValue);
                return sdf.format(calendar.getTime());
            case Calendar.SECOND:
                calendar.add(Calendar.SECOND, diffValue);
                return sdf.format(calendar.getTime());
        }

        return "";
    }

    /**
     * 获取年，月，日
     *
     * @param date 时间字符串
     * @return Map  year,month,day
     */
    public static Map<String, String> getDateMap(String date) {
        Map<String, String> map = new HashMap<>();
        map.put("year", date.substring(0, 4));
        map.put("month", date.substring(5, 7));
        map.put("day", date.substring(8, 10));
        return map;
    }


    /**
     * 获取过去/将来时间
     *
     * @param type  year/month/week
     * @param value 差值，负数拿过去时间，正数拿将来时间
     * @return String
     */
    public static String getDateTime(String type, String date, int value, String format) {
        format = StringKit.isEmpty(format) ? DateUtil.DEFAULT_DATE_TIME_REGEX : format;

        Calendar curr = Calendar.getInstance();
        curr.setTime(getDate(date, format));
        if ("week".equals(type)) {
            curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + 7 * value);
            return format(curr.getTime(), DateUtil.DEFAULT_DATE_TIME_REGEX);
        } else if ("month".equals(type)) {
            curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + value);
            return format(curr.getTime(), DateUtil.DEFAULT_DATE_TIME_REGEX);
        } else if ("year".equals(type)) {
            curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + value);
            return format(curr.getTime(), DateUtil.DEFAULT_DATE_TIME_REGEX);
        }

        return "未知类型!";
    }

    /**
     * 获取日期时间
     *
     * @param time   时间
     * @param format 格式化标准
     * @return Date
     */
    public static Date getDate(String time, String format) {
        return format(time, format);
    }

    /**
     * 根据指定日期月份，获取月份的开始和结束日期
     *
     * @param month 月份 Demo:2017-03
     * @return Map<String, String>
     */
    public static Map<String, String> getMonthDate(String month) {
        //2017-03-15 12:00:00
        month = month + "-15 12:00:00";
        Date d = DateUtil.getDate(month, DateUtil.DEFAULT_DATE_TIME_REGEX);
        Map<String, Date> map = DateUtil.getMonthDate(d);

        Map<String, String> rtnMap = new HashedMap();
        rtnMap.put("start", DateUtil.format(map.get("start"), DateUtil.DEFAULT_DATE_TIME_REGEX));
        rtnMap.put("end", DateUtil.format(map.get("end"), DateUtil.DEFAULT_DATE_TIME_REGEX));

        return rtnMap;
    }

    /**
     * 获取时间戳
     *
     * @param dateTime 2018-01-02 14:23:23  to 20180102142323
     * @return
     */
    public static String getTimestamp(String dateTime) {
        return dateTime.replace("-", "")
                .replace(":", "")
                .replace(" ", "");
    }

    /**
     * 根据当前日期获得所在周的日期区间（周一和周日日期）
     */
    public static String getTimeWeekInterval(Date date, SimpleDateFormat sdf) {
        if (null == sdf) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());

        return imptimeBegin + "," + imptimeEnd;
    }

    /**
     * @param time1 时间1 格式要求："HH:mm:ss"
     * @param time2 时间2 格式要求："HH:mm:ss"
     * @return int
     * 1  -- time1 > time2
     * 0  -- time1 = time2
     * -1 -- time1 < time2
     * -2 -- 格式错误转换异常
     */
    public static int compareTime(String time1, String time2) {
        //创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        try {
            //将字符串转换为date类型
            Date dt1 = df.parse(time1);
            Date dt2 = df.parse(time2);

            return dt1.compareTo(dt2);
        } catch (ParseException e) {
            return -2;
        }
    }
}

class EPNDateFormat {
    private SimpleDateFormat instance;

    EPNDateFormat() {
        instance = new SimpleDateFormat(DateUtil.DEFAULT_DATE_REGEX);
        instance.setLenient(false);
    }

    EPNDateFormat(String regex) {
        instance = new SimpleDateFormat(regex);
        instance.setLenient(false);
    }

    synchronized String format(Date date) {
        if (date == null) {
            return "";
        }
        return instance.format(date);
    }

    synchronized Date parse(String source) throws ParseException {
        return instance.parse(source);
    }
}
