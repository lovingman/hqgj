import request from '@/utils/request'

//请求page
export function page(query) {
  return request({
    url: '/hqgj/serveCultivate/page',
    method: 'get',
    params: query
  })
}
//创建
export function create(data) {
  return request({
    url: '/hqgj/serveCultivate/create ',
    method: 'post',
    data: data
  })
}
//修改
export function update(data) {
  return request({
    url: '/hqgj/serveCultivate/update ',
    method: 'post',
    data: data
  })
}
//获取
export function getById(id) {
  return request({
    url: '/hqgj/serveCultivate/getById',
    method: 'get',
    params: {
      id: id
    },
  })
}
//删除
export function deleteByIds(ids) {
  return request({
    url: '/hqgj/serveCultivate/deleteByIds',
    method: 'post',
    params: {
      ids: ids
    },
  })
}