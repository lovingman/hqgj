// pages/TrainingScheduleDetails/index.js
var cfg = require("../../utils/config.js");
var app = getApp(); // 取得全局App
Page({

  /**
   * 页面的初始数据
   */
  data: {
    basicForm: {},//数据容器
    specialityList: [],//领域数组
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
    app.request(cfg.scheduleGetByIdUrl, {
      id: ids
    }, function (res) {
      console.log(res);
      if (res.status == 1) {
        that.setData({
          basicForm: res.data,
          specialityList: res.data.baseOrganizationMember.speciality.split(',')
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