var server = "https://test.huacainfo.com";
// var server = "http://192.168.2.114";
var config = {
    login: `${server}/hqgj-portal/security/login`,
    authority: `${server}/wxmss/www/mini/authority`, //微信授权
    getCaptcha: `${server}/hqgj/www/reg/captcha`, //验证码
    register: `${server}/hqgj/www/reg/register`, //注册
    captchaForgetPwd: `${server}/hqgj/www/reg/captchaForgetPwd`, //   验证码获取接口
    resetPassword: `${server}/hqgj/www/reg/resetPassword`, //重置密码
    getUserInfo: `${server}/hqgj/personalCenter/homePage`, //获取用户详情
    getByCategoryUrl: `${server}/hqgj-portal/dict/getByCategoryIds`, //字典
    trainPageUrl: `${server}/hqgj/www/serveCultivate/page`, //培训列表page请求
    selectByIdDetailsUrl: `${server}/hqgj/www/serveCultivate/selectByIdDetails`, //培训列表详情请求
    schedulePageUrl: `${server}/hqgj/www/serveCultivate/schedulePage`, //日程请求列表
    scheduleGetByIdUrl: `${server}/hqgj/www/serveCultivate/scheduleGetById`, //日程列表详情
    financePageUrL: `${server}/hqgj/www/serveFinance/page`, //财税服务page请求
    financeIdUrl: `${server}/hqgj/www/serveFinance/getById`, //财税服务列表详情
    baseOrganizationUrl: `${server}/hqgj/www/baseOrganization/page`, //机构page请求
    organizationList: `${server}/hqgj/baseOrganization/page`, //组织列表 
    baseOrganizationByIdUrl: `${server}/hqgj/www/baseOrganization/getById`, //机构详情
    baseOrganizationMemberUrl: `${server}/hqgj/www/baseOrganizationMember/page`, //法律服务列表成员page
    baseOrganizationById: `${server}/hqgj/www/baseOrganizationMember/getById`, //机构成员详情
    basicAnnexUrl: `${server}/hqgj/www/basicAnnex/page`, //附件page
    lawServePageUrl: `${server}/hqgj/www/lawServe/policyPage`, //政策服务page
    lawServeByIdUrl: `${server}/hqgj/www/lawServe/policyGetById`, //政策服务详情
    serveBusinessCreateUrl: `${server}/hqgj/serveBusiness/create`, //新增创业服务
    serveCultivateEnrollUrl: `${server}/hqgj/serveCultivateEnroll/create`, //培训提升服务报名
    isLoginUrl: `${server}/hqgj/www/reg/isLogin`, //判断是否登录
    enrollPage: `${server}/hqgj/www/serveCultivate/enrollPage`, //报名管理列表查看
    myEnrollPage: `${server}/hqgj/serveCultivateEnroll/enrollPage`, //我的培训列表
    cancelEnroll: `${server}/hqgj/serveCultivateEnroll/cancelEnroll`, //取消报名
    deleteEnroll: `${server}/hqgj/serveCultivateEnroll/deleteById`, //删除培训报名  
    orderPage: `${server}/hqgj/serveFinanceOrder/page`, //订单。 
    myOrderPage: `${server}/hqgj/serveFinanceOrder/orderPage`, //我的订单订单。 
    updateOrderStatus: `${server}/hqgj/serveFinanceOrder/updateStatus1`, //订单修改状态
    deleteOrder: `${server}/hqgj/serveFinanceOrder/deleteById`, //删除订单。
    evaluateOrder: `${server}/hqgj/serveFinanceEvaluate/create`, //评价订单
    homePage: `${server}/hqgj/personalCenter/homePage`, //用户信息
    getAreaTreeUrl: `${server}/hqgj-portal/system/getAreaTree`, //获取行政区划
    serveBusinessPageUrl: `${server}/hqgj/serveBusiness/page`, //创业服务page
    businessPage: `${server}/hqgj/serveBusiness/businessPage`, //创业服务page
    serveBusinessPreviewInfoUrl: `${server}/hqgj/serveBusiness/previewInfo`, //创业服务详情
    enterpriseList: `${server}/hqgj/baseCompany/page`, //企业列表  / 
    joinOrg: `${server}/hqgj/personalCenter/bingUser`, //加入组织企业
    relieveBind: `${server}/hqgj/personalCenter/relieveBind`, //解除组织企业
    modifyPwd: `${server}/hqgj-portal/users/updatePwd`, //修改密码
    enrollUpdate: `${server}/hqgj/serveCultivateEnroll/updateStatus`, //签到管理
    relieveByUnionId: `${server}/hqgj/personalCenter/relieveByUnionId`, //解绑微信
    bindUnionId: `${server}/hqgj/personalCenter/bindUnionId`, //绑定微信
    integralDetailed: `${server}/hqgj/serveBusinessIntegral/IntegralDetailed`, //积分明细
    orderContactIdPage: `${server}/hqgj/serveFinanceOrder/orderContactIdPage`, //服务列表
    lecturerPage: `${server}/hqgj/serveCultivate/lecturerPage`, //服务列表
    lawServeTemplate: `${server}/hqgj/www/lawServe/page`, //法律模板
    financeOrder: `${server}/hqgj/serveFinanceOrder/create`, //财税下单
    serveBusinessUpdateUrl: `${server}/hqgj/serveBusiness/update`, //创业服务page
    createAppeal: `${server}/hqgj/companyAppeal/create`,//提交诉求
    getMyAppeal: `${server}/hqgj/companyAppeal/userpage`,//我的诉求
    getAppealList: `${server}/hqgj/companyAppeal/page`//我的诉求
};

module.exports = config