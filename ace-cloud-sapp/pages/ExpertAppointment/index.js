// pages/ExpertAppointment/index.js
var cfg = require("../../utils/config.js");
var util = require("../../utils/util.js");
var request = require("../../utils/request.js");
import Dialog from '../../vant/weapp/dialog/dialog';
import Toast from '../../vant/weapp/toast/toast';
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: "", //id值
    basicForm: {}, //数据容器
    specialityList: [], //领域数组
    expertId: '', //专家ID
    getCurrentPageUrlWithArgs: "", //当前页面URL+参数
  },
  //获取数据
  getById: function() {
    var that = this;
    request.getJSON(cfg.financeIdUrl, {
      id: that.data.id
    }).then(res => {
      console.log(res);
      if (res.data.status == 1) {
        that.setData({
          basicForm: res.data.data,
          expertId: res.data.data.baseOrganizationMember.id,
          specialityList: res.data.data.baseOrganizationMember.speciality.split(',')
        })
      }
    })
  },
  //专家预约
  expertClick: function() {
    var that = this;
    let basicForm = that.data.basicForm;
    if (app.globalData.islogin) {
      if (app.globalData.userInfo.companyId != null) {
        if (basicForm.surplusQuota != 0) {
          Dialog.confirm({
            title: '专家确认',
            message: '是否预约该专家为您服务'
          }).then(() => {
            request.postJSON(cfg.financeOrder, {
              financeId: that.data.id,
              type: 3,
              contactId: that.data.expertId
            }).then(res => {
              if (res.data.status == 1) {
                Toast.fail("预约" + res.data.message);
                this.getById();
              }
            })
          }).catch(() => {
            Toast.fail("已取消当前操作");
          });
        } else {
          Toast.fail("预约名额已满");
        }
      } else {
        Dialog.alert({
          message: '未绑定企业，请前往个人中心绑定'
        }).then(() => {
          // on close
        })
      }

    } else {
      Dialog.alert({
        message: '未登录，请先登录'
      }).then(() => {
        // on close
        wx.navigateTo({
          url: '../SignIn/index?url=' + encodeURIComponent(that.data.getCurrentPageUrlWithArgs),
        })
      })
    }

  },

  //拨打电话
  telePhoneClick: function(e) {
    console.log(e)
    var that = this;
    var phone = e.currentTarget.dataset.id;
    that.setData({
      phone: phone
    })
    if (phone) {
      wx.makePhoneCall({
        phoneNumber: that.data.phone
      })
    } else {
      return false
    }
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
    that.getById();
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