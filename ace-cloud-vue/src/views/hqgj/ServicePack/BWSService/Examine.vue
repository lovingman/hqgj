<template>
    <div class="container">
        <el-tabs @tab-click="handleClick" v-model="activeName">
            <el-tab-pane label="资料清单" name="1">
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
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="名称" prop="name" sortable='custom'>
                    </el-table-column>
                    <el-table-column label="提交时间" prop="creatdata" width="300">
                    </el-table-column>
                    <el-table-column label="附件数" prop="number" width="250">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button @click="" type="text">下载</el-button>
                            <span class="strightline">|</span>
                            <el-button type="text">
                                <router-link to="/application" target="_blank">预览</router-link>
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-dropdown trigger="click" style="margin-top: 20px">
                    <el-button>
                        压缩包下载<i class="el-icon-arrow-down el-icon--right"></i>
                    </el-button>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>rar下载</el-dropdown-item>
                        <el-dropdown-item>zip下载</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-tab-pane>
            <el-tab-pane label="申请人信息" name="2">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">申请人信息</span>
                </div>
                <el-divider></el-divider>
                <el-form :rules="rules" class="demo-ruleForm" label-width="150px" ref="ruleForm">
                    <el-row class="elrow">
                        <el-col :span="8">
                            <el-form-item label="姓名:" prop="name">
                                <span>付小小</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="联系电话:" prop="name">
                                <span>18100000000</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="身份证号:" prop="name">
                                <span>431224199600556596</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row class="elrow">
                        <el-form-item label="申请日期:" prop="name">
                            <span>2019-11-12 10:22:20</span>
                        </el-form-item>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="基本信息" name="3">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">基本信息</span>
                    <el-button @click="" style="float: right;margin-right: 25px;padding-top: 1px" type="text">审核
                    </el-button>
                </div>
                <el-divider></el-divider>
                <el-form :rules="rules" class="demo-ruleForm" label-width="150px" ref="ruleForm">
                    <el-row class="elrow">
                        <el-col :span="12">
                            <el-form-item label="企业名称:" prop="name">
                                <span>湖南华彩伟业网络科技有限公司</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="备用名:" prop="name">
                                <span>湖南常德华彩伟业网络科技有限公司</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row class="elrow">
                        <el-col :span="12">
                            <el-form-item label="注册资金:" prop="name">
                                <span>1000万人民币</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="企业地址:" prop="name">
                                <span>常德市武陵区移动互联网创业园A02栋第三层303号</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row class="elrow">
                        <el-form-item label="经营范围:" prop="name">
                                <span>基础软件开发，支撑软件开发，应用软件开发，信息系统集成服务，信息技术咨询服务，信息处理和存储支持服务；架线工程服务，智能化安装工程服务；计算机、软件及辅助设备、电子产品（不含电子出版物）、灯具、五金产品、首饰、工艺美术品（不含文物、象牙及其制品）的销售。（以公司登记机关核准为准）（依法须经批准的项目，经相关部门批准后方可开展经营活动）
