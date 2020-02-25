<template>
  <div class="main-box">
    <div class="title">政策服务创建</div>
    <div class="conetnet-box">
      <el-form
        :inline="true"
        :model="serviceForm"
        :rules="serviceRules"
        class="formBox"
        label-width="120px"
        ref="serviceForm"
      >
        <el-row>
          <el-col :span="12">
            <el-form-item label="标题" prop="title">
              <el-input
                clearable
                maxlength="200"
                placeholder="请输入标题"
                show-word-limit
                v-model.trim="serviceForm.title"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="来源">
              <el-input
                clearable
                maxlength="50"
                placeholder="请输入来源"
                show-word-limit
                v-model.trim="serviceForm.source"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="文件" prop="fileList">
              <el-upload
                      :before-upload="beforeAvatarUpload"
                      :file-list="serviceForm.fileList"
                      :http-request="uploadServer"
                      :on-remove="fileRemove"
                      action="none"
                      class="upload-demo"
                      drag
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
        <el-button @click="handleAdd('serviceForm')" type="primary">提交</el-button>
      </div>
    </div>
  </div>
</template>


<script>
import { createPolicy } from "@/api/hqgj/Policies";
import { fileUpImg, fileUpload } from "@/api/sys";
import EditorBar from "../../publicTemplate/wangEnduit";
import CKEditor from "@ckeditor/ckeditor5-build-decoupled-document";
import "@ckeditor/ckeditor5-build-decoupled-document/build/translations/zh-cn";

export default {
  name: "add",
  components: { EditorBar },
  data() {
    return {
      basicAnnexesArr: [],
      editor: "", //编辑器实例
      //数据
      serviceForm: {
        title: "",
        source: "",
        content: "",
        basicAnnexes: [],
        fileList: []
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
  mounted() {
    this.initCKEditor();
  },
  methods: {
    handleAdd(formName) {
      this.serviceForm.content = this.editor.getData();
      this.$refs[formName].validate(valid => {
        if (valid) {
          createPolicy(this.serviceForm).then(response => {
            if (response.status == 1) {
              this.$message.success("创建成功");
              this.back();
            }
          });
        } else {
          return false;
        }
      });
    },
      FileUpload(obj) {
          console.log(obj);
          this.actionUrls = "/hqgj-portal/www/uploadFile";
          fileUpload(obj, this.actionUrls).then(response => {
              if (response.status == 1) {
                  // this.serviceForm.basicAnnexes.push({fileName:obj.file.name.substring(0,obj.file.name.indexOf(".")),fileURL:response.data})
                  this.serviceForm.fileList.push({
                      name: obj.file.name,
                      url: response.data
                  });
                  this.uploadSuccess(response, obj.file, this.serviceForm.fileList);
              } else {
                  this.$message({
                      message: response.message,
                      type: "warning"
                  });
                  this.uploadError(response, obj.file, this.serviceForm.fileList);
              }
              return response.status;
          });
      },
      //文件移除
      fileRemove(file, fileList) {
          this.basicAnnexesArr = [];
          for (var i = 0; i < fileList.length; i++) {
              this.basicAnnexesArr.push({
                  fileName: fileList[i].name.substring(
                      0,
                      fileList[i].name.indexOf(".")
                  ),
                  fileURL: fileList[i].url
              });
          }
          this.serviceForm.basicAnnexes = this.basicAnnexesArr;
          console.log(fileList);
          this.serviceForm.fileList = fileList;
      },
      //自定义上传
      uploadServer(param) {
          let obj = {};
          obj.file = param.file;
          this.FileUpload(obj);
          const prom = new Promise((resolve, reject) => {});
          prom.abort = () => {};
          return prom;
          // return true
      },
      uploadSuccess(response, file, fileList) {
          this.basicAnnexesArr = [];
          for (var i = 0; i < fileList.length; i++) {
              this.basicAnnexesArr.push({
                  fileName: fileList[i].name.substring(
                      0,
                      fileList[i].name.indexOf(".")
                  ),
                  fileURL: fileList[i].url
              });
          }
          this.serviceForm.basicAnnexes = this.basicAnnexesArr;
          this.serviceForm.fileList = fileList;
      },
      uploadError(response, file, fileList) {
          this.$message.error("上传失败");
      },
      // 上传前对文件的大小的判断
      beforeAvatarUpload(file) {
          let isRightType = /\.xls$|\.xlsx$|\.doc$|\.docx$|\.pptx$|\.ppt$|\.pdf$/i.test(
              file.name
          );
          const isLt2M = file.size / 1024 / 1024 < 50;
          if (!isRightType) {
              this.$message.warning(
                  "上传文件只能是 xls、xlsx、doc、docx 、pdf、pptx、ppt格式!"
              );
          }
          if (!isLt2M) {
              this.$message.warning("上传模板大小不能超过 10MB!");
          }
          return isRightType && isLt2M;
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
