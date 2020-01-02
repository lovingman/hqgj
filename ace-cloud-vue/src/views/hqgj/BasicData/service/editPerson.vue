<template>
    <div class="container">
        <div class="title">编辑机构成员</div>
        <div class="handle-box">
            <el-form :model="form" :rules="rules" class="demo-ruleForm" label-width="100px" ref="ruleForm">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="姓名:" prop="name">
                            <el-input placeholder="请输入姓名" v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="身份证号:" prop="idCard">
                            <el-input placeholder="请输入身份证号" :disabled="true" v-model="form.idCard"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="mobile">
                            <el-input placeholder="请输入联系方式" v-model="form.mobile"></el-input>
                        </el-form-item>
                        <el-form-item label="职务/职称:" prop="jobs">
                            <el-input placeholder="请输入职务/职称" v-model="form.jobs"></el-input>
                        </el-form-item>
                        <el-form-item label="擅长领域:" prop="speciality">
                            <el-tag
                                    :disable-transitions="false"
                                    :key="tag"
                                    @close="handleClose(tag)"
                                    closable
                                    v-for="tag in specialityarr">
                                {{tag}}
                            </el-tag>
                            <el-input
                                    @blur="handleInputConfirm"
                                    @keyup.enter.native="handleInputConfirm"
                                    class="input-new-tag"
                                    ref="saveTagInput"
                                    size="small"
                                    v-if="inputVisible"
                                    v-model="inputValue"
                            >
                            </el-input>
                            <el-button @click="showInput" class="button-new-tag" icon="el-icon-plus" size="small"
                                       v-else>添加
                            </el-button>
                            <div class="tips" style="font-size:12px;margin-left: 10px">不超过5个</div>
                        </el-form-item>
                        <el-form-item label="简介:" prop="content">
                            <el-input
                                    maxRows="6"
                                    maxlength="500"
                                    placeholder="请输入简介"
                                    rows="6"
                                    show-word-limit
                                    type="textarea"
                                    v-model="form.content"
                            >
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="形象照:" prop="imagePhotoarr">
                            <el-upload
                                    :before-upload="beforeAvatarUpload"
                                    :class="{hide:hideUpload}"
                                    :file-list="form.imagePhotoarr"
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
                        <!--<el-form-item label="微信二维码:" prop="wechatPhotoarr">-->
                            <!--<el-upload-->
                                    <!--:before-upload="beforeAvatarUpload2"-->
                                    <!--:class="{hide2:hideUpload2}"-->
                                    <!--:file-list="form.wechatPhotoarr"-->
                                    <!--:http-request="uploadServerEdit2"-->
                                    <!--:limit="limitImgCount"-->
                                    <!--:on-preview="unitPictureCardPreview2"-->
                                    <!--:on-remove="unitPictrueRemove2"-->
                                    <!--:on-success="unitPictrueUploadSuccess2"-->
                                    <!--action="fakeaction"-->
                                    <!--list-type="picture-card">-->
                                <!--<i class="el-icon-plus"></i>-->
                            <!--</el-upload>-->
                            <!--<div class="tips" style="font-size:12px;">支持jpg.png，大小不超过10M</div>-->
                            <!--<el-dialog :visible.sync="dialogVisible2" append-to-body>-->
                                <!--<img :src="dialogImageUrl2" alt="" width="100%">-->
                            <!--</el-dialog>-->
                        <!--</el-form-item>-->
                    </el-col>
                </el-row>
                <el-form-item style="bottom: 10px;margin-left: 300px">
                    <el-button @click="back">取消</el-button>
                    <el-button @click="handleEdit('ruleForm')" type="primary">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getByIdPerson, updatePerson} from "@/api/hqgj/service";

    export default {
        name: "createPerson",
        data() {
            return {
                inputVisible: false,
                inputValue: '',
                limitImgCount: 1, //限制文件上传数量
                hideUpload: false, //隐藏上传形象照按钮
                dialogVisible: false, //dialog预览形象照属性
                dialogImageUrl: '', //显示预览的形象照
                dialogImageUrl2: '',//显示预览的微信二维码
                dialogVisible2: false,//dialog预览微信二维码属性
                hideUpload2: false,//隐藏上传微信二维码按钮
                specialityarr: [],//擅长领域数组
                form: {
                    orgId: "",
                    name: "",
                    idCard:"",
                    mobile: "",
                    jobs: "",
                    speciality: "",
                    content: "",
                    imagePhoto: "",
                    imagePhotoarr: [],
                    wechatPhoto: "",
                    wechatPhotoarr: [],
                },
                rules: {
                    name: [
                        {required: true, message: "请输入姓名", trigger: "blur"},
                    ],
                    mobile: [
                        {required: true, message: "请输入联系方式", trigger: "blur"},
                    ],
                    idCard: [
                        {required: true, message: "请输入身份证号", trigger: "blur"},
                        {min: 18, max: 18, message: '请填写18位身份证号码', trigger: 'blur'},
                    ],
                    jobs: [
                        {required: true, message: "请输入职务/职称", trigger: "blur"}
                    ],
                    content: [
                        {required: true, message: "请输入简介", trigger: "blur"},
                    ],
                    imagePhotoarr: [
                        {required: true, message: "请上传形象照"}
                    ],
                    // wechatPhotoarr: [
                    //     {required: true, message: "请上传微信二维码"}
                    // ],
                },

            };
        },
        created() {
            this.getDetails();
        },
        methods: {
            getDetails() {
                this.id = this.$route.query.id;
                getByIdPerson(this.id)
                    .then(response => {
                        this.form = response.data;
                        this.specialityarr = this.form.speciality.split(",");
                        //字符串图片地址转换数组
                        this.convert(this.form.imagePhoto);
                        this.convert2(this.form.wechatPhoto);
                    })

            },
            convert(data){
                var tempImgArr = data.split(",");
                var imgArr = [];
                if (tempImgArr.length > 0) {
                    tempImgArr.forEach((item, i) => {
                        imgArr.push({ url: item });
                    });
                }
                this.form.imagePhotoarr = imgArr;
                this.hideUpload = true; // 隐藏上传按钮

            },
            convert2(data){
                var tempImgArr = data.split(",");
                var imgArr = [];
                if (tempImgArr.length > 0) {
                    tempImgArr.forEach((item, i) => {
                        imgArr.push({ url: item });
                    });
                }
                this.form.wechatPhotoarr = imgArr;
                this.hideUpload2 = true; // 隐藏上传按钮

            },
            handleEdit(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.form.speciality = this.specialityarr.join();
                        updatePerson(this.form).then(response => {
                            if (response.status == 1) {
                                this.$message.success("编辑成功");
                                this.back();
                            }
                        })
                    } else {
                        return false;
                    }
                });

            },
            back() {
                this.$router.push({path: "/hqgj/BasicData/service/Member", query: {id: this.form.orgId}});
            },
            handleClose(tag) {
                this.specialityarr.splice(this.specialityarr.indexOf(tag), 1);
                console.log(tag)
            },

            showInput() {
                this.inputVisible = true;
                this.$nextTick(_ => {
                    this.$refs.saveTagInput.$refs.input.focus();
                });
            },

            handleInputConfirm() {
                let inputValue = this.inputValue;
                if (inputValue) {
                    this.specialityarr.push(inputValue);
                }
                this.inputVisible = false;
                this.inputValue = '';
            },
            //形象照上传
            unitPictrueUploadSuccess(response, file, fileList) {
                if (response.status == 1) {
                    var imgArr = this.form.imagePhotoarr;
                    var tempArr = [];
                    imgArr.push({
                        url: response.data
                    }); // push response.data进去 里面含3个字段
                    if (imgArr.length > 0) {
                        imgArr.forEach(item => {
                            tempArr.push(item.url);
                        });
                    }
                    this.form.imagePhotoarr = imgArr; //更新data数据
                    this.form.imagePhoto = tempArr.join(",");
                    if (imgArr.length >= this.limitImgCount) {
                        this.hideUpload = true; //隐藏上传按钮
                    }
                    this.hideUpload = true; //隐藏上传按钮
                }
            },
            //编辑形象照上传服务器
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
                        // if (that.submitType == "edit") {
                        //     that.actionUrls = "";
                        //     // obj.projectId = that.byIdData.id;
                        //     obj.coverUrl = newUrl;
                        // } else {
                            that.actionUrls = "/hqgj-portal/www/uploadFileBase";
                            obj.file = newUrl;
                        // }
                        that.imageUpload(obj);
                    };
                };
            },
            //形象照上传
            imageUpload(obj) {
                fileUpImg(this.actionUrls, obj).then(response => {
                    if (response.status == 1) {
                        var imgArr = this.form.imagePhotoarr;
                        var tempArr = [];
                        imgArr.push({url: response.data}); // push response.data进去 里面含3个字段
                        if (imgArr.length > 0) {
                            imgArr.forEach(item => {
                                tempArr.push(item.url);
                            });
                        }
                        this.form.imagePhotoarr = imgArr; //更新data数据
                        this.form.imagePhoto = tempArr.join(",");
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
            //限制用户上传的形象照格式和大小
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
            // 移除形象照
            unitPictrueRemove(file, fileList) {
                console.log(file);
                console.log(fileList);
                var imgArrTemp = this.form.imagePhotoarr;
                if (file.status == "success") {
                    imgArrTemp.forEach((item, i) => {
                        var url = file.url; // 接口返回值没有逗号，加一个逗号
                        if (item.url == url) {
                            imgArrTemp.splice(i, 1);
                        }
                    });
                    this.form.imagePhotoarr = imgArrTemp; //更新data imgArr 数据
                    var tempArr = [];
                    if (imgArrTemp.length > 0) {
                        imgArrTemp.forEach((item, i) => {
                            tempArr.push(item.url);
                        });
                    }
                    this.form.imagePhoto = tempArr.join(","); //更新data pictureStr 数据
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
            //微信二维码上传
            unitPictrueUploadSuccess2(response, file, fileList) {
                if (response.status == 1) {
                    var imgArr = this.form.wechatPhotoarr;
                    var tempArr = [];
                    imgArr.push({
                        url: response.data
                    }); // push response.data进去 里面含3个字段
                    if (imgArr.length > 0) {
                        imgArr.forEach(item => {
                            tempArr.push(item.url);
                        });
                    }
                    this.form.wechatPhotoarr = imgArr; //更新data数据
                    this.form.wechatPhoto = tempArr.join(",");
                    if (imgArr.length >= this.limitImgCount) {
                        this.hideUpload2 = true; //隐藏上传按钮
                    }
                }
            },
            //编辑微信二维码上传服务器
            uploadServerEdit2(param) {
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
                        // if (that.submitType == "edit") {
                        //     that.actionUrls = "";
                        //     // obj.projectId = that.byIdData.id;
                        //     obj.coverUrl = newUrl;
                        // } else {
                        that.actionUrls = "/hqgj-portal/www/uploadFileBase";
                        obj.file = newUrl;
                        // }
                        that.imageUpload2(obj);
                    };
                };
            },
            //微信二维码上传
            imageUpload2(obj) {
                fileUpImg(this.actionUrls, obj).then(response => {
                    if (response.status == 1) {
                        var imgArr = this.form.wechatPhotoarr;
                        var tempArr = [];
                        imgArr.push({url: response.data}); // push response.data进去 里面含3个字段
                        if (imgArr.length > 0) {
                            imgArr.forEach(item => {
                                tempArr.push(item.url);
                            });
                        }
                        this.form.wechatPhotoarr = imgArr; //更新data数据
                        this.form.wechatPhoto = tempArr.join(",");
                        if (imgArr.length >= this.limitImgCount) {
                            this.hideUpload2 = true; //隐藏上传按钮
                        }
                    } else {
                        this.$message({
                            message: response.message,
                            type: "warning"
                        });
                    }
                });
            },
            //限制用户上传的微信二维码格式和大小
            beforeAvatarUpload2(file) {
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
            // 移除微信二维码
            unitPictrueRemove2(file, fileList) {
                console.log(file);
                console.log(fileList);
                var imgArrTemp = this.form.wechatPhotoarr;
                if (file.status == "success") {
                    imgArrTemp.forEach((item, i) => {
                        var url = file.url; // 接口返回值没有逗号，加一个逗号
                        if (item.url == url) {
                            imgArrTemp.splice(i, 1);
                        }
                    });
                    this.form.wechatPhotoarr = imgArrTemp; //更新data imagePhotoarr 数据
                    var tempArr = [];
                    if (imgArrTemp.length > 0) {
                        imgArrTemp.forEach((item, i) => {
                            tempArr.push(item.url);
                        });
                    }
                    this.form.wechatPhoto = tempArr.join(","); //更新data pictureStr 数据
                    if (imgArrTemp.length < this.limitImgCount) {
                        this.hideUpload2 = false; //显示上传按钮
                    }
                }
            },
            // 微信二维码预览
            unitPictureCardPreview2(file) {
                this.dialogImageUrl2 = file.url;
                this.dialogVisible2 = true;
            },


        }
    }
</script>

<style scoped>
    .container {
        background-color: #fff;
    }

    .button-new-tag {
        color: #0067D9;
        margin-left: 10px;
        height: 32px;
        line-height: 30px;
        padding-top: 0;
        padding-bottom: 0;
    }

    .hide /deep/ .el-upload--picture-card {
        display: none;
    }

    .hide2 /deep/ .el-upload--picture-card {
        display: none;
    }

    .title {
        font-size: 16px;
        font-weight: bold;
        padding: 20px 30px;
        border-bottom: 1px solid #eee;
    }

    .handle-box {
        padding-top: 40px;
        padding-bottom: 40px;
    }
</style>
