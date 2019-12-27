var server = "http://test.huacainfo.com";
var config = {
  login: `${server}/hqgj-portal/security/login`,
  authority: `${server}/wxms/www/mini/authority`, //微信授权
  getCaptcha: `${server}/hqgj/www/reg/captcha`, //验证码
  register: `${server}/hqgj/www/reg/register`, //注册
  captchaForgetPwd: `${server}/hqgj/www/reg/captchaForgetPwd`, //   验证码获取接口
  resetPassword: `${server}/hqgj/www/reg/resetPassword`, //重置密码
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
};


module.exports = config