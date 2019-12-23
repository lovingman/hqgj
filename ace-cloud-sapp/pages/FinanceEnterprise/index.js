// pages/FinanceEnterprise/index.js
var cfg = require("../../utils/config.js");
var app = getApp(); // 取得全局App
Page({

  /**
   * 页面的初始数据
   */
  data: {
    show: false,
    showId: 0, //服务项目选中当前状态
    radio: 0,
    detailsForm: {}, //详情数据容器
    priceForm: {}, //服务项目数据
    financeItemArr: [], //服务项目列表
    isTrue: true, //初始显示大小价格值
    isPrice: false, //点击服务项目后显示的价格值
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    var types = options.type;
    if (types == 2) { //2代表是财税管家
      that.setData({
        show: true,
      });
    }
    var ids = options.id; //获取列表传递过来的ID
    app.request(cfg.financeIdUrl, {
      id: ids
    }, function(res) {
      console.log(res);
      if (res.status == 1) {
        that.setData({
          detailsForm: res.data,
          financeItemArr: res.data.financeItemList
        })
      }
    })

  },
  //服务项目点击切换当前状态
  activeClick: function(e) {
    console.log(e)
    var that = this;
    var ids = e.currentTarget.dataset.id;
    var index = parseInt(e.currentTarget.dataset.index);
    var priceForm = that.data.financeItemArr[index];
    that.setData({
      showId: ids,
      priceForm: priceForm,
      isTrue: false, //隐藏初始价格
      isPrice: true //显示点击服务的价格
    });

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