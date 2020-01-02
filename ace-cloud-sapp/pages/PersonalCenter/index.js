// pages/PersonalCenter/index.js
const app = getApp()
var util = require("../../utils/util.js");
var request = require("../../utils/request.js");
var cfg = require("../../utils/config.js");
import Dialog from '../../vant/weapp/dialog/dialog';
import Toast from '../../vant/weapp/toast/toast';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        navigates: [{
                text: '注册记录',
                imgUrl: '/assets/image/icon3-1.png',
                path: '/'
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
                text: '服务',
                imgUrl: '/assets/image/icon3-4.png',
                path: '/pages/RecordsFinance/index'
            }
        ],
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
        this.initUserInfo();
    },

    // 判断是否登录
    isloginHandler() {
        request.getJSON(cfg.getUserInfo).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                app.globalData.islogin = true;
                app.globalData.userInfo = res.data;
                this.initUserInfo();
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
    signOutHandler(){
        wx.setStorageSync('Authorization', '');
        Toast.success({
            message: "退出成功",
            onClose: () => {
                this.isloginHandler();
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