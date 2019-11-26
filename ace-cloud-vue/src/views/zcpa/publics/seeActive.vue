<template>
  <div class="see-box">
    <el-dialog title="活动推送记录查看" :visible.sync="recordVisible" @close="recordVisible = false">
      <el-form label-width="140px">
        <el-col :span="24">
          <el-form-item label="活动主题：">
            <span>{{see.title}}</span>
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
          <el-form-item label="活动地点：">
            <span>{{see.areaCode}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系人姓名：">
            <span>{{see.contactName}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系人电话：">
            <span>{{see.contactTel}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所在区域：">
            <span>{{see.areaName}}</span>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="活动详情：">
            <span>{{see.content}}</span>
          </el-form-item>
        </el-col>
      </el-form>
      <el-table :data="personnelData" border style="width: 100%">
        <el-table-column type="index" width="55" label="序号" align="center"></el-table-column>
        <el-table-column prop="name" width="120" label="姓名"></el-table-column>
        <el-table-column prop="mobile" width="180" label="手机号码"></el-table-column>
        <el-table-column prop="userName" width="120" label="所属类型"></el-table-column>
        <el-table-column prop="areaName" label="所在地区"></el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSize"
        @current-change="handleCurrent"
        :current-page="currentPages"
        :page-size="pageSize"
        :total="total"
        background
        layout="total,sizes,prev, pager, next ,jumper"
      ></el-pagination>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click=" recordVisible = false">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { seePersonnel } from "@/api/zcpa/actProject";
export default {
  name: "seeActive",
  props: {
    seeByDada: {
      type: Object
    },
    category: {
      type: String
    },
    projectId: {
      type: String
    },
    areaCode: {
      type: String
    },
    targetId: {
      type: String
    },
    dictArrayObj: {
      type: Object
    }
  },
  watch: {
    seeByDada: {
      handler(newName, oldName) {
        this.see = newName;
      },
      immediate: true, //立刻
      deep: true
    },
    category: {
      handler(newName, oldName) {
        if (newName) {
          this.category = newName;
        }
      }
    },
    projectId: {
      handler(newName, oldName) {
        if (newName) {
          this.projectId = newName;
        }
      }
    },
    areaCode: {
      handler(newName, oldName) {
        if (newName) {
          this.areaCode = newName;
        }
      }
    },
    targetId: {
      handler(newName, oldName) {
        if (newName) {
          this.targetId = newName;
        }
      }
    },
    dictArrayObj: {
      handler(newName, oldName) {
        this.initDictData(newName);
      },
      immediate: true, //立刻
      deep: true
    }
  },
  data() {
    return {
      recordVisible: false, //是否显示
      personnelData: [], //志愿者人数data
      peopleValueArr: [], //人物身份
      currentPages: 1, //志愿者初始页
      pageSize: 10, //志愿者每页数据
      total: 0 //志愿者总条数
    };
  },
  methods: {
    handleSize: function(size) {
      this.pageSize = size;
      this.initPersonnel();
      //每页下拉显示数据
    },
    handleCurrent: function(currentPage) {
      this.currentPages = currentPage;
      this.initPersonnel();
      //点击第几页
    },
    initPersonnel() {
      var obj = {};
      obj.projectCategory = this.category;
      obj.projectId = this.projectId;
      obj.areaCode = this.areaCode;
      obj.targetId = this.targetId;
      obj.pageNum = this.currentPages;
      obj.pageSize = this.pageSize;
      obj.totalRecord = this.total;
      seePersonnel(obj).then(response => {
        if (response.status == 1) {
          this.personnelData = response.rows;
          this.total = response.total;
          this.currentPages = 1;
          if (this.personnelData) {
            for (var i = 0; i < this.personnelData.length; i++) {
              for (var y = 0; y < this.peopleValueArr.length; y++) {
                if (
                  this.personnelData[i].userType == this.peopleValueArr[y].code
                ) {
                  this.personnelData[i].userType = this.peopleValueArr[y].code;
                  this.personnelData[i].userName = this.peopleValueArr[y].name;
                }
              }
            }
          }
        }
      });
    },
    //初始化字典数据
    initDictData() {
      this.peopleValueArr = this.dictArrayObj.peopleValue; //49人物身份
    }
  }
};
</script>

<style lang="less" scoped>
.see-box {
  /deep/ .el-form-item--medium .el-form-item__content {
    line-height: 36px;
  }
  span {
    word-break: break-all;
  }
}
</style>