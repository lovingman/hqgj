import request from '@/utils/request'
// export function getAreaTree(query) {
//   return request({
//     url: '/portal/system/getAreaTree',
//     method: 'get',
//     params: query
//   })
// }
// export function initProvinceCity(query) {
//   return request({
//     url: '/portal/system/getAreaTree?pid=00&type=4',
//     method: 'get',
//     params: query
//   })
// }
// export function getDict(ids) {
//   return request({
//     url: '/portal/dict/getByCategoryIds',
//     method: 'get',
//     params: {categoryIds:ids}
//   })
// }
// export function fileUpload(fileobj,url) {
//   let param = new FormData()
//   param.append('file',fileobj.file)
//   return request({
//     method: 'post',
//     url: url,
//     headers: {'Content-Type':'multipart/form-data'},
//     data: param
//   })
// }
//
// export function filedown(query,url) {
//   return request({
//     url: url,
//     method: 'get',
//     params: query,
//     responseType: 'blob'
//   })
// }
// export function getPAreaCode(areaCode) {
//   return request({
//     url: '/portal/system/getPAreaCode',
//     method: 'get',
//     params:{areaCode:areaCode}
//   })
// }
//
// export function fileUpImg(url,data) {
//   console.log(url)
//   console.log(data)
//   return request({
//     url: url,
//     method: 'post',
//     data: data,
//     transformRequest: [function (data) {
//       let ret = ''
//       for (let it in data) {
//         ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
//       }
//       return ret
//     }],
//     headers: {
//       'Content-Type': 'application/x-www-form-urlencoded'
//     }
//   })
// }
export function getAreaTree(query) {
  return request({
    url: '/hqgj-portal/system/getAreaTree',
    method: 'get',
    params: query
  })
}
export function initProvinceCity(query) {
  return request({
    url: '/hqgj-portal/system/getAreaTree?pid=00&type=4',
    method: 'get',
    params: query
  })
}
export function getDict(ids) {
  return request({
    url: '/hqgj-portal/dict/getByCategoryIds',
    method: 'get',
    params: {
      categoryIds: ids
    }
  })
}

export function fileUpload(fileobj, url) {
  let param = new FormData()
  param.append('file', fileobj.file)
  return request({
    method: 'post',
    url: url,
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: param
  })
}

export function filedown(query, url) {
  return request({
    url: url,
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
export function getPAreaCode(areaCode) {
  return request({
    url: '/hqgj-portal/system/getPAreaCode',
    method: 'get',
    params: {
      areaCode: areaCode
    }
  })
}

export function fileUpImg(url, data) {
  return request({
    url: url,
    method: 'post',
    data: data,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}


//获取当前登录用户信息
export function getUser() {
  return request({
    url: '/hqgj/reg/selectUserInfo',
    method: 'get',
  })
}

//获取服务机构信息
export function lecturerMechanism(data) {
  return request({
    url: '/hqgj/baseOrganization/page',
    method: 'get',
    params: {
      type: data
    }
  })
}

//获取服务机构人员信息
export function lecturerPage(id) {
  return request({
    url: '/hqgj/baseOrganizationMember/page',
    method: 'get',
    params: {
      orgId: id
    }
  })
}

// 文件上传
export function uploadFile(data) {
  return request({
    url: '/hqgj-portal/www/uploadFile',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    }
  })
}