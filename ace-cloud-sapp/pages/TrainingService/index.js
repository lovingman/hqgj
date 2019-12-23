// pages/TrainingService/index.js
var cfg = require("../../utils/config.js");
var app = getApp(); // 取得全局App
Page({

  /**
   * 页面的初始数据
   */
  data: {

    listArr: [],
    option1: [{
      text: '全部商品',
      value: 0
    },
    {
      text: '新款商品',
      value: 1
    },
    {
      text: '活动商品',
      value: 2
    }
    ],
    option2: [{
      text: '默认排序',
      value: 'a'
    },
    {
      text: '好评排序',
      value: 'b'
    },
    {
      text: '销量排序',
      value: 'c'
    }
    ],
    value1: 0,
    value2: 'a'
  },
  //请求page数据渲染列表
  getList: function () {
    var that = this;
    app.request(cfg.trainPageUrl, {}, function (res) {
      if (res.status == 1) {
        console.log(res);
        that.setData({
          listArr: res.rows
        })
      }
    })
  },
  //点击列表跳转详情传递ID
  listClcik: function (e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.listArr[index].id;
    wx.navigateTo({
      url: '/pages/TrainingServiceInfo/index?id=' + listId,
    })
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