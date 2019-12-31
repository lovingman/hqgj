// pages/RegProgress/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        steps: [
            {
                text: '资料提交成功，等待审核',
                desc: '2019-07-12 12:40'
            },
            {
                text: '资料审核通过，开始办理',
                desc: ''
            },
            {
                text: '人工到工商局窗口审核名称。确定名称100%可用',
                desc: ''
            },
            {
                text: '收集所有新办企业需要提供的资料',
                desc: ''
            },
            {
                text: '资料达到工商局标准后上传工商“全程电子化业务系统”',
                desc: ''
            },
            {
                text: '资料达到工商局标准后上传工商“全程电子化业务系统”',
                desc: ''
            },
            {
                text: '签字完后后，资料提交到工商局内部系统',
                desc: ''
            },
            
            {
                text: '工商审核通过后，3个工作日下发执照（若资料有问题，工商局退回资料，重新修改，重新3-5步，直到审核通过）',
                desc: ''
            },
            {
                text: '已下发执照，恭喜您注册成功',
                desc: ''
            }
        ]
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },
    //前往修改
    updata(){
      wx.navigateTo({
        url: '../RegProcess/index?id=' + '6f5d86ae16534050a043ea57d679ae00',
      })
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