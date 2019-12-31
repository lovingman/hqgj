<template>
    <div class="container">
        <div class="title">创建</div>
        <div class="handle-box">
            <el-form :model="addform" :rules="addrules" class="demo-ruleForm" label-width="100px" ref="ruleForm">
                <el-form-item label="企业名称:" prop="companyId">
                    <el-select placeholder="请选择企业" @change="changeLocationValue" filterable clearable v-model="addform.companyId" style="width: 30%">
                        <el-option
                                :key="item.id"
                                :label="item.companyName"
                                :value="item.id"
                                v-for="item in dict"
                        ></el-option>
                    </el-select>
                    <!--<el-input placeholder="请输入企业名称" style="width: 50%" v-model="addform.companyName"></el-input>-->
                </el-form-item>
                <el-form-item label="获得积分:" prop="getIntegral">
                    <el-input :disabled="true" placeholder="请输入获得积分" style="width: 30%" v-model="addform.getIntegral"></el-input>
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
                    // companyNameObj:'',
                    companyName: "",
                    getIntegral: "500",
                    surplusIntegral:"500",
                    register: "2"
                },
                dict: [],
                addrules: {
                    companyId: [
                        {required: true, message: "请选择企业", trigger: "chenge"},
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
                    console.log(this.dict)
                })
            },
            changeLocationValue(val){
                //locations是v-for里面的也是datas里面的值
                let obj = {};
                obj = this.dict.find((item)=>{
                    return item.id === val;
                });
                console.log(obj);
                this.addform.companyName = obj.companyName;
            },
            handleadd(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        // this.addform.companyId = this.addform.companyNameObj.id;
                        // this.addform.companyName = this.addform.companyNameObj.companyName;
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
