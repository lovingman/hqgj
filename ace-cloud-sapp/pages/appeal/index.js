// pages/base/index.js
const app = getApp()
var util = require("../../utils/util.js");
var request = require('../../utils/request.js');
var cfg = require('../../utils/config.js');
var validate = require('../../utils/validate.js');
import Toast from '../../vant/weapp/toast/toast';
import Dialog from '../../vant/weapp/dialog/dialog';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: "",
        typeArr: [], //类型数组,
        goodsArr: [], //物资数组,
        showType: false,
        showGoods: false,
        pickerKey: '',
        phoneBar: false,
        flagText: true,
        text2: '',
        nowData: {},
        info: {
            companyId: "",
            companyName: '',
            type: '',
            typeName: '',
            companyNumber: '',
            title: '',
            money: '',
            text: '',
            personName: '',
            personTel: '',
            bank: '',
            companyMaterial: [{
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
            companyName: 'companyName',
            type: 'notEmpty',
            companyNumber: 'integer',
            title: 'notEmpty',
            money: 'floatNum4',
            text: 'notEmpty',
            personName: 'chineseName',
            personTel: 'mobilePhone',
            bank: 'companyName'
        },
        appealList: [],
        idx:1,
        timer:'',
    },
    lookMore(){
        wx.navigateTo({
            url: '/pages/appeals/index',
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function() {
        var that = this;
        that.initUser();
        that.getDic();
        that.getTopAppeal();
    },
    getTopAppeal() {
        let that = this;
        request.getJSON(cfg.getAppealList, {
            pageSize: 10,
            pageNum: 1,
        }).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                that.data.appealList = res.rows;
                that.setData({
                    nowData:res.rows[0]
                })
                that.showTime();
            }
        })
    },
    showTime() {
        let that = this;
        that.data.timer = setInterval(()=>{
            that.setData({
                nowData:that.data.appealList[that.data.idx]
            })
            that.data.idx++;
            if (that.data.idx > that.data.appealList.length){
                that.data.idx=0;
            }
            console.log(that.data.idx);
        }, 3000)
    },
    initUser: function() {
        let that = this;
        request.getJSON(cfg.getUserInfo).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                if (!res.data.companyId) {
                    that.showSpecialTextarea();
                    Dialog.alert({
                        confirmButtonText: "个人中心",
                        title: '提示',
                        message: '您没有绑定企业'
                    }).then(() => {
                        wx.switchTab({
                            url: '/pages/PersonalCenter/index',
                        })
                    });
                }
                that.data.info.companyName = res.data.companyName;
                that.data.info.companyId = res.data.companyId;
                that.data.info.personName = res.data.name;
                that.data.info.personTel = res.data.mobile;
                that.setData({
                    info: that.data.info
                })
            } else {
                that.showSpecialTextarea();
                Dialog.alert({
                    title: '提示',
                    message: '您还没有登录。。。'
                }).then(() => {
                    wx.redirectTo({
                        url: '../SignIn/index?url=' + encodeURIComponent(util.getCurrentPageUrlWithArgs()),
                    })
                });
            }
        })
    },
    validate(e) {
        let that = this;
        let field = e.target.dataset.field;
        that.validateHandler(field);
    },
    showSpecialTextarea() {
        let that = this;
        let text = that.data.info.text;
        if (!text) {
            text = "请描述您的诉求"
        }
        that.setData({
            flagText: false,
            text2: text
        })
    },
    hideSpecialTextarea() {
        let that = this;
        let n = `info.text`;
        that.setData({
            [n]: that.data.info.text,
            flagText: true
        })
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
    onChangeMaterial(e) {
        let that = this;
        let d = e.currentTarget.dataset;
        that.data.info.companyMaterial[d.index][d.field] = e.detail;
    },

    initValidate(e) {
        let that = this;
        let field = e.target.dataset.field;
        let filed = `state.${field}`;
        that.setData({
            [filed]: false
        })
    },
    addMaterial() {
        let that = this;
        let list = that.data.info.companyMaterial
        list.push({
            category: "",
            number: "",
        });
        let filed = `info.companyMaterial`;
        that.setData({
            [filed]: list
        })
    },
    removeMaterial(e) {
        let that = this;
        let list = that.data.info.companyMaterial
        let index = e.currentTarget.dataset.index;
        list.splice(index, 1);
        let filed = `info.companyMaterial`;
        that.setData({
            [filed]: list
        })
    },
    submission() {
        let that = this;
        if (!that.validateHandler('companyName')) {
            Toast.fail('请输入正确的企业名称。');
            return;
        }
        if (!that.validateHandler('type')) {
            Toast.fail('诉求类型必填项。');
            return;
        }
        let type = that.data.info.type;
        if (type == 1) {
            if (!that.validateHandler('companyNumber')) {
                Toast.fail('企业人数必填项。');
                return;
            }
            let list = that.data.info.companyMaterial;
            for (let item in list) {
                console.log(item);
                if (!validate['notEmpty'].test(list[item].category)) {
                    Toast.fail(`物资${item+1}：物资类型必填项。`);
                    return;
                }
                if (!validate['integer'].test(list[item].number)) {
                    Toast.fail(`物资${item+1}：物资数量必填项。`);
                    return;
                }
            }
        } else if (type == 3) {
            if (!that.validateHandler('money')) {
                Toast.fail('请输入正确的融资需求名称。');
                return;
            }
            if (!that.validateHandler('bank')) {
                Toast.fail('请输入正确的期望银行名称。');
                return;
            }
        } else {
            if (!that.validateHandler('title')) {
                Toast.fail('诉求标题必填项');
                return;
            }
        }
        if (!that.validateHandler('text')) {
            Toast.fail('诉求描述必填项');
            return;
        }
        if (!that.validateHandler('personName')) {
            Toast.fail('请输入正确的联系人。');
            return;
        }
        if (!that.validateHandler('personTel')) {
            Toast.fail('请输入正确的联系方式。');
            return;
        }
        request.postJSON(cfg.createAppeal,
            this.data.info
        ).then(rst => {
            let r = rst.data;
            if (r.status == 1) {
                that.showSpecialTextarea();
                wx.redirectTo({
                    url: '../success/index',
                })
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
        clearInterval(this.data.timer)
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
    //请求字典
    getDic() {
        let that = this;
        let datas = {
            categoryIds: '62,63'
        }
        request.getJSON(cfg.getByCategoryUrl, datas).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                that.setData({
                    typeArr: res.data[62],
                    goodsArr: res.data[63]
                })
            }
        })
    },


    //显示类型选择
    showTypeClick(e) {
        let that = this;
        that.setData({
            showType: !that.data.showType
        })
        that.showSpecialTextarea();
    },
    //显示物资选择
    showGoodsClick(e) {
        let that = this;
        that.data.pickerKey = e.currentTarget.dataset.index;
        this.setData({
            showGoods: !that.data.showGoods
        })
        that.showSpecialTextarea();
    },
    //取消类型选择
    cancelType() {
        this.setData({
            showType: !this.data.showType,
        })
        this.hideSpecialTextarea();
    },
    //取消类型选择
    cancelGoods() {
        this.setData({
            showGoods: !this.data.showGoods,
        })
        this.hideSpecialTextarea();
    },
    //类型确定选择
    confirms(e) {
        let that = this;
        let typeType = `info.typeName`;
        let type = `info.type`;
        let n = e.detail.value;
        that.setData({
            [typeType]: n.name,
            [type]: n.code,
            showType: false
        })
        that.hideSpecialTextarea();
    },
    //类型确定选择
    confirmsGoods(e) {
        let that = this;
        let index = that.data.pickerKey;
        let typeType = `info.companyMaterial[${index}].categoryName`;
        let n = e.detail.value;
        that.data.info.companyMaterial[index].category = n.code;
        that.setData({
            [typeType]: n.name,
            showGoods: false
        })
        that.hideSpecialTextarea();
    },
    callPhone() {
        this.setData({
            phoneBar: true
        })
    },
    callPhoneHandler(e) {
        let number = e.currentTarget.dataset.number;
        wx.makePhoneCall({
            phoneNumber: number, //仅为示例，并非真实的电话号码
            fail() {
                Toast.fail("已取消")
            }
        })
    },
    phoneBarCloseHandler() {
        this.setData({
            phoneBar: false
        })
    },
})