// pages/RegProcess/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    dicObj: {},
    fileList: [], //图片上传文件
    active: 0, //tab切换
    result1: {
      type: 1,
      option: [],
    },
    result2: {
      type: 2,
      option: [],
    },
    result3: {
      type: 3,
      option: [],
    },
    result4: {
      type: 4,
      option: [],
    },
    special: {
      type: 5,
      option: 0,
      content: ""
    },
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
      areaType: '请选择区域',
      companyAddress: '',
      manageExtent: ''
    },
    finance: { //财务
      name: "",
      mobile: "",
      type: 4,
      basicAnnexes: [{
        fileURL: "",
        fileName: "财务身份证附件",
        fileType: 1
      }, {
        fileURL: "",
        fileName: "财务身份证附件",
        fileType: 2
      }]
    },
    legalPerson: { //法人
      name: "",
      mobile: "",
      type: 1,
      basicAnnexes: [{
        fileURL: "",
        fileName: "法人身份证附件",
        fileType: 1
      }, {
        fileURL: "",
        fileName: "法人身份证附件",
        fileType: 2
      }]
    },
    shareholder: [{ //股东
      name: "",
      sharesProportion: "",
      type: 3,
      basicAnnexes: [{
        fileURL: "",
        fileName: "股东身份证附件",
        fileType: 1
      }, {
        fileURL: "",
        fileName: "股东身份证附件",
        fileType: 2
      }]
    }],
    supervisor: [{ //监事
      name: "",
      mobile: "",
      type: 2,
      basicAnnexes: [{
        fileURL: "",
        fileName: "监事身份证附件",
        fileType: 1
      }, {
        fileURL: "",
        fileName: "监事身份证附件",
        fileType: 2
      }]
    }],
    contract1: { //房产证复印件
      name: "房产证复印件",
      type: 6,
      basicAnnexes: [{
        fileName: "房产合同复印件",
        fileURL: ""
      }]
    },
    contract2: { //房东营业执照
      name: "房东营业执照",
      type: 7,
      basicAnnexes: [{
        fileName: "房东营业执照",
        fileURL: ""
      }]
    },
    contract3: { //租赁合同复印件
      name: "租赁合同复印件",
      type: 5,
      basicAnnexes: [{
        fileName: "租赁合同复印件",
        fileURL: ""
      }]
    },
    contract4: { //房屋无偿提供使用证明
      name: "房屋无偿提供使用证明",
      type: 8,
      basicAnnexes: [{
        fileName: "房屋无偿提供使用证明",
        fileURL: ""
      }]
    },
    areaList: []
  },
  getData(id) { //获取创业服务包详情
    let that = this;
    request.getJSON(cfg.serveBusinessPreviewInfoUrl, {
      id: id
    }).then(rst => {
      const res = rst.data;
      console.log(res.data)
      if (res.status == 1) {
        this.data.supervisor = [];
        this.data.shareholder = [];
        let businessDetailList = res.data.businessDetailList;
        let businessAppendList = res.data.businessAppendList;
        let serveBusiness = res.data.serveBusiness;
        businessDetailList.forEach((item)=>{
          if (item.type == 1){
            that.data.legalPerson = item;
          }
          if(item.type == 2){
            this.data.supervisor.push(item);
          }
          if(item.type == 3){
            this.data.shareholder.push(item);
          }
          if(item.type == 4) {
            this.data.finance = item;
          }
          if (item.type == 5) {
            this.data.contract3 = item;
          }
          if (item.type == 6) {
            this.data.contract1 = item;
          }
          if (item.type == 7) {
            this.data.contract2 = item;
          }
          if (item.type == 8) {
            this.data.contract4 = item;
          }
        })
        businessAppendList.forEach((item)=>{
          if (item.type == 1){
            item.option = item.option.split(',');
            that.data.result1 = item;
          }
          if (item.type == 2) {
            that.data.result2 = item;
          }
          if (item.type == 3) {
            that.data.result3 = item;
          }
          if (item.type == 4) {
            that.data.result4 = item;
          }
          if (item.type == 5) {
            that.data.special = item;
          }
        })
        that.setData({
          serveBusiness: serveBusiness,
          legalPerson: that.data.legalPerson,
          supervisor: that.data.supervisor,
          shareholder: that.data.shareholder,
          finance: that.data.finance,
          contract1: that.data.contract1,
          contract2: that.data.contract2,
          contract3: that.data.contract3,
          contract4: that.data.contract4,
          result1: that.data.result1,
          result2: that.data.result2,
          result3: that.data.result3,
          result4: that.data.result4,
          special: that.data.special
        })
      }
    })

  },
  onChange1(event) {
    this.data.result1.option = event.detail
    this.setData({
      result1: this.data.result1
    });
    console.log(this.data.result1)
  },
  onChange2(event) {
    this.data.result2.option = event.detail
    this.setData({
      result2: this.data.result2
    });
    console.log(this.data.result2)
  },
  onChange3(event) {
    this.data.result3.option = event.detail
    this.setData({
      result3: this.data.result3
    });
    console.log(this.data.result3)
  },
  onChange4(event) {
    this.data.result4.option = event.detail
    this.setData({
      result4: this.data.result4
    });
    console.log(this.data.result4)
  },
  //获取字典
  getDic() {
    let that = this;
    let datas = {
      categoryIds: '57,58,59,60'
    }
    request.getJSON(cfg.getByCategoryUrl, datas).then(rst => {
      console.log(rst);
      let res = rst.data;
      if (res.status == 1) {
        console.log(res.data)
        that.setData({
          dicObj: res.data
        })
      }
    })
  },
  changes(e) {  //控制行政区划
    console.log(e)
    if (e.detail.index == 1) {
      this.data.areaList.forEach((item) => {
        if (item.className == "column2") {
          item.values = e.detail.value[e.detail.index].children;
        }
      })
    }
    this.setData({
      areaList: this.data.areaList
    })
  },
  //获取武陵区行政区划
  getAreaTree() {
    let that = this;
    request.getJSON(cfg.getAreaTreeUrl + "?pid=430702&type=1&hasSelf=true", {}).then(rst => {
      console.log(rst);
      let res = rst.data;
      if (res.status == 1) {
        console.log(res.data[0])
        let obj = [];
        // obj[]  
        for (var i = 0; i < res.data[0].children.length; i++) {
          obj = obj.concat(res.data[0].children[i].children)
        }
        console.log(obj)
        that.data.areaList = [{
            values: res.data,
            className: 'column0'
          },
          {
            values: res.data[0].children,
            className: 'column1'
          },
          {
            values: res.data[0].children[0].children,
            className: 'column2',
            defaultIndex: 0
          }
        ]
        that.setData({
          areaList: that.data.areaList
        })
        // this.data.areaList.province_list[res.data[0].id] = res.data[0].text;
        // console.log(this.data.areaList)
      }
    })
  },
  //添加监事
  addSupervisor(){
    let obj = {
      name: "",
      mobile: "",
      type: 2,
      basicAnnexes: [{
        fileURL: "",
        fileName: "监事身份证附件",
        fileType: 1
      }, {
        fileURL: "",
        fileName: "监事身份证附件",
        fileType: 2
      }]
    };
    this.data.supervisor.push(obj);
    this.setData({
      supervisor: this.data.supervisor
    })
  },
  //添加股东
  addShareholder() {
    let obj = {
      name: "",
      sharesProportion: "",
      type: 3,
      basicAnnexes: [{
        fileURL: "",
        fileName: "股东身份证附件",
        fileType: 1
      }, {
        fileURL: "",
        fileName: "股东身份证附件",
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
    const {
      file
    } = event.detail;
    console.log(event)
    console.log(file)
    //当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
    wx.uploadFile({
      url: 'http://192.168.2.114/hqgj-portal/www/uploadFile', // 仅为示例，非真实的接口地址
      filePath: file.path,
      name: 'file',
      formData: {
        file: file.path
      },
      success(res) {
        console.log(res)
        // 上传完成需要更新 fileList
        // const { fileList = [] } = this.data;
        // fileList.push({ ...file, url: res.data });
        if (event.currentTarget.dataset.file) { //如果有正反
          if (event.currentTarget.dataset.obj == "shareholder") {
            if (event.currentTarget.dataset.file == 1) {
              that.data.shareholder[index].basicAnnexes[0].fileURL = JSON.parse(res.data).data;
            } else {
              that.data.shareholder[index].basicAnnexes[1].fileURL = JSON.parse(res.data).data;
            }
          } else if (event.currentTarget.dataset.obj == "supervisor"){
            if (event.currentTarget.dataset.file == 1) {
              that.data.supervisor[index].basicAnnexes[0].fileURL = JSON.parse(res.data).data;
            } else {
              that.data.supervisor[index].basicAnnexes[1].fileURL = JSON.parse(res.data).data;
            }
          }else{
            if (event.currentTarget.dataset.file == 1) {
              that.data[event.currentTarget.dataset.obj].basicAnnexes[0].fileURL = JSON.parse(res.data).data;
            } else {
              that.data[event.currentTarget.dataset.obj].basicAnnexes[1].fileURL = JSON.parse(res.data).data;
            }
          }
        } else {
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
  bindinputs: function(e) {
    this.data.shareholder[e.currentTarget.dataset.index][e.currentTarget.dataset.model] = e.detail;
    this.setData({
      shareholder: this.data.shareholder
    })
  },
  //监事双向绑定
  bindinputy:function(e){
    this.data.supervisor[e.currentTarget.dataset.index][e.currentTarget.dataset.model] = e.detail;
    this.setData({
      supervisor: this.data.supervisor
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
    this.data.serveBusiness.areaCode = e.detail.value[e.detail.value.length - 1].id;
    let str = [];
    e.detail.value.forEach((item) => {
      str.push(item.text)
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
    console.log(options)
    this.getAreaTree()
    if(options.id){
      this.setData({
        id: options.id
      })
    }
    // console.log(areaList.default)
    // this.setData({
    //   areaList: areaList.default
    // })
  },
  clickTab: function(e) {
    this.setData({
      active: e.detail.index
    })
  },
  nextClick: function() {
    this.setData({
      active: this.data.active + 1
    })
  },
  preClick: function() {
    this.setData({
      active: this.data.active - 1
    })
  },
  submit() {
    let obj = {
      serveBusiness: {},
      serveBusinessDetail: [],
      serveBusinessAppend: []
    }
    obj.serveBusiness = this.data.serveBusiness;
    obj.serveBusinessDetail = [this.data.legalPerson, this.data.finance, this.data.contract1, this.data.contract2, this.data.contract3, this.data.contract4];
    if (this.data.shareholder.length > 0) {
      this.data.shareholder.forEach((item) => {
        obj.serveBusinessDetail.push(item);
      })
    }
    if (this.data.supervisor.length > 0) {
      this.data.supervisor.forEach((item) => {
        obj.serveBusinessDetail.push(item);
      })
    }
    let result1 = {
      type: 1,
      option: this.data.result1.option.join(",")
    }
    obj.serveBusinessAppend = [result1, this.data.result2, this.data.result3, this.data.result4, this.data.special];
    console.log(JSON.stringify(obj))
    if (obj.serveBusiness.id){
      this.upDatas(obj);
    }else{
      this.addData(obj);
    }
  },
  addData(obj){  //新增
    let that = this;
    request.postJSON(cfg.serveBusinessCreateUrl, obj).then(rst => {
      console.log(rst);
      let res = rst.data;
      if (res.status == 1) {
        console.log(res.data)

      }
    })
  },
  upDatas(obj) {  //修改
    let that = this;
    request.postJSON(cfg.serveBusinessUpdateUrl, obj).then(rst => {
      console.log(rst);
      let res = rst.data;
      if (res.status == 1) {
        console.log(res.data)

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
    this.getDic();
    this.getData(this.data.id);
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