<template>
    <div class="container">
        <div class="title">机构创建</div>
        <div class="handle-box">
            <el-form :model="addform" :rules="addrules" class="demo-ruleForm" label-width="100px" ref="ruleForm">
                <el-form-item label="类型:" prop="type">
                    <el-radio-group style="margin-left: 20px" v-model="addform.type">
                        <el-radio :label="1" @click.native.prevent="clickitem(1)">律师事务所</el-radio>
                        <el-radio :label="2" @click.native.prevent="clickitem(2)">会计事务所</el-radio>
                        <el-radio :label="3" @click.native.prevent="clickitem(3)">培训机构</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="机构名称:" prop="orgName">
                    <el-input v-model.trim="addform.orgName" maxlength="50" show-word-limit placeholder="请输入机构名称" style="width: 30%"></el-input>
                </el-form-item>
                <el-form-item label="logo:" prop="fmUrlarr">
                    <el-upload
                            :before-upload="beforeAvatarUpload"
                            :class="{hides:hideUpload}"
                            :file-list="addform.fmUrlarr"
                            :http-request="uploadServerEdit"
                            :limit="limitImgCount"
                            :on-preview="unitPictureCardPreview"
                            :on-remove="unitPictrueRemove"
                            :on-success="unitPictrueUploadSuccess"
                            action="fakeaction"
                            list-type="picture-card">
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <div class="tips" style="font-size:12px;">支持jpg.png，大小不超过10M</div>
                    <el-dialog :visible.sync="dialogVisible" append-to-body>
                        <img :src="dialogImageUrl" alt="" width="100%">
                    </el-dialog>
                </el-form-item>
                <el-form-item label="联系方式:" prop="contactPersonTel">
                    <el-input v-model.trim="addform.contactPersonName" placeholder="联系人姓名" style="width: 12%"></el-input>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <el-input v-model.trim="addform.contactPersonTel" placeholder="联系电话" style="width: 16%"></el-input>
                </el-form-item>
                <el-form-item label="地址:" prop="areaCodes">
                    <el-cascader
                            :options="areaCodeOptions"
                            :props="areaCodeProps"
                            ref="myCascader"
                            change-on-select
                            clearable
                            placeholder="请选择行政区划"
                            style="width: 30%"
                            v-model.trim="addform.areaCodes"/>
                </el-form-item>
                <el-form-item prop="orgAddress">
                    <el-input
                            v-model.trim="addform.orgAddress"
                            placeholder="请输入机构详细地址，xx社区xx路xx号(xx小区xx栋xx楼xx号）"
                            style="width: 30%"
                            type="textarea"></el-input>
                </el-form-item>
                <el-form-item label="简介:" prop="content">
                    <el-input
                            v-model.trim="addform.content"
                            maxlength="1000"
                            placeholder="请输入机构简介"
                            rows="4"
                            show-word-limit
                            style="width: 30%"
                            type="textarea"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="back">取消</el-button>
                    <el-button @click="handleadd('ruleForm')" type="primary">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {create} from "@/api/hqgj/service";
    import {getAreaTree, getDict,fileUpImg} from "@/api/sys";
    export default {
        name: "create",
        data() {
            return {
                limitImgCount: 1, //限制文件上传数量
                hideUpload: false, //隐藏上传形象照按钮
                dialogVisible: false, //dialog预览形象照属性
                dialogImageUrl: '', //显示预览的形象照
                allAddress: '',//详细完整地址
                areaCodeOptions: [], //行政区划
                areaCodeProps: {
                    value: "id",
                    label: "text"
                },
                addform: {
                    orgName: "",
                    creditCode: "",
                    fmUrl:"",
                    fmUrlarr:[],
                    contactPersonName: "",
                    contactPersonTel: "",
                    areaCode: "",
                    orgAddress: "",
                    completeAddress:"",
                    content: "",
                    type: "",
                    areaCodes: []
                },
                addrules: {
                    orgName: [
                        {required: true, message: "请输入机构名称", trigger: "blur"},
                    ],
                    // fmUrlarr: [
                    //     {required: true, message: "请上传机构封面", trigger: "blur"}
                    // ],
                    contactPersonTel: [
                        {required: true, message: "请输入联系方式", trigger: "blur"},
                    ],
                    areaCodes: [
                        {required: true, message: "请选择所属行政区划", trigger: "change"}
                    ],
                    orgAddress: [
                        {required: true, message: "请输入机构详细地址", trigger: "blur"},
                    ],
                    content: [
                        {required: true, message: "请输入机构简介", trigger: "blur"},
                    ],
                    type: [
                        {required: true, message: "请选择机构类型", trigger: "change"}
                    ],
                },

            };
        },
        created() {
            this.AreaCodeQuery();
        },
        methods: {
            handleadd(formName){
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.allAddress = this.$refs.myCascader.getCheckedNodes(this.addform.areaCodes)[0].pathLabels.join('').replace(/,/g,"");
                        for (let e of this.addform.areaCodes) {
                            this.addform.areaCode = e;
                        }
                        this.addform.completeAddress = this.allAddress + this.addform.orgAddress;
                        create(this.addform).then(response => {
                            if (response.status == 1) {
                                this.$message.success("创建成功");
                                this.back();
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            back() {
                this.$router.push({path: "/hqgj/BasicData/service"});
            },
            clickitem(e) {
                e === this.addform.type ? this.addform.type = '' : this.addform.type = e
            },
            //获取行政区划数据
            AreaCodeQuery() {
                getAreaTree({pid: 430702, type: 1, hasSelf: "true"})
                    .then(response => {
                        this.areaCodeOptions = response.data;
                    })
            },
            //机构封面上传
            unitPictrueUploadSuccess(response, file, fileList) {
                if (response.status == 1) {
                    var imgArr = this.addform.fmUrlarr;
                    var tempArr = [];
                    imgArr.push({
                        url: response.data
                    }); // push response.data进去 里面含3个字段
                    if (imgArr.length > 0) {
                        imgArr.forEach(item => {
                            tempArr.push(item.url);
                        });
                    }
                    this.addform.fmUrlarr = imgArr; //更新data数据
                    this.addform.fmUrl = tempArr.join(",");
                    if (imgArr.length >= this.limitImgCount) {
                        this.hideUpload = true; //隐藏上传按钮
                    }
                    this.hideUpload = true; //隐藏上传按钮
                }
            },
            //编辑机构封面上传服务器
            uploadServerEdit(param) {
                var that = this;
                // 获取文件对象
                let file = param.file;
                // 创建一个HTML5的FileReader对象
                var reader = new FileReader();
                //创建一个img对象
                var img = new Image();
                let filename = param.filename;
                if (file) {
                    reader.readAsDataURL(file);
                }
                var newUrl = null;
                reader.onload = e => {
                    let base64Str = reader.result.split(",")[1];
                    img.src = e.target.result;
                    // base64地址图片加载完毕后执行
                    img.onload = function () {
                        // 缩放图片需要的canvas（也可以在DOM中直接定义canvas标签，这样就能把压缩完的图片不转base64也能直接显示出来）
                        var canvas = document.createElement("canvas");
                        var context = canvas.getContext("2d");
                        // 图片原始尺寸
                        var originWidth = this.width;
                        var originHeight = this.height;
                        // 最大尺寸限制，可通过设置宽高来实现图片压缩程度
                        var maxWidth = that.globalData.albumWidth * 2,
                            maxHeight = that.globalData.albumHeight * 2;
                        // 目标尺寸
                        var targetWidth = originWidth,
                            targetHeight = originHeight;
                        // 图片尺寸超过最大尺寸的限制
                        if (originWidth > maxWidth || originHeight > maxHeight) {
                            if (originWidth / originHeight > maxWidth / maxHeight) {
                                // 更改宽度，按照宽度限定尺寸
                                targetWidth = maxWidth;
                                targetHeight = Math.round(
                                    maxWidth * (originHeight / originWidth)
                                );
                            } else {
                                targetHeight = maxHeight;
                                targetWidth = Math.round(
                                    maxHeight * (originWidth / originHeight)
                                );
                            }
                        }
                        //对图片进行缩放
                        canvas.width = targetWidth;
                        canvas.height = targetHeight;
                        // 清除画布
                        context.clearRect(0, 0, targetWidth, targetHeight);
                        // 图片压缩
                        context.drawImage(img, 0, 0, targetWidth, targetHeight);
                        /*第一个参数是创建的img对象；第二三个参数是左上角坐标，后面两个是画布区域宽高*/
                        //压缩后的base64文件
                        newUrl = canvas.toDataURL("image/jpeg", 0.7);
                        // 根据后台需求数据格式
                        const formData = new FormData();
                        // 文件对象
                        let obj = {};
                        if (that.submitType == "edit") {
                            that.actionUrls = "";
                            // obj.projectId = that.byIdData.id;
                            obj.coverUrl = newUrl;
                        } else {
                            that.actionUrls = "/hqgj-portal/www/uploadFileBase";
                            obj.file = newUrl;
                        }
                        that.imageUpload(obj);
                    };
                };
            },
            //机构封面上传
            imageUpload(obj) {
                fileUpImg(this.actionUrls, obj).then(response => {
                    if (response.status == 1) {
                        var imgArr = this.addform.fmUrlarr;
                        var tempArr = [];
                        imgArr.push({url: response.data}); // push response.data进去 里面含3个字段
                        if (imgArr.length > 0) {
                            imgArr.forEach(item => {
                                tempArr.push(item.url);
                            });
                        }
                        this.addform.fmUrlarr = imgArr; //更新data数据
                        this.addform.fmUrl = tempArr.join(",");
                        if (imgArr.length >= this.limitImgCount) {
                            this.hideUpload = true; //隐藏上传按钮
                        }
                    } else {
                        this.$message({
                            message: response.message,
                            type: "warning"
                        });
                    }
                });
            },
            //限制用户上传的机构封面格式和大小
            beforeAvatarUpload(file) {
                var isRightType = /^image\/(jpeg|png|jpg)$/.test(file.type);
                var imgSize = file.size / 1024 / 1024 < 5;
                if (!isRightType) {
                    this.$message.warning("上传头像图片只能是 jpg、jpeg、png 格式!");
                }
                if (!imgSize) {
                    this.$message.warning("上传头像图片大小不能超过 5MB!");
                }
                return isRightType && imgSize;
            },
            // 移除机构封面
            unitPictrueRemove(file, fileList) {
                console.log(file);
                console.log(fileList);
                var imgArrTemp = this.addform.fmUrlarr;
                if (file.status == "success") {
                    imgArrTemp.forEach((item, i) => {
                        var url = file.url; // 接口返回值没有逗号，加一个逗号
                        if (item.url == url) {
                            imgArrTemp.splice(i, 1);
                        }
                    });
                    this.addform.fmUrlarr = imgArrTemp; //更新data imgArr 数据
                    var tempArr = [];
                    if (imgArrTemp.length > 0) {
                        imgArrTemp.forEach((item, i) => {
                            tempArr.push(item.url);
                        });
                    }
                    this.addform.fmUrl = tempArr.join(","); //更新data pictureStr 数据
                    if (imgArrTemp.length < this.limitImgCount) {
                        this.hideUpload = false; //显示上传按钮
                    }
                }
            },
            // 形象照预览
            unitPictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },

        }
    }
</script>

<style scoped lang="less">
    .container {
        background-color: #fff;
    }
    .hides /deep/ .el-upload--picture-card {
        display: none;
    }
    .hides /deep/.el-upload-list--picture-card .el-upload-list__item-thumbnail{
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .title {
        font-size: 16px;
        font-weight: bold;
        padding: 20px 30px;
        border-bottom: 1px solid #eee;
    }
    .handle-box{
        padding-top: 40px;
        padding-bottom: 40px;
    }
</style>
