// pages/FinanceTax/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        active: 0, //当前tab索引
        listArr: [], //page数组
        index: "", //当前tab索引
        basicAnnexArr: [], //法律模板数组
        basicLength: "", //文件数量
        query: {
            pageSize: 10,
            pageNum: 1,
            type: 1, //类型 1 律师事务所 2，会计 3 培训
            title: "", //搜索值
        },
        isload: false,
        loading: false,
    },
    //请求page
    getList: function () {
        var that = this;
        that.showloading();
        that.data.query.type = 1;
        request.getJSON(cfg.baseOrganizationUrl, that.data.query).then(res => {
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
    //法律模板请求
    getBasicAnnex() {
        let that = this;
        that.data.query.type = 2;
        that.showloading();
        request.getJSON(cfg.lawServeTemplate, that.data.query).
            then(res => {
                that.hideloading();
                let e = res.data;
                let len = e.rows ? e.rows.length : 0;
                if (len < that.data.query.pageSize) {
                    that.setData({
                        isload: true
                    })
                }
                if (e.status == 1) {
                    let rows = that.data.basicAnnexArr.concat(res.data.rows);
                    that.setData({
                        basicAnnexArr: rows,

                    })
                }
                if (that.data.query.pageNum == 1) {
                    that.setData({
                        basicLength: res.data.total
                    })
                }
            })
    },
    //tab切换
    tabClick: function (event) {
        var that = this;
        var index = parseInt(event.detail.index); //获取当前点击的tabs的索引值

        that.setData({
            index: index
        })
        if (index == 1) { //0 律师事务所  1法律模板
            that.data.basicAnnexArr = [];
            that.data.query.pageNum = 1;
            that.getBasicAnnex();
        } else {
            that.data.listArr = [];
            that.data.query.pageNum = 1;
            that.getList();
        }
    },
    //点击跳转详情
    listClcik: function (e) {
        var index = parseInt(e.currentTarget.dataset.index);
        var listId = this.data.listArr[index].id;
        wx.navigateTo({
            url: '/pages/LawFirm/index?id=' + listId,
        })
    },
    //点击模板跳转下载
    templateClick: function (e) {
        let that = this;
        let index = parseInt(e.currentTarget.dataset.index);
        let listId = that.data.basicAnnexArr[index].id;
        let titles = that.data.basicAnnexArr[index].title;
        let times = that.data.basicAnnexArr[index].createDate
        wx.navigateTo({
            url: '/pages/LawyerTemplate/index?id=' + listId + '&title=' + titles + '&time=' + times,
        })
    },
    //搜索事件
    onChange: function (e) {
        var that = this;
        that.data.query.title = e.detail;
        request.getJSON(cfg.lawServeTemplate, that.data.query).then(res => {
            if (res.data.status == 1) {
                that.setData({
                    basicAnnexArr: res.data.rows,
                    basicLength: res.data.total
                })
            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.getList();
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {
        let that = this;
        if (that.data.index != 1) {
            that.data.query.pageNum = 1;
            that.data.listArr = [];
            that.getList();
            wx.stopPullDownRefresh();
        } else {
            that.data.query.type = 2;
            that.data.query.pageNum = 1;
            that.data.basicAnnexArr = [];
            that.getBasicAnnex();
            wx.stopPullDownRefresh();
        }

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {
        let that = this;
        if (that.data.isload) {
            return;
        }
        if (that.data.index != 1) {
            that.data.query.pageNum++;
            that.getList();
        } else {
            that.data.query.pageNum++;
            that.getBasicAnnex();
        }
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})