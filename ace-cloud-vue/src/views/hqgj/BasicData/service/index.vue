<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="2">
                        <el-button @click="create" icon="el-icon-plus" style="float: left" type="primary">创建</el-button>
                    </el-col>
                    <el-col :span="10">
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
                    <el-col :span="4">
                        <el-select placeholder="全部类型">
                            <el-option>
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="6">
                        <el-input
                                 v-model="query.orgName"
                                @change="toggleChange"
                                class="input-with-select"
                                placeholder="请输入机构名称"
                                style="float: right"
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
                    @selection-change="handleSelectionChange"
                    @sort-change="handleSort"
                    border
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" type="selection" width="55"></el-table-column>
                <el-table-column label="机构名称" prop="orgName" sortable='custom' >
                </el-table-column>
                <el-table-column  label="机构类型" prop="type" width="250">
                </el-table-column>
                <el-table-column label="联系方式" prop="contactPersonTel" width="300">
                </el-table-column>
                <el-table-column label="地址" prop="orgAddress" width="350">
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button  height="40" type="text" @click="person(scope.$index,scope.row)">成员管理</el-button>
                        <span class="strightline">|</span>
                        <el-button  height="40" type="text" @click="edit(scope.$index,scope.row)">编辑</el-button>
                        <span class="strightline">|</span>
                        <el-button  type="text" @click="handleDele(scope.$index,scope.row)">删除</el-button>
                        <span class="strightline">|</span>
                        <el-button type="text" @click="preview(scope.$index,scope.row)">详情</el-button>
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
    import {getList,deleteById} from "@/api/hqgj/service"
    export default {
        name: "index",
        data() {
            return {
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                list:[],
                query: {
                    orgName: ""
                },
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
                this.query = Object.assign(this.query, {
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                getList(this.query).then(response => {
                    this.total = response.total;
                    this.list = response.rows;
                    console.log(response);
                })
            },
            search(){
                this.handleQuery();
            },
            handleDele(index,data){
                this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.id = data.id;
                        deleteById(this.id).then(response => {
                            this.$message.success("删除成功");
                            this.getlist();
                        });
                    })
                    .catch(() => {

                    });
            },
            person(index,data){
                this.$router.push({ path: "/hqgj/BasicData/service/Member", query: { id: data.id }});
            },
            create(){
                this.$router.push({ path: "/hqgj/BasicData/service/create" });
            },
            edit(index,data){
                this.$router.push({ path: "/hqgj/BasicData/service/edit", query: { id: data.id }});
            },
            preview(index,data){
                this.$router.push({ path: "/hqgj/BasicData/service/details", query: { id: data.id } });
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
