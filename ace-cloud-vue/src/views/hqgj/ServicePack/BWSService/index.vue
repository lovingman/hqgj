<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="14">
                        <el-date-picker
                                align="right"
                                value-format="yyyy-MM-dd"
                                end-placeholder="结束时间"
                                start-placeholder="开始时间"
                                style="float: right"
                                v-model="timeArr"
                                type="daterange">
                        </el-date-picker>
                    </el-col>
                    <el-col :span="4">
                        <el-select placeholder="状态" style="float: right;margin-right: 20px" clearable v-model="statusObj" @change="handleStatus">
                            <el-option
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                                    v-for="item in options">
                            </el-option>
                        </el-select>
                    </el-col>
                    <!--<el-col :span="4">-->
                        <!--<el-input-->
                                <!--class="input-with-select"-->
                                <!--placeholder="请输入公司名称、申请人姓名"-->
                                <!--style="float: right;width: 250px"-->
                                <!--v-model="query.applyPersonName"-->
                        <!--&gt;</el-input>-->
                    <!--</el-col>-->
                    <el-col :span="6">
                        <el-input class="input-with-select" clearable placeholder="请输入公司名称、申请人姓名" v-model="query.applyPersonName">
                            <el-button :loading="loading" @click="search" icon="el-icon-search" slot="append"></el-button>
                        </el-input>
                        <!--<el-button @click="search" icon="el-icon-search" style="float: right" type="primary">搜索-->
                        <!--</el-button>-->
                    </el-col>
                </el-row>
            </div>
            <el-table
                    :data="list"
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                <el-table-column label="流水编号" prop="lsNo" width="180">
                </el-table-column>
                <el-table-column label="注册企业名称" prop="companyName">
                </el-table-column>
                <el-table-column label="申请人" prop="applyPersonName" width="150">
                </el-table-column>
                <el-table-column label="联系电话" prop="applyPersonTel" width="180">
                </el-table-column>
                <el-table-column label="申请日期" prop="createDate" width="180">
                </el-table-column>
                <el-table-column label="状态" prop="status" width="100">
                    <template slot-scope="scope">
                        <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                        <div class="blue" type="text" v-if="scope.row.status=='1'">办理中</div>
                        <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                        <div class="green" type="text" v-if="scope.row.status=='3'">注册成功</div>
                    </template>
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button @click="examine(scope.$index,scope.row)"
                                   height="40" type="text" v-if="scope.row.status=='0' || scope.row.status=='2'">审核
                        </el-button>
                        <el-button @click="progress(scope.$index,scope.row)" height="40" type="text"
                                   v-if="scope.row.status=='1'">进度标记
                        </el-button>
                        <el-button v-if="scope.row.status=='3'" @click="progress(scope.$index,scope.row)" height="40" type="text">进度记录</el-button>
                        <span class="strightline">|</span>
                        <el-button @click="dele(scope.$index,scope.row)" type="text">删除</el-button>
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
        <el-dialog
                :visible.sync="progressVisible"
                title="办理进度标记"
                width="60%">
            <div class="dialog-main">
                <light-timeline :items='items'>
                    <template slot='tag' slot-scope='{ item }'>
                        <!--@click.native.prevent="item.checked == '1' ? item.checked = '' : item.checked = '1'"-->
                        <div v-if="item.code!= '0'">
                            <el-radio v-model="item.checked" @click.native.prevent="clickitem(1,item.code)"
                                      label="1">
                                <span v-if="item.checked =='1'">已标记</span>
                                <span v-else="item.checked">标记</span>
                            </el-radio>
                        </div>
                        <!--<el-checkbox @change="sign(item.code)" false-label="0" true-label="1" v-model="item.checked">-->
                        <!--<span v-if="item.checked">已标记</span>-->
                        <!--<span v-else="item.checked">标记</span>-->
                        <!--</el-checkbox>-->
                    </template>
                    <template slot='content' slot-scope='{ item }'>
                        <div>
                            0{{item.code}}-{{item.name}}
                        </div>
                    </template>
                </light-timeline>
            </div>
            <span class="dialog-footer" slot="footer">
    <el-button @click="progressVisible = false">取 消</el-button>
    <el-button @click="sign" type="primary">确 定</el-button>
  </span>
        </el-dialog>
    </div>
