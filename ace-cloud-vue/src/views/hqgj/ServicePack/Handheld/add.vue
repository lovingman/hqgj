<template>
  <div class="main-box">
    <div class="title">财税服务创建</div>
    <div class="content-box">
      <div class="title">基本信息</div>
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
            <el-form-item label="类型：" prop="type">
              <el-select
                v-model="serviceForm.type"
                clearable
                @change="changeType"
                placeholder="请选择"
              >
                <el-option
                  v-for="item in typeArr"
                  :key="item.code"
                  :label="item.name"
                  :value="item.code"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <div
            class="ties"
            v-if="serviceForm.type == 1"
          >选择该类型，用户支付费用通过应付费用=500积分+支付费用的方式，抵扣的积分将返现给服务机构,500积分=500元</div>
          <div class="ties" v-if="serviceForm.type == 3">选择该类型，用户可免费享受咨询服务</div>
        </el-row>
        <!-- 代理财政是否显示 -->
        <div class="show-box">
          <el-row>
            <el-col :span="12">
              <el-form-item label="服务机构：" prop="orgId">
                <el-select
                  v-model="serviceForm.orgId"
                  :disabled="orgDisabled"
                  clearable
                  @change="changeService"
                  placeholder="请选择"
                >
                  <el-option
                    v-for="item in corpArr"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="serviceForm.type == 3">
              <el-form-item label="专家：" prop="contactId">
                <el-select
                  clearable
                  v-model="serviceForm.contactId"
                  @change="changeContactPerson"
                  placeholder="请选择专家"
                >
                  <el-option
                    v-for="item in contactPersonArr"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="serviceForm.type == 3">
              <el-form-item label="名额：" prop="quota">
                <el-input v-model.trim="serviceForm.quota" clearable placeholder="请输入免费咨询名额"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="联系方式：" prop="contactPersonTel">
                <el-input
                  v-model.trim="serviceForm.contactPersonTel"
                  clearable
                  placeholder="请输入联系方式,例如：13500228899或者0736-1234567"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="serviceForm.type == 3">
              <el-form-item label="地点：" prop="address">
                <el-input v-model.trim="serviceForm.address" clearable placeholder="请输入地点"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <photo ref="imgUpload" :getData="getData"></photo>
          </el-row>
          <el-row>
            <el-form-item label="服务介绍：" prop="content">
              <!-- 工具栏容器 -->
              <div class="ckeditor">
                <div id="toolbar-container"></div>
                <!-- 编辑器容器 -->
                <div id="editor"></div>
              </div>
            </el-form-item>
          </el-row>
          <el-row v-if="serviceForm.type == 2">
            <div class="title">服务项目</div>
            <div class="project-top">
              <div class="ltt">
                <i>*</i>服务项目：
              </div>
              <div class="rtt">最多添加四项服务，例：名称-小规模纳税人 价格-4000元</div>
            </div>
            <div class="project-item">
              <div class="project-item-box">
                <div
                  class="memberModel"
                  v-for="(domain,index) in serviceForm.financeItemList"
                  :key="domain.key"
                >
                  <div class="member-top">
                    <div class="ltt">服务项目{{index+1}}</div>
                    <div class="rtt" @click="removeDomain(domain)">删除</div>
                  </div>

                  <el-col :span="12">
                    <el-form-item
                      :label="'名称'"
                      :prop="'financeItemList.'+index+'.itemName'"
                      :rules="serviceRules.itemName"
                      :key="domain.key"
                    >
                      <el-input
                        clearable
                        v-model.trim="domain.itemName"
                        show-word-limit
                        maxlength="20"
                        placeholder="请输入名称"
                      ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item
                      :label="'价格'"
                      :prop="'financeItemList.'+index+'.price'"
                      :rules="serviceRules.price"
                      :key="domain.key"
                    >
                      <el-input v-model.trim="domain.price" clearable placeholder="请输入价格"></el-input>
                    </el-form-item>
                  </el-col>
                </div>
              </div>
              <el-button type="primary" @click="addMember" style="margin-top:20px;">
                <i class="el-icon-plus"></i>添加项目
              </el-button>
            </div>
          </el-row>
        </div>
        <!-- end -->
      </el-form>
    </div>
    <!-- 底部按钮 -->
    <div class="footer">
      <div class="footer-flex">
        <el-button @click="black">取消</el-button>
        <el-button @click="submission('serviceForm')" type="primary">提交</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { create } from "@/api/hqgj/handheld";
