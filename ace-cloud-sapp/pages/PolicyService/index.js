// pages/PolicyService/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    listArr: [], //page 数组
    listNumll: "", //长度
  },
  //请求page
  getList: function() {
    var that = this;
    //2代表请求已发布的状态
    request.getJSON(cfg.lawServePageUrl, {
      status: 2
    }).then(res => {
      if (res.data.status == 1) {
        var listLength = res.data.rows.length;
        that.setData({
          listArr: res.data.rows,
          listNumll: res.data.total
        })
      }
    })
  },
  //点击列表跳转详情
  listClcik: function(e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.listArr[index].id;
    wx.navigateTo({
      url: '/pages/PolicyServiceDetail/index?id=' + listId,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getList();
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