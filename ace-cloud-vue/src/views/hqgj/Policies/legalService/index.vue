<template>
    <div class="main-box">
        <div class="header">
            <el-row>
                <el-button @click="create" style="float:left;" type="primary">创建</el-button>
                <el-col :span="10" class="selectSearch">
                    <el-input class="input-with-select" clearable placeholder="请输入名称" v-model="query.name">
                        <el-button slot="append">搜索</el-button>
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
                        <el-button type="text">下载</el-button>
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
                tableData: [
                    {
                        name: "我去",
                        time: "2019-05-01"
                    },
                    {
                        name: "我去2",
                        time: "2019-05-11"
                    },
                    {
                        name: "我去3",
                        time: "2019-05-12"
                    },
                    {
                        name: "我去4",
                        time: "2019-05-22"
                    }
                ]
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

