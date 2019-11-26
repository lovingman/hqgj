import request from '@/utils/request'

// export function login(data) {
//   return request({
//     url: '/portal/security/login',
//     method: 'post',
//     data: data,
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
//
// export function getInfo() {
//   return request({
//     url: '/portal/system/getUserPropInfo',
//     method: 'get'
//   })
// }
//
// export function logout() {
//   return request({
//     url: '/portal/security/logout',
//     method: 'get'
//   })
// }
//
//
// export function getMenu() {
//   return request({
//     url: '/portal/system/getSysTreeMenu',
//     method: 'get'
//   })
// }
// export function getBtn() {
//   return request({
//     url: '/portal/system/getSysButtons',
//     method: 'get'
//   })
// }
// // export function getProvinceCityOptions() {
// //   return request({
// //     url: '/portal/system/getAreaTree?pid=00&type=4',
// //     method: 'get'
// //   })
// // }
// export function getDict(ids) {
//   return request({
//     url: '/portal/dict/getByCategoryIds',
//     method: 'get',
//     params: {
//       categoryIds: `4,47,48,49,50,51`,
//     }
//   })
// }
export function login(data) {
  return request({
    url: '/zcpa-portal/security/login',
    method: 'post',
    data: data,
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

export function getInfo() {
  return request({
    url: '/zcpa-portal/system/getUserPropInfo',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/zcpa-portal/security/logout',
    method: 'get'
  })
}


export function getMenu() {
  return request({
    url: '/zcpa-portal/system/getSysTreeMenu',
    method: 'get'
  })
}
export function getBtn() {
  return request({
    url: '/zcpa-portal/system/getSysButtons',
    method: 'get'
  })
}
export function getDict(ids) {
  return request({
    url: '/zcpa-portal/dict/getByCategoryIds',
    method: 'get',
    params: {
      categoryIds: `4,47,48,49,50,51`,
    }
  })
}
