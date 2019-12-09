<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <!--<el-col :span="2">-->
                        <!--<el-button @click="createPerson" icon="el-icon-plus" style="float: left" type="primary">添加</el-button>-->
                    <!--</el-col>-->
                    <el-col :span="16">
                        <el-dropdown style="line-height: 35px;" trigger="click">
                            <el-button>
                                批量操作<i class="el-icon-arrow-down el-icon--right"></i>
                            </el-button>
                            <!--<span style="color: black" >批量操作<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></span>-->
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="importXls">批量导入</el-dropdown-item>
                                <el-dropdown-item command="exportXls">批量导出</el-dropdown-item>
                                <el-dropdown-item command="deleteIds">批量删除</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </el-col>
                    <el-col :span="6">
                        <el-input
                                @change="toggleChange"
                                v-model="query.name"
                                class="input-with-select"
                                placeholder="请输入姓名或手机号码"
                                style="float: right"
                        ></el-input>
                    </el-col>
                    <el-col :span="2">
                        <el-button @click="search" icon="el-icon-search" style="float: right" type="primary">搜索</el-button>
                    </el-col>
                </el-row>

            </div>
            <el-table
                    :data="rows"
                    @selection-change="handleSelectionChange"
                    @sort-change="handleSort"
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" type="selection" width="55"></el-table-column>
                <el-table-column label="姓名" prop="name" sortable='custom' width="300">
                </el-table-column>
                <el-table-column label="手机号码" prop="mobile">
                </el-table-column>
                <el-table-column label="添加时间" prop="createDate" width="500">
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                    <template slot-scope="scope">
                        <!--<el-button @click="" height="40" type="text" @click="editPerson">编辑</el-button>-->
                        <!--<span class="strightline">|</span>-->
                        <el-button @click="Delete(scope.$index,scope.row)" type="text">删除</el-button>
                        <!--<span class="strightline">|</span>-->
                        <!--<el-button @click="previewPerson" type="text">详情</el-button>-->
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
    import {personPage,deletePerson} from "@/api/hqgj/enterprise";
    export default {
        name: "Member",
        data() {
            return {
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                rows:[],
                query: {
                    companyId:"",
                    name: ""
                },
            };
        },
        created() {
            this.getlist();
            // this.AreaCodeQuery();
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
            getlist(){
                this.query.companyId = this.$route.query.id;
                this.query = Object.assign(this.query, {
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                personPage(this.query).then(response => {
                    this.total= response.total;
                    this.rows = response.rows;
                    console.log(response);
                })
            },
            search(){
                this.handleQuery();
            },
            Delete(index,data){
                this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.id = data.id;
                        deletePerson(this.id).then(response => {
                            this.$message.success("删除成功");
                            this.gelist();
                        });
                    })
                    .catch(() => {

                    });
            },
            createPerson(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise/createPerson" });
            },
            editPerson(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise/editPerson" });
            },
            previewPerson(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise/detailsPerson" });
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
