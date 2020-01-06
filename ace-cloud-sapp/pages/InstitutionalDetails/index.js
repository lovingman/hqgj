// pages/InstitutionalDetails/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: "", //id值
    orgArr: {}, //机构数组
  },
  //获取机构详情
  getByOrg: function() {
    let that = this;
    request.getJSON(cfg.baseOrganizationByIdUrl, {
      id: that.data.id
    }).then(res => {
      let e = res.data;
      if (e.status == 1) {
        that.setData({
          orgArr: e.data
        })
        console.log(that.data.orgArr)
      }
    })

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;
    that.data.id = options.id;
    that.getByOrg();

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