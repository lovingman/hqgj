package com.huacainfo.ace.wxms.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * PackageName:com.cdkj.common.util
 * Descript:随机码生成器 <br/>
 * date: 2016/3/31 <br/>
 * User: jyune
 * version 1.0
 */
public class RandomValidateCode {
    public static final String RANDOMCODEKEY = "CDKJ_RANDOM_CODE_KEY";// 放到session中的key
    protected static Logger logger = LoggerFactory.getLogger(RandomValidateCode.class);
    private Random random = new Random();
    // 随机产生数字与字母组合的字符串
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    /*
     * private String randString = "0123456789";//随机产生只有数字的字符串 private String
     * randString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生只有字母的字符串
     */
    private int width = 80;// 图片宽
    private int height = 26;// 图片高
    private int lineSize = 40;// 干扰线数量
    private int stringNum = 4;// 随机产生字符数量

    /**
     * 方法说明：
     * 该函数获得随机数字符串
     *
     * @param iLen  int :需要获得随机数的长度
     * @param iType int:随机数的类型：'0':表示仅获得数字随机数；'1'：表示仅获得字符随机数；'2'：表示获得数字字符混合随机数
     * @since 1.0.0
     */
    public static final String CreateRadom(int iLen, int iType) {
        String strRandom = "";//随机字符串
        Random rnd = new Random();
        if (iLen < 0) {
            iLen = 5;
        }
        if ((iType > 2) || (iType < 0)) {
            iType = 2;
        }
        switch (iType) {
            case 0:
                for (int iLoop = 0; iLoop < iLen; iLoop++) {
                    strRandom += Integer.toString(rnd.nextInt(10));
                }
                break;
            case 1:
                for (int iLoop = 0; iLoop < iLen; iLoop++) {
                    strRandom += Integer.toString((35 - rnd.nextInt(10)), 36);
                }
                break;
            case 2:
                for (int iLoop = 0; iLoop < iLen; iLoop++) {
                    strRandom += Integer.toString(rnd.nextInt(36), 36);
                }
                break;
        }
        return strRandom;
    }

    /*
     * 获得字体
     */
    public Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /*
     * 获得颜色
     */
    public Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public void getRandcode(HttpServletRequest request,
                            HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
        //将生成的随机字符串保存到session中，而jsp界面通过session.getAttribute("RANDOMCODEKEY")，
        //获得生成的验证码，然后跟用户输入的进行比较
        //将随机字符串存入Redis中,界面进行验证时,取出来进行处理
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY, randomString);
        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            logger.error("run error", e);
        }
    }

    /*
     * 绘制字符串
     */
    public String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /*
     * 绘制干扰线
     */
    public void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /*
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }
}
