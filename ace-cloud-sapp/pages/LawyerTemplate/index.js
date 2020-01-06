// pages/FinanceTax/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    basicAnnexesArr: [], //下载模板数组
    headers: {}, //头部信息
    query: {
      pageSize: 10,
      pageNum: 1,
      type: 2, //法律模板类型
      id: "", //ID值
    },
    isload: false,
    loading: false,
  },
  //  根据传递获取数据
  getList: function() {
    let that = this;
    that.showloading();
    request.getJSON(cfg.basicAnnexUrl, that.data.query).then(res => {
      that.hideloading();

      let e = res.data;
      let len = e.rows ? e.rows.length : 0;
      if (len < that.data.query.pageSize) {
        that.setData({
          isload: true
        })
      }
      if (e.status == 1) {
        let rows = that.data.basicAnnexesArr.concat(res.data.rows);
        that.setData({
          basicAnnexesArr: rows
        })
      }
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
  //点击下载文件
  downloadFile: function(e) {
    console.log(e);
    wx.downloadFile({
      url: e.currentTarget.dataset.url,
      success: function(res) {
        console.log(res);
        const tempFilePath = res.tempFilePath;
        // 保存文件
        wx.saveFile({
          tempFilePath,
          success: function(res) {
            console.log(res);
            const savedFilePath = res.savedFilePath;
          },
          fail: function(err) {
            console.log(err);
            console.log('保存失败：', err)
          }
        });
      },
      fail: function(err) {
        console.log('下载失败：', err);
      },
    });
  },
  openFile(e) {
    console.log(e)
    wx.downloadFile({
      url: e.currentTarget.dataset.src,
      success: function(res) {
        const filePath = res.tempFilePath
        wx.openDocument({
          filePath: filePath,
          success: function(res) {
            console.log('打开文档成功')
          }
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;
    that.data.query.id = options.id;
    let obj = {};
    obj.title = options.title;
    obj.time = options.time;
    that.setData({
      headers: obj
    })
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
    that.data.basicAnnexesArr = [];
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