import {
  getDict,
  getUser,
  lecturerMechanism,
  lecturerPage,
  fileUpImg
} from "@/api/sys";
import photo from "../../publicTemplate/photo";
import CKEditor from "@ckeditor/ckeditor5-build-decoupled-document";
import "@ckeditor/ckeditor5-build-decoupled-document/build/translations/zh-cn";
export default {
  components: {
    photo
  },
  name: "add",
  data() {
    return {
      orgDisabled: true, //是否禁止服务机构选择
      //服务包类型
      typeArr: [],
      //机构类型
      corpArr: [],
      //专家类型
      contactPersonArr: [],
      getData: {}, //专家形象照片
      editor: "", //编辑器实例
      //服务包容器
      serviceForm: {
        type: "", //类型
        contactId: "", //专家ID
        contactPersonName: "", //专家Name
        contactPersonTel: "", //联系方式
        orgName: "", //服务机构name
        orgId: "", //服务机构ID
        itemName: "", //服务名称
        price: "", //价格
        financeItemList: [], //新增服务项目数组
        quota: "", //名额
        content: "", //详细介绍服务内容
        address: "" //地点
      },
      serviceRules: {
        type: [
          {
            required: true,
            message: "请选择类型",
            trigger: "change"
          }
        ],
        contactId: [
          {
            required: true,
            message: "请选择专家",
            trigger: "change"
          }
        ],
        contactPersonTel: [
          {
            required: true,
            message: "请输入联系方式,例如：13500228899或者07361234567",
            trigger: "blur"
          },
          { validator: this.globalMethods.checkNumbar, trigger: "blur" }
        ],
        orgId: [
          {
            required: true,
            message: "请选择服务机构",
            trigger: "change"
          }
        ],
        quota: [
          {
            required: true,
            message: "请输入免费咨询名额",
            trigger: "blur"
          },
          { validator: this.globalMethods.checkIntegerP, trigger: "blur" }
        ],
        content: [
          {
            required: true,
            message: "请输入详细介绍服务内容",
            trigger: "blur"
          }
        ],
        itemName: [
          {
            required: true,
            message: "请输入名称，字数在20字以内",
            trigger: "blur"
          }
        ],
        price: [
          {
            required: true,
            message: "请输入价格",
            trigger: "blur"
          },
          { validator: this.globalMethods.checkIntegerDecimal, trigger: "blur" }
        ],
        contact: [
          {
            required: true,
            message: "请输入联系方式",
            trigger: "blur"
          }
        ],
        address: [
          {
            required: true,
            message: "请输入地点",
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.dictQuery();
    this.loadUser();
  },
  mounted() {
    this.initCKEditor();
  },
  methods: {
    //获取用户信息
    loadUser() {
      this.serviceForm.type = "1";
      this.changeType(this.serviceForm.type);
      getUser().then(res => {
        this.userType = res.data.userType; //1律师服务 2会计师服务 3培训机构 4工信局
        this.dataRows = res.data;
        if (this.userType == 4) {
          //如果是工信局请求服务机构数据, 2是会计师服务type值
          let obj = 2;
          this.orgDisabled = false;
          lecturerMechanism(obj).then(res => {
            if (res.status == 1) {
              console.log(res);
              this.disabled = false;
              this.orgRows = res.rows;
              for (let i = 0; i < this.orgRows.length; i++) {
                var obj = {};
                obj.id = this.orgRows[i].id;
                obj.name = this.orgRows[i].orgName;
                obj.phone = this.orgRows[i].contactPersonTel;
                this.corpArr.push(obj);
              }
            }
          });
        } else {
          this.orgDisabled = true;
          this.serviceForm.orgId = this.dataRows.corpId;
          this.serviceForm.contactPersonTel = res.data.mobile;
          var arrs = {};
          arrs.id = this.dataRows.corpId;
          arrs.name = this.dataRows.corpName;
          this.corpArr.push(arrs);
          lecturerPage(this.dataRows.corpId).then(res => {
            if (res.status == 1) {
              console.log(res);
              this.contactPersonArr = [];
              this.serviceForm.contactId = "";
              let personnelRows = res.rows;
              console.log(personnelRows);
              for (let i = 0; i < personnelRows.length; i++) {
                let obj = {};
                obj.id = personnelRows[i].id;
                obj.name = personnelRows[i].name;
                obj.fmUrl = personnelRows[i].imagePhoto;
                obj.phone = personnelRows[i].mobile;
                this.contactPersonArr.push(obj);
              }
            }
          });
        }
      });
    },
    //获取类型字典
    dictQuery() {
      getDict("55").then(res => {
        this.typeArr = res.data["55"]; //类型字典
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
    //专家选择
    changeContactPerson(value) {
      let obj = {};
      obj = this.contactPersonArr.find(item => {
        //这里的contactPersonArr就是上面遍历的数据源
        return item.id === value; //筛选出匹配数据
      });
      this.serviceForm.contactId = value;
      this.serviceForm.contactPersonName = obj.name;
      this.serviceForm.contactPersonTel = obj.phone;
      let arrs = {};
      arrs.fmUrl = obj.fmUrl;
      this.getData = arrs;
    },
    //类型选择
    changeType(value) {
      this.type = value;
      if (this.userType == 4) {
        this.serviceForm.orgId = "";
      }
      if (value == 2) {
        if (this.serviceForm.financeItemList.length == 0) {
          this.addMember();
        } else {
          this.removeDomain();
        }
      }
      if (value == 3) {
        this.serviceForm.contactPersonTel = "";
      }
    },
    //服务机构选择
    changeService(value) {
      if (value) {
        let orgId = value;
        let obj = {};
        obj = this.corpArr.find(item => {
          //这里的userList就是上面遍历的数据源
          return item.id === value; //筛选出匹配数据
        });
        this.serviceForm.orgId = value;
        this.serviceForm.orgName = obj.name;
        this.serviceForm.contactPersonTel = obj.phone;
        if (this.userType == 4) {
          //如果是工信局用户先选择机构再调取人员信息
          //选择服务机构调取服务机构下面的人员信息
          lecturerPage(orgId).then(res => {
            if (res.status == 1) {
              this.contactPersonArr = [];
              this.serviceForm.contactId = "";
              let personnelRows = res.rows;
              for (let i = 0; i < personnelRows.length; i++) {
                let obj = {};
                obj.id = personnelRows[i].id;
                obj.name = personnelRows[i].name;
                obj.fmUrl = personnelRows[i].imagePhoto;
                obj.phone = personnelRows[i].mobile;
                this.contactPersonArr.push(obj);
              }
            }
          });
        }
      }
    },

    //取消返回
    black() {
      this.$router.push({ path: "/hqgj/ServicePack/Handheld/index" });
    },
    //提交
    submission(formName) {
      //验证子组件图片是否有上传照片
      let photo;
      this.$refs["imgUpload"].$refs["photoForm"].validate(valid => {
        photo = valid;
      });
      if (!photo) {
        this.$message({
          message: "请上传封面照片",
          type: "error"
        });
        return;
      }
      this.serviceForm.content = this.editor.getData();
      //验证其他信息
      this.$refs[formName].validate(valid => {
        if (valid) {
          create({
            type: this.type, // 类型 1代理记账 2财税管理 3专家问诊
            orgName: this.serviceForm.orgName, //服务机构名称
            orgId: this.serviceForm.orgId, //服务机构ID
            contactId: this.serviceForm.contactId, //专家ID
            contactPersonName: this.serviceForm.contactPersonName, //专家ID
            contactPersonTel: this.serviceForm.contactPersonTel, //联系电话
            fmUrl: this.$refs.imgUpload.photoForm.fmUrl, //封面照片(子组件传递过来的值)
            content: this.serviceForm.content, //服务介绍内容
            financeItemList: this.serviceForm.financeItemList, //时间数组
            quota: this.serviceForm.quota, //名额
            address: this.serviceForm.address //地址
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
    //新增服务项目日程
    addMember() {
      this.serviceForm.financeItemList.push({
        itemName: "", //名称
        price: "", //价格
        key: Date.now()
      });
    },
    //删除服务项目日程
    removeDomain(item) {
      var index = this.serviceForm.financeItemList.indexOf(item);
      if (index !== -1) {
        this.serviceForm.financeItemList.splice(index, 1);
      }
    }
  }
};
</script>

<style scoped lang="less">
.main-box {
  background: #fff;
  min-height: 100%;
  /deep/ .el-row {
    margin-bottom: 12px;
  }
  .formBox {
    padding-right: 50px;
    /deep/ .el-form-item {
      width: 100%;
    }
    /deep/ .el-form-item__content {
      width: calc(~"100% - 120px");
    }
    /deep/ .el-select {
      width: 100%;
    }
    /deep/ .el-input--medium .el-input__inner {
      padding-right: 80px;
    }
  }
  .ties {
    width: 100%;
    float: left;
    font-size: 13px;
    color: #aaa;
    padding-left: 120px;
    margin-top: -15px;
    margin-bottom: 10px;
  }
  .title {
    font-size: 16px;
    font-weight: bold;
    padding: 20px 30px;
    border-bottom: 1px solid #eee;
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
  .content-box {
    padding-top: 20px;
    padding-left: 20px;
    .title {
      font-weight: bold;
      font-size: 16px;
      padding-left: 20px;
      margin-bottom: 20px;
      border-bottom: none;
    }
    .title::after {
      content: "";
      float: left;
      width: 3px;
      height: 16px;
      background-color: #1890ff;
      margin-right: 8px;
    }
    .project-top {
      display: flex;
      font-size: 14px;
      line-height: 26px;
      padding-left: 20px;
      .ltt {
        float: left;
        i {
          padding: 0 5px;
          color: red;
        }
      }
      .rtt {
        flex: 1;
        color: #aaa;
      }
    }
    .project-item {
      padding-left: 106px;
      margin-top: 10px;
      padding-bottom: 20px;
      .project-item-box {
        background-color: #f5f7fa;
        padding: 15px;
        width: 100%;
        float: left;
      }
    }
    .member-top {
      width: 100%;
      float: left;
      color: #000;
      font-size: 14px;
      margin-bottom: 10px;
      .ltt {
        float: left;
      }
      .rtt {
        float: right;
        color: #0971ea;
        cursor: pointer;
      }
    }
    .memberModel {
      padding-right: 30px;
    }
  }
  .show-box {
    width: 100%;
    padding-bottom: 30px;
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
  .ckeditor {
    width: 100%;
    border: 1px solid #dcdfe6;
    /deep/ .ck.ck-toolbar {
      border: none;
      border-bottom: 1px solid #dcdfe6;
      background-color: #f5f7fa;
    }
    /deep/ .ck-editor__editable {
      height: 300px;
      overflow-y: auto;
    }
  }
  .ck.ck-editor__editable_inline > :last-child {
    margin: 0 !important;
  }
  .ck.ck-editor__editable_inline > :first-child {
    margin: 0 !important;
  }
}
</style>