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
                <el-form-item label="地点：" prop="address" class="address-box">
                  <el-input clearable v-model="basicForm.detailedAddress"></el-input>
                  <el-button @click="getAddress" class="get-address">
                    <i class="el-icon-plus"></i>
                    <span>获取地点</span>
                  </el-button>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <photo :getData="getData" :submitType="submitType" ref="imgUpload"></photo>
              </el-col>
            </el-row>
            <el-row>
              <el-form-item label="内容" prop="content">
                <!-- 工具栏容器 -->
                <div class="ckeditor">
                  <div id="toolbar-container"></div>
                  <!-- 编辑器容器 -->
                  <div id="editor"></div>
                </div>
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
              <div class="schedule-top">
                <div class="schedule-title">日程安排-{{index+1}}</div>
                <div @click="removeModel(scheduleModel)" class="rtt">删除</div>
              </div>
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
                  <!--:file-list="showList[index].fileArr"-->
                  <div @mouseover="getImageTypeIndex(index)">
                    <el-upload
                      :on-remove="fileRemove"
                      :before-upload="beforeAvatarUpload"
                      :file-list="scheduleModel.showbasicAnnexes"
                      :http-request="uploadServer"
                      action="none"
                      class="upload-demo"
                      multipl
                    >
                      <el-button size="small" type="primary">上传课件</el-button>
                      <div class="el-upload__tip" slot="tip">支持上传doc/ppt/excel/rar/zip文件，大小不超过10M</div>
                    </el-upload>
                  </div>
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
    <!--地图弹窗-->
    <el-dialog :visible.sync="addressVisible" title="地图" width="60%">
      <div>
        <div style="margin-bottom: 10px;">
          <el-input style="width: 300px" clearable placeholder="请输入地点名称" v-model="addressname">
            <el-button
              :loading="loading"
              @click="searchKeyword"
              icon="el-icon-search"
              slot="append"
            ></el-button>
          </el-input>
          <el-button style="float: right" @click="addressVisible = false">取 消</el-button>
          <el-button
            style="float: right;margin-right: 10px"
            @click="enterAddress"
            type="primary"
          >确 定</el-button>
          <!--<input id="keyword" type="textbox" value />-->
          <!--<el-button @click="searchKeyword" type="text" value="search">搜索</el-button>-->
        </div>
        <div id="container2"></div>
      </div>
      <!--<span class="dialog-footer" slot="footer">-->
      <!--<el-button @click="addressVisible = false">取 消</el-button>-->
      <!--<el-button @click="enterAddress" type="primary">确 定</el-button>-->
      <!--</span>-->
    </el-dialog>
  </div>
</template>

