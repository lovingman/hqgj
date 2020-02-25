// pages/PolicyServiceDetail/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
import Notify from '../../vant/weapp/notify/notify';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: "", //id值
        basicForm: {}, //信息容器
        query: {
            pageSize: 10,
            pageNum: 1,
            type: 4, //政策附件
            relationId: "" //ID值
        },
        basicAnnexesArr: [],

    },

    /**
     * 生命周期函数--监听页面加载
     */
    //附件列表
    getList: function() {
        let that = this;
        that.data.query.relationId = that.data.id;
        request.getJSON(cfg.basicAnnexUrl, that.data.query).then(res => {
            console.log(that.data.query)
            let e = res.data;
            if (e.status == 1) {
                let rows = res.data.rows;
                that.setData({
                    basicAnnexesArr: rows
                })
            }
        })
    },
    //点击下载文件
    downloadFile: function(e) {
        console.log(e);
        let links = e.currentTarget.dataset.url;
        wx.setClipboardData({
            data: links,
            success: function(res) {
                Notify({
                    type: 'success',
                    message: '复制成功，请前往浏览器打开下载'
                });
            }
        })
    },
    onLoad: function(options) {
        var that = this;
        that.setData({
            id: options.id
        })
        request.getJSON(cfg.lawServeByIdUrl, {
            id: that.data.id
        }).then(res => {
            console.log(res);
            that.setData({
                basicForm: res.data.data,
                basicFormContent: res.data.data.content.replace(/\<img/gi, '<img style="max-width:100%;height:auto;"')
                    .replace(/<figure/g, '<div')
                    .replace(/\/figure>/g, '\div>'), //富文本内容，替换掉fiugre标签不显示图片问题，换成div
            })
            // console.log(that.data.basicFormContent)
        })
        that.getList();
    },
    //富文本复制
    // copy(e) {
    //     let that = this;
    //     let text = e.currentTarget.dataset.text;
    //     console.log(e);
    //     wx.setClipboardData({
    //         data: text,
    //         success: function(res) {
    //             // wx.hideToast();    //打开可不显示提示框
    //             wx.getClipboardData({
    //                 success(res) {
    //                     console.log(res.data) // data
    //                 }
    //             })
    //         }
    //     })
    // },
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