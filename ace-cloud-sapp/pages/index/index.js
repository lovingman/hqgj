//index.js
//获取应用实例
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
const app = getApp()

Page({
  data: {
    navigates: [{
        text: '创业服务包',
        imgUrl: '/assets/image/icon1-2.png',
        path: '../RegEnterprise/index'
      },
      {
        text: '财税服务包',
        imgUrl: '/assets/image/icon1-3.png',
        path: '../FinanceTax/index'
      },
      {
        text: '培训提升服务',
        imgUrl: '/assets/image/icon1-5.png',
        path: '../TrainingService/index'
      },
      {
        text: '专家问诊',
        imgUrl: '/assets/image/icon1-4.png',
        path: '../ExpertConsultation/index'
      },

      {
        text: '法律服务',
        imgUrl: '/assets/image/icon1-6.png',
        path: '../LawyerService/index'
      },
      {
        text: '政策服务',
        imgUrl: '/assets/image/icon1-7.png',
        path: '../PolicyService/index'
      }
    ],
    lawServeList: [], //政策服务page
    trainList: [], //培训page
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })

  },
  //请求政策page
  getLawServe: function() {
    var that = this;
    request.getJSON(cfg.lawServePageUrl).then(res => {
      if (res.data.status == 1) {
        that.setData({
          lawServeList: res.data.rows
        })
        console.log(that.data.lawServeList)
      }
    })
  },
  //请求培训page
  getTrain: function() {
    var that = this;
    request.getJSON(cfg.trainPageUrl, {
      status: 1 //1.进行中
    }).then(res => {
      if (res.data.status == 1) {
        that.setData({
          trainList: res.data.rows
        })
        console.log(that.data.trainList)
      }
    })
  },
  //点击政策服务去详情
  lawServeClcik: function(e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.lawServeList[index].id;

    wx.navigateTo({
      url: '/pages/PolicyServiceDetail/index?id=' + listId,
    })
  },
  //点击培训跳转培训详情
  trainClick: function(e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.trainList[index].id;

    wx.navigateTo({
      url: '/pages/TrainingServiceInfo/index?id=' + listId,
    })
  },
  getUser() {
    request.getJSON(cfg.homePage, {}).then(rst => {
      const res = rst.data;
      if (res.status == 1) {
        app.globalData.userObj = res.data;
      }
    })
  },
  onLoad: function() {
    this.getLawServe(); //请求政策服务page
    this.getTrain(); //请求培服务page
    // this.getUser();
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  onPullDownRefresh() {
    this.getLawServe(); //请求政策服务page
    this.getTrain(); //请求培服务page
    wx.stopPullDownRefresh();
  }
})