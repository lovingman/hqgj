// pages/RegProcess/index.js
var cfg = require("../../utils/config.js");
var request = require("../../utils/request.js");
var validate = require('../../utils/validate.js');
import Toast from '../../vant/weapp/toast/toast';
Page({

    /**
     * 页面的初始数据
     */
    data: {
        id: '',
        dicObj: {},
        fileList: [], //图片上传文件
        areaList: [],
        areaCodeList: [],
        active: 0, //tab切换
        phoneBar: false,
        submitBtn:true,
        username: "",
        showPickerAreas: false, //是否显示picker
        dataCollection: [],
        flagVail: {
            type5: '',
            type8: ''
        },
        formCollection: [{
                companyName: '', // 企业名称
                readyName: '', // 备用名称
                regBonus: '', // 注册资金
                areaCode: '', //公司地址
                areaType: '请选择区域', //地址文字信息
                companyAddress: '', //公司详细地址
                manageExtent: '' //经营范围
            },
            {
                type1: [{ //法人
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
                }],
                type2: [{ //监事
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
                }]
            },
            {
                type3: [{
                    name: "",
                    sharesProportion: "",
                    type: 3,
                    basicAnnexes: [{
                            fileURL: "",
                            fileName: "股东身份证附件",
                            fileType: 1
                        },
                        {
                            fileURL: "",
                            fileName: "股东身份证附件",
                            fileType: 2
                        }
                    ]
                }]
            },
            {
                type4: [{
                    name: "",
                    mobile: "",
                    type: 4,
                    basicAnnexes: [{
                            fileURL: "",
                            fileName: "财务身份证附件",
                            fileType: 1
                        },
                        {
                            fileURL: "",
                            fileName: "财务身份证附件",
                            fileType: 2
                        }
                    ]
                }]
            }, {
                type6: {
                    name: "房产证复印件",
                    type: 6,
                    basicAnnexes: []
                },
                type7: {
                    name: "房东营业执照",
                    type: 7,
                    basicAnnexes: []
                },
                type5: {
                    name: "租赁合同复印件",
                    type: 5,
                    basicAnnexes: []
                },
                type8: {
                    name: "房屋无偿提供使用证明",
                    type: 8,
                    basicAnnexes: []
                }
            }, {
                option1: {
                    type: 1,
                    option: 0,
                    content: ""
                },
                option57: {
                    type: 57,
                    options: [],
                    option: '',
                },
                option58: {
                    type: 58,
                    option: 0,
                },
                option59: {
                    type: 59,
                    option: 0,
                },
                option60: {
                    type: 60,
                    option: 0,
                }
            }
        ],
        vailForm: [{
                companyName: false,
                readyName: false,
                regBonus: false,
            },
            {
                type1: [{
                    name: false,
                    mobile: false,
                }],
                type2: [{
                    name: false,
                    mobile: false
                }]
            },
            {
                type3: [{
                    name: false,
                    sharesProportion: false,
                }]
            },
            {
                type4: [{
                    name: false,
                    mobile: false,
                }]
            }
        ],
        vailMap: {
            companyName: 'companyName',
            readyName: 'companyName',
            regBonus: 'floatNum4',
            name: 'chineseName',
            mobile: 'mobilePhone',
            sharesProportion: 'float2Num2',
        }
    },
    getData() { //获取创业服务包详情
        let that = this;
        if (!that.data.id) {
            return;
        }
        request.getJSON(cfg.serveBusinessPreviewInfoUrl, {
            id: that.data.id
        }).then(rst => {
            const res = rst.data;
            if (res.status == 1) {
                let x = res.data.serveBusiness;
                let r = [];
                that.formatAreaCode(that.data.areaCodeList, x.areaCode, r)
                console.log(r);
                let _1 = {
                    companyName: x.companyName, // 企业名称
                    readyName: x.readyName, // 备用名称
                    regBonus: x.regBonus, // 注册资金
                    areaCode: x.areaCode, //公司地址
                    areaType: r.join(' '), //地址文字信息
                    companyAddress: x.companyAddress, //公司详细地址
                    manageExtent: x.manageExtent //经营范围
                }
                that.data.dataCollection.push(_1);
                that.formatTypeList(res.data.businessDetailList);
                that.formatOptionList(res.data.businessAppendList);
                that.setData({
                    formCollection: that.data.dataCollection
                })
            }
        })
    },
    phoneBarCloseHandler() {
        this.setData({
            phoneBar: false
        })
    },
    changeTabHandler(e) {
        this.data.active = e.detail.index;
    },
    formatTypeList(data) {
        let that = this;
        let b = {
            type1: [],
            type2: [],
            type3: [],
            type4: [],
            type5: {},
            type6: {},
            type7: {},
            type8: {}
        }
        for (let i of data) {
            if (i.type < 5) {
                b['type' + i.type].push(i)
            } else {
                b['type' + i.type] = i;
            }
        }
        that.data.dataCollection.push({
            type1: b.type1,
            type2: b.type2,
        });
        for (let n = 0; n < b.type2.length; n++) {
            that.data.vailForm[1].type2.push({
                name: false,
                mobile: false
            });
        }
        that.data.dataCollection.push({
            type3: b.type3,
        })
        for (let n = 0; n < b.type3.length; n++) {
            that.data.vailForm[2].type3.push({
                name: false,
                sharesProportion: false
            });
        }
        that.data.dataCollection.push({
            type4: b.type4,
        })
        that.data.dataCollection.push({
            type5: b.type5,
            type6: b.type6,
            type7: b.type7,
            type8: b.type8,
        })
    },
    formatOptionList(data) {
        let r = {};
        for (let i of data) {
            if (i.type == '57') {
                i.options = i.option.split(',')
            }
            r['option' + i.type] = i;
        }
        this.data.dataCollection.push(r);
    },
    formatAreaCode(list, areaCode, r) {
        for (let i of list) {
            if (areaCode.indexOf(i.id) == 0) {
                r.push(i.text);
                if (i.children && i.children.length) {
                    this.formatAreaCode(i.children, areaCode, r);
                } else {
                    return r;
                }
            }
        }
    },
    onSelectChange(e) {
        let type = e.currentTarget.dataset.type;
        if (type == '57') {
            this.data.formCollection[5]['option' + type].options = e.detail
        } else {
            this.data.formCollection[5]['option' + type].option = e.detail
        }
        let f = `formCollection[5].option${type}`
        this.setData({
            [f]: this.data.formCollection[5]['option' + type]
        });
    },
    //获取字典
    getDic() {
        let that = this;
        let datas = {
            categoryIds: '57,58,59,60'
        }
        request.getJSON(cfg.getByCategoryUrl, datas).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                that.setData({
                    dicObj: res.data
                })
            }
        })
    },
    changes(e) { //控制行政区划
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
            let res = rst.data;
            if (res.status == 1) {
                this.getData();
                let obj = [];
                // obj[]  
                for (var i = 0; i < res.data[0].children.length; i++) {
                    obj = obj.concat(res.data[0].children[i].children)
                }
                that.data.areaCodeList = res.data;
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
            }
        })
    },
    //添加监事
    addSupervisor() {
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
        this.data.formCollection[1].type2.push(obj);
        this.data.vailForm[1].type2.push({
            name: false,
            mobile: false
        });
        let f = 'formCollection[1].type2'
        let n = 'vailForm[1].type2'
        this.setData({
            [f]: this.data.formCollection[1].type2,
            [n]: this.data.vailForm[1].type2
        })
    },
    removeSupervisor(e) {
        let index = e.currentTarget.dataset.index;
        this.data.formCollection[1].type2.splice(index, 1);
        this.data.vailForm[1].type2.splice(index, 1);
        let n = 'vailForm[1].type2'
        let f = 'formCollection[1].type2'
        this.setData({
            [f]: this.data.formCollection[1].type2,
            [n]: this.data.vailForm[1].type2
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
        this.data.formCollection[2].type3.push(obj);
        this.data.vailForm[2].type3.push({
            name: false,
            sharesProportion: false
        });
        let f = 'formCollection[2].type3';
        let n = 'vailForm[2].type3'
        this.setData({
            [f]: this.data.formCollection[2].type3,
            [n]: this.data.vailForm[2].type3
        })
    },
    removeShareholder(e) {
        let index = e.currentTarget.dataset.index;
        this.data.formCollection[2].type3.splice(index, 1);
        this.data.vailForm[2].type3.splice(index, 1);
        let f = 'formCollection[2].type3'
        let n = 'vailForm[2].type3'
        this.setData({
            [f]: this.data.formCollection[2].type3,
            [n]: this.data.vailForm[2].type3
        })
    },
    //图片上传前
    afterRead(e) {
        let that = this;
        let obj = e.currentTarget.dataset.obj;
        let type = e.currentTarget.dataset.type;
        let index = e.currentTarget.dataset.index;
        const {
            file
        } = e.detail;
        //当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
        wx.uploadFile({
            url: 'https://test.huacainfo.com/hqgj-portal/www/uploadFile', // 仅为示例，非真实的接口地址
            filePath: file.path,
            name: 'file',
            formData: {
                file: file.path
            },
            success(res) {
                // 上传完成需要更新 fileList
                // const { fileList = [] } = this.data;
                // fileList.push({ ...file, url: res.data });
                that.setData({
                    [obj + '[' + index + '].basicAnnexes[' + type + '].fileURL']: JSON.parse(res.data).data
                })
            }
        });
    },
    afterRead_two(e) {
        let that = this;
        let obj = e.currentTarget.dataset.obj;
        let type = e.currentTarget.dataset.type;
        const {
            file
        } = e.detail;
        //当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
        wx.uploadFile({
            url: 'https://test.huacainfo.com/hqgj-portal/www/uploadFile', // 仅为示例，非真实的接口地址
            filePath: file.path,
            name: 'file',
            formData: {
                file: file.path
            },
            success(res) {
                // 上传完成需要更新 fileList
                // const { fileList = [] } = this.data;
                // fileList.push({ ...file, url: res.data });
                that.data[obj][4][type].basicAnnexes.push({
                    fileURL: JSON.parse(res.data).data
                });
                that.setData({
                    [obj + '[4].' + type + '.basicAnnexes']: that.data[obj][4][type].basicAnnexes
                })
            }
        });
    },
    //其它双向绑定
    bindinput(e) {
        let model = e.currentTarget.dataset.model;
        let index = e.currentTarget.dataset.index;
        let feild = 'formCollection[' + index + '].' + model;
        let v = e.detail;
        if (typeof(v) == 'object') {
            v = v.value
        }
        this.setData({
            [feild]: v
        })
    },
    focusHandler(e) {
        let model = e.currentTarget.dataset.model;
        let index = e.currentTarget.dataset.index;
        let feild = 'vailForm[' + index + '].' + model;
        this.setData({
            [feild]: false
        })
    },
    focusObjHandler(e) {
        let vail = e.currentTarget.dataset.vail;
        let model = e.currentTarget.dataset.model;
        let index = e.currentTarget.dataset.index;
        this.setData({
            [vail + '[' + index + '].' + model]: false
        })
    },
    blurHandler(e) {
        let model = e.currentTarget.dataset.model;
        let index = e.currentTarget.dataset.index;
        let that = this;
        let feild = 'vailForm[' + index + '].' + model;
        console.log(e.detail);
        if (that.validateHandler(model, e.detail.value)) {
            that.setData({
                [feild]: true,
            })
        }
    },
    blurObjHandler(e) {
        let vail = e.currentTarget.dataset.vail;
        let model = e.currentTarget.dataset.model;
        let index = e.currentTarget.dataset.index;

        let that = this;
        let feild = vail + '[' + index + '].' + model;
        console.log(e.detail);
        if (that.validateHandler(model, e.detail.value)) {
            that.setData({
                [feild]: true,
            })
        }
    },
    validateHandler(target, val) {
        let that = this;
        if (validate[that.data.vailMap[target]].test(val)) {
            console.log('ok')
            return false;
        }
        console.log('no ok')
        return true
    },
    bindObjInput(e) {
        let obj = e.currentTarget.dataset.obj;
        let model = e.currentTarget.dataset.model;
        let index = e.currentTarget.dataset.index;
        this.setData({
            [obj + '[' + index + '].' + model]: e.detail
        })
    },
    bindinput_two(e) {
        this.setData({
            [`formCollection[5].option1.content`]: e.detail
        })
    },
    //股东双向绑定
    bindinputs(e) {
        this.data.shareholder[e.currentTarget.dataset.index][e.currentTarget.dataset.model] = e.detail;
        this.setData({
            shareholder: this.data.shareholder
        })
    },
    //监事双向绑定
    bindinputy(e) {
        this.data.supervisor[e.currentTarget.dataset.index][e.currentTarget.dataset.model] = e.detail;
        this.setData({
            supervisor: this.data.supervisor
        })
    },
    showPickerArea() {
        this.setData({
            showPickerAreas: !this.data.showPickerAreas
        })
    },
    textAreaLineChange(e) {
        this.setData({
            txtHeight: e.detail.height
        })
    },
    cancelshowPickerArea() {
        this.setData({
            showPickerAreas: !this.data.showPickerAreas
        })
    },
    confirms(e) {
        let r = this.data.formCollection[0];
        r.areaCode = e.detail.value[e.detail.value.length - 1].id;
        let str = [];
        e.detail.value.forEach((item) => {
            str.push(item.text)
        })
        r.areaType = str.join(',');
        this.setData({
            [`formCollection[0]`]: r,
            showPickerAreas: !this.data.showPickerAreas
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.getAreaTree();
        if (options.id) {
            this.data.id = options.id
        }
        if(options.flag){
            this.setData({
                submitBtn:false
            })
        }
    },
    clickTab(e) {
        this.setData({
            active: e.detail.index
        })
    },
    nextClick() {
        this.setData({
            active: this.data.active + 1
        })
    },
    preClick() {
        this.setData({
            active: this.data.active - 1
        })
    },
    submit() {
        let that = this;
        let j = 6
        if (that.data.id) {
            j = 1
        }
        for (let i = 0; i < j; i++) {
            if (i == 5) {
                continue;
            }
            let k = that.deeploop(that.data.formCollection[i])
            if (k) {
                Toast.fail({
                    message: "有未填写信息",
                    zIndex: 1000
                });
                that.setData({
                    active: i
                })
                return;
            }
        }
        for (let n = 0; n < 4; n++) {
            let k = that.deeploopFlase(that.data.vailForm[n])
            if (k) {
                Toast.fail({
                    message: "填写类容格式错误",
                    zIndex: 1000
                });
                that.setData({
                    active: n
                })
                return;
            }
        };
        let b = that.data.formCollection[4];
        let flag = false;
        if (b.type6.basicAnnexes.length < 1) {
            flag = true;
        }
        if (b.type7.basicAnnexes.length < 1) {
            flag = true;
        }
        if ((b.type5.basicAnnexes.length + b.type8.basicAnnexes.length) < 1) {
            flag = true;
        }
        if (flag) {
            Toast.fail({
                message: "有未填写信息",
                zIndex: 1000
            });
            that.setData({
                active: 4
            })
            return;
        }
        let obj = {
            serveBusiness: {},
            serveBusinessDetail: [],
            serveBusinessAppend: []
        };
        for (let item of that.data.formCollection) {
            for (let i in item) {
                if (i.indexOf('type') == 0) {
                    if (Array.isArray(item[i])) {
                        for (let r of item[i]) {
                            obj.serveBusinessDetail.push(r);
                        }
                    } else {
                        obj.serveBusinessDetail.push(item[i]);
                    }
                } else if (i.indexOf('option') == 0) {
                    if (i == 'option57') {
                        item[i].option = item[i].options.join(',');
                    };
                    obj.serveBusinessAppend.push(item[i]);
                } else {
                    obj.serveBusiness[i] = item[i];
                }
            }
        }
        if (that.data.id) {
            obj.serveBusiness.id = that.data.id;
            this.upDatas(obj);
        } else {
            this.addData(obj);
        }
    },
    deeploop(data) {
        let that = this;
        for (let keys in data) { // 遍历目标
            if (data.hasOwnProperty(keys)) {
                console.log(JSON.stringify(data[keys]));
                if (data[keys] && typeof data[keys] === 'object') { // 如果值是对象，就递归一下
                    if (that.deeploop(data[keys])) {
                        return true;
                    };
                } else { // 如果不是，就直接赋值
                    if (!data[keys] && data[keys] !== 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    },

    deeploopFlase(data) {
        let that = this;
        for (let keys in data) { // 遍历目标
            if (data.hasOwnProperty(keys)) {
                console.log(JSON.stringify(data[keys]));
                if (data[keys] && typeof data[keys] === 'object') { // 如果值是对象，就递归一下
                    if (that.deeploopFlase(data[keys])) {
                        return true;
                    };
                } else { // 如果不是，就直接赋值
                    if (data[keys]) {
                        return true;
                    }
                }
            }
        }
        return false;
    },

    addData(obj) { //新增
        let that = this;
        request.postJSON(cfg.serveBusinessCreateUrl, obj).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                Toast.success({
                    message: "申请注册成功\n返回个人中心",
                    zIndex: 2000,
                    onClose() {
                        wx.reLaunch({
                            url: '/pages/PersonalCenter/index',
                        })
                    }
                });
            } else {
                Toast.fail(res.message);
            }
        })
    },
    upDatas(obj) { //修改
        let that = this;
        request.postJSON(cfg.serveBusinessUpdateUrl, obj).then(rst => {
            let res = rst.data;
            if (res.status == 1) {
                Toast.success({
                    message: "修改成功\n返回个人中心",
                    zIndex: 2000,
                    onClose() {
                        wx.reLaunch({
                            url: '/pages/PersonalCenter/index',
                        })
                    }
                });
            } else {
                Toast.fail(res.message);
            }
        })
    },
    removeImgHandler(e) {
        let that = this;
        let obj = e.currentTarget.dataset.obj;
        let type = e.currentTarget.dataset.type;
        let index = e.currentTarget.dataset.index;
        that.data[obj][4][type].basicAnnexes.splice(index, 1);
        that.setData({
            [obj + '[4].' + type + '.basicAnnexes']: that.data[obj][4][type].basicAnnexes
        })
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
        this.getDic();
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {

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
    }
})