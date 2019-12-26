// pages/RegProcess/index.js
var areaList = require("../../utils/area.js"); 
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    fileList: [],  //图片上传文件
    active: 0,  //tab切换
    result: ['a', 'b'],
    username: "",
    showPickerAreas: false, //是否显示picker
    serveBusinessAppend: [],
    serveBusinessDetail: [],
    serveBusiness: {
      companyName: '',
      readyName: '',
      legalPerson: '',
      regBonus: '',
      areaCode: '',
      areaType: '',
      companyAddress: '',
      manageExtent: ''
    },
    finance: {//财务
      name: "",
      mobile: "",
      basicAnnexes: [{
        fileURL: "",
        fileType: 1
      }, {
        fileURL: "",
        fileType: 2
      }]
    },
    legalPerson: {//法人
      name:"",
      mobile:"",
      basicAnnexes:[{
        fileURL:"",
        fileType:1
      }, {
          fileURL: "",
          fileType: 2
        }]
    },
    supervisor: {//监事
      name: "",
      mobile: "",
      basicAnnexes: [{
        fileURL: "",
        fileType: 1
      }, {
        fileURL: "",
        fileType: 2
      }]
    },
    contract1: {//房产证复印件
      name:"房产证复印件",
      type:6,
      basicAnnexes:[{
        fileURL:""
      }]
    },
    contract2: {//房东营业执照
      name: "房东营业执照",
      type: 7,
      basicAnnexes: [{
        fileURL: ""
      }]
    },
    contract3: {//租赁合同复印件
      name: "租赁合同复印件",
      type: 5,
      basicAnnexes: [{
        fileURL: ""
      }]
    },
    contract4: {//房屋无偿提供使用证明
      name: "房屋无偿提供使用证明",
      type: 8,
      basicAnnexes: [{
        fileURL: ""
      }]
    },
    shareholder: [{//股东
      name:"",
      sharesProportion:"",
      basicAnnexes: [{
        fileURL: "",
        fileType: 1
      }, {
        fileURL: "",
        fileType: 2
      }]
    }],
    areaList: {}
  },
  //添加股东
  addShareholder(){
    let obj = {
      name: "",
      sharesProportion: "",
      basicAnnexes: [{
        fileURL: "",
        fileType: 1
      }, {
        fileURL: "",
        fileType: 2
      }]
    };
    this.data.shareholder.push(obj);
    this.setData({
      shareholder: this.data.shareholder
    })
  },
  //图片上传前
  afterRead(event) {
    let that = this;
    let index = event.currentTarget.dataset.index;
    const { file } = event.detail;
    console.log(event)
    console.log(file)
    //当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
    wx.uploadFile({
      url: 'http://192.168.2.114/hqgj-portal/www/uploadFile', // 仅为示例，非真实的接口地址
      filePath: file.path,
      name: 'file',
      formData: { file: file.path },
      success(res) {
        console.log(res)
        // 上传完成需要更新 fileList
        // const { fileList = [] } = this.data;
        // fileList.push({ ...file, url: res.data });
        if (event.currentTarget.dataset.file){  //如果有正反
          if (event.currentTarget.dataset.obj == "shareholder") {
            if (event.currentTarget.dataset.file == 1) {
              that.data.shareholder[index].basicAnnexes[0].fileURL = JSON.parse(res.data).data;
            } else {
              that.data.shareholder[index].basicAnnexes[1].fileURL = JSON.parse(res.data).data;
            }
          } else {
            if (event.currentTarget.dataset.file == 1) {
              that.data[event.currentTarget.dataset.obj].basicAnnexes[0].fileURL = JSON.parse(res.data).data;
            } else {
              that.data[event.currentTarget.dataset.obj].basicAnnexes[1].fileURL = JSON.parse(res.data).data;
            }
          }
        }else{
          that.data[event.currentTarget.dataset.obj].basicAnnexes[0].fileURL = JSON.parse(res.data).data;
        }
        that.setData({
          [event.currentTarget.dataset.obj]: that.data[event.currentTarget.dataset.obj]
        })
        console.log(that.data[event.currentTarget.dataset.obj])
      }
    });
  },
  //其它双向绑定
  bindinput: function(e) {
    this.data[e.currentTarget.dataset.obj][e.currentTarget.dataset.model] = e.detail;
  },
  //股东双向绑定
  bindinputs:function(e){
    this.data.shareholder[e.currentTarget.dataset.index][e.currentTarget.dataset.model] = e.detail;
    this.setData({
      shareholder: this.data.shareholder
    })
  },
  showPickerArea: function() {
    console.log(123)
    this.setData({
      showPickerAreas: !this.data.showPickerAreas
    })
  },
  cancelshowPickerArea: function() {
    this.setData({
      showPickerAreas: !this.data.showPickerAreas
    })
  },
  confirms: function(e) {
    console.log(e.detail.values[e.detail.values.length - 1].code)
    console.log(e.detail.values[e.detail.values.length - 1].name)
    this.data.serveBusiness.areaCode = e.detail.values[e.detail.values.length - 1].code;
    let str = [];
    e.detail.values.forEach((item) => {
      str.push(item.name)
    })
    this.data.serveBusiness.areaType = str.join(',');
    this.setData({
      serveBusiness: this.data.serveBusiness,
      showPickerAreas: !this.data.showPickerAreas
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    console.log(areaList.default)
    this.setData({
      areaList: areaList.default
    })
  },
  nextClick: function() {
    console.log(this.data.serveBusiness)
    console.log(this.data.legalPerson)
    console.log(this.data.supervisor)
    console.log(this.data.shareholder)
    console.log(this.data.finance)
    console.log(this.data.contract1)
    console.log(this.data.contract2)
    console.log(this.data.contract3)
    console.log(this.data.contract4)
    this.setData({
      active: this.data.active + 1
    })
  },
  preClick: function() {
    this.setData({
      active: this.data.active - 1
    })
  },
  save() {
    console.log(this.data.serveBusiness)
    console.log(this.data.serveBusinessDetail)
    console.log(this.data.serveBusinessAppend)
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