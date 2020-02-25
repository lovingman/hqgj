// pages/appealDetail/index.js
const app = getApp()
var util = require("../../utils/util.js");
var request = require('../../utils/request.js');
var cfg = require('../../utils/config.js');
import Toast from '../../vant/weapp/toast/toast';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: '',
        info:{}
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that = this;
        that.data.id = options.id;
        that.appealDetail();
    },
    appealDetail() {
        let that = this;
        request.getJSON(cfg.getAppealgetById, {
            id:that.data.id
        }).then(rst => {
            const res = rst.data;
            if (res.status == 1) {
               that.setData({
                   info:res.data
               })
            }
        })
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