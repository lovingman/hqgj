<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="4">
                        <el-button
                                @click="exportXls"
                                style="float: left;border-radius:5px;"
                                type="primary"
                        >数据导出
                        </el-button>
                    </el-col>
                    <el-col :span="8">
                        <el-date-picker v-model="Time"
                                        type="daterange"
                                        align="right"
                                        style="float: right;width: 100%;border-right: 0"
                                        unlink-panels
                                        format="yyyy-MM-dd"
                                        value-format="yyyy-MM-dd"
                                        range-separator="至"
                                        start-placeholder="开始日期"
                                        end-placeholder="结束日期"
                                        :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-col>
                    <el-col :span="3">
                        <el-select
                                v-model="query.type"
                                clearable
                                placeholder="全部类型">
                            <el-option
                                    :key="item.code"
                                    :label="item.name"
                                    :value="item.code"
                                    v-for="item in dict"
                            ></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="3">
                        <el-select
                                v-model="query.status"
                                clearable
                                placeholder="全部状态">
                            <el-option
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                                    v-for="item in options"
                            ></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="6" class="selectSearch">
                        <el-input
                                class="input-with-select"
                                clearable
                                placeholder="请输入企业名称"
                                v-model="query.companyName"
                        >
                            <el-button :loading="loading" @click="search" icon="el-icon-search"
                                       slot="append"></el-button>
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
                    v-loading="loading"
            >
                <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                <el-table-column label="类型" prop="type" width="100">
                    <template slot-scope="scope">
                        <div type="text" v-if="scope.row.type=='1'">防疫物资</div>
                        <div type="text" v-if="scope.row.type=='2'">法律服务</div>
                        <div type="text" v-if="scope.row.type=='3'">融资需求</div>
                        <div type="text" v-if="scope.row.type=='4'">其它</div>
                    </template>
                </el-table-column>
                <el-table-column label="企业名称" :show-overflow-tooltip="true" prop="companyName"
                                 ></el-table-column>
                <el-table-column label="联系人" prop="personName" width="100"></el-table-column>
                <el-table-column label="联系方式" prop="personTel" width="150"></el-table-column>
                <el-table-column label="创建时间" prop="createDate" width="200"></el-table-column>
                <el-table-column label="状态" prop="status" width="80">
                    <template slot-scope="scope">
                        <div style="color: orangered" type="text" v-if="scope.row.status=='1'">待受理</div>
                        <div style="color: green" type="text" v-if="scope.row.status=='2'">已受理</div>
                        <div style="color: #007cff" type="text" v-if="scope.row.status=='3'">已解决</div>
                    </template>
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button v-if="scope.row.status=='1'" @click="saveExamine(scope.$index,scope.row,'Acc')"
                                   height="40" type="text">受理
                        </el-button>
                        <el-button v-if="scope.row.status=='2'" @click="saveExamine(scope.$index,scope.row,'Solve')"
                                   height="40" type="text">解决
                        </el-button>
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
                    layout="total, sizes, prev, pager, next, jumper"
            ></el-pagination>
        </div>
    </div>
</template>

<script>
    import {
        getList,
        exportXls,
        updateProjectState
    } from "@/api/hqgj/Appeal";
    import {getDict} from "@/api/sys";

    export default {
        name: "index",
        data() {
            return {
                Time:'',
                loading: false,
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                multipleSelection: [], //选中行数据
                dict: [], //企业诉求类型
                options: [{
                    value: '1',
                    label: '待受理'
                }, {
                    value: '2',
                    label: '已受理'
                }, {
                    value: '3',
                    label: '已解决'
                }], //企业诉求类型
                list: [],
                examine:{
                    id:"",
                    status:""
                },
                query: {
                    type: "",
                    status: "",
                    companyName: "",
                    startTime:"",
                    endTime:""
                },
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },

            };
        },
        created() {
            this.getlist();
            this.dictQuery();
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
                this.loading = true;
                if(this.Time){
                    this.query.startTime = this.Time[0];
                    this.query.endTime = this.Time[1];
                }else{
                    this.query.startTime='';
                    this.query.endTime='';
                }
                this.query = Object.assign(this.query, {
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                getList(this.query).then(response => {
                    this.total = response.total;
                    this.list = response.rows;
                    this.loading = false;
                });
            },
            search() {
                this.handleQuery();
            },
            //服务机构类型搜索框数据
            handleStatus() {
                if (this.typeObj == 0) {
                    this.query.type = "";
                } else {
                    this.query.type = this.typeObj;
                }
            },

            exportXls() {
                exportXls().then(response => {
                    const blob = new Blob([response], {
                        type: "application/vnd.ms-excel"
                    });
                    const fileName = "企业诉求.xlsx";
                    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                        navigator.msSaveBlob(blob, fileName);
                    } else {
                        const a = document.createElement("a");
                        a.href = URL.createObjectURL(blob);
                        a.download = fileName;
                        a.style.display = "none";
                        document.body.appendChild(a);
                        a.click();
                        URL.revokeObjectURL(a.href);
                        document.body.removeChild(a);
                    }
                })
                    .catch(error => {
                        this.$message.error(error);
                    });
            },
            // 受理
            saveExamine(index, data, word) {
                if(word=='Acc'){
                    this.$confirm("是否确定受理?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    })
                        .then(() => {
                            this.examine.status ="2";
                            this.examine.id=data.id;
                            updateProjectState(this.examine)
                                .then(response => {
                                    if (response.status == 1) {
                                        this.$message.success(`受理成功`);
                                        this.getlist();
                                    } else {
                                        this.$message({
                                            message: response.message,
                                            type: "warning"
                                        });
                                    }
                                })
                        })
                        .catch(() => {});
                }
                if(word =='Solve'){
                    this.$confirm("是否确定解决?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }).then(() => {
                            this.examine.status ="3";
                            this.examine.id=data.id;
                            updateProjectState(this.examine)
                                .then(response => {
                                    if (response.status == 1) {
                                        this.$message.success(`解决成功`);
                                        this.getlist();
                                    } else {
                                        this.$message({
                                            message: response.message,
                                            type: "warning"
                                        });
                                    }
                                })
                        })
                        .catch(() => {});
                }

            },
            //获取选中行数据
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            //获取项目类型数据
            dictQuery() {
                getDict("62").then(response => {
                    this.dict = response.data["62"];
                });
            },
            preview(index, data) {
                this.$router.push({
                    path: "/hqgj/Appeal/details",
                    query: {id: data.id,type: data.type}
                });
            }
        }
    };
</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }

    .input-with-select {
        float: right;
    }
</style>
