import request from '@/utils/request'
// export function page(query) {
//   return request({
//     url: '/portal/role/page',
//     method: 'get',
//     params: query
//   })
// }
// export function create(query) {
//   return request({
//     url: '/portal/role/create',
//     params: query
//   })
// }
//
// export function update(query) {
//   return request({
//     url: '/portal/role/update',
//     params: query
//   })
// }
// export function deleteById(id) {
//   return request({
//     url: '/portal/role/deleteById',
//     params: { id: id }
//   })
// }
// export function deleteByIds(ids) {
//   return request({
//     url: '/portal/role/deleteByIds',
//     method: 'get',
//     params: { ids: ids }
//   })
// }
// export function getById(id) {
//   return request({
//     url: '/portal/role/getById',
//     method: 'get',
//     params: { id: id }
//   })
// }
//
//
// export function insertRoleRes(query) {
//   return request({
//     url: '/portal/role/insertRoleRes',
//     params: query
//   })
// }
// export function getResTreeList() {
//   return request({
//     url: '/portal/resources/getResTreeList',
//     method: 'get',
//     params: {}
//   })
// }
// export function selectRoleResByRoleId(roleId) {
//   return request({
//     url: '/portal/role/selectRoleResByRoleId',
//     method: 'get',
//     params: { roleId: roleId }
//   })
// }
export function page(query) {
  return request({
    url: '/hqgj-portal/role/page',
    method: 'get',
    params: query
  })
}
export function create(query) {
  return request({
    url: '/hqgj-portal/role/create',
    params: query
  })
}

export function update(query) {
  return request({
    url: '/hqgj-portal/role/update',
    params: query
  })
}
export function deleteById(id) {
  return request({
    url: '/hqgj-portal/role/deleteById',
    params: { id: id }
  })
}
export function deleteByIds(ids) {
  return request({
    url: '/hqgj-portal/role/deleteByIds',
    method: 'get',
    params: { ids: ids }
  })
}
export function getById(id) {
  return request({
    url: '/hqgj-portal/role/getById',
    method: 'get',
    params: { id: id }
  })
}


export function insertRoleRes(query) {
  return request({
    url: '/hqgj-portal/role/insertRoleRes',
    params: query
  })
}
export function getResTreeList() {
  return request({
    url: '/hqgj-portal/resources/getResTreeList',
    method: 'get',
    params: {}
  })
}
export function selectRoleResByRoleId(roleId) {
  return request({
    url: '/hqgj-portal/role/selectRoleResByRoleId',
    method: 'get',
    params: { roleId: roleId }
  })
}

