<template>
    <div class="container">
        <div class="title">企业创建</div>
        <div class="handle-box">
            <el-form :model="addform" :rules="addrules" class="demo-ruleForm" label-width="600px" ref="ruleForm">
                <el-form-item label="企业名称:" prop="companyName">
                    <el-input placeholder="请输入企业名称" style="width: 50%" v-model="addform.companyName"></el-input>
                </el-form-item>
                <el-form-item label="统一社会信用代码:" prop="creditCode">
                    <el-input placeholder="请输入18位统一社会信用代码" style="width: 50%" v-model="addform.creditCode"></el-input>
                </el-form-item>
                <el-form-item label="法定代表人:" prop="legalPerson">
                    <el-input placeholder="请输入法定代表人姓名" style="width: 50%" v-model="addform.legalPerson"></el-input>
                </el-form-item>
                <el-form-item label="联系方式:" prop="contactPersonTel">
                    <el-input placeholder="联系人姓名" style="width: 21%" v-model="addform.contactPersonName"></el-input>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <el-input placeholder="联系电话" style="width: 26%" v-model="addform.contactPersonTel"></el-input>
                </el-form-item>
                <el-form-item label="地址:" prop="areaCodes">
                    <el-cascader
                            :options="areaCodeOptions"
                            :props="areaCodeProps"
                            change-on-select
                            clearable
                            filterable
                            placeholder="请选择行政区划"
                            style="width: 50%"
                            v-model="addform.areaCodes"/>
                    <!--<el-select placeholder="请选择省份" style="width: 12%;margin-right: 5px">-->
                    <!--<el-option label="区域一" value="shanghai"></el-option>-->
                    <!--<el-option label="区域二" value="beijing"></el-option>-->
                    <!--</el-select>-->
                    <!--<el-select placeholder="请选择市" style="width: 10%;margin-right: 5px">-->
                    <!--<el-option label="区域一" value="shanghai"></el-option>-->
                    <!--<el-option label="区域二" value="beijing"></el-option>-->
                    <!--</el-select>-->
                    <!--<el-select placeholder="请选择区县" style="width: 12%;margin-right: 5px">-->
                    <!--<el-option label="区域一" value="shanghai"></el-option>-->
                    <!--<el-option label="区域二" value="beijing"></el-option>-->
                    <!--</el-select>-->
                    <!--<el-select placeholder="请选择乡镇街道" style="width: 15%;margin-right: 5px">-->
                    <!--<el-option label="区域一" value="shanghai"></el-option>-->
                    <!--<el-option label="区域二" value="beijing"></el-option>-->
                    <!--</el-select>-->
                </el-form-item>
                <el-form-item prop="companyAddress">
                    <el-input
                            placeholder="请输入企业详细地址，xx社区xx路xx号(xx小区xx栋xx楼xx号）"
                            style="width: 50%"
                            type="textarea"
                            v-model="addform.companyAddress"></el-input>
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
                        {required: true, message: "请输入法定代表人姓名", trigger: "blur"},
                    ],
                    contactPersonTel: [
                        {required: true, message: "联系方式", trigger: "blur"},
                    ],
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
                        for (let e of this.addform.areaCodes) {
                            this.addform.areaCode = e;
                        }
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
                getAreaTree({pid: 4307, type: 1, hasSelf: "true"})
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
