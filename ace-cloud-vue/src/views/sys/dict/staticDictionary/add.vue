<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-container>
        <el-header style="height:1.5rem">
          <div class="section_title">静态字典</div>
        </el-header>
        <el-main
          v-loading="loading"
          element-loading-text="请求中"
          element-loading-spinner="el-icon-loading"
        >
          <el-form
            :rules="rules"
            ref="ruleForm"
            :model="addform"
            label-width="80px"
            style="width:100%"
          >
            <el-form-item label="字典类型" prop="categoryId">
              <el-select v-model="addform.categoryId" clearable placeholder="请选择字典类型" style="width:100%">
                <el-option
                        v-for="item in dict"
                        :key="item.categoryId"
                        :label="item.name"
                        :value="item.categoryId"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="名称" prop="name">
              <el-input v-model="addform.name"></el-input>
            </el-form-item>
            <el-form-item label="编号" prop="code">
              <el-input v-model="addform.code"></el-input>
            </el-form-item>
            <el-form-item class="submit">
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
  import { create} from "@/api/sys/dict";
import { Categorypage} from "@/api/sys/dictCategory";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      dict: {},
      loading: false,
      fenye:{
        currentPage: 1, // 列表显示初始页
        pagesize: 100, //  列表显示每页的数据
        total: 0,
      },
      addform: {
        categoryId: "",
        name: "",
        code: ""
      },
      rules: {
        name: [
          {
            required: true,
            message: "请输入字典名称",
            trigger: "blur"
          },
          {
            min: 2,
            max: 50,
            message: "长度在 2 到 50 个字符",
            trigger: "blur"
          }
        ],
        categoryId: [
          {
            required: true,
            message: "请选择字典类型",
            trigger: "change"
          }
        ],
        code: [
          {
            required: true,
            message: "请输入编号",
            trigger: "blur"
          }
        ],
      }
    };
  },
  computed: {
    ...mapGetters(["userProp"])
  },
  created() {
    this.listQuery();
  },
  methods: {
    listQuery() {
      this.query = Object.assign({
        pageNum: this.fenye.currentPage,
        pageSize: this.fenye.pagesize,
        totalRecord: this.fenye.total
      });
      Categorypage(this.query).then(response => {
        this.dict = response.rows;
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          create({jsons: JSON.stringify(this.addform)})
            .then(response => {
              this.loading = false;
              this.$confirm(response.message + " 是否返回?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "success",
                center: true
              }).then(() => {
                this.back();
              }).catch(() => {});
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
      this.$router.push({
        path: "/sys/dict/staticDictionary"
      });
    },
  }
};
</script>
<style>
</style>
