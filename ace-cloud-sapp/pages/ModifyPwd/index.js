// pages/Register/index.js
var request = require('../../utils/request.js');
var config = require('../../utils/config.js');
var validate = require('../../utils/validate.js');
import Toast from '../../vant/weapp/toast/toast';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        oldPwd: '',
        oldPwdState: false,
        oldPwdVali: 'thanlen6Str',
        newPwd: '',
        newPwdState: false,
        newPwdVali: 'thanlen6Str',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

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
    next(){
        let that=this;
        if (!that.validateHandler('oldPwd')){
            Toast.fail('旧密码不符合要求');
            return;
        }
        if (!that.validateHandler('newPwd')) {
            Toast.fail('新密码不符合要求');
            return;
        }
        request.post(config.modifyPwd,that.data).then(
            res=>{
                let r=res.data;
                if (r.status==1){
                    Toast.success({
                        message:'修改成功',
                        duration:1000,
                        onClose:()=>{
                            wx.reLaunch({
                                url: '/pages/PersonalCenter/index'
                            });
                        }
                    });
                }else{
                    Toast.fail(r.message);
                }
            }
        )
    },
    onChange(e) {
        let that = this;
        that.data[e.target.dataset.field] = e.detail;
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

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})