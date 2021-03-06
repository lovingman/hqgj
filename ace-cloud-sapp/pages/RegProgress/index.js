// pages/RegProgress/index.js
var request = require("../../utils/request.js");
var cfg = require("../../utils/config.js");
Page({

    /**
     * 页面的初始数据
     */
    data: {
        steps: [],
        activeColor: "#07c160",
        active: 0,
        id: '',
        flag: false,
        res: {}
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        // this.getDic();
        this.getData(options.id)
        this.data.id = options.id
    },
    getData(id) { //获取创业服务包详情
        let that = this;
        if (!id) {
            return;
        }
        request.getJSON(cfg.serveBusinessPreviewInfoUrl, {
            id: id
        }).then(rst => {
            let r = rst.data;
            if (r.status == 1) {
                that.getDic();
                that.data.res = r.data
            }
        })
    },
    //前往修改
    updata() {
        wx.navigateTo({
            url: '../RegProcess/index?id=' + this.data.id,
        })
    },
    //获取字典
    getDic() {
        let that = this;
        let datas = {
            categoryIds: '54'
        }
        request.getJSON(cfg.getByCategoryUrl, datas).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                let list = res.data['54'];
                for (let i of list) {
                    i.text = i.name
                }
                let n = that.data.res;
                if (n.tab == null) {
                    n.tab = 0;
                    list[0].text += '(审核中)'
                } else if (n.tab == 0 && n.status == "2") {
                    list[0].text += '(审核驳回)'
                } else {
                    list[0].text += '(审核通过)'
                }
                if (n.status == "2") {
                    that.setData({
                        activeColor: '#EE0A24',
                        flag: true
                    })
                }
                that.setData({
                    steps: list
                })
                that.setData({
                    active: n.tab
                })
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

    }
})