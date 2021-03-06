function wxPromisify(fn) {
    return function(obj = {}) {
        return new Promise((resolve, reject) => {
            obj.success = function(res) {
                if (res.data.status != 1) {
                    const app = getApp()
                    app.globalData.islogin = false;
                }
                resolve(res)
            }
            obj.fail = function(res) {
                //失败
                reject(res)
            }
            fn(obj)
        })
    }
}
//无论promise对象最后状态如何都会执行
Promise.prototype.finally = function(callback) {
    let P = this.constructor;
    return this.then(
        value => P.resolve(callback()).then(() => value),
        reason => P.resolve(callback()).then(() => {
            throw reason
        })
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
            'WX-SESSION-ID': wx.getStorageSync('WX-SESSION-ID'),
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
        header: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
            'Authorization': wx.getStorageSync('Authorization'),
            'WX-SESSION-ID': wx.getStorageSync('WX-SESSION-ID'),
            "text":url,
        },
    })
}
//postJSON请求
function postJSON(url, data) {
    var postRequest = wxPromisify(wx.request)
    return postRequest({
        url: url,
        method: 'POST',
        data: data,
        dataType: "json",
        header: {
            "Content-Type": "application/json",
            'Authorization': wx.getStorageSync('Authorization'),
            'WX-SESSION-ID': wx.getStorageSync('WX-SESSION-ID'),
        },
    })
}
module.exports = {
    post,
    getJSON,
    postJSON
}