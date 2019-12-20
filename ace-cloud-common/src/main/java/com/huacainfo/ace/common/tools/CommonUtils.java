package com.huacainfo.ace.common.tools;

import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.vo.UserProp;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils extends StringUtils {
    public static final int IDENTIFY_CODE_TYPE_NUMBER = 0;

    public static final String CREATE = "create";

    public static final String MODIFY = "modify";


    /**
     * 获取随机验证码
     *
     * @param length 验证码长度
     * @param type   验证码类型 默认为0
     * @return
     */
    public static String getIdentifyCode(int length, int type) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(String.valueOf(random.nextInt(10)));
        }
        return code.toString();
    }

    public static String parseDate2String(Date date, String formatStr)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(
                formatStr);
        return formatter.format(date);
    }

    public static Date parseString2Date(String dateStr, String formatStr)
            throws ParseException {
        Date date = null;
        if (isNotEmpty(dateStr)) {
            SimpleDateFormat formatter = new SimpleDateFormat(
                    formatStr);
            date = formatter.parse(dateStr);
        }
        return date;
    }

    public static void writeStringToFile(String filePath, String content) {
        File file = new File(filePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            Writer out = new OutputStreamWriter(fos, "UTF-8");
            out.write(content);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Object getPropValue(Object obj, String prop)
            throws IllegalArgumentException, IllegalAccessException {
        Object val = null;
        if (obj != null) {
            Field[] fs = obj.getClass().getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                if (f.getName().equals(prop)) {
                    f.setAccessible(true); // 设置些属性是可以访问的
                    val = f.get(obj); // 得到此属性的值
                    break;
                }
            }
        }
        return val;

    }

    /**
     * 获取异常堆栈信息
     *
     * @param ex
     * @return
     */
    public static String getExceptionStack(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        StringBuffer error = stringWriter.getBuffer();
        return error.toString();
    }

    /**
     * 非空判断
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        return str != null && !str.toString().trim().equals("");
    }

    public static boolean isValidEmail(String paramStr) {
        String email = paramStr;
        String check = "\\w+@(\\w+.)+[a-z]{2,3}";
        check = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-z]+(\\.[a-z]+)?";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidParam(String paramStr) {
        String email = paramStr;
        String check = "^([a-z0-9A-Z]+[-|\\.]?)";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidTel(String paramStr) {
        String str = paramStr;
        String check = "([0-9]{3,4})-([0-9]{7,8})";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidFigures(String paramStr) {
        String str = paramStr;
        String check = "[0-9]{1,50}";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidQQ(String paramStr) {
        String str = paramStr;
        String check = "[0-9]{4,11}";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidMobile(String paramStr) {
        String str = paramStr;
        String check = "^((13[0-9])|(14[5,7,9])|(16[0-9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);
        boolean isMatched = matcher.matches();
        return (isMatched);
    }

    public static boolean isValidMinLength(String paramStr, int paramMin) {
        String str = paramStr;
        int min = paramMin;
        boolean isValidMinLength;
        isValidMinLength = false;
        if (str != null) {
            if (str.length() >= min) {
                isValidMinLength = true;
            }
        }
        return (isValidMinLength);
    }

    public static boolean isValidMaxLength(String paramStr, int paramMax) {
        String str = paramStr;
        int max = paramMax;
        boolean isValidMaxLength;
        isValidMaxLength = false;
        if (str != null) {
            if (str.length() <= max) {
                isValidMaxLength = true;
            }
        }
        return (isValidMaxLength);
    }

    public static boolean isValidUrl(String paramStr, String[] endStr) {
        paramStr = paramStr.toLowerCase();
        if (paramStr.indexOf("http://") != 0) {
            return false;
        }
        for (int i = 0; i < endStr.length; i++) {

            if (paramStr.endsWith(endStr[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidDate(String dateStr) {
        try {
            if (dateStr.length() > 10) {
                dateStr.substring(0, 10);
            }
            java.sql.Date.valueOf(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getMd5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 将首字母大字
     *
     * @param str
     * @return 转化后后字符
     */
    public static String firstToUpper(String str) {
        char[] chs = str.toCharArray();
        chs[0] = Character.toUpperCase(chs[0]);
        return new String(chs);
    }

    /**
     * 首字母小字
     *
     * @param str
     * @return 转化后后字符
     */
    public static String firstToLower(String str) {
        char[] chs = str.toCharArray();
        chs[0] = Character.toLowerCase(chs[0]);
        return new String(chs);
    }

    public static String getJavaName(String column) {
        if (column == null) {
            return "";
        }
        if (!column.contains("_")) {
            return column;
        }
        String[] names = column.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder(names[0]);
        for (int i = 1; i < names.length; i++) {
            sb.append(CommonUtils.firstToUpper(names[i]));
        }
        return sb.toString();
    }

    public static boolean isValidIdCard(String idCard) {
        try {
            if (idCard.length() == 15 || idCard.length() == 18) {
                String temp = null;
                if (idCard.length() == 15) {
                    temp = "19" + idCard.substring(6, 6 + 6);
                } else {
                    temp = idCard.substring(6, 6 + 8);
                }
                String dateStr = temp.substring(0, 4) + "-"
                        + temp.substring(4, 6) + "-" + temp.substring(6, 8);
                SimpleDateFormat sdf = null;
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.parse(dateStr);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对象属性转换为字段 例如：userName to user_name
     *
     * @param property 字段名
     * @return
     */
    public static String propertyToField(String property) {
        if (null == property) {
            return "";
        }
        char[] chars = property.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c : chars) {
            if (CharUtils.isAsciiAlphaUpper(c)) {
                sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 字段转换成对象属性 例如：user_name to userName
     *
     * @param field
     * @return
     */
    public static String fieldToProperty(String field) {
        if (null == field) {
            return "";
        }
        char[] chars = field.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                int j = i + 1;
                if (j < chars.length) {
                    sb.append(StringUtils.upperCase(CharUtils
                            .toString(chars[j])));
                    i++;
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String genRandomNum(int pwd_len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }

        return pwd.toString();
    }

    public static long getRandomNum(int maxNum) {
        Random r = new Random();
        return Long.valueOf(r.nextInt(maxNum));

    }

    public static String getStringByExpression(String expression,
                                               Map<String, Object> valueMap) {
        String regex = "\\$\\{[^\\}]+\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(expression);
        String paramKey;
        while (m.find()) {
            paramKey = m.group(0);
            paramKey = paramKey.substring(2, paramKey.length() - 1);
            Object paramValue = valueMap.get(paramKey);
            expression = m.replaceFirst(String.valueOf(paramValue));
            m = p.matcher(expression);
        }
        return expression;
    }

    private static int getIndex(String[] tmpByte, int length, int index) {

        int byteTotleMin = 0;

        int myIndex = -1;

        for (int i = 0; i < length; i++) {

            byteTotleMin = byteTotleMin + tmpByte[i].getBytes().length;

            if (byteTotleMin >= index) {

                myIndex = i;

                break;

            }

        }

        return myIndex;

    }

    private static byte[] getBytesByIndex(String[] tmpByte, int index) {

        if (index > tmpByte.length) {

            return null;

        }

        index++;

        byte[][] tmp = new byte[index][];

        int count = 0;

        for (int i = 0; i < index; i++) {

            tmp[i] = tmpByte[i].getBytes();

            count = count + tmp[i].length;

        }

        byte[] result = new byte[count];

        int one = tmp.length;

        int indexTmp = 0;

        for (int i = 0; i < one; i++) {

            int two = tmp[i].length;

            for (int j = 0; j < two; j++) {

                result[indexTmp] = tmp[i][j];

                indexTmp++;

            }

        }

        return result;

    }

    public static String spiltString(String resource, int index) {
        if (resource == null) {
            return null;
        }
        byte[] strByte = resource.getBytes();
        int byteLength = strByte.length;
        if (index <= 0 || index > byteLength) {
            return null;
        }
        int strLength = resource.length();
        String[] tmpByte = new String[strLength];
        for (int i = 0; i < strLength; i++) {
            tmpByte[i] = resource.substring(i, i + 1);
        }
        int myIndex = getIndex(tmpByte, strLength, index);
        byte[] resultByte = getBytesByIndex(tmpByte, myIndex);
        if (resultByte == null) {
            return null;
        }
        String result = new String(resultByte);
        return result;
    }

    public static String[] spiltChineseString(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) + "").getBytes().length > 1) {
                sb.append(str.charAt(i));
                sb.append(";");
            }
        }
        return sb.toString().split(";");
    }

    public static double compareChineseString(String orig, String dest) {
        double i = 0;
        if (null == orig || dest == null) {
            return 0;
        }
        for (String o : CommonUtils.spiltChineseString(orig)) {
            if (dest.indexOf(o) > -1) {
                i++;
            }
        }
        return i / CommonUtils.spiltChineseString(orig).length;
    }

    public static boolean isBlank(Object o) {
        boolean struts;
        struts = false;
        if (o == null) {
            struts = true;
        }

        if (o != null) {
            if (o instanceof String) {
                if (((String) o).trim().equals("")) {
                    struts = true;
                }
            } else {
                struts = false;
            }

        }
        return (struts);
    }

    /**
     * 获取某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    /**
     * 获取某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    public static Object[] toArrays(Collection<?> coll) {
        Object[] collValues = new Object[coll.size()];
        collValues = coll.toArray(collValues);
        return collValues;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String formatDate(Date date, String arg0) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat(arg0);
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static Date parseDate(String dateStr) {
        try {
            if (dateStr.length() > 10) {
                dateStr.substring(0, 10);
            }
            return (Date) java.sql.Date.valueOf(dateStr);
        } catch (Exception e) {
            return new Date();
        }
    }

    public static Date parseDate(String dateStr, String arg0) {
        try {
            if (dateStr.length() > 10) {
                SimpleDateFormat sdf = new SimpleDateFormat(arg0);
                return sdf.parse(dateStr);
            }
            return (Date) java.sql.Date.valueOf(dateStr);
        } catch (Exception e) {
            return new Date();
        }
    }


    public static Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Object> sortedMap = new TreeMap<String, Object>(
                new Comparator<String>() {
                    public int compare(String key1, String key2) {
                        return key1.compareTo(key2);
                    }
                });
        sortedMap.putAll(oriMap);
        return sortedMap;
    }

    private static int getInt(String str) {
        int i = 0;
        try {
            Pattern p = Pattern.compile("^\\d+");
            Matcher m = p.matcher(str);
            if (m.find()) {
                i = Integer.valueOf(m.group());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Entry<String, String>> entryList = new ArrayList<Entry<String, String>>(
                    oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Entry<String, String>>() {
                        public int compare(Entry<String, String> entry1,
                                           Entry<String, String> entry2) {
                            int value1 = 0, value2 = 0;
                            try {
                                value1 = getInt(entry1.getValue());
                                value2 = getInt(entry2.getValue());
                            } catch (NumberFormatException e) {
                                value1 = 0;
                                value2 = 0;
                            }
                            return value2 - value1;
                        }
                    });
            Iterator<Entry<String, String>> iter = entryList.iterator();
            Entry<String, String> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }

    public static Map<String, String> converMap(Map<String, Object> orgin) throws Exception {
        if (orgin == null) {
            return null;
        }
        Map<String, String> dest = new HashMap<String, String>();
        for (Entry<String, Object> entry : orgin.entrySet()) {
            if (entry.getValue() instanceof String) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Date) {
                dest.put(entry.getKey(), CommonUtils.parseDate2String((Date) entry.getValue(), "yyyy-mm-dd"));
            }
            if (entry.getValue() instanceof Double) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Long) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Integer) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
            if (entry.getValue() instanceof Float) {
                dest.put(entry.getKey(), (String) entry.getValue());
            }
        }
        return dest;
    }


    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        // 将字符串转换成字节序列
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i] & 0xff));
            // 将每个字符转换成ASCII码
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff) + " ");
        }
        return strBuf.toString();


    }

    public static boolean isNumber(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    public static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] getrandomarray(int log) {
        int[] result = new int[log];
        for (int i = 0; i < log; i++) {
            result[i] = i;
        }
        for (int i = 0; i < log; i++) {
            int random = (int) (log * Math.random());
            int temp = result[i];
            result[i] = result[random];
            result[random] = temp;
        }
        return result;
    }

    public static long getRandomNum(long Min, long Max) {
        long Range = Max - Min;
        double Rand = Math.random();
        return (Min + Math.round(Rand * Range));
    }

    public static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    public static String parseDate(Date date) {
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }

    /**
     * 获取文件后缀。
     */
    public static String getSuffix(String url) {
        int index = url.lastIndexOf(".");
        return url.substring(index + 1, url.length());
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     * @author lzf
     */
    public static Map<String, String> urlSplit(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     * @author lzf
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;
        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                for (int i = 1; i < arrSplit.length; i++) {
                    strAllParam = arrSplit[i];
                }
            }
        }
        return strAllParam;
    }

    @SuppressWarnings({"static-access", "unchecked"})
    public static <T> T setUserInfo(T bean, UserProp userProp, Class<T> clazz, String type) {
    	net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(bean);
        switch (type) {
            case CREATE:
                json.put("createUserId", userProp.getUserId());
                json.put("createUserName", userProp.getName());
                break;
            case MODIFY:
                json.put("modifyUserId", userProp.getUserId());
                json.put("modifyUserName", userProp.getName());
                break;
            default:
                break;
        }

        return (T) json.toBean(json, clazz);
    }

    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }
    
	/**
	 * 校验json值
	 * 
	 * @throws Exception
	 */
    public static void validateJsonObj(JSONObject json,String[] keys) throws Exception {
    	for(String key : keys) {
    		if(!json.containsKey(key)) {
    			throw new Exception(key + " 信息为空！");
        	}
    	}
    }

    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 图片url转base64
     * @param url
     * @return
     * @throws Exception
     */
    public static String encodeImageToBase64(URL url) throws Exception {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
          //设置请求方式为"GET"
            conn.setRequestMethod("GET");
          //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
         //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
          //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
         //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
         //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
          //使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
         //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
           //关闭输入流
            inStream.close();
            byte[] data = outStream.toByteArray();
           //对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(data);
            return base64;//返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("图片失败!");
        }
    }

}
