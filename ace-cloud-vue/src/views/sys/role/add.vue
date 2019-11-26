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
            <el-form-item label="角色名称" prop="roleName">
              <el-input v-model="form.roleName"></el-input>
            </el-form-item>

            <el-form-item label="角色类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择单位类型" style="width:100%">
                <el-option
                  v-for="item in dict['40']"
                  :key="item.code"
                  :label="item.name"
                  :value="item.code"
                ></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="备注" prop="remark">
              <el-input type="textarea" :rows="2" v-model="form.remark"></el-input>
            </el-form-item>

            <el-form-item label="归属系统" prop="syid">
              <el-select v-model="form.syid" placeholder="请选择归属统" style="width:100%">
                <el-option
                  v-for="item in dict['41']"
                  :key="item.code"
                  :label="item.name"
                  :value="item.code"
                ></el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button @click="back">取消</el-button>
              <el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import { create } from "@/api/sys/role";
import { getDict } from "@/api/sys";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      dict: {},
      loading: false,
      form: {
        roleId: "",
        roleName: "",
        type: "",
        syid: "",
        remark: ""
      },
      rules: {
        name: [
          { required: true, message: "请输入角色名称", trigger: "blur" },
          { min: 2, max: 50, message: "长度在 2 到 50 个字符", trigger: "blur" }
        ],
        type: [
          { required: true, message: "请选择角色类型", trigger: "change" }
        ],
        syid: [{ required: true, message: "请选择归属系统", trigger: "change" }]
      }
    };
  },
  computed: {
    ...mapGetters(["userProp"])
  },
  created() {
    this.dictQuery();
  },
  methods: {
    dictQuery() {
      getDict("40,41").then(response => {
        this.dict = response.data;
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          create({ jsons: JSON.stringify(this.form) })
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
      this.$router.push({ path: "/sys/role" });
    }
  }
};
</script>
<style>

</style>
