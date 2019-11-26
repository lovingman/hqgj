<template>
  <div class="active-box">
    <el-dialog title="活动推送" :visible.sync="activityVisible" @close="emptyForm('activitysForm')">
      <el-form
        :model="activitysForm"
        :inline="true"
        :rules="activitysRules"
        ref="activitysForm"
        label-width="120px"
        class="activitysBox"
      >
        <el-row>
          <el-col :span="24">
            <el-form-item label="活动主题" prop="title">
              <el-input
                maxlength="20"
                v-model="activitysForm.title"
                clearable
                show-word-limit
                placeholder="请输入活动主题,字数在20字以内"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker
                v-model="activitysForm.startTime"
                type="datetime"
                @change="startChange()"
                :picker-options="startPicker"
                clearable
                default-time="08:00:00"
                placeholder="选择开始日期"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker
                v-model="activitysForm.endTime"
                :picker-options="endPicker"
                clearable
                :disabled="isDisabled"
                type="datetime"
                placeholder="选择结束日期"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="活动地点" prop="areaCode">
              <el-input
                class="address-right"
                maxlength="20"
                v-model="activitysForm.areaCode"
                clearable
                show-word-limit
                placeholder="请输入活动地点，字数在20字以内"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系人姓名" prop="contactName">
              <el-input v-model="activitysForm.contactName" clearable placeholder="请输入联系人姓名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系人电话" prop="contactTel">
              <el-input v-model="activitysForm.contactTel" clearable placeholder="请输入联系人电话"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动详情" prop="content">
              <el-input
                type="textarea"
                :rows="6"
                maxlength="50"
                v-model="activitysForm.content"
                clearable
                show-word-limit
                placeholder="请输入活动详情，字数在50字以内"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelArr('activitysForm')">取 消</el-button>
        <el-button type="primary" @click="submitForm('activitysForm')">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  naem: "active",
  data() {
    return {
      isDisabled: true,
      activityVisible: false,
      startPicker: this.startPickers(), //开始时间限制
      endPicker: this.endPickers(), //结束时间限制
      //活动推送
      activitysForm: {
        title: "", //活动主题
        startTime: "", //开始时间
        endTime: "", //结束时间
        areaCode: "", //活动地点
        contactName: "", //联系人姓名
        contactTel: "", //联系人电话
        currentAreaCode: "", //当前区域areaCode
        content: "" //活动详情
      },
      activitysRules: {
        title: [
          {
            required: true,
            message: "请输入活动主题,字数在20字以内",
            trigger: "blur"
          }
        ],
        startTime: [
          { required: true, message: "请选择开始时间", trigger: "change" }
        ],
        endTime: [
          { required: true, message: "请选择结束时间", trigger: "change" }
        ],
        areaCode: [
          {
            required: true,
            message: "请输入活动地点,字数在20字以内",
            trigger: "blur"
          }
        ],
        contactName: [
          { required: true, message: "请输入联系人姓名", trigger: "blur" }
        ],
        contactTel: [
          { required: true, message: "请输入联系人电话", trigger: "blur" },
          { validator: this.globalMethods.checkPhone, trigger: "blur" }
        ],
        content: [
          {
            required: true,
            message: "请输入活动详情，字数在50字以内",
            trigger: "blur"
          },
          { validator: this.globalMethods.checkTextareaone, trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    //关闭
    emptyForm(type) {
      if (type) {
        this.$refs[type].resetFields();
      }
    },
    //开始事件选择
    startChange() {
      if (this.activitysForm.startTime) {
        this.isDisabled = false;
      }
    },
    //取消
    cancelArr(formName) {
      this.$nextTick(() => {
        this.$refs[formName].resetFields();
      });
      this.activityVisible = false;
    },
    //活动提交
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //验证无误后请求接口
          this.$emit("submitForm");
        } else {
          return false;
        }
      });
    },
    //开始时间限制
    startPickers() {
      // const self = this;
      // return {
      //   disabledDate(time) {
      //     if (self.activitysForm.endTime) {
      //       //如果结束时间不为空，则小于结束时间
      //       return (
      //         new Date(self.activitysForm.endTime).getTime() < time.getTime()
      //       );
      //     } else {
      //       // return time.getTime() > Date.now()//开始时间不选时，结束时间最大值小于等于当天
      //     }
      //   }
      // };
    },
    //结束时间限制
    endPickers() {
      const self = this;
      return {
        disabledDate(time) {
          if (self.activitysForm.startTime) {
            //如果开始时间不为空，则结束时间大于开始时间
            return (
              new Date(self.activitysForm.startTime).getTime() - 8.64e7 >
              time.getTime()
            );
          } else {
            return (
              new Date(self.activitysForm.startTime).getTime() > time.getTime()
            );
          }
        }
      };
    }
  }
};
</script>

<style lang="less" scoped>
.activitysBox {
  /deep/ .el-form-item {
    width: 100%;
  }
  /deep/ .el-form-item__content {
    width: calc(~"100% - 160px");
  }
  /deep/ .el-date-editor.el-input {
    width: 100%;
  }
  /deep/ .address-right .el-input__inner {
    padding-right: 90px;
  }
  /deep/ .el-textarea__inner {
    word-break: break-all;
  }
}
</style>