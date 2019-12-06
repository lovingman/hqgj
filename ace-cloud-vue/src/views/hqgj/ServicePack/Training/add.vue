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
                <el-form-item label="人数：" prop="peopleNumber">
                  <el-input v-model="basicForm.peopleNumber" clearable placeholder="请输入最多可报名人数"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="时间：" prop="time">
                  <el-date-picker
                    v-model="basicForm.time"
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
                <el-form-item label="封面：" prop="photo">
                  <el-upload
                    action="https://jsonplaceholder.typicode.com/posts/"
                    list-type="picture-card"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove"
                  >
                    <i class="el-icon-plus"></i>
                    <div class="el-upload__tip" slot="tip">支持jpg.png，大小不超过10M</div>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt />
                  </el-dialog>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-form-item label="内容" prop="content">
                <editor-bar v-model="basicForm.content" :isClear="isClear" @change="change"></editor-bar>
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
                    :prop="'scheduleModels.'+index+'.name'"
                    :rules="scheduleRules.name"
                    :key="scheduleModel.key"
                  >
                    <el-input
                      type="text"
                      maxlength="50"
                      show-word-limit
                      placeholder="请输入培训名称"
                      v-model="scheduleModel.name"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    :label="'讲师：'"
                    :prop="'scheduleModels.'+index+'.lecturer'"
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
                    :prop="'scheduleModels.'+index+'.address'"
                    :rules="scheduleRules.address"
                    :key="scheduleModel.key"
                  >
                    <el-input
                      type="text"
                      maxlength="50"
                      show-word-limit
                      placeholder="请输入详细地址"
                      v-model="scheduleModel.address"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    :label="'时间：'"
                    :prop="'scheduleModels.'+index+'.time'"
                    :rules="scheduleRules.time"
                    :key="scheduleModel.key"
                  >
                    <el-date-picker
                      v-model="scheduleModel.time"
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
                    v-model="scheduleForm.content"
                  ></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item :label="'课件：'" :prop="'scheduleModels.'+index+'.courseware'">
                  <el-upload
                    class="upload-demo"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :on-preview="handlePreview"
                    :on-remove="handleRemove"
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
          <el-button @click="nextClick" type="primary">下一步</el-button>
        </div>
        <div v-if="!isShow">
          <el-button @click="prevClick">上一步</el-button>
          <el-button type="primary">提交</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import EditorBar from "../../publicTemplate/wangEnduit";
export default {
  components: { EditorBar },
  name: "add",
  data() {
    return {
      tabsName: "first", //选项卡展示第几个
      noClickTabs: true, //是否可以点击选项卡
      isShow: true, //是否显示
      dialogVisible: false, //预览弹窗是否显示
      dialogImageUrl: "", //是否显示预览图片
      //基本信息
      basicForm: {
        title: "", //标题
        peopleNumber: "", //人数
        time: "", //时间
        address: "", //地点
        photo: "", //封面
        content: "" //内容
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
        time: [
          {
            required: true,
            message: "请选择开始结束时间",
            trigger: "change"
          }
        ],
        peopleNumber: [
          {
            required: true,
            message: "请输入最多可报名人数",
            trigger: "blur"
          }
        ],
        content: [
          {
            required: true,
            message: "请输入内容",
            trigger: "blur"
          }
        ]
      },
      //日程管理
      scheduleForm: {
        name: "", //名称
        address: "", //地点
        time: "", //时间
        content: "", //简介
        courseware: "", //课件
        scheduleModels: [] //日程数组
      },
      //日程管理验证
      scheduleRules: {
        name: [
          {
            required: true,
            message: "请输入培训名称,字数在50字以内",
            trigger: "blur"
          }
        ],
        time: [
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
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    //返回
    black() {
      this.$router.push({
        path: "/hqgj/ServicePack/Training"
      });
    },
    //基本信息下一步
    nextClick() {
      this.tabsName = "second";
      this.isShow = false;
    },
    //上一步
    prevClick() {
      this.tabsName = "first";
      this.isShow = true;
    },

    //添加日程
    addSchedule() {
      this.scheduleForm.scheduleModels.push({
        name: "", //名称
        lecturer: "", //讲师
        address: "", //地点
        time: "", //时间
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