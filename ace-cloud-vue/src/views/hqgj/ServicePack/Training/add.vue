<template>
    <div class="main-box">
        <el-tabs :class="{noClick:noClickTabs}" v-model="tabsName">
            <el-tab-pane label="基本信息" name="first">
                <div class="content-box">
                    <div class="title">基本信息</div>
                    <el-form
                            :inline="true"
                            :model="basicForm"
                            :rules="basicRules"
                            class="formBox"
                            label-width="120px"
                            ref="basicForm"
                    >
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="机构：" prop="orgId">
                                    <el-select
                                            :disabled="disabled"
                                            @change="changeOrg"
                                            clearable
                                            placeholder="请选择机构"
                                            v-model="basicForm.orgId"
                                    >
                                        <el-option
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id"
                                                v-for="item in corpArr"
                                        ></el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="标题：" prop="title">
                                    <el-input
                                            clearable
                                            maxlength="50"
                                            placeholder="请输入标题"
                                            show-word-limit
                                            v-model="basicForm.title"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="人数：" prop="cultivatePersonNumber">
                                    <el-input
                                            clearable
                                            placeholder="请输入最多可报名人数"
                                            v-model="basicForm.cultivatePersonNumber"
                                    ></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <el-form-item label="时间：" prop="timeArr">
                                    <el-date-picker
                                            end-placeholder="结束日期"
                                            range-separator="至"
                                            start-placeholder="开始日期"
                                            type="datetimerange"
                                            v-model="basicForm.timeArr"
                                    ></el-date-picker>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="地点：" prop="address">
                                    <el-button @click="getAddress" class="get-address">
                                        <i class="el-icon-plus"></i>获取地点
                                    </el-button>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="12">
                                <photo ref="imgUpload"></photo>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-form-item label="内容" prop="content">
                                <el-input
                                        :rows="8"
                                        placeholder="请输入详细内容"
                                        type="textarea"
                                        v-model="basicForm.content"
                                ></el-input>
                                <!-- <editor-bar v-model="basicForm.content" :isClear="isClear" @change="change"></editor-bar> -->
                            </el-form-item>
                        </el-row>
                    </el-form>
                </div>
            </el-tab-pane>
            <el-tab-pane label="日程安排" name="second">
                <div class="content-box">
                    <div class="title">日程安排</div>
                    <el-form
                            :inline="true"
                            :model="scheduleForm"
                            :rules="scheduleRules"
                            class="formBox scheduleBox"
                            label-width="120px"
                            ref="scheduleForm"
                    >
                        <div
                                :key="scheduleModel.key"
                                class="memberModel"
                                v-for="(scheduleModel,index) in scheduleForm.scheduleModels"
                        >
                            <div class="schedule-title">日程安排-{{index+1}}</div>
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item
                                            :key="scheduleModel.key"
                                            :label="'名称：'"
                                            :prop="'scheduleModels.'+index+'.title'"
                                            :rules="scheduleRules.title"
                                    >
                                        <el-input
                                                maxlength="50"
                                                placeholder="请输入培训名称"
                                                show-word-limit
                                                type="text"
                                                v-model="scheduleModel.title"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item
                                            :key="scheduleModel.key"
                                            :label="'讲师：'"
                                            :prop="'scheduleModels.'+index+'.lecturerId'"
                                            :rules="scheduleRules.lecturerId"
                                    >
                                        <el-select
                                                @change="changeLecturer($event,index)"
                                                clearable
                                                placeholder="请选择讲师"
                                                v-model="scheduleModel.lecturerId"
                                        >
                                            <el-option
                                                    :key="item.id"
                                                    :label="item.name"
                                                    :value="item.id"
                                                    v-for="item in lecturerArr"
                                            ></el-option>
                                        </el-select>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-col :span="12">
                                    <el-form-item
                                            :key="scheduleModel.key"
                                            :label="'地点：'"
                                            :prop="'scheduleModels.'+index+'.detailedAddress'"
                                            :rules="scheduleRules.detailedAddress"
                                    >
                                        <el-input
                                                maxlength="50"
                                                placeholder="请输入详细地址"
                                                show-word-limit
                                                type="text"
                                                v-model="scheduleModel.detailedAddress"
                                        ></el-input>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item
                                            :key="scheduleModel.key"
                                            :label="'时间：'"
                                            :prop="'scheduleModels.'+index+'.timeArr'"
                                            :rules="scheduleRules.timeArr"
                                    >
                                        <el-date-picker
                                                end-placeholder="结束日期"
                                                range-separator="至"
                                                start-placeholder="开始日期"
                                                type="datetimerange"
                                                v-model="scheduleModel.timeArr"
                                        ></el-date-picker>
                                    </el-form-item>
                                </el-col>
                            </el-row>
                            <el-row>
                                <el-form-item
                                        :key="scheduleModel.key"
                                        :label="'简介：'"
                                        :prop="'scheduleModels.'+index+'.content'"
                                        :rules="scheduleRules.content"
                                >
                                    <el-input
                                            :rows="8"
                                            maxlength="200"
                                            placeholder="简要概述培训内容，不超过200字"
                                            show-word-limit
                                            type="textarea"
                                            v-model="scheduleModel.content"
                                    ></el-input>
                                </el-form-item>
                            </el-row>
                            <el-row>
                                <el-form-item :label="'课件：'" :prop="'scheduleModels.'+index+'.courseware'">
                                    <el-upload
                                            :before-upload="beforeAvatarUpload"
                                            :file-list="fileList"
                                            :http-request="uploadServer"
                                            :on-error="uploadError"
                                            :on-success="uploadSuccess"
                                            action="none"
                                            class="upload-demo"
                                            multiple
                                    >
                                        <el-button size="small" type="primary">上传课件</el-button>
                                        <div class="el-upload__tip" slot="tip">支持上传doc/ppt/excel/rar/zip文件，大小不超过10M
                                        </div>
                                    </el-upload>
                                </el-form-item>
                            </el-row>
                        </div>
                        <el-button
                                @click="addSchedule"
                                style="margin-top:20px;margin-left:120px;"
                                type="primary"
                        >
                            <i class="el-icon-plus"></i>添加日程
                        </el-button>
                    </el-form>
                </div>
            </el-tab-pane>
        </el-tabs>
        <!-- 底部按钮 -->
        <div class="footer">
            <div class="footer-flex">
                <div v-if="isShow">
                    <el-button @click="black">取消</el-button>
                    <el-button @click="nextClick('basicForm')" type="primary">下一步</el-button>
                </div>
                <div v-if="!isShow">
                    <el-button @click="prevClick">上一步</el-button>
                    <el-button @click="totalSubmit('scheduleForm')" type="primary">提交</el-button>
                </div>
            </div>
        </div>
        <el-dialog
                :visible.sync="addressVisible"
                title="提示"
                width="30%">

                  <span class="dialog-footer" slot="footer">
    <el-button @click="addressVisible = false">取 消</el-button>
    <el-button @click="addressVisible = false" type="primary">确 定</el-button>
                </span>
        </el-dialog>
    </div>

