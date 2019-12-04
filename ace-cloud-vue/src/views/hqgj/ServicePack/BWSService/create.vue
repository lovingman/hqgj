<template>
    <div class="container">
        <div class="title">创建</div>
        <el-form :rules="rules" ref="ruleForm" label-width="600px" class="demo-ruleForm">
            <el-form-item label="企业名称:" prop="name">
                <el-input style="width: 50%" placeholder="请输入企业名称"></el-input>
            </el-form-item>
            <el-form-item label="获得积分:" prop="name">
                <el-input style="width: 50%" placeholder="请输入获得积分"></el-input>
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
        name: "create",
        data() {
            return {

            };
        },
        created(){

        },
        methods:{
            back(){
                this.$router.push({ path: "/hqgj/ServicePack/BWSService/index" });
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
