import request from '@/utils/request'

export function getList(query) {
    return request({
        url: '/zcpa/actActivityForumposting/page',
        method: 'get',
        params: query
    })
}

export function updateStatus(id, status) {
    return request({
        url: '/zcpa/actActivityForumposting/updateStatus',
        method: 'post',
        params: {id: id, status: status}
    })
}

export function deleteByIds(ids) {
    return request({
        url: '/zcpa/actActivityForumposting/deleteByIds',
        method: 'post',
        params: {ids: ids}
    })
}

export function getForumPostingDetail(id) {
    return request({
        url: '/zcpa/actActivityForumposting/getForumPostingDetail',
        method: 'get',
        params: {id: id}
    })

}

export function findForumList(data) {
    return request({
        url: '/zcpa/actActivityForum/findForumList',
        method: 'get',
        params: data
    })
}

export function deleteRepliesByIds(ids) {
    return request({
        url: '/zcpa/actActivityForum/deleteByIds',
        method: 'post',
        params: {ids: ids}
    })
}
export function createPerson(data) {
    return request({
        url: '/zcpa/actPosterBlacklist/create',
        method: 'post',
        data: data
    })
}

export function getBlacklist(query) {
    return request({
        url: '/zcpa/actPosterBlacklist/page',
        method: 'get',
        params: query
    })
}

export function BlackdeleteByIds(ids) {
    return request({
        url: '/zcpa/actPosterBlacklist/deleteByIds',
        method: 'post',
        params:{ids:ids}
    })
}

export function actActivityForumpage(query) {
    return request({
        url: '/zcpa/actActivityForum/page',
        method: 'get',
        params: query
    })
}
