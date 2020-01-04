// pages/Accounting/index.js
const app = getApp()
var request = require("../../utils/request.js");
var cfg = require("../../utils/config.js");
import Dialog from '../../vant/weapp/dialog/dialog';
import Toast from '../../vant/weapp/toast/toast';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        list: [],
        query: {
            pageSize: 10,
            pageNum: 1
        },
        isload: false,
        loading: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        this.getMyservice();
    },
    getMyservice() {
        let that = this;
        that.setData({
            loading: true
        })
        wx.stopPullDownRefresh();
        request.getJSON(cfg.lecturerPage, that.data.query).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                if (that.data.query.pageSize > res.rows.length) {
                    that.setData({
                        isload: true
                    })
                }
                let list = that.data.list.concat(res.rows);
                that.setData({
                    list: list,
                    loading: false
                })
            }
        })
    },
    confirmEvaluate(e) {
        let that = this;
        Dialog.confirm({
            title: '注意',
            message: '确定咨询'
        }).then(() => {
            let id = e.currentTarget.dataset.id;
            let index = e.currentTarget.dataset.index;
            request.post(cfg.updateOrderStatus, {
                id: id,
                status: '2'
            }).then(rst => {
                let r = rst.data;
                if (r.status == 1) {
                    Toast.success({
                        message: "确认咨询成功",
                        zIndex: 1000
                    });
                    that.data.list[index].status = '2';
                    that.setData({
                        list: that.data.list
                    })
                } else {
                    Toast.fail({
                        message: "确认咨询失败",
                        zIndex: 1000
                    });
                }
            })
        }).catch(() => {
            wx.showToast({
                title: '取消',
                icon: 'none',
                duration: 1000
            })
        });
    },
    registrationEnterpriseClick(e) {
        let id = e.currentTarget.dataset.id;
        if (!id) {
            Toast.fail({
                message: "数据缺失，刷新重试",
                zIndex: 1000
            });
        }
        wx.navigateTo({
            url: '/pages/TrainingEnrollmentManagement/index?id=' + id,
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
        that.data.query.pageNum = 1;
        that.data.list = [];
        that.setData({
            isload: false
        })
        that.getMyservice();
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        let that = this;
        if (!that.data.isload) {
            that.data.query.pageNum++;
            that.getMyservice();
        }
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    }
})