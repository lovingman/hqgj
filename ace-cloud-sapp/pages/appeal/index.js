// pages/base/index.js
const app = getApp()
var request = require('../../utils/request.js');
var cfg = require('../../utils/config.js');
var validate = require('../../utils/validate.js');
import Toast from '@vant/weapp/toast/toast';
import Dialog from '@vant/weapp/dialog/dialog';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: "",
        typeArr: [], //类型数组
        showType: false,
        info: {
            companyId: "",
            companyName: '',
            type: '',
            companyNumber: '',
            title: '',
            money: '',
            text: '',
            personName: '',
            personTel: '',
            bank: '',
            material: [{
                category: "",
                number: "",
            }]
        },
        state: {
            type: '',
            companyNumber: '',
            title: '',
            money: '',
            text: '',
            personName: '',
            personTel: '',
            bank: '',
        },
        validate: {
            type: 'notEmpty',
            companyNumber: 'integer',
            title: 'notEmpty',
            money: 'floatNum4',
            text: 'notEmpty',
            personName: 'chineseName',
            personTel: 'mobilePhone',
            bank: 'companyName',
        },
        areaList: [],
        showPickerAreas: false,
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function() {
        var that = this;
        that.getAreaTree();
        that.getDic();
        that.reportMsg();
    },
    validate(e) {
        let that = this;
        let field = e.target.dataset.field;

        that.validateHandler(field);
    },

    validateHandler(target) {
        let that = this;
        if (validate[that.data.validate[target]].test(that.data.info[target])) {
            return true;
        }
        let filed = `state.${target}`;
        that.setData({
            [filed]: true
        })
        return false
    },
    onChange(e) {
        let that = this;
        that.data.info[e.target.dataset.field] = e.detail;
    },

    initValidate(e) {
        let that = this;
        let field = e.target.dataset.field;
        let filed = `state.${field}`;
        that.setData({
            [filed]: false
        })
    },

    // 获取上报疫情的详情
    reportMsg() {
        var that = this;
        var id = that.data.id;
        if (id) {
            request.getJSON(cfg.getTripById, {
                id: id
            }).then(rst => {
                let r = rst.data;
                if (r.status == 1) {
                    r.data.age = 32;
                    that.setData({
                        info: r.data
                    })
                }
            })
        }
    },
    submission() {
        let that = this;
        if (!that.validateHandler('companyName')) {
            Toast.fail('请输入正确的企业名称。');
            return;
        }
        if (!that.validateHandler('category')) {
            Toast.fail('诉求类型必填项。');
            return;
        }
        if (!that.validateHandler('text')) {
            Toast.fail('企业诉求必填项');
            return;
        }
        if (!that.validateHandler('name')) {
            Toast.fail('请输入正确的联系人。');
            return;
        }
        if (!that.validateHandler('tel')) {
            Toast.fail('请输入正确的联系方式。');
            return;
        }
        request.postJSON(cfg.createAppeal,
            this.data.info
        ).then(rst => {
            let r = rst.data;
            if (r.status == 1) {
                Dialog.alert({
                    title: '提交成功',
                    message: '感谢您的配合!'
                }).then(() => {
                    wx.switchTab({
                        url: '/pages/index/index',
                    })
                });
            } else {
                Toast.fail(r.message);
            }
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

    },
    cancelshowPickerArea() {
        this.setData({
            showPickerAreas: false
        })
    },
    //获取武陵区行政区划
    getAreaTree() {
        let that = this;
        request.getJSON(cfg.getAreaTree + "?pid=4307&type=3", {}).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                let obj = [];
                // obj[]  
                that.data.areaList = [{
                    values: res.data,
                    className: 'column0'
                }]
                that.setData({
                    areaList: that.data.areaList
                })
            }
        })
    },
    /**显示区域选择器 */
    showPickerArea() {
        this.setData({
            showPickerAreas: true
        })
    },
    //请求字典
    getDic() {
        let that = this;
        let datas = {
            categoryIds: '63'
        }
        request.getJSON(cfg.getByCategoryUrl, datas).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                var r = res.data[63];
                that.setData({
                    typeArr: r
                })
            }
        })
    },


    //显示类型选择
    showTypeClick() {
        this.setData({
            showType: !this.data.showType
        })
    },
    //取消类型选择
    cancelType() {
        this.setData({
            showType: !this.data.showType,

        })
    },
    //类型确定选择
    confirms(e) {
        let that = this;
        let typeType = "info.categoryName";
        let n = e.detail.value;
        console.log(n);
        that.data.info.category = n.code;
        let typeName = n.name;
        that.setData({
            [typeType]: typeName,
            showType: false
        })
    },

    /**
     * 提交areacode
     */
    areaConfirmsHandler(e) {
        let that = this;
        let val = e.detail.value;
        that.data.info.areaCode = val[val.length - 1].id;
        let areaCodeName = val[val.length - 1].text;
        let n = `info.areaCodeName`
        that.setData({
            [n]: areaCodeName,
            showPickerAreas: false
        });
    }
})