package com.huacainfo.ace.wxms.wechat.constant;

/**
 * @ClassName WxApiRtnCode
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/9/17 14:53
 */
public interface WxApiRtnCode {
    /**
     * OK			成功
     */
    int OK_0 = 0;
    /**
     * unauthorized create invoice	没有开票平台的权限，请检查是否已开通相应权限。
     */
    int ERROR_72015 = 72015;
    /**
     * invoice has been lock	发票已被其他公众号锁定。一般为发票已进入后续报销流程，报销企业公众号/企业号/App锁定了发票。
     */
    int ERROR_72023 = 72023;
    /**
     * invoice status error	发票状态错误
     */
    int ERROR_72024 = 72024;
    /**
     * invoice token error	wx_invoice_token 无效
     */
    int ERROR_72025 = 72025;
    /**
     * invoice never set pay mch info	未设置微信支付商户信息
     */
    int ERROR_72028 = 72028;
    /**
     * invalid mchid	mchid 无效
     */
    int ERROR_72030 = 72030;
    /**
     * invalid params	参数错误。可能为请求中包括无效的参数名称或包含不通过后台校验的参数值
     */
    int ERROR_72031 = 72031;
    /**
     * biz reject insert	发票已经被拒绝开票。若order_id被用作参数调用过拒绝开票接口，再使用此order_id插卡机会报此错误
     */
    int ERROR_72035 = 72035;
    /**
     * invoice is busy	发票正在被修改状态，请稍后再试
     */
    int ERROR_72036 = 72036;
    /**
     * invoice order never auth	订单没有授权，可能是开票平台 appid 、商户 appid 、订单 order_id 不匹配
     */
    int ERROR_72038 = 72038;
    /**
     * invoice must be lock first	订单未被锁定，需要先锁定再核销
     */
    int ERROR_72039 = 72039;
    /**
     * invoice pdf error	Pdf 无效，请提供真实有效的 pdf
     */
    int ERROR_72040 = 72040;
    /**
     * billing_code   and billing_no repeated	发票号码和发票代码重复，该发票已经被其它用户领取
     */
    int ERROR_72042 = 72042;
    /**
     * billing_code   or billing_no size error	发票号码和发票代码错误
     */
    int ERROR_72043 = 72043;
    /**
     * scan text out of time	发票抬头二维码超时
     */
    int ERROR_72044 = 72044;
    /**
     * biz contact is empty	商户联系方式未空，请先调用接口设置商户联系方式
     */
    int ERROR_72063 = 72063;
    /**
     * sys error make out invoice failed	开票平台逻辑错误
     */
    int ERROR_73000 = 73000;
    /**
     * wxopenid error	OpenId错误
     */
    int ERROR_73001 = 73001;
    /**
     * ddh orderid empty	订单号为空
     */
    int ERROR_73002 = 73002;
    /**
     * fpqqlsh empty	发票流水号为空
     */
    int ERROR_73003 = 73003;
    /**
     * kplx empty	发票流水号为空
     */
    int ERROR_73004 = 73004;
    /**
     * nsrmc empty	纳税人名称为空
     */
    int ERROR_73007 = 73007;
    /**
     * nsrdz empty	纳税人地址为空
     */
    int ERROR_73008 = 73008;
    /**
     * 73009	nsrdh empty	纳税人电话为空
     */
    int ERROR_73009 = 73009;
    /**
     * 73010	ghfmc empty	购货方名称为空
     */
    int ERROR_73010 = 73010;
    /**
     * 73011	kpr empty	开票人为空
     */
    int ERROR_73011 = 73011;
    /**
     * 73012	jshj empty	计税合计为空
     */
    int ERROR_73012 = 73012;
    /**
     * 73013	hjje empty	合计金额为空
     */
    int ERROR_73013 = 73013;
    /**
     * 73014	hjse empty	合计税额为空
     */
    int ERROR_73014 = 73014;
    /**
     * 73015	hylx empty	行业类型为空
     */
    int ERROR_73015 = 73015;
    /**
     * 73016	nsrsbh empty	纳税人识别号为空
     */
    int ERROR_73016 = 73016;
    /**
     * 73100	ka plat error	开票平台错误
     */
    int ERROR_73100 = 73100;
    /**
     * 73101	nsrsbh not cmp
     * 纳税人识别号不匹配，请求中的纳税人识别号和创建工单填写的纳税人识别号不一致
     */
    int ERROR_73101 = 73101;
    /**
     * 73102	sys error
     * 微信开票平台系统错误
     */
    int ERROR_73102 = 73102;
    /**
     * 73105	Kp plat make invoice timeout, please try again with the same fpqqlsh
     * 开票平台开票中，请使用相同的发票请求流水号重试开票
     */
    int ERROR_73105 = 73105;
    /**
     * 73106	Fpqqlsh exist with different ddh
     * 发票请求流水号已存在，并被其他订单号占用
     */
    int ERROR_73106 = 73106;
    /**
     * 73107	Fpqqlsh is processing, please wait and query later
     * 发票请求流水正在被处理，请通过查询接口获取结果
     */
    int ERROR_73107 = 73107;
    /**
     * 73108	This ddh with other fpqqlsh already exist
     * 该订单已被其他发票请求流水处理
     */
    int ERROR_73108 = 73108;
    /**
     * 73110	fpqqlsh first 6 byte not cmp
     * 发票请求流水号前6位不正确
     */
    int ERROR_73110 = 73110;
    /**
     * 40078	invalid   card status
     * card_id未授权。 若开发者使用沙箱环境报此错误，主要因为未将调用接口的微信添加到测试把名单； 若开发者使用正式环境报此错误，主要原因可能为：调用接口公众号未开通卡券权限，或创建card_id与插卡时间间隔过短。
     */
    int ERROR_40078 = 40078;

    /**
     * **************** 微信网页授权错误代码 ****************
     */
    /**
     * redirect_uri域名与后台配置不一致
     */
    int ERROR_10003 = 10003;
    /**
     * 此公众号被封禁
     */
    int ERROR_10004 = 10004;
    /**
     * 此公众号并没有这些scope的权限
     */
    int ERROR_10005 = 10005;
    /**
     * 必须关注此测试号
     */
    int ERROR_10006 = 10006;
    /**
     * 操作太频繁了，请稍后重试
     */
    int ERROR_10009 = 10009;
    /**
     * scope不能为空
     */
    int ERROR_10010 = 10010;
    /**
     * redirect_uri不能为空
     */
    int ERROR_10011 = 10011;
    /**
     * appid不能为空
     */
    int ERROR_10012 = 10012;
    /**
     * state不能为空
     */
    int ERROR_10013 = 10013;
    /**
     * 公众号未授权第三方平台，请检查授权状态
     */
    int ERROR_10015 = 10015;
    /**
     * 不支持微信开放平台的Appid，请使用公众号Appid
     */
    int ERROR_10016 = 10016;
    /**
     * **************** 微信网页授权错误代码 ****************
     */
}