<script>
import { update, getById } from "@/api/hqgj/training";
import {
  getUser,
  lecturerMechanism,
  lecturerPage,
  fileUpload,
  fileUpImg
} from "@/api/sys";
import EditorBar from "../../publicTemplate/wangEnduit";
import photo from "../../publicTemplate/photo";
import CKEditor from "@ckeditor/ckeditor5-build-decoupled-document";
import "@ckeditor/ckeditor5-build-decoupled-document/build/translations/zh-cn";
export default {
  components: { EditorBar, photo },
  name: "edit",
  data() {
    return {
      addressname: "",
      basicAnnexesArr: [],
      //附件列表
      filelist: [],
      filelistArr: [],
      uploadfileindex: "", //上传标识
      address: "", //详细地址
      latitude: [], //经纬度
      markers: [],
      searchService: [],
      loading: false, //laoding加载状态
      geocoder: [],
      addressVisible: false,
      tabsName: "first", //选项卡展示第几个
      noClickTabs: true, //是否可以点击选项卡
      isShow: true, //是否显示
      getData: {}, //编辑数据
      submitType: "edit", //类型
      disabled: true, //机构是否禁止选择
      editor: "", //编辑器实例
      //基本信息
      basicForm: {
        corpName: "", //机构名称
        orgId: "", //机构ID
        title: "", //标题
        cultivatePersonNumber: "", //人数
        detailedAddress: "", //地点
        startLng: "", //地点经度
        startLat: "", //地点纬度
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
          { validator: this.globalMethods.checkIntegerP, trigger: "blur" }
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
        lecturerId: "", //讲师Id
        lecturerName: "", //讲师Name
        timeArr: [], //时间
        content: "", //简介
        basicAnnexes: [], //课件
        scheduleModels: [] //日程数组
      },
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
    this.load();
    this.loadUser();
  },
  mounted() {
    this.initCKEditor();
  },
  methods: {
    //富文本编辑器
    initCKEditor() {
      class myUploadLoader {
        constructor(loader) {
          this.loader = loader;
        }
        upload() {
          //重置上传路径
          return this.loader.file.then(
            file =>
              new Promise((resolve, reject) => {
                let reader = new FileReader();
                reader.addEventListener(
                  "load",
                  function() {
                    this.actionUrls = "/hqgj-portal/www/uploadFileBase";
                    fileUpImg(this.actionUrls, { file: reader.result }).then(
                      res => {
                        debugger;
                        if (res.status == 1) {
                          resolve({
                            default: res.data
                          });
                        } else {
                          reject(err);
                        }
                      }
                    );
                  },
                  false
                );
                reader.readAsDataURL(file);
              })
          );
        }
        abort() {}
      }
      function myUpload(e) {
        // 使用 CKeditor 提供的 API 修改上传适配器
        e.plugins.get("FileRepository").createUploadAdapter = loader =>
          new myUploadLoader(loader);
      }
      CKEditor.create(document.querySelector("#editor"), {
        language: "zh-cn",
        removePlugins: ["MediaEmbed"], //除去视频按钮
        extraPlugins: [myUpload] // 添加自定义图片上传适配插件
      })
        .then(editor => {
          const toolbarContainer = document.querySelector("#toolbar-container");
          toolbarContainer.appendChild(editor.ui.view.toolbar.element);
          this.editor = editor; //将编辑器保存起来，用来随时获取编辑器中的内容等，执行一些操作
          console.log(this.editor);
        })
        .catch(error => {
          console.log(error);
        });
    },
    //获取数据
    load() {
      this.id = this.$route.query.id;
      //请求数据接口
      getById(this.id).then(res => {
        if (res.status == 1) {
          //显示文件列表
          this.basicForm = res.data; //基本信息
          this.getData = res.data; //传递给子组件的数据
          this.editor.setData(this.basicForm.content); //显示编辑器的内容
          //时间数组
          var obj = [];
          obj.push(res.data.startDate); //开始时间
          obj.push(res.data.endDate); //结束时间
          this.basicForm.timeArr = obj;
          //机构数组
          var arrs = {};
          arrs.id = res.data.orgId;
          arrs.name = res.data.orgName;
          this.corpArr.push(arrs);
          //获取机构下面的人员
          lecturerPage(res.data.orgId).then(res => {
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
          var scheduleModelsArr = res.data.scheduleList;
          if (scheduleModelsArr) {
            for (let i = 0; i < scheduleModelsArr.length; i++) {
              var obj = [];
              obj.push(scheduleModelsArr[i].startDate);
              obj.push(scheduleModelsArr[i].endDate);
              scheduleModelsArr[i].timeArr = obj;
            }
            this.scheduleForm = {
              scheduleModels: scheduleModelsArr
            };
          }
          var scheduleModelsArr2 = res.data.scheduleList;
          if (scheduleModelsArr2) {
            for (let i = 0; i < scheduleModelsArr2.length; i++) {
              var obj = [];
              for (
                let j = 0;
                j < scheduleModelsArr2[i].basicAnnexes.length;
                j++
              ) {
                obj.push({
                  name:
                    scheduleModelsArr2[i].basicAnnexes[j].fileName +
                    "." +
                    scheduleModelsArr2[i].basicAnnexes[j].fileURL.substr(
                      scheduleModelsArr2[i].basicAnnexes[j].fileURL.lastIndexOf(
                        "."
                      ) + 1
                    ),
                  url: scheduleModelsArr2[i].basicAnnexes[j].fileURL
                });
              }
              console.log(obj);
              scheduleModelsArr2[i].showbasicAnnexes = obj;
            }
            this.scheduleForm = {
              scheduleModels: scheduleModelsArr2
            };
          }
        } else {
          this.$message({
            message: res.message,
            type: "warning"
          });
        }
      });
    },
    //获取用户信息
    loadUser() {
      getUser().then(res => {
        if (res.status == 1) {
          this.userType = res.data.userType; //1律师服务 2会计师服务 3培训机构 4工信局
          this.corpId = res.data.corpId; //服务机构corpId
          if (this.userType == 4) {
            //如果是 工信局请求服务机构数据, 3是培训机构的type值
            var obj = 3;
            this.corpArr = [];
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
          }
        }
      });
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
    //返回
    black() {
      this.$router.push({
        path: "/hqgj/ServicePack/Training"
      });
    },
    //基本信息下一步
    nextClick(formName) {
      //验证子组件图片是否有上传照片
      let photo;
      this.$refs["imgUpload"].$refs["photoForm"].validate(valid => {
        photo = valid;
      });
      if (!photo) {
        this.$message({
          message: "请上传封面照片",
          type: "error"
        });
        return;
      }
      this.basicForm.content = this.editor.getData();
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.tabsName = "second";
          this.isShow = false;
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
      this.basicForm.content = this.editor.getData();
      this.$refs[formName].validate(valid => {
        if (valid) {
          //基本信息
          let serveCultivate = {
            title: this.basicForm.title, //标题
            cultivatePersonNumber: this.basicForm.cultivatePersonNumber, //人数
            startLng: this.basicForm.startLng, //地点经度
            startLat: this.basicForm.startLat, //地点纬度
            detailedAddress: this.basicForm.detailedAddress, //详细地址
            fmUrl: this.$refs.imgUpload.photoForm.fmUrl, //封面照片
            orgId: this.basicForm.orgId, //机构ID
            id: this.id, //基本信息表id
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
          update({
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
                .catch(() => {});
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
        lecturerId: "", //讲师Id
        lecturerName: "", //讲师Name
        detailedAddress: "", //地点
        timeArr: [], //时间
        content: "", //简介
        basicAnnexes: [], //课件
        key: Date.now()
      });
    },
    //删除日程
    removeModel(item) {
      var index = this.scheduleForm.scheduleModels.indexOf(item);
      if (index !== -1) {
        this.scheduleForm.scheduleModels.splice(index, 1);
      }
    },
    //上传文件
    FileUpload(obj) {
      console.log(obj);
      this.actionUrls = "/hqgj-portal/www/uploadFile";
      fileUpload(obj, this.actionUrls).then(response => {
        if (response.status == 1) {
          this.scheduleForm.scheduleModels[
            this.uploadfileindex
          ].showbasicAnnexes.push({ name: obj.file.name, url: response.data });
          // this.filelistArr.push();
          // this.filelist.push={filelistArrs:this.filelistArr};
          this.uploadSuccess(
            response,
            obj.file,
            this.scheduleForm.scheduleModels[this.uploadfileindex]
              .showbasicAnnexes
          );
        } else {
          this.$message({
            message: response.message,
            type: "warning"
          });
          this.uploadError(
            response,
            obj.file,
            this.filelist[this.uploadfileindex].filelistArr
          );
        }
        return response.status;
      });
    },
    //自定义上传
    uploadServer(param) {
      let obj = {};
      obj.file = param.file;
      this.FileUpload(obj);
      const prom = new Promise((resolve, reject) => {});
      prom.abort = () => {};
      return prom;
      // return true;
    },
    //文件移除
    fileRemove(file, fileList) {
      console.log(
        this.scheduleForm.scheduleModels[this.uploadfileindex].basicAnnexes
      );
      console.log(fileList);
      for (var i = 0; i < fileList.length; i++) {
        this.basicAnnexesArr[i] = {
          fileName: fileList[i].name.substring(
            0,
            fileList[i].name.indexOf(".")
          ),
          fileURL: fileList[i].url
        };
      }
      this.scheduleForm.scheduleModels[
        this.uploadfileindex
      ].basicAnnexes = this.basicAnnexesArr;
      var obj = [];
      for (let j = 0; j < fileList.length; j++) {
        obj.push({
          name: fileList[j].name,
          url: fileList[j].url
        });
        console.log(obj);
      }
      this.scheduleForm.scheduleModels[
        this.uploadfileindex
      ].showbasicAnnexes = obj;
    },
    //文件上传成功
    uploadSuccess(response, file, fileList) {
      console.log(fileList);
      for (var i = 0; i < fileList.length; i++) {
        this.basicAnnexesArr[i] = {
          fileName: fileList[i].name.substring(
            0,
            fileList[i].name.indexOf(".")
          ),
          fileURL: fileList[i].url
        };
      }
      this.scheduleForm.scheduleModels[
        this.uploadfileindex
      ].basicAnnexes = this.basicAnnexesArr;
      var obj = [];
      for (let j = 0; j < fileList.length; j++) {
        obj.push({
          name: fileList[j].name,
          url: fileList[j].url
        });
        console.log(obj);
      }
      this.scheduleForm.scheduleModels[
        this.uploadfileindex
      ].showbasicAnnexes = obj;
    },
    //文件上传失败
    uploadError(response, file, fileList) {
      this.$message.error("上传失败");
    },
    // 上传前对文件的大小的判断
    beforeAvatarUpload(file) {
      let isRightType = /\.xls$|\.xlsx$|\.doc$|\.docx$|\.pptx$|\.ppt$|\.pdf$|\.jpg$|\.rar$|\.zip$/i.test(
        file.name
      );
      const isLt2M = file.size / 1024 / 1024 < 50;
      if (!isRightType) {
        this.$message.warning(
          "上传文件只能是 xls、xlsx、doc、docx 、pptx、ppt、pdf、jpg、zip、rar格式!"
        );
      }
      if (!isLt2M) {
        this.$message.warning("上传模板大小不能超过 10MB!");
      }
      return isRightType && isLt2M;
    },
    //获取上传组件的标识
    getImageTypeIndex: function(index) {
      this.uploadfileindex = index; //先在data里定义下，此处省略定义
      console.log(this.uploadfileindex);
    },
    //地图初始化
    intMap() {
      var that = this;
      var map = new qq.maps.Map(document.getElementById("container2"), {
        center: new qq.maps.LatLng(29.0551, 111.68313),
        zoom: 13
      });
      var info = new qq.maps.InfoWindow({ map: map });
      that.geocoder = new qq.maps.Geocoder({
        complete: function(result) {
          map.setCenter(result.detail.location);
          var marker = new qq.maps.Marker({
            map: map,
            position: result.detail.location
          });
          qq.maps.event.addListener(map, "click", function(event) {
            info.close();
            marker.setMap(null);
          });
          //添加监听事件 当标记被点击了  设置图层
          qq.maps.event.addListener(marker, "click", function() {
            info.open();
            info.setContent(
              '<div style="width:280px;height:100px;">' +
                result.detail.address +
                "</div>"
            );
            info.setPosition(result.detail.location);
          });
          that.address = result.detail.address;
        }
      });
      //添加监听事件   获取鼠标单击事件
      qq.maps.event.addListener(map, "click", function(event) {
        var marker = new qq.maps.Marker({
          position: event.latLng,
          map: map
        });
        qq.maps.event.addListener(map, "click", function(event) {
          info.close();
          marker.setMap(null);
        });
        if (that.markers) {
          for (var e in that.markers) {
            that.markers[e].setMap(null);
          }
        }
        var info = new qq.maps.InfoWindow({ map: map });
        //调用获取位置方法
        that.geocoder.getAddress(marker.position);
        //打印地图信息
        that.latitude = marker.position;
      });
      var latlngBounds = new qq.maps.LatLngBounds();
      //设置Poi检索服务，用于本地检索、周边检索
      that.searchService = new qq.maps.SearchService({
        //设置搜索范围为北京
        location: "常德市武陵区",
        // //设置搜索页码为1
        // pageIndex: 1,
        // //设置每页的结果数为5
        // pageCapacity: 5,
        // //设置展现查询结构到infoDIV上
        // panel: document.getElementById('infoDiv'),
        //设置动扩大检索区域。默认值true，会自动检索指定城市以外区域。
        autoExtend: true,
        //检索成功的回调函数
        complete: function(results) {
          //设置回调函数参数
          var pois = results.detail.pois;
          for (var i = 0, l = pois.length; i < l; i++) {
            var poi = pois[i];
            //扩展边界范围，用来包含搜索到的Poi点
            latlngBounds.extend(poi.latLng);
            var marker = new qq.maps.Marker({
              map: map,
              position: poi.latLng
            });
            marker.setTitle(i + 1);
            that.markers.push(marker);
          }
          //调整地图视野
          map.fitBounds(latlngBounds);
        },
        //若服务请求失败，则运行以下函数
        error: function() {
          alert("出错了。");
        }
      });
    },

    //清除地图上的marker
    clearOverlays(overlays) {
      var overlay;
      while ((overlay = overlays.pop())) {
        overlay.setMap(null);
      }
    },
    //地图搜索
    searchKeyword() {
      var keyword = this.addressname;
      console.log(keyword);
      this.clearOverlays(this.markers);
      //根据输入的城市设置搜索范围
      // searchService.setLocation("北京");
      //根据输入的关键字在搜索范围内检索
      this.searchService.search(keyword);
    },
    //获取地理位置弹窗
    getAddress() {
      this.addressVisible = true;
      clearTimeout(this.timer); //清除延迟执行
      this.timer = setTimeout(() => {
        //设置延迟执行
        this.intMap();
      }, 500);
    },
    //确认传递地址信息
    enterAddress() {
      //地点经度
      this.basicForm.startLng = this.latitude.lng;
      //地点纬度
      this.basicForm.startLat = this.latitude.lat;
      this.basicForm.detailedAddress = this.address;
      this.addressVisible = false;
    }
  }
};
</script>

<style lang="less" scoped>
#container2 {
  min-width: 600px;
  min-height: 400px;
}
.main-box {
  background-color: #fff;
  min-height: 100%;
  /deep/ .el-row {
    margin-bottom: 12px;
  }
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

  .scheduleBox {
    padding: 20px 50px 0 30px;

    .schedule-title {
      font-weight: bold;
      flex: 1;
    }

    .schedule-top {
      width: 100%;
      display: flex;
      align-items: center;
      line-height: 30px;
      border-bottom: 1px dotted #ddd;
      margin-bottom: 30px;
      padding-bottom: 20px;
    }

    .rtt {
      float: right;
      background-color: #e8f4ff;
      text-align: center;
      padding: 0 15px;
      color: #1890ff;
      font-size: 13px;
      height: 30px;
      cursor: pointer;
    }
  }

  .noClick /deep/ .el-tabs__item {
    pointer-events: none;
    cursor: default;
  }

  .formBox {
    padding-right: 50px;

    /deep/ .el-form-item {
      width: 100%;
    }
    /deep/ .el-select {
      width: 100%;
    }
    /deep/ .el-input--medium .el-input__inner {
      padding-right: 80px;
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
    .address-box {
      position: relative;
      /deep/ .el-form-item__content {
        padding-left: 140px;
      }
      /deep/ .el-input--medium .el-input__inner {
        padding-right: 20px;
      }
      .get-address {
        position: absolute;
        left: 0;
        top: 4px;
      }
    }
  }
  .isError {
    /deep/ .el-form-item__error {
      display: none;
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
  .ckeditor {
    width: 100%;
    border: 1px solid #dcdfe6;
    /deep/ .ck.ck-toolbar {
      border: none;
      border-bottom: 1px solid #dcdfe6;
      background-color: #f5f7fa;
    }
    /deep/ .ck-editor__editable {
      height: 300px;
      overflow-y: auto;
    }
  }
  .ck.ck-editor__editable_inline > :last-child {
    margin: 0 !important;
  }
  .ck.ck-editor__editable_inline > :first-child {
    margin: 0 !important;
  }
}
</style>
