<template>
    <div class="main-box">
        <div class="title">创建</div>
        <div class="conetnet-box">
            <el-form
                    :inline="true"
                    :model="serviceForm"
                    :rules="serviceRules"
                    class="formBox"
                    label-width="120px"
                    ref="serviceForm"
            >
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="名称" prop="title">
                            <el-input
                                    clearable
                                    maxlength="50"
                                    placeholder="请输入模板名称"
                                    show-word-limit
                                    v-model="serviceForm.title"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="文件" prop="basicAnnexes">
                            <el-upload
                                    :before-upload="beforeAvatarUpload"
                                    :file-list="fileList"
                                    :http-request="uploadServer"
                                    :on-error="uploadError"
                                    :on-success="uploadSuccess"
                                    action="none"
                                    class="upload-demo"
                                    drag
                                    multiple
                            >
                                <i class="el-icon-upload"></i>
                                <div class="el-upload__text">
                                    将文件拖到此处，或
                                    <em>点击上传</em>
                                </div>
                                <div class="el-upload__tip" slot="tip">支持扩展名：.rar .zip .doc .docx .pdf .jpg....</div>
                            </el-upload>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <!-- 底部按钮 -->
        <div class="footer">
            <div class="footer-flex">
                <el-button @click="back">取消</el-button>
                <el-button @click="handleAdd('serviceForm')" type="primary">确定</el-button>
            </div>
        </div>
    </div>
</template>

<script>
    import {fileUpload} from "@/api/sys";
    import {createServe} from "@/api/hqgj/Policies";
    export default {
        name: "add",
        data() {
            return {
                fileList: [],
                //数据
                serviceForm: {
                    title: "",
                    basicAnnexes: []
                },
                //验证
                serviceRules: {
                    title: [{required: true, message: "请输入模板名称,字数在50字以内", trigger: "blur"}],
                    basicAnnexes: [{required: true, message: "请上传文件", trigger: "blur"}]
                }
            };
        },
        methods: {
            handleAdd(formName){
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        createServe(this.serviceForm).then(response=>{
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
            FileUpload(obj) {
                console.log(obj);
                this.actionUrls = "/hqgj-portal/www/uploadFile";
                fileUpload(obj, this.actionUrls).then(response => {
                    if (response.status == 1) {
                        this.serviceForm.basicAnnexes.push({fileName:obj.file.name.substring(0,obj.file.name.indexOf(".")),fileURL:response.data})
                        this.fileList.push({name: obj.file.name, url: response.data})
                        this.uploadSuccess(response, obj.file, this.fileList);
                    } else {
                        this.$message({
                            message: response.message,
                            type: "warning"
                        });
                        this.uploadError(response, obj.file, this.fileList);
                    }
                    return response.status
                });

            },
            //自定义上传
            uploadServer(param) {
                let obj = {};
                obj.file = param.file;
                this.FileUpload(obj);
                return true
            },
            uploadSuccess(response, file, fileList) {
                this.fileList = fileList;
            },
            uploadError(response, file, fileList) {
                this.$message.error('上传失败')
            },
            // 上传前对文件的大小的判断
            beforeAvatarUpload(file) {
                const extension = file.name.split('.')[1] === 'xls';
                const extension2 = file.name.split('.')[1] === 'xlsx';
                const extension3 = file.name.split('.')[1] === 'doc';
                const extension4 = file.name.split('.')[1] === 'docx';
                const isLt2M = file.size / 1024 / 1024 < 10;
                if (!extension && !extension2 && !extension3 && !extension4) {
                    this.$message.warning('上传文件只能是 xls、xlsx、doc、docx 、pdf、jpg、zip、rar格式!');
                }
                if (!isLt2M) {
                    this.$message.warning('上传模板大小不能超过 10MB!')
                }
                console.log(extension, extension2, extension3, extension4, isLt2M)
                return (extension || extension2 || extension3 || extension4) && isLt2M
            },
            back() {
                this.$router.push({
                    path: "/hqgj/Policies/legalService"
                });
            }
        }
    }
    ;
</script>

<style lang="less" scoped>
    .main-box {
        background: #fff;

        .title {
            font-size: 16px;
            font-weight: bold;
            padding: 20px 30px;
            border-bottom: 1px solid #eee;
        }

        .conetnet-box {
            padding: 30px 0 0 0;

            /deep/ .el-form-item {
                width: 100%;
            }

            /deep/ .el-form-item__content {
                width: calc(~"100% - 120px");
            }

            /deep/ .el-input--medium .el-input__inner {
                padding-right: 80px;
            }

            /deep/ .el-upload {
                width: 100%;

                /deep/ .el-upload-dragger {
                    width: 100%;
                }
            }
        }

        .footer {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            border-top: 1px solid #eee;
            height: 60px;
            background-color: #fff;

            .footer-flex {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100%;

                /deep/ .el-button--medium {
                    border-radius: 4px;
                    margin-left: 20px;
                }
            }
        }
    }
</style>
