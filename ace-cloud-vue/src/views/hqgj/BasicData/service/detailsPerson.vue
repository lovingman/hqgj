<template>
    <div class="container">
        <div class="title">
            <span>机构成员详情</span>
            <el-button type="primary" style="float: right; border-radius:5px;" @click="back">返回</el-button>
        </div>
        <div class="handle-box">
            <el-form :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="姓名:" prop="name">
                            <span>{{form.name}}</span>
                        </el-form-item>
                        <el-form-item label="身份证号:" prop="name">
                            <span>{{form.idCard}}</span>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="name">
                            <span>{{form.mobile}}</span>
                        </el-form-item>
                        <el-form-item label="职务/职称:" prop="name">
                            <span>{{form.jobs}}</span>
                        </el-form-item>
                        <el-form-item label="擅长领域:" prop="name">
                            <span>{{form.speciality}}</span>
                        </el-form-item>
                        <el-form-item label="简介:" prop="name">
                            <span style="word-wrap: break-word;word-break: break-all;">{{form.content}}</span>
                        </el-form-item>
                        <el-form-item label="创建人:" prop="createUserName" style="width: 70%">
                            <span style="word-wrap: break-word;word-break: break-all;">{{form.createUserName}}</span>
                        </el-form-item>
                        <el-form-item label="创建时间:" prop="createDate" style="width: 70%">
                            <span style="word-wrap: break-word;word-break: break-all;">{{form.createDate}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="形象照:" prop="name">
                            <i class="image" @click="Preview(form.imagePhoto)"><img :src="form.imagePhoto" alt="" class="img"></i>
                            <el-dialog :visible.sync="dialogVisible" append-to-body>
                                <img :src="dialogImageUrl" alt="" width="100%">
                            </el-dialog>
                        </el-form-item>
                        <!--<el-form-item label="微信二维码:" prop="name">-->
                            <!--<i @click="Preview(form.wechatPhoto)"><img :src="form.wechatPhoto" alt="" class="img"></i>-->
                        <!--</el-form-item>-->
                    </el-col>
                </el-row>
                <!--<el-form-item  style="bottom: 10px;margin-left: 300px">-->
                    <!--<el-button type="primary" @click="back">返回</el-button>-->
                <!--</el-form-item>-->
            </el-form>
        </div>
    </div>
</template>

<script>

    import {getByIdPerson} from "@/api/hqgj/service";
    export default {
        name: "createPerson",
        data() {
            return {
                dialogVisible:false,
                dialogImageUrl:"",
                form:{},
            };
        },
        created(){
            this.getDetails();
        },
        methods:{
            getDetails() {
                this.id = this.$route.query.id;
                getByIdPerson(this.id)
                    .then(response => {
                        this.form = response.data;
                    })

            },
            // 图片预览
            Preview(data) {
                this.dialogImageUrl = data;
                this.dialogVisible = true;
            },
            back(){
                this.$router.push({ path: "/hqgj/BasicData/service/Member",query: { id: this.form.orgId } });
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
        line-height: 30px;
        font-size: 16px;
        font-weight: bold;
        padding: 20px 30px;
        border-bottom: 1px solid #eee;
    }
    .handle-box{
        padding-top: 40px;
        padding-bottom: 40px;
    }
    .image {
        overflow: hidden;
        background-color: #fff;
        border: 1px solid #c0ccda;
        border-radius: 6px;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        width: 148px;
        height: 148px;
        margin: 0 8px 8px 0;
        display: inline-block;
    }
    .img{
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
</style>
