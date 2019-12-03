<template>
  <div class="main-box">
    <div class="title">创建服务包</div>
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
            <el-form-item label="类型：" prop="typeName">
              <el-select v-model="serviceForm.typeName" @change="changeType" placeholder="请选择">
                <el-option
                  v-for="item in typeArr"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <div class="ties" v-if="tieShow">选择该类型，用户可免费享受咨询服务</div>
        </el-row>
        <!-- 代理财政是否显示 -->
        <div v-if="financeShow" class="show-box">
          <el-row>
            <el-col :span="12">
              <el-form-item label="标题：" prop="title">
                <el-input
                  v-model="serviceForm.title"
                  maxlength="50"
                  show-word-limit
                  placeholder="请输入标题"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="服务机构：" prop="mechanism">
                <el-select
                  v-model="serviceForm.mechanism"
                  @change="changeService"
                  placeholder="请选择"
                >
                  <el-option
                    v-for="item in mechanismArr"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item label="封面：" prop="photo">
              <el-upload
                action="https://jsonplaceholder.typicode.com/posts/"
                list-type="picture-card"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
              >
                <i class="el-icon-plus"></i>
                <div class="el-upload__tip" slot="tip">支持jpg.png，大小不超过10M</div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt />
              </el-dialog>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="服务介绍：" prop="introduce">
              <el-input
                type="textarea"
                placeholder="详细介绍服务内容"
                v-model="serviceForm.introduce"
                rows="10"
              ></el-input>
            </el-form-item>
          </el-row>
        </div>
        <!-- end -->
        <!-- 财税管理 -->
        <div class="show-box" v-if="financeTaxShow">
          <el-row>
            <el-col :span="12">
              <el-form-item label="标题：" prop="title">
                <el-input
                  v-model="serviceForm.title"
                  maxlength="50"
                  show-word-limit
                  placeholder="请输入标题"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="服务机构：" prop="mechanism">
                <el-select
                  v-model="serviceForm.mechanism"
                  @change="changeService"
                  placeholder="请选择"
                >
                  <el-option
                    v-for="item in mechanismArr"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item label="封面：" prop="photo">
              <el-upload
                action="https://jsonplaceholder.typicode.com/posts/"
                list-type="picture-card"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
              >
                <i class="el-icon-plus"></i>
                <div class="el-upload__tip" slot="tip">支持jpg.png，大小不超过10M</div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt />
              </el-dialog>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="服务介绍：" prop="introduce">
              <el-input
                type="textarea"
                placeholder="详细介绍服务内容"
                v-model="serviceForm.introduce"
                rows="10"
              ></el-input>
            </el-form-item>
          </el-row>
          <!-- 服务项目 -->
          <el-row>
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
                  v-for="(domain,index) in serviceForm.domains"
                  :key="domain.key"
                >
                  <div class="member-top">
                    <div class="ltt">服务项目{{index+1}}</div>
                    <div class="rtt" @click="removeDomain(domain)">删除</div>
                  </div>

                  <el-col :span="12">
                    <el-form-item
                      :label="'名称'"
                      :prop="'domains.'+index+'.serviceName'"
                      :rules="serviceRules.serviceName"
                      :key="domain.key+'01'"
                    >
                      <el-input
                        v-model="domain.serviceName"
                        show-word-limit
                        maxlength="20"
                        placeholder="请输入名称"
                      ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item :label="'价格'" :rules="serviceRules.prices" :key="domain.key+'01'">
                      <el-input v-model="domain.prices" placeholder="请输入价格"></el-input>
                    </el-form-item>
                  </el-col>
                </div>
              </div>
              <el-button type="primary" @click="addMember" style="margin-top:20px;">
                <i class="el-icon-plus"></i>添加日程
              </el-button>
            </div>
          </el-row>
          <!-- end -->
        </div>
        <!-- end -->
        <!-- 专家问诊 -->
        <div class="show-box" v-if="expertShow">
          <el-row>
            <el-col :span="12">
              <el-form-item label="专家：" prop="expert">
                <el-button class="get-address">
                  <i class="el-icon-plus"></i>选择专家
                </el-button>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="名额：" prop="quota">
                <el-input v-model="serviceForm.quota" placeholder="请输入免费咨询名额"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="联系方式：" prop="contact">
                <el-input v-model="serviceForm.contact" placeholder="请输入联系方式"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="地点：" prop="place">
                <el-input v-model="serviceForm.place" placeholder="请输入地点"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item label="封面：" prop="photo">
              <el-upload
                action="https://jsonplaceholder.typicode.com/posts/"
                list-type="picture-card"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
              >
                <i class="el-icon-plus"></i>
                <div class="el-upload__tip" slot="tip">支持jpg.png，大小不超过10M</div>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt />
              </el-dialog>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="服务介绍：" prop="serviceIntroduce">
              <el-input
                type="textarea"
                :rows="10"
                placeholder="详细介绍服务内容"
                v-model="serviceForm.serviceIntroduce"
              ></el-input>
            </el-form-item>
          </el-row>
          <div
            class="memberModel"
            v-for="(timeModel,index) in serviceForm.timeModels"
            :key="timeModel.key"
          >
            <el-row>
              <el-col :span="12">
                <el-form-item
                  :label="'服务时间'"
                  :prop="'timeModels.'+index+'.'"
                  :rules="serviceRules.serviceTime"
                  :key="timeModel.key+'01'"
                >
                  <el-date-picker
                    v-model="serviceForm.serviceTime"
                    type="datetimerange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                  ></el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
          </div>
          <el-button type="primary" @click="addTime" style="margin-left:120px;">
            <i class="el-icon-plus"></i>添加时间
          </el-button>
        </div>
        <!-- end -->
      </el-form>
    </div>
    <!-- 底部按钮 -->
    <div class="footer">
      <div class="footer-flex">
        <el-button @click="black">取消</el-button>
        <el-button @click="submission" type="primary">提交</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "add",
  data() {
    return {
      financeShow: false, //代理财政是否显示
      financeTaxShow: false, //财税管理是否显示
      expertShow: false, //专家问诊是否显示
      dialogVisible: false, //预览弹窗是否显示
      dialogImageUrl: "", //显示预览图片
      tieShow: false, //提示是否显示
      //服务包类型
      typeArr: [
        {
          value: "1",
          label: "代理计财"
        },
        {
          value: "2",
          label: "财税管理"
        },
        {
          value: "3",
          label: "专家问诊"
        }
      ],
      mechanismArr: [
        {
          value: "1",
          label: "机构1"
        },
        {
          value: "2",
          label: "机构2"
        },
        {
          value: "3",
          label: "机构3"
        }
      ],
      //服务包容器
      serviceForm: {
        typeName: "", //类型
        title: "", //标题
        mechanism: "", //服务机构
        photo: "", //封面
        introduce: "", //详细内容
        project: "", //服务项目
        serviceName: "", //服务名称
        prices: "", //价格
        domains: [], //新增服务项目数组
        timeModels: [], ///新增服务时间数组
        quota: "", //名额
        contact: "", //联系方式
        place: "", //地点
        serviceIntroduce: "", //介绍
        serviceTime: "" //服务时间
      },
      serviceRules: {
        typeName: [
          {
            required: true,
            message: "请选择类型",
            trigger: "change"
          }
        ],
        title: [
          {
            required: true,
            message: "请输入标题，字数在50字以内",
            trigger: "blur"
          }
        ],
        mechanism: [
          {
            required: true,
            message: "请选择服务机构",
            trigger: "change"
          }
        ],
        introduce: [
          {
            required: true,
            message: "请输入详细介绍服务内容",
            trigger: "blur"
          }
        ],
        serviceIntroduce: [
          {
            required: true,
            message: "请输入详细介绍服务内容",
            trigger: "blur"
          }
        ],
        serviceName: [
          {
            required: true,
            message: "请输入名称，字数在20字以内",
            trigger: "blur"
          }
        ],
        contact: [
          {
            required: true,
            message: "请输入联系方式",
            trigger: "blur"
          }
        ],
        place: [
          {
            required: true,
            message: "请输入地点",
            trigger: "blur"
          }
        ],
        serviceTime: [
          {
            required: true,
            message: "请选择服务时间",
            trigger: "change"
          }
        ]
      }
    };
  },
  methods: {
    //类型选择
    changeType(value) {
      if (value == 1) {
        this.financeShow = true;
        this.financeTaxShow = false;
        this.expertShow = false;
      }
      if (value == 2) {
        this.financeTaxShow = true;
        this.financeShow = false;
        this.expertShow = false;
      }
      if (value == 3) {
        this.expertShow = true;
        this.financeShow = false;
        this.financeTaxShow = false;
        this.tieShow = true;
      }
    },
    //服务机构选择
    changeService() {},

    //封面照片
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    //取消返回
    black() {
      this.$router.push({ path: "/hqgj/ServicePack/Handheld" });
    },
    //提交
    submission() {},
    //新增服务项目日程
    addMember() {
      this.serviceForm.domains.push({
        serviceName: "", //名称
        key: Date.now()
      });
    },
    //删除服务项目日程
    removeDomain(item) {
      var index = this.serviceForm.domains.indexOf(item);
      if (index !== -1) {
        this.serviceForm.domains.splice(index, 1);
      }
    },
    //添加服务时间
    addTime() {
      this.serviceForm.timeModels.push({
        serviceTime: "", //服务时间
        key: Date.now()
      });
    }
  }
};
</script>

<style scoped lang="less">
.main-box {
  background: #fff;
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
  }
  .ties {
    width: 100%;
    float: left;
    font-size: 13px;
    color: #aaa;
    padding-left: 120px;
    margin-top: -15px;
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
}
</style>