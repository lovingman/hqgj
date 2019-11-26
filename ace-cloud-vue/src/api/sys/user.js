import request from '@/utils/request'
// export function page(query) {
//   return request({
//     url: '/portal/users/page',
//     method: 'get',
//     params: query
//   })
// }
// export function create(data) {
//   return request({
//     url: '/portal/users/create',
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
//     url: '/portal/users/update',
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
//       url: '/portal/role/deleteById',
//       method: 'get',
//       params: {id:id}
//     })
//   }
// export function deleteByIds(ids) {
//     return request({
//       url: '/portal/users/deleteByIds',
//       method: 'get',
//       params: {ids:ids}
//     })
//   }
// export function getById(id) {
//     return request({
//         url: '/portal/users/getById',
//         method: 'get',
//         params: {id:id}
//     })
// }
//
//
// export function assignRoles(data) {
//     return request({
//       url: '/portal/users/assignRoles',
//       method: 'post',
//       data:data,
//       transformRequest: [function (data) {
//         let ret = ''
//         for (let it in data) {
//           ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
//         }
//         return ret
//       }],
//       headers: {
//         'Content-Type': 'application/x-www-form-urlencoded'
//       }
//     })
//   }
//   export function getAllRoles() {
//     return request({
//       url: '/portal/users/getAllRoles',
//       method: 'get',
//       params: {}
//     })
//   }
//   export function getMyRoles(userId) {
//     return request({
//       url: '/portal/users/getMyRoles',
//       method: 'get',
//       params: {userId:userId}
//     })
//   }
//
//
//   export function initPwd(data) {
//     return request({
//       url: '/portal/users/initPwd',
//       method: 'post',
//       data:data,
//       transformRequest: [function (data) {
//         let ret = ''
//         for (let it in data) {
//           ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
//         }
//         return ret
//       }],
//       headers: {
//         'Content-Type': 'application/x-www-form-urlencoded'
//       }
//     })
//   }
//
//   export function updateStatus(userId,status) {
//     return request({
//         url: '/portal/users/updateStatus',
//         method: 'get',
//         params: {userId:userId,status:status}
//     })
// }
export function page(query) {
  return request({
    url: '/zcpa-portal/users/page',
    method: 'get',
    params: query
  })
}
export function create(data) {
  return request({
    url: '/zcpa-portal/users/create',
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
    url: '/zcpa-portal/users/update',
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
    url: '/zcpa-portal/users/deleteById',
    method: 'get',
    params: {id:id}
  })
}
export function deleteByIds(ids) {
  return request({
    url: '/zcpa-portal/users/deleteByIds',
    method: 'get',
    params: {ids:ids}
  })
}
export function getById(id) {
  return request({
    url: '/zcpa-portal/users/getById',
    method: 'get',
    params: {id:id}
  })
}


export function assignRoles(data) {
  return request({
    url: '/zcpa-portal/users/assignRoles',
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
export function getAllRoles() {
  return request({
    url: '/zcpa-portal/users/getAllRoles',
    method: 'get',
    params: {}
  })
}
export function getMyRoles(userId) {
  return request({
    url: '/zcpa-portal/users/getMyRoles',
    method: 'get',
    params: {userId:userId}
  })
}


export function initPwd(data) {
  return request({
    url: '/zcpa-portal/users/initPwd',
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

export function updateStatus(userId,status) {
  return request({
    url: '/zcpa-portal/users/updateStatus',
    method: 'get',
    params: {userId:userId,status:status}
  })
}

