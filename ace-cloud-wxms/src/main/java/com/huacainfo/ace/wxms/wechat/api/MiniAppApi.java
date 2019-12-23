package com.huacainfo.ace.wxms.wechat.api;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.wxms.wechat.entity.pojo.MiniUserInfo;
import com.huacainfo.ace.wxms.wechat.entity.pojo.resp.Code2SessionResp;
import com.huacainfo.ace.wxms.wechat.util.HttpKit;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;

/**
 * @ClassName MiniAppApi
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/10/8 15:17
 */
public class MiniAppApi {
    /**
     * auth.code2Session
     */
    public static final String API_URI_AUTH_CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
    private static final Logger logger = LoggerFactory.getLogger(MiniAppApi.class);

    /**
     * 调用 auth.code2Session 接口，换取 用户唯一标识 OpenID 和 会话密钥 session_key。
     *
     * @param appid     小程序 appId
     * @param appSecret 小程序 appSecret
     * @param jscode    登录时获取的 code；调用 wx.login() 获取 临时登录凭证code
     * @return Code2SessionResp
     */
    public static Code2SessionResp authCode2Session(String appid, String appSecret, String jscode) {
        String apiUri = MiniAppApi.API_URI_AUTH_CODE2SESSION
                .replace("APPID", appid)
                .replace("SECRET", appSecret)
                .replace("JSCODE", jscode);

        return JsonUtil.toObject(HttpKit.get(apiUri), Code2SessionResp.class);
    }

    public static MiniUserInfo decryptUserInfo(String sessionKey, String encryptedData, String iv) throws Exception {
        logger.info("encryptedData: {} sessionKey: {} iv:{}", sessionKey, encryptedData, iv);

        // 加密秘钥
        byte[] keyByte = Base64.decodeBase64(sessionKey);
        // 被加密的数据
        byte[] dataByte = Base64.decodeBase64(encryptedData);
        // 偏移量
        byte[] ivByte = Base64.decodeBase64(iv);
        // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
        int base = 16;
        if (keyByte.length % base != 0) {
            int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
            keyByte = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
        SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
        AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
        parameters.init(new IvParameterSpec(ivByte));
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
        byte[] rstByte = cipher.doFinal(dataByte);
        if (null != rstByte && rstByte.length > 0) {
            String rst = new String(rstByte, "UTF-8");
            return JsonUtil.toObject(rst, MiniUserInfo.class);
        }

        return null;
    }
}
