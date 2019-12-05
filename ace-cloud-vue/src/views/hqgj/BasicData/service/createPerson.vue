<template>
    <div class="container">
        <div class="title" style="margin-bottom: 30px">添加机构成员</div>
        <div class="handle-box">
            <el-form :rules="rules" ref="ruleForm" label-width="300px" class="demo-ruleForm">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="姓名:" prop="name">
                            <el-input  placeholder="请输入姓名"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="name">
                            <el-input  placeholder="请输入联系方式"></el-input>
                        </el-form-item>
                        <el-form-item label="职务/职称:" prop="name">
                            <el-input  placeholder="请输入职务/职称"></el-input>
                        </el-form-item>
                        <el-form-item label="擅长领域:" prop="name">
                            <el-tag
                                    :key="tag"
                                    v-for="tag in dynamicTags"
                                    closable
                                    :disable-transitions="false"
                                    @close="handleClose(tag)">
                                {{tag}}
                            </el-tag>
                            <el-input
                                    class="input-new-tag"
                                    v-if="inputVisible"
                                    v-model="inputValue"
                                    ref="saveTagInput"
                                    size="small"
                                    @keyup.enter.native="handleInputConfirm"
                                    @blur="handleInputConfirm"
                            >
                            </el-input>
                            <el-button v-else class="button-new-tag" icon="el-icon-plus" size="small" @click="showInput">添加</el-button>
                            <div class="tips" style="font-size:12px;margin-left: 10px">不超过5个</div>
                        </el-form-item>
                        <el-form-item label="简介:" prop="name">
                            <el-input
                                    type="textarea"
                                    placeholder="请输入简介"
                                    v-model="textarea"
                                    maxlength="500"
                                    rows="6"
                                    maxRows="6"
                                    show-word-limit
                            >
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="形象照:" prop="name">
                            <el-upload
                                    :class="{hide:hideUpload}"
                                    action="https://jsonplaceholder.typicode.com/posts/"
                                    list-type="picture-card"
                                    :on-preview="handlePictureCardPreview"
                                    :on-remove="handleRemove"
                                    :on-progress="uploading">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            <div class="tips" style="font-size:12px;">支持jpg.png，大小不超过10M</div>
                            <el-dialog :visible.sync="dialogVisible" append-to-body>
                                <img width="100%" :src="dialogImageUrl" alt="">
                            </el-dialog>
                        </el-form-item>
                        <el-form-item label="微信二维码:" prop="name">
                            <el-upload
                                    :class="{hide2:hideUpload2}"
                                    action="https://jsonplaceholder.typicode.com/posts/"
                                    list-type="picture-card"
                                    :on-preview="handlePictureCardPreview2"
                                    :on-remove="handleRemove2"
                                    :on-progress="uploading2">
                                <i class="el-icon-plus"></i>
                            </el-upload>
                            <div class="tips" style="font-size:12px;">支持jpg.png，大小不超过10M</div>
                            <el-dialog :visible.sync="dialogVisible2" append-to-body>
                                <img width="100%" :src="dialogImageUrl2" alt="">
                            </el-dialog>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item  style="bottom: 10px;margin-left: 300px">
                    <el-button @click="back">取消</el-button>
                    <el-button type="primary" @click="">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "createPerson",
        data() {
            return {
                dynamicTags: [],
                inputVisible: false,
                inputValue: '',
                dialogImageUrl: '',
                dialogVisible: false,
                hideUpload:false,
                dialogImageUrl2: '',
                dialogVisible2: false,
                hideUpload2:false

            };
        },
        created(){

        },
        methods:{
            back(){
                this.$router.push({ path: "/hqgj/BasicData/service/Member" });
            },
            handleClose(tag) {
                this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
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
                    this.dynamicTags.push(inputValue);
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
            uploading(event, file, fileList){
                this.hideUpload = true;

            },
            handleRemove2(file, fileList) {
                this.hideUpload2 = false;
            },
            handlePictureCardPreview2(file) {
                this.dialogImageUrl2 = file.url;
                this.dialogVisible2 = true;
            },
            uploading2(event, file, fileList){
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
    .handle-box{
        padding-top: 40px;
        padding-bottom: 40px;
    }
</style>