基础软件开发，支撑软件开发，应用软件开发，信息系统集成服务，信息技术咨询服务，信息处理和存储支持服务；架线工程服务，智能化安装工程服务；计算机、软件及辅助设备、电子产品（不含电子出版物）、灯具、五金产品、首饰、工艺美术品（不含文物、象牙及其制品）的销售。（以公司登记机关核准为准）（依法须经批准的项目，经相关部门批准后方可开展经营活动）</span>
                        </el-form-item>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="法人、监事" name="4">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">法人、监事</span>
                </div>
                <el-divider></el-divider>
                <el-table
                        :data="list2"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="姓名" prop="name" sortable='custom' width="120">
                    </el-table-column>
                    <el-table-column label="职位" prop="position" width="150">
                    </el-table-column>
                    <el-table-column label="联系电话" prop="number" width="150">
                    </el-table-column>
                    <el-table-column label="身份证附件" prop="file">
                        <!--<template slot-scope="scope">-->
                        <!--<viewer  :images="scope.list2.file">-->
                        <!--<img v-for="attach in scope.list2.file" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>-->
                        <!--</viewer>-->
                        <!--</template>-->
                    </el-table-column>
                    <el-table-column label="状态" prop="status" width="80">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                        <template slot-scope="scope">
                            <el-button @click="preview" type="text">审核</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="股东" name="5">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">股东</span>
                </div>
                <el-divider></el-divider>
                <el-table
                        :data="list2"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="股东姓名" prop="name" sortable='custom' width="120">
                    </el-table-column>
                    <el-table-column label="股份比例" prop="position" width="150">
                    </el-table-column>
                    <el-table-column label="身份证附件" prop="file">
                        <!--<template slot-scope="scope">-->
                        <!--<viewer  :images="scope.list2.file">-->
                        <!--<img v-for="attach in scope.list2.file" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>-->
                        <!--</viewer>-->
                        <!--</template>-->
                    </el-table-column>
                    <el-table-column label="状态" prop="status" width="80">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                        <template slot-scope="scope">
                            <el-button @click="preview" type="text">审核</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="margin-top: 20px">
                    <span style="font-weight:bold">特殊说明:</span>
                    <div>
                        <span style="font-size:small">无</span>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="财务" name="6">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">财务</span>
                </div>
                <el-divider></el-divider>
                <el-table
                        :data="list2"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="姓名" prop="name" sortable='custom' width="120">
                    </el-table-column>
                    <el-table-column label="联系电话" prop="position" width="150">
                    </el-table-column>
                    <el-table-column label="身份证附件" prop="file">
                        <!--<template slot-scope="scope">-->
                        <!--<viewer  :images="scope.list2.file">-->
                        <!--<img v-for="attach in scope.list2.file" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>-->
                        <!--</viewer>-->
                        <!--</template>-->
                    </el-table-column>
                    <el-table-column label="状态" prop="status" width="80">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                        <template slot-scope="scope">
                            <el-button @click="preview" type="text">审核</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="房屋证明" name="7">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">房屋证明</span>
                </div>
                <el-divider></el-divider>
                <el-table
                        :data="list2"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="名称" prop="name" sortable='custom' width="250">
                    </el-table-column>
                    <el-table-column label="附件" prop="file">
                        <!--<template slot-scope="scope">-->
                        <!--<viewer  :images="scope.list2.file">-->
                        <!--<img v-for="attach in scope.list2.file" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>-->
                        <!--</viewer>-->
                        <!--</template>-->
                    </el-table-column>
                    <el-table-column label="状态" prop="status" width="80">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                        <template slot-scope="scope">
                            <el-button @click="preview" type="text">审核</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="其他" name="8">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">其他</span>
                </div>
                <el-divider></el-divider>
                <el-form label-width="250px" ref="form">
                    <el-form-item label="申请免费服务项目:">
                        <span>工商注册代办服务（含公章一套）、银行基本户开户代办服务 、银行开户费、小额账户管理费</span>
                    </el-form-item>
                    <el-form-item label="确定培训意向:">
                        <span>是否愿意参加创业培训、是否愿意加入创业孵化基地</span>
                    </el-form-item>
                    <el-form-item label="开户银行:">
                        <span>兴业银行各分行</span>
                    </el-form-item>
                    <el-form-item label="是否选择代理记账服务:">
                        <span>选择武陵区中小企业公共服务平台推荐的代理记账机构，合作条款及收费详见合同；</span>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
        </el-tabs>
    </div>

</template>

<script>
    export default {
        name: "Examine",
        data() {
            return {
                activeName: '1',
                value: '',
                value2: '',
                list: [
                    {
                        name: "武陵区中小型企业公共服务平台“创业服务一条龙”免费申请服务表",
                        creatdata: "2017-12-2",
                        number: "1",
                    }
                ],
                list2: [
                    {
                        name: "王琦",
                        position: "法人代表",
                        number: "12345678901",
                        status: "1",
                    }
                ],
            };
        },
        created() {

        },
        methods: {
            preview() {
                this.$router.push('/application');
            }
        }

    }
</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }

    .elrow {
        margin-bottom: 0;
    }
</style>
