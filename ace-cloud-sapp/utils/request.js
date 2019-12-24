function wxPromisify(fn) {
    return function (obj = {}) {
        return new Promise((resolve, reject) => {
            obj.success = function (res) {
                resolve(res)
            }
            obj.fail = function (res) {
                //失败
                reject(res)
            }
            fn(obj)
        })
    }
}
//无论promise对象最后状态如何都会执行
Promise.prototype.finally = function (callback) {
    let P = this.constructor;
    return this.then(
        value => P.resolve(callback()).then(() => value),
        reason => P.resolve(callback()).then(() => { throw reason })
    );
};
/**
 * 微信请求get方法
 * url
 * data 以对象的格式传入
 */
function getJSON(url, data) {
    var getRequest = wxPromisify(wx.request)
    return getRequest({
        url: url,
        method: 'GET',
        data: data,
        header: {
            'Authorization': wx.getStorageSync('Authorization'),
        },
    })
}

/**
 * 微信请求post方法封装
 * url
 * data 以对象的格式传入
 */
function post(url, data) {
    var postRequest = wxPromisify(wx.request)
    return postRequest({
        url: url,
        method: 'POST',
        data: data,
        dataType: "json",
        header: {
            "content-type": "application/x-www-form-urlencoded",
            'Authorization': wx.getStorageSync('Authorization'),
        },
    })
}

module.exports = {
    post,
    getJSON
}