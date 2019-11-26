import request from '@/utils/request'
export function getList(query) {
    return request({
        url: '/zcpa/actProject/page',
        method: 'get',
        params: query
    })
}

export function getById(id) {
    return request({
        url: '/zcpa/actProject/getByProjectId',
        method: 'get',
        params: {
            id: id
        }
    })
}

export function updateState(id, projectState) {
    return request({
        url: '/zcpa/actProject/updateProjectState',
        method: 'post',
        params: {
            id: id,
            projectState: projectState
        }
    })
}

export function deleteById(id) {
    return request({
        url: '/zcpa/actProject/deleteById',
        method: 'post',
        params: {
            id: id
        }
    })
}

export function create(data) {
    return request({
        url: '/zcpa/actProject/create',
        method: 'post',
        data: data
    })
}

export function update(data) {
    return request({
        url: '/zcpa/actProject/update',
        method: 'post',
        data: data
    })
}

export function page(query) {
    return request({
        url: '/zcpa/actPerson/page?screening=1',
        method: 'get',
        params: query
    })
}

export function bfwcreate(data) {
    return request({
        url: '/zcpa/actFamilyTarget/create',
        method: 'post',
        data: data
    })
}

export function schoolcreate(data) {
    return request({
        url: '/zcpa/actProjectTarget/create',
        method: 'post',
        data: data
    })
}

export function zyzpage(query) {
    return request({
        url: '/zcpa/baseVolunteer/page?screening=1',
        method: 'get',
        params: query
    })
}
export function zyzupdate(data) {
    return request({
        url: '/zcpa/actFamilyVolunteer/update',
        method: 'post',
        data: data
    })
}
//actFamilyVolunteer/batchUpdate
export function batchUpdate(ids) {
    return request({
        url: '/zcpa/actFamilyVolunteer/batchUpdate',
        method: 'post',
        params: {
            ids: ids
        }
    })
}
//actFamilyTarget/deleteByIds
export function bfwdeleteByIds(ids) {
    return request({
        url: '/zcpa/actFamilyTarget/deleteByIds',
        method: 'post',
        params: {
            ids: ids
        }
    })
}
//actSchool/page
export function actSchoolpage(query) {
    return request({
        url: '/zcpa/actSchool/page',
        method: 'get',
        params: query
    })
}
//actProjectVolunteer/create
export function actSchoolcreate(data) {
    return request({
        url: '/zcpa/actProjectVolunteer/create',
        method: 'post',
        data: data
    })
}

export function schooldeleteByIds(ids) {
    return request({
        url: '/zcpa/actProjectVolunteer/deleteByIds',
        method: 'post',
        params: {
            ids: ids
        }
    })
}

export function selectByProjectId(projectId, targetId) {
    return request({
        url: '/zcpa/actProjectVolunteer/selectByProjectId',
        method: 'get',
        params: {
            projectId: projectId,
            targetId: targetId,

        }
    })
}

export function schoolbfwdeleteByIds(ids) {
    return request({
        url: '/zcpa/actProjectTarget/deleteByIds',
        method: 'post',
        params: {
            ids: ids
        }
    })
}

//情暖家庭，护安校园
export function familyCampusById(query) {
    return request({
        url: '/zcpa/actProject/findVolunteerList',
        method: 'get',
        params: query
    })
}
//其他类型对接
export function otherById(query) {
    return request({
        url: '/zcpa/actProjectVolunteer/page',
        method: 'get',
        params: query
    })
}
//已对接服务查询
export function serviceObject(query) {
    return request({
        url: '/zcpa/actProject/findVolunteerDockedList',
        method: 'get',
        params: query
    })
}

//活动推送创建
export function activeCreate(data) {
    return request({
        url: '/zcpa/actProjectTopic/create',
        method: 'post',
        data: data
    })
}
//活动推送获取
export function getActive(id) {
    return request({
        url: '/zcpa/actProjectTopic/getById',
        method: 'get',
        params: {
            id: id
        }
    })
}
//删除活动
export function deleteActive(id) {
    return request({
        url: '/zcpa/actProjectTopic/deleteByIds',
        method: 'post',
        params: {
            ids: id
        }
    })
}
//活动推送列表
export function activePage(query) {
    return request({
        url: '/zcpa/actProjectTopic/page',
        method: 'get',
        params: query
    })
}

//查看推送活动人员接口
export function seePersonnel(query) {
    return request({
        url: '/zcpa/actProjectTopic/topicVolunteerList',
        method: 'get',
        params: query
    })
}