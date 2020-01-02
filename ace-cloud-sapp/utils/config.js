// var server = "https://test.huacainfo.com";
var server = "http://192.168.2.114";
// var server = "http://192.168.2.105";
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
    baseOrganizationUrl: `${server}/hqgj/www/baseOrganization/page`, //法律服务page请求
    baseOrganizationByIdUrl: `${server}/hqgj/www/baseOrganization/getById`, //法律服务列表详情
    baseOrganizationMemberUrl: `${server}/hqgj/www/baseOrganizationMember/page`, //法律服务列表成员page
    basicAnnexUrl: `${server}/hqgj/www/basicAnnex/page`, //法律服务请求模板page
    lawServePageUrl: `${server}/hqgj/www/lawServe/policyPage`, //政策服务page
    lawServeByIdUrl: `${server}/hqgj/www/lawServe/policyGetById`, //政策服务详情
    serveBusinessCreateUrl: `${server}/hqgj/serveBusiness/create`, //新增创业服务
    serveCultivateEnrollUrl: `${server}/hqgj/serveCultivateEnroll/create`, //培训提升服务报名
    isLoginUrl: `${server}/hqgj/www/reg/isLogin`, //判断是否登录
    enrollPage: `${server}/hqgj/www/serveCultivate/enrollPage`, //报名管理列表查看
    myEnrollPage: `${server}/hqgj/serveCultivateEnroll/enrollPage`, //我的培训列表
    cancelEnroll: `${server}/hqgj/serveCultivateEnroll/cancelEnroll`, //取消报名
    deleteEnroll: `${server}/hqgj/serveCultivate/deleteById`, //删除培训报名  /serveFinanceOrder/page
    orderPage: `${server}/hqgj/serveFinanceOrder/page`, //订单。  serveFinanceOrder/updateStatus
    updateOrderStatus: `${server}/hqgj/serveFinanceOrder/updateStatus`, //订单修改状态
    deleteOrder: `${server}/hqgj/serveFinanceOrder/deleteById`, //删除订单。deleteById  /serveFinanceEvaluate/create
    evaluateOrder: `${server}/hqgj/serveFinanceEvaluate/create`, //评价订单
    homePage: `${server}/hqgj/personalCenter/homePage`, //用户信息
    getAreaTreeUrl: `${server}/hqgj-portal/system/getAreaTree`, //获取行政区划
    serveBusinessPageUrl: `${server}/hqgj/serveBusiness/page`, //创业服务page
    serveBusinessPreviewInfoUrl: `${server}/hqgj/serveBusiness/previewInfo`, //创业服务详情
};


module.exports = config