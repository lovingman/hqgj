// pages/FinanceEnterprise/index.js
var cfg = require("../../utils/config.js");
var util = require("../../utils/util.js");
var request = require("../../utils/request.js");
import Dialog from '../../vant/weapp/dialog/dialog';
import Toast from '../../vant/weapp/toast/toast';
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        show: false,
        showId: 0, //服务项目选中当前状态
        radio: 0,
        detailsForm: {}, //详情数据容器
        priceModel: {}, //服务项目数据
        financeItemArr: [], //服务项目列表
        isTrue: true, //初始显示大小价格值
        isPrice: false, //点击服务项目后显示的价格值
        id: "", //id 值
        type: 1, //类型 1,代理记账 2财税管家
        itemId: "", //财税管家服务项目ID
        getCurrentPageUrlWithArgs: "", //当前页面URL+参数
    },
    //服务项目点击切换当前状态
    activeClick: function(e) {
        console.log(e)
        var that = this;
        var ids = e.currentTarget.dataset.id;
        var index = parseInt(e.currentTarget.dataset.index);
        var priceForm = that.data.financeItemArr[index];
        that.data.priceModel = priceForm;
        that.setData({
            showId: ids,
            itemId: ids,
            priceModel: priceForm,
            isTrue: false, //隐藏初始价格
            isPrice: true //显示点击服务的价格
        });
    },
    //关闭
    onClose: function() {
        var that = this;
        that.setData({
            show: false,
        });
    },
    //点击下单
    orderClick: function() {
        var that = this;
        if (app.globalData.islogin) {
            if (app.globalData.userInfo.companyId != null) {
                if (that.data.type == 1) {
                    var detailsObj = that.data.detailsForm;
                    if (detailsObj.integral >= 500) {
                        Dialog.confirm({
                            title: '确认订单',
                            message: '是否抵扣积分'
                        }).then(() => {
                            request.postJSON(cfg.financeOrder, {
                                financeId: that.data.id,
                                type: 1,
                            }).then(res => {
                                if (res.data.status == 1) {
                                    Toast.success("订单" + res.data.message);
                                    this.getById();
                                }
                            })
                        }).catch(() => {
                            Toast.fail("已取消当前操作");
                        });
                    } else {
                        Toast.fail("对不起，您的积分不够500");
                    }
                } else {
                    that.setData({
                        show: true,
                    });
                }
            } else {
                Dialog.alert({
                    message: '未绑定企业，请前往个人中心绑定'
                }).then(() => {
                    // on close
                })
            }
        } else {
            Dialog.alert({
                message: '未登录，请先登录'
            }).then(() => {
                // on close
                wx.navigateTo({
                    url: '../SignIn/index?url=' + encodeURIComponent(that.data.getCurrentPageUrlWithArgs),
                })
            })
        }
    },
    //财税订单确定
    orderDetermine: function() {
        var that = this;
        if (app.globalData.islogin) {
            if (app.globalData.userInfo.companyId != null) {
                if (that.data.itemId != "") {
                    Dialog.confirm({
                        title: '确认订单',
                        message: '是否确定下单'
                    }).then(() => {
                        request.postJSON(cfg.financeOrder, {
                            financeId: that.data.id,
                            type: 2,
                            itemId: that.data.itemId
                        }).then(res => {
                            if (res.data.status == 1) {
                                Toast.success("下单" + res.data.message);
                                that.setData({
                                    show: false,
                                });
                                this.getById();
                            }
                        })
                    }).catch(() => {
                        Toast.fail("已取消当前操作");
                    });
                } else {
                    Toast.fail("请选择一个服务项目");
                }
            } else {
                Toast.fail("您还没有绑定企业，请您先去个人中心绑定一个企业，谢谢");
            }

        } else {
            Toast.fail("未登录，请先登录");
            wx.navigateTo({
                url: '../SignIn/index?url=' + encodeURIComponent(that.data.getCurrentPageUrlWithArgs),
            })
        }
    },
    //获取数据
    getById: function() {
        var that = this;
        request.getJSON(cfg.financeIdUrl, {
            id: that.data.id
        }).then(res => {
            console.log(res);
            if (res.data.status == 1) {
                that.setData({
                    detailsForm: res.data.data,
                    detailsFormContent: res.data.data.content.replace(/\<img/gi, '<img style="max-width:100%;height:auto;"').replace(/<figure/g, '<div')
                        .replace(/\/figure>/g, '\div>'),
                    financeItemArr: res.data.data.financeItemList
                })
            }
        })
    },
    //拨打电话
    telePhoneClick: function(e) {
        var that = this;
        var phone = e.currentTarget.dataset.id;
        that.setData({
            phone: phone
        })
        if (phone) {
            wx.makePhoneCall({
                phoneNumber: that.data.phone
            })
        } else {
            return false
        }
    },
    companyInfo(e) {
        var agentId = e.currentTarget.dataset.id; //获取当前点击的tabs的索引值
        wx.navigateTo({
            url: '/pages/AccountingCompany/index?id=' + agentId
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that = this;
        console.log(options);
        that.setData({
            id: options.id,
            type: options.type,
            getCurrentPageUrlWithArgs: util.getCurrentPageUrlWithArgs()
        });
        that.getById();
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