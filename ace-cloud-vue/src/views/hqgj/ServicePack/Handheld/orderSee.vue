<template>
  <div class="main-box">
    <div class="content-box">
      <div class="title">基本信息</div>
      <el-form label-width="140px" class="formBox">
        <el-row>
          <el-col :span="12">
            <el-form-item label="订单号：">
              <span>{{basicForm.orderNo}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型：">
              <span>{{basicForm.typeName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="企业名称：">
              <span>{{basicForm.companyName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="姓名：">
              <span>{{basicForm.userName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系方式：">
              <span>{{basicForm.userMobile}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="服务机构：">
              <span>{{basicForm.orgName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="创建时间：">
              <span>{{basicForm.createDate}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="basicForm.type == 1">
            <el-form-item label="抵扣积分：">
              <span>500积分</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="basicForm.type == 3">
            <el-form-item label="专家：">
              <span>{{basicForm.mName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="basicForm.type == 2">
            <el-form-item label="服务项目：">
              <span>{{basicForm.itemName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="basicForm.type == 2">
            <el-form-item label="服务金额：">
              <span>{{basicForm.price}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态：">
              <span>{{basicForm.statusName}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="basicForm.status == 3">
            <el-form-item label="完成时间：">
              <span>{{basicForm.modifyDate}}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="basicForm.status == 1">
            <el-form-item label="取消时间：">
              <span>{{basicForm.modifyDate}}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
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
import { ordergetById } from "@/api/hqgj/handheld";
export default {
  name: "see",
  data() {
    return {
      isShow: true, //是否显示
      basicForm: {}, //数据容器
      showService: false, //是否显示
      expertShow: false, //专家类容是否显示
      serviceList: [] //服务项目数组
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
      ordergetById(this.id).then(res => {
        if (res.status == 1) {
          console.log(res);
          this.basicForm = res.data; //基本信息
          this.serviceList = res.data.financeItemList; //服务项目
          if (this.basicForm.type == 1) {
            this.basicForm.typeName = "代理计账";
          }
          if (this.basicForm.type == 2) {
            this.basicForm.typeName = "财税管家";
          }
          if (this.basicForm.type == 3) {
            this.basicForm.typeName = "专家问诊";
          }
          if (this.basicForm.status == 1) {
            this.basicForm.statusName = "已取消";
          }
          if (this.basicForm.type == 3) {
            this.basicForm.statusName = "已完成";
          }
        }
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
    width: 100%;

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