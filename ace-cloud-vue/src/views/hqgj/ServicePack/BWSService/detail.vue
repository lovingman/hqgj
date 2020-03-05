<template>
    <div class="container">
        <div style="width: 100%;height: 20px">
            <!--<el-button @click="Auditing" size="small" style="float: right" type="primary"-->
            <!--v-if="this.$route.query.word=='examine'">完成审核-->
            <!--</el-button>-->
            <el-button @click="back" size="small" style="float: right" type="primary"
                       v-if="this.$route.query.word=='preview'">返回
            </el-button>
        </div>
        <div class="tab-pane" style="width: 100%">
            <el-tabs @tab-click="handleClick" style="width: 100%" v-model="activeName">
                <el-tab-pane label="资料清单" name="1">
                    <el-table :data="lists" @selection-change="handleSelectionChange" class="table" max-height="475"
                              ref="multipleTable" v-loading="loading">
                        <el-table-column :selectable='selectInit' align="center" type="selection"
                                         width="55"></el-table-column>
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="名称" prop="fileName" sortable="custom"></el-table-column>
                        <el-table-column label="提交时间" prop="createDate" width="300"></el-table-column>
                        <el-table-column label="附件数" prop="annexNum" width="250"></el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作" width="100">
                            <template slot-scope="scope">
                                <el-button @click="FileDownload(scope.$index,scope.row)" type="text"
                                           v-if="scope.row.id != null">下载
                                </el-button>
                                <el-button type="text" v-else="scope.row.id != null">
                                    <router-link :to="{name:'application',query:{id:form.id,name:'download'}}"
                                                 target="_blank">下载
                                    </router-link>
                                </el-button>
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
                    <el-dropdown @command="handleCommand" style="margin-top: 20px" trigger="click">
                        <el-button>压缩包下载<i class="el-icon-arrow-down el-icon--right"></i>
                        </el-button>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="rarDownload">rar下载</el-dropdown-item>
                            <el-dropdown-item command="zipDownload">zip下载</el-dropdown-item>
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
                            <el-col :span="8">
                                <el-form-item label="申请日期:" prop="name">
                                    <span>{{form.createDate}}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8" v-if="form.shOrg!=null">
                                <el-form-item label="审核机构:" prop="shOrg">
                                    <span v-if="form.shOrg=='1'">律师事务所</span>
                                    <span v-if="form.shOrg=='2'">会计事务所</span>
                                    <span v-if="form.shOrg=='3'">培训机构</span>
                                    <span v-if="form.shOrg=='4'">工信局</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8" v-if="form.shOrg!=null">
                                <el-form-item label="审核时间:" prop="name">
                                    <span>{{form.shDate}}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="基本信息" name="3">
                    <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                        <span style="margin-left: 20px;font-weight:bold;">基本信息</span>
                        <el-button @click="reviewBasic" style="float: right;margin-right: 25px;padding-top: 1px"
                                   type="text"
                                   v-if="form.basicStatus!='1' && this.$route.query.word=='examine'">审核
                        </el-button>
                        <div class="green" style="float: right;margin-right: 25px;padding-top: 1px" type="text"
                             v-if="form.basicStatus=='1' && this.$route.query.word=='examine'">通过
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
                                    <span>{{form.regBonus}}万元</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="企业地址:" prop="name">
                                    <span>{{form.areaName}}{{form.companyAddress}}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row class="elrow">
                            <el-col :span="12">
                                <el-form-item label="经营范围:" prop="name">
                                    <span>{{form.manageExtent}}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                &nbsp;
                            </el-col>
                        </el-row>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="法人、监事" name="4">
                    <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                        <span style="margin-left: 20px;font-weight:bold;">法人、监事</span>
                    </div>
                    <el-divider></el-divider>
                    <el-table :data="list" @selection-change="handleSelectionChange" class="table" max-height="475"
                              ref="multipleTable" v-loading="loading">
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="姓名" prop="name" sortable="custom" width="120"></el-table-column>
                        <el-table-column label="职位" prop="type" width="150">
                            <template slot-scope="scope">
                                <div type="text" v-if="scope.row.type=='1'">法人</div>
                                <div type="text" v-if="scope.row.type=='2'">监事</div>
                                <div type="text" v-if="scope.row.type=='3'">股东</div>
                                <div type="text" v-if="scope.row.type=='4'">财务</div>
                            </template>
                        </el-table-column>
                        <el-table-column label="联系电话" prop="mobile" width="150"></el-table-column>
                        <el-table-column label="身份证附件" prop="basicAnnexes">
                            <template slot-scope="scope">
                                <viewer :images="scope.row.basicAnnexes">
                                    <img :key="attach.fileURL" :src="attach.fileURL" class="head_pic"
                                         v-for="attach in scope.row.basicAnnexes"/>
                                </viewer>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" prop="status" v-if="this.$route.query.word=='examine'" width="100">
                            <template slot-scope="scope">
                                <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                                <div class="green" type="text" v-if="scope.row.status=='1'">通过</div>
                                <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                            </template>
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作"
                                         v-if="this.$route.query.word=='examine'" width="60">
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
                    <el-table :data="list" @selection-change="handleSelectionChange" class="table" max-height="475"
                              ref="multipleTable" v-loading="loading">
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="股东姓名" prop="name" sortable="custom" width="120"></el-table-column>
                        <el-table-column label="股份比例" prop="sharesProportion" width="150"></el-table-column>
                        <el-table-column label="身份证附件" prop="basicAnnexes">
                            <template slot-scope="scope">
                                <viewer :images="scope.row.basicAnnexes">
                                    <img :key="attach.fileURL" :src="attach.fileURL" class="head_pic"
                                         v-for="attach in scope.row.basicAnnexes"/>
                                </viewer>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" prop="status" v-if="this.$route.query.word=='examine'" width="100">
                            <template slot-scope="scope">
                                <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                                <div class="green" type="text" v-if="scope.row.status=='1'">通过</div>
                                <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                            </template>
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作"
                                         v-if="this.$route.query.word=='examine'" width="60">
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
                    <el-table :data="list" @selection-change="handleSelectionChange" class="table" max-height="475"
                              ref="multipleTable" v-loading="loading">
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="姓名" prop="name" sortable="custom" width="120"></el-table-column>
                        <el-table-column label="联系电话" prop="mobile" width="150"></el-table-column>
                        <el-table-column label="身份证附件" prop="basicAnnexes">
                            <template slot-scope="scope">
                                <viewer :images="scope.row.basicAnnexes">
                                    <img :key="attach.fileURL" :src="attach.fileURL" class="head_pic"
                                         style="line-height: 48px"
                                         v-for="attach in scope.row.basicAnnexes"/>
                                </viewer>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" prop="status" v-if="this.$route.query.word=='examine'" width="100">
                            <template slot-scope="scope">
                                <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                                <div class="green" type="text" v-if="scope.row.status=='1'">通过</div>
                                <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                            </template>
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作"
                                         v-if="this.$route.query.word=='examine'" width="60">
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
                    <el-table :data="list" @selection-change="handleSelectionChange" class="table" max-height="475"
                              ref="multipleTable">
                        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                        <el-table-column label="名称" prop="name" sortable="custom" width="250"></el-table-column>
                        <el-table-column label="附件" prop="file">
                            <template slot-scope="scope">
                                <viewer :images="scope.row.basicAnnexes">
                                    <img :key="attach.fileURL" :src="attach.fileURL" class="head_pic"
                                         v-for="attach in scope.row.basicAnnexes"/>
                                </viewer>
                            </template>
                        </el-table-column>
                        <el-table-column label="状态" prop="status" v-if="this.$route.query.word=='examine'" width="100">
                            <template slot-scope="scope">
                                <div class="orange" type="text" v-if="scope.row.status=='0'">待审核</div>
                                <div class="green" type="text" v-if="scope.row.status=='1'">通过</div>
                                <div class="red" type="text" v-if="scope.row.status=='2'">驳回修改</div>
                            </template>
                        </el-table-column>
                        <el-table-column align="right" fixed="right" header-align="center" label="操作"
                                         v-if="this.$route.query.word=='examine'" width="60">
                            <template slot-scope="scope">
                                <el-button @click="reviewDetail(scope.$index,scope.row)" type="text"
                                           v-if="scope.row.status!= '1'">审核
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
                <el-tab-pane label="其他" name="8" v-if="this.$route.query.word=='preview'">
                    <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                        <span style="margin-left: 20px;font-weight:bold;">其他</span>
                    </div>
                    <el-divider></el-divider>
                    <el-form label-width="250px" ref="form">
                        <el-form-item label="申请免费服务项目:">
                            <div v-for="item in Other">
                                <span v-if="item.type==57">{{item.optionName}}</span>
                            </div>
                        </el-form-item>
                        <el-form-item label="确定培训意向:">
                            <div v-for="item in Other">
                                <span v-if="item.type==58">{{item.optionName}}</span>
                            </div>
                        </el-form-item>
                        <el-form-item label="开户银行:">
                            <div v-for="item in Other">
                                <span v-if="item.type==59">{{item.optionName}}</span>
                            </div>
                        </el-form-item>
                        <el-form-item label="是否选择代理记账服务:">
                            <div v-for="item in Other">
                                <span v-if="item.type==60">{{item.optionName}}</span>
                            </div>
                        </el-form-item>
                        <el-form-item label="特殊说明:">
                            <div v-for="item in Other">
                                <span v-if="item.type==1">{{item.content}}</span>
                            </div>
                        </el-form-item>
                    </el-form>
                </el-tab-pane>
            </el-tabs>
        </div>
        <!--预览弹窗-->
        <el-dialog :visible.sync="lookingVisible" title="预览" width="60%">
            <div class="dialog-main">
                <div class="dialog-box" v-for="attach in fileList">
                    <div class="list-box">
                        <div class="title">{{attach.name}}</div>
                        <ul style="padding-left: 42px!important">
                            <li style="list-style: none;display: inline;" v-for="imgArr in attach.basicAnnexes">
                                <i class="image" @click="previews(imgArr.fileURL)"><img :src="imgArr.fileURL" alt="" class="img-box"></i>
                                <el-dialog :visible.sync="dialogVisible" append-to-body>
                                    <img :src="dialogImageUrl" alt="" width="100%">
                                </el-dialog>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="dialog-footer" slot="footer">
                <el-button @click="lookingVisible = false">取 消</el-button>
                <el-button @click="lookingVisible = false" type="primary">确 定</el-button>
            </div>
        </el-dialog>
        <!--基础信息审核弹窗-->
        <el-dialog :visible.sync="reviewVisible" title="审核" width="30%">
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
        <el-dialog :visible.sync="reviewDetailVisible" title="审核" width="30%">
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
        getAnnex,
        getAppend
    } from "@/api/hqgj/BWSService";
    import {
        getAreaTree,
        getDict
    } from "@/api/sys";
    import JSZip from "jszip";
    import FileSaver from "file-saver";

    export default {
        name: "details",
        data() {
            return {
                dialogVisible:false,
                zipfile: [],
                dict: [],
                dict2: [],
                dict3: [],
                dict4: [],
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
                Other: [],
                list: [],
                fileList: [],
                type: [],
                others: [],
                query: {
                    types: "",
                    businessId: ""
                },
                query2: {
                    type: "",
                    relationId: ""
                },
                query3: {
                    businessId: ""
                },
                lists: [],
            };
        },
        created() {
            this.getDetails();
            this.getannexList();
            this.dictQuery();
            this.otherhandle();
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
                    this.lists = this.istop(response.data)
                })
            },
            //完成审核
            Auditing() {
                this.id = this.$route.query.id;
                selectBasicStatus(this.id).then(response => {
                    if (response.data != '') {
                        this.type = [];
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
                            if (response.data[i] == 9) {
                                this.type.push("基本信息");
                            }
                        }

                        var str = this.type.join("、");
                        this.$message({
                            message: str + '未审核或未通过审核',
                            type: 'warning'
                        });
                    } else {
                        this.$confirm("审核通过后，将发送审核通过通知，是否继续?", "提示", {
                            confirmButtonText: "确定",
                            cancelButtonText: "取消",
                            type: "warning"
                        }).then(() => {
                            this.id = this.$route.query.id;
                            updateBasicStatus(this.id, 1, 2).then(response => {
                                if (response.status == 1) {
                                    this.$message.success(`审核通过`);
                                    this.back();
                                } else {
                                    this.$message({
                                        message: response.message,
                                        type: "warning"
                                    });
                                }
                            })
                        }).catch(() => {
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
                this.lookingVisible = true;
                let obj = {};
                obj.id = data.id;
                obj.type = data.type;
                getAnnex(obj).then(response => {
                    this.fileList = response.data;
                })
            },
            //下载
            FileDownload(index, data) {
                if (data.fileName != '') {
                    let obj = {};
                    obj.businessId = data.id;
                    obj.type = data.type;
                    downloadimg(obj).then(response => {
                        if (response.data != []) {
                            for (var i = 0; i < response.data.length; i++) {
                                let link = document.createElement('a')
                                link.href = 'data:image/png;base64,' + response.data[i]
                                link.download = data.fileName + '.png'
                                link.click()
                            }
                        } else {
                            this.$message.warning(`附件数据丢失`);
                        }
                    })
                } else {
                    this.$message.warning(`数据缺失`);
                }

            },
            //标签页查看
            handleClick(tab, event) {
                if (tab.name == 4) {
                    this.query.types = "1,2";
                    this.query.businessId = this.$route.query.id;
                    this.getMemberlist();
                }
                if (tab.name == 5) {
                    this.query.types = "3";
                    this.query.businessId = this.$route.query.id;
                    this.getMemberlist();
                }
                if (tab.name == 6) {
                    this.query.types = "4";
                    this.query.businessId = this.$route.query.id;
                    this.getMemberlist();
                }
                if (tab.name == 7) {
                    this.query.types = "5,6,7,8";
                    this.query.businessId = this.$route.query.id;
                    this.getMemberlist();
                }
                if (tab.name == 8) {
                    this.otherhandle();
                }
            },
            otherhandle() {
                this.query3.businessId = this.$route.query.id;
                getAppend(this.query3).then(response => {
                    for (var i = 0; i < response.rows.length; i++) {
                        this.Other[i] = this.getName(response.rows[i]);
                    }
                    console.log(this.Other);
                })
            },
            //服务申请字典
            dictQuery() {
                getDict("57")
                    .then(response => {
                        this.dict = response.data['57'];
                    })
                getDict("58")
                    .then(response => {
                        this.dict2 = response.data['58'];
                    })
                getDict("59")
                    .then(response => {
                        this.dict3 = response.data['59'];
                    })
                getDict("60")
                    .then(response => {
                        this.dict4 = response.data['60'];
                    })
            },
            //字符转换
            getName(data) {
                console.log(data.type);
                if (data.type == 57) {
                    let nameNull = data.option.split(",");
                    console.log(nameNull)
                    if (nameNull != 0) {
                        var arr = [];
                        for (var i = 0; i < nameNull.length; i++) {
                            for (var j = 0; j < this.dict.length; j++) {
                                if (nameNull[i] == this.dict[j].code) {
                                    console.log(nameNull[i])
                                    arr[i] = this.dict[j].name;
                                    console.log(arr)
                                }
                            }
                        }
                        data.optionName = arr.join('、');
                        console.log(data.optionName)
                        return data;
                    }
                }
                if (data.type == 58) {
                    let nameNull = data.option.split(",");
                    if (nameNull != 0) {
                        var arr = [];
                        for (var i = 0; i < nameNull.length; i++) {
                            for (var j = 0; j < this.dict2.length; j++) {
                                if (nameNull[i] == this.dict2[j].code) {
                                    arr[i] = this.dict2[j].name;
                                }
                            }

                        }
                        data.optionName = arr.join('、');
                        return data;
                    }
                }
                if (data.type == 59) {
                    let nameNull = data.option.split(",");
                    if (nameNull != 0) {
                        var arr = [];
                        for (var i = 0; i < nameNull.length; i++) {
                            for (var j = 0; j < this.dict3.length; j++) {
                                if (nameNull[i] == this.dict3[j].code) {
                                    arr[i] = this.dict3[j].name;

                                }
                            }

                        }
                        data.optionName = arr.join('、');
                        return data;
                    }
                }
                if (data.type == 60) {
                    let nameNull = data.option.split(",");
                    if (nameNull != 0) {
                        var arr = [];
                        for (var i = 0; i < nameNull.length; i++) {
                            for (var j = 0; j < this.dict4.length; j++) {
                                if (nameNull[i] == this.dict4[j].code) {
                                    arr[i] = this.dict4[j].name;
                                }
                            }

                        }
                        data.optionName = arr.join('、');
                        return data;
                    }
                }
                if (data.type == 1) {
                    return data;
                }
            },
            //基础信息审核弹窗
            reviewBasic() {
                this.reviewVisible = true;
            },
            //确认基础信息审核
            handleReview() {
                if (this.review.basicStatus == "") {
                    this.$message(`未选择状态`);
                } else {
                    this.review.id = this.$route.query.id;
                    updateBasicStatus(this.review.id, this.review.basicStatus, 1).then(response => {
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
                            this.reviewDetailVisible = false;
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            });
                        }
                    })
                }
            },
            //压缩包下载
            handleCommand(command) {
                //rar下载
                if (command == 'rarDownload') {
                    if (this.multipleSelection.length != 0) {
                        this.type = this.zipfile.join(',');
                        let obj = {};
                        obj.type = this.type;
                        obj.businessId = this.$route.query.id
                        downloadimg(obj).then(response => {
                            if (response.data != []) {
                                var zip = new JSZip();
                                var img = zip.folder("附件");
                                for (var i = 0; i < response.data.length; i++) {
                                    console.log(response.data[i]);
                                    var imgData = response.data[i];
                                    var j = i + 1
                                    img.file("附件" + j + ".png", imgData, {
                                        base64: true
                                    });
                                }
                                zip.generateAsync({
                                    type: "blob"
                                })
                                    .then(function (content) {
                                        FileSaver.saveAs(content, "附件.rar");
                                    });
                            } else {
                                this.$message.warning(`附件数据丢失`);
                            }
                        })
                    } else {
                        let obj = {};
                        obj.businessId = this.$route.query.id;
                        downloadimg(obj).then(response => {
                            if (response.data != []) {
                                var zip = new JSZip();
                                var img = zip.folder("附件");
                                for (var i = 0; i < response.data.length; i++) {
                                    console.log(response.data[i]);
                                    var imgData = response.data[i];
                                    var j = i + 1
                                    img.file("附件" + j + ".png", imgData, {
                                        base64: true
                                    });
                                }
                                zip.generateAsync({
                                    type: "blob"
                                })
                                    .then(function (content) {
                                        FileSaver.saveAs(content, "附件.rar");
                                    });
                            } else {
                                this.$message.warning(`附件数据丢失`);
                            }
                        })
                    }
                }
                //zip下载
                if (command == 'zipDownload') {
                    console.log(this.multipleSelection.length)
                    if (this.multipleSelection.length != 0) {
                        this.type = this.zipfile.join(',');
                        let obj = {};
                        obj.type = this.type;
                        obj.businessId = this.$route.query.id;
                        downloadimg(obj).then(response => {
                            if (response.data != []) {
                                var zip = new JSZip();
                                var img = zip.folder("附件");
                                for (var i = 0; i < response.data.length; i++) {
                                    console.log(response.data[i]);
                                    var imgData = response.data[i]
                                    var j = i + 1
                                    img.file("附件" + j + ".png", imgData, {
                                        base64: true
                                    });
                                }
                                zip.generateAsync({
                                    type: "blob"
                                })
                                    .then(function (content) {
                                        FileSaver.saveAs(content, "附件.zip");
                                    });
                            } else {
                                this.$message.warning(`附件数据丢失`);
                            }
                        })
                    } else {
                        let obj = {};
                        obj.businessId = this.$route.query.id;
                        // this.businessId = this.$route.query.id;
                        downloadimg(obj).then(response => {
                            if (response.data != []) {
                                var zip = new JSZip();
                                var img = zip.folder("附件");
                                for (var i = 0; i < response.data.length; i++) {
                                    console.log(response.data[i]);
                                    var imgData = response.data[i]
                                    var j = i + 1
                                    img.file("附件" + j + ".png", imgData, {
                                        base64: true
                                    });
                                }
                                zip.generateAsync({
                                    type: "blob"
                                })
                                    .then(function (content) {
                                        FileSaver.saveAs(content, "附件.zip");
                                    });
                            } else {
                                this.$message.warning(`附件数据丢失`);
                            }
                        })
                    }

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
                console.log(this.multipleSelection);
                for (var i = 0; i < this.multipleSelection.length; i++) {
                    this.zipfile[i] = this.multipleSelection[i].type;
                }
                console.log(this.zipfile);
            },
            back() {
                this.$router.push({
                    path: "/hqgj/ServicePack/BWSService/index"
                });
            },
            selectInit(row, index) {
                // console.log(row)
                if (index == 0) {
                    return false //不可勾选
                } else {
                    return true //可勾选
                }
            },
            // 图片预览
            previews(data) {
                this.dialogImageUrl = data;
                this.dialogVisible = true;
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

    .dialog-box {
        margin-bottom: 20px;

    }

    .dialog-box .title {
        margin-bottom: 20px;
    }

    .dialog-box .img {
        display: inline-block;
        margin-right: 20px;
    }

    .img-box {
        width: 148px;
        height: 148px;
        object-fit: cover;
        margin-right: 10px;
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
