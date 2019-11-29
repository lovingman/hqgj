<template>
    <div class="container">
        <div class="table">
            <div class="handle-box">
                <el-row>
                    <el-col :span="2">
                        <el-button @click="createPerson" icon="el-icon-plus" style="float: left" type="primary">添加</el-button>
                    </el-col>
                    <el-col :span="14">
                        <el-dropdown style="line-height: 35px;" trigger="click">
                            <span style="color: black" >批量操作<i class="el-icon-arrow-down el-icon--right"></i></span>
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
                                placeholder="请输入姓名或手机号码"
                                style="float: right"
                        ></el-input>
                    </el-col>
                    <el-col :span="2">
                        <el-button @click="search" icon="el-icon-search" style="float: right" type="primary">搜索</el-button>
                    </el-col>
                </el-row>

            </div>
            <el-table
                    :data="rows"
                    @selection-change="handleSelectionChange"
                    @sort-change="handleSort"
                    border
                    class="table"
                    max-height="475"
                    ref="multipleTable"
                    v-loading="loading">
                <el-table-column align="center" type="selection" width="55"></el-table-column>
                <el-table-column label="姓名" prop="name" sortable='custom' width="300">
                </el-table-column>
                <el-table-column label="手机号码" prop="mobile">
                </el-table-column>
                <el-table-column label="添加时间" prop="createData" width="500">
                </el-table-column>
                <el-table-column align="right" fixed="right" header-align="center" label="操作" width="150">
                    <template slot-scope="scope">
                        <el-button @click="" height="40" type="text" @click="editPerson">编辑</el-button>
                        <span class="strightline">|</span>
                        <el-button @click="" type="text">删除</el-button>
                        <span class="strightline">|</span>
                        <el-button @click="previewPerson" type="text">详情</el-button>
                    </template>
                </el-table-column>
            </el-table>

        </div>
    </div>
</template>

<script>
    export default {
        name: "Member",
        data() {
            return {
                rows:[
                    {
                        name:"王钦",
                        mobile:"17688876666",
                        createData: "2019-11-21 09:31:08"
                    }
                ]
            };
        },
        methods:{
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
</style>
