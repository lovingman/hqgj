<template>
  <div class="volunteer-box">
    <el-dialog title="新增志愿者" :visible.sync="volunteerVisible" :before-close="closeDialog">
      <el-form label-width="100px">
        <el-col>
          <el-form-item label="新增志愿者" style="font-size: 20px">
            <el-input v-model="zyz.name" clearable placeholder="请输入姓名或身份证号" style="width: 70%;">
              <el-button slot="append" @click="queryzyz">排查</el-button>
            </el-input>
          </el-form-item>
        </el-col>
      </el-form>
      <template>
        <div class="zyz">
          <el-table
            ref="multipleTableVolunteer"
            :data="volunteer"
            style="width: 100%"
            @selection-change="handleSelecetVolunteer"
          >
            <el-table-column type="selection" width="55" center></el-table-column>
            <el-table-column prop="name" label="姓名" width="120"></el-table-column>
            <el-table-column prop="idCard" label="身份证" width="220"></el-table-column>
            <el-table-column prop="mobile" label="联系方式" width="200"></el-table-column>
            <el-table-column prop="userTypeName" label="志愿者类型"></el-table-column>
          </el-table>
        </div>
      </template>
      <el-pagination
        @size-change="handleSizeVolunteer"
        @current-change="handleCurrentVolunteer"
        :current-page="volunteerCurrentPage"
        :page-size="volunteerPagesize"
        :total="volunteerTotal"
        background
        layout="total,sizes,prev, pager, next ,jumper"
      ></el-pagination>
      <template>
        <el-form ref="zyz" :model="zyz" label-width="100%" v-if="this.category == '1'">
          <p style="font-size: 20px;color: black;margin: 20px 0">设定志愿者类型</p>
          <el-radio-group v-model="zyz.userType">
            <el-row style="margin-bottom: 20px;margin-left: 50px">
              <el-col :span="8">
                <el-radio :label="1">心理咨询师</el-radio>
              </el-col>
              <el-col :span="8">
                <el-radio :label="2" style="margin-left: 30px">法律工作者</el-radio>
              </el-col>
              <el-col :span="8">
                <el-radio :label="3" style="margin-left: 30px">医护人员</el-radio>
              </el-col>
            </el-row>
            <el-row style="margin-left: 50px">
              <el-col :span="8">
                <el-radio :label="4">社会工作者</el-radio>
              </el-col>
              <el-col :span="8">
                <el-radio :label="5" style="margin-left: 30px">教育工作者</el-radio>
              </el-col>
              <el-col :span="8">
                <el-radio :label="6" style="margin-left: 30px">其他志愿者</el-radio>
              </el-col>
            </el-row>
          </el-radio-group>
        </el-form>
      </template>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleVolunteer('zyz')">取 消</el-button>
        <el-button type="primary" @click="saveAddVolunteer">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { zyzpage } from "@/api/zcpa/actProject";
export default {
  props: {
    dictArrayObj: {
      type: Object
    },
    category: {
      type: String
    },
    zyzaidTargetType: {
      type: String
    },
    volunteercode: {
      type: String
    }
  },
  watch: {
    dictArrayObj: {
      handler(newName, oldName) {
        this.initDictData(newName);
      },
      immediate: true, //立刻
      deep: true
    },
    volunteercode: {
      handler(newName, oldName) {
        this.areaCode = newName;
        this.getVolunteerData();
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
    zyzaidTargetType: {
      handler(newName, oldName) {
        this.zyzaidTargetType = newName;
      },
      immediate: true, //立刻
      deep: true
    }
  },
  name: "volunteer",
  data() {
    return {
      volunteer: [],
      peopleValueArr: [],
      volunteerVisible: false, //是否显示弹框
      volunteerCurrentPage: 1, //志愿者初始页
      volunteerPagesize: 10, //志愿者每页数据
      volunteerTotal: 0, //志愿者总条数
      zyz: {
        name: "",
        userType: ""
      }
    };
  },
  methods: {
    //新增志愿者选中
    handleSelecetVolunteer(val) {
      this.volunteerArr = val;
    },
    handleSizeVolunteer: function(size) {
      this.volunteerPagesize = size;
      this.getVolunteerData();
      //每页下拉显示数据
    },
    handleCurrentVolunteer: function(currentPage) {
      this.volunteerCurrentPage = currentPage;
      //点击第几页
      this.getVolunteerData();
    },
    //确定志愿者
    saveAddVolunteer() {
      this.$emit("saveAddVolunteer");
    },
    //关闭弹窗
    handleVolunteer(type) {
      this.zyz.name = "";
      this.volunteerVisible = false;
      this.getVolunteerData();
    },
    closeDialog() {
      this.zyz.name = "";
      this.volunteerVisible = false;
      this.getVolunteerData(); //刷新子组件
    },
    //获取志愿者数据
    getVolunteerData() {
      var obj = {};
      obj.pageNum = this.volunteerCurrentPage;
      obj.pageSize = this.volunteerPagesize;
      obj.totalRecord = this.volunteerTotal;
      obj.name = this.zyz.name;
      obj.areaCode = this.areaCode;
      this.volunteerCurrentPage = 1;
      if (this.category == "1" && this.zyzaidTargetType != 6) {
        obj.userType = this.zyzaidTargetType;
      } else {
        obj.userType = "";
      }
      zyzpage(obj).then(response => {
        if (response.status == 1) {
          this.volunteer = response.rows;
          this.volunteerTotal = response.total;
          if (this.volunteer) {
            for (var i = 0; i < this.volunteer.length; i++) {
              for (var y = 0; y < this.peopleValueArr.length; y++) {
                if (this.volunteer[i].userType == this.peopleValueArr[y].code) {
                  this.volunteer[i].userType = this.peopleValueArr[y].code;
                  this.volunteer[i].userName = this.peopleValueArr[y].name;
                }
              }
            }
          }
        }
      });
    },
    //志愿者排查
    queryzyz() {
      this.getVolunteerData();
    },
    //初始化字典数据
    initDictData() {
      this.peopleValueArr = this.dictArrayObj.peopleValue; //49人物身份
    }
  }
};
</script>

<style lang="less" scoped>
</style>