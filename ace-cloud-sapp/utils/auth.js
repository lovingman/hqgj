var request = require('request.js')
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

// 同意获取用户信息
Auth.agreeGetUser = function(e, wxLoginInfo, authFlag) {
    console.log(e);
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
                sysId:'hqgj'
            }

            let userInfo = userInfoDetail.userInfo;
            userInfo.isLogin = true;
            data.userInfo = userInfo;
            var url = 'http://192.168.2.124/wxms/www/mini/authority';
            // var postOpenidRequest = request.post(url, args);
            //获取openid
            postOpenidRequest.then(response => {
                if (response.data.status == '200') {
                    //console.log(response.data.openid)
                    console.log("授权登录获取成功");
                    data.openid = response.data.openid;
                    var userLevel = {};
                    if (response.data.userLevel) {
                        userLevel = response.data.userLevel;
                    } else {
                        userLevel.level = "0";
                        userLevel.levelName = "订阅者";
                    }
                    data.userLevel = userLevel;
                    data.errcode = "";
                    resolve(data);

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
// 微信登录
Auth.wxLogin = function() {
    return new Promise(function(resolve, reject) {
        wx.login({
            success: function(res) {
                console.log(res);
                let args = {};
                args.js_code = res.code;
                resolve(args);
            },
            fail: function(err) {
                console.log(err);
                reject(err);
            }
        });
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