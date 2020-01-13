import request from '@/utils/request'

export function getList(query) {
    return request({
        url: '/hqgj/baseOrganization/page',
        method: 'get',
        params: query
    })
}

export function getUser(query) {
    return request({
        url: '/hqgj/personalCenter/homePage',
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
export function deleteByIds(ids) {
    return request({
        url: '/hqgj/baseOrganization/deleteByIds',
        method: 'post',
        params: {ids:ids}
    })
}
export function exportXls(query) {
    return request({
        url: '/hqgj/baseOrganization/exportXls',
        method: 'get',
        params: query,
        responseType: 'blob'
    })
}
export function personPage(query) {
    return request({
        url: '/hqgj/baseOrganizationMember/page',
        method: 'get',
        params: query
    })
}
export function exportXlsPerson(query) {
    return request({
        url: '/hqgj/baseOrganizationMember/exportXls',
        method: 'get',
        params: query,
        responseType: 'blob'
    })
}
export function deletePerson(id) {
    return request({
        url: '/hqgj/baseOrganizationMember/deleteById',
        method: 'post',
        params: {id:id}
    })
}
export function deletePersons(ids) {
    return request({
        url: '/hqgj/baseOrganizationMember/deleteByIds',
        method: 'post',
        params: {ids:ids}
    })
}

export function getByIdPerson(id) {
    return request({
        url: '/hqgj/baseOrganizationMember/getById',
        method: 'get',
        params: {id:id}
    })
}
export function createPerson(data) {
    return request({
        url: '/hqgj/baseOrganizationMember/create',
        method: 'post',
        data: data
    })
}
export function updatePerson(data) {
    return request({
        url: '/hqgj/baseOrganizationMember/update',
        method: 'post',
        data: data
    })
}

