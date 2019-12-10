import request from '@/utils/request'
// export function page(query) {
//   return request({
//     url: '/portal/dict/page',
//     method: 'get',
//     params: query
//   })
// }
// export function create(data) {
//   return request({
//     url: '/portal/dict/create',
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
//     url: '/portal/dict/update',
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
//       url: '/portal/dict/deleteById',
//       method: 'get',
//       params: {id:id}
//     })
//   }
// export function deleteByIds(ids) {
//     return request({
//       url: '/portal/dict/deleteByIds',
//       method: 'get',
//       params: {ids:ids}
//     })
//   }
// export function getById(id) {
//     return request({
//         url: '/portal/dict/getById',
//         method: 'get',
//         params: {id:id}
//     })
// }
//
// export function getList() {
//   return request({
//       url: '/portal/dictCategory/findDictCategoryListAll',
//       method: 'get'
//   })
// }
export function page(query) {
  return request({
    url: '/hqgj-portal/dict/page',
    method: 'get',
    params: query
  })
}
export function create(data) {
  return request({
    url: '/hqgj-portal/dict/create',
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
    url: '/hqgj-portal/dict/update',
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
    url: '/hqgj-portal/dict/deleteById',
    method: 'get',
    params: {id:id}
  })
}
export function deleteByIds(ids) {
  return request({
    url: '/hqgj-portal/dict/deleteByIds',
    method: 'get',
    params: {ids:ids}
  })
}
export function getById(id) {
  return request({
    url: '/hqgj-portal/dict/getById',
    method: 'get',
    params: {id:id}
  })
}

export function getList() {
  return request({
    url: '/hqgj-portal/dictCategory/findDictCategoryListAll',
    method: 'get'
  })
}
