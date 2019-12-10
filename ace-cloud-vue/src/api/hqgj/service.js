import request from '@/utils/request'

export function getList(query) {
    return request({
        url: '/hqgj/baseOrganization/page',
        method: 'get',
        params: query
    })
}
export function getById(id) {
    return request({
        url: '/hqgj/baseOrganization/getById',
        method: 'get',
        params: {id:id}
    })
}
export function create(data) {
    return request({
        url: '/hqgj/baseOrganization/create',
        method: 'post',
        data: data
    })
}
export function update(data) {
    return request({
        url: '/hqgj/baseOrganization/update',
        method: 'post',
        data: data
    })
}
export function deleteById(id) {
    return request({
        url: '/hqgj/baseOrganization/deleteById',
        method: 'post',
        params: {id:id}
    })
}