</template>

<script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77">
    import {create} from "@/api/hqgj/training";
    import {getUser, lecturerMechanism, lecturerPage,fileUpload} from "@/api/sys";
    import EditorBar from "../../publicTemplate/wangEnduit";
    import photo from "../../publicTemplate/photo";
    export default {
        components: {EditorBar, photo},
        name: "add",
        data() {
            return {
                addressVisible: false,
                tabsName: "first", //选项卡展示第几个
                noClickTabs: true, //是否可以点击选项卡
                isShow: true, //是否显示
                disabled: true, //机构是否禁止选择
                //附件列表
                fileList:[],
                //基本信息
                basicForm: {
                    orgName: "", //机构
                    orgId: "", //机构ID
                    title: "", //标题
                    cultivatePersonNumber: "", //人数
                    address: "", //地点
                    content: "", //内容
                    timeArr: [] //基本时间数组
                },
                isClear: false,
                lecturerArr: [], //讲师数组
                corpArr: [], //机构数组
                //基本信息验证
                basicRules: {
                    title: [
                        {
                            required: true,
                            message: "请输入标题,字数在50字以内",
                            trigger: "blur"
                        }
                    ],
                    orgId: [
                        {
                            required: true,
                            message: "请选择服务机构",
                            trigger: "change"
                        }
                    ],
                    timeArr: [
                        {
                            required: true,
                            message: "请选择时间",
                            trigger: "change"
                        }
                    ],
                    cultivatePersonNumber: [
                        {
                            required: true,
                            message: "请输入最多可报名人数",
                            trigger: "blur"
                        },
                        {validator: this.globalMethods.checkIntegerP, trigger: "blur"}
                    ],
                    content: [
                        {
                            required: true,
                            message: "请输入详细内容",
                            trigger: "blur"
                        }
                    ]
                },
                //日程管理
                scheduleForm: {
                    title: "", //名称
                    detailedAddress: "", //地点
                    lecturerId: "", //讲师id
                    lecturerName: "", //讲师name
                    mechanismValue: "", //机构id
                    mechanismName: "", //机构name
                    timeArr: [], //时间
                    content: "", //简介
                    basicAnnexes: [], //课件
                    scheduleModels: [] //日程数组
                },
                //上传的附件列表
                basicAnnexesArr:[],
                //日程管理验证
                scheduleRules: {
                    title: [
                        {
                            required: true,
                            message: "请输入培训名称,字数在50字以内",
                            trigger: "blur"
                        }
                    ],
                    lecturerId: [
                        {
                            required: true,
                            message: "请选择讲师",
                            trigger: "change"
                        }
                    ],
                    mechanismValue: [
                        {
                            required: true,
                            message: "请选择机构",
                            trigger: "change"
                        }
                    ],
                    timeArr: [
                        {
                            required: true,
                            message: "请选择时间",
                            trigger: "change"
                        }
                    ],
                    content: [
                        {
                            required: true,
                            message: "简要概述培训内容，不超过200字",
                            trigger: "blur"
                        }
                    ]
                }
            };
        },
        created() {
            this.loadUser();
        },
        methods: {

            //获取用户信息
            loadUser() {
                getUser().then(res => {
                    if (res.status == 1) {
                        this.userType = res.data.userType; //1律师服务 2会计师服务 3培训机构 4工信局
                        this.basicRows = res.data;
                        if (this.userType == 4) {
                            //如果是 工信局请求服务机构数据, 3是培训机构的type值
                            var obj = 3;
                            lecturerMechanism(obj).then(res => {
                                if (res.status == 1) {
                                    this.disabled = false;
                                    this.orgRows = res.rows;
                                    for (let i = 0; i < this.orgRows.length; i++) {
                                        var obj = {};
                                        obj.id = this.orgRows[i].id;
                                        obj.name = this.orgRows[i].orgName;
                                        this.corpArr.push(obj);
                                    }
                                }
                            });
                        } else {
                            //机构数组
                            this.basicForm.orgId = this.basicRows.corpId;
                            var arrs = {};
                            arrs.id = this.basicRows.corpId;
                            arrs.name = this.basicRows.corpName;
                            this.corpArr.push(arrs);
                            lecturerPage(this.basicRows.corpId).then(res => {
                                //根据corpId获取服务机构人员
                                if (res.status == 1) {
                                    this.lecturerRows = res.rows;
                                    for (let i = 0; i < this.lecturerRows.length; i++) {
                                        //循环获取到的数据，依次拿到name 和 id，push给下拉框容器
                                        var obj = {};
                                        obj.name = this.lecturerRows[i].name;
                                        obj.id = this.lecturerRows[i].id;
                                        this.lecturerArr.push(obj);
                                    }
                                }
                            });
                        }
                    }
                });
            },
            //选择讲师
            changeLecturer(value, index) {
                let obj = {};
                obj = this.lecturerArr.find(item => {
                    //这里的userList就是上面遍历的数据源
                    return item.id === value; //筛选出匹配数据
                });
                this.scheduleForm.scheduleModels[index].lecturerId = value;
                this.scheduleForm.scheduleModels[index].lecturerName = obj.name;
            },
            //选择机构
            changeOrg(value) {
                var lecturerCorpId = value;
                let obj = {};
                obj = this.corpArr.find(item => {
                    //这里的userList就是上面遍历的数据源
                    return item.id === value; //筛选出匹配数据
                });
                this.basicForm.orgId = value;
                this.basicForm.orgName = obj.name;
                if (this.userType == 4) {
                    lecturerPage(lecturerCorpId).then(res => {
                        //根据corpId获取服务机构人员
                        if (res.status == 1) {
                            this.lecturerArr = []; //选择机构的时候先清空专家人员信息，调取数据重新渲染该机构下的人员
                            this.lecturerRows = res.rows;
                            for (let i = 0; i < this.lecturerRows.length; i++) {
                                //循环获取到的数据，依次拿到name 和 id，push给下拉框容器
                                var obj = {};
                                obj.name = this.lecturerRows[i].name;
                                obj.id = this.lecturerRows[i].id;
                                this.lecturerArr.push(obj);
                            }
                        }
                    });
                }
            },
            //获取地理位置
            getAddress() {
                this.addressVisible = true;
            },

            //返回
            black() {
                this.$router.push({
                    path: "/hqgj/ServicePack/Training"
                });
            },
            //基本信息下一步
            nextClick(formName) {
                //验证子组件图片是否有上传照片
                this.$refs["imgUpload"].$refs["photoForm"].validate(valid => {
                    if (valid) {
                    } else {
                        return false;
                    }
                });
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.tabsName = "second";
                        this.isShow = false;
                        if (this.scheduleForm.scheduleModels.length == 0) {
                            this.addSchedule();
                        }
                    } else {
                        return false;
                    }
                });
            },
            //上一步
            prevClick() {
                this.tabsName = "first";
                this.isShow = true;
            },
            //提交数据
            totalSubmit(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        //基本信息
                        let serveCultivate = {
                            title: this.basicForm.title, //标题
                            orgId: this.basicForm.orgId, //机构ID
                            orgName: this.basicForm.orgName, //机构名称
                            cultivatePersonNumber: this.basicForm.cultivatePersonNumber, //人数
                            fmUrl: this.$refs.imgUpload.photoForm.fmUrl, //封面照片
                            startDate:
                                this.basicForm.timeArr.length > 0
                                    ? this.basicForm.timeArr[0]
                                    : "", //开始时间
                            endDate:
                                this.basicForm.timeArr.length > 0
                                    ? this.basicForm.timeArr[1]
                                    : "", //结束时间
                            content: this.basicForm.content //内容
                        };
                        //日程
                        //日程开始时间和结束时间
                        for (var i = 0; i < this.scheduleForm.scheduleModels.length; i++) {
                            this.scheduleForm.scheduleModels[i].startDate =
                                this.scheduleForm.scheduleModels[i].timeArr.length > 0
                                    ? this.scheduleForm.scheduleModels[i].timeArr[0]
                                    : ""; //开始时间
                            this.scheduleForm.scheduleModels[i].endDate =
                                this.scheduleForm.scheduleModels[i].timeArr.length > 0
                                    ? this.scheduleForm.scheduleModels[i].timeArr[1]
                                    : ""; //结束时间
                        }
                        let serveCultivateSchedule = this.scheduleForm.scheduleModels;
                        create({
                            serveCultivate: serveCultivate,
                            serveCultivateSchedule: serveCultivateSchedule
                        }).then(res => {
                            if (res.status == 1) {
                                this.$confirm(res.message + " 是否返回？", "提示", {
                                    confirmButtonText: "确定",
                                    cancelButtonText: "取消",
                                    type: "success",
                                    center: true
                                })
                                    .then(() => {
                                        this.black();
                                    })
                                    .catch(() => {
                                    });
                            }
                        });
                    } else {
                        return false;
                    }
                });
            },
            //添加日程
            addSchedule() {
                this.scheduleForm.scheduleModels.push({
                    title: "", //名称
                    detailedAddress: "", //地点
                    lecturerId: "", //讲师id
                    lecturerName: "", //讲师name
                    timeArr: [], //时间
                    mechanismValue: "", //机构id
                    mechanismName: "", //机构name
                    content: "", //简介
                    basicAnnexes: [], //课件
                    key: Date.now()
                });
            },
            FileUpload(obj) {
                console.log(obj);
                this.actionUrls = "/hqgj-portal/www/uploadFile";
                fileUpload(obj, this.actionUrls).then(response => {
                    if (response.status == 1) {
                        this.fileList.push({name: obj.file.name, url: response.data})
                        this.uploadSuccess(response, obj.file, this.fileList);
                    } else {
                        this.$message({
                            message: response.message,
                            type: "warning"
                        });
                        this.uploadError(response, obj.file, this.fileList);
                    }
                    return response.status
                });

            },
            //自定义上传
            uploadServer(param) {
                let obj = {};
                obj.file = param.file;
                this.FileUpload(obj);
                return true
            },
            //文件移除
            fileRemove(file, fileList) {
                for (var i = 0; i < fileList.length; i++) {
                    this.basicAnnexesArr.push({
                        fileName: fileList[i].name.substring(0, fileList[i].name.indexOf(".")),
                        fileURL: fileList[i].url
                    })
                }
                this.scheduleForm.scheduleModels.basicAnnexes=this.basicAnnexesArr;
                this.fileList = fileList;
            },
            //文件上传成功
            uploadSuccess(response, file, fileList) {
                for (var i = 0; i < fileList.length; i++) {
                    this.basicAnnexesArr.push({
                        fileName: fileList[i].name.substring(0, fileList[i].name.indexOf(".")),
                        fileURL: fileList[i].url
                    })
                }
                this.scheduleForm.scheduleModels.basicAnnexes=this.basicAnnexesArr;
                this.fileList = fileList;
            },
            //文件上传失败
            uploadError(response, file, fileList) {
                this.$message.error('上传失败')
            },
            // 上传前对文件的大小的判断
            beforeAvatarUpload(file) {
                const extension = file.name.split('.')[1] === 'xls';
                const extension2 = file.name.split('.')[1] === 'xlsx';
                const extension3 = file.name.split('.')[1] === 'doc';
                const extension4 = file.name.split('.')[1] === 'docx';
                const extension5 = file.name.split('.')[1] === 'pptx';
                const extension6 = file.name.split('.')[1] === 'rar';
                const extension7 = file.name.split('.')[1] === 'zip';
                const isLt2M = file.size / 1024 / 1024 < 10;
                if (!extension && !extension2 && !extension3 && !extension4 && !extension5 && !extension6 && !extension7) {
                    this.$message.warning('上传文件只能是 xls、xlsx、doc、docx 、pdf、jpg、zip、rar格式!');
                }
                if (!isLt2M) {
                    this.$message.warning('上传模板大小不能超过 10MB!')
                }
                return (extension || extension2 || extension3 || extension4 || extension5 || extension6 || extension7) && isLt2M
            },
        }
    };
