// pages/FinanceTax/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0,
    listArr: [], //列表容器
    index: 0, //tab索引值
    type: 1, //1代理记账，2财税管家
  },
  //请求page数据渲染列表
  getList: function () {
    var that = this;
    console.log(that.data.index);
    if (that.data.index == 0) {
      that.setData({
        type: 1
      })
    }
    if (that.data.index == 1) {
      that.setData({
        type: 2
      })
    }
    request.getJSON(cfg.financePageUrL, {
      type: that.data.type
    }).then(res => {
      if (res.data.status == 1) {
        console.log(res);
        that.setData({
          listArr: res.data.rows
        })
      }
    })
  },
  //tab点击切换
  tabClick: function (event) {
    var that = this;
    var index = parseInt(event.detail.index); //获取当前点击的tabs的索引值
    that.setData({
      index: index
    })
    this.getList();
  },
  //点击列表跳转详情
  listClcik: function (e) {
    if (this.data.type == 1) {
      var agentIndex = parseInt(e.currentTarget.dataset.index); //获取当前点击的tabs的索引值
      var agentId = this.data.listArr[agentIndex].id;
      wx.navigateTo({
        url: '/pages/FinanceEnterprise/index?id=' + agentId,
      })
    }
    if (this.data.type == 2) {
      var financeIndex = parseInt(e.currentTarget.dataset.index); //获取当前点击的tabs的索引值
      var financeId = this.data.listArr[financeIndex].id;
      wx.navigateTo({
        url: '/pages/FinanceEnterprise/index?id=' + financeId + '&type=' + this.data.type,
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getList();
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