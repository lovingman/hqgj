<template>
    <div class="container">
        <div class="title">
            <span>机构详情</span>
            <el-button @click="back" style="float: right; border-radius:5px;" type="primary">返回</el-button>
        </div>
        <div class="handle-box">
            <el-form :model="form" :rules="rules" class="demo-ruleForm" label-width="100px" ref="ruleForm">
                <el-form-item label="类型:" prop="name">
                    <span v-if="form.type==1">律师事务所</span>
                    <span v-if="form.type==2">会计事务所</span>
                    <span v-if="form.type==3">培训机构</span>
                </el-form-item>
                <el-form-item label="机构名称:" prop="name">
                    <span>{{form.orgName}}</span>
                </el-form-item>
                <el-form-item label="logo:" prop="fmUrlarr">
                    <i class="image" @click="preview(form.fmUrl)"><img :src="form.fmUrl" alt=""class="img"></i>
                    <el-dialog :visible.sync="dialogVisible" append-to-body>
                        <img :src="dialogImageUrl" alt="" width="100%">
                    </el-dialog>
                </el-form-item>
                <el-form-item label="联系方式:" prop="name">
                    <span>{{form.contactPersonName}}</span>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <span>{{form.contactPersonTel}}</span>
                </el-form-item>
                <el-form-item label="地址:" prop="region">
                    <span>{{form.completeAddress}}</span>
                </el-form-item>
                <el-form-item label="简介:" prop="name" style="width: 95%">
                    <span style="word-wrap: break-word;word-break: break-all;">{{form.content}}</span>
                </el-form-item>
                <el-form-item label="创建人:" prop="name" style="width: 70%">
                    <span style="word-wrap: break-word;word-break: break-all;">{{form.createUserName}}</span>
                </el-form-item>
                <el-form-item label="创建时间:" prop="name" style="width: 70%">
                    <span style="word-wrap: break-word;word-break: break-all;">{{form.createDate}}</span>
                </el-form-item>
                <!--<el-form-item>-->
                    <!--<el-button @click="back" type="primary">返回</el-button>-->
                <!--</el-form-item>-->
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getById} from "@/api/hqgj/service"

    export default {
        name: "edit",
        data() {
            return {
                dialogVisible:false,
                dialogImageUrl:"",
                form:{},
            };
        },
        created() {
            this.getDetails();
        },
        methods: {
            getDetails() {
                this.id = this.$route.query.id;
                getById(this.id)
                    .then(response => {
                        // this.loading = false;
                        this.form = response.data;
                    })

            },
            // 图片预览
            preview(data) {
                this.dialogImageUrl = data;
                this.dialogVisible = true;
            },
            back() {
                this.$router.push({path: "/hqgj/BasicData/service"});
            }
        }
    }
</script>

<style scoped>
    .container {
        background-color: #fff;
    }

    .title {
        line-height: 30px;
        font-size: 16px;
        font-weight: bold;
        padding: 20px 30px;
        border-bottom: 1px solid #eee;
    }

    .handle-box {
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
