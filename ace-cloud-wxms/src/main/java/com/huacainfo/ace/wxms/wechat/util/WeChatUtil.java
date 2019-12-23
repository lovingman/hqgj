package com.huacainfo.ace.wxms.wechat.util;

import com.huacainfo.ace.wxms.wechat.entity.ConfigWechat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 微信工具类
 *
 * @author ArvinZou
 */
public class WeChatUtil {
    private static Logger logger = LoggerFactory.getLogger(WeChatUtil.class);
    /**
     * <原始ID,公众号配置信息>
     */
    private static Map<String, ConfigWechat> configs;

    static {
        configs = new HashMap<>();
        //华彩伟业公众号
        ConfigWechat hcwy = new ConfigWechat();
        hcwy.setWxOriginalId("gh_87c582f16661");
        hcwy.setWxToken("hcwy20180514_fwh");//令牌(Token)
        hcwy.setWxAppId("wx29ecb720b03ea466");
        hcwy.setSysId("hcwy");
        hcwy.setWxSecretKey("680XkW466zc5v56agjHBgGmANVKRb2zrizeWscX60p2");

        ConfigWechat fop = new ConfigWechat();
        fop.setWxOriginalId("gh_959a710a5fc2");
        fop.setWxAppId("wxfdb1e4819dee7b62");
        fop.setWxToken("fop20180608_fwh");//令牌(Token)
        fop.setSysId("fop");
        fop.setWxSecretKey("dc51907c900e5bcb9527daec79d05e61");
        //
        configs.put(hcwy.getWxOriginalId(), hcwy);
        configs.put(fop.getWxOriginalId(), fop);
    }

    public static ConfigWechat getConfigByOrgId(String orgId) {
        return configs.get("orgId");
    }

    /**
     * 微信签名校验，设置服务器时用到
     * 遍历所有的微信号的token，如果有一个验证通过，则返回true
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        Set<String> set = new HashSet<>();
        for (Map.Entry<String, ConfigWechat> entry : configs.entrySet()) {
            set.add(entry.getValue().getWxToken());
        }
        for (String token : set) {
            String temp = getSign(timestamp, nonce, token);
            if (temp.equals(signature)) {
                return true;
            }
        }
        //重新加载一次中间件的微信信息，用于解决中间件微信信息添加之后，本地没同步问题。
//        AccessUtil.initConfigWechatMap();
        return false;
    }

    /**
     * 本地签名方法，用于系统内部签名验证
     *
     * @param arr 参数
     * @return 签名结果
     */
    public static String getSign(String... arr) {
        //排序
        Arrays.sort(arr);
        //生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        //sha1加密
        return Sha1Util.getSha1(content.toString());
    }
}
