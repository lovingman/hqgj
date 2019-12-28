// pages/PersonalCenter/index.js
const app = getApp()
import Dialog from '../../vant/weapp/dialog/dialog';
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
                path: '/'
            },
            {
                text: '问诊记录',
                imgUrl: '/assets/image/icon3-3.png',
                path: '/'
            },
            {
                text: '财税订单',
                imgUrl: '/assets/image/icon3-4.png',
                path: '/'
            }
        ],
        userInfo: {
            name: "用户"
        },
        show: false,
        optIndex: 1,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        this.initUserInfo();
    },
    // 判断是否登录
    isloginHandler() {
        if (!app.globalData.islogin) {
            Dialog.alert({
                title: '提示',
                message: '登录后可以查看“我的”页面'
            }).then(() => {
                // on close
            });
        }
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
                userInfo: userInfo
            })
        }
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