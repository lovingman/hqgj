<template>
    <div class="container">
        <div class="title" style="margin-bottom: 30px">编辑企业</div>
        <div class="handle-box">
            <el-form :model="form" :rules="rules" class="demo-ruleForm" label-width="100px" ref="ruleForm">
                <el-form-item label="企业名称:" prop="companyName">
                    <el-input placeholder="请输入企业名称" maxlength="50" show-word-limit v-model.trim="form.companyName" style="width: 30%"></el-input>
                </el-form-item>
                <!--<el-form-item label="统一社会信用代码:" prop="creditCode">-->
                    <!--<el-input placeholder="请输入18位统一社会信用代码" v-model="form.creditCode" style="width: 50%"></el-input>-->
                <!--</el-form-item>-->
                <el-form-item label="法人代表:" prop="legalPerson">
                    <el-input placeholder="请输入法人代表姓名" v-model.trim="form.legalPerson" style="width: 30%"></el-input>
                </el-form-item>
                <el-form-item label="联系方式:" prop="contactPersonTel">
                    <el-input placeholder="联系人姓名" v-model.trim="form.contactPersonName" style="width: 12%"></el-input>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <el-input placeholder="联系电话" v-model.trim="form.contactPersonTel" style="width: 16%"></el-input>
                </el-form-item>
                <el-form-item label="地址:" prop="areaCodes">
                    <el-cascader
                            placeholder="请选择行政区划"
                            ref="myCascader"
                            v-model="form.areaCodes"
                            :options="areaCodeOptions"
                            :props="areaCodeProps"
                            clearable
                            change-on-select
                            filterable
                            style="width: 30%"/>
                </el-form-item>
                <el-form-item prop="companyAddress">
                    <el-input
                            v-model.trim="form.companyAddress"
                            placeholder="请输入企业详细地址，xx社区xx路xx号(xx小区xx栋xx楼xx号）"
                            style="width: 30%"
                            type="textarea"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="back">取消</el-button>
                    <el-button @click="editorEnd('ruleForm')" type="primary">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getById,update} from "@/api/hqgj/enterprise";
    import {getAreaTree, getDict} from "@/api/sys";
    export default {
        name: "edit",
        data() {
            return {
                allAddress: '',//详细完整地址
                areaCodeOptions: [], //行政区划
                areaCodeProps: {
                    value: "id",
                    label: "text"
                },
               //编辑
                form: {
                    companyName: "",
                    creditCode: "",
                    legalPerson: "",
                    contactPersonName: "",
                    contactPersonTel: "",
                    areaCode: "",
                    companyAddress: "",
                    completeAddress:"",
                    createUserId: "",
                    createUserName: "",
                    areaCodes: [], //编辑行政区划
                    createDate: ""
                },
                rules: {
                    companyName: [
                        { required: true, message: "请输入企业名称", trigger: "blur" },
                    ],
                    legalPerson: [
                        { required: true, message: "请输入法人代表姓名", trigger: "blur" },
                    ],
                    // contactPersonTel: [
                    //     { required: true, message: "联系方式", trigger: "blur" },
                    // ],
                    areaCodes: [
                        { required: true, message: "请选择所属单位", trigger: "change" }
                    ],
                    companyAddress: [
                        { required: true, message: "请输入企业详细地址", trigger: "blur" },
                    ],
                },
            };
        },
        created() {
            this.getDetails();
            this.AreaCodeQuery();
        },
        methods: {
            // handleChange(value) {
            // },
            getDetails() {
                this.id = this.$route.query.id;
                getById(this.id)
                    .then(response => {
                        this.loading = false;
                        this.form = response.data;
                        this.form.areaCodes = [];
                        var str = this.form.areaCode;
                        var arr = [6, 9, 12];
                        for (var i = 0; i < 3; i++) {
                            if(arr[i]<=str.length){
                                this.form.areaCodes[i] = str.substring(0, arr[i]);
                            }
                        }
                    })

            },
            back() {
                this.$router.push({path: "/hqgj/BasicData/enterprise"});
            },
            editorEnd(formName){
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.allAddress = this.$refs.myCascader.getCheckedNodes(this.form.areaCodes)[0].pathLabels.join('').replace(/,/g,"");
                        for (let e of this.form.areaCodes) {
                            this.form.areaCode = e;
                        }
                        this.form.completeAddress = this.allAddress + this.form.companyAddress;
                        update(this.form).then(response => {
                            if (response.status == 1){
                                this.$message.success("编辑成功");
                                this.back();
                            }
                        })
                    } else {
                        return false;
                    }
                });
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
