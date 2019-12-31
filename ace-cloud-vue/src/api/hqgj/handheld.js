import request from '@/utils/request'

//请求page
export function page(query) {
  return request({
    url: '/hqgj/serveFinance/page',
    method: 'get',
    params: query
  })
}

//编辑请求财税管家的服务项目
export function itemPage(id) {
  return request({
    url: '/hqgj/serveFinanceItem/page',
    method: 'get',
    params: {
      financeId: id
    }
  })
}
//创建
export function create(data) {
  return request({
    url: '/hqgj/serveFinance/create',
    method: 'post',
    data: data
  })
}

//服务机构请求列表数组
export function orgChange(query) {
  return request({
    url: '/hqgj/baseOrganization/page',
    method: 'get',
    params: query
  })
}

//编辑修改
export function update(data) {
  return request({
    url: '/hqgj/serveFinance/update ',
    method: 'post',
    data: data
  })
}

//获取
export function getById(id) {
  return request({
    url: '/hqgj/serveFinance/getById',
    method: 'get',
    params: {
      id: id
    },
  })
}
//删除
export function deleteByIds(ids) {
  return request({
    url: '/hqgj/serveFinance/deleteByIds',
    method: 'post',
    params: {
      ids: ids
    },
  })
}
//审核
export function updateStatus(id, status) {
  return request({
    url: '/hqgj/serveFinance/updateStatus',
    method: 'post',
    params: {
      id: id,
      status: status
    },
  })
}

//订单管理page
export function orderPage(query) {
  return request({
    url: '/hqgj/serveFinanceOrder/page',
    method: 'get',
    params: query,
  })
}
//订单管理查看
export function ordergetById(id) {
  return request({
    url: '/hqgj/serveFinanceOrder/getById',
    method: 'get',
    params: {
      id: id
    },
  })
}

//订单管理确认
export function orderStatus(id, status) {
  return request({
    url: '/hqgj/serveFinanceOrder/updateStatus',
    method: 'post',
    params: {
      id: id,
      status: status
    },
  })
}

//评价管理page
export function evaluatePage(query) {
  return request({
    url: '/hqgj/serveFinanceEvaluate/page',
    method: 'get',
    params: query,
  })
}

//评价删除
export function evaluateDelete(ids) {
  return request({
    url: '/hqgj/serveFinanceEvaluate/deleteByIds',
    method: 'post',
    params: {
      ids: ids
    },
  })
}