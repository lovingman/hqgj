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
        mobile: '',
        mobileState: false,
        mobileVali: 'mobilePhone',
        newPassword: '',
        captcha:'',
        captchaVali: 'len4Num',
        newPasswordState: false,
        newPasswordVali: 'thanlen6Str',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },
    getcode(){
        let that = this;
        that.settime();
        if (that.validateHandler('mobile')){
            request.post(config.captchaForgetPwd, { mobile:that.data.mobile}).then(rst => {
                let r=rst.data;
                if(r.status==1){
                    Toast.success("发送成功");
                }
                else{
                    Toast.fail(r.message);
                }
            });
        }else{
            Toast.fail(手机号码错误);
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
        if (!that.validateHandler('mobile')){
            Toast.fail('手机号码错误');
            return;
        }
        if (!that.validateHandler('captcha')) {
            Toast.fail('验证码不对');
            return;
        }
        if (!that.validateHandler('newPassword')) {
            Toast.fail('密码位数不对');
            return;
        }
        wx.navigateTo({
          url: "../RegisterVerifies/index"
        })
        request.post(config.resetPassword,data).then(
            res=>{
                let r=res.data;
                if(res.status==1){
                    Toast.success({
                        message:'修改成功',
                        onClose:()=>{
                            wx.navigateTo({
                                url: "../SignIn/index"
                            })
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