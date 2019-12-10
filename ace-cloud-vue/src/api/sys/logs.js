import request from '@/utils/request'
// export function page(query) {
//   return request({
//     url: '/portal/logs/page',
//     method: 'get',
//     params: query
//   })
// }
//
// export function getById(id) {
//     return request({
//         url: '/portal/logs/getById',
//         method: 'get',
//         params: {id:id}
//     })
// }
export function page(query) {
    return request({
        url: '/hqgj-portal/logs/page',
        method: 'get',
        params: query
    })
}

export function getById(id) {
    return request({
        url: '/hqgj-portal/logs/getById',
        method: 'get',
        params: {id:id}
    })
}
