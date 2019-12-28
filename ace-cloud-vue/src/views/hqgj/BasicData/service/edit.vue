<template>
    <div class="container">
        <div class="title">编辑服务机构</div>
        <div class="handle-box">
            <el-form :model="form" :rules="rules" ref="ruleForm" label-width="600px" class="demo-ruleForm">
                <el-form-item label="机构名称:" prop="orgName">
                    <el-input v-model="form.orgName" maxlength="50" show-word-limit style="width: 50%" placeholder="请输入机构名称"></el-input>
                </el-form-item>
                <!--<el-form-item label="统一社会信用代码:" prop="creditCode">-->
                    <!--<el-input v-model="form.creditCode" style="width: 50%" placeholder="请输入18位统一社会信用代码"></el-input>-->
                <!--</el-form-item>-->
                <el-form-item label="联系方式:" prop="contactPersonTel">
                    <el-input v-model="form.contactPersonName" style="width: 21%" placeholder="联系人姓名"></el-input>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <el-input v-model="form.contactPersonTel" style="width: 26%" placeholder="联系电话"></el-input>
                </el-form-item>
                <el-form-item label="地址:" prop="areaCodes">
                    <el-cascader
                            :options="areaCodeOptions"
                            :props="areaCodeProps"
                            ref="myCascader"
                            change-on-select
                            clearable
                            filterable
                            placeholder="请选择行政区划"
                            style="width: 50%"
                            v-model="form.areaCodes"/>
                </el-form-item>
                <el-form-item prop="orgAddress">
                    <el-input
                            v-model="form.orgAddress"
                            type="textarea"
                            placeholder="请输入机构详细地址，xx社区xx路xx号(xx小区xx栋xx楼xx号）"
                            style="width: 50%"></el-input>
                </el-form-item>
                <el-form-item label="简介:" prop="content">
                    <el-input
                            v-model="form.content"
                            @change="handleChange"
                            ref="myCascader"
                            type="textarea"
                            maxlength="500"
                            rows="4"
                            show-word-limit
                            placeholder="请输入机构简介"
                            style="width: 50%"></el-input>
                </el-form-item>
                <el-form-item label="类型:" prop="type">
                    <el-radio v-model="form.type" label="1">律师事务所</el-radio>
                    <el-radio v-model="form.type" label="2">会计事务所</el-radio>
                    <el-radio v-model="form.type" label="3">培训机构</el-radio>
                    <!--<el-radio-group style="margin-left: 20px" lable="form.type"-->
                                    <!--v-model="form.type">-->
                        <!--<el-radio :label="1" @click.native.prevent="clickitem(1)">律师事务所</el-radio>-->
                        <!--<el-radio :label="2" @click.native.prevent="clickitem(2)">会计事务所</el-radio>-->
                        <!--<el-radio :label="3" @click.native.prevent="clickitem(3)">培训机构</el-radio>-->
                    <!--</el-radio-group>-->
                </el-form-item>
                <el-form-item>
                    <el-button @click="back">取消</el-button>
                    <el-button type="primary" @click="handleEdit('ruleForm')">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getById,update} from "@/api/hqgj/service";
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
                    orgName: "",
                    creditCode: "",
                    contactPersonName: "",
                    contactPersonTel: "",
                    areaCode: "",
                    orgAddress: "",
                    completeAddress:"",
                    content: "",
                    type: "",
                    areaCodes: []
                },
                rules: {
                    orgName: [
                        {required: true, message: "请输入机构名称", trigger: "blur"},
                    ],
                    contactPersonTel: [
                        {required: true, message: "请输入联系方式", trigger: "blur"},
                    ],
                    areaCodes: [
                        {required: true, message: "请选择所属行政区划", trigger: "change"}
                    ],
                    orgAddress: [
                        {required: true, message: "请输入机构详细地址", trigger: "blur"},
                    ],
                    content: [
                        {required: true, message: "请输入机构简介", trigger: "blur"},
                    ],
                    type: [
                        {required: true, message: "请选择机构类型", trigger: "change"}
                    ],
                },
            };
        },
        created(){
            this.getDetails();
            this.AreaCodeQuery();
        },
        methods:{
            getDetails() {
                this.id = this.$route.query.id;
                getById(this.id)
                    .then(response => {
                        this.form = response.data;
                        this.form.areaCodes = [];
                        var str = this.form.areaCode;
                        var arr = [6, 9, 12];
                        for (var i = 0; i < 3; i++) {
                            this.form.areaCodes[i] = str.substring(0, arr[i]);
                        }
                    })

            },
            handleEdit(formName){
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.allAddress = this.$refs.myCascader.getCheckedNodes(this.form.areaCodes)[0].pathLabels.join('').replace(/,/g,"");
                        for (let e of this.form.areaCodes) {
                            this.form.areaCode = e;
                        }
                        this.form.completeAddress = this.allAddress + this.form.orgAddress;
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
            back(){
                this.$router.push({ path: "/hqgj/BasicData/service" });
            },
            clickitem(e) {
                e === this.form.type ? this.form.type = '' : this.form.type = e
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
    .handle-box{
        padding-top: 40px;
        padding-bottom: 40px;
    }
</style>
