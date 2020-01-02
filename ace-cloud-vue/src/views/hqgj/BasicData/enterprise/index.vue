<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="12">
                        <el-button @click="create" icon="el-icon-plus" style="float: left" type="primary">创建</el-button>
                    </el-col>
                    <!--<el-col :span="14">-->
                        <!--<el-dropdown @command="handleCommand" style="line-height: 35px;" trigger="click">-->
                            <!--<el-button>-->
                                <!--批量操作<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
                            <!--</el-button>-->
                            <!--&lt;!&ndash;<span style="color: black" >批量操作<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></span>&ndash;&gt;-->
                            <!--<el-dropdown-menu slot="dropdown">-->
                                <!--<el-dropdown-item command="importXls">批量导入</el-dropdown-item>-->
                                <!--<el-dropdown-item command="exportXls">批量导出</el-dropdown-item>-->
                                <!--<el-dropdown-item command="deleteIds">批量删除</el-dropdown-item>-->
                            <!--</el-dropdown-menu>-->
                        <!--</el-dropdown>-->
                    <!--</el-col>-->
                    <el-col :span="6">
                        <el-select style="float: right" v-model="query.source" clearable placeholder="来源">
                            <el-option
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                                    v-for="item in options"
                            ></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="6"  class="selectSearch">
                        <el-input class="input-with-select" clearable placeholder="请输入企业名称" v-model="query.companyName">
                            <el-button :loading="loading" @click="search" icon="el-icon-search" slot="append"></el-button>
                        </el-input>
                    </el-col>
                </el-row>

            </div>
            <el-table
                    :data="list"
                    @selection-change="handleSelectionChange"
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                <el-table-column label="企业名称" :show-overflow-tooltip="true" prop="companyName" width="300">
                </el-table-column>
                <el-table-column label="来源" prop="source" width="100">
                    <template slot-scope="scope">
                        <div type="text" v-if="scope.row.source=='1'">系统录入</div>
                        <div type="text" v-if="scope.row.source=='2'">小程序注册</div>
                    </template>
                </el-table-column>
                <el-table-column label="法人代表" prop="legalPerson" width="100">
                </el-table-column>
                <!--<el-table-column label="联系方式" prop="contactPersonTel" width="150">-->
                <!--</el-table-column>-->
                <el-table-column label="地址" :show-overflow-tooltip="true" prop="completeAddress">
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button @click="person(scope.$index,scope.row)" height="40" type="text">员工管理</el-button>
                        <span class="strightline">|</span>
                        <el-button @click="edit(scope.$index,scope.row)" height="40" type="text">编辑</el-button>
                        <span class="strightline">|</span>
                        <el-button @click="handleDelete(scope.$index,scope.row)" type="text">删除</el-button>
                        <span class="strightline">|</span>
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
                    layout="total, sizes, prev, pager, next, jumper">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import {getList, deleteById, deleteByIds, exportXls} from "@/api/hqgj/enterprise";

    export default {
        name: "index",
        data() {
            return {
                loading:false,
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                list: [],
                multipleSelection: [],//选中行数据
                //来源
                options: [{
                    value: '1',
                    label: '系统录入'
                }, {
                    value: '2',
                    label: '小程序注册'
                }],
                //搜索
                query: {
                    companyName: "",
                    source:""
                },
                exportDatas:{
                    companyName: ""
                }

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
            //获取列表数据
            getlist() {
                this.loading=true;
                this.query = Object.assign(this.query, {
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                getList(this.query).then(response => {
                    this.total = response.total;
                    this.list = response.rows;
                    this.loading=false;
                })
            },
            //搜索
            search() {
                this.handleQuery();
            },
            //批量操作
            handleCommand(command) {
                //批量导入
                if (command == 'importXls') {
                    console.log(456)
                }
                //批量导出
                if (command == 'exportXls') {
                    this.exportDatas.companyName = this.query.companyName;
                    exportXls(this.exportDatas).then(response => {
                        const blob = new Blob([response], {type: 'application/vnd.ms-excel'});
                        const fileName = '企业信息.xlsx';
                        if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                            navigator.msSaveBlob(blob, fileName)
                        } else {
                            const a = document.createElement('a');
                            a.href = URL.createObjectURL(blob);
                            a.download = fileName;
                            a.style.display = 'none';
                            document.body.appendChild(a);
                            a.click();
                            URL.revokeObjectURL(a.href);
                            document.body.removeChild(a)
                        }
                    }).catch((error) => {
                        this.$message.error(error)
                    })
                }
                //批量删除
                if (command == 'deleteIds') {
                    if (this.multipleSelection.length) {
                        this.ids = this.multipleSelection.map(item => item.id).join(",");
                        this.$confirm("此操作将永久删除选中数据, 是否继续?", "提示", {
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            type: "warning"
                        })
                            .then(() => {
                                deleteByIds(this.ids).then(response => {
                                    this.$message.success("删除成功");
                                    this.multipleSelection = [];
                                    this.getlist();
                                });
                            })
                            .catch(() => {
                            });
                    } else {
                        this.$message({
                            message: (`未选中数据`),
                            type: "warning"
                        });
                    }
                }
            },
            //获取选中行数据
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            person(index, data) {
                this.$router.push({path: "/hqgj/BasicData/enterprise/Member", query: {id: data.id}});
            },
            create() {
                this.$router.push({path: "/hqgj/BasicData/enterprise/create"});
            },
            edit(index, data) {
                this.$router.push({path: "/hqgj/BasicData/enterprise/edit", query: {id: data.id}});
            },
            preview(index, data) {
                this.$router.push({path: "/hqgj/BasicData/enterprise/details", query: {id: data.id}});
            },
            //删除
            handleDelete(index, data) {
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
            handleSelectionChange() {

            }
        }
    }
</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }
    .input-with-select{
        float: right;
        width: 350px;
    }
</style>
