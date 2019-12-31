<template>
    <div class="container">
        <div class="title">企业详情</div>
        <div class="handle-box">
            <el-form :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="来源:" prop="source">
                    <span>{{form.source}}</span>
                </el-form-item>
                <el-form-item label="企业名称:" prop="name">
                    <span>{{form.companyName}}</span>
                </el-form-item>
                <!--<el-form-item label="统一社会信用代码:" prop="name">-->
                    <!--<span>{{form.creditCode}}</span>-->
                <!--</el-form-item>-->
                <el-form-item label="法人代表:" prop="name">
                    <span>{{form.legalPerson}}</span>
                </el-form-item>
                <el-form-item label="联系方式:" prop="name">
                    <span>{{form.contactPersonName}}</span>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <span>{{form.contactPersonTel}}</span>
                </el-form-item>
                <el-form-item label="地址:" prop="region">
                    <span>湖南省常德市{{form.completeAddress}}</span>
                </el-form-item>
                <el-form-item label="创建人:" prop="region">
                    <span>{{form.createUserName}}</span>
                </el-form-item>
                <el-form-item label="创建时间:" prop="region">
                    <span>{{form.createDate}}</span>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="back">返回</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getById} from "@/api/hqgj/enterprise";
    export default {
        name: "edit",
        data() {
            return {
                form: {}
            };
        },
        created(){
            this.getDetails();
        },
        methods:{
            getDetails(){
                this.id = this.$route.query.id;
                getById(this.id)
                    .then(response => {
                        this.loading = false;
                        this.form=response.data;
                    })
            },
            back(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise" });
            },
            clickitem(e) {
                e === this.add.type ? this.add.type = '' : this.add.type = e
            },

        }
    }
</script>

<style scoped>
    .container {
        background-color: #fff;
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
