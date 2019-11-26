<template>
  <div class="see-box">
    <el-dialog title="查看" :visible.sync="lookVisible" class="seeForm" @close="lookVisible = false">
      <el-form label-width="140px">
        <el-col :span="12">
          <el-form-item class="projectName" label="项目名称：">
            <el-tooltip class="item" effect="dark" :content="see.projectName" placement="top-start">
              <span>{{see.projectName}}</span>
            </el-tooltip>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="项目级别：">
            <span>{{see.projectLevel}}</span>
          </el-form-item>
        </el-col>
        <el-row style="margin-bottom:0;width: 100%; float: left;">
          <el-col :span="12">
            <el-form-item label="项目类型：">
              <span>{{see.category}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="isShow">
            <el-form-item label="需对接志愿者数：">
              <span>{{see.peopleNumber}}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-col :span="12">
          <el-form-item label="行政区划：">
            <span>{{see.areaName}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="开始时间：">
            <span>{{see.startTime}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="结束时间：">
            <span>{{see.endTime}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="活动积分:">
            <span>{{see.jiFen}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="19积分:">
            <span>{{see.nineteenJiFen}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="开展次数：">
            <span>{{see.developNum}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="综合得分：">
            <span>{{see.score}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发现异常数：">
            <span>{{see.ycNum}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="参与总时长：">
            <span>{{see.volHour}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="项目状态：">
            <span>{{see.projectState}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <div class="imgForm">
            <div class="lebel">相册封面：</div>
            <div class="previewImg" v-if="see.fmUrl">
              <img :src="see.fmUrl" />
              <div class="hidePrevie">
                <i class="el-icon-zoom-in" @click="previeClick"></i>
              </div>
            </div>
            <div v-else>
              <div class="hide-txt">未上传图片</div>
            </div>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item label="项目介绍:">
            <span>{{see.summary}}</span>
          </el-form-item>
        </el-col>
      </el-form>
      <el-dialog class="imgShowDemo" title="图片预览" :visible.sync="previeVisible" append-to-body>
        <img width="100%" :src="unitImageUrl" />
      </el-dialog>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click=" lookVisible = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getById } from "@/api/zcpa/actProject";
export default {
  props: {
    seeId: {
      type: String
    },
    seeRow: {
      type: Object
    },
    dictArrayObj: {
      type: Object
    }
  },
  watch: {
    seeId: {
      handler(newName, oldName) {
        if (newName) {
          this.id = newName;
          this.initSee();
        }
      },
      immediate: true, //立刻
      deep: true
    },
    seeRow: {
      handler(newName, oldName) {
        if (newName) {
          this.seeRows = newName;
        }
      },
      immediate: true, //立刻
      deep: true
    },
    dictArrayObj: {
      handler(newName, oldName) {
        this.initDictData(newName);
      },
      immediate: true, //立刻
      deep: true
    }
  },
  name: "see",
  data() {
    return {
      see: {},
      seeRows: {},
      isShow: false, //是否显示
      unitImageUrl: "", //查看图片预览
      lookVisible: false, //是否隐藏显示
      previeVisible: false //查看图片弹框
    };
  },
  created() {
    this.initDictData();
  },
  methods: {
    initSee() {
      getById(this.id).then(response => {
        if (response.status == 1) {
          this.see = response.data;
          this.see.areaName = this.seeRows.areaName;
          var categoryName = this.see.category;
          var projectLevelName = this.see.projectLevel;
          var statusName = this.see.projectState;
          this.translateCategory(categoryName, this.projectTypeArr);
          this.translateProjectLevel(projectLevelName, this.projectLevelArr);
          this.translateStatus(statusName, this.statusValueArr);
          if (categoryName == 1) {
            this.isShow = false;
          } else {
            this.isShow = true;
          }
        }
      });
    },
    //项目类型字典code 找中文
    translateCategory(code, objArr) {
      let obj = {};
      obj = objArr.find(item => {
        //找到匹配的数据
        return item.code == code;
      });
      this.see.category = obj.name; //赋值汉字
    },
    //项目等级字典code 找中文
    translateProjectLevel(code, objArr) {
      let obj = {};
      obj = objArr.find(item => {
        //找到匹配的数据
        return item.code == code;
      });
      this.see.projectLevel = obj.name; //赋值汉字
    },
    //数据状态字典code 找中文
    translateStatus(code, objArr) {
      let obj = {};
      obj = objArr.find(item => {
        //找到匹配的数据
        return item.code == code;
      });
      this.see.projectState = obj.name; //赋值汉字
    },
    //查看图片预览
    previeClick() {
      this.unitImageUrl = this.see.fmUrl;
      this.previeVisible = true;
    },
    //初始化字典数据
    initDictData() {
      this.projectTypeArr = this.dictArrayObj.projectValue; //47项目类型
      this.projectLevelArr = this.dictArrayObj.ProjectLevelValue; //48项目等级
      this.statusValueArr = this.dictArrayObj.statusValue; //50数据状态
    }
  }
};
</script>

<style lang="less" scoped>
.seeForm {
  word-wrap: break-word;
  word-break: normal;
  /deep/ .el-form-item__label {
    text-align: left;
  }
  /deep/ .el-form-item {
    margin-bottom: 0;
  }
  /deep/ .el-form-item__content {
    line-height: 36px;
    height: 36px;
    // overflow: hidden;
    // text-overflow: ellipsis;
    // white-space: nowrap;
  }
  /deep/ .projectName {
    padding-right: 20px;
  }
  /deep/ .projectName .el-form-item__content {
    line-height: 36px;
    height: 36px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .imgForm {
    margin-bottom: 20px;
    width: 100%;
    float: left;
    .lebel {
      width: 140px;
      float: left;
      line-height: 36px;
    }
    .hide-txt {
      line-height: 36px;
    }
    .previewImg {
      width: 200px;
      height: 200px;
      border: 1px solid #ddd;
      padding: 10px;
      float: left;
      border-radius: 10px;
      position: relative;
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
    }
    .previewImg:hover .hidePrevie {
      position: absolute;
      left: 0;
      top: 0;
      width: 200px;
      height: 200px;
      text-align: center;
      line-height: 200px;
      transition: all 0.3s ease;
      background-color: rgba(0, 0, 0, 0.5);
    }
    .hidePrevie {
      i {
        font-size: 30px;
        color: #fff;
        cursor: pointer;
      }
    }
  }
}
</style>