</template>

<script>
    import {getList, deleteById, getById,update,updateBasicStatus} from "@/api/hqgj/BWSService";
    import {getAreaTree, getDict} from "@/api/sys";

    export default {
        name: "index",
        data() {
            return {
                loading:false,
                checked: true,
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                progressVisible: false,
                items: [],
                options:[
                    {
                        value: '4',
                        label: '全部'
                    }, {
                        value: '0',
                        label: '待审核'
                    }, {
                        value: '1',
                        label: '办理中'
                    }, {
                        value: '2',
                        label: '驳回修改'
                    }, {
                        value: '3',
                        label: '注册成功'
                    }
                ],
                byid: {},
                list: [],
                statusObj:"",
                query: {
                    // endtime:"",
                    // starttime:"",
                    status:"",
                    applyPersonName:""
                },
                value: '',
                timeArr: []
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
                console.log(this.timeArr);
                if (this.timeArr) {
                    this.query.startTime = this.timeArr.length > 0 ? this.timeArr[0] : ""; //开始时间
                    this.query.endTime = this.timeArr.length > 0 ? this.timeArr[1] : ""; //结束时间
                } else {
                    this.query.startTime = "";
                    this.query.endTime = "";
                }
                getList(this.query).then(response => {
                    this.total = response.total;
                    this.list = response.rows;
                    this.loading=false;
                })
            },
            //搜索
            search(){
                this.handleQuery();
            },
            dele(index, data){
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
            //企业注册状态搜索框数据
            handleStatus(){
                if (this.statusObj == 4){
                    this.query.status ="";
                }else {
                    this.query.status =this.statusObj;
                }
            },
            //获取企业注册步骤数据
            dictQuery() {
                getDict("54")
                    .then(response => {
                        // response.data['54'];
                        this.items = this.handling(response.data['54']);
                    })
            },
            //进度标记
            handling(data) {
                for (var j = 0; j < data.length; j++) {
                    for (var i = 0; i <= this.byid.tab; i++) {
                        data[i].checked = "1";
                    }
                    if (data[j].checked == "1") {
                        data[j].color = '#1890FF';
                    } else {
                        data[j].color = '#949fa8';
                    }
                }

                return data;
            },
            //根据id获取数据
            getdata(data) {
                this.id = data;
                getById(this.id).then(response => {
                    this.byid = response.data;
                    this.dictQuery();
                })
            },
            examine(index, data) {
                this.$router.push({path: "/hqgj/ServicePack/BWSService/Examine", query: {id: data.id,word:'examine'}});
            },
            preview(index, data) {
                this.$router.push({path: "/hqgj/ServicePack/BWSService/detail", query: {id: data.id,word:'preview'}});
            },
            progress(index, data) {
                this.getdata(data.id);
                this.progressVisible = true;
            },
            sign() {
                if(this.byid.tab == '7'){
                    this.status = 3;
                }else {
                    this.status = 1;
                }
                updateBasicStatus(this.byid.id,this.byid.tab,3).then(response => {
                    if (response.status == 1){
                        this.$message.success("标记成功");
                        this.getlist();
                    }
                })

                this.progressVisible = false;

            },
            clickitem(e,x) {
                console.log(e,x);
                e === this.items[x].checked ? this.items[x].checked = '' : this.items[x].checked = e;
                console.log(e,x);
                if(e == '1'){
                    this.byid.tab = x;
                    this.dictQuery();
                }
            },
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
    .red {
        color: red;
    }

    .green {
        color: green;
    }

    .blue {
        color: #1890FF;
    }

    .orange {
        color: #FF9900;
    }
</style>
