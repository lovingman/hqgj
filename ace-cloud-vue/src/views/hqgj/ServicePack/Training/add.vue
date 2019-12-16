<template>
  <div class="main-box">
    <el-tabs v-model="tabsName" :class="{noClick:noClickTabs}">
      <el-tab-pane label="基本信息" name="first">
        <div class="content-box">
          <div class="title">基本信息</div>
          <el-form
            :model="basicForm"
            :inline="true"
            :rules="basicRules"
            ref="basicForm"
            label-width="120px"
            class="formBox"
          >
            <el-row>
              <el-col :span="12">
                <el-form-item label="标题：" prop="title">
                  <el-input
                    maxlength="50"
                    v-model="basicForm.title"
                    clearable
                    show-word-limit
                    placeholder="请输入标题"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="人数：" prop="cultivatePersonNumber">
                  <el-input
                    v-model="basicForm.cultivatePersonNumber"
                    clearable
                    placeholder="请输入最多可报名人数"
                  ></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="时间：" prop="timeArr">
                  <el-date-picker
                    v-model="basicForm.timeArr"
                    type="datetimerange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="地点：" prop="address">
                  <el-button class="get-address">
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
                  type="textarea"
                  :rows="8"
                  placeholder="请输入详细内容"
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
            :model="scheduleForm"
            :inline="true"
            :rules="scheduleRules"
            ref="scheduleForm"
            label-width="120px"
            class="formBox scheduleBox"
          >
            <div
              class="memberModel"
              v-for="(scheduleModel,index) in scheduleForm.scheduleModels"
              :key="scheduleModel.key"
            >
              <div class="schedule-title">日程安排-{{index+1}}</div>
              <el-row>
                <el-col :span="12">
                  <el-form-item
                    :label="'名称：'"
                    :prop="'scheduleModels.'+index+'.title'"
                    :rules="scheduleRules.title"
                    :key="scheduleModel.key"
                  >
                    <el-input
                      type="text"
                      maxlength="50"
                      show-word-limit
                      placeholder="请输入培训名称"
                      v-model="scheduleModel.title"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    :label="'讲师：'"
                    :prop="'scheduleModels.'+index+'.lecturerName'"
                    :key="scheduleModel.key"
                  >
                    <el-button class="get-address">
                      <i class="el-icon-plus"></i>选择讲师
                    </el-button>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item
                    :label="'地点：'"
                    :prop="'scheduleModels.'+index+'.detailedAddress'"
                    :rules="scheduleRules.detailedAddress"
                    :key="scheduleModel.key"
                  >
                    <el-input
                      type="text"
                      maxlength="50"
                      show-word-limit
                      placeholder="请输入详细地址"
                      v-model="scheduleModel.detailedAddress"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    :label="'时间：'"
                    :prop="'scheduleModels.'+index+'.timeArr'"
                    :rules="scheduleRules.timeArr"
                    :key="scheduleModel.key"
                  >
                    <el-date-picker
                      v-model="scheduleModel.timeArr"
                      type="datetimerange"
                      range-separator="至"
                      start-placeholder="开始日期"
                      end-placeholder="结束日期"
                    ></el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-form-item
                  :label="'简介：'"
                  :prop="'scheduleModels.'+index+'.content'"
                  :rules="scheduleRules.content"
                  :key="scheduleModel.key"
                >
                  <el-input
                    type="textarea"
                    maxlength="200"
                    show-word-limit
                    :rows="8"
                    placeholder="简要概述培训内容，不超过200字"
                    v-model="scheduleModel.content"
                  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item :label="'课件：'" :prop="'scheduleModels.'+index+'.courseware'">
                  <el-upload
                    class="upload-demo"
                    accept="*, *"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
                    :before-upload="beforeAvatarUpload"
                    multiple
                  >
                    <el-button size="small" type="primary">上传课件</el-button>
                    <div slot="tip" class="el-upload__tip">支持上传doc/ppt/excel/rar/zip文件，大小不超过10M</div>
                  </el-upload>
                </el-form-item>
              </el-row>
            </div>
            <el-button
              type="primary"
              @click="addSchedule"
              style="margin-top:20px;margin-left:120px;"
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
          <el-button type="primary" @click="totalSubmit('scheduleForm')">提交</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { create } from "@/api/hqgj/training";
import EditorBar from "../../publicTemplate/wangEnduit";
import photo from "../../publicTemplate/photo";
export default {
  components: { EditorBar, photo },
  name: "add",
  data() {
    return {
      tabsName: "first", //选项卡展示第几个
      noClickTabs: true, //是否可以点击选项卡
      isShow: true, //是否显示
      //基本信息
      basicForm: {
        title: "", //标题
        cultivatePersonNumber: "", //人数
        address: "", //地点
        content: "", //内容
        timeArr: [] //基本时间数组
      },
      isClear: false,
      //基本信息验证
      basicRules: {
        title: [
          {
            required: true,
            message: "请输入标题,字数在50字以内",
            trigger: "blur"
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
        lecturerName: "", //讲师
        timeArr: [], //时间
        content: "", //简介
        courseware: "", //课件
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
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    beforeAvatarUpload(file) {
      console.log(file);
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
          return;
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
        lecturerName: "", //讲师
        detailedAddress: "", //地点
        timeArr: [], //时间
        content: "", //简介
        courseware: "", //课件
        key: Date.now()
      });
    },
    change(val) {
      console.log(val);
    }
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
  .noClick /deep/ .el-tabs__item {
    pointer-events: none;
    cursor: default;
  }
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