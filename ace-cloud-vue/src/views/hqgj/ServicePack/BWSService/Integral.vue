<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="18">
                        <el-button @click="create" icon="el-icon-plus" style="float: left" type="primary">创建</el-button>
                    </el-col>
                    <el-col :span="4">
                        <el-input
                                v-model="query.companyName"
                                clearable
                                class="input-with-select"
                                placeholder="请输入企业名称"
                                style="float: right;width: 250px"
                        ></el-input>
                    </el-col>
                    <el-col :span="2">
                        <el-button @click="search" icon="el-icon-search" style="float: right" type="primary">搜索
                        </el-button>
                    </el-col>
                </el-row>
            </div>
            <el-table
                    :data="list"
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" type="selection" width="55"></el-table-column>
                <el-table-column label="企业名称" prop="companyName">
                </el-table-column>
                <el-table-column width="200" label="注册方式" prop="register">
                    <template slot-scope="scope">
                        <div type="text" v-if="scope.row.register=='1'">小程序</div>
                        <div type="text" v-if="scope.row.register=='2'">线下窗口</div>
                    </template>
                </el-table-column>
                <el-table-column width="150" label="获得积分" prop="getIntegral">
                </el-table-column>
                <el-table-column label="剩余积分" prop="surplusIntegral" width="150">
                </el-table-column>
                <el-table-column label="创建时间" prop="createDate" width="250">
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                    <template slot-scope="scope">
                        <!--<el-button @click="" type="text">编辑</el-button>-->
                        <!--<span class="strightline">|</span>-->
                        <el-button @click="deletIntegral(scope.$index,scope.row)" type="text">删除</el-button>
                        <!--<span class="strightline">|</span>-->
                        <!--<el-button @click="preview" type="text">详情</el-button>-->
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-size="pagesize"
                    background
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>

        </div>
    </div>
</template>

<script>
    import {getPage,deleteIntegral} from "@/api/hqgj/BWSService";
    export default {
        name: "index",
        data() {
            return {
                loading:false,
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                list:[],
                query: {
                    companyName:""
                },
                value: '',
                value2: ''
            };
        },
        created(){
            this.getlist();
        },
        methods:{
            handleQuery: function () {
                this.currentPage = 1;
                this.getlist();
            },
            handleSizeChange: function (size) {
                this.pagesize = size;
                //每页下拉显示数据
                this.getlist();
            },
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage;
                //点击第几页
                this.getlist();
            },
            //获取列表数据
            getlist() {
                this.loading=true;
                this.query = Object.assign(this.query, {
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                getPage(this.query).then(response => {
                    this.total = response.total;
                    this.list = response.rows;
                    this.loading=false;
                })
            },
            //搜索
            search(){
                this.handleQuery();
            },
            //deletIntegral删除
            deletIntegral(index,data){
                this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.id = data.id;
                        deleteIntegral(this.id).then(response => {
                            this.$message.success("删除成功");
                            this.getlist();
                        });
                    })
                    .catch(() => {

                    });
            },
            create(){
                this.$router.push({ path: "/hqgj/ServicePack/BWSService/create" });
            },
            preview(){
                this.$router.push({ path: "/hqgj/ServicePack/BWSService/Examine" });
            }
        }
    }
</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }
</style>
