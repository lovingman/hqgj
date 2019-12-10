<template>
    <div class="container">
        <div class="title">编辑服务机构</div>
        <div class="handle-box">
            <el-form :model="form" :rules="rules" ref="ruleForm" label-width="600px" class="demo-ruleForm">
                <el-form-item label="机构名称:" prop="orgName">
                    <el-input v-model="form.orgName" style="width: 50%" placeholder="请输入机构名称"></el-input>
                </el-form-item>
                <el-form-item label="统一社会信用代码:" prop="creditCode">
                    <el-input v-model="form.creditCode" style="width: 50%" placeholder="请输入18位统一社会信用代码"></el-input>
                </el-form-item>
                <el-form-item label="联系方式:" prop="contactPersonTel">
                    <el-input v-model="form.contactPersonName" style="width: 21%" placeholder="联系人姓名"></el-input>
                    <span style="padding-left: 10px;padding-right: 10px">--</span>
                    <el-input v-model="form.contactPersonTel" style="width: 26%" placeholder="联系电话"></el-input>
                </el-form-item>
                <el-form-item label="地址:" prop="areaCode">
                    <el-cascader
                            :options="areaCodeOptions"
                            :props="areaCodeProps"
                            change-on-select
                            clearable
                            filterable
                            placeholder="请选择行政区划"
                            style="width: 50%"
                            v-model="areaCode"/>
                    <!--<el-select  placeholder="请选择省份" style="width: 12%;margin-right: 5px">-->
                        <!--<el-option label="区域一" value="shanghai"></el-option>-->
                        <!--<el-option label="区域二" value="beijing"></el-option>-->
                    <!--</el-select>-->
                    <!--<el-select  placeholder="请选择市" style="width: 10%;margin-right: 5px">-->
                        <!--<el-option label="区域一" value="shanghai"></el-option>-->
                        <!--<el-option label="区域二" value="beijing"></el-option>-->
                    <!--</el-select>-->
                    <!--<el-select  placeholder="请选择区县" style="width: 12%;margin-right: 5px">-->
                        <!--<el-option label="区域一" value="shanghai"></el-option>-->
                        <!--<el-option label="区域二" value="beijing"></el-option>-->
                    <!--</el-select>-->
                    <!--<el-select  placeholder="请选择乡镇街道" style="width: 15%;margin-right: 5px">-->
                        <!--<el-option label="区域一" value="shanghai"></el-option>-->
                        <!--<el-option label="区域二" value="beijing"></el-option>-->
                    <!--</el-select>-->

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
                    <el-button type="primary" @click="handleEdit">提交</el-button>
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
                areaCode: [], //编辑行政区划
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
                    content: "",
                    type: "",
                    areaCodes: []
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
                        this.areaCode = [];
                        var str = this.form.areaCode;
                        var arr = [4, 6, 9, 12];
                        for (var i = 0; i < 4; i++) {
                            this.areaCode[i] = str.substring(0, arr[i]);
                        }
                    })

            },
            handleEdit(){
                for (let e of this.areaCode) {
                    this.form.areaCode = e;
                }
                update(this.form).then(response => {
                    if (response.status == 1){
                        this.$message.success("编辑成功");
                        this.back();
                    }
                })
            },
            back(){
                this.$router.push({ path: "/hqgj/BasicData/service" });
            },
            clickitem(e) {
                e === this.form.type ? this.form.type = '' : this.form.type = e
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
