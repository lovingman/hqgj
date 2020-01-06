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
        text: '全部状态',
        value: '1,3'
      },
      {
        text: '进行中',
        value: 1,
      },
      {
        text: '已结束',
        value: 3
      }
    ],
    option2: [{
        text: '按最新',
        value: 'createDate'
      },
      {
        text: '有名额',
        value: 'surplusQuota'
      }
    ],
    query: {
      pageSize: 10,
      pageNum: 1,
      status: '1,3',
    },
    isload: false,
    loading: false,
    status: '1,3',
    orderBy: 'createDate',
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
      console.log(e.rows);
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
  //状态选择事件
  changStatus: function(e) {
    console.log(e);
    let that = this;
    that.showloading();
    that.data.query.sord = 'desc';
    that.data.query.status = e.detail;
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
        that.setData({
          listArr: e.rows
        })
      }
    })
  },
  //最新或者名额选择
  changOrderBy: function(e) {
    console.log(e);
    let that = this;
    that.showloading();
    that.data.query.orderBy = e.detail;
    that.data.query.sord = 'desc';
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
        that.setData({
          listArr: e.rows
        })
      }
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
    if (thta.data.isload) {
      return;
    }
    that.data.query.pageNum++;
    that.getList();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})