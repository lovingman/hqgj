// pages/RecordTrain/index.js
var request = require('../../utils/request.js');
var config = require('../../utils/config.js');
import Dialog from '../../vant/weapp/dialog/dialog';
import Toast from '../../vant/weapp/toast/toast';
// 状态  0 - 待完成 1 - 已取消 2 - 专家确认 3 - 已完成  4 - 待评价
Page({

    /**
     * 页面的初始数据
     */
    data: {
        active: 0,
        value: 3,
        show: false,
        evaluate: {
            type: '',
            orderNo: '',
            orgId: '',
            evaluateGrade: 4,
            evaluateContent: '',
            id: '',
            index: ''
        },
        tabs: [{
                name: '全部',
                list: [],
                isload: false,
                loading: false,
                query: {
                    pageSize: 10,
                    pageNum: 1,
                    type: '3'
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
                    status: '0,2',
                    type: '3'
                }
            },
            {
                name: '待评价',
                list: [],
                isload: false,
                loading: false,
                query: {
                    pageSize: 10,
                    pageNum: 1,
                    status: '31',
                    type: '3'
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
                    status: '31,32',
                    type: '3'
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
    changeStarHandler(e) {
        this.data.evaluate.evaluateGrade = e.detail;
    },
    changeEvaluateHandler(e) {
        this.data.evaluate.evaluateContent = e.detail.value
    },
    closeEvaluate(e) {
        if (e.detail === 'confirm') {
            return;
        }
        console.log(2);
        this.setData({
            show: false
        })
    },
    // 获取列表
    getData() {
        let that = this;
        let index = that.data.active;
        let targetObj = that.data.tabs[index];
        that.showloading();
        request.getJSON(config.orderPage, targetObj.query).then(res => {
            that.hideloading();
            let e = res.data;
            let len = e.rows ? e.rows.length : 0;
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
        targetObj.isload = false;
        targetObj.query.pageNum = 1;
        targetObj.list = [];
        that.getData();
    },
    cancelOrder(e) {
        let that = this;
        Dialog.confirm({
            title: '注意',
            message: '确定取消报名'
        }).then(() => {
            let id = e.currentTarget.dataset.id;
            let index = e.currentTarget.dataset.index;
            request.post(config.updateOrderStatus, {
                id: id,
                status: '1'
            }).then(rst => {
                let r = rst.data;
                if (r.status == 1) {
                    let idx = that.data.active;
                    let targetObj = that.data.tabs[idx];
                    if (idx != '0') {
                        let i = targetObj.list.splice(index, 1);
                        that.data.tabs[0].list.push(i);
                        let list = "tabs[" + idx + "].list"
                        that.setData({
                            [list]: targetObj.list
                        })
                    } else {
                        targetObj.list[index].status = '1';
                        let list = "tabs[" + idx + "].list"
                        that.setData({
                            [list]: targetObj.list
                        })
                    }
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
    confirmEvaluate(e) {
        let that = this;
        Dialog.confirm({
            title: '注意',
            message: '确定咨询'
        }).then(() => {
            let id = e.currentTarget.dataset.id;
            let index = e.currentTarget.dataset.index;
            request.post(config.updateOrderStatus, {
                id: id,
                status: '31'
            }).then(rst => {
                let r = rst.data;
                if (r.status == 1) {
                    Toast.success({
                        message: "确认咨询成功",
                        zIndex: 1000
                    });
                    let idx = that.data.active;
                    let targetObj = that.data.tabs[idx];
                    targetObj.list[index].status = '31';
                    let list = "tabs[" + idx + "].list"
                    that.setData({
                        [list]: targetObj.list
                    })
                }else{
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
    deleteOrder(e) {
        let that = this;
        Dialog.confirm({
            title: '注意',
            message: '确定删除记录'
        }).then(() => {
            let id = e.currentTarget.dataset.id;
            let index = e.currentTarget.dataset.index;
            request.post(config.deleteOrder, {
                id: id
            }).then(rst => {
                let r = rst.data;
                if (r.status == 1) {
                    Toast.success({
                        message: "删除成功",
                        zIndex: 1000
                    });
                    let idx = that.data.active;
                    let targetObj = that.data.tabs[idx];
                    targetObj.list.splice(index, 1);
                    let list = "tabs[" + idx + "].list"
                    that.setData({
                        [list]: targetObj.list
                    })
                }else{
                    Toast.fail({
                        message: "删除失败",
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
    evaluateOrder(e) {
        let that = this;
        console.log(e);
        let self = that.data.evaluate;
        self.orgId = e.currentTarget.dataset.orgid;
        self.orderNo = e.currentTarget.dataset.orderno;
        self.type = e.currentTarget.dataset.type;
        self.id = e.currentTarget.dataset.id;
        self.index = e.currentTarget.dataset.index;
        that.setData({
            show: true
        })
    },
    sendEvaluate() {
        let that = this;
        request.postJSON(config.evaluateOrder,
            that.data.evaluate
        ).then(rst => {
            let r = rst.data;
            if (r.status == 1) {
                let data = {
                    type: '',
                    orderNo: '',
                    orgId: '',
                    evaluateGrade: 4,
                    evaluateContent: ''
                };
                that.setData({
                    evaluate: data,
                    show: false
                })
                Toast.success({
                    message: "评价成功",
                    zIndex: 3000
                });
                let idx = that.data.active;
                let targetObj = that.data.tabs[idx];
                let index = that.data.evaluate.index;
                if (idx != '0' || idx != '3') {
                    let i = targetObj.list.splice(index, 1);
                    let list = "tabs[" + idx + "].list"
                    that.setData({
                        [list]: targetObj.list
                    })
                } else {
                    targetObj.list[index].status = '32';
                    let list = "tabs[" + idx + "].list"
                    that.setData({
                        [list]: targetObj.list
                    })
                }
            } else {
                Toast.fail ({
                    message: "评价失败",
                    zIndex: 3000
                });
                that.setData({
                    show: false
                })
            }
        });
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
        targetObj.isload = false;
        targetObj.query.pageNum = 1;
        targetObj.list = [];
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
        if (targetObj.isload) {
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