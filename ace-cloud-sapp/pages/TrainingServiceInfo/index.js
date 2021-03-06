// pages/TrainingServiceInfo/index.js
var cfg = require("../../utils/config.js");
var util = require("../../utils/util.js");
var request = require("../../utils/request.js");
import Toast from '../../vant/weapp/toast/toast';
import Dialog from '../../vant/weapp/dialog/dialog';
import Notify from '../../vant/weapp/notify/notify';
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        basicForm: {}, //基本信息数据
        scheduleList: [], //日程列表
        isHides: true, //是否隐藏立即报名按钮
        isSuccess: false, //成功报名后是否显示
        enrollForm: {}, //报名企业数据
        basicAnnexList: [], //课件列表数组
    },
    //tab切换日常调取日程接口
    tabClick: function(event) {
        var that = this;
        var index = parseInt(event.detail.index); //获取当前点击的tabs的索引值
        if (index == 1) { //0是培训介绍， 1是日程安排
            request.getJSON(cfg.schedulePageUrl, {
                serveCultivateId: that.data.id
            }).
            then(res => {
                console.log(res);
                if (res.data.status == 1) {
                    that.setData({
                        scheduleList: res.data.rows
                    })
                }
            })
        }
    },
    //点击报名先判断是否登录
    isSign: function(e) {
        var that = this;
        if (app.globalData.islogin) {
            if (app.globalData.userInfo.companyId != null) {
                request.postJSON(cfg.serveCultivateEnrollUrl, {
                    serveCultivateId: that.data.id
                }).then(res => {
                    if (res.data.status == 1) {
                        Toast.success(res.data.message);
                        this.getById(); //更新当前请求数据
                    }
                })
            } else {
                Dialog.alert({
                    message: '未绑定企业，请前往个人中心绑定'
                }).then(() => {
                    // on close
                })
            }
        } else {
            Dialog.alert({
                message: '未登录，请先登录'
            }).then(() => {
                // on close
                wx.navigateTo({
                    url: '../SignIn/index?url=' + encodeURIComponent(that.data.getCurrentPageUrlWithArgs),
                })
            })
        }
    },
    //点击报名企业跳转
    registrationEnterpriseClick: function() {
        wx.navigateTo({
            url: '/pages/TrainingManagement/index?id=' + this.data.id,
        })
    },
    //点击日程去详情
    scheduleClcik: function(e) {
        var index = parseInt(e.currentTarget.dataset.index);
        var listId = this.data.scheduleList[index].id;
        wx.navigateTo({
            url: '/pages/TrainingScheduleDetails/index?id=' + listId,
        })
    },
    //请求页面数据
    getById: function() {
        var that = this;
        request.getJSON(cfg.selectByIdDetailsUrl, {
            id: that.data.id
        }).then(res => {
            console.log(res);
            if (res.data.status == 1) {
                that.setData({
                    basicForm: res.data.data,
                    basicFormContent: res.data.data.content.replace(/\<img/gi, '<img style="max-width:100%;height:auto;"')
                        .replace(/<figure/g, '<div')
                        .replace(/\/figure>/g, '\div>'), //富文本内容，替换掉fiugre标签不显示图片问题，换成div
                    basicAnnexList: res.data.data.basicAnnexList
                })
                console.log(that.data.basicFormContent);
            }
        })
        request.getJSON(cfg.enrollPage, {
            serveCultivateId: that.data.id
        }).then(res => {
            console.log(res);
            if (res.data.status == 1) {
                that.setData({
                    enrollForm: res.data.rows
                })
            }
        })
    },
    //点击复制文件
    downloadFile: function(e) {
        console.log(e);
        let that = this;
        if (app.globalData.islogin) { //判断是否登录
            if (that.data.basicForm.isSign == 1) {
                let links = e.currentTarget.dataset.url;
                wx.setClipboardData({
                    data: links,
                    success: function(res) {
                        Notify({
                            type: 'success',
                            message: '复制成功，请前往浏览器打开下载'
                        });
                    }
                })
            } else {
                Dialog.alert({
                    message: '您还未签到，请先签到'
                }).then(() => {

                })
            }

        } else {
            Dialog.alert({
                message: '未登录，请先登录'
            }).then(() => {
                wx.navigateTo({
                    url: '../SignIn/index?url=' + encodeURIComponent(that.data.getCurrentPageUrlWithArgs),
                })
            })
        }

        // console.log(e);
        // wx.downloadFile({
        //   url: e.currentTarget.dataset.url,
        //   success: function(res) {
        //     console.log(res);
        //     const tempFilePath = res.tempFilePath;
        //     // 保存文件
        //     wx.saveFile({
        //       tempFilePath,
        //       success: function(res) {
        //         console.log(res);
        //         const savedFilePath = res.savedFilePath;
        //       },
        //       fail: function(err) {
        //         console.log('保存失败：', err)
        //       }
        //     });
        //   },
        //   fail: function(err) {
        //     console.log('下载失败：', err);
        //   },
        // });
    },
    openFile(e) {
        console.log(e)
        wx.downloadFile({
            url: e.currentTarget.dataset.src,
            success: function(res) {
                const filePath = res.tempFilePath
                wx.openDocument({
                    filePath: filePath,
                    success: function(res) {
                        console.log('打开文档成功')
                    }
                })
            }
        })
    },
    //点击机构跳转详情
    orgClick: function(e) {
        console.log(e);
        let listId = e.currentTarget.dataset.id;
        wx.navigateTo({
            url: '/pages/InstitutionalDetails/index?id=' + listId,
        })
    },
    //调取地图
    getLocation() {
        let that = this;
        wx.getLocation({
            type: 'wgs84',
            success(res) {
                console.log(res);
                // const latitude = that.data.basicForm.startLat;
                // const longitude = that.data.basicForm.startLng;
                wx.openLocation({
                    latitude: that.data.basicForm.startLat,
                    longitude: that.data.basicForm.startLng,
                    scale: 18,
                    name: that.data.basicForm.detailedAddress
                })

            }
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        var that = this;
        console.log(options.id);
        that.setData({
            id: options.id,
            getCurrentPageUrlWithArgs: util.getCurrentPageUrlWithArgs()
        })
        this.getById();
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