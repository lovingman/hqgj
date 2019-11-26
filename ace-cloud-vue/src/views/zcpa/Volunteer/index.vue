<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row :gutter="24">
                    <!--<el-col :span="2">-->
                        <!--<el-button type="primary" @click="Added" style="float: left;width: 93px">新增</el-button>-->
                    <!--</el-col>-->

                    <el-col :span="14" v-if="userBtn['/zcpa/baseVolunteer/exportXls']">
                        <el-button type="primary" v-if="userBtn['/zcpa/baseVolunteer/exportXls']" @click="exported" style="float: left">{{userBtn['/zcpa/baseVolunteer/exportXls']}}</el-button>
                    </el-col>
                    <el-col :span="14" v-else="userBtn['/zcpa/baseVolunteer/exportXls']">&nbsp;</el-col>
                    <!--<el-col :span="12">-->
                        <!--<el-button type="primary" @click="deletePage" style="float: left">批量删除-->
                        <!--</el-button>-->
                    <!--</el-col>-->
                    <el-col :span="4">
                        <el-cascader
                                placeholder="请选择行政区划"
                                v-model="areaCodess"
                                :options="areaCodeOptions"
                                :props="areaCodeProps"
                                clearable
                                change-on-select
                                filterable class="handle-select mr11" style="float: right; width: 251px"/>
                    </el-col>
                    <el-col :span="4">
                        <el-input v-model="query.name" clearable style="float: left;margin-left: 10px"
                                  placeholder="请输入姓名或身份证号码" @keyup.enter.native="search" class="handle-input"></el-input>
                    </el-col>
                    <el-col :span="2">
                        <el-button type="primary" icon="el-icon-search" style="float: right;margin-left: 25px"
                                   @click="search">搜索
                        </el-button>
                    </el-col>
                </el-row>
            </div>
            <el-table :data="rows"
                      border
                      fit
                      max-height="475"
                      class="table"
                      ref="multipleTable"
                      v-loading="loading"
                      element-loading-text="加载中"
                      element-loading-spinner="el-icon-loading"
                      @selection-change="handleSelectionChange"
                      @sort-change="handleSort">
                <el-table-column type="selection" width="55" label="序号" align="center"></el-table-column>
                <el-table-column prop="name" label="姓名" sortable='custom' width="100">
                </el-table-column>
                <el-table-column prop="sex" label="性别" sortable='custom' width="100">
                    <template slot-scope="scope">
                        <div type="text" v-if="scope.row.sex=='1'">男</div>
                        <div type="text" v-if="scope.row.sex=='2'">女</div>
                    </template>
                </el-table-column>
                <el-table-column prop="mobile" label="手机号码" width="150">
                </el-table-column>
                <el-table-column prop="areaName" label="所在地区">
                </el-table-column>
                <el-table-column prop="userTypeName" label="所属类型" sortable='custom' width="180">
                </el-table-column>
                <el-table-column prop="createDate" label="注册日期" sortable='custom' width="220">
                </el-table-column>
                <!--<el-table-column prop="status" label="状态" width="100">-->
                <!--<template slot-scope="scope">-->
                <!--<div type="text" class="green" v-if="scope.row.status=='1'">有效</div>-->
                <!--<div type="text" class="orange" v-if="scope.row.status=='0'">失效</div>-->
                <!--</template>-->
                <!--</el-table-column>-->
                <el-table-column label="操作" fixed="right" width="100" align="right" header-align="center">
                    <template slot-scope="scope">
                        <el-button type="text" v-if="userBtn['/zcpa/baseVolunteer/update']" @click="handleEdit(scope.$index,scope.row)">{{userBtn['/zcpa/baseVolunteer/update']}}</el-button>
                        <span class="strightline" v-if="userBtn['/zcpa/baseVolunteer/update']">|</span>
                        <!--<el-button type="text" @click="handleDelete(scope.$index,scope.row)">删除</el-button>-->
                        <!--<span class="strightline">|</span>-->
                        <el-button type="text" @click="handleLook(scope.$index,scope.row)">详情</el-button>
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

        <!--志愿者编辑-->
        <el-dialog title="志愿者编辑" :visible.sync="editVisible" width="60%">
            <el-form ref="form" class="editForm" :model="form" label-width="120px">
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="姓名" prop="name">
                            <el-input v-model="form.name" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="性别" prop="sex">
                            <el-select v-model="form.sex" clearable placeholder="请选择性别"
                                       style="width: 100%">
                                <el-option
                                        v-for="item in sexValueArr"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="手机号码" prop="mobile">
                            <el-input v-model="form.mobile" clearable placeholder="请输入手机号码"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="身份证号码" prop="idCard">
                            <el-input v-model="form.idCard" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="出生日期" prop="name">
                            <el-date-picker
                                    v-model="form.birthDate"
                                    type="date"
                                    :disabled="true"
                                    style="width: 100%"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="政治面貌" prop="deptId">
                            <el-select v-model="form.politicalAppearance" clearable placeholder="请选择政治面貌"
                                       style="width: 100%">
                                <el-option
                                        v-for="item in politicalValueArr"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="所属单位" prop="deptId">
                            <el-input v-model="form.deptId" clearable placeholder="请输入所属单位"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="所属地区" prop="areaCodess">
                            <el-cascader
                                    placeholder="请选择行政区划"
                                    v-model="areaCodeses"
                                    :options="areaCodeOptions"
                                    :props="areaCodeProps"
                                    clearable
                                    change-on-select
                                    filterable style="width: 100%"/>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="所属类型" prop="userType">
                            <el-select v-model="form.userType" clearable placeholder="请选择志愿者类型"
                                       style="width: 100%">
                                <el-option
                                        v-for="item in peopleValueArr"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="注册日期" prop="createDate">
                            <el-input v-model="form.createDate" :disabled="true"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <span slot="footer" class="dialog-footer">
                   <el-button @click="editVisible = false">取消</el-button>
                 <el-button type="primary" @click="editVolunteer">提交</el-button>
            </span>
        </el-dialog>

        <!--志愿者新增-->
        <el-dialog title="志愿者新增" :visible.sync="addVisible" width="60%" @close="closeReset('addform')">
            <el-form ref="addform"
                     class="addForm"
                     :rules="addsRules"
                     v-if="hackReset"
                     :model="addform"
                     label-width="120px">
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="姓名" prop="name">
                            <el-input v-model="addform.name" placeholder="请输入姓名"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="性别" prop="sex">
                            <el-select v-model="addform.sex" clearable placeholder="请选择性别"
                                       style="width: 100%">
                                <el-option
                                        v-for="item in sexValueArr"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="手机号码" prop="mobile">
                            <el-input v-model="addform.mobile" placeholder="请输入手机号码"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="身份证号码" prop="idCard">
                            <el-input v-model="addform.idCard"
                                      placeholder="请输入身份证号码"
                                      @blur="getName(addform.idCard.length)"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="出生日期" prop="birthDate">
                            <el-date-picker
                                    v-model="addform.birthDate"
                                    type="date"
                                    style="width: 100%"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="政治面貌" prop="politicalAppearance">
                            <el-select v-model="addform.politicalAppearance" clearable placeholder="请选择政治面貌"
                                       style="width: 100%">
                                <el-option
                                        v-for="item in politicalValueArr"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="所属单位" prop="deptId">
                            <el-input v-model="addform.deptId" placeholder="请输入所属单位"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="10">
                        <el-form-item label="所属地区" prop="areaCodes">
                            <el-cascader
                                    placeholder="请选择行政区划"
                                    v-model="addform.areaCodes"
                                    :options="areaCodeOptions"
                                    :props="areaCodeProps"
                                    clearable
                                    change-on-select
                                    filterable style="width: 100%"/>
                            <!--<el-input v-model=""></el-input>-->
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="10">
                        <el-form-item label="所属类型" prop="userType">
                            <el-select v-model="addform.userType" clearable placeholder="请选择志愿者类型"
                                       style="width: 100%">
                                <el-option
                                        v-for="item in peopleValueArr"
                                        :key="item.code"
                                        :label="item.name"
                                        :value="item.code"
                                ></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <span slot="footer" class="dialog-footer">
                   <el-button @click="cancelReset('addform')">取消</el-button>
                 <el-button type="primary" @click="addVolunteer('addform')">提交</el-button>
            </span>
        </el-dialog>

        <!--查看志愿者-->
        <el-dialog title="查看志愿者"
                   :visible.sync="lookVisible"
                   width="60%"
                   @close="lookVisible = false">
            <el-form class="lookForm" content="100%">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="姓名:">
                            <span>{{see.name}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="性别:">
                            <span>{{see.sexName}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="手机号:">
                            <span>{{see.mobile}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="身份证号码:">
                            <span>{{see.idCard}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="出生日期:">
                            <span>{{see.birthDate}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="政治面貌:">
                            <span>{{see.politicalAppearanceType}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="所属单位:">
                            <span>{{see.deptId}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="所属地区:">
                            <span>{{see.areaName}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="所属类型:">
                            <span>{{see.userTypeName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="注册日期:">
                            <span>{{see.createDate}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <span slot="footer" class="dialog-footer">
                   <el-button type="primary" @click="lookVisible = false">关闭</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    import {
        page,
        update,
        getById,
        deleteById,
        deleteByIds,
        create,
        exportXls,
        getAreaCode
    } from "@/api/zcpa/baseVolunteer";
    import {getAreaTree, getDict} from "@/api/sys";
    import {getInfo} from "@/api/user";
    import {mapGetters} from "vuex";

    export default {
        name: "index",
        data() {
            return {
                rows: [],
                //账号信息
                userDisplay: {
                    areaCode: ""
                },
                currentPage: 1, // 列表显示初始页
                pagesize: 10, //  列表显示每页的数据
                total: 0,
                see: {},
                hackReset: true,//重置属性
                loading: false,
                lookVisible: false,
                editVisible: false,
                addVisible: false,
                multipleSelection: [],//选中行数据
                peopleValueArr: [], //志愿者类型
                sexValueArr: [], //性别类型
                politicalValueArr: [], //政治面貌类型
                areaCodess: [], //搜索行政区划
                areaCodeses: [], //编辑行政区划
                areaCodeOptions: [], //行政区划
                areaCodeProps: {
                    value: "id",
                    label: "text"
                },
                //搜索
                query: {
                    orderBy:"",
                    sord:"",
                    name: "",
                    // areaCode:""
                },
                //导出条件
                exportDatas: {
                    name: ""
                },
                //编辑
                form: {
                    name: "",
                    sex: "",
                    mobile: "",
                    idCard: "",
                    deptId: "",
                    areaCode: "",
                    userType: "",
                    birthDate: "",
                    orgId: "",
                    createDate: "",
                    politicalAppearance: ""
                },
                //新增
                addform: {
                    name: "",
                    sex: "",
                    mobile: "",
                    idCard: "",
                    deptId: "",
                    birthDate: "",
                    areaCode: "",
                    userType: "",
                    orgId: "",
                    areaCodes: [],
                    politicalAppearance: ""
                },
                addsRules: {
                    name: [
                        {required: true, message: "请输入姓名", trigger: "blur"}
                    ],
                    sex: [
                        {required: true, message: "请选择性别", trigger: "change"}
                    ],
                    mobile: [
                        {required: true, message: "请输入手机号码", trigger: "blur"},
                    ],
                    idCard: [
                        {required: true, message: "请输入身份证号码", trigger: "blur"},
                    ],
                    politicalAppearance: [
                        {required: true, message: "请输入政治面貌", trigger: "blur"}
                    ],
                    areaCodes: [
                        {required: true, message: "请选择所属地区", trigger: "change"}
                    ],
                    userType: [
                        {required: true, message: "请选择专业类型", trigger: "change"}
                    ],
                },

            };
        },
        created() {
            this.getinformation();
            // this.getData();
            this.initDictData();
            this.sexQuery();
            this.politicalQuery();

        },
        computed: {
            ...mapGetters(["dictArrayObj","userBtn"])
        },
        methods: {
            //获取账号信息
            getinformation() {
                getAreaCode().then(response => {
                    this.userDisplay = response.data;
                    this.areaCodeObj = this.userDisplay.areaCode;
                    this.getData();
                    this.AreaCodeQuery(this.userDisplay);
                });
            },

            //排序
            handleSort(column, prop, order){
                console.log(column.prop,column.order);
                   if (column.order == null || column.prop == null) {
                       this.query.sord = "";
                       this.query.orderBy = "";
                   }else{
                       this.query.sord = column.order.substring(0,column.order.length-6);
                       this.query.orderBy = column.prop.replace("Name","");
                   }

                this.getData();
            },
            handleQuery: function () {
                this.currentPage = 1;
                this.getData();
            },
            handleSizeChange: function (size) {
                this.pagesize = size;
                //每页下拉显示数据
                this.getData();
            },
            handleCurrentChange: function (currentPage) {
                this.currentPage = currentPage;
                //点击第几页
                this.getData();
            },
            //获取列表数据
            getData() {
                // console.log(this.query.areaCode);
                this.query = Object.assign(this.query, {
                    areaCode: this.areaCodeObj,
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                this.loading = true;
                page(this.query).then(response => {
                    this.total = response.total;
                    // this.rows = this.formatDataList(response.rows);
                    this.rows = this.politicalDataList(response.rows);
                    this.loading = false;
                });
            },
            //翻译专业类型
            // formatDataList(data) {
            //     var peopleType = this.peopleValueArr;
            //     for (var i = 0; i < data.length; i++) {
            //         for (var y = 0; y < peopleType.length; y++) {
            //             if (data[i].userType == peopleType[y].code) {
            //                 data[i].userType$ = peopleType[y].name;
            //             }
            //         }
            //     }
            //     return data;
            // },

            //翻译政治面貌
            politicalDataList(data) {
                var politicalType = this.politicalValueArr;
                for (var i = 0; i < data.length; i++) {
                    for (var y = 0; y < politicalType.length; y++) {
                        if (data[i].politicalAppearance == politicalType[y].code) {
                            data[i].politicalAppearanceType = politicalType[y].name;
                        }
                    }
                }
                return data;
            },

            //身份证号码转换生日
            getName(val) {
                let iden = this.addform.idCard;
                let birth = null;
                let myDate = new Date();
                let sex = null;

                if (val === 18) {
                    sex = iden.substring(16,17);
                    birth = iden.substring(6, 10) + "-" + iden.substring(10, 12) + "-" + iden.substring(12, 14);


                }
                if (val === 15) {
                    birth = "19" + iden.substring(6, 8) + "-" + iden.substring(8, 10) + "-" + iden.substring(10, 12);
                }
                if(sex%2 === 0) {
                    sex = 2
                }
                else{
                    sex = 1
                }
                this.addform.birthDate = birth;
                this.addform.sex =sex;

            },

            //搜索
            search() {
                this.areaCodeObj = "";
                if (this.areaCodess.length == 0){
                    this.areaCodeObj = this.userDisplay.areaCode;
                } else {
                    for (let e of this.areaCodess) {
                        this.areaCodeObj = e;
                    }
                }
                this.handleQuery();
            },

            //导出数据
            exported() {
                this.exportDatas.name = this.query.name;
                exportXls(this.exportDatas).then(response => {
                    const blob = new Blob([response], {type: 'application/vnd.ms-excel'});
                    const fileName = '平安志愿者.xlsx';
                    if (window.navigator && window.navigator.msSaveOrOpenBlob) {
                        navigator.msSaveBlob(blob, fileName)
                    } else {
                        const a = document.createElement('a');
                        a.href = URL.createObjectURL(blob);
                        a.download = fileName;
                        a.style.display = 'none';
                        document.body.appendChild(a);
                        a.click();
                        URL.revokeObjectURL(a.href);
                        document.body.removeChild(a)
                    }
                }).catch((error) => {
                    this.$message.error(error)
                })
            },

            //志愿者新增弹窗
            Added() {
                this.addVisible = true;
            },
            //确认志愿者新增
            addVolunteer(obj) {
                this.$refs[obj].validate(valid => {
                    if (valid) {
                        for (let e of this.addform.areaCodes) {
                            this.addform.areaCode = e;
                        }
                        create(this.addform).then(response => {
                            this.$message.success("新增成功");
                            this.addVisible = false;
                            this.getData();
                        });
                    } else {
                        return false;
                    }
                });

            },

            //关闭弹框重置表单
            closeReset(type) {
                if (type) {
                    this.$refs[type].resetFields();
                }
            },
            //取消弹框重置表单
            cancelReset(formName) {
                this.$nextTick(() => {
                    this.$refs[formName].resetFields();
                });
                this.addVisible = false
            },
            //编辑志愿者数据
            handleEdit(index, row) {
                this.id = row.id;
                getById(this.id).then(response => {
                    this.form = response.data;
                    this.areaCodeses = [];
                    this.form.areaName = row.areaName;
                    var str = row.areaCode;
                    var arr = [4, 6, 9, 12];
                    for (var i = 0; i < 4; i++) {
                        this.areaCodeses[i] = str.substring(0, arr[i]);
                    }
                    this.editVisible = true;
                })
            },

            //确定编辑
            editVolunteer() {
                for (let e of this.areaCodeses) {
                    this.form.areaCode = e;
                }
                update(this.form).then(response => {
                    this.editVisible = false;
                    this.$message.success("编辑成功");
                    this.getData();
                })
            },

            //查看志愿者
            handleLook(index, row) {
                this.id = row.id;
                getById(this.id).then(response => {
                    this.see = response.data;
                    this.see.politicalAppearanceType = row.politicalAppearanceType;
                    this.see.areaName = row.areaName;
                    // this.see.userTypNamee = row.userTypeName;
                    this.see.sexType = row.sexType;
                    console.log(this.see);
                    this.lookVisible = true;
                })
            },

            //删除志愿者
            handleDelete(index, row) {
                this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.idx = index;
                        this.id = row.id;
                        deleteById(this.id).then(response => {
                            this.$message.success("删除成功");
                            this.getData();
                        });
                    })
                    .catch(() => {

                    });
            },

            //批量删除
            deletePage() {
                if (this.multipleSelection.length) {
                    this.ids = this.multipleSelection.map(item => item.id).join(",");
                    this.$confirm("此操作将永久删除选中数据, 是否继续?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    })
                        .then(() => {
                            deleteByIds(this.ids).then(response => {
                                this.$message.success("删除成功");
                                this.multipleSelection = [];
                                this.getData();
                            });
                        })
                        .catch(() => {
                        });
                } else {
                    this.$message({
                        message: (`未选中数据`),
                        type: "warning"
                    });
                }

            },

            //获取选中行数据
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },

            //获取行政区划数据
            AreaCodeQuery(userDisplay) {
                const userProp = this.userDisplay;
                getAreaTree({pid: userProp.areaCode, type: 1, hasSelf: "true"})
                    .then(response => {
                        this.areaCodeOptions = response.data;
                    })
            },

            //获取人物性别
            sexQuery() {
                getDict("4")
                    .then(response => {
                        this.sexValueArr = response.data['4'];
                    })
            },

            //获取政治面貌
            politicalQuery() {
                getDict("1")
                    .then(response => {
                        this.politicalValueArr = response.data['1'];
                    })
            },


            //初始化字典数据
            initDictData() {
                this.projectTypeArr = this.dictArrayObj.projectValue; //47项目类型
                this.projectLevelArr = this.dictArrayObj.ProjectLevelValue; //48项目等级
                this.peopleValueArr = this.dictArrayObj.peopleValue; //49人物身份
                this.statusValueArr = this.dictArrayObj.statusValue; //50数据状态
            },
        }
    }
</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }

    .orange {
        color: orange;
    }

    .green {
        color: green;
    }

    .strightline {
        color: #eeeeee;
    }

    .table /deep/ .el-dialog__header {
        background-color: #b405ec;
        padding: 10px 20px;
    }

    .table /deep/ .el-dialog__title {
        color: #fff;
    }

    .table /deep/ .el-dialog__headerbtn .el-dialog__close {
        color: #fff;
    }

    .table /deep/ .el-dialog__headerbtn {
        top: 12px;
    }

    .lookForm /deep/ .el-form-item__label {
        text-align: right;
    }

    .lookForm /deep/ .el-form-item__conten {
        text-align: left;
    }

    .lookForm /deep/ .el-row {
        margin-bottom: 0;
    }

    .table /deep/ .el-table th {
        background-color: #007cff;
    }

    .table /deep/ .el-table th > .cell {
        color: #fff;
    }

    .table /deep/ .el-table .sort-caret.ascending {
        border-bottom-color: #ffffff;
    }

    .table /deep/ .el-table .sort-caret.descending {
        border-top-color: #fff;
    }
</style>
