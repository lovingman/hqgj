<template>
  <div class="main-box">
    <div class="title">创建</div>
    <div class="conetnet-box">
      <el-form
        :model="serviceForm"
        :inline="true"
        :rules="serviceRules"
        ref="serviceForm"
        label-width="120px"
        class="formBox"
      >
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input
                maxlength="50"
                v-model="serviceForm.name"
                clearable
                show-word-limit
                placeholder="请输入模板名称"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="文件" prop="fileType">
              <el-upload
                class="upload-demo"
                drag
                action="https://jsonplaceholder.typicode.com/posts/"
                multiple
              >
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">
                  将文件拖到此处，或
                  <em>点击上传</em>
                </div>
                <div class="el-upload__tip" slot="tip">支持扩展名：.rar .zip .doc .docx .pdf .jpg....</div>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <!-- 底部按钮 -->
    <div class="footer">
      <div class="footer-flex">
        <el-button @click="black">取消</el-button>
        <el-button type="primary">确定</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "add",
  data() {
    return {
      //数据
      serviceForm: {
        name: ""
      },
      //验证
      serviceRules: {
        name: [
          {
            required: true,
            message: "请输入模板名称,字数在50字以内",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    black() {
      this.$router.push({
        path: "/hqgj/Policies/legalService"
      });
    }
  }
};
</script>

<style lang="less" scoped>
.main-box {
  background: #fff;
  .title {
    font-size: 16px;
    font-weight: bold;
    padding: 20px 30px;
    border-bottom: 1px solid #eee;
  }
  .conetnet-box {
    padding: 30px 0 0 0;
    /deep/ .el-form-item {
      width: 100%;
    }
    /deep/ .el-form-item__content {
      width: calc(~"100% - 120px");
    }
    /deep/ .el-input--medium .el-input__inner {
      padding-right: 80px;
    }
    /deep/ .el-upload {
      width: 100%;
      /deep/ .el-upload-dragger {
        width: 100%;
      }
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