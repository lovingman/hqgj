<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-row :gutter="20">
        <el-col :span="2">
          <el-button-group>
            <el-button
              type="primary"
              @click="create"
              v-if="userBtn['/portal/user/create']"
            >{{userBtn['/portal/user/create']}}</el-button>
          </el-button-group>
        </el-col>
        <el-col :span="5">
            <el-radio-group size="medium" v-model="query.status" @change="toggleTab">
            <el-radio-button
              :label="item.id"
              v-for="item in statusData"
              :key="item.id"
            >{{item.name}}</el-radio-button>
          </el-radio-group>
           </el-col>
  <el-col :span="5">
          <el-cascader
                placeholder="请选择行政区划"
                v-model="areaCodes"
                :options="areaCodeOptions"
                :props="areaCodeProps"
                clearable
                change-on-select
                filterable style="width:100%"/>



        </el-col>

        <el-col :span="12">
          <el-input
            placeholder="请输入用户名称"
            v-model="query.userName"
            @change="toggleChange"
           class="input-with-select"
          >
            <el-select class="type" clearable slot="prepend"
                v-model="query.corpId"
                filterable
                remote
                reserve-keyword
                placeholder="请选择所在组织"
                :remote-method="corpQuery"
                :loading="loading">
                <el-option
                v-for="item in corps"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>

            <el-button
              slot="append"
              icon="el-icon-search"
              :loading="loading"
              @click="handleQuery"
            ></el-button>
          </el-input>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-table
            ref="filterTable"
            :data="rows"
            style="width: 100%"
            v-loading="loading"
            element-loading-text="加载中"
            element-loading-spinner="el-icon-loading"
          >
            <el-table-column type="index" width="60"></el-table-column>
            <el-table-column prop="name" label="姓名" width="100" sortable></el-table-column>
            <el-table-column prop="account" label="账号" width="230" sortable></el-table-column>
            <el-table-column prop="corpName" label="单位" width="250" sortable></el-table-column>
            <el-table-column prop="createTime" label="创建时间"  width="180" sortable></el-table-column>

            <el-table-column prop="opt" label="操作" align="right">
              <template slot-scope="props">

                 <el-button
                  type="text"
                  @click="assignRoles(props.row)"
                  v-if="userBtn['/portal/user/assignRoles']"
                >{{userBtn['/portal/user/assignRoles']}}</el-button>
                <span class="strightline" v-if="userBtn['/portal/user/assignRoles']">|</span>

                 <el-button
                  type="text"
                  @click="initPwd(props.row)"
                  v-if="userBtn['/portal/user/initPwd']"
                >{{userBtn['/portal/user/initPwd']}}</el-button>
                <span class="strightline" v-if="userBtn['/portal/user/initPwd']">|</span>

                <el-button
                  type="text"
                  @click="update(props.row)"
                  v-if="userBtn['/portal/user/update']"
                >编辑</el-button>
                <span class="strightline" v-if="userBtn['/portal/user/update']">|</span>

                <el-button
                  type="text"
                  @click="deleteById(props.row)"
                  v-if="userBtn['/portal/user/deleteById']"
                >删除</el-button>
                <span class="strightline" v-if="userBtn['/portal/user/deleteById']">|</span>

                <el-button type="text" @click="preview(props.row)">详细</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="4">
          <template>
            <div class="page-total">
              总记录数
              <span class="page-total-num">{{total}}</span>
            </div>
          </template>
        </el-col>
        <el-col :span="20">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pagesize"
            :total="total"
            background
            layout="prev, pager, next"
          ></el-pagination>
        </el-col>
      </el-row>
    </el-card>

    <el-dialog title="详细" :visible.sync="dialogPreviewVisible" width="80%">
      <el-container>
        <el-header style="height:1.5rem">

        </el-header>
        <el-main
          v-loading="previewLoading"
          element-loading-text="加载中"
          element-loading-spinner="el-icon-loading"
        >

          <el-row :gutter="40">
            <el-col :span="24"><div class="section_title">基本信息</div></el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">姓名</el-col>
            <el-col :span="20">{{o.name}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">身份证号</el-col>
            <el-col :span="20">{{o.idCard}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">组织</el-col>
            <el-col :span="20">{{o.corpName}}</el-col>
          </el-row>

           <el-row :gutter="40">
            <el-col :span="4">所属行政区划</el-col>
            <el-col :span="20">{{o.areaName}}</el-col>
          </el-row>


           <el-row :gutter="40">
            <el-col :span="244"><div class="section_title">联系信息</div></el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">手机号</el-col>
            <el-col :span="20">{{o.mobile}}</el-col>
          </el-row>
           <el-row :gutter="40">
            <el-col :span="4">邮箱</el-col>
            <el-col :span="20">{{o.email}}</el-col>
          </el-row>
           <el-row :gutter="40">
            <el-col :span="244"><div class="section_title">账号信息</div></el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">账号</el-col>
            <el-col :span="20">{{o.account}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">锁定标识</el-col>
            <el-col :span="20">{{o.locked}}</el-col>
          </el-row>

           <el-row :gutter="40">
            <el-col :span="4">最后锁定时间</el-col>
            <el-col :span="20">{{o.lockedTime}}</el-col>
          </el-row>


           <el-row :gutter="40">
            <el-col :span="244"><div class="section_title">创建信息</div></el-col>
          </el-row>


          <el-row :gutter="40">
            <el-col :span="4">创建时间</el-col>
            <el-col :span="20">{{o.createTime}}</el-col>
          </el-row>
        </el-main>
      </el-container>
      <div slot="footer" class="dialog-footer">

      </div>


      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPreviewVisible = false">关闭</el-button>
      </div>
    </el-dialog>


    <el-dialog title="密码重置" :visible.sync="dialogInitPwdVisible" width="50%">
      <el-container  v-loading="initPwdLoading"
          element-loading-text="请求中"
          element-loading-spinner="el-icon-loading">
        <el-header style="height:1.5rem">
          <div class="section_title">{{form.name}}</div>
        </el-header>
        <el-main>
          <el-form :rules="rules" ref="ruleForm" :model="form" label-width="80px" style="width:80%">
            <el-form-item label="新密码" prop="password">
              <el-input  v-model="form.password" show-password></el-input>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>


      <div slot="footer" class="dialog-footer">
         <el-button @click="dialogInitPwdVisible = false">取消</el-button>
        <el-button type="primary" :loading="initPwdLoading" @click="submitForm('ruleForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { page, getById, deleteById, initPwd,updateStatus,assignRoles,getAllRoles,getMyRoles} from "@/api/sys/user";
import { getAreaTree,getDict } from "@/api/sys";
import { getList } from "@/api/sys/corp";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
     dict:{},
     corps:[],
      areaCodeOptions: [],
      areaCodeProps: {
        value: "id",
        label: "text"
      },
      statusData: [
        { id: "", name: "全部" },
        { id: "1", name: "正常" },
        { id: "2", name: "注销" }
      ],
      currentPage: 1, //初始页
      pagesize: 10, //  每页的数据
      rows: [],
      total: 0,
      query: { name: "", corpId: "",areaCode:"",status:"1"},
      loading: false,
      previewLoading: false,
      dialogPreviewVisible: false,
      dialogInitPwdVisible: false,
      initPwdLoading:false,
      o: {},
      areaCodes:[],
      form:{
        userId:"",
        password:"",
        name:""
      },
      rules: {
        password: [
          { required: true, message: "请输入新密码长度在6位以上", trigger: "blur" },
          { min: 6, max: 30, message: "长度在 6 到 30 个字符", trigger: "blur" }
        ]
      }
    };
  },
  computed: {
    ...mapGetters(["userBtn"])
  },
  created() {
    this.handlePageList();
    this.dictQuery();
    this.AreaCodeQuery();
    this.corpQuery();
  },
  methods: {
      corpQuery(query) {
         getList(query)
            .then(response => {
                this.corps = response.data;
            })
    },
     AreaCodeQuery() {
       const userProp = this.$store.getters.userProp;
      getAreaTree({ pid: userProp.areaCode, type: 1, hasSelf: "true" })
        .then(response => {
          this.areaCodeOptions = response.data;
        })

    },
    dictQuery(){
        getDict("73")
        .then(response => {
          this.dict=response.data;
        })
    },
    handleSizeChange: function(size) {
      this.pagesize = size;
      window.console.log(this.pagesize); //每页下拉显示数据
    },
    handleQuery:function(){
      this.currentPage = 1;
      this.handlePageList();
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
      window.console.log(this.currentPage); //点击第几页
      this.handlePageList();
    },
    handlePageList() {
      this.query.areaCode="";
      for(let e of this.areaCodes){
          this.query.areaCode=e;
      }
      this.query = Object.assign(this.query, {
        pageNum: this.currentPage,
        pageSize: this.pagesize,
        totalRecord:this.total
      });
      window.console.log(this.query);
      this.loading = true;
      page(this.query)
        .then(response => {
          this.loading = false;
          this.rows = response.rows;
          this.total = response.total;
        })
    },
    toggleChange(name) {
      this.query["name"] = name;
      this.currentPage = 1;
      this.handlePageList();
    },
      toggleTab(status) {
      this.query["status"] = status;
      this.currentPage = 1;
      this.handlePageList();
    },
    create() {
      this.$router.push({ path: "/sys/user/add" });
    },
    deleteById(data) {
      window.console.log(data);
      this.$confirm("删除" + data.name + ", 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info",
        center: true
      })
        .then(() => {
          this.loading = true;
          deleteById(data.userId)
            .then(response => {
              this.loading = false;
              this.handlePageList();
            })
        })
        .catch(() => {});
    },
    update(data) {
      window.console.log(data);
      this.$router.push({ path: "/sys/user/edit", query: { id: data.userId } });
    },
    preview(data) {
      this.dialogPreviewVisible = true;
      this.previewLoading = true;
      getById(data.userId)
        .then(response => {
          this.previewLoading = false;
          this.o = response.data;
        })
      this.selectuserResByuserId(data.userId);
    },
    assignRoles(data){
        this.$router.push({ path: "/sys/user/assignRoles", query: { id: data.userId } });
    },
    initPwd(data){
        this.dialogInitPwdVisible=true;
        this.form.userId=data.userId;
        this.form.name=data.name;
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.initPwdLoading = true;
            initPwd(this.form)
            .then(response => {
              this.initPwdLoading = false;
              this.$confirm(response.message , "提示", {
                  confirmButtonText: "确定",
                  type: "success",
                  center: true
              })
              .then(() => {
                  this.dialogInitPwdVisible=false;
              })
            })
            .catch(error => {
              this.initPwdLoading = false;
            });
        } else {
          return false;
        }
      });
    },
  }
};
</script>
<style>
 .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  .type {
  width: 15rem;
}
</style>



