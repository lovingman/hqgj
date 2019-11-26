import request from '@/utils/request'
// export function page(query) {
//   return request({
//     url: '/portal/corp/page',
//     method: 'get',
//     params: query
//   })
// }
// export function create(data) {
//   return request({
//     url: '/portal/corp/create',
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
//     url: '/portal/corp/update',
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
//       url: '/portal/corp/deleteById',
//       method: 'get',
//       params: {id:id}
//     })
//   }
// export function deleteByIds(ids) {
//     return request({
//       url: '/portal/corp/deleteByIds',
//       method: 'get',
//       params: {ids:ids}
//     })
//   }
// export function getById(id) {
//     return request({
//         url: '/portal/corp/getById',
//         method: 'get',
//         params: {id:id}
//     })
// }
//
// export function getList(name) {
//   return request({
//       url: '/portal/corp/getList',
//       method: 'get',
//       params: {name:name}
//   })
// }
export function page(query) {
    return request({
        url: '/zcpa-portal/corp/page',
        method: 'get',
        params: query
    })
}
export function create(data) {
    return request({
        url: '/zcpa-portal/corp/create',
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
        url: '/zcpa-portal/corp/update',
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
        url: '/zcpa-portal/corp/deleteById',
        method: 'get',
        params: {id:id}
    })
}
export function deleteByIds(ids) {
    return request({
        url: '/zcpa-portal/corp/deleteByIds',
        method: 'get',
        params: {ids:ids}
    })
}
export function getById(id) {
    return request({
        url: '/zcpa-portal/corp/getById',
        method: 'get',
        params: {id:id}
    })
}

export function getList(name) {
    return request({
        url: '/zcpa-portal/corp/getList',
        method: 'get',
        params: {name:name}
    })
}
