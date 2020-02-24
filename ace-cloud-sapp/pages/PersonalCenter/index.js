// pages/PersonalCenter/index.js
const app = getApp()
var util = require("../../utils/util.js");
var request = require("../../utils/request.js");
var cfg = require("../../utils/config.js");
import Dialog from '../../vant/weapp/dialog/dialog';
import Toast from '../../vant/weapp/toast/toast';
var Auth = require('../../utils/auth.js');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        navigates: [{
                text: '注册记录',
                imgUrl: '/assets/image/icon3-1.png',
                path: '/pages/ServiceEnterprise/index'
            },
            {
                text: '培训记录',
                imgUrl: '/assets/image/icon3-2.png',
                path: '/pages/RecordTrain/index'
            },
            {
                text: '问诊记录',
                imgUrl: '/assets/image/icon3-3.png',
                path: '/pages/RecordInquiry/index'
            },
            {
                text: '财税订单',
                imgUrl: '/assets/image/icon3-4.png',
                path: '/pages/RecordsFinance/index'
            },
            {
                text: '我的诉求',
                imgUrl: '/assets/image/icon3-6.png',
                path: '/pages/appealList/index'
            }
        ],
        service: {
            text: '服务管理',
            imgUrl: '/assets/image/icon3-5.png',
            path: ''
        },
        userInfo: {
            name: "用户",
            integral: 0
        },
        show: false,
        optIndex: 1,
    },
    selectOrg() {
        wx.navigateTo({
            url: '../JoinEnterprises/index?type=' + this.data.optIndex
        })
    },
    // pages/ServiceEnterprise/index  企业服务
    // pages/TrainingInstitutions/index  培训
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        let that = this;
        Auth.checkLogin(that);
        that.initUserInfo();
    },

    // 判断是否登录
    isloginHandler() {
        request.getJSON(cfg.getUserInfo).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                app.globalData.islogin = true;
                app.globalData.userInfo = res.data;
                this.initUserInfo();
                let u = ''
                // 1 - 律师事务所 2 - 会计事务所 3 - 培训机构  4 - 工信局 5 - 企业 ',
                if (res.data.userType == 3) {
                    u = '/pages/TrainingInstitutions/index'
                } else if (res.data.userType == 2) {
                    u = '/pages/ServiceAccounting/index'
                }
                let f = 'service.path';
                this.setData({
                    [f]: u
                })
            } else {
                Dialog.alert({
                    title: '提示',
                    message: '登录后可以查看“我的”页面'
                }).then(() => {
                    wx.navigateTo({
                        url: '../SignIn/index?url=' + encodeURIComponent(util.getCurrentPageUrlWithArgs()),
                    })
                });
            }
        })
    },
    // 选择单位隐藏
    onClickHide() {
        this.setData({
            show: false
        });
    },
    // 点击选择单位
    onClickShow() {
        this.setData({
            show: true
        });
    },
    // 获取用户信息
    // /personalCenter/relieveByUnionId   解绑微信
    // /personalCenter/bindUnionId?unionId=123456  绑定微信
    relieveByUnionId() {
        request.post(cfg.relieveByUnionId).then(rst => {
            let r = rst.data;
            if (r.status == 1) {
                Toast.success(r.message)
                app.globalData.islogin = false;
                this.isloginHandler();
                Auth.checkLogin(that);
            } else {
                Toast.fail(r.message)
            }
        })
    },
    onGotUserInfo(e) {
        Auth.wxUserInfo(e).then(rst => {
            request.post(cfg.bindUnionId, {
                unionId: rst.unionId
            }).then(rs => {
                let r = rs.data;
                if (r.status == "1") {
                    Toast.success(r.message);
                    app.globalData.islogin = false;
                    this.isloginHandler();
                } else {
                    Toast.fail(r.message);
                }
            })
        }).catch(() => {
            Toast.fail('授权失败，请重试');
        });
    },
    initUserInfo() {
        let that = this;
        let userInfo = app.globalData.userInfo;
        if (userInfo) {
            that.setData({
                userInfo: userInfo,
            })
        }
    },
    relieveBind() {
        request.post(cfg.relieveBind).then(rst => {
            let r = rst.data;
            if (r.status == 1) {
                app.globalData.islogin = false;
                this.isloginHandler();
            }
        })
    },
    signOutHandler() {
        wx.setStorageSync('Authorization', '');
        app.globalData.islogin = false;
        app.globalData.userInfo = '';
        Toast.success({
            message: "退出成功",
            onClose: () => {
                wx.reLaunch({
                    url: "/pages/index/index"
                })
            }
        });
    },
    // 选择企业
    selectOne() {
        this.setData({
            optIndex: 1
        })
    },
    // 选择组织
    selectTwo() {
        this.setData({
            optIndex: 2
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
        this.isloginHandler();
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