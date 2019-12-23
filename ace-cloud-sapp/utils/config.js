var server = "http://192.168.2.114";
var config = {
  trainPageUrl: `${server}/hqgj/www/serveCultivate/page`,  //培训列表page请求
  selectByIdDetailsUrl: `${server}/hqgj/www/serveCultivate/selectByIdDetails`,  //培训列表详情请求
  schedulePageUrl: `${server}/hqgj/www/serveCultivate/schedulePage`,  //日程请求列表
  scheduleGetByIdUrl: `${server}/hqgj/www/serveCultivate/scheduleGetById`,  //日程列表详情
  financePageUrL: `${server}/hqgj/www/serveFinance/page`,  //财税服务page请求
  financeIdUrl: `${server}/hqgj/www/serveFinance/getById`,  //财税服务列表详情
};

module.exports = config