import request from '@/utils/request'

export function getPolicy(query) {
    return request({
        url: '/hqgj/lawPolicy/page',
        method: 'get',
        params: query
    })
}
export function createPolicy(data) {
    return request({
        url: '/hqgj/lawPolicy/create',
        method: 'post',
        data: data
    })
}
export function getPolicyById(id) {
    return request({
        url: '/hqgj/lawPolicy/getById',
        method: 'get',
        params: {id:id}
    })
}
export function updatePolicy(data) {
    return request({
        url: '/hqgj/lawPolicy/update',
        method: 'post',
        data: data
    })
}
export function deletePolicyById(id) {
    return request({
        url: '/hqgj/lawPolicy/deleteById',
        method: 'post',
        params: {id:id}
    })
}
export function updateStatus(id,status) {
    return request({
        url: '/hqgj/lawPolicy/updateStatus',
        method: 'post',
        params: {id: id, status: status}
    })
}
export function getServe(query) {
    return request({
        url: '/hqgj/lawServe/page',
        method: 'get',
        params: query
    })
}
export function createServe(data) {
    return request({
        url: '/hqgj/lawServe/create',
        method: 'post',
        data: data
    })
}
export function getServeById(id) {
    return request({
        url: '/hqgj/lawServe/getById',
        method: 'get',
        params: {id:id}
    })
}
export function updateServe(data) {
    return request({
        url: '/hqgj/lawServe/update',
        method: 'post',
        data: data
    })
}
export function deleteServeById(id) {
    return request({
        url: '/hqgj/lawServe/deleteById',
        method: 'post',
        params: {id:id}
    })
}
export function basicAnnexPage(query) {
    return request({
        url: '/hqgj/basicAnnex/page',
        method: 'get',
        params: query
    })
}
