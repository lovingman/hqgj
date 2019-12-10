<template>
    <div class="container">
        <div class="title" style="margin-bottom: 30px">添加机构成员</div>
        <div class="handle-box">
            <el-form :model="addform" :rules="addrules" class="demo-ruleForm" label-width="300px" ref="ruleForm">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="姓名:" prop="name">
                            <el-input placeholder="请输入姓名" v-model="addform.name"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="mobile">
                            <el-input placeholder="请输入联系方式" v-model="addform.mobile"></el-input>
                        </el-form-item>
                        <el-form-item label="职务/职称:" prop="jobs">
                            <el-input placeholder="请输入职务/职称" v-model="addform.jobs"></el-input>
                        </el-form-item>
                        <el-form-item label="擅长领域:" prop="speciality">
                            <el-tag
                                    :disable-transitions="false"
                                    :key="tag"
                                    @close="handleClose(tag)"
                                    closable
                                    v-for="tag in addform.speciality">
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
                                    v-model="addform.content"
                            >
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="形象照:" prop="imagePhoto">
                            <el-upload
                                    :class="{hide:hideUpload}"
                                    :on-preview="handlePictureCardPreview"
                                    :on-progress="uploading"
                                    :on-remove="handleRemove"
                                    action="http://39.97.185.200:9011/zcpa-portal/www/uploadFile"
                                    list-type="picture-card">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            <div class="tips" style="font-size:12px;">支持jpg.png，大小不超过10M</div>
                            <el-dialog :visible.sync="dialogVisible" append-to-body>
                                <img :src="dialogImageUrl" alt="" width="100%">
                            </el-dialog>
                        </el-form-item>
                        <el-form-item label="微信二维码:" prop="wechatPhoto">
                            <el-upload
                                    :class="{hide2:hideUpload2}"
                                    :on-preview="handlePictureCardPreview2"
                                    :on-progress="uploading2"
                                    :on-remove="handleRemove2"
                                    action="https://jsonplaceholder.typicode.com/posts/"
                                    list-type="picture-card">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            <div class="tips" style="font-size:12px;">支持jpg.png，大小不超过10M</div>
                            <el-dialog :visible.sync="dialogVisible2" append-to-body>
                                <img :src="dialogImageUrl2" alt="" width="100%">
                            </el-dialog>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item style="bottom: 10px;margin-left: 300px">
                    <el-button @click="back">取消</el-button>
                    <el-button @click="handleAdd('ruleForm')" type="primary">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {createPerson} from "@/api/hqgj/service";
    export default {
        name: "createPerson",
        data() {
            return {
                inputVisible: false,
                inputValue: '',
                dialogImageUrl: '',
                dialogVisible: false,
                hideUpload: false,
                dialogImageUrl2: '',
                dialogVisible2: false,
                hideUpload2: false,
                addform: {
                    orgId:"",
                    name: "",
                    mobile: "",
                    jobs: "",
                    speciality: [],
                    content: "",
                    imagePhoto: "",
                    wechatPhoto: "",
                },
                addrules: {
                    name: [
                        {required: true, message: "请输入姓名", trigger: "blur"},
                    ],
                    mobile: [
                        {required: true, message: "请输入联系方式", trigger: "blur"},
                    ],
                    jobs: [
                        {required: true, message: "请输入职务/职称", trigger: "blur"}
                    ],
                    content: [
                        {required: true, message: "请输入简介", trigger: "blur"},
                    ],
                    // imagePhoto: [
                    //     {required: true, message: "请输入机构简介", trigger: "blur"},
                    // ],
                    // wechatPhoto: [
                    //     {required: true, message: "请选择机构类型", trigger: "change"}
                    // ],
                },

            };
        },
        created() {

        },
        methods: {
            handleAdd(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        console.log(this.$route.query.id)
                        this.addform.orgId = this.$route.query.id;
                        createPerson(this.addform).then(response => {
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
                this.$router.push({path: "/hqgj/BasicData/service/Member"});
            },
            handleClose(tag) {
                this.addform.speciality.splice(this.addform.speciality.indexOf(tag), 1);
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
                    this.addform.speciality.push(inputValue);
                }
                this.inputVisible = false;
                this.inputValue = '';
            },
            handleRemove(file, fileList) {
                this.hideUpload = false;
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            uploading(event, file, fileList) {
                this.hideUpload = true;

            },
            handleRemove2(file, fileList) {
                this.hideUpload2 = false;
            },
            handlePictureCardPreview2(file) {
                this.dialogImageUrl2 = file.url;
                this.dialogVisible2 = true;
            },
            uploading2(event, file, fileList) {
                this.hideUpload2 = true;

            }


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
