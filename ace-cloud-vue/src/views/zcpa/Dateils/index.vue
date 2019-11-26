<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-row>
                    <el-col :span="13">
                        <el-cascader
                                placeholder="请选择行政区划"
                                v-model="areaCodes"
                                :options="areaCodeOptions"
                                :props="areaCodeProps"
                                clearable
                                change-on-select
                                filterable class="handle-select mr11" style="float: right; width: 160px"/>
                    </el-col>
                    <el-col :span="3">
                        <el-select v-model="query.projectCategory" clearable placeholder="项目类型"
                                   class="handle-select mr11" style="float: right">
                            <el-option
                                    v-for="item in projectTypeArr"
                                    :key="item.code"
                                    :label="item.name"
                                    :value="item.code"
                            ></el-option>
                        </el-select>
                    </el-col>
                    <el-col :span="3">
                        <el-date-picker
                                v-model="query.startTime"
                                type="date"
                                :picker-options="startTime"
                                format="yyyy-MM-dd"
                                value-format="yyyy-MM-dd 00:00:00"
                                placeholder="开始时间"
                                class="handle-input mr10"
                                style="float: right"
                        ></el-date-picker>
                    </el-col>
                    <el-col :span="3">
                        <el-date-picker
                                v-model="query.endTime"
                                type="date"
                                :picker-options="endTime"
                                format="yyyy-MM-dd"
                                value-format="yyyy-MM-dd 23:59:59"
                                placeholder="结束时间"
                                class="handle-input mr10"
                                style="float: right"
                        ></el-date-picker>
                    </el-col>
                    <el-col :span="2">
                        <el-button type="primary" icon="el-icon-search" @click="search" style="float: right">搜索</el-button>
                    </el-col>
                </el-row>
            </div>
            <el-table :data="list"
                      border
                      max-height="475"
                      class="table"
                      ref="multipleTable"
                      v-loading="loading"
                      @selection-change="handleSelectionChange"
                      @sort-change="handleSort">
                <el-table-column type="index" width="55" label="序号" align="center"></el-table-column>
                <el-table-column prop="projectCategory$" label="项目类型" sortable='custom' width="105">
                </el-table-column>
                <el-table-column prop="activityTitle" :show-overflow-tooltip="true" label="活动标题">
                </el-table-column>
                <el-table-column prop="createUserName" label="平安志愿者" width="100">
                </el-table-column>
                <el-table-column prop="startTime" label="开始时间" sortable='custom' width="163">
                </el-table-column>
                <el-table-column prop="endTime" label="结束时间" sortable='custom' width="163">
                </el-table-column>
                <el-table-column prop="volHour" label="服务时长(h)" align="right" sortable='custom' width="130">
                </el-table-column>
                <el-table-column prop="activityScore" label="评分" align="right" sortable='custom' width="80">
                </el-table-column>
                <el-table-column prop="isNineteen" label="是否19活动" align="center" sortable='custom' width="120">
                    <template slot-scope="scope">
                        <div type="text" v-if="scope.row.isNineteen=='1'">是</div>
                        <div type="text" v-if="scope.row.isNineteen=='0'">否</div>
                    </template>
                </el-table-column>
                <el-table-column prop="isYc" label="是否发现异常" align="center" sortable='custom' width="130">
                    <template slot-scope="scope">
                        <div type="text" v-if="scope.row.isYc=='y'">是</div>
                        <div type="text" v-if="scope.row.isYc!='y'">否</div>
                    </template>
                </el-table-column>
                <el-table-column prop="status" label="状态" sortable='custom' width="80" align="center">
                    <template slot-scope="scope">
                        <div type="text" class="green" v-if="scope.row.status==='1'">通过</div>
                        <div type="text" class="red" v-if="scope.row.status==='2'">未通过</div>
                        <div type="text" class="orange" v-if="scope.row.status==='3'">待审核</div>
                    </template>
                </el-table-column>
                <el-table-column label="操作" fixed="right" width="100" align="right" header-align="center">
                        <template slot-scope="scope">
                            <el-button type="text" height="40" v-if="scope.row.status=='3' && userBtn['/zcpa/actProjectActivity/updateProjectState']"
                                       @click="handleExamine(scope.$index,scope.row)">{{userBtn['/zcpa/actProjectActivity/updateProjectState']}}
                            </el-button>
                            <span class="strightline" v-if="scope.row.status=='3' && userBtn['/zcpa/actProjectActivity/updateProjectState']">|</span>
                            <el-button type="text" @click="handleLook(scope.$index,scope.row)">查看</el-button>
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
        <!-- 审核弹出框 -->
        <el-dialog title="审核" :visible.sync="examineVisible" width="25%">
            <el-form ref="form" :model="updateActivity" label-width="100%">
                <p style="line-height: 50px">1.本次志愿活动内容是否通过？</p>
                <el-radio-group v-model="updateActivity.status" style="margin-left: 20px">
                    <el-radio @click.native.prevent="clickitem1(1)" :label="1">通过</el-radio>
                    <el-radio @click.native.prevent="clickitem1(2)" :label="2">不通过</el-radio>
                </el-radio-group>
                <div>
                    <!-- -->
                    <p style="line-height: 50px">2.请对本次志愿活动进行评分</p>
                    <el-radio-group :disabled="this.updateActivity.status=='2'" v-model="updateActivity.activityScore"
                                    style="margin-left: 20px">
                        <el-radio @click.native.prevent="clickitem2(1)" :label="1">1</el-radio>
                        <el-radio @click.native.prevent="clickitem2(2)" :label="2">2</el-radio>
                        <el-radio @click.native.prevent="clickitem2(3)" :label="3">3</el-radio>
                        <el-radio @click.native.prevent="clickitem2(4)" :label="4">4</el-radio>
                        <el-radio @click.native.prevent="clickitem2(5)" :label="5">5</el-radio>
                    </el-radio-group>
                </div>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="examineVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveExamine()">确 定</el-button>
            </span>
        </el-dialog>
        <!--查看弹出框-->
        <el-dialog title="查看活动详情" :visible.sync="lookVisible" width="60%">
            <div class="el-dialog-div">
                <el-form  content="100%">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="项目名称:">
                                <span>{{see.projectName}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="项目类型:">
                                <span>{{see.projectCategory$}}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="活动标题:">
                                <span>{{see.activityTitle}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="提交日期:">
                                <span>{{see.createDate}}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="平安志愿者:">
                                <span>{{see.createUserName}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="所属社会组织:">
                                <span>未加入社会组织</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="被服务对象:">
                                <span v-if="see.projectCategory == '1'">{{see.personName}}</span>
                                <span v-if="see.projectCategory == '2'">{{see.schoolName}}</span>
                                <span v-if="see.projectCategory != '2'&& see.projectCategory != '1'">无</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="所属网格:">
                                <span>{{see.areaName}}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="活动开始时间:">
                                <span>{{see.startTime}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="活动结束时间:">
                                <span>{{see.endTime}}</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="是否19活动:">
                                <span v-if="see.isNineteen == '1'">是</span>
                                <span v-if="see.isNineteen == '0'">否</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="活动积分:">
                                <span>{{see.activityJiFen}}</span>
                                <span v-if="see.activityJiFen == null">无</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="活动时长(h):">
                                <span>{{see.volHour}}</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="状态:">
                                <span v-if="see.status == '1'" class="green">通过</span>
                                <span v-if="see.status == '2'" class="red">未通过</span>
                                <span v-if="see.status == '3'" class="orange">待审核</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-form-item label="志愿服务描述:">
                        <span style="word-wrap: break-word;word-break: break-all;">{{see.content}}</span>
                    </el-form-item>
                    <el-form-item label="附件:">
                        <ul style="padding-left: 42px!important">
                            <li style="list-style: none;display: inline;">
                                <video-player v-if="see.typeDetail == 'video'"
                                              style="width: 70%"
                                              class="video-player vjs-custom-skin"
                                              ref="videoPlayer"
                                              :playsinline="true"
                                              :options="playerOptions"
                                ></video-player>
                                <viewer v-else="see.typeDetail == 'video'"  :images="see.activitieDetail">
                                    <img v-for="attach in see.activitieDetail"   :src="attach.fileurl" :key="attach.fileurl" class="img">
                                </viewer>
                                <!--<i @click="Preview(see.activitieDetail)"><img :src="attach.fileurl" alt="" class="img"></i>-->
                            </li>
                        </ul>
                    </el-form-item>
                    <el-row>
                        <h4>是否有可疑迹象</h4>
                    </el-row>
                    <div>
                        <div v-if="see.isYc == 'y'">
                            <el-form-item label="异常描述:">
                                <span style="word-wrap: break-word;word-break: break-all;">{{see.ycDesc}}</span>
                            </el-form-item>
                            <el-form-item label="异常附件:">
                                <ul style="padding-left: 69px!important">
                                    <li style="list-style: none;display: inline;">
                                        <video-player v-if="see.typeAbnormal == 'video'"
                                                      style="width: 70%"
                                                      class="video-player vjs-custom-skin"
                                                      ref="videoPlayer"
                                                      :playsinline="true"
                                                      :options="playerOptions"
                                        ></video-player>
                                        <viewer v-else="see.typeAbnormal == 'video'" :images="see.activitieAbnormal">
                                            <img v-for="attach in see.activitieAbnormal" :src="attach.fileurl" :key="attach.fileurl" class="img">
                                        </viewer>
                                        <!--<i @click="Preview(attach.fileurl)"><img :src="attach.fileurl" alt="" class="img"></i>-->
                                    </li>
                                </ul>
                            </el-form-item>
                        </div>
                        <div v-if="see.isYc != 'y'">
                            <el-row>
                                <h5 align="center">未发现可疑迹象</h5>
                            </el-row>
                        </div>
                    </div>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="lookVisible = false">关闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import {page, getById, updateProjectState} from '@/api/zcpa/actProjectActivity';
    import {getAreaTree, getDict} from "@/api/sys";
    import {getAreaCode} from "@/api/zcpa/baseVolunteer";
    import {mapGetters} from "vuex";

    export default {
        name: 'Dateils',
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
                userDisplay:{
                    areaCode:""
                },
                tableData: [],
                currentPage: 1, //初始页
                pagesize: 10, //  每页的数据
                total: 0,
                list: [],
                see: {},
                // unitImageUrl: "", //显示的图片
                projectTypeArr: [], //项目类型
                multipleSelection: [],
                lookVisible: false,
                examineVisible: false,
                delVisible: false,
                loading: false,
                areaCodes: [],
                areaCodeOptions: [], //行政区划
                examine: {},
                areaCodeProps: {
                    value: "id",
                    label: "text"
                },
                query: {
                    startTime: "",
                    endTime: "",
                    // areaCode: "",
                    projectCategory: ""
                },
                // query: { projectCategory: ""},
                updateActivity: {
                    status: 1,
                    activityScore: 5,
                },
                form: {
                    startTime: "",
                    nineteenJiFen: "",
                    orgId: "",
                    personName: "",
                    areaCode: "",
                    isYc: "",
                    endTime: "",
                    content: "",
                    fileURL: ""
                },
                idx: -1,
                // 开始时间
                startTime: {
                    disabledDate: time => {
                        if (this.query.endTime) {
                            return (
                                time.getTime() > new Date(this.query.endTime).getTime()
                            )
                        }
                    }
                },
                // 结束时间
                endTime: {
                    disabledDate: time => {
                        if (this.query.startTime) {
                            return (
                                // time.getTime() < Date.now()- 8.64e7 ||
                                time.getTime() < new Date(this.query.startTime).getTime()
                            )
                        }
                    }
                },
            }
        },
        created() {
            this.getinformation();
            // this.getData();
            // this.AreaCodeQuery();
            this.initDictData();
        },
        computed: {
            ...mapGetters(["dictArrayObj","userBtn"])
        },
        methods: {
            //获取账号信息
            getinformation(){
                this.loading = false;
                getAreaCode().then(response => {
                    if (response.status == 1){
                        this.loading = true;
                        this.userDisplay = response.data;
                        this.areaCodeObj = this.userDisplay.areaCode;
                        this.getData();
                        this.AreaCodeQuery(this.userDisplay);
                    }

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
                    this.query.orderBy = column.prop.replace("$","");
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
            // 获取列表的数据
            getData() {
                this.query = Object.assign(this.query, {
                    areaCode:this.areaCodeObj,
                    pageNum: this.currentPage,
                    pageSize: this.pagesize,
                    totalRecord: this.total
                });
                this.loading = true;
                page(this.query).then(response => {
                    this.loading = false;
                    this.list = this.formatDataList(response.rows);
                    this.total = response.total;
                })
            },
            //翻译项目类别
            formatDataList(data) {
                var projectType = this.projectTypeArr;
                for (var i = 0; i < data.length; i++) {
                    for (var y = 0; y < projectType.length; y++) {
                        if (data[i].projectCategory == projectType[y].code) {
                            data[i].projectCategory$ = projectType[y].name;
                        }
                    }
                }
                return data;
            },

            //搜索
            search() {
                this.areaCodeObj = "";
                if (this.areaCodes.length == 0){
                    // console.log(1111);
                    this.areaCodeObj = this.userDisplay.areaCode;
                } else {
                    for (let e of this.areaCodes) {
                        this.areaCodeObj = e;
                    }
                }

                this.handleQuery();
            },

            //获取行政区划数据
            AreaCodeQuery(userDisplay) {
                // console.log(this.$store.getters.userProp);
                const userProp = this.userDisplay;
                getAreaTree({pid: userProp.areaCode, type: 1, hasSelf: "true"})
                    .then(response => {
                        this.areaCodeOptions = response.data;
                    })
            },
            //获取项目类型数据
            dictQuery() {
                getDict("47")
                    .then(response => {
                        this.dict = response.data['47'];
                    })
            },

            //确定审核
            saveExamine() {
                this.examine.status = this.updateActivity.status;
                this.examine.activityScore = this.updateActivity.activityScore;
                updateProjectState(this.examine)
                    .then(response => {
                        if (response.status == 1) {
                            this.$message.success(`审核成功`);
                            this.getData();
                        } else {
                            this.$message({
                                message: response.message,
                                type: "warning"
                            });
                        }
                        this.examineVisible = false;
                    })
            },

            //审核弹窗
            handleExamine(index, row) {
                this.updateActivity.status =1;
                this.updateActivity.activityScore=5;
                this.idx = index;
                this.examine = row;
                this.examineVisible = true;

            },
            clickitem1(e) {
                e === this.updateActivity.status ? this.updateActivity.status = '' : this.updateActivity.status = e
            },
            clickitem2(e) {
                e === this.updateActivity.activityScore ? this.updateActivity.activityScore = '' : this.updateActivity.activityScore = e
            },
            //查看
            handleLook(index, row) {
                this.idx = index;
                this.id = row.id;
                getById(this.id)
                    .then(response => {
                        this.see = response.data;
                        this.see.isNineteen= row.isNineteen;
                        this.see.areaName = row.areaName;
                        this.see.projectCategory$ = row.projectCategory$;
                        this.srcType(this.see);
                        // this.see.areaCodeName = row.areaCodeName;
                    })
                console.log(this.see);
                this.lookVisible = true;
            },
            //判断资源类型
            srcType(data){
                if (data.activitieDetail.length > 0){
                    var indexs = data.activitieDetail[0].fileurl.lastIndexOf(".");
                    var exts = data.activitieDetail[0].fileurl.substr(indexs+1);
                    if (exts == "mp4" || exts == "MP4" ||exts == "MOV"){
                        data.typeDetail = "video";
                        this.playerOptions.sources[0].src = data.activitieDetail[0].fileurl;
                    }else {
                        data.typeDetail = "";
                    }
                }
                if (data.activitieAbnormal.length > 0){
                    var index = data.activitieAbnormal[0].fileurl.lastIndexOf(".");
                    var ext = data.activitieAbnormal[0].fileurl.substr(index+1);
                    if (ext == "mp4" || ext == "MP4" ||ext == "MOV"){
                        data.typeAbnormal = "video";
                        this.playerOptions.sources[0].src = data.activitieAbnormal[0].fileurl;
                    }else {
                        data.typeAbnormal = "";
                    }
                }
            },

            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            changeProjectType() {},
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

    .el-form /deep/ .el-form-item__label {
        text-align: left;
    }

    .el-form /deep/ .el-form-item__conten {
        text-align: left;
    }

    .el-form /deep/ .el-row {
        margin-bottom: 0;
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

    .orange {
        color: orange;
    }

    .img {
        width: 12%!important;
        height: 130px;
        vertical-align: top;
        margin-right: 20px;
        margin-bottom: 15px;
    }

    .single {
        margin-left: 10px;
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
    .el-dialog-div{
        max-height: 70vh;
        overflow: auto;
    }
</style>
