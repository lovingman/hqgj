// pages/JoinEnterprises/index.js
// pages/LawFirm/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
import Dialog from '../../vant/weapp/dialog/dialog';
import Toast from '../../vant/weapp/toast/toast';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        url: cfg.organizationList,
        type: '2',

    },

    /**
     * 生命周期函数--监听页面加载
     */
    // 1- 企业   2-机构
    onLoad: function(options) {
        console.log(options.type);
        if (options.type == '1') {
            this.data.url = cfg.enterpriseList
            this.data.type = '1'
        }
    },
    // / personalCenter / bingUser ? id =& type=1    绑定  1 - 企业   2 - 机构
    join(e) {
        Dialog.confirm({
            title: '提示',
            message: '确定加入'
        }).then(() => {
            let q = {
                type: this.data.type,
                id: e.currentTarget.dataset.id
            }
            request.post(cfg.joinOrg, q).then(rst => {
                let r = rst.data;
                if (r.status == 1) {
                    Toast.success({
                        message: r.message,
                        onClose: () => {
                            wx.reLaunch({
                                url: '/pages/PersonalCenter/index'
                            });
                        }
                    });
                }else{
                    Toast.fail(r.message);
                }
            })
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
    onChangeHandler(e) {
        let that = this;
        if (!e.detail) {
            that.setData({
                list: []
            })
            return;
        }
        let p = {
            pageSize: 50,
            pageNum: 1,
        }
        if (that.data.type == "1") {
            p.companyName = e.detail;
        } else {
            p.orgName = e.detail;
        }

        request.getJSON(that.data.url, p).then(rst => {
            let r = rst.data;
            if (r.status == 1) {
                that.setData({
                    list: r.rows
                })
            }
        })
    }
})