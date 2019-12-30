// pages/RecordTrain/index.js
var request = require('../../utils/request.js');
var config = require('../../utils/config.js');
// 状态  0-待签到 1-已取消 2-已签到(已完成) 
Page({

    /**
     * 页面的初始数据
     */
    data: {
        active: 0,
        value: 3,
        show: false,
        tabs: [{
                name: '全部',
                list: [],
                isload: false,
                loading: false,
                query: {
                    pageSize: 10,
                    pageNum: 1
                }
            },
            {
                name: '待完成',
                list: [],
                isload: false,
                loading: false,
                query: {
                    pageSize: 10,
                    pageNum: 1,
                    status: 0
                }
            },
            {
                name: '已取消',
                list: [],
                isload: false,
                loading: false,
                query: {
                    pageSize: 10,
                    pageNum: 1,
                    status: 1
                }
            },
            {
                name: '已完成',
                list: [],
                isload: false,
                loading: false,
                query: {
                    pageSize: 10,
                    pageNum: 1,
                    status: 2
                }
            }
        ]
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        this.getData();
    },
    // 获取列表
    getData() {
        let that = this;
        let index = that.data.active;
        let targetObj = that.data.tabs[index];
        that.showloading();
        request.getJSON(config.myEnrollPage, targetObj.query).then(res => {
            that.hideloading();
            let e = res.data;
            let len = e.rows?e.rows.length:0;
            if (len < targetObj.query.pageSize) {
                let isload = "tabs[" + index + "].isload"
                that.setData({
                    [isload]: true
                })
            }
            if (e.status == 1) {
                let list = targetObj.list.concat(e.rows);
                let field = "tabs[" + index + "].list"
                that.setData({
                    [field]: list
                })
            }
        })

    },
    // 显示正在加载
    showloading() {
        let that = this;
        let index = that.data.active;
        let targetObj = that.data.tabs[index];
        let loading = "tabs[" + index + "].loading"
        that.setData({
            [loading]: true
        })
    },
    // 隐藏正在加载
    hideloading() {
        let that = this;
        let index = that.data.active;
        let targetObj = that.data.tabs[index];
        let loading = "tabs[" + index + "].loading"
        that.setData({
            [loading]: false
        })
    },
    changeTabHandler(e) {
        let that = this;
        let index = e.detail.index;
        that.data.active = index;
        let targetObj = that.data.tabs[index];
        if (!targetObj.list.length) {
            that.getData();
        }
    },
    cancelEnroll(e){
        let that=this;
        let id = e.currentTarget.dataset.id;
        let index = e.currentTarget.dataset.index;
        request.post(config.cancelEnroll, { id: id}).then(rst=>{
            let r = rst.data;
            if (r.status == 1) {
                let idx = that.data.active;
                let targetObj = that.data.tabs[idx];
                targetObj.list[index].status='1';
                let list = "tabs[" + idx + "].list"
                that.setData({
                    [list]: targetObj.list
                })
            }
        })
    },
    deleteEnroll(e){
        let id = e.currentTarget.dataset.id;
        let index = e.currentTarget.dataset.index;
        request.post(config.deleteEnroll, { id: id }).then(rst => {
            let r=rst.data;
            if(r.status==1){
                let idx = that.data.active;
                let targetObj = that.data.tabs[idx];
                targetObj.list.splice(index,1);
                let list = "tabs[" + idx + "].list"
                that.setData({
                    [list]: targetObj.list
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
        let that = this;
        let index = that.data.active;
        let targetObj = that.data.tabs[index];
        targetObj.isload=false;
        targetObj.query.pageNum=1;
        targetObj.list=[];
        that.getData();
        wx.stopPullDownRefresh();
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        let that = this;
        let index = that.data.active;
        let targetObj = that.data.tabs[index];
        if(targetObj.isload){
            return;
        }
        targetObj.query.pageNum++;
        that.getData();
    },


    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    }
})