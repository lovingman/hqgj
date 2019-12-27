// pages/Register/index.js
var request = require('../../utils/request.js');
var config = require('../../utils/config.js');
var validate = require('../../utils/validate.js');
import Toast from '/@vant/weapp/toast/toast';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        captchaTxt:'发送验证码',
        disabled:false,
        countdown:60,
        account: '',
        accountState: false,
        accountVali: 'mobilePhone',
        password: '',
        captcha:'',
        captchaVali: 'len4Num',
        passwordState: false,
        passwordVali: 'thanlen6Str',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },
    getcode(){
        let that = this;
        if (that.validateHandler('account')){
            request.post(config.getCaptcha, { mobile:that.data.account}).then(rst => {
                that.settime();
            });
        }else{
            wx.showToast({
                title: '手机号码错误',
                icon:'warn'
            })
            return;
        }
    },
    settime(){
        let that = this;
        if (that.data.countdown == 0) {
            that.setData({
                captchaTxt: '发送验证码',
                disabled: false
            });
            that.data.countdown = 60;
        } else {
            that.setData({
                captchaTxt: "重新发送(" + that.data.countdown + ")",
                disabled: true
            });
            that.data.countdown--;
            setTimeout(function () {
                that.settime()
            }, 1000)
        }
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
        if (!that.validateHandler('account')){
            Toast.fail('手机号码错误');
            return;
        }
        if (!that.validateHandler('captcha')) {
            Toast.fail('验证码不对');
            return;
        }
        if (!that.validateHandler('password')) {
            Toast.fail('密码位数不对');
            return;
        }
        wx.navigateTo({
          url: "../RegisterVerifies/index?account=" + that.data.account + "&captcha=" + that.data.captcha + "&password=" + that.data.password
        })
        // request.post(config.register,data).then(
        //     res=>{
        //         let r=res.data;
        //         if(res.status==1){
        //             Toast.success({
        //                 message:'注册成功',
        //                 onClose:()=>{
                            
        //                 }
        //             });
        //         }else{
        //             Toast.fail(r.message);
        //         }
        //     }
        // )
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