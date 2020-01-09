// pages/FinanceTax/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        active: 0,
        listArr: [], //列表容器
        index: 0, //tab索引值
        query: {
            pageSize: 10,
            pageNum: 1,
            type: 1, //1代理记账，2财税管家
            status: '1,3', //状态  0-待审核 1-审核通过 2-未通过 3-已上线 4-已下线
        },
        isload: false,
        loading: false,
    },
    //请求page数据渲染列表
    getList: function() {
        var that = this;

        if (that.data.index == 0) {
            that.data.query.type = 1;
        }
        if (that.data.index == 1) {
            that.data.query.type = 2;
        }
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
    //tab点击切换
    tabClick: function(event) {
        var that = this;
        var index = parseInt(event.detail.index); //获取当前点击的tabs的索引值
        that.setData({
            index: index
        })
        that.data.listArr = [];
        that.data.query.pageNum = 1;
        this.getList();
    },
    //点击列表跳转详情
    listClcik: function(e) {
        let that = this;
        if (that.data.query.type == 1) {
            var agentIndex = parseInt(e.currentTarget.dataset.index); //获取当前点击的tabs的索引值
            var agentId = this.data.listArr[agentIndex].id;
            wx.navigateTo({
                url: '/pages/FinanceEnterprise/index?id=' + agentId + '&type=' + that.data.query.type,
            })
        }
        if (that.data.query.type == 2) {
            var financeIndex = parseInt(e.currentTarget.dataset.index); //获取当前点击的tabs的索引值
            var financeId = this.data.listArr[financeIndex].id;
            wx.navigateTo({
                url: '/pages/FinanceEnterprise/index?id=' + financeId + '&type=' + that.data.query.type,
            })
        }
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
        if (that.data.index != 1) {
            that.data.query.pageNum = 1;
            that.data.listArr = [];
            that.data.query.type = 2;
            that.getList();
            wx.stopPullDownRefresh();
        } else {
            that.data.query.type = 1;
            that.data.query.pageNum = 1;
            that.data.listArr = [];
            that.getList();
            wx.stopPullDownRefresh();
        }
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