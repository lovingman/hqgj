import request from '@/utils/request'

export function getList(query) {
    return request({
        url: '/hqgj/lawPolicy/page',
        method: 'get',
        params: query
    })
}
export function getById(id) {
    return request({
        url: '/hqgj/lawPolicy/getById',
        method: 'get',
        params: {id:id}
    })
}
export function create(data) {
    return request({
        url: '/hqgj/lawPolicy/create',
        method: 'post',
        data: data
    })
}
export function update(data) {
    return request({
        url: '/hqgj/lawPolicy/update',
        method: 'post',
        data: data
    })
}
export function deleteById(id) {
    return request({
        url: '/hqgj/lawPolicy/deleteById',
        method: 'post',
        params: {id:id}
    })
}
