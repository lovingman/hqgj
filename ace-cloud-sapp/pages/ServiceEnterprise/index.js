// pages/Service/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        servicePack: []
    },
    getData() {
        let that = this;
        request.getJSON(cfg.businessPage, {
            pageSize: 100,
            pageNum: 1
        }).then(rst => {
            const res = rst.data;
            if (res.status == 1) {
                if (res.rows && res.rows.length > 0) {
                    that.setData({
                        servicePack: res.rows
                    })
                }
            }
        })
    },
    progressHandler(e){
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: `/pages/RegProgress/index?id=${id}`,
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        this.getData();
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