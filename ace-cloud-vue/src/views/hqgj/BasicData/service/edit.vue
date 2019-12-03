<template>
    <div class="container">
        <div class="handle-box">
            <span style="font-size: 25px;font-weight:bold">编辑服务机构</span>
        </div>
        <el-form :rules="rules" ref="ruleForm" label-width="600px" class="demo-ruleForm">
            <el-form-item label="机构名称:" prop="name">
                <el-input style="width: 50%" placeholder="请输入机构名称"></el-input>
            </el-form-item>
            <el-form-item label="统一社会信用代码:" prop="name">
                <el-input style="width: 50%" placeholder="请输入18位统一社会信用代码"></el-input>
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
                        placeholder="请输入机构详细地址，xx社区xx路xx号(xx小区xx栋xx楼xx号）"
                        style="width: 50%"></el-input>
            </el-form-item>
            <el-form-item label="简介:" prop="name">
                <el-input
                        type="textarea"
                        maxlength="500"
                        rows="4"
                        show-word-limit
                        placeholder="请输入机构简介"
                        style="width: 50%"></el-input>
            </el-form-item>
            <el-form-item label="类型:" prop="name">
                <el-radio-group style="margin-left: 20px"
                                v-model="add.type">
                    <el-radio :label="1" @click.native.prevent="clickitem(1)">律师事务所</el-radio>
                    <el-radio :label="2" @click.native.prevent="clickitem(2)">会计事务所</el-radio>
                    <el-radio :label="3" @click.native.prevent="clickitem(3)">培训机构</el-radio>
                </el-radio-group>
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
        name: "edit",
        data() {
            return {
                add: {
                    type:1
                }
            };
        },
        created(){

        },
        methods:{
            back(){
                this.$router.push({ path: "/hqgj/BasicData/service" });
            },
            clickitem(e) {
                e === this.add.type ? this.add.type = '' : this.add.type = e
            },

        }
    }
</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }
</style>
