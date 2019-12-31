<template>
  <div class="main-box">
    <el-tabs v-model="tabsName" :class="{noClick:noClickTabs}">
      <el-tab-pane label="基本信息" name="first">
        <div class="content-box">
          <div class="title">基本信息</div>
          <el-form label-width="140px" class="formBox">
            <el-row>
              <el-col :span="12">
                <el-form-item label="培训标题：">
                  <span>{{basicForm.title}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="培训人数：">
                  <span>{{basicForm.cultivatePersonNumber}}</span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="创建人：">
                  <span>{{basicForm.createUserName}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="所属机构：">
                  <span>{{basicForm.orgName}}</span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="开始和截至时间：">
                  <span>{{basicForm.startDate}}</span>
                  <span style="padding: 0 10px;">至</span>
                  <span>{{basicForm.endDate}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="培训地点：">
                  <span></span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="封面：">
                  <span class="img-border">
                    <img :src="basicForm.fmUrl" />
                  </span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-form-item label="内容：">
                <span>{{basicForm.content}}</span>
              </el-form-item>
            </el-row>
          </el-form>
        </div>
      </el-tab-pane>
      <el-tab-pane label="日程安排" name="second">
        <div class="content-box">
          <div class="title">日程安排</div>
          <el-form label-width="120px" class="formBox scheduleBox">
            <div
              class="memberModel"
              v-for="(scheduleModel,index) in scheduleModelList"
              :key="index"
            >
              <div class="schedule-top">
                <div class="schedule-title">日程安排-{{index+1}}</div>
                <div class="rtt" @click="removeModel(scheduleModel)">删除</div>
              </div>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="名称：">
                    <span>{{scheduleModel.title}}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="讲师：">
                    <span>{{scheduleModel.lecturerName}}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="地点：">
                    <span>{{scheduleModel.detailedAddress}}</span>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="时间：">
                    <span>{{scheduleModel.startDate}}</span>
                    <span style="padding:0 10px;">至</span>
                    <span>{{scheduleModel.endDate}}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-form-item label="简介：">
                  <span>{{scheduleModel.content}}</span>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="课件：">
                  <spna v-for="item in scheduleModel.basicAnnexes">{{item.fileName+"."+item.fileURL.substr(item.fileURL.lastIndexOf(".") + 1)}}<br></spna>
                </el-form-item>
              </el-row>
            </div>
          </el-form>
        </div>
      </el-tab-pane>
    </el-tabs>
    <!-- 底部按钮 -->
    <div class="footer">
      <div class="footer-flex">
        <div v-if="isShow">
          <el-button @click="black" type="primary">取消</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getById } from "@/api/hqgj/training";
export default {
  name: "see",
  data() {
    return {
      tabsName: "first", //选项卡展示第几个
      noClickTabs: true, //是否可以点击选项卡
      isShow: true, //是否显示
      submitType: "edit", //类型
      basicForm: {},
      scheduleModelList: {}
    };
  },
  created() {
    this.load();
  },
  methods: {
    //获取数据
    load() {
      this.id = this.$route.query.id;
      //请求数据接口
      getById(this.id).then(res => {
        if (res.status == 1) {
          console.log(res);
          this.basicForm = res.data; //基本信息
          this.scheduleModelList = res.data.scheduleList;
          console.log(this.scheduleModelList);
        }
      });
    },
    //
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
    beforeAvatarUpload() {},
    //返回
    black() {
      this.$router.push({
        path: "/hqgj/ServicePack/Training"
      });
    },
    //基本信息下一步
    nextClick(formName) {
      this.tabsName = "second";
      this.isShow = false;
    },
    //上一步
    prevClick() {
      this.tabsName = "first";
      this.isShow = true;
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

  .formBox {
    padding-right: 50px;
    /deep/ .el-form-item {
      width: 100%;
    }
    // /deep/ .el-form-item__content {
    //   width: calc(~"100% - 120px");
    // }
  }
  .img-border {
    width: 395px;
    height: 220px;
    padding: 10px;
    border-radius: 15px;
    border: 1px solid #aaa;
    float: left;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 15px;
    }
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
