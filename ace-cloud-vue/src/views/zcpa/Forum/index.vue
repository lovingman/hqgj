<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row :gutter="24">
                    <el-col :span="2" v-if="userBtn['/zcpa/actPosterBlacklist/page']">
                        <el-button type="primary" v-if="userBtn['/zcpa/actPosterBlacklist/page']" @click="handleBlacklist" style="background-color: red;border-color: red;float: left;">{{userBtn['/zcpa/actPosterBlacklist/page']}}</el-button>
                    </el-col>
                    <el-col :span="2" v-else="userBtn['/zcpa/actPosterBlacklist/page']">&nbsp;</el-col>
                    <!--<el-col :span="2">-->
                        <!--<el-button type="primary" @click="handleLookId(null,'all')" style="float: left;">评论管理</el-button>-->
                    <!--</el-col>-->
                    <el-col :span="12">
                        <el-select v-model="query.projectCode" clearable placeholder="项目类型" class="handle-select mr11"
                                   style="float: right;width: 196px">
                            <el-option
                                    v-for="item in projectTypeArr"
                                    v-if="item.code!='1'"
                                    :key="item.code"
                                    :label="item.name"
                                    :value="item.code"
                            ></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="4">
                        <el-select v-model="statusObj" clearable placeholder="论坛状态" class="handle-select mr11"
                                   style="float: right;width: 196px" @change="handleStatus">
                            <el-option
                                    v-for="item in furomStatusValueArr"
                                    :key="item.code"
                                    :label="item.name"
                                    :value="item.code"
                            ></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="4">
                        <el-input v-model="query.title" clearable placeholder="请输入论坛标题" class="handle-input mr10"
                                  style="float: right" @keyup.enter.native="search"></el-input>
                    </el-col>
                    <el-col :span="2">
                        <el-button type="primary" icon="el-icon-search" style="float: right" @click="search">搜索
                        </el-button>
                    </el-col>
                </el-row>
            </div>
            <el-table :data="rows"
                      border
                      max-height="475"
                      class="table"
                      ref="multipleTable"
                      v-loading="loading"
                      @selection-change="handleSelectionChange"
                      @sort-change="handleSort">
                <el-table-column type="index" width="55" label="序号" align="center"></el-table-column>
                <el-table-column prop="title" :show-overflow-tooltip="true" label="论坛标题">
                </el-table-column>
                <el-table-column prop="categoryType" label="项目类型" width="90">
                </el-table-column>
                <el-table-column prop="userName" label="平安志愿者" width="93">
                </el-table-column>
                <el-table-column prop="createDate" label="发布日期" width="165">
                </el-table-column>
                <el-table-column prop="totalForum" label="论坛评论数" align="right" sortable='custom' width="117">
                    <template slot-scope="scope">
                        <el-button type="text" @click="handleLookId(scope.row,'one')">
                            {{scope.row.totalForum}}
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="totalAdmirer" label="论坛点赞数" align="right" sortable='custom' width="117">
                </el-table-column>
                <el-table-column prop="totalReport" label="被举报及次数" sortable='custom' width="130" align="center">
                    <template slot-scope="scope">
                        <div type="text" class="green" v-if="scope.row.totalReport=='0'">无</div>
                        <div type="text" class="red" v-if="scope.row.totalReport!='0'">被举报{{scope.row.totalReport}}次
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="status" label="状态" align="center" width="75">
                    <template slot-scope="scope">
                        <div type="text" class="green" v-if="scope.row.status==='1'">通过</div>
                        <div type="text" class="red" v-if="scope.row.status==='2'">未通过</div>
                        <div type="text" class="orange" v-if="scope.row.status==='3'">待审核</div>
                    </template>
                </el-table-column>
                <el-table-column label="操作" fixed="right" width="100" align="right" header-align="center">
                    <template slot-scope="scope">
                        <!--<el-button type="text" height="40"-->
                                   <!--v-if="scope.row.status=='3'&& scope.row.areaCode.includes(scope.row.userAreaCode) && scope.row.userAreaCode.length <= 9"-->
                                   <!--@click="handleExamine(scope.$index, scope.row)">审核-->
                        <!--</el-button>-->
                        <!--<span class="strightline"-->
                              <!--v-if="scope.row.status=='3' && scope.row.areaCode.includes(scope.row.userAreaCode) && scope.row.userAreaCode.length <= 9">|</span>-->
                        <el-button type="text" v-if="userBtn['/zcpa/actActivityForumposting/deleteByIds']" @click="handleDelete(scope.$index, scope.row)">{{userBtn['/zcpa/actActivityForumposting/deleteByIds']}}</el-button>
                        <span class="strightline" v-if="userBtn['/zcpa/actActivityForumposting/deleteByIds']">|</span>
                        <el-button type="text" @click="handleLook(scope.$index, scope.row)">查看</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="fenye1.currentPage"
                    :page-size="fenye1.pagesize"
                    background
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="fenye1.total">
            </el-pagination>
        </div>
        <!-- 审核弹出框 -->
        <el-dialog title="审核" :visible.sync="examineVisible" width="25%">
            <el-form ref="form" :model="updateStatus" label-width="100%" style="text-align: center">
                <p style="line-height: 40px; font-size: 20px">审核通过后，该论坛将于手机端进行公开发布。</p>
                <el-radio-group v-model="updateStatus.status">
                    <el-radio @click.native.prevent="clickitem(1)" :label="1">通过</el-radio>
                    <el-radio @click.native.prevent="clickitem(2)" :label="2">不通过</el-radio>
                </el-radio-group>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="examineVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveExamine">确 定</el-button>
            </span>
        </el-dialog>

        <!--论坛评论列表-->
        <el-dialog title="论坛评论列表" :visible.sync="lookForumVisible" width="60%" @close="lookForumVisible = false">
            <div class="handle-box">
                <el-row :gutter="24">
                    <el-col :span="2">
                        <el-button type="primary" v-if="userBtn['/zcpa/actActivityForum/deleteByIds']" @click="delPost">{{userBtn['/zcpa/actActivityForum/deleteByIds']}}</el-button>
                    </el-col>
                    <el-col :span="3">
                        <el-button type="primary" v-if="userBtn['/zcpa/actPosterBlacklist/create']" @click="prohibit">{{userBtn['/zcpa/actPosterBlacklist/create']}}</el-button>
                    </el-col>
                </el-row>
            </div>
            <el-table :data="ForumList"
                      border
                      class="table"
                      ref="multipleTable"
                      max-height="470"
                      v-loading="loading"
                      element-loading-text="加载中"
                      element-loading-spinner="el-icon-loading"
                      @selection-change="handleSelectionChange"
                      @sort-change="handleSort1">
                <el-table-column type="selection" width="40" align="center"></el-table-column>
                <el-table-column prop="userName" label="姓名" width="80"></el-table-column>
                <el-table-column prop="reportedNum" label="被举报次数" sortable='custom' width="115"></el-table-column>
                <el-table-column prop="blackStatus" label="是否被禁止评论" width="120">
                    <template slot-scope="scope">
                        <div type="text" class="red" v-if="scope.row.blackStatus=='1'">是</div>
                        <div type="text" class="green" v-if="scope.row.blackStatus!='1'">否</div>
                    </template>
                </el-table-column>
                <el-table-column prop="admirerNum" label="点赞数" sortable='custom' width="90"></el-table-column>
                <el-table-column prop="commenttime" label="评论时间" sortable='custom' width="180"></el-table-column>
                <el-table-column prop="commenttext" label="评论内容" :show-overflow-tooltip="true"
                                 width="200"></el-table-column>
                <el-table-column prop="attachList" label="附件">
                    <template slot-scope="scope">
                        <viewer  :images="scope.row.attachList">
                            <img v-for="attach in scope.row.attachList" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>
                        </viewer>
                        <!--<i  @click="Preview(attach.fileurl)">-->
                            <!--<img :src="attach.fileurl" width="12%" style="line-height: 48px" class="head_pic"/>-->
                        <!--</i>-->
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    @size-change="handleSizeChange1"
                    @current-change="handleCurrentChange1"
                    :current-page="fenye2.currentPage"
                    :page-size="fenye2.pagesize"
                    :total="fenye2.total"
                    background
                    layout="total, sizes, prev, pager, next, jumper"
            ></el-pagination>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="lookForumVisible = false">关闭</el-button>
            </span>
        </el-dialog>

        <!--黑名单列表-->
        <el-dialog title="黑名单列表" :visible.sync="blackListVisible" width="40%" @close="blackListVisible = false">
            <div class="handle-box">
                <el-row :gutter="24">
                    <el-col :span="3">
                        <el-button type="primary" v-if="userBtn['/zcpa/actPosterBlacklist/deleteByIds']" @click="deleteBlacklist">{{userBtn['/zcpa/actPosterBlacklist/deleteByIds']}}</el-button>
                    </el-col>
                </el-row>
            </div>
            <el-table
                    :data="blackData"
                      border
                      class="table"
                      ref="multipleTable"
                      v-loading="loading"
                      element-loading-text="加载中"
                      element-loading-spinner="el-icon-loading"
                      @selection-change="handleSelectionChange"
                    @sort-change="handleSort3">
                <el-table-column type="selection" width="40" align="center"></el-table-column>
                <el-table-column prop="userName" label="姓名" width="120"></el-table-column>
                <el-table-column prop="createDate" label="被禁止日期" sortable='custom'></el-table-column>
            </el-table>
            <el-pagination
                    @size-change="handleSizeChange3"
                    @current-change="handleCurrentChange3"
                    :current-page="fenye4.currentPage"
                    :page-size="fenye4.pagesize"
                    :total="fenye4.total"
                    background
                    layout="total, prev, pager, next"
            ></el-pagination>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="blackListVisible = false">关闭</el-button>
            </span>
        </el-dialog>

        <!--查看弹出框-->
        <el-dialog title="论坛详情" :visible.sync="lookVisible" width="60%" heigh="100%" @close="lookVisible = false">
            <div class="el-dialog-div">
                <el-form label-heigh="100%">
                    <div>
                        <h3>{{see.title}}</h3>
                        <el-row>
                            <el-col :span="8">
                                <el-form-item label="发布日期:">
                                    <span>{{see.createDate}}</span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="5">
                                <el-form-item label="平安志愿者:">
                                    <span>{{see.userName}}</span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <ul style="padding-left: 0px!important">
                            <li style="list-style: none;display: inline">
                                <video-player v-if="see.type == 'video'"  class="video-player vjs-custom-skin" style="width: 70%"
                                               ref="videoPlayer"
                                               :playsinline="true"
                                               :options="playerOptions"
                                ></video-player>
                                <viewer v-else="see.type == 'video'" :images="see.actActivityAttach">
                                    <img  v-for="attach in see.actActivityAttach " :src="attach.fileurl" :key="attach.fileurl" class="imgs">
                                </viewer>
                                <!--<i @click="Preview(attach.fileurl)">-->
                                    <!--<img :src="attach.fileurl" alt="" class="img ">-->
                                <!--</i>-->
                            </li>
                        </ul>
                        <el-row>
                            <div>{{see.content}}</div>
                        </el-row>
                    </div>
                    <div>
                        <ul style="padding-left: 0px!important;">
                            <li v-for="date in lookForumList" style="list-style: none;">
                                <hr style="background-color: #c0c4cc;height:1px;border:none;">
                                <el-row style="margin-top: 10px">
                                    <label :for="date.id">{{date.userName}}</label>
                                    <span style="margin-left: 20px;color: black">{{date.commenttime}}</span>
                                    <span style="margin-left: 20px;color: red" v-if="date.reportedNum>0">被举报{{date.reportedNum}}次</span>

                                    <span v-if="date.blackStatus == '1'" style="margin-left: 30px">已禁止该用户评论</span>
                                    <el-row style="margin-top: 20px">
                                        <span style="word-wrap: break-word;word-break: break-all;">{{date.commenttext}}</span>
                                    </el-row>
                                    <viewer  :images="date.attachList">
                                        <img v-for="attach in date.attachList" :src="attach.fileurl" :key="attach.fileurl" style="width:40px;height: 35px"/>
                                    </viewer>
                                    <!--<i v-for="attach in date.attachList" @click="Preview(attach.fileurl)">-->
                                        <!---->
                                        <!--<img :src="attach.fileurl" style="width:40px;height: 35px"/>-->
                                    <!--</i>-->
                                </el-row>
                            </li>
                        </ul>
                    </div>
                </el-form>
            </div>
            <el-pagination
                    v-if="fenye3.total!=0"
                    @size-change="handleSizeChange2"
                    @current-change="handleCurrentChange2"
                    :current-page="fenye3.currentPage"
                    :page-size="fenye3.pagesize"
                    :total="fenye3.total"
                    background
                    layout="total, prev, pager, next"
            ></el-pagination>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="lookVisible = false">关闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import {
        getList,
        updateStatus,
        deleteByIds,
        getForumPostingDetail,
        findForumList,
        deleteRepliesByIds,
        createPerson,
        getBlacklist,
        BlackdeleteByIds,
        actActivityForumpage
    } from '@/api/zcpa/actActivityForumposting';
    import {getAreaTree, getDict} from "@/api/sys";
    import {getAreaCode} from "@/api/zcpa/baseVolunteer";
    import {mapGetters} from "vuex";

    export default {
        name: 'basetable',
        data() {
            return {
                playerOptions : {
                    playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
                    autoplay: false, //如果true,浏览器准备好时开始回放。
                    muted: false, // 默认情况下将会消除任何音频。
                    loop: false, // 导致视频一结束就重新开始。
                    preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
                    language: 'zh-CN',
                    aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                    fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                    sources: [{
                        type: "video/mp4",//这里的种类支持很多种：基本视频格式、直播、流媒体等，具体可以参看git网址项目
                        src: "" //url地址
                    }],
                    // poster: "../../static/images/test.jpg", //你的封面地址
                    // width: document.documentElement.clientWidth, //播放器宽度
                    notSupportedMessage: '此视频暂无法播放，请稍后再试', //允许覆盖Video.js无法播放媒体源时显示的默认信息。
                    controlBar: {
                        timeDivider: true,
                        durationDisplay: true,
                        remainingTimeDisplay: false,
                        fullscreenToggle: true  //全屏按钮
                    }
                },
                //账号行政区划
                userDisplay: {},
                radio: 2,
                delcom: [],
                tableData: [],
                //基本数据分页
                fenye1: {
                    currentPage: 1, // 列表显示初始页
                    pagesize: 10, //  列表显示每页的数据
                    total: 0,
                },
                //论坛回帖分页
                fenye2: {
                    currentPage: 1, // 列表显示初始页
                    pagesize: 10, //  列表显示每页的数据
                    total: 0,
                },
                //查看论坛详情分页
                fenye3: {
                    currentPage: 1, // 列表显示初始页
                    pagesize: 5, //  列表显示每页的数据
                    total: 0,
                },
                //黑名单分页
                fenye4: {
                    currentPage: 1, // 列表显示初始页
                    pagesize: 5, //  列表显示每页的数据
                    total: 0,
                },
                rows: [], //列表数据
                blackData:[],//黑名单数据
                see: {}, //查看数据
                //选中禁止的评论
                jzxzrft: {
                    actFamilyVolunteer:[
                        {
                            postingId:""
                        }
                    ]
                },
                ForumList: [],//论坛评论列表
                lookForumList: {}, //评论详情列表
                projectTypeArr: [], //项目类型
                furomStatusValueArr:[], //论坛状态
                multipleSelection: [],
                replies: [],
                del_list: [],
                statusObj:"",
                is_search: false,
                addVisible: false,
                lookVisible: false,
                lookForumVisible: false,
                examineVisible: false,
                editVisible: false,
                delVisible: false,
                loading: false,
                blackListVisible:false,
                areaCode:"",
                areaCodes: [], //搜索行政区划
                // areaCodeOptions: [],
                areaCodeProps: {
                    value: "id",
                    label: "text"
                },
                query: {
                    orderBy:"",
                    sord:"",
                    title: "",
                    status:"",
                    projectCode: ""
                },
                query1: {
                    orderBy:"",
                    sord:"",
                    postingId: ""
                },
                query2: {
                    postingId: ""
                },
                query3:{
                    orderBy:"",
                    sord:"",
                },
                updateStatus: {
                    status: 1
                },
                form: {
                    name: '',
                    date: '',
                    address: ''
                },
                idx: -1,
                id: -1
            }
        },
        created() {
            this.getinformation();
            // this.getData();
            // this.AreaCodeQuery();
            this.initDictData();
            this.furomStatusQuery();

        },
        computed: {
            ...mapGetters(["dictArrayObj","userBtn"])
        },
        methods: {

            //获取账号信息
            getinformation() {
                this.loading = true;
                getAreaCode().then(response => {
                    if (response.status == 1){
                        this.userDisplay = response.data;
                        this.getData();
                        this.loading = false;
                    }
                });
            },

            //主表排序
            handleSort(column, prop, order){
                    if (column.order == null || column.prop == null) {
                        this.query.sord = "";
                        this.query.orderBy = "";
                    }else{
                        this.query.sord = column.order.substring(0,column.order.length-6);
                        this.query.orderBy = column.prop.replace("$","");
                    }
                    this.getData();
            },
            //评论列表排序
            handleSort1(column, prop, order){
                    if (column.order == null || column.prop == null) {
                        this.query1.sord = "";
                        this.query1.orderBy = "";
                    }else{
                        this.query1.sord = column.order.substring(0,column.order.length-6);
                        this.query1.orderBy = column.prop.replace("$","");
                    }
                    this.handleLookForum();


            },
            //黑名单排序
            handleSort3(column, prop, order){
                    if (column.order == null || column.prop == null) {
                        this.query3.sord = "";
                        this.query3.orderBy = "";
                    }else{
                        this.query3.sord = column.order.substring(0,column.order.length-6);
                        this.query3.orderBy = column.prop.replace("$","");
                    }
                    this.handleBlacklist();
            },

            handleQuery: function () {
                this.fenye1.currentPage = 1;
                this.getData();
            },
            handleSizeChange: function (size) {
                this.fenye1.pagesize = size;
                //每页下拉显示数据
                this.getData();
            },
            handleCurrentChange: function (currentPage) {
                this.fenye1.currentPage = currentPage;
                //点击第几页
                this.getData();
            },
            // 获取列表的数据
            getData() {
                this.query = Object.assign(this.query, {
                    pageNum: this.fenye1.currentPage,
                    pageSize: this.fenye1.pagesize,
                    totalRecord: this.fenye1.total
                });
                this.loading = true;
                getList(this.query).then(response => {
                    this.loading = false;
                    this.fenye1.total = response.total;
                    this.rows = this.formatDataList(response.rows);
                    this.rows = this.UserAreaCode(response.rows);

                });
            },

            //翻译类型
            formatDataList(data) {
                var projectType = this.projectTypeArr;
                for (var i = 0; i < data.length; i++) {
                    for (var y = 0; y < projectType.length; y++) {
                        if (data[i].projectCode == projectType[y].code) {
                            data[i].categoryType = projectType[y].name;
                        }
                    }
                }
                return data;
            },
            //获取账号区划
            UserAreaCode(data) {
                for (var i = 0; i < data.length; i++) {
                    data[i].userAreaCode = this.userDisplay.areaCode;

                }
                return data;
            },

            //搜索
            search() {
                this.handleQuery();

            },
            clickitem(e) {
                e === this.updateStatus.status ? this.updateStatus.status = '' : this.updateStatus.status = e
            },
            formatter(row, column) {
                return row.address;
            },
            handleAdd() {
                this.addVisible = true;
            },

            //状态搜索框数据
            handleStatus(){
                if (this.statusObj == 0){
                    this.query.status ="";
                }else {
                    this.query.status =this.statusObj;
                }
            },

            // //获取行政区划数据
            // AreaCodeQuery() {
            //     const userProp = this.$store.getters.userProp;
            //     getAreaTree({pid: userProp.areaCode, type: 1, hasSelf: "true"})
            //         .then(response => {
            //             this.areaCodeOptions = response.data;
            //         })
            //
            // },
            //确定审核
            saveExamine() {
                if (this.updateStatus.status == ""){
                    this.$message(`未选择状态`);
                }else {
                    updateStatus(this.id, this.updateStatus.status)
                        .then(response => {
                            if (response.status == 1) {
                                this.$message.success(`审核成功`);
                                this.getData();
                                this.examineVisible = false;
                            } else {
                                this.$message({
                                    message: response.message,
                                    type: "warning"
                                });
                            }
                        })
                }
            },

            //审核弹窗
            handleExamine(index, row) {
                this.idx = index;
                this.id = row.id;
                this.examineVisible = true;

            },
            //查看弹框
            handleLook(index, row) {
                this.idx = index;
                this.id = row.id;
                this.query2.postingId = row.id;
                getForumPostingDetail(this.id)
                    .then(response => {
                        this.see = response.data;
                        this.srcType(this.see);
                    });
                this.handleQuery2();
                // this.handleForumList();
                this.lookVisible = true;
            },
            //判断资源类型
            srcType(data){
                if (data.actActivityAttach.length > 0){
                    var index = data.actActivityAttach[0].fileurl.lastIndexOf(".");
                    var ext = data.actActivityAttach[0].fileurl.substr(index+1);
                    if (ext == "mp4" || ext == "MP4" ||ext == "MOV"){
                        data.type = "video";
                        this.playerOptions.sources[0].src = data.actActivityAttach[0].fileurl;
                    }else {
                        data.type = "";
                    }
                }
            },

            handleQuery2: function () {
                this.fenye3.currentPage = 1;
                this.handleForumList();
            },
            handleSizeChange2: function (size) {
                this.fenye3.pagesize = size;
                //每页下拉显示数据
            },
            handleCurrentChange2: function (currentPage) {
                this.fenye3.currentPage = currentPage;
                //点击第几页
                this.handleForumList();
            },
            handleForumList() {
                this.query2 = Object.assign(this.query2, {
                    pageNum: this.fenye3.currentPage,
                    pageSize: this.fenye3.pagesize,
                    totalRecord: this.fenye3.total
                });
                findForumList(this.query2)
                    .then(response => {
                        this.lookForumList = response.rows;
                        this.fenye3.total = response.total;
                    });
            },
            handleQuery1: function () {
                this.fenye2.currentPage = 1;
                this.handleLookForum();
            },
            handleSizeChange1: function (size) {
                this.fenye2.pagesize = size;
                //每页下拉显示数据
            },
            handleCurrentChange1: function (currentPage) {
                this.fenye2.currentPage = currentPage;
                //点击第几页
                this.handleLookForum();
            },

            //查看论坛回帖详情
            handleLookId(row,word) {
                if (word == "one") {
                    this.id = row.id;
                    this.areaCode = row.areaCode;
                    this.query1.postingId = this.id;
                    this.handleQuery1();
                }
                // if (word == "all"){
                //     this.query5 = Object.assign({
                //         pageNum: this.fenye2.currentPage,
                //         pageSize: this.fenye2.pagesize,
                //         totalRecord: this.fenye2.total
                //     });
                //     actActivityForumpage(this.query5).then(response=>{
                //         this.ForumList = response.rows;
                //         this.fenye2.total = response.total;
                //     })
                //     this.lookForumVisible = true;
                // }
                // this.handleLookForum();
            },
            handleLookForum() {
                this.query1 = Object.assign(this.query1, {
                    pageNum: this.fenye2.currentPage,
                    pageSize: this.fenye2.pagesize,
                    totalRecord: this.fenye2.total
                });
                findForumList(this.query1)
                    .then(response => {
                        this.ForumList = response.rows;
                        this.fenye2.total = response.total;
                    });
                this.lookForumVisible = true;
            },
            //禁止选择人发帖
            prohibit() {
                if (this.multipleSelection.length) {
                    var campusArr = [];
                    for (var i = 0; i < this.multipleSelection.length; i++) {
                        var obj = {};
                        obj.postingId = this.multipleSelection[i].userid;
                        campusArr.push(obj);
                    }
                    this.jzxzrft.actFamilyVolunteer= campusArr;
                    createPerson(this.jzxzrft).then(response => {
                        if (response.status == 1) {
                            this.$message.success(`禁止成功`);
                            this.handleLookForum();
                            // this.multipleSelection = [];
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            });
                        }

                    })
                } else {
                    this.$message({
                        message: (`未选中数据`),
                        type: "warning"
                    });
                }

            },


            //删除选中帖子
            delPost() {
                if (this.multipleSelection.length) {
                    this.ids = this.multipleSelection.map(item => item.id).join(",");
                    deleteRepliesByIds(this.ids).then(response => {
                        if (response.status == 1) {
                            findForumList(this.query1)
                                .then(response => {
                                    if (response.status == 1) {
                                        this.ForumList = response.rows;
                                        this.fenye2.total = response.total;
                                        this.$message.success(`删除成功`);
                                        this.multipleSelection = [];
                                    } else {
                                        this.$message({
                                            message: response.message,
                                            type: "warning"
                                        });
                                    }
                                });
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            });
                        }
                    });
                } else {
                    this.$message({
                        message: (`未选中数据`),
                        type: "warning"
                    });
                }

            },

            handleQuery3: function () {
                this.fenye4.currentPage = 1;
                this.handleBlacklist();
            },
            handleSizeChange3: function (size) {
                this.fenye4.pagesize = size;
                //每页下拉显示数据
            },
            handleCurrentChange3: function (currentPage) {
                this.fenye4.currentPage = currentPage;
                //点击第几页
                this.handleBlacklist();
            },
            //获取黑名单数据
            handleBlacklist(){
                this.query3 = Object.assign(this.query3,{
                    pageNum: this.fenye4.currentPage,
                    pageSize: this.fenye4.pagesize,
                    totalRecord: this.fenye4.total
                });
                getBlacklist(this.query3).then(response=>{
                    this.blackData = response.rows;
                    this.fenye4.total = response.total;
                    this.blackListVisible=true;
                })
            },
            //解除禁止评论
            deleteBlacklist() {
                if (this.multipleSelection.length) {
                    this.ids = this.multipleSelection.map(item => item.id).join(",");
                    BlackdeleteByIds(this.ids).then(response => {
                        if (response.status == 1) {
                            getBlacklist(this.query3)
                                .then(response => {
                                    if (response.status == 1) {
                                        this.blackData = response.rows;
                                        this.fenye4.total = response.total;
                                        findForumList(this.query1)
                                            .then(response => {
                                                if (response.status == 1) {
                                                    this.ForumList = response.rows;
                                                    this.$message.success(`移除成功`);
                                                    this.multipleSelection = [];
                                                } else {
                                                    this.$message({
                                                        message: response.message,
                                                        type: "warning"
                                                    });
                                                }
                                            });
                                    } else {
                                        this.$message({
                                            message: response.message,
                                            type: "warning"
                                        });
                                    }
                                });
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            });
                        }
                    });
                } else {
                    this.$message({
                        message: (`未选中数据`),
                        type: "warning"
                    });
                }

            },
            //删除活动弹窗
            handleDelete(index, row) {
                this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                    .then(() => {
                        this.idx = index;
                        this.ids = row.id;
                        deleteByIds(this.ids).then(response => {
                            this.$message.success("删除成功");
                            this.getData();
                        });
                    })
                    .catch(() => {
                    });
            },

            handleSelectionChange(val) {
                this.multipleSelection = val;
            },

            //获取论坛状态
            furomStatusQuery() {
                getDict("52")
                    .then(response => {
                        this.furomStatusValueArr = response.data['52'];
                    })
            },

            changeProjectType() {
            },
            //初始化字典数据
            initDictData() {
                this.projectTypeArr = this.dictArrayObj.projectValue; //47项目类型
                this.projectLevelArr = this.dictArrayObj.ProjectLevelValue; //48项目等级
                this.peopleValueArr = this.dictArrayObj.peopleValue; //49人物身份
                this.statusValueArr = this.dictArrayObj.statusValue; //50数据状态
            },
            //项目类型
            changeProjectType(value) {
                var obj = {};
                obj = this.projectTypeArr.find(item => {
                    //找到匹配的数据
                    return item.code == value;
                });
                this.addsForm.category = value; //code
                this.addsForm.categoryType = obj.name; //赋值汉字
            }
        }
    }

