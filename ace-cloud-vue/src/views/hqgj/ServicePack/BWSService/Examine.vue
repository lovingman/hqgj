<template>
    <div class="container">
        <div style="width: 100%;height: 20px">
            <el-button @click="Auditing" size="small" style="float: right" type="primary">完成审核</el-button>
        </div>
        <div class="tab-pane" style="width: 100%">
            <el-tabs @tab-click="handleClick" style="width: 100%" v-model="activeName">
                <el-tab-pane label="资料清单" name="1">
                    <el-table
                            :data="lists"
                            @selection-change="handleSelectionChange"
                            border
                            class="table"
                            max-height="475"
                            ref="multipleTable"
                            v-loading="loading">
                        <el-table-column align="center" type="selection" width="55"></el-table-column>
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="名称" prop="fileName" sortable='custom'>
                        </el-table-column>
                        <el-table-column label="提交时间" prop="createDate" width="300">
                        </el-table-column>
                        <el-table-column label="附件数" prop="annexNum" width="250">
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作" width="100">
                            <template slot-scope="scope">
                                <el-button @click="FileDownload(scope.$index,scope.row)" type="text">下载</el-button>
                                <span class="strightline">|</span>
                                <el-button @click="looking(scope.$index,scope.row)" type="text"
                                           v-if="scope.row.id != null">预览
                                </el-button>
                                <el-button type="text" v-else="scope.row.id != null">
                                    <router-link :to="{name:'application',query:{id:form.id}}" target="_blank">预览
                                    </router-link>
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-dropdown style="margin-top: 20px" trigger="click">
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
                    <el-form :model="form" class="demo-ruleForm" label-width="150px">
                        <el-row class="elrow">
                            <el-col :span="8">
                                <el-form-item label="姓名:" prop="name">
                                    <span>{{form.applyPersonName}}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="联系电话:" prop="name">
                                    <span>{{form.applyPersonTel}}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="身份证号:" prop="name">
                                    <span>{{form.idCard}}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row class="elrow">
                            <el-form-item label="申请日期:" prop="name">
                                <span>{{form.createDate}}</span>
                            </el-form-item>
                        </el-row>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="基本信息" name="3">
                    <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                        <span style="margin-left: 20px;font-weight:bold;">基本信息</span>
                        <el-button @click="reviewBasic" style="float: right;margin-right: 25px;padding-top: 1px"
                                   type="text"
                                   v-if="form.basicStatus!='1'">审核
                        </el-button>
                        <div class="green" style="float: right;margin-right: 25px;padding-top: 1px" type="text"
                             v-if="form.basicStatus=='1'">通过
                        </div>

                    </div>
                    <el-divider></el-divider>
                    <el-form :model="form" class="demo-ruleForm" label-width="150px">
                        <el-row class="elrow">
                            <el-col :span="12">
                                <el-form-item label="企业名称:" prop="name">
                                    <span>{{form.companyName}}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="备用名:" prop="name">
                                    <span>{{form.readyName}}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row class="elrow">
                            <el-col :span="12">
                                <el-form-item label="注册资金:" prop="name">
                                    <span>{{form.regBonus}}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="企业地址:" prop="name">
                                    <span>{{form.companyAddress}}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row class="elrow">
                            <el-form-item label="经营范围:" prop="name">
                                <span>{{form.manageExtent}}</span>
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
                            :data="list"
                            @selection-change="handleSelectionChange"
                            border
                            class="table"
                            max-height="475"
                            ref="multipleTable"
                            v-loading="loading">
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="姓名" prop="name" sortable='custom' width="120">
                        </el-table-column>
                        <el-table-column label="职位" prop="jobs" width="150">
                        </el-table-column>
                        <el-table-column label="联系电话" prop="mobile" width="150">
                        </el-table-column>
                        <el-table-column label="身份证附件" prop="basicAnnexes">
                            <template slot-scope="scope">
                                <viewer :images="scope.row.basicAnnexes">
                                    <img :key="attach.fileURL" :src="attach.fileURL"
                                         class="head_pic" v-for="attach in scope.row.basicAnnexes"/>
                                </viewer>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" prop="status" width="100">
                            <template slot-scope="scope">
                                <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                                <div class="green" type="text" v-if="scope.row.status=='1'">通过</div>
                                <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                            </template>
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                            <template slot-scope="scope">
                                <el-button @click="reviewDetail(scope.$index,scope.row)" type="text"
                                           v-if="scope.row.status!= '1'">审核
                                </el-button>
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
                            :data="list"
                            @selection-change="handleSelectionChange"
                            border
                            class="table"
                            max-height="475"
                            ref="multipleTable"
                            v-loading="loading">
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="股东姓名" prop="name" sortable='custom' width="120">
                        </el-table-column>
                        <el-table-column label="股份比例" prop="sharesProportion" width="150">
                        </el-table-column>
                        <el-table-column label="身份证附件" prop="basicAnnexes">
                            <template slot-scope="scope">
                                <viewer :images="scope.row.basicAnnexes">
                                    <img :key="attach.fileURL" :src="attach.fileURL"
                                         class="head_pic" v-for="attach in scope.row.basicAnnexes"/>
                                </viewer>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" prop="status" width="100">
                            <template slot-scope="scope">
                                <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                                <div class="green" type="text" v-if="scope.row.status=='1'">通过</div>
                                <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                            </template>
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                            <template slot-scope="scope">
                                <el-button @click="reviewDetail(scope.$index,scope.row)" type="text"
                                           v-if="scope.row.status!= '1'">审核
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane label="财务" name="6">
                    <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                        <span style="margin-left: 20px;font-weight:bold;">财务</span>
                    </div>
                    <el-divider></el-divider>
                    <el-table
                            :data="list"
                            @selection-change="handleSelectionChange"
                            border
                            class="table"
                            max-height="475"
                            ref="multipleTable"
                            v-loading="loading">
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="姓名" prop="name" sortable='custom' width="120">
                        </el-table-column>
                        <el-table-column label="联系电话" prop="mobile" width="150">
                        </el-table-column>
                        <el-table-column label="身份证附件" prop="basicAnnexes">
                            <template slot-scope="scope">
                                <viewer :images="scope.row.basicAnnexes">
                                    <img :key="attach.fileURL" :src="attach.fileURL" class="head_pic"
                                         style="line-height: 48px" v-for="attach in scope.row.basicAnnexes"/>
                                </viewer>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" prop="status" width="100">
                            <template slot-scope="scope">
                                <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                                <div class="green" type="text" v-if="scope.row.status=='1'">通过</div>
                                <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                            </template>
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                            <template slot-scope="scope">
                                <el-button @click="reviewDetail(scope.$index,scope.row)" type="text"
                                           v-if="scope.row.status!= '1'">审核
                                </el-button>
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
                            :data="list"
                            @selection-change="handleSelectionChange"
                            border
                            class="table"
                            max-height="475"
                            ref="multipleTable"
                    >
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="名称" prop="name" sortable='custom' width="250">
                        </el-table-column>
                        <el-table-column label="附件" prop="file">
                            <template slot-scope="scope">
                                <viewer :images="scope.row.basicAnnexes">
                                    <img :key="attach.fileURL" :src="attach.fileURL"
                                         class="head_pic" v-for="attach in scope.row.basicAnnexes"/>
                                </viewer>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" prop="status" width="100">
                            <template slot-scope="scope">
                                <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                                <div class="green" type="text" v-if="scope.row.status=='1'">通过</div>
                                <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                            </template>
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                            <template slot-scope="scope">
                                <el-button @click="reviewDetail(scope.$index,scope.row)" type="text"
                                           v-if="scope.row.status!= '1'">审核
                                </el-button>
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
                        <el-form-item label="特殊说明:">
                            <span>无</span>
                        </el-form-item>
                    </el-form>
                </el-tab-pane>
            </el-tabs>
        </div>
        <!--预览弹窗-->
        <el-dialog
                :visible.sync="lookingVisible"
                title="预览"
                width="60%">
            <div class="dialog-main">
                <viewer :images="fileList">
                    <img :key="attach.fileURL" :src="attach.fileURL"
                         class="head_pics" v-for="attach in fileList"/>
                </viewer>
            </div>
            <div class="dialog-footer" slot="footer">
                <el-button @click="lookingVisible = false">取 消</el-button>
                <el-button @click="lookingVisible = false" type="primary">确 定</el-button>
            </div>
        </el-dialog>
        <!--基础信息审核弹窗-->
        <el-dialog
                :visible.sync="reviewVisible"
                title="审核"
                width="30%">
            <div style="text-align: center">
                <el-radio-group v-model="this.review.basicStatus">
                    <el-radio :label="1" @click.native.prevent="clickitem(1)">通过</el-radio>
                    <el-radio :label="2" @click.native.prevent="clickitem(2)">不通过</el-radio>
                </el-radio-group>
            </div>
            <div class="dialog-footer" slot="footer">
                <el-button @click="reviewVisible = false">取 消</el-button>
                <el-button @click="handleReview" type="primary">确 定</el-button>
            </div>
        </el-dialog>
        <!--资料清单审核弹窗-->
        <el-dialog
                :visible.sync="reviewDetailVisible"
                title="审核"
                width="30%">
            <div style="text-align: center">
                <el-radio-group v-model="this.reviewdata.status">
                    <el-radio :label="1" @click.native.prevent="clickitem1(1)">通过</el-radio>
                    <el-radio :label="2" @click.native.prevent="clickitem1(2)">不通过</el-radio>
                </el-radio-group>
            </div>
            <div class="dialog-footer" slot="footer">
                <el-button @click="reviewDetailVisible = false">取 消</el-button>
                <el-button @click="handleDetail" type="primary">确 定</el-button>
            </div>
        </el-dialog>
    </div>

