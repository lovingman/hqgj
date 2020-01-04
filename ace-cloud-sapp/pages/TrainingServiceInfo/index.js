// pages/TrainingServiceInfo/index.js
var cfg = require("../../utils/config.js");
var util = require("../../utils/util.js");
var request = require("../../utils/request.js");
import Toast from '../../vant/weapp/toast/toast';
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    basicForm: {}, //基本信息数据
    scheduleList: [], //日程列表
    isHides: true, //是否隐藏立即报名按钮
    isSuccess: false, //成功报名后是否显示
    enrollForm: {}, //报名企业数据
    basicAnnexList: [], //课件列表数组
  },
  //tab切换日常调取日程接口
  tabClick: function(event) {
    var that = this;
    var index = parseInt(event.detail.index); //获取当前点击的tabs的索引值
    if (index == 1) { //0是培训介绍， 1是日程安排
      request.getJSON(cfg.schedulePageUrl, {
        serveCultivateId: that.data.id
      }).
      then(res => {
        console.log(res);
        if (res.data.status == 1) {
          that.setData({
            scheduleList: res.data.rows
          })
        }
      })
    }
  },
  //点击报名先判断是否登录
  isSign: function(e) {
    var that = this;
    if (app.globalData.islogin) {
      request.postJSON(cfg.serveCultivateEnrollUrl, {
        serveCultivateId: that.data.id
      }).then(res => {
        if (res.data.status == 1) {
          Toast.fail(res.data.message);
          this.getById(); //更新当前请求数据
        }
      })
    } else {
      Toast.fail("未登录，请先登录");
      wx.navigateTo({
        url: '../SignIn/index?url=' + encodeURIComponent(that.data.getCurrentPageUrlWithArgs),
      })
    }
  },
  //点击报名企业跳转
  registrationEnterpriseClick: function() {
    wx.navigateTo({
      url: '/pages/TrainingManagement/index?id=' + this.data.id,
    })
  },
  //点击日程去详情
  scheduleClcik: function(e) {
    var index = parseInt(e.currentTarget.dataset.index);
    var listId = this.data.scheduleList[index].id;
    wx.navigateTo({
      url: '/pages/TrainingScheduleDetails/index?id=' + listId,
    })
  },
  //请求页面数据
  getById: function() {
    var that = this;
    request.getJSON(cfg.selectByIdDetailsUrl, {
      id: that.data.id
    }).then(res => {
      console.log(res);
      if (res.data.status == 1) {
        that.setData({
          basicForm: res.data.data,
          basicAnnexList: res.data.data.basicAnnexList
        })
      }
    })
    request.getJSON(cfg.enrollPage, {
      serveCultivateId: that.data.id
    }).then(res => {
      console.log(res);
      if (res.data.status == 1) {
        that.setData({
          enrollForm: res.data.rows
        })
      }
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
    var that = this;
    that.setData({
      id: options.id,
      getCurrentPageUrlWithArgs: util.getCurrentPageUrlWithArgs()
    })
    this.getById();
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