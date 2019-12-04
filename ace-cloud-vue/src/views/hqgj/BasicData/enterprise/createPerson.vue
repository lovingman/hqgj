<template>
    <div class="container">
        <div class="title" style="margin-bottom: 30px">添加企业成员</div>
        <el-form :rules="rules" ref="ruleForm" label-width="600px" class="demo-ruleForm">
            <el-form-item label="姓名:" prop="name">
                <el-input style="width: 50%" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="身份证号码:" prop="name">
                <el-input style="width: 50%" placeholder="请输入身份证号"></el-input>
            </el-form-item>
            <el-form-item label="手机号码:" prop="name">
                <el-input style="width: 50%" placeholder="请输入联系方式"></el-input>
            </el-form-item>
            <el-form-item label="职位:" prop="name">
                <el-input style="width: 26%" placeholder="请输入职位"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="back">取消</el-button>
                <el-button type="primary" @click="">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "createPerson",
        data() {
            return {

            };
        },
        created(){

        },
        methods:{
            back(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise/Member" });
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
</style>
