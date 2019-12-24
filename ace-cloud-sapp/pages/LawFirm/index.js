// pages/LawFirm/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: "", //id值
    basicForm: {}, //基本信息
    orgArr:[],//成员数组
  },
  //tab切换
  tabClick: function(event) {
    var that = this;
    var index = parseInt(event.detail.index); //获取当前点击的tabs的索引值
    if (index == 1) { //0 简介， 1律师成员
      request.getJSON(cfg.baseOrganizationMemberUrl, {
        orgId: that.data.id
      }).
      then(res => {
        console.log(res);
        if (res.data.status == 1) {
          that.setData({
            orgArr: res.data.rows
          })
        }
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.setData({
      id: options.id
    })
    request.getJSON(cfg.baseOrganizationByIdUrl, {
      id: that.data.id
    }).then(res => {
      console.log(res);
      if (res.data.status == 1) {
        that.setData({
          basicForm: res.data.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})