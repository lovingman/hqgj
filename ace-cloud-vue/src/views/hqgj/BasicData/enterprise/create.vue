<template>
    <div class="container">
        <div class="title">企业创建</div>
        <div class="handle-box">
            <el-form :model="addform" :rules="addrules" class="demo-ruleForm" label-width="100px" ref="ruleForm">
                <el-form-item label="企业名称:" prop="companyName">
                    <el-input placeholder="请输入企业名称" maxlength="50" show-word-limit style="width: 30%" v-model.trim="addform.companyName"></el-input>
                </el-form-item>
                <!--<el-form-item label="统一社会信用代码:" prop="creditCode">-->
                <!--<el-input placeholder="请输入18位统一社会信用代码"  style="width: 50%" v-model="addform.creditCode"></el-input>-->
                <!--</el-form-item>-->
                <el-form-item label="法人代表:" prop="legalPerson">
                    <el-input placeholder="请输入法人代表姓名" style="width: 30%" v-model.trim="addform.legalPerson"></el-input>
                </el-form-item>
                <el-form-item label="联系方式:" prop="contactPersonTel">
                    <el-input placeholder="联系人姓名" style="width: 12%" v-model.trim="addform.contactPersonName"></el-input>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <el-input placeholder="联系电话" style="width: 16%" v-model.trim="addform.contactPersonTel"></el-input>
                </el-form-item>
                <el-form-item label="地址:" prop="areaCodes">
                    <el-cascader
                            :options="areaCodeOptions"
                            :props="areaCodeProps"
                            change-on-select
                            clearable
                            placeholder="请选择行政区划"
                            ref="myCascader"
                            style="width: 30%"
                            v-model="addform.areaCodes"/>
                </el-form-item>
                <el-form-item prop="companyAddress">
                    <el-input
                            placeholder="请输入企业详细地址，xx社区xx路xx号(xx小区xx栋xx楼xx号）"
                            style="width: 30%"
                            type="textarea"
                            v-model.trim="addform.companyAddress"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="back">取消</el-button>
                    <el-button @click="addhandle('ruleForm')" type="primary">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {create} from "@/api/hqgj/enterprise";
    import {getAreaTree, getDict} from "@/api/sys";

    export default {
        name: "create",
        data() {
            return {
                allAddress: '',//详细完整地址
                areaCodeOptions: [], //行政区划
                areaCodeProps: {
                    value: "id",
                    label: "text"
                },
                //新增
                addform: {
                    companyName: "",
                    creditCode: "",
                    legalPerson: "",
                    contactPersonName: "",
                    contactPersonTel: "",
                    areaCode: "",
                    companyAddress: "",
                    completeAddress: "",
                    createUserId: "",
                    createUserName: "",
                    createDate: "",
                    areaCodes: []
                },
                addrules: {
                    companyName: [
                        {required: true, message: "请输入企业名称", trigger: "blur"},
                    ],
                    legalPerson: [
                        {required: true, message: "请输入法人代表姓名", trigger: "blur"},
                    ],
                    // contactPersonTel: [
                    //     {required: true, message: "联系方式", trigger: "blur"},
                    // ],
                    areaCodes: [
                        {required: true, message: "请选择所属单位", trigger: "change"}
                    ],
                    companyAddress: [
                        {required: true, message: "请输入企业详细地址", trigger: "blur"},
                    ],
                },
            };
        },
        created() {
            this.AreaCodeQuery();
        },
        methods: {
            addhandle(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.allAddress = this.$refs.myCascader.getCheckedNodes(this.addform.areaCodes)[0].pathLabels.join('').replace(/,/g,"");
                        for (let e of this.addform.areaCodes) {
                            this.addform.areaCode = e;
                        }
                        this.addform.completeAddress = this.allAddress + this.addform.companyAddress;
                        create(this.addform).then(response => {
                            if (response.status == 1) {
                                this.$message.success("创建成功");
                                this.back();
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            back() {
                this.$router.push({path: "/hqgj/BasicData/enterprise"});
            },
            //获取行政区划数据
            AreaCodeQuery() {
                getAreaTree({pid: 430702, type: 1, hasSelf: "true"})
                    .then(response => {
                        this.areaCodeOptions = response.data;
                    })
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

    .handle-box {
        padding-top: 40px;
        padding-bottom: 40px;
    }
</style>
