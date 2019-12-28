<template>
    <div class="container">
        <div class="title">创建服务机构</div>
        <div class="handle-box">
            <el-form :model="addform" :rules="addrules" class="demo-ruleForm" label-width="600px" ref="ruleForm">
                <el-form-item label="机构名称:" prop="orgName">
                    <el-input v-model="addform.orgName" maxlength="50" show-word-limit placeholder="请输入机构名称" style="width: 50%"></el-input>
                </el-form-item>
                <!--<el-form-item label="统一社会信用代码:" prop="creditCode">-->
                    <!--<el-input v-model="addform.creditCode" placeholder="请输入18位统一社会信用代码" style="width: 50%"></el-input>-->
                <!--</el-form-item>-->
                <el-form-item label="联系方式:" prop="contactPersonTel">
                    <el-input v-model="addform.contactPersonName" placeholder="联系人姓名" style="width: 21%"></el-input>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <el-input v-model="addform.contactPersonTel" placeholder="联系电话" style="width: 26%"></el-input>
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
                            v-model="addform.areaCodes"/>
                </el-form-item>
                <el-form-item prop="orgAddress">
                    <el-input
                            v-model="addform.orgAddress"
                            placeholder="请输入机构详细地址，xx社区xx路xx号(xx小区xx栋xx楼xx号）"
                            style="width: 50%"
                            type="textarea"></el-input>
                </el-form-item>
                <el-form-item label="简介:" prop="content">
                    <el-input
                            v-model="addform.content"
                            maxlength="500"
                            placeholder="请输入机构简介"
                            rows="4"
                            show-word-limit
                            style="width: 50%"
                            type="textarea"></el-input>
                </el-form-item>
                <el-form-item label="类型:" prop="type">
                    <el-radio-group style="margin-left: 20px" v-model="addform.type">
                        <el-radio :label="1" @click.native.prevent="clickitem(1)">律师事务所</el-radio>
                        <el-radio :label="2" @click.native.prevent="clickitem(2)">会计事务所</el-radio>
                        <el-radio :label="3" @click.native.prevent="clickitem(3)">培训机构</el-radio>
                    </el-radio-group>
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
    import {create} from "@/api/hqgj/service";
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
                addform: {
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
                addrules: {
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
        created() {
            this.AreaCodeQuery();
        },
        methods: {
            handleadd(formName){
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.allAddress = this.$refs.myCascader.getCheckedNodes(this.addform.areaCodes)[0].pathLabels.join('').replace(/,/g,"");
                        for (let e of this.addform.areaCodes) {
                            this.addform.areaCode = e;
                        }
                        this.addform.completeAddress = this.allAddress + this.addform.orgAddress;
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
                this.$router.push({path: "/hqgj/BasicData/service"});
            },
            clickitem(e) {
                e === this.addform.type ? this.addform.type = '' : this.addform.type = e
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
