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
export function update(data) {
    return request({
        url: '/hqgj/serveBusiness/update',
        method: 'post',
        data: data
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
export function deleteIntegral(id) {
    return request({
        url: '/hqgj/serveBusinessIntegral/deleteById',
        method: 'post',
        params: {id:id}
    })
}

export function getMember(query) {
    return request({
        url: '/hqgj/serveBusinessDetail/page',
        method: 'get',
        params: query
    })
}

export function getannexList(id) {
    return request({
        url: '/hqgj/serveBusinessDetail/annexList',
        method: 'get',
        params: {id:id}
    })
}
export function getAnnex(query) {
    return request({
        url: '/hqgj/basicAnnex/page',
        method: 'get',
        params: query
    })
}
