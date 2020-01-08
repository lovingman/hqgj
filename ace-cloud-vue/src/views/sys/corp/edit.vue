<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-container>
        <el-header style="height:1.5rem">
          <div class="section_title">基本信息</div>
        </el-header>
        <el-main
          v-loading="loading"
          element-loading-text="请求中"
          element-loading-spinner="el-icon-loading"
        >
          <el-form :rules="rules" ref="ruleForm" :model="form" label-width="80px" style="width:50%">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name"></el-input>
            </el-form-item>

            <!--<el-form-item label="单位类型" prop="type">-->
              <!--<el-select v-model="form.type" placeholder="请选择单位类型" style="width:100%">-->
                <!--<el-option-->
                  <!--v-for="item in dict['73']"-->
                  <!--:key="item.code"-->
                  <!--:label="item.name"-->
                  <!--:value="item.code"-->
                <!--&gt;</el-option>-->
              <!--</el-select>-->
            <!--</el-form-item>-->

            <el-form-item label="行政区划" prop="areaCode">
              <el-cascader
                placeholder="请选择行政区划"
                v-model="form.areaCode"
                :options="areaCodeOptions"
                :props="areaCodeProps"
                clearable
                change-on-select
                filterable
                style="width:100%"
              ></el-cascader>
            </el-form-item>

            <el-form-item label="归属单位" prop="pid">
              <el-select
                v-model="form.pid"
                filterable
                remote
                reserve-keyword
                placeholder="请输入归属单位名称"
                :remote-method="pidQuery"
                style="width:100%"
              >
                <el-option
                  v-for="item in pids"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button @click="back" style="border-radius:5px;">取消</el-button>
              <el-button type="primary" @click="submitForm('ruleForm')" style="border-radius:5px;">保存</el-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import { update, getList, getById } from "@/api/sys/corp";
import { getAreaTree, getDict } from "@/api/sys";
import { mapGetters } from "vuex";
import { toArray } from "@/utils/areaCode-util";
export default {
  data() {
    return {
      loading: false,
      id: "",
      dict: {},
      areaCodeOptions: [],
      areaCodeProps: {
        value: "id",
        label: "text"
      },
      pids: [],
      form: {
        id: "",
        name: "",
        areaCode: [],
        // type: "",
        pid: ""
      },
      rules: {
        name: [
          { required: true, message: "请输入单位名称", trigger: "blur" },
          { min: 2, max: 50, message: "长度在 2 到 50 个字符", trigger: "blur" }
        ],
        // type: [
        //   { required: true, message: "请选择单位类型", trigger: "change" }
        // ],
        areaCode: [
          { required: true, message: "请选择行政区划", trigger: "change" }
        ],
        pid: [{ required: true, message: "请选择归属单位", trigger: "change" }]
      }
    };
  },
  computed: {
    ...mapGetters(["userProp"])
  },
  created() {
    this.AreaCodeQuery();
    this.pidQuery();
    // this.dictQuery();
    this.load();
  },
  methods: {
    load() {
      this.loading = true;
      this.id = this.$route.query.id;
      getById(this.id).then(response => {
        this.loading = false;
        this.form = response.data;
        this.form.areaCode = toArray(response.data.areaCode);
      });
    },
    AreaCodeQuery() {
      const userProp = this.$store.getters.userProp;
      window.console.log(userProp);
      getAreaTree({ pid: userProp.areaCode, type: 3, hasSelf: "true" }).then(
        response => {
          this.areaCodeOptions = response.data;
        }
      );
    },
    // dictQuery() {
    //   getDict("73").then(response => {
    //     this.dict = response.data;
    //   });
    // },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.form.areaCode = this.form.areaCode.pop();
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
      this.$router.push({ path: "/sys/corp" });
    },
    pidQuery(query) {
       getList(query).then(response => {
          this.pids = response.data;
        });
    }
  }
};
</script>
