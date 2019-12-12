import request from '@/utils/request'

export function getList(query) {
    return request({
        url: '/hqgj/serveBusiness/page',
        method: 'get',
        params: query
    })
}
export function getById(id) {
    return request({
        url: '/hqgj/serveBusiness/getById',
        method: 'get',
        params: {id:id}
    })
}
export function deleteById(id) {
    return request({
        url: '/hqgj/serveBusiness/deleteById',
        method: 'post',
        params: {id:id}
    })
}

export function getPage(query) {
    return request({
        url: '/hqgj/serveBusinessIntegral/page',
        method: 'get',
        params: query
    })
}
export function createIntegral(data) {
    return request({
        url: '/hqgj/serveBusinessIntegral/create',
        method: 'post',
        data: data
    })
}
