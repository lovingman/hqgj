<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="2">
                        <el-button @click="create" icon="el-icon-plus" style="float: left" type="primary">创建</el-button>
                    </el-col>
                    <el-col :span="10">
                        <el-dropdown style="line-height: 35px;" trigger="click">
                            <span style="color: black" >批量操作<i class="el-icon-arrow-down el-icon--right"></i></span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item command="importXls">批量导入</el-dropdown-item>
                                <el-dropdown-item command="exportXls">批量导出</el-dropdown-item>
                                <el-dropdown-item command="deleteIds">批量删除</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </el-col>
                    <el-col :span="4">
                        <el-select placeholder="全部类型">
                            <el-option>
                            </el-option>
                        </el-select>
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
                    border
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" type="selection" width="55"></el-table-column>
                <el-table-column label="机构名称" prop="name" sortable='custom' >
                </el-table-column>
                <el-table-column width="250" label="机构类型" prop="type">
                </el-table-column>
                <el-table-column label="联系方式" prop="mobile" width="300">
                </el-table-column>
                <el-table-column label="地址" prop="address" width="350">
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="200">
                    <template slot-scope="scope">
                        <el-button  height="40" type="text" @click="person">成员管理</el-button>
                        <span class="strightline">|</span>
                        <el-button  height="40" type="text" @click="edit">编辑</el-button>
                        <span class="strightline">|</span>
                        <el-button  type="text">删除</el-button>
                        <span class="strightline">|</span>
                        <el-button type="text" @click="preview">详情</el-button>
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
                        type:"会计事务所",
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
                this.$router.push({ path: "/hqgj/BasicData/service/Member" });
            },
            create(){
                this.$router.push({ path: "/hqgj/BasicData/service/create" });
            },
            edit(){
                this.$router.push({ path: "/hqgj/BasicData/service/edit" });
            },
            preview(){
                this.$router.push({ path: "/hqgj/BasicData/service/details" });
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
