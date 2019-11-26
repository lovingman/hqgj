import request from '@/utils/request'
export function page(query) {
    return request({
        url: '/zcpa/baseVolunteer/page',
        method: 'get',
        params: query
    })
}
export function update(data) {
    return request({
        url: '/zcpa/baseVolunteer/update',
        method: 'post',
        data: data
    })
}
///getById
export function getById(id) {
    return request({
        url: '/zcpa/baseVolunteer/getById',
        method: 'get',
        params:{id:id}
    })
}
//deleteById
export function deleteById(id) {
    return request({
        url: '/zcpa/baseVolunteer/deleteById',
        method: 'post',
        params:{id:id}
    })
}
//deleteByIds
export function deleteByIds(ids) {
    return request({
        url: '/zcpa/baseVolunteer/deleteByIds',
        method: 'post',
        params:{ids:ids}
    })
}
//create
export function create(data) {
    return request({
        url: '/zcpa/baseVolunteer/create',
        method: 'post',
        data:data
    })
}
//baseVolunteer/exportXls
export function exportXls(query) {
    return request({
        url: '/zcpa/baseVolunteer/exportXls',
        method: 'get',
        params: query,
        responseType: 'blob'
    })
}
export function getAreaCode(query) {
    return request({
        url: '/zcpa/www/reg/getAreaCode',
        method: 'get',
        params: query,
    })
}
