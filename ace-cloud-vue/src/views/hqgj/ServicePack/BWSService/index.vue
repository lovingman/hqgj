<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="14">
                        <el-date-picker
                                style="float: right"
                                v-model="value2"
                                type="datetimerange"
                                align="right"
                                start-placeholder="开始时间"
                                end-placeholder="结束时间"
                                :default-time="['12:00:00', '08:00:00']">
                        </el-date-picker>
                    </el-col>
                    <el-col :span="4">
                        <el-select v-model="value" placeholder="状态" style="float: right;margin-right: 20px">
                            <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="4">
                        <el-input
                                @change="toggleChange"
                                class="input-with-select"
                                placeholder="请输入公司名称、申请人姓名"
                                style="float: right;width: 250px"
                        ></el-input>
                    </el-col>
                    <el-col :span="2">
                        <el-button @click="" icon="el-icon-search" style="float: right" type="primary">搜索
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
                <el-table-column label="注册企业名称" prop="name" sortable='custom' >
                </el-table-column>
                <el-table-column width="200" label="申请人" prop="personName">
                </el-table-column>
                <el-table-column label="联系电话" prop="mobile" width="250">
                </el-table-column>
                <el-table-column label="申请日期" prop="address" width="300">
                </el-table-column>
                <el-table-column label="状态" prop="status" width="100">
                    <!--<template slot-scope="scope">-->
                        <!--<div type="text" class="green" v-if="scope.list.status==='1'">待审核</div>-->
                        <!--<div type="text" class="red" v-if="scope.list.status==='2'">办理中</div>-->
                        <!--<div type="text" class="orange" v-if="scope.list.status==='3'">注册成功</div>-->
                        <!--<div type="text" class="orange" v-if="scope.list.status==='4'">驳回修改</div>-->
                    <!--</template>-->
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button @click="" height="40" type="text" @click="edit">审核</el-button>
                        <!--<el-button v-if="scope.list.status=='2'" @click="" height="40" type="text" @click="edit">进度标记</el-button>-->
                        <!--<el-button v-if="scope.list.status=='3'" @click="" height="40" type="text" @click="edit">进度记录</el-button>-->
                        <span class="strightline">|</span>
                        <el-button @click="" type="text">删除</el-button>
                        <span class="strightline">|</span>
                        <el-button @click="preview" type="text">详情</el-button>
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
    export default {
        name: "index",
        data() {
            return {
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                list:[
                    {
                        name:"湖南华彩伟业网络科技有限公司",
                        personName:"余跃辉",
                        mobile:"陈琳-0736-7123101",
                        address: "武陵区互联网产业园A02-3",
                        status:1
                    }
                ],
                value: '',
                value2: ''
            };
        },
        created(){

        },
        methods:{
            edit(){
                this.$router.push({ path: "/hqgj/ServicePack/BWSService/Examine" });
            },
            preview(){
                this.$router.push({ path: "/hqgj/ServicePack/BWSService/Examine" });
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
