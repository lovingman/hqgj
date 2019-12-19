<template>
    <div class="main-box">
        <div class="title">编辑</div>
        <div class="conetnet-box">
            <el-form
                    :model="serviceForm"
                    :inline="true"
                    :rules="serviceRules"
                    ref="serviceForm"
                    label-width="120px"
                    class="formBox"
            >
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="标题" prop="title">
                            <el-input
                                    maxlength="50"
                                    v-model="serviceForm.title"
                                    clearable
                                    show-word-limit
                                    placeholder="请输入标题"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="来源">
                            <el-input
                                    maxlength="50"
                                    v-model="serviceForm.source"
                                    clearable
                                    show-word-limit
                                    placeholder="请输入来源"
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        <el-form-item label="内容:" prop="content">
                            <el-input
                                    type="textarea"
                                    placeholder="请输入内容"
                                    v-model="serviceForm.content"
                                    rows="20"
                                    show-word-limit
                            ></el-input>
                            <!--<editor-bar v-model="serviceForm.content" :isClear="isClear" @change="change"></editor-bar>-->
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <!-- 底部按钮 -->
        <div class="footer">
            <div class="footer-flex">
                <el-button @click="back">取消</el-button>
                <el-button @click="handleEdit" type="primary">确定</el-button>
            </div>
        </div>
    </div>
</template>

<script>

    import {getPolicyById,updatePolicy} from "@/api/hqgj/Policies";
    export default {
        name: "edit",
        data() {
            return {
                //数据
                serviceForm: {
                    title: "",
                    source: "",
                    content: ""
                },
                //验证
                serviceRules: {
                    title: [
                        {
                            required: true,
                            message: "请输入标题,字数在50字以内",
                            trigger: "blur"
                        }
                    ],
                    content: [
                        {
                            required: true,
                            message: "请输入详细内容",
                            trigger: "blur"
                        }
                    ]
                }
            };
        },
        created(){
            this.getDetails();
        },
        methods: {
            getDetails() {
                this.id = this.$route.query.id;
                getPolicyById(this.id)
                    .then(response => {
                        this.serviceForm = response.data;
                    })

            },
            handleEdit(){
                updatePolicy(this.serviceForm).then(response=>{
                    if (response.status == 1) {
                        this.$message.success("编辑成功");
                        this.back();
                    }
                })
            },
            back() {
                this.$router.push({
                    path: "/hqgj/Policies/OtrlPolicy"
                });
            },
            // change(val) {
            //   console.log(val);
            // }
        }
    }
</script>

<style lang="less" scoped>
    .main-box {
        background: #fff;
        .formBox {
            padding-right: 50px;
        }
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
