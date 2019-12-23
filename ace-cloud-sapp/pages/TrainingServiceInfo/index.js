// pages/TrainingServiceInfo/index.js
var cfg = require("../../utils/config.js");
var app = getApp(); // 取得全局App
Page({

  /**
   * 页面的初始数据
   */
  data: {
    basicForm: {}, //基本信息数据
    scheduleList: [],//日程列表
  },
  //tab切换日常调取日程接口
  tabClick: function (event) {
    var that = this;
    var index = parseInt(event.detail.index); //获取当前点击的tabs的索引值
    if (index == 1) { //0是培训介绍， 1是日程安排
      app.request(cfg.schedulePageUrl, {
        serveCultivateId: this.data.id
      }, function (res) {
        console.log(res);
        if (res.status == 1) {
          that.setData({
            scheduleList: res.rows
          })
        }
      })
    }
  },
  //点击日程去详情
  scheduleClcik: function (e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.scheduleList[index].id;
    wx.navigateTo({
      url: '/pages/TrainingScheduleDetails/index?id=' + listId,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    var that = this;
    this.setData({
      id: options.id
    })
    var ids = this.data.id;
    app.request(cfg.selectByIdDetailsUrl, {
      id: ids
    }, function (res) {
      console.log(res);
      if (res.status == 1) {
        that.setData({
          basicForm: res.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})