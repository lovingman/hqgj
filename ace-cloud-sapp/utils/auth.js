var request = require('request.js')
var cfg = require('config.js')

const Auth = {}

Auth.wxUserInfo = function (e) {
    let that = this;
    return new Promise(function (resolve, reject) {
        wx.login({
            success(res) {
                if (res.code) {
                    let args = {
                        iv: e.detail.iv,
                        encryptedData: e.detail.encryptedData,
                        signature: e.detail.signature,
                        rawData: e.detail.rawData,
                        jscode: res.code,
                        sysId: 'hqgj'
                    };
                    request.post(cfg.authority, args).then(response => {
                        let res = response.data;
                        if (res.status == '1') {
                            resolve(res);
                        } else {
                            reject(res);
                        }
                    }).catch(error => {
                        wx.showToast({
                            title: "授权失败，请重试",
                            icon: 'none',
                            duration: 2000
                        });
                    })
                } else {
                    console.log('登录失败！' + res.errMsg)
                }
            }
        })
    });
}

module.exports = Auth;