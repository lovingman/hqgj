var server = "http://192.168.2.114";
var config = {
    login: `${server}/hqgj-portal/security/login`,//登录
    authority: `${server}/wxms/www/mini/authority`,//微信授权
    getCaptcha: `${server}/hqgj/www/reg/captcha`,//验证码
    register: `${server}/hqgj/www/reg/register`,//注册
    captchaForgetPwd: `${server}/hqgj/www/reg/captchaForgetPwd`,//   验证码获取接口
    resetPassword: `${server}/hqgj/www/reg/resetPassword`,//重置密码
    trainPageUrl: `${server}/hqgj/www/serveCultivate/page`, //培训列表page请求
    selectByIdDetailsUrl: `${server}/hqgj/www/serveCultivate/selectByIdDetails`, //培训列表详情请求
    schedulePageUrl: `${server}/hqgj/www/serveCultivate/schedulePage`, //日程请求列表
    scheduleGetByIdUrl: `${server}/hqgj/www/serveCultivate/scheduleGetById`, //日程列表详情
    financePageUrL: `${server}/hqgj/www/serveFinance/page`, //财税服务page请求
    financeIdUrl: `${server}/hqgj/www/serveFinance/getById`, //财税服务列表详情
  login: `${server}/hqgj-portal/security/login`,
  getByCategoryUrl: `${server}/hqgj-portal/dict/getByCategoryIds`,//字典
  trainPageUrl: `${server}/hqgj/www/serveCultivate/page`,  //培训列表page请求
  selectByIdDetailsUrl: `${server}/hqgj/www/serveCultivate/selectByIdDetails`,  //培训列表详情请求
  schedulePageUrl: `${server}/hqgj/www/serveCultivate/schedulePage`,  //日程请求列表
  scheduleGetByIdUrl: `${server}/hqgj/www/serveCultivate/scheduleGetById`,  //日程列表详情
  financePageUrL: `${server}/hqgj/www/serveFinance/page`,  //财税服务page请求
  financeIdUrl: `${server}/hqgj/www/serveFinance/getById`,  //财税服务列表详情
  serveBusinessCreateUrl: `${server}/hqgj/serveBusiness/create`,  //新增创业服务
};

module.exports = config