</template>

<script>
    import {
        getById,
        update,
        getMember,
        downloadimg,
        updateBasicStatus,
        selectBasicStatus,
        updateState,
        getannexList,
        getAnnex
    } from "@/api/hqgj/BWSService";

    export default {
        name: "Examine",
        data() {
            return {
                lookingVisible: false,
                reviewVisible: false,
                reviewDetailVisible: false,
                loading: false,
                activeName: '1',
                multipleSelection: [],
                form: {},
                review: {
                    id: "",
                    basicStatus: ""
                },
                reviewdata: {
                    id: "",
                    businessId: "",
                    status: ""
                },
                list: [],
                fileList: [],
                type: [],
                query: {
                    type: ""
                },
                query2: {
                    type: "",
                    relationId: ""
                },
                lists: [],
            };
        },
        created() {
            this.getDetails();
            this.getannexList();
        },
        methods: {
            getDetails() {
                this.id = this.$route.query.id;
                getById(this.id)
                    .then(response => {
                        this.form = response.data;
                    })

            },
            getMemberlist() {
                getMember(this.query).then(response => {
                    this.list = response.rows;
                })
            },
            getannexList() {
                this.id = this.$route.query.id;
                getannexList(this.id).then(response => {
                    // response.data;
                    this.lists = this.istop(response.data)

                })
            },
            //完成审核
            Auditing() {
                this.id = this.$route.query.id;
                selectBasicStatus(this.id).then(response => {
                    if (response.data) {
                        this.type
                        for (var i = 0; i < response.data.length; i++) {
                            if (response.data[i] == 1) {
                                this.type.push("法人");
                            }
                            if (response.data[i] == 2) {
                                this.type.push("监事");
                            }
                            if (response.data[i] == 3) {
                                this.type.push("股东");
                            }
                            if (response.data[i] == 4) {
                                this.type.push("财务");
                            }
                            if (response.data[i] == 5) {
                                this.type.push("房屋证明");

                            }
                        }
                        var str = this.type.join();
                        console.log(str);
                        this.$message({
                            message: str+ '未审核',
                            type: 'warning'
                        });
                    }

                })
            },
            //申请表置顶
            istop(data) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].id == null) {
                        var index = i;
                    }
                }
                data[0] = data.splice(index, 1, data[0])[0];
                return data;

            },

            //预览
            looking(index, data) {
                console.log(data)
                this.lookingVisible = true;
                this.query2.relationId = data.id;
                this.query2.type = 3;
                getAnnex(this.query2).then(response => {
                    this.fileList = response.rows;
                })
            },
            //下载
            FileDownload(index, data) {
                console.log(data);
                this.relationId = data.id;
                console.log(this.relationId);
                downloadimg(this.relationId).then(response => {
                    for (var i = 0; i < response.data.length; i++) {
                        let link = document.createElement('a')
                        link.href = 'data:image/png;base64,' + response.data[i]
                        link.download = data.fileName + '.png'
                        link.click()
                    }


                })
            },
            handleClick(tab, event) {
                if (tab.name == 4) {
                    this.query.type = "1,2";
                    this.getMemberlist();
                }
                if (tab.name == 5) {
                    this.query.type = "3";
                    this.getMemberlist();
                }
                if (tab.name == 6) {
                    this.query.type = "4";
                    this.getMemberlist();
                }
                if (tab.name == 7) {
                    this.query.type = "5,6,7,8";
                    this.getMemberlist();
                }
            },
            //基础信息审核弹窗
            reviewBasic() {
                this.reviewVisible = true;
            },
            //确认审核
            handleReview() {
                if (this.review.basicStatus == "") {
                    this.$message(`未选择状态`);
                } else {
                    this.review.id = this.$route.query.id;
                    updateBasicStatus(this.review.id, this.review.basicStatus).then(response => {
                        if (response.status == 1) {
                            this.$message.success(`审核成功`);
                            this.getDetails();
                            this.reviewVisible = false;
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            });
                        }
                    })
                }
            },
            //资料清单审核弹窗
            reviewDetail(index, data) {
                this.reviewdata.id = data.id;
                this.reviewDetailVisible = true;
            },
            //资料清单确认审核
            handleDetail() {
                if (this.reviewdata.status == "") {
                    this.$message(`未选择状态`);
                } else {
                    this.reviewdata.businessId = this.$route.query.id;
                    updateState(this.reviewdata).then(response => {
                        if (response.status == 1) {
                            this.$message.success(`审核成功`);
                            this.getMemberlist();
                            this.reviewVisible = false;
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            });
                        }
                    })
                }
            },
            clickitem(e) {
                e === this.review.basicStatus ? this.review.basicStatus = '' : this.review.basicStatus = e
            },
            clickitem1(e) {
                e === this.reviewdata.status ? this.reviewdata.status = '' : this.reviewdata.status = e
            },
            //获取选中行数据
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
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

    .tab-pane /deep/ .el-tabs__nav-wrap {
        overflow: unset !important;
    }

    .elrow {
        margin-bottom: 0;
    }

    .head_pic {
        max-height: 30px;
        vertical-align: middle;
    }

    .head_pics {
        max-height: 200px;
        margin-left: 20px;
        vertical-align: middle;
    }

    .red {
        color: red;
    }

    .green {
        color: green;
    }

    .orange {
        color: #FF9900;
    }
</style>
