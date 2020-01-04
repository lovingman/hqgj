// pages/TrainingService/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
const app = getApp()
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
    query: {
      pageSize: 8,
      pageNum: 1,
      status: '1,3',
    },
    isload: false,
    loading: false,
    value1: 0,
    value2: 'a',
    quotaShow: true, //是否显示名额
  },
  //请求page数据渲染列表
  getList: function() {
    var that = this;
    that.showloading();
    request.getJSON(cfg.trainPageUrl, that.data.query).then(res => {
      that.hideloading();
      let e = res.data;
      let len = e.rows ? e.rows.length : 0;
      if (len < that.data.query.pageSize) {
        that.setData({
          isload: true
        })
      }
      if (e.status == 1) {
        let rows = that.data.listArr.concat(res.data.rows);
        that.setData({
          listArr: rows
        })
      }
    })
  },
  //点击列表跳转详情传递ID
  listClcik: function(e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.listArr[index].id;
    wx.navigateTo({
      url: '/pages/TrainingServiceInfo/index?id=' + listId,
    })
  },
  //显示加载
  showloading() {
    let that = this;
    that.setData({
      loading: true
    })
  },
  //隐藏加载
  hideloading() {
    let that = this;
    that.setData({
      loading: false
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;
    that.getList();
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
    // this.getList();
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
    let that = this;
    that.data.query.pageNum = 1;
    that.data.listArr = [];
    console.log(that.data.listArr);
    that.getList();
    wx.stopPullDownRefresh();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    let that = this;
    that.data.query.pageNum++;
    that.getList();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})