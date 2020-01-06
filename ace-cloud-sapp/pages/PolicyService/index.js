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
    isload: false,
    loading: false,
    query: {
      pageSize: 8,
      pageNum: 1,
      status: '2', //2代表请求已发布的状态
    },
  },
  //请求page
  getList: function() {
    var that = this;
    that.showloading();
    request.getJSON(cfg.lawServePageUrl, that.data.query).then(res => {
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
          listArr: rows,
          listNumll: res.data.total
        })
      }
    })
  },
  //显示加载
  showloading() {
    console.log("11111111")
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
  //搜索
  onChange(e) {
    console.log(e);
    var that = this;
    var obj = {};
    obj.status = 2; //2代表发布成功的状态
    obj.title = e.detail;
    request.getJSON(cfg.lawServePageUrl, obj).then(res => {
      if (res.data.status == 1) {
        console.log(res)
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
    let that = this;
    that.data.query.pageNum = 1;
    that.data.listArr = [];
    that.getList();
    wx.stopPullDownRefresh();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    let that = this;
    if (that.data.isload) {
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