<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="2">
                        <el-button @click="create" icon="el-icon-plus" style="float: left" type="primary">创建</el-button>
                    </el-col>
                    <el-col :span="14">
                        <el-dropdown style="line-height: 35px;" trigger="click">
                            <el-button>
                                批量操作<i class="el-icon-arrow-down el-icon--right"></i>
                            </el-button>
                            <!--<span style="color: black" >批量操作<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></span>-->
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="importXls">批量导入</el-dropdown-item>
                                <el-dropdown-item command="exportXls">批量导出</el-dropdown-item>
                                <el-dropdown-item command="deleteIds">批量删除</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </el-col>
                    <el-col :span="6">
                        <el-input
                                @change="toggleChange"
                                class="input-with-select"
                                placeholder="请输入企业名称或统一社会信用代码"
                                style="float: right"
                        ></el-input>
                    </el-col>
                    <el-col :span="2">
                        <el-button @click="search" icon="el-icon-search" style="float: right" type="primary">搜索
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
                <el-table-column label="企业名称" prop="name" sortable='custom' >
                </el-table-column>
                <el-table-column width="250" label="企业法人" prop="personName">
                </el-table-column>
                <el-table-column label="联系方式" prop="mobile" width="300">
                </el-table-column>
                <el-table-column label="地址" prop="address" width="350">
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button @click="" height="40" type="text" @click="person">员工管理</el-button>
                        <span class="strightline">|</span>
                        <el-button @click="" height="40" type="text" @click="edit">编辑</el-button>
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
                        address: "武陵区互联网产业园A02-3"
                    }
                ]
            };
        },
        created(){

        },
        methods:{
            person(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise/Member" });
            },
            create(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise/create" });
            },
            edit(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise/edit" });
            },
            preview(){
                this.$router.push({ path: "/hqgj/BasicData/enterprise/details" });
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
