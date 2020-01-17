<template>
  <div class="main-box">
    <div class="content-box">
      <div class="title-top">
        <div class="title">财税服务详情</div>
        <div class="rtt">
          <el-button @click="black" type="primary">返回</el-button>
        </div>
      </div>
      <el-form label-width="140px" class="formBox">
        <el-row>
          <el-col :span="12">
            <el-form-item label="创建人：">
              <span>{{basicForm.createUserName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创建时间：">
              <span>{{basicForm.createDate}}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="类型：">
              <span>{{typeName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="服务机构：">
              <span>{{basicForm.orgName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="this.basicForm.type == 3">
            <el-form-item label="专家：">
              <span>{{basicForm.baseOrganizationMember.name}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="this.basicForm.type == 3">
            <el-form-item label="名额：">
              <span>{{basicForm.quota}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系方式：">
              <span>{{basicForm.contactPersonTel}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="this.basicForm.type == 3">
            <el-form-item label="地点：">
              <span>{{basicForm.address}}</span>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="封面：" style="margin-bottom:20px;">
              <span class="img-border">
                <img :src="basicForm.fmUrl" />
              </span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="服务介绍：">
            <!-- <span style="padding:0;margin:0;" v-html="basicForm.content"></span> -->
            <!-- 工具栏容器 -->
            <div class="ckeditor">
              <div id="toolbar-container"></div>
              <!-- 编辑器容器 -->
              <div id="editor"></div>
            </div>
          </el-form-item>
        </el-row>
      </el-form>
      <div class="show-box" v-if="this.basicForm.type == 2">
        <div class="title">服务项目</div>
        <el-form label-width="120px" class="project-item">
          <div class="project-item-box">
            <div class="memberModel" v-for="(domain,index) in serviceList" :key="domain.key">
              <div class="member-top">
                <div class="ltt">服务项目{{index+1}}</div>
              </div>
              <el-col :span="12">
                <el-form-item label="名称：">
                  <span>{{domain.itemName}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="价格：">
                  <span>{{domain.price}}</span>
                </el-form-item>
              </el-col>
            </div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { getById } from "@/api/hqgj/handheld";
import CKEditor from "@ckeditor/ckeditor5-build-decoupled-document";
export default {
  name: "see",
  data() {
    return {
      isShow: true, //是否显示
      basicForm: {}, //数据容器
      showService: false, //是否显示
      expertShow: false, //专家类容是否显示
      serviceList: [], //服务项目数组
      typeName: "", //类型名称
      editor: "" //编辑器实例
    };
  },
  created() {
    this.load();
  },
  mounted() {
    this.initCKEditor();
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
          this.serviceList = res.data.financeItemList; //服务项目
          this.editor.setData(this.basicForm.content);
          if (this.basicForm.type == 1) {
            this.typeName = "代理计账";
          }
          if (this.basicForm.type == 2) {
            this.typeName = "财税管家";
          }
          if (this.basicForm.type == 3) {
            this.typeName = "专家问诊";
          }
        }
      });
    },
    //富文本编辑器
    initCKEditor() {
      CKEditor.create(document.querySelector("#editor"))
        .then(editor => {
          editor.isReadOnly = true; //将编辑器设为只读
          this.editor = editor;
        })
        .catch(error => {
          console.error(error);
        });
    },
    //返回
    black() {
      this.$router.push({
        path: "/hqgj/ServicePack/Handheld/index"
      });
    }
  }
};
</script>

<style lang="less" scoped>
.main-box {
  background-color: #fff;
  min-height: 100%;
  /deep/ .el-row {
    margin-bottom: 0;
  }
  /deep/ .el-form-item {
    margin-bottom: 10px;
  }
  /deep/ .el-tabs__nav-scroll {
    padding: 0 20px;
  }
  .content-box {
    padding-top: 20px;
    padding-bottom: 30px;
    span {
      word-wrap: break-word;
    }
    .title {
      font-weight: bold;
      font-size: 16px;
      padding-left: 20px;
      margin-bottom: 20px;
      flex: 1;
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
    width: 100%;

    /deep/ .el-form-item {
      width: 100%;
    }
  }
  .title-top {
    display: flex;
    border-bottom: 1px solid #eee;
    padding-bottom: 20px;
    margin-bottom: 20px;
    .rtt {
      float: right;
      margin-left: 40px;
      margin-right: 30px;
      /deep/ .el-button--medium {
        border-radius: 5px;
      }
    }
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
  .project-item {
    padding-left: 106px;
    padding-right: 106px;
    margin-top: 10px;
    padding-bottom: 20px;
    display: flex;
    .project-item-box {
      padding: 15px;
      width: 100%;
      display: block;
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
  .ckeditor {
    width: 100%;
    border: 1px solid #ddd;
    /deep/ .ck-editor__editable {
      min-height: 200px;
    }
  }
}
</style>