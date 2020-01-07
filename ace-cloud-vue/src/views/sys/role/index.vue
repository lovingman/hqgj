<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-row :gutter="20">
        <el-col :span="16">
          <el-button-group>
            <el-button
              type="primary"
              @click="create"
              style="border-radius:5px;"
              v-if="userBtn['/portal/role/create']"
            >{{userBtn['/portal/role/create']}}</el-button>
          </el-button-group>
        </el-col>

        <el-col :span="8">
          <el-input
            placeholder="请输入角色名称"
            v-model="query.roleName"
            @change="toggleChange"
            class="input-with-select"
          >
            <el-button slot="append" icon="el-icon-search" :loading="loading" @click="handleQuery"></el-button>
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
            <el-table-column type="index" width="100"></el-table-column>
            <el-table-column prop="roleName" label="角色名称" width="250" sortable></el-table-column>
            <el-table-column prop="typeName" label="角色类型" width="150" sortable></el-table-column>
            <el-table-column prop="syid" label="系统" width="150" sortable></el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="250" sortable></el-table-column>

            <el-table-column prop="opt" label="操作" align="right">
              <template slot-scope="props">
                <el-button
                  type="text"
                  @click="assignPermissions(props.row)"
                  v-if="userBtn['/portal/role/insertRoleRes']"
                >{{userBtn['/portal/role/insertRoleRes']}}</el-button>
                <span class="strightline" v-if="userBtn['/portal/role/insertRoleRes']">|</span>

                <el-button
                  type="text"
                  @click="update(props.row)"
                  v-if="userBtn['/portal/role/update']"
                >编辑</el-button>
                <span class="strightline" v-if="userBtn['/portal/role/update']">|</span>

                <el-button
                  type="text"
                  @click="deleteById(props.row)"
                  v-if="userBtn['/portal/role/deleteById']"
                >删除</el-button>
                <span class="strightline" v-if="userBtn['/portal/role/deleteById']">|</span>

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
          <div class="section_title">基本信息</div>
        </el-header>
        <el-main
          v-loading="previewLoading"
          element-loading-text="加载中"
          element-loading-spinner="el-icon-loading"
        >
          <el-row :gutter="40">
            <el-col :span="4">角色名称</el-col>
            <el-col :span="20">{{o.roleName}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">角色类型</el-col>
            <el-col :span="20">{{o.typeName}}</el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">归属系统</el-col>
            <el-col :span="20">{{o.syid}}</el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">备注</el-col>
            <el-col :span="20">{{o.remark}}</el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">创建人</el-col>
            <el-col :span="20">{{o.name}}</el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">创建时间</el-col>
            <el-col :span="20">{{o.createTime}}</el-col>
          </el-row>
        </el-main>
      </el-container>
      <div slot="footer" class="dialog-footer"></div>

      <el-container>
        <el-header style="height:1.5rem">
          <div class="section_title">权限信息</div>
        </el-header>
        <el-main
          v-loading="previewLoading"
          element-loading-text="加载中"
          element-loading-spinner="el-icon-loading"
        >
          <el-tree
            :data="data"
            show-checkbox
            :expand-on-click-node="true"
            :check-on-click-node="false"
            :default-checked-keys="defaultCheckedKeys"
            default-expand-all
            node-key="id"
            ref="tree"
            highlight-current
            :props="defaultProps"
          ></el-tree>
        </el-main>
      </el-container>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPreviewVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  page,
  getById,
  deleteById,
  deleteByIds,
  selectRoleResByRoleId,
  getResTreeList
} from "@/api/sys/role";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      dict: {},
      currentPage: 1, //初始页
      pagesize: 10, //  每页的数据
      rows: [],
      total: 0,
      query: { roleName: "" },
      loading: false,
      previewLoading: false,
      dialogPreviewVisible: false,
      o: {},
      defaultProps: {
        label: "text",
        disabled: "id"
      },
      data: [],
      defaultCheckedKeys: []
    };
  },
  computed: {
    ...mapGetters(["userBtn"])
  },
  created() {
    this.handlePageList();
    this.getResTreeList();
  },
  methods: {
    getResTreeList() {
      this.loading = true;
      getResTreeList().then(response => {
        this.loading = false;
        this.data = response;
      });
    },
    selectRoleResByRoleId(roleId) {
      this.loading = true;
      selectRoleResByRoleId(roleId).then(response => {
        this.loading = false;
        this.defaultCheckedKeys = response.data;
      });
    },
    handleQuery: function() {
      this.currentPage = 1;
      this.handlePageList();
    },
    handleSizeChange: function(size) {
      this.pagesize = size;
      // window.console.log(this.pagesize); //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
      //window.console.log(this.currentPage); //点击第几页
      this.handlePageList();
    },
    handlePageList() {
      this.query = Object.assign(this.query, {
        pageNum: this.currentPage,
        pageSize: this.pagesize,
        totalRecord: this.total
      });
      //window.console.log(this.query);
      this.loading = true;
      page(this.query).then(response => {
        this.loading = false;
        this.rows = response.rows;
        this.total = response.total;
      });
    },
    toggleChange(roleName) {
      this.currentPage = 1;
      this.query["roleName"] = roleName;
      this.handlePageList();
    },
    create() {
      this.$router.push({ path: "/sys/role/add" });
    },
    deleteById(data) {
      //window.console.log(data);
      this.$confirm("删除" + data.roleName + ", 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info",
        center: true
      })
        .then(() => {
          this.loading = true;
          deleteById(data.roleId).then(response => {
            this.loading = false;
            this.handlePageList();
          });
        })
        .catch(() => {});
    },
    update(data) {
      //window.console.log(data);
      this.$router.push({ path: "/sys/role/edit", query: { id: data.roleId } });
    },
    preview(data) {
      //window.console.log(data);
      this.dialogPreviewVisible = true;
      this.previewLoading = true;
      getById(data.roleId).then(response => {
        this.previewLoading = false;
        this.o = response.data;
      });
      this.selectRoleResByRoleId(data.roleId);
    },
    assignPermissions(data) {
      this.$router.push({
        path: "/sys/role/assignPermissions",
        query: { id: data.roleId }
      });
    }
  }
};
</script>
<style>
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>





