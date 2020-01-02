<template>
    <div class="main-box">
        <div class="header">
            <el-row>
                <el-button @click="create" style="float:left;" type="primary">创建</el-button>
                <el-col :span="10" class="selectSearch">
                    <el-col :span="7" style="margin-right:20px;">
                        <el-select @change="handleStatus" clearable placeholder="请选择" v-model="statusObj">
                            <el-option
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                                    v-for="item in stautsArr"
                            ></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="16">
                        <el-input class="input-with-select" clearable placeholder="请输入名称" v-model="query.title">
                            <el-button :loading="loading" @click="search" icon="el-icon-search" slot="append"></el-button>
                        </el-input>
                    </el-col>
                </el-col>
            </el-row>
        </div>
        <div class="table-box">
            <el-table :data="tableData" v-loading="loading" style="width: 100%">
                <el-table-column align="center" label="序号" type="index" width="80"></el-table-column>
                <el-table-column label="政策名称" prop="title" sortable></el-table-column>
                <el-table-column label="创建时间" prop="createDate" sortable></el-table-column>
                <el-table-column label="状态" prop="status" sortable>
                    <template slot-scope="scope">
                        <div class="orange" type="text" v-if="scope.row.status=='1'">待发布</div>
                        <div class="green" type="text" v-if="scope.row.status=='2'">已发布</div>
                    </template>
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="240">
                    <template slot-scope="scope">
                        <el-button @click="release(scope.$index,scope.row)" type="text" v-if="scope.row.status=='1'">
                            发布
                        </el-button>
                        <!--<el-button v-if="scope.row.status=='2'" type="text">发布</el-button>-->
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
    import {getPolicy, deletePolicyById, updateStatus} from "@/api/hqgj/Policies";

    export default {
        name: "index",
        data() {
            return {
                loading:false,
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                //状态容器
                stautsArr: [
                    {
                        value: '0',
                        label: '全部'
                    },
                    {
                        value: '1',
                        label: '待发布'
                    },
                    {
                        value: '2',
                        label: '已发布'
                    }
                ],
                statusObj: "",
                query: {
                    title: "",
                    status: ""
                },
                tableData: []
            };
        },
        created() {
            this.getlist();
            // this.dictQuery()
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
                getPolicy(this.query).then(response => {
                    this.total = response.total;
                    this.tableData = response.rows;
                    this.loading=false;
                })
            },
            //搜索
            search() {
                this.handleQuery();
            },
            //政策服务状态搜索框数据
            handleStatus() {
                if (this.statusObj == 0) {
                    this.query.status = "";
                } else {
                    this.query.status = this.statusObj;
                }
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
                        deletePolicyById(this.id).then(response => {
                            this.$message.success("删除成功");
                            this.getlist();
                        });
                    })
                    .catch(() => {
                        this.$message.error('error')
                    });
            },
            //发布
            release(index, data) {
                this.$confirm("发布成功后，将发送发布成功通知，是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.id = data.id;
                    updateStatus(this.id, 2).then(response => {
                        if (response.status == 1) {
                            this.$message.success(`发布成功`);
                            this.getlist();
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            });
                        }
                    })
                }).catch(() => {
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
                this.$router.push({path: "/hqgj/Policies/OtrlPolicy/add"});
            },
            //编辑
            edit(index, data) {
                this.$router.push({path: "/hqgj/Policies/OtrlPolicy/edit", query: {id: data.id}});
            },
            //详情
            preview(index, data) {
                this.$router.push({path: "/hqgj/Policies/OtrlPolicy/details", query: {id: data.id}});
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

                /*/deep/ .el-button--medium {*/
                    /*color: #fff;*/
                    /*background-color: #007cff;*/
                    /*border-color: #007cff;*/
                    /*border-radius: 0;*/
                /*}*/
            }
        }

        .orange {
            color: #FF9900;
        }

        .green {
            color: green;
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

