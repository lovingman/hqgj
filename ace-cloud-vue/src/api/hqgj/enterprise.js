import request from '@/utils/request'

export function getList(query) {
    return request({
        url: '/hqgj/baseCompany/page',
        method: 'get',
        params: query
    })
}

export function getById(id) {
    return request({
        url: '/hqgj/baseCompany/getById',
        method: 'get',
        params: {id:id}
    })
}
export function create(data) {
    return request({
        url: '/hqgj/baseCompany/create',
        method: 'post',
        data: data
    })
}
export function update(data) {
    return request({
        url: '/hqgj/baseCompany/update',
        method: 'post',
        data: data
    })
}
export function deleteById(id) {
    return request({
        url: '/hqgj/baseCompany/deleteById',
        method: 'post',
        params: {id:id}
    })
}
export function personPage(query) {
    return request({
        url: '/hqgj/baseCompanyMember/page',
        method: 'get',
        params: query
    })
}
export function deletePerson(id) {
    return request({
        url: '/hqgj/baseCompanyMember/deleteById',
        method: 'post',
        params: {id:id}
    })
}
