<template>
  <div class="app-container">
    <el-card shadow="never">


      <el-container>
        <el-header style="height:1.5rem">

        </el-header>
        <el-main
          v-loading="loading"
          element-loading-text="请求中"
          element-loading-spinner="el-icon-loading"
        >
          <el-form :rules="rules" ref="ruleForm" :model="form" label-width="80px" style="width:50%">
            <div class="el-form-item el-form-item--medium">
              <div class="section_title">基本信息</div>
            </div>
            <el-form-item label="姓名" prop="name">
              <el-input  v-model="form.name"></el-input>
            </el-form-item>

  <el-form-item label="性别" prop="sex">
                  <el-radio-group v-model="form.sex">
    <el-radio   v-for="item in dict['4']" :key="item.code" :label="item.code">{{item.name}}</el-radio>
  </el-radio-group>
            </el-form-item>
             <el-form-item label="身份证号" prop="idCard">
              <el-input  v-model="form.idCard"></el-input>
            </el-form-item>


             <el-form-item label="组织" prop="corpId">


              <el-select v-model="form.corpId" placeholder="请选择所在组织" style="width:100%"
                filterable
                remote
                reserve-keyword
                :remote-method="corpQuery"
                :loading="loading">
                <el-option
                v-for="item in corps"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>

            </el-form-item>






             <div class="el-form-item el-form-item--medium">
              <div class="section_title">联系方式 </div>
            </div>


            <el-form-item label="手机号" prop="mobile">
              <el-input  v-model="form.mobile"></el-input>
            </el-form-item>

                <el-form-item label="邮箱" prop="email">
              <el-input  v-model="form.email"></el-input>
            </el-form-item>




    <el-form-item label="状态" prop="status">
         <el-switch v-model="form.status" name="status" active-text="正常" inactive-text="注销" active-value="1" inactive-value="2"></el-switch>
                </el-form-item>






            <el-form-item>
              <el-button @click="back">取消</el-button>
              <el-button type="primary"  @click="submitForm('ruleForm')">保存</el-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>


    </el-card>
  </div>
</template>

<script>
import { update,getById } from "@/api/sys/user";
import { getDict } from "@/api/sys";
import { mapGetters } from 'vuex';
import { getList } from "@/api/sys/corp";
export default {
  data() {
    return {
      dict:{},
      corps:[],
      loading: false,
      form: {
        userId: "",
        name: "",
        corpId:"",
        deptId:"",
        account: "",
        password: "",
        mobile:"",
        email:"",
        sex:"",
        idCard:"",
        birthday:"",
        status:'1'

      },
      rules: {
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { min: 2, max: 50, message: "长度在 2 到 20 个字符", trigger: "blur" }
        ],
        corpId: [
          { required: true, message: "请选择所属单位", trigger: "change" }
        ],
        account: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { min: 3, max: 30, message: "长度在 3 到 30 个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 100, message: "长度在 6 到 100 个字符", trigger: "blur" }
        ],
        //  idCard: [
        //   { required: true, message: "请输入身份证号", trigger: "blur" },
        //   { min: 18, max: 18, message: "长度 18 个字符", trigger: "blur" }
        // ]
      },
      statusData: [
        { code: "1", name: "正常" },
        { code: "2", name: "注销" }
      ],
    };
  },
   computed: {
    ...mapGetters([
      'userProp'
    ])
  },
  created() {
    this.dictQuery();
    this.corpQuery();
    this.load();
  },
  methods: {
    load(){
        this.loading = true;
        this.id = this.$route.query.id;
        getById(this.id)
        .then(response => {
          this.loading = false;
          this.form=response.data;
          this.corps.push({value:response.data.corpId,label:response.data.corpName});
          //window.console.log(this.form);
        })
    },
    dictQuery(){
        getDict("4")
        .then(response => {
          this.dict=response.data;
        })
    },
    corpQuery(query) {
         getList(query)
            .then(response => {
                this.corps = response.data;
            })
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
            update({ jsons: JSON.stringify(this.form) })
            .then(response => {
              this.loading = false;
              this.$confirm(response.message + " 是否返回?", "提示", {
                  confirmButtonText: "确定",
                  cancelButtonText: "取消",
                  type: "success",
                  center: true
              })
              .then(() => {
                  this.back();
              })
              .catch(() => {});
            })
            .catch(error => {
              this.loading = false;

            });
        } else {
          return false;
        }
      });
    },
    back() {
      this.$router.push({ path: "/sys/user" });
    }
  }
};
</script>
<style>
</style>



