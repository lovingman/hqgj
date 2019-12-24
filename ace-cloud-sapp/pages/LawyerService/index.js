// pages/FinanceTax/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0, //当前tab索引
    listArr: [], //page数组
    type: 1, //类型 1 律师事务所 2，会计 3 培训
    basicAnnexArr: [], //法律模板数组
    basicLength: "", //文件数量
  },
  //请求page
  getList: function() {
    var that = this;
    request.getJSON(cfg.baseOrganizationUrl, {
      type: that.data.type
    }).then(res => {
      if (res.data.status == 1) {
        console.log(res)
        that.setData({
          listArr: res.data.rows,
        })
      }
    })
  },
  //tab切换
  tabClick: function(event) {
    var that = this;
    var types = 2;
    var index = parseInt(event.detail.index); //获取当前点击的tabs的索引值
    if (index == 1) { //0 律师事务所  1法律模板
      request.getJSON(cfg.basicAnnexUrl, {
        type: types
      }).
      then(res => {
        console.log(res);
        if (res.data.status == 1) {
          var basicAnnexLength = res.data.rows.length;
          console.log(basicAnnexLength)
          that.setData({
            basicAnnexArr: res.data.rows,
            basicLength: basicAnnexLength
          })
        }
      })
    }
  },
  //点击跳转详情
  listClcik: function(e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.listArr[index].id;
    wx.navigateTo({
      url: '/pages/LawFirm/index?id=' + listId,
    })
  },
  //点击下载文件
  downloadFile: function(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    var fileURL = that.data.basicAnnexArr[index].fileURL;
    that.setData({
      flieUrl: fileURL
    });
    wx.downloadFile({
      url: that.data.flieUrl,
      success: function(res) {
        console.log(res);
      },
      fail: function(res) {
        console.log('文件下载失败');
      },
      complete: function(res) {},
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