</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }

    .handle-box {
        text-align: right;
        margin-bottom: 20px;
    }

    .handle-select {
        width: 80%;
    }

    .handle-input {
        width: 80%;
        display: inline-block;
    }

    .head_pic {
        max-height: 20px;
        vertical-align: middle;
    }

    .del-dialog-cnt {
        font-size: 16px;
        text-align: center
    }
    .video-player/deep/.vjs-big-play-button{
        background-color: rgba(0,0,0,0.45);
        font-size: 2.5em;
         border-radius: 50%;
        width: 2.5em !important;
        height: 2.5em !important;
        line-height: 2.5em !important;
        margin-top: -1em !important;
    }

    .table {
        width: 100%;
        font-size: 14px;
    }

    .green {
        color: green;
    }

    .red {
        color: red;
    }

    .imgs {
        width: 30%;
        height: 300px;
        margin-right: 20px;
        margin-bottom: 10px;
        vertical-align: top;
        object-fit: cover;
    }

    .orange {
        color: orange;
    }

    .strightline {
        color: #eeeeee;
    }

    .el-form /deep/ .el-form-item__label {
        text-align: left;
    }

    .el-form /deep/ .el-form-item__conten {
        text-align: left;
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

    .table /deep/ .el-dialog {
        margin-top: 3% !important;
    }

    .el-dialog-div {
        max-height: 70vh;
        overflow: auto;
    }
</style>
