// pages/TrainingEnrollmentManagement/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
import Toast from '../../vant/weapp/toast/toast';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        checked: false, //全选按钮
        //id值
        enrollForm: [], //page数组
        result: [], //单选按钮数组
        isCheck: false, //是否显示全选按钮
        isTrue: false, //禁止
        query: {
            pageSize: 10,
            pageNum: 1,
            orderBy: 'status',
            serveCultivateId: "",
            sord: 'asc'
        },
        isload: false,
        loading: false,
    },
    //拨打电话
    telePhoneClick: function(e) {
        console.log(e)
        var that = this;
        var phone = e.currentTarget.dataset.id;
        if (phone) {
            wx.makePhoneCall({
                phoneNumber: phone
            })
        } else {
            return false
        }
    },
    //点击签到出现全选按钮
    checkClick: function() {
        var that = this;
        that.setData({
            isCheck: true,
        })
    },
    //全选操作
    onChange(event) {
        var that = this;
        if (that.data.checked == false) { //判断当前全县按钮是否为已选或者没选
            var obj = [];
            obj = that.data.enrollForm.map(item => { //循环page数组，依次拿到ID存入OBJ
                return item.id.toString();
            })
        } else {
            var obj = []; //清空
        }
        that.setData({
            checked: event.detail,
            result: obj
        });
    },
    //单选操作
    singleClcik: function(event) {
        var that = this;
        that.setData({
            result: event.detail
        });
    },
    //获取列表数据
    getList: function() {
        var that = this;
        that.setData({
            loading: true
        })
        wx.stopPullDownRefresh();
        request.getJSON(cfg.enrollPage, that.data.query).then(res => {
            let r = res.data
            if (r.status == 1) {
                if(that.data.query.pageNum==1){
                    that.setData({
                        enrollNumll: r.total
                    })
                }
                var listRows = r.rows;
                if (listRows.length<that.data.query.pageSize){
                    that.setData({
                        isload: true
                    })
                }
                let list = that.data.enrollForm.concat(listRows);
                that.setData({
                    enrollForm: list, //赋值给自定义的data
                    loading: false
                })
                for (let i = 0; i < listRows.length; i++) {
                    if (listRows[i].status == 2) {
                        that.data.result.push(listRows[i].id);
                    }
                }
            }
        })
    },
    //
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        that.data.query.serveCultivateId = options.id
        this.getList();
    },
    //点击取消全选
    cancelClick: function() {
        var that = this;
        var obj = [];
        that.setData({
            checked: false,
            result: obj,
            isCheck: false
        });
    },
    //确定签到
    determineClick: function() {
        var that = this;
        var obj = {};
        obj.status = 2;
        obj.ids = that.data.result.join(',');
        request.post(cfg.enrollUpdate, obj).then(res => {
            console.log(res);
            if (res.data.status == 1) {
                Toast.fail("报名" + res.data.message);
                this.onPullDownRefresh();
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
        let that = this;
        that.data.query.pageNum = 1;
        that.data.enrollForm = [];
        that.data.result=[];
        that.setData({
            isload: false
        })
        that.getList();
    },
    

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {
        let that = this;
        if (!that.data.isload) {
            that.data.query.pageNum++;
            that.getList();
        }
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    }
})