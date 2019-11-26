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
          <el-form :rules="rules" ref="ruleForm" :model="form" label-width="80px" style="width:60%">
            <el-form-item label="类型" prop="resourcesType">
              <el-radio-group v-model="form.resourcesType">
                <el-radio
                  v-for="item in dict['44']"
                  :key="item.code"
                  :label="item.code"
                >{{item.name}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="父级编号" prop="parentResourcesId">
              <el-input v-model="form.parentResourcesId"></el-input>
            </el-form-item>

            <el-form-item label="名称" prop="resourcesName">
              <el-input v-model="form.resourcesName"></el-input>
            </el-form-item>

            <el-form-item label="地址" prop="resourcesUrl">
              <el-input v-model="form.resourcesUrl"></el-input>
            </el-form-item>
            <el-form-item label="图标" prop="resourcesIcon">
              <el-input v-model="form.resourcesIcon"></el-input>
            </el-form-item>
            <el-form-item class="submit">
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
import { update, getById } from "@/api/sys/resource";
import { getDict } from "@/api/sys";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      dict: {},
      loading: false,
      form: {
        resourcesId: "",
        parentResourcesId: this.$route.query.id,
        resourcesType: "",
        resourcesName: "",
        resourcesUrl: "",
        resourcesIcon: ""
      },
      rules: {
        resourcesName: [
          {
            required: true,
            message: "请输入名称",
            trigger: "blur"
          },
          {
            min: 2,
            max: 50,
            message: "长度在 2 到 50 个字符",
            trigger: "blur"
          }
        ],
        resourcesUrl: [
          {
            required: true,
            message: "请输入地址",
            trigger: "blur"
          },
          {
            min: 2,
            max: 50,
            message: "长度在 2 到 100 个字符",
            trigger: "blur"
          }
        ],

        resourcesType: [
          {
            required: true,
            message: "请选择类型",
            trigger: "change"
          }
        ],
        parentResourcesId: [
          {
            required: true,
            message: "请输入父编号",
            trigger: "change"
          }
        ]
      }
    };
  },
  computed: {
    ...mapGetters(["userProp"])
  },
  created() {
    this.dictQuery();
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
    dictQuery() {
      getDict("44").then(response => {
        this.dict = response.data;
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          update({
            jsons: JSON.stringify(this.form)
          }).then(response => {
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
          });
        } else {
          return false;
        }
      });
    },
    back() {
      this.$router.push({
        path: "/sys/resources"
      });
    }
  }
};
</script>
<style>
</style>
