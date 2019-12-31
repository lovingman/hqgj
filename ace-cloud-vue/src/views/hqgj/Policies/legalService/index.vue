<template>
    <div class="main-box">
        <div class="header">
            <el-row>
                <el-button @click="create" style="float:left;" type="primary">创建</el-button>
                <el-col :span="10" class="selectSearch">
                    <el-input class="input-with-select" clearable placeholder="请输入名称" v-model="query.title">
                        <el-button :loading="loading" @click="search" icon="el-icon-search" slot="append"></el-button>
                    </el-input>
                </el-col>
            </el-row>
        </div>
        <div class="table-box">
            <el-table :data="tableData" v-loading="loading" style="width: 100%">
                <el-table-column align="center" label="序号" type="index" width="80"></el-table-column>
                <el-table-column label="名称" prop="title" sortable></el-table-column>
                <el-table-column label="上传时间" prop="createDate" sortable></el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="240">
                    <template slot-scope="scope">
                        <el-button @click="download(scope.$index,scope.row)" type="text">下载</el-button>
                        <el-button @click="edit(scope.$index,scope.row)" type="text">编辑</el-button>
                        <el-button @click="handleDele(scope.$index,scope.row)" type="text">删除</el-button>
                        <el-button @click="preview(scope.$index,scope.row)" type="text">详情</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    :current-page="currentPage"
                    :page-size="pagesize"
                    :total="total"
                    @current-change="handleCurrentChange"
                    @size-change="handleSizeChange"
                    background
                    layout="total,sizes,prev, pager, next ,jumper"
            ></el-pagination>
        </div>
    </div>
</template>

<script>
    import {getServe, deleteServeById} from "@/api/hqgj/Policies";
    import {getAnnex} from "@/api/hqgj/BWSService";

    export default {
        name: "index",
        data() {
            return {
                loading:false,
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                query: {
                    title: "" //搜索
                },
                query2: {
                    relationId: "" //搜索
                },
                tableData: []
            };
        },
        created() {
            this.getlist();
        },
        methods: {
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
            getlist() {
                this.loading=true;
                this.query = Object.assign(this.query, {
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                getServe(this.query).then(response => {
                    this.total = response.total;
                    this.tableData = response.rows
                    this.loading=false;
                })
            },
            //搜索
            search() {
                this.handleQuery();
            },
            //删除
            handleDele(index, data) {
                this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.id = data.id;
                        deleteServeById(this.id).then(response => {
                            this.$message.success("删除成功");
                            this.getlist();
                        });
                    })
                    .catch(() => {
                        // this.$message('已取消下载')
                    });
            },
            //下载
            download(index, data) {
                this.$confirm("此操作将下载所有附件, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.query2.relationId = data.id;
                        getAnnex(this.query2).then(response => {
                            for (var i = 0; i < response.rows.length; i++) {
                                this.downloading(response.rows[i].fileURL);
                            }
                        });
                    })
                    .catch(() => {
                        // this.$message.error('error')
                    });
            },
            downloading(url) {
                const iframe = document.createElement("iframe");
                iframe.style.display = "none";  // 防止影响页面
                iframe.style.height = 0;  // 防止影响页面
                iframe.src = url;
                document.body.appendChild(iframe);  // 这一行必须，iframe挂在到dom树上才会发请求
                // 5分钟之后删除（onload方法对于下载链接不起作用）
                setTimeout(()=>{
                    iframe.remove();
                },1000);
            },
            //选择tableSize事件
            handleTableSize() {
            },
            //选择tablePage事件
            handleTableCurrent() {
            },
            //创建
            create() {
                this.$router.push({path: "/hqgj/Policies/legalService/add"});
            },
            //编辑
            edit(index, data) {
                this.$router.push({path: "/hqgj/Policies/legalService/edit", query: {id: data.id}});
            },
            //详情
            preview(index, data) {
                this.$router.push({path: "/hqgj/Policies/legalService/details", query: {id: data.id}});
            }
        }
    };
</script>

<style lang="less" scoped>
    .main-box {
        background-color: #fff;

        .header {
            padding: 30px 30px 10px 30px;

            /deep/ .el-button--medium {
                border-radius: 4px;
                height: 36px;
            }

            .selectSearch {
                float: right;
            }
        }

        .table-box {
            padding: 0 30px 30px 30px;

            /deep/ .el-table th:last-child {
                text-align: right;
                padding-right: 10px;
            }
        }
    }
</style>>

