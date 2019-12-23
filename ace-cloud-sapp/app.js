//app.js
var cfg = require("/utils/config.js");
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  request: function (url, data, success, fail, complete) { //get请求方法
    let _this = this;
    var _url = url,
      _data = data,
      _success = success,
      _fail = fail,
      _complete = complete;
    wx.showNavigationBarLoading();
    wx.showLoading({
      title: "请求中"
    });
    wx.request({
      url: url,
      data: data,
      method: "GET",
      dataType: "json",
      header: {
        'Authorization': wx.getStorageSync('Authorization'),
      },
      success: function (res) {
        // console.log(res)
        wx.hideNavigationBarLoading() //完成停止加载
        wx.hideLoading();
        if (res.data.status == 1) {
          wx.showToast({
            title: res.data.message,
            icon: 'none',
            duration: 2000
          });
        }
        if (typeof _success == "function") {
          _success(res.data);
        }
      },
      fail: function (res) {
        console.log(res)
        wx.hideNavigationBarLoading() //完成停止加载
        wx.hideLoading();
        if (res.data.status == 1) {
          wx.showToast({
            title: res.data.message,
            icon: 'none',
            duration: 2000
          });
        }
        if (typeof _fail == "function") {
          _fail(res);
        }
      },
      complete: function (res) {
        if (typeof _complete == "function") {
          _complete(res);
        }
      }
    });
  },
  post: function (url, data, success, fail, complete) {//post json格式
    let _this = this;
    var _url = url,
      _data = data,
      _success = success,
      _fail = fail,
      _complete = complete;
    wx.showNavigationBarLoading();
    wx.showLoading({
      title: "请求中"
    });
    wx.request({
      url: url,
      data: data,
      method: "post",
      dataType: "json",
      header: {
        'Authorization': wx.getStorageSync('Authorization'),
        "Content-Type": "application/json",
      },
      success: function (res) {
        wx.hideNavigationBarLoading() //完成停止加载
        wx.hideLoading();
        if (res.data.status == 1) {
          wx.showToast({
            title: res.data.message,
            icon: 'none',
            duration: 2000
          });
        }
        if (typeof _success == "function") {
          _success(res.data);
        }
      },
      fail: function (res) {
        wx.hideNavigationBarLoading() //完成停止加载
        wx.hideLoading();
        if (res.data.status == 1) {
          wx.showToast({
            title: res.data.message,
            icon: 'none',
            duration: 2000
          });
        }
        if (typeof _fail == "function") {
          _fail(res);
        }
      },
      complete: function (res) {
        if (typeof _complete == "function") {
          _complete(res);
        }
      }
    });
  },
  globalData: {
    userInfo: null
  }
})