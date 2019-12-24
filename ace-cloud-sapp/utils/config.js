var server = "http://192.168.2.114";
var config = {
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
};

module.exports = config