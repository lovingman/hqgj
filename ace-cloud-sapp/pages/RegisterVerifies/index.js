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
        name: '',
        nameState: false,
        nameVali: 'chineseName',
        idCard: '',
        idCardState: false,
        idCardVali: 'IdCard',
        nextData:{}
    },
    submit() {
        let that = this;
        if (!that.validateHandler('name')) {
            Toast.fail('名字格式错误');
            return;
        }
        if (!that.validateHandler('idCard')) {
            Toast.fail('身份证格式错误');
            return;
        }
        let data={
            name:that.data.name,
            idCard:that.data.idCard
        }
        request.post(config.register, Object.assign(data,that.data.nextData)).then(
            res=>{
                let r=res.data;
                if(res.status==1){
                    Toast.success({
                        message:'注册成功',
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
    // 表单验证*************************************************start
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
    onChange(e) {
        let that = this;
        that.data[e.target.dataset.field] = e.detail;
    },
    // 表单验证结束**************************************************end
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.log(options);
        let that =this;
        that.data.nextData=options;
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