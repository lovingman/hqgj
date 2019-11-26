import request from '@/utils/request'
// export function page(query) {
//   return request({
//     url: '/portal/dictCategory/page',
//     method: 'get',
//     params: query
//   })
// }
// export function create(data) {
//   return request({
//     url: '/portal/dictCategory/create',
//     method: 'post',
//     data:data,
//     transformRequest: [function (data) {
//       let ret = ''
//       for (let it in data) {
//         ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
//       }
//       return ret
//     }],
//     headers: {
//       'Content-Type': 'application/x-www-form-urlencoded'
//     }
//   })
// }
// export function update(data) {
//   return request({
//     url: '/portal/dictCategory/update',
//     method: 'post',
//     data:data,
//     transformRequest: [function (data) {
//       let ret = ''
//       for (let it in data) {
//         ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
//       }
//       return ret
//     }],
//     headers: {
//       'Content-Type': 'application/x-www-form-urlencoded'
//     }
//   })
// }
// export function deleteById(id) {
//     return request({
//       url: '/portal/dictCategory/deleteById',
//       method: 'get',
//       params: {id:id}
//     })
//   }
// export function deleteByIds(ids) {
//     return request({
//       url: '/portal/dictCategory/deleteByIds',
//       method: 'get',
//       params: {ids:ids}
//     })
//   }
// export function getById(id) {
//     return request({
//         url: '/portal/dictCategory/getById',
//         method: 'get',
//         params: {id:id}
//     })
// }
export function Categorypage(query) {
  return request({
    url: '/zcpa-portal/dictCategory/page',
    method: 'get',
    params: query
  })
}
export function create(data) {
  return request({
    url: '/zcpa-portal/dictCategory/create',
    method: 'post',
    data:data,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
export function update(data) {
  return request({
    url: '/zcpa-portal/dictCategory/update',
    method: 'post',
    data:data,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
export function deleteById(id) {
  return request({
    url: '/zcpa-portal/dictCategory/deleteById',
    method: 'get',
    params: {id:id}
  })
}
export function deleteByIds(ids) {
  return request({
    url: '/zcpa-portal/dictCategory/deleteByIds',
    method: 'get',
    params: {ids:ids}
  })
}
export function getById(id) {
  return request({
    url: '/zcpa-portal/dictCategory/getById',
    method: 'get',
    params: {id:id}
  })
}
