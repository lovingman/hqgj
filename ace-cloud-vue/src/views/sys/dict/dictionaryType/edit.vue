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
            <el-form-item label="备注" prop="remark">
              <el-input type="textarea" :rows="2" v-model="form.remark"></el-input>
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
import { update, getById } from "@/api/sys/dictCategory";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      dict: {},
      loading: false,
      form: {
        categoryId: "",
        name: "",
        remark: ""
      },
      rules: {
        name: [
          { required: true, message: "请输入名称", trigger: "blur" },
          { min: 2, max: 50, message: "长度在 2 到 50 个字符", trigger: "blur" }
        ]
      }
    };
  },
  computed: {
    ...mapGetters(["userProp"])
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      this.loading = true;
      this.id = this.$route.query.id;
      getById(this.id).then(response => {
        this.loading = false;
        this.form = response.data;
      });
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
      this.$router.push({ path: "/sys/dict/dictionaryType" });
    }
  }
};
</script>








