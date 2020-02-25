// pages/Service/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        load: false,
        nomore: false,
        servicePack: [],
        query: {
            pageSize: 10,
            pageNum: 1
        }
    },
    getData() {
        let that = this;
        that.showloading();
        request.getJSON(cfg.getAppealList, that.data.query).then(rst => {
            that.hideloading();
            let e = rst.data;
            let len = e.rows ? e.rows.length : 0;
            if (len < that.data.query.pageSize) {
                that.setData({
                    nomore: true
                })
            }
            if (e.status == 1) {
                let list = that.data.servicePack.concat(e.rows);
                that.setData({
                    servicePack: list
                })
            }
        })
    },
    // 显示正在加载
    showloading() {
        this.setData({
            load: true
        })
    },
    // 隐藏正在加载
    hideloading() {
        this.setData({
            load: false
        })
    },
    progressHandler(e) {
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: `/pages/RegProgress/index?id=${id}`,
        })
    },
    companyDetail(e) {
        let id = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: `/pages/RegProcess/index?flag=true&id=${id}`
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
        let that = this;
        that.data.query.pageNum = 1;
        that.data.servicePack = [];
        that.getData();
        wx.stopPullDownRefresh();
        that.setData({
            nomore:false
        })
    },  

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        let that = this;
        if (that.data.nomore){
            return;
        }
        that.data.query.pageNum++;
        that.getData();
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    }
})