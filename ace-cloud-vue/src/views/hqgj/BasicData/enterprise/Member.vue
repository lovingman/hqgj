<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <!--<el-col :span="2">-->
                        <!--<el-button @click="createPerson" icon="el-icon-plus" style="float: left" type="primary">添加</el-button>-->
                    <!--</el-col>-->
                    <!--<el-col :span="16">-->
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
                    <!--<el-col :span="22">-->
                        <!--<el-input-->
                                <!--v-model="query.name"-->
                                <!--clearable-->
                                <!--class="input-with-select"-->
                                <!--placeholder="请输入姓名或手机号码"-->
                                <!--style="float: right;width: 200px"-->
                        <!--&gt;</el-input>-->
                    <!--</el-col>-->
                    <el-col :span="24" class="selectSearch">
                        <el-input class="input-with-select" clearable placeholder="请输入姓名或手机号码" v-model="query.name">
                            <el-button :loading="loading" @click="search" icon="el-icon-search" slot="append"></el-button>
                        </el-input>
                        <!--<el-button @click="search" icon="el-icon-search" style="float: right" type="primary">搜索</el-button>-->
                    </el-col>
                </el-row>

            </div>
            <el-table
                    :data="rows"
                    @selection-change="handleSelectionChange"
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                <el-table-column label="姓名" prop="name" width="300">
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
    import {personPage,deletePerson,deletePersons,exportXlsPerson} from "@/api/hqgj/enterprise";
    export default {
        name: "Member",
        data() {
            return {
                loading:false,
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                multipleSelection: [],//选中行数据
                rows:[],
                query: {
                    companyId:"",
                    name: ""
                },
                exportDatas:{
                    companyId:"",
                    name: ""
                }
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
                this.loading=true;
                this.query.companyId = this.$route.query.id;
                this.query = Object.assign(this.query, {
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                personPage(this.query).then(response => {
                    this.total= response.total;
                    this.rows = response.rows;
                    this.loading=false;
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
                            this.getlist();
                        });
                    })
                    .catch(() => {

                    });
            },
            //批量操作
            handleCommand(command) {
                //导入
                if (command == 'importXls') {
                    console.log(456)
                }
                //导出
                if (command == 'exportXls') {
                    this.exportDatas.companyId = this.query.companyId;
                    this.exportDatas.name = this.query.name;
                    exportXlsPerson(this.exportDatas).then(response => {
                        const blob = new Blob([response], {type: 'application/vnd.ms-excel'});
                        const fileName = '企业成员信息.xlsx';
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

                    console.log(789)
                }
                //删除
                if (command == 'deleteIds') {
                    if (this.multipleSelection.length) {
                        this.ids = this.multipleSelection.map(item => item.id).join(",");
                        this.$confirm("此操作将永久删除选中数据, 是否继续?", "提示", {
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            type: "warning"
                        })
                            .then(() => {
                                deletePersons(this.ids).then(response => {
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
    /*.selectSearch {*/
        /*float: right;*/
    /*}*/
    .input-with-select{
        float: right;
        width: 350px;
    }
    .selectSearch/deep/ .el-button--medium {
        color: #fff;
        background-color: #007cff;
        border-color: #007cff;
        border-radius: 0;
    }
</style>
