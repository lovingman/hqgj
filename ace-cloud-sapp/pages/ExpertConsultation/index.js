// pages/ExpertConsultation/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        listArr: [], //列表容器
        query: {
            pageSize: 10,
            pageNum: 1,
            status: 3, //状态  0-待审核 1-审核通过 2-未通过 3-已上线 4-已下线
            type: 3, //类型 3专家问诊
        },
    },
    //获取page列表
    getList: function() {
        var that = this;
        that.showloading();
        request.getJSON(cfg.financePageUrL, that.data.query).then(res => {
            that.hideloading();
            let e = res.data;
            let len = e.rows ? e.rows.length : 0;
            if (len < that.data.query.pageSize) {
                that.setData({
                    isload: true
                })
            }
            if (e.status == 1) {
                console.log(res);
                let rows = that.data.listArr.concat(res.data.rows);
                that.setData({
                    listArr: rows
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
    //点击列表跳转详情
    listClcik: function(e) {
        var index = parseInt(e.currentTarget.dataset.index);
        var listId = this.data.listArr[index].id;
        wx.navigateTo({
            url: '/pages/ExpertAppointment/index?id=' + listId,
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