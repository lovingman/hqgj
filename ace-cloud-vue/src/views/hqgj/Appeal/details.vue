<template>
    <div class="container">
        <div class="title">
            <span>企业诉求详情</span>
            <el-button @click="back" style="float: right;border-radius:5px;" type="primary">返回</el-button>
        </div>
        <div class="handle-box">
            <el-form :rules="rules" class="demo-ruleForm" label-width="120px" ref="ruleForm">
                <el-form-item label="类型:" prop="typeName">
                    <span>{{form.typeName}}</span>
                </el-form-item>
                <el-form-item label="企业名称:" prop="companyName">
                    <span>{{form.companyName}}</span>
                </el-form-item>
                <el-form-item label="需求额度:" v-if="form.type=='3'" prop="money">
                    <span>{{form.money}}</span>
                </el-form-item>
                <el-form-item label="期望银行:" v-if="form.type=='3'" prop="bank">
                    <span>{{form.bank}}</span>
                </el-form-item>
                <el-form-item label="企业人数:" v-if="form.type=='1'" prop="companyNumber">
                    <span>{{form.companyNumber}}</span>
                </el-form-item>
                <el-form-item label="物资名称及数量:" v-if="form.type=='1'" prop="companyMaterial">
                    <div v-for="item in form.companyMaterial">
                        <span>{{item.categoryName}}&nbsp;{{item.number}}</span>
                    </div>
                </el-form-item>
                <el-form-item label="诉求标题:" v-if="form.type=='2' || form.type=='4'" prop="title">
                    <span>{{form.title}}</span>
                </el-form-item>
                <el-form-item label="诉求描述:" prop="text">
                    <span>{{form.text}}</span>
                </el-form-item>
                <el-form-item label="联系人:" prop="personName">
                    <span>{{form.personName}}</span>
                </el-form-item>
                <el-form-item label="联系方式:" prop="personTel">
                    <span>{{form.personTel}}</span>
                </el-form-item>
                <el-form-item label="上报时间:" prop="region">
                    <span>{{form.createDate}}</span>
                </el-form-item>
                <el-form-item label="状态:" prop="status">
                    <span v-if="form.status=='1'">待受理</span>
                    <span v-if="form.status=='2'">已受理</span>
                    <span v-if="form.status=='3'">已解决</span>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getById} from "@/api/hqgj/Appeal";

    export default {
        name: "edit",
        data() {
            return {
                form: {},
                type:''
            };
        },
        created() {
            this.getDetails();
        },
        methods: {
            getDetails() {
                this.id = this.$route.query.id;
                this.type = this.$route.query.type
                getById(this.id).then(response => {
                    this.loading = false;
                    this.form = response.data;
                });
            },
            back() {
                this.$router.push({path: "/hqgj/Appeal"});
            },
        }
    };
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
</style>
