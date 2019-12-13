<template>
    <div class="container">
        <div style="width: 100%">
            <el-button size="small" type="primary " style="float: right">完成审核</el-button>
        </div>
        <div class="tab-pane">
            <el-tabs @tab-click="handleClick" style="width: 100%" v-model="activeName">
                <el-tab-pane label="资料清单" name="1">
                    <el-table
                            :data="lists"
                            @selection-change="handleSelectionChange"
                            @sort-change="handleSort"
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
                                <el-button @click="" type="text">下载</el-button>
                                <span class="strightline">|</span>
                                <el-button v-if="scope.row.id != null"  @click="looking(scope.$index,scope.row)" type="text">预览</el-button>
                                <el-button v-else="scope.row.id != null" type="text">
                                    <router-link target="_blank" to="/application">预览</router-link>
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
                        <el-button @click="" style="float: right;margin-right: 25px;padding-top: 1px" type="text">审核
                        </el-button>
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
                        <el-table-column label="状态" prop="status" width="80">
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                            <template slot-scope="scope">
                                <el-button @click="preview" type="text">审核</el-button>
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
                            :data="list"
                            @selection-change="handleSelectionChange"
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
                        <el-form-item label="特殊说明:">
                            <span>无</span>
                        </el-form-item>
                    </el-form>
                </el-tab-pane>
            </el-tabs>
        </div>
        <el-dialog
                :before-close="handleClose"
                :visible.sync="lookingVisible"
                title="预览"
                width="60%">
            <div class="dialog-main">
                <viewer :images="fileList">
                    <img :key="attach.fileURL" :src="attach.fileURL"
                         class="head_pics" v-for="attach in fileList"/>
                </viewer>
            </div>
            <span class="dialog-footer" slot="footer">
    <el-button @click="lookingVisible = false">取 消</el-button>
    <el-button @click="sign" type="primary">确 定</el-button>
  </span>
        </el-dialog>
    </div>

</template>

<script>
    import {getById, getMember,getannexList,getAnnex} from "@/api/hqgj/BWSService";

    export default {
        name: "Examine",
        data() {
            return {
                lookingVisible:false,
                activeName: '1',
                multipleSelection:[],
                form: {},
                list: [],
                fileList:[],
                query: {
                    type: ""
                },
                query2: {
                    type: "",
                    relationId:""
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
            getannexList(){
                this.id = this.$route.query.id;
                getannexList(this.id).then(response =>{
                    // response.data;
                    this.lists = this.istop(response.data)

                })
            },
            //申请表置顶
            istop(data){
                for(var i = 0; i< data.length;i++){
                    if (data[i].id== null){
                        var index = i;
                    }
                }
                data[0] = data.splice(index, 1, data[0])[0];
               return data;

            },

            //预览
            looking(index,data){
                this.lookingVisible = true;
                this.query2.relationId =data.id;
                this.query2.type = 3;
                getAnnex(this.query2).then(response=>{
                    this.fileList=response.rows;
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
                if(tab.name == 7){
                    this.query.type = "5,6,7,8";
                    this.getMemberlist();
                }


                // console.log(tab.label);
                // console.log(tab.name);
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
        max-height: 25px;
        vertical-align: middle;
    }
    .head_pics {
        max-height: 200px;
        margin-left: 20px;
        vertical-align: middle;
    }
</style>
