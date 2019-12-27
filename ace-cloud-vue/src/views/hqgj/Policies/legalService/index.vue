<template>
    <div class="main-box">
        <div class="header">
            <el-row>
                <el-button @click="create" style="float:left;" type="primary">创建</el-button>
                <el-col :span="10" class="selectSearch">
                    <el-input class="input-with-select" clearable placeholder="请输入名称" v-model="query.title">
                        <el-button @click="search" slot="append">搜索</el-button>
                    </el-input>
                </el-col>
            </el-row>
        </div>
        <div class="table-box">
            <el-table :data="tableData" style="width: 100%">
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
        created(){
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
                this.query = Object.assign(this.query, {
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                getServe(this.query).then(response => {
                    this.total = response.total;
                    this.tableData = response.rows
                })
            },
            //搜索
            search(){
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
            download(index,data){
                this.$confirm("此操作将下载所有附件, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.query2.relationId = data.id;
                        getAnnex(this.query2).then(response => {
                            for(var i=0;i<response.rows.length;i++){
                                let link = document.createElement('a')
                                link.href = response.rows[i].fileURL;
                                // link.download = response.rows[i].fileName + '.'+response.rows[i].fileURL.substr(response.rows[i].fileURL.lastIndexOf(".") + 1)
                                // link.download = '武陵区中小企业公共服务平台免费服务申请表.doc';
                                link.click()
                            }
                        });
                    })
                    .catch(() => {
                        // this.$message.error('error')
                    });
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
            edit(index,data){
                this.$router.push({path: "/hqgj/Policies/legalService/edit",query:{id:data.id}});
            },
            //详情
            preview(index, data) {
                this.$router.push({path: "/hqgj/Policies/legalService/details",query:{id:data.id}});
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

                /deep/ .el-button--medium {
                    color: #fff;
                    background-color: #007cff;
                    border-color: #007cff;
                    border-radius: 0;
                }
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

