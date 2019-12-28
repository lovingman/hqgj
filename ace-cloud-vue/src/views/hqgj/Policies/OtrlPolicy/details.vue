<template>
    <div class="container">
        <div class="title">政策服务详情</div>
        <div class="handle-box">
            <el-form :model="serviceForm" class="demo-ruleForm" label-width="100px">
                <el-form-item label="标题名称:" prop="title">
                    <span>{{serviceForm.title}}</span>
                </el-form-item>
                <el-form-item label="来源:" prop="source">
                    <span>{{serviceForm.source}}</span>
                </el-form-item>
                <el-form-item label="创建时间:" prop="createDate">
                    <span>{{serviceForm.createDate}}</span>
                </el-form-item>
                <el-form-item label="内容:" prop="content">
                    <span>{{serviceForm.content}}</span>
                </el-form-item>
                <el-form-item>
                    <el-button @click="back" type="primary">返回</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getPolicyById} from "@/api/hqgj/Policies";
    export default {
        name: "details",
        data() {
            return {
                //数据
                serviceForm: {
                    title: "",
                    source: "",
                    content: ""
                },
            };
        },
        created(){
            this.getDetails();
        },
        methods: {
            getDetails() {
                this.id = this.$route.query.id;
                getPolicyById(this.id)
                    .then(response => {
                        this.serviceForm = response.data;
                    })

            },
            back() {
                this.$router.push({
                    path: "/hqgj/Policies/OtrlPolicy"
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
