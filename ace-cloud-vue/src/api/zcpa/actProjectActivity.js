import request from '@/utils/request'
export function page(query) {
    return request({
        url: '/zcpa/actProjectActivity/page',
        method: 'get',
        params: query
    })
}
export function getById(id) {
    return request({
        url: '/zcpa/actProjectActivity/selectByActivity',
        method: 'get',
        params: {id:id}
    })
}
export  function updateProjectState(data){
    return request({
        url: '/zcpa/actProjectActivity/updateProjectState',
        method: 'post',
        data: data
    })
}
