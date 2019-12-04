<template>
    <div class="container">
        <div class="title">企业创建</div>
        <div class="handle-box">
            <el-form :rules="rules" ref="ruleForm" label-width="600px" class="demo-ruleForm">
                <el-form-item label="企业名称:" prop="name">
                    <el-input style="width: 50%" placeholder="请输入企业名称"></el-input>
                </el-form-item>
                <el-form-item label="统一社会信用代码:" prop="name">
                    <el-input style="width: 50%" placeholder="请输入18位统一社会信用代码"></el-input>
                </el-form-item>
                <el-form-item label="法定代表人:" prop="name">
                    <el-input style="width: 50%" placeholder="请输入法定代表人姓名"></el-input>
                </el-form-item>
                <el-form-item label="联系方式:" prop="name">
                    <el-input style="width: 21%" placeholder="联系人姓名"></el-input>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <el-input style="width: 26%" placeholder="联系电话"></el-input>
                </el-form-item>
                <el-form-item label="地址:" prop="region">
                    <el-select  placeholder="请选择省份" style="width: 12%;margin-right: 5px">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                    <el-select  placeholder="请选择市" style="width: 10%;margin-right: 5px">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                    <el-select  placeholder="请选择区县" style="width: 12%;margin-right: 5px">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                    <el-select  placeholder="请选择乡镇街道" style="width: 15%;margin-right: 5px">
                        <el-option label="区域一" value="shanghai"></el-option>
                        <el-option label="区域二" value="beijing"></el-option>
                    </el-select>
                    <el-input
                            type="textarea"
                            placeholder="请输入企业详细地址，xx社区xx路xx号(xx小区xx栋xx楼xx号）"
                            style="width: 50%"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="back">取消</el-button>
                    <el-button type="primary" @click="">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
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
                this.$router.push({ path: "/hqgj/BasicData/enterprise" });
            },

        }
    }
</script>

<style lang="less" scoped>
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
