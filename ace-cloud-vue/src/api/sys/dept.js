import request from '@/utils/request'
// export function page(query) {
//   return request({
//     url: '/portal/dept/page',
//     method: 'get',
//     params: query
//   })
// }
// export function create(data) {
//   return request({
//     url: '/portal/dept/create',
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
//     url: '/portal/dept/update',
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
//       url: '/portal/dept/deleteById',
//       method: 'get',
//       params: {id:id}
//     })
//   }
// export function deleteByIds(ids) {
//     return request({
//       url: '/portal/dept/deleteByIds',
//       method: 'get',
//       params: {ids:ids}
//     })
//   }
// export function getById(id) {
//     return request({
//         url: '/portal/dept/getById',
//         method: 'get',
//         params: {id:id}
//     })
// }
//
// export function getList(name) {
//   return request({
//       url: '/portal/dept/getList',
//       method: 'get',
//       params: {name:name}
//   })
// }
export function page(query) {
    return request({
        url: '/hqgj-portal/dept/page',
        method: 'get',
        params: query
    })
}
export function create(data) {
    return request({
        url: '/hqgj-portal/dept/create',
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
        url: '/hqgj-portal/dept/update',
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
        url: '/hqgj-portal/dept/deleteById',
        method: 'get',
        params: {id:id}
    })
}
export function deleteByIds(ids) {
    return request({
        url: '/hqgj-portal/dept/deleteByIds',
        method: 'get',
        params: {ids:ids}
    })
}
export function getById(id) {
    return request({
        url: '/hqgj-portal/dept/getById',
        method: 'get',
        params: {id:id}
    })
}

export function getList(name) {
    return request({
        url: '/hqgj-portal/dept/getList',
        method: 'get',
        params: {name:name}
    })
}
