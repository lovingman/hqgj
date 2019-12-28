<template>
    <div class="container">
        <div class="title">政策服务详情</div>
        <div class="handle-box">
            <el-form :model="serviceForm" class="demo-ruleForm" label-width="100px">
                <el-form-item label="标题名称:" prop="title">
                    <span>{{serviceForm.title}}</span>
                </el-form-item>
                <el-form-item label="附件:" prop="basicAnnexes"  v-for="item in serviceForm.basicAnnexes">
                    <span>{{item.fileName+"."+item.fileURL.substr(item.fileURL.lastIndexOf(".") + 1)}}</span>
                </el-form-item>
                <el-form-item label="创建时间:" prop="createDate">
                    <span>{{serviceForm.createDate}}</span>
                </el-form-item>
                <el-form-item label="创建人姓名:" prop="createUserName">
                    <span>{{serviceForm.createUserName}}</span>
                </el-form-item>
                <el-form-item>
                    <el-button @click="back" type="primary">返回</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getServeById} from "@/api/hqgj/Policies";
    export default {
        name: "details",
        data() {
            return {
                //数据
                serviceForm: {
                    title: "",
                    createUserName: "",
                    createDate: "",
                    basicAnnexes:[]
                },
            };
        },
        created(){
            this.getDetails();
        },
        methods: {
            getDetails() {
                this.id = this.$route.query.id;
                getServeById(this.id)
                    .then(response => {
                        this.serviceForm = response.data;
                    })

            },
            back() {
                this.$router.push({
                    path: "/hqgj/Policies/legalService"
                });
            },
            // change(val) {
            //   console.log(val);
            // }
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
