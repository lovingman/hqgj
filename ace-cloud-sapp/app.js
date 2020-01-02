var request = require('./utils/request.js');
var config = require('./utils/config.js');
var util = require('./utils/util.js');
App({
    onLaunch: function() {
        //调用API从本地缓存中获取数据
        var logs = wx.getStorageSync('logs') || []
        logs.unshift(Date.now())
        wx.setStorageSync('logs', logs)
        // 小程序主动更新
        this.set3dsessin();
        this.updateManager();
        this.getUserInfo();
    },
    getUserInfo: function(cb) {
        var that = this
        request.getJSON(config.getUserInfo).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                that.globalData.islogin = true;
                that.globalData.userInfo = res.data;
            }
        })
    },
    set3dsessin(){
        if (!wx.getStorageSync('WX-SESSION-ID')){
            wx.setStorageSync('WX-SESSION-ID', util.uuidGenerator());
        }
    },
    /*小程序主动更新
     */
    updateManager() {
        if (!wx.canIUse('getUpdateManager')) {
            return false;
        }
        const updateManager = wx.getUpdateManager();
        updateManager.onCheckForUpdate(function(res) {});
        updateManager.onUpdateReady(function() {
                wx.showModal({
                    title: '有新版本',
                    content: '新版本已经准备好，即将重启',
                    showCancel: false,
                    success(res) {
                        if (res.confirm) {
                            updateManager.applyUpdate()
                        }
                    }
                });
            }),
            updateManager.onUpdateFailed(function() {
                wx.showModal({
                    title: '更新提示',
                    content: '新版本下载失败',
                    showCancel: false
                })
            })
    },
    globalData: {
        userInfo: null,
        userObj: null,
        openid: '',
        islogin: false,
        isGetUserInfo: false,
        isGetOpenid: false

    }


    /*小程序主动更新
     */


})