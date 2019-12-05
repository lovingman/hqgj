<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="14">
                        <el-date-picker
                                :default-time="['12:00:00', '08:00:00']"
                                align="right"
                                end-placeholder="结束时间"
                                start-placeholder="开始时间"
                                style="float: right"
                                type="datetimerange"
                                v-model="value2">
                        </el-date-picker>
                    </el-col>
                    <el-col :span="4">
                        <el-select placeholder="状态" style="float: right;margin-right: 20px" v-model="value">
                            <el-option
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                                    v-for="item in options">
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
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" type="selection" width="55"></el-table-column>
                <el-table-column label="注册企业名称" prop="name">
                </el-table-column>
                <el-table-column label="申请人" prop="personName" sortable='custom' width="200">
                </el-table-column>
                <el-table-column label="联系电话" prop="mobile" width="250">
                </el-table-column>
                <el-table-column label="申请日期" prop="address" sortable='custom' width="300">
                </el-table-column>
                <el-table-column label="状态" prop="status" sortable='custom' width="100">
                    <!--<template slot-scope="scope">-->
                    <!--<div type="text" class="green" v-if="scope.list.status==='1'">待审核</div>-->
                    <!--<div type="text" class="red" v-if="scope.list.status==='2'">办理中</div>-->
                    <!--<div type="text" class="orange" v-if="scope.list.status==='3'">注册成功</div>-->
                    <!--<div type="text" class="orange" v-if="scope.list.status==='4'">驳回修改</div>-->
                    <!--</template>-->
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button @click="edit" height="40" type="text">审核</el-button>
                        <el-button @click="progress" height="40" type="text">进度标记</el-button>
                        <!--<el-button v-if="scope.list.status=='3'" @click="" height="40" type="text" @click="edit">进度记录</el-button>-->
                        <span class="strightline">|</span>
                        <el-button @click="" type="text">删除</el-button>
                        <span class="strightline">|</span>
                        <el-button @click="preview" type="text">详情</el-button>
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
                :before-close="handleClose"
                :visible.sync="progressVisible"
                title="办理进度标记"
                width="60%">
            <light-timeline :items='items'>
                <template slot='tag' slot-scope='{ item }'>
                    <!--<el-radio v-model="item.checked" label="1" @click.native.prevent="item.checked == '1' ? item.checked = '' : item.checked = '1'">-->
                        <!--<span v-if="item.checked">已标记</span>-->
                        <!--<span v-else="item.checked">标记</span>-->
                    <!--</el-radio>-->
                    <el-checkbox true-label="1" v-model="item.checked">
                        <span v-if="item.checked">已标记</span>
                        <span v-else="item.checked">标记</span>
                    </el-checkbox>
                </template>
                <template slot='content' slot-scope='{ item }'>
                    <div>
                        {{item.content}}
                    </div>
                    <div>
                        {{item.tag}}
                    </div>
                </template>
            </light-timeline>
            <span class="dialog-footer" slot="footer">
    <el-button @click="progressVisible = false">取 消</el-button>
    <el-button @click="progressVisible = false" type="primary">确 定</el-button>
  </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "index",
        data() {
            return {
                checked:true,
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                progressVisible: false,
                items: [
                    {
                        tag: '2018-01-12',
                        content: '00-资料审核已通过',
                        checked:"1",
                        color: '#949fa8',
                    },
                    {
                        tag: '2018-01-13',
                        content: '01-人工到工商局窗口审核名称。确定名称100%可用',
                        checked:"",
                        color: '#949fa8',
                    },
                    {
                        tag: '2018-01-14',
                        content: '02-收集所有新办企业需要提供的资料',
                        class: 'fas fa-star',
                        checked:"",
                        color: '#949fa8',
                    }
                ],
                list: [
                    {
                        name: "湖南常德市凤天有限公司",
                        personName: "王琦",
                        mobile: "17688876666",
                        address: "2019-11-21 09:31:08",
                        status: "1"
                    },
                    {
                        name: "湖南常德市凤天有限公司",
                        personName: "王琦",
                        mobile: "17688876666",
                        address: "2019-11-21 09:31:08",
                        status: "2"
                    }
                ],
                value: '',
                value2: ''
            };
        },
        created() {

        },
        methods: {
            edit() {
                this.$router.push({path: "/hqgj/ServicePack/BWSService/Examine"});
            },
            preview() {
                this.$router.push({path: "/hqgj/ServicePack/BWSService/Examine"});
            },
            progress() {
                this.progressVisible = true;
            },
        }
    }
</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }
</style>
