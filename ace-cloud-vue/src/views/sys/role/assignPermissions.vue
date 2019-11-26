<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-container>
        <el-header style="height:1.5rem">
          <div class="section_title">{{o.roleName}}的权限</div>
        </el-header>
        <el-main
          v-loading="loading"
          element-loading-text="请求中"
          element-loading-spinner="el-icon-loading"
        >
          <el-tree
            :data="data"
            show-checkbox
            :expand-on-click-node="false"
            :check-on-click-node="true"
            :default-checked-keys="defaultCheckedKeys"
            default-expand-all
            node-key="id"
            ref="tree"
            highlight-current
            :props="defaultProps"
          ></el-tree>
        </el-main>
        <el-footer>
          <div class="buttons">
            <el-button @click="back">取消</el-button>
            <el-button type="primary" @click="submitForm()">保存</el-button>
          </div>
        </el-footer>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import {
  getResTreeList,
  insertRoleRes,
  selectRoleResByRoleId,
  getById
} from "@/api/sys/role";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      id: "",
      defaultProps: {
        label: "text"
      },
      loading: false,
      data: [],
      defaultCheckedKeys: [],
      o: {},
      rules: {}
    };
  },
  computed: {
    ...mapGetters(["userProp"])
  },
  created() {
    this.load();
    this.getResTreeList();
    this.selectRoleResByRoleId();
  },
  methods: {
    load() {
      this.loading = true;
      this.id = this.$route.query.id;
      getById(this.id).then(response => {
        this.loading = false;
        this.o = response.data;
      });
    },
    getResTreeList() {
      this.loading = true;
      getResTreeList().then(response => {
        this.loading = false;
        this.data = response;
      });
    },
    selectRoleResByRoleId() {
      this.loading = true;
      this.id = this.$route.query.id;
      selectRoleResByRoleId(this.id).then(response => {
        this.loading = false;
        this.defaultCheckedKeys = response.data;
      });
    },
    submitForm(formName) {
      let checkedKeys = this.$refs.tree.getCheckedKeys();
      let halfCheckedKeys = this.$refs.tree.getHalfCheckedKeys();
      let allCheckedKeys = checkedKeys.concat(halfCheckedKeys);
      //window.console.log(allCheckedKeys);
      this.loading = true;
      this.id = this.$route.query.id;

      insertRoleRes({ roleId: this.id, resId: allCheckedKeys.join(",") })
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
    },
    back() {
      this.$router.push({ path: "/sys/role" });
    }
  }
};
</script>
<style>
</style>



