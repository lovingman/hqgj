<template>
  <div class="main-box">
    <div class="title">政策服务编辑</div>
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
            <el-form-item label="标题" prop="title">
              <el-input
                maxlength="200"
                v-model.trim="serviceForm.title"
                clearable
                show-word-limit
                placeholder="请输入标题"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源">
              <el-input
                maxlength="50"
                v-model.trim="serviceForm.source"
                clearable
                show-word-limit
                placeholder="请输入来源"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="内容:" prop="content">
              <!-- 工具栏容器 -->
              <div class="ckeditor">
                <div id="toolbar-container"></div>
                <!-- 编辑器容器 -->
                <div id="editor"></div>
              </div>
              <!--<el-input-->
              <!--type="textarea"-->
              <!--placeholder="请输入内容"-->
              <!--v-model.trim="serviceForm.content"-->
              <!--rows="20"-->
              <!--maxlength="10000"-->
              <!--show-word-limit-->
              <!--&gt;</el-input>-->
              <!--<editor-bar v-model="serviceForm.content" :isClear="isClear" @change="change"></editor-bar>-->
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <!-- 底部按钮 -->
    <div class="footer">
      <div class="footer-flex">
        <el-button @click="back">取消</el-button>
        <el-button @click="handleEdit('serviceForm')" type="primary">提交</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { getPolicyById, updatePolicy } from "@/api/hqgj/Policies";
import { fileUpImg } from "@/api/sys";
import CKEditor from "@ckeditor/ckeditor5-build-decoupled-document";
import "@ckeditor/ckeditor5-build-decoupled-document/build/translations/zh-cn";
export default {
  name: "edit",
  data() {
    return {
      editor: "", //编辑器实例
      //数据
      serviceForm: {
        title: "",
        source: "",
        content: ""
      },
      //验证
      serviceRules: {
        title: [
          {
            required: true,
            message: "请输入标题,字数在50字以内",
            trigger: "blur"
          }
        ],
        content: [
          {
            required: true,
            message: "请输入详细内容",
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.getDetails();
  },
  mounted() {
    this.initCKEditor();
  },
  methods: {
    getDetails() {
      this.id = this.$route.query.id;
      getPolicyById(this.id).then(response => {
        this.serviceForm = response.data;
        this.editor.setData(this.serviceForm.content);
      });
    },
    handleEdit(formName) {
      this.serviceForm.content = this.editor.getData();
      this.$refs[formName].validate(valid => {
        if (valid) {
          updatePolicy(this.serviceForm).then(response => {
            if (response.status == 1) {
              this.$message.success("编辑成功");
              this.back();
            }
          });
        } else {
          return false;
        }
      });
    },
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
        })
        .catch(error => {
          console.error(error);
        });
    },
    back() {
      this.$router.push({
        path: "/hqgj/Policies/OtrlPolicy"
      });
    }
    // change(val) {
    //   console.log(val);
    // }
  }
};
</script>

<style lang="less" scoped>
.main-box {
  background: #fff;
  height: 100%;
  .formBox {
    padding-right: 50px;
  }
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
  .ckeditor {
    width: 100%;
    border: 1px solid #ddd;

    /deep/ .ck-editor__editable {
      height: 580px;
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