</script>

<style lang="less" scoped>
    .main-box {
        background-color: #fff;

        /deep/ .el-tabs__nav-scroll {
            padding: 0 20px;
        }

        .content-box {
            padding-top: 20px;
            padding-bottom: 30px;

            .title {
                font-weight: bold;
                font-size: 16px;
                padding-left: 20px;
                margin-bottom: 20px;
            }
        }

        .title::after {
            content: "";
            float: left;
            width: 3px;
            height: 16px;
            background-color: #1890ff;
            margin-right: 8px;
        }

        /*.noClick /deep/ .el-tabs__item {*/
        /*pointer-events: none;*/
        /*cursor: default;*/
        /*}*/

        .formBox {
            padding-right: 50px;

            /deep/ .el-form-item {
                width: 100%;
            }

            /deep/ .el-form-item__content {
                width: calc(~"100% - 120px");
            }

            /deep/ .el-range-editor.el-input__inner {
                width: 100%;
            }

            /deep/ .get-address {
                color: #007cff;
                border-color: #1890ff;
                border-style: dashed;
                background-color: #f1f8ff;

                i {
                    margin: 0 10px 0 0;
                }
            }
        }

        .scheduleBox {
            padding: 20px 50px 0 30px;

            .schedule-title {
                font-weight: bold;
                margin-bottom: 30px;
            }
        }

        .footer {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            border-top: 1px solid #eee;
            height: 60px;
            background-color: #fff;

            .footer-flex {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100%;

                /deep/ .el-button--medium {
                    border-radius: 4px;
                    margin-left: 20px;
                }
            }
        }
    }
</style>
