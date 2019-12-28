// pages/SignIn/index.js

var Auth = require('../../utils/auth.js');
var request = require('../../utils/request.js');
var config = require('../../utils/config.js');
var validate = require('../../utils/validate.js');
import Toast from '../../vant/weapp/toast/toast';
var app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        username: '',
        usernameState: false,
        usernameVali: 'mobilePhone',
        password: '',
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        Auth.setUserInfoData(that);
        Auth.checkLogin(that);
    },
    getUserInfo: function(e) {
        let that = this;
        Auth.wxUserInfo(e).then(rst=>{
            console.log(rst);
        });
    },
    onChange(e) {
        let that = this;
        that.data[e.target.dataset.field] = e.detail;
    },
    submitForm() {
        let that = this;
        // if (!that.validateHandler('username')) {
        //     Toast.fail('手机号码错误',);
        //     return false;
        // }
        request.post(config.login, that.data).then(rst => {
            console.log(rst);
            let res=rst.data;
            if (res.status==1){
                const tokenStr = res.data.tokenHead + res.data.token;
                wx.setStorage({
                    key: "Authorization",
                    data: tokenStr
                })
                app.getUserInfo();
            }
        })
    },
    validate(e) {
        let that = this;
        let field = e.target.dataset.field;
        that.validateHandler(field);
    },
    initValidate(e) {
        let that = this;
        let field = e.target.dataset.field;
        let fileds = field + 'State';
        that.setData({
            [fileds]: false
        })
    },
    validateHandler(target) {
        let that = this;
        if (validate[that.data[target + 'Vali']].test(that.data[target])) {
            return true;
        }
        let filed = target + 'State';
        that.setData({
            [filed]: true
        })
        return false
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