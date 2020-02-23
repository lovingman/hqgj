import request from '@/utils/request'

export function getList(query) {
    return request({
        url: '/hqgj/companyAppeal/page',
        method: 'get',
        params: query
    })
}
export function exportXls(query) {
    return request({
        url: '/hqgj/companyAppeal/exportXls',
        method: 'get',
        params: query,
        responseType: 'blob'
    })
}
export function getById(id) {
    return request({
        url: '/hqgj/companyAppeal/getById',
        method: 'get',
        params: {id:id}
    })
}
export  function updateProjectState(data){
    return request({
        url: '/hqgj/companyAppeal/updateProjectState',
        method: 'post',
        data: data
    })
}