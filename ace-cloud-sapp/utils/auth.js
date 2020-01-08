var request = require('request.js')
var config = require('config.js')
// var Api = require('api.js');

const Auth = {}

/**检查session
 * flag：是不是现在开始登录 'isLoginNow' yes
 */
Auth.checkSession = function(appPage, flag) {
    // 获取openid
    let openid = wx.getStorageSync('openid');
    if (!openid) {
        // 如果openId 为空。
        if ('isLoginNow' == flag) {
            // 初始化登录用户信息
            var userInfo = {
                avatarUrl: "../../images/gravatar.png",
                nickName: "登录",
                isLogin: false
            }
            appPage.setData({
                isLoginPopup: true,
                userInfo: userInfo
            });
        }

    }
}
// 检查登录
Auth.checkLogin = function(appPage) {
    // 微信登录信息
    let wxLoginInfo = wx.getStorageSync('wxLoginInfo');
    wx.checkSession({
        success: function() {
            if (!wxLoginInfo.js_code) {
                Auth.wxLogin().then(res => {
                    appPage.setData({
                        wxLoginInfo: res
                    });
                    wx.setStorageSync('wxLoginInfo', res);
                    console.log('checkSession_success_wxLogins');
                })
            }
        },
        fail: function() {
            Auth.wxLogin().then(res => {
                appPage.setData({
                    wxLoginInfo: res
                });
                wx.setStorageSync('wxLoginInfo', res);
                console.log('checkSession_fail_wxLoginfo');
            })
        }
    })
}
Auth.checkAgreeGetUser = function(e, app, appPage, authFlag) {
    let wxLoginInfo = wx.getStorageSync('wxLoginInfo');
    if (wxLoginInfo.js_code) {
        Auth.agreeGetUser(e, wxLoginInfo, authFlag).then(res => {
            if (res.errcode == "") {
                wx.setStorageSync('userInfo', res.userInfo);
                wx.setStorageSync('openid', res.openid);
                wx.setStorageSync('userLevel', res.userLevel);
                appPage.setData({
                    openid: res.openid
                });
                appPage.setData({
                    userInfo: res.userInfo
                });
                appPage.setData({
                    userLevel: res.userLevel
                });
            } else {
                var userInfo = {
                    avatarUrl: "../../images/gravatar.png",
                    nickName: "点击登录",
                    isLogin: false
                }
                appPage.setData({
                    userInfo: userInfo
                });
                console.log("用户拒绝了授权");
            }
            appPage.setData({
                isLoginPopup: false
            });

        })
    } else {
        console.log("登录失败");
        wx.showToast({
            title: '登录失败',
            mask: false,
            duration: 1000
        });

    }
}

Auth.wxLogin = function() {
    return new Promise(function(resolve, reject) {})
}

// 同意获取用户信息
Auth.agreeGetUser = function(e, wxLoginInfo, authFlag) {
    return new Promise(function(resolve, reject) {
        let args = {};
        let data = {};
        if (authFlag == '0' && e.detail.errMsg == 'getUserInfo:fail auth deny') {
            args.errcode = e.detail.errMsg;
            args.userInfo = {
                isLogin: false
            }
            args.userSession = "";
            resolve(args);
            return;
        }
        var userInfoDetail = {};
        if (authFlag == '0') //未授权过,通过按钮授权
        {
            userInfoDetail = e.detail;
        } else if (authFlag == '1') //已经授权过，直接通过wx.getUserInfo获取
        {
            userInfoDetail = e;
        }
        if (userInfoDetail && userInfoDetail.userInfo) {
            args = {
                iv: userInfoDetail.iv,
                encryptedData: userInfoDetail.encryptedData,
                signature: userInfoDetail.signature,
                rawData: userInfoDetail.rawData,
                jscode: wxLoginInfo.js_code,
                sysId: 'hqgj'
            }

            let userInfo = userInfoDetail.userInfo;
            userInfo.isLogin = true;
            data.userInfo = userInfo;
            var url = 'http://192.168.2.124/wxms/www/mini/';
            //获取openid
            request.post(url, args).then(response => {
                let res = response.data;
                if (res.status == '1') {
                    resolve(res.data);
                } else {
                    console.log(response);
                    reject(response);
                }
            }).catch(function(error) {
                console.log('error: ' + error);
                reject(error);
            })

            // Auth.userLogin(args,api).then(userSession=>{
            //     args.userSession=userSession;
            //     args.errcode ="";
            //     resolve(args);
            // }).catch(function (error) {
            //     console.log('error: ' + error);                        
            //     reject(error);
            // })
        } else {
            args.errcode = "error";
            args.userInfo = {
                isLogin: false
            };
            args.userSession = "";
            resolve(args);
        }
    })
}
Auth.setUserInfoData = function(appPage) {
    if (!appPage.data.openid) {
        appPage.setData({
            userInfo: wx.getStorageSync('userInfo'),
            openid: wx.getStorageSync('openid'),
            userLevel: wx.getStorageSync('userLevel')
        })

    }

}


Auth.wxUserInfo = function(e) {
    console.log(e);
    return new Promise(function(resolve, reject) {
        var userInfo = e.detail.userInfo;
        // 判断用户是否授权
        if (userInfo != null && userInfo != undefined) { //授权
            wx.checkSession({
                success: function() {
                    //session 未过期，并且在本生命周期一直有效
                    wx.getUserInfo({
                        success(res){
                            console.log("wx.getUserInfo");
                            // return;
                            let args = {
                                iv: e.detail.iv,
                                encryptedData: e.detail.encryptedData,
                                signature: e.detail.signature,
                                rawData: e.detail.rawData,
                                jscode: res.code,
                                sysId: 'hqgj'
                            };
                            request.post(config.authority, args).then(response => {
                                let res = response.data;
                                if (res.status == '1') {
                                    resolve(res.data);
                                } else {
                                    reject(res);
                                }
                            }).catch(error => {
                                wx.showToast({
                                    title: "授权失败，请重试",
                                    icon: 'none',
                                    duration: 2000
                                });
                                reject(error);
                            })
                        },
                        fail(err){
                            console.log(err);
                            reject(err);
                        }
                    })
                },
                fail: function() {
                    wx.login({
                        success: function (res) {
                            console.log("wx.login")
                            // return;
                            let args = {
                                iv: e.detail.iv,
                                encryptedData: e.detail.encryptedData,
                                signature: e.detail.signature,
                                rawData: e.detail.rawData,
                                jscode: res.code,
                                sysId: 'hqgj'
                            };
                            request.post(config.authority, args).then(response => {
                                let res = response.data;
                                if (res.status == '1') {
                                    resolve(res.data);
                                } else {
                                    reject(error);
                                }
                            }).catch(error => {
                                wx.showToast({
                                    title: "授权失败，请重试",
                                    icon: 'none',
                                    duration: 2000
                                });
                                reject(error);
                            })
                        },
                        fail: function (err) {
                            console.log(err);
                            reject(err);
                        }
                    });
                }
            });
        } else { //不授权
            console.log(333, '执行到这里，说明拒绝了授权')
            wx.showToast({
                title: "为了您更好的体验,请先同意授权",
                icon: 'none',
                duration: 2000
            });
        }
    })
}




Auth.logout = function(appPage) {
    appPage.setData({
        openid: '',
        userLevel: {},
        userInfo: {},
        wxLoginInfo: {}
    })
    wx.removeStorageSync('userInfo');
    wx.removeStorageSync('openid');
    wx.removeStorageSync('userLevel');
    wx.removeStorageSync('wxLoginInfo');
}

module.exports = Auth;