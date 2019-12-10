import request from '@/utils/request'

// export function page(query) {
//   return request({
//     url: '/portal/resources/page',
//     method: 'get',
//     params: query
//   })
// }
// export function create(data) {
//   return request({
//     url: '/portal/resources/create',
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
//       headers: {
//         'Content-Type': 'application/x-www-form-urlencoded'
//       }
//     }
//   })
// }
// export function update(data) {
//   return request({
//     url: '/portal/resources/update',
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
// export function deleteById(id) {
//   return request({
//     url: '/portal/resources/deleteById',
//     method: 'get',
//     params: {
//       id: id
//     }
//   })
// }
// export function deleteByIds(ids) {
//   return request({
//     url: '/portal/resources/deleteByIds',
//     method: 'get',
//     params: {
//       ids: ids
//     }
//   })
// }
// export function getById(id) {
//   return request({
//     url: '/portal/resources/getById',
//     method: 'get',
//     params: {
//       id: id
//     }
//   })
// }
//
// export function getTree() {
//   return request({
//     url: '/portal/resources/getResTreeList',
//     method: 'get'
//   })
// }
//
// export function getListByPid(pid) {
//   return request({
//     url: '/portal/resources/getListByPid',
//     method: 'get',
//     params: {
//       pid: pid
//     }
//   })
// }
//
// export function updateSequence(data) {
//   return request({
//     url: '/portal/resources/updateSequence',
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
export function page(query) {
  return request({
    url: '/hqgj-portal/resources/page',
    method: 'get',
    params: query
  })
}
export function create(data) {
  return request({
    url: '/hqgj-portal/resources/create',
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
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    }
  })
}
export function update(data) {
  return request({
    url: '/hqgj-portal/resources/update',
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
export function deleteById(id) {
  return request({
    url: '/hqgj-portal/resources/deleteById',
    method: 'get',
    params: {
      id: id
    }
  })
}
export function deleteByIds(ids) {
  return request({
    url: '/hqgj-portal/resources/deleteByIds',
    method: 'get',
    params: {
      ids: ids
    }
  })
}
export function getById(id) {
  return request({
    url: '/hqgj-portal/resources/getById',
    method: 'get',
    params: {
      id: id
    }
  })
}

export function getTree() {
  return request({
    url: '/hqgj-portal/resources/getResTreeList',
    method: 'get'
  })
}

export function getListByPid(pid) {
  return request({
    url: '/hqgj-portal/resources/getListByPid',
    method: 'get',
    params: {
      pid: pid
    }
  })
}

export function updateSequence(data) {
  return request({
    url: '/hqgj-portal/resources/updateSequence',
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
