<template>
    <div class="container">
        <div class="title">创建</div>
        <div class="handle-box">
            <el-form :model="addform" :rules="addrules" class="demo-ruleForm" label-width="600px" ref="ruleForm">
                <el-form-item label="企业名称:" prop="companyName">
                    <el-select clearable placeholder="请选择企业" v-model="addform.companyNameObj" style="width: 50%">
                        <el-option
                                :key="item"
                                :label="item.companyName"
                                :value="item"
                                v-for="item in dict"
                        ></el-option>
                    </el-select>
                    <!--<el-input placeholder="请输入企业名称" style="width: 50%" v-model="addform.companyName"></el-input>-->
                </el-form-item>
                <el-form-item label="获得积分:" prop="getIntegral">
                    <el-input placeholder="请输入获得积分" style="width: 50%" v-model="addform.getIntegral"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="back">取消</el-button>
                    <el-button @click="handleadd('ruleForm')" type="primary">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {createIntegral} from "@/api/hqgj/BWSService";
    import {getList} from "@/api/hqgj/enterprise";

    export default {
        name: "create",
        data() {
            return {
                addform: {
                    companyId:"",
                    companyNameObj:[],
                    companyName: "",
                    getIntegral: "500",
                    register: "2"
                },
                dict: [],
                addrules: {
                    companyNameObj: [
                        {required: true, message: "请选择企业", trigger: "change"},
                    ],
                    getIntegral: [
                        {required: true, message: "请输入获得积分", trigger: "blur"},
                    ],
                },
            };
        },
        created() {
            this.getlist();
        },
        methods: {
            //获取企业数据
            getlist() {
                getList().then(response => {
                    this.dict = response.rows;
                    console.log(this.addform.companyNameObj)
                })
            },
            handleadd(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.addform.companyId = this.addform.companyNameObj.id;
                        this.addform.companyName = this.addform.companyNameObj.companyName;
                        createIntegral(this.addform).then(response => {
                            if (response.status == 1) {
                                this.$message.success("创建成功");
                                this.addform.companyNameObj=[];
                                this.back();
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            back() {
                this.$router.push({path: "/hqgj/ServicePack/BWSService/Integral"});
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

    .handle-box {
        padding-top: 40px;
        padding-bottom: 40px;
    }
</style>
