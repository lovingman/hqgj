// pages/ExpertConsultation/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    type: 3, //类型 3专家问诊
    listArr: [], //列表容器
  },
  //获取page列表
  getList: function() {
    var that = this;
    request.getJSON(cfg.financePageUrL, {
      type: that.data.type,
      status: 3, //状态  0-待审核 1-审核通过 2-未通过 3-已上线 4-已下线
    }).then(res => {
      console.log(res);
      if (res.data.status == 1) {
        that.setData({
          listArr: res.data.rows
        })
        console.log(that.data.listArr);
      }
    })
  },
  //点击列表跳转详情
  listClcik: function(e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.listArr[index].id;
    wx.navigateTo({
      url: '/pages/ExpertAppointment/index?id=' + listId,
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