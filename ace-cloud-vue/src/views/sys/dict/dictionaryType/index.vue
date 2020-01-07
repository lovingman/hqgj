<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-row :gutter="20">
        <el-col :span="14">
          <el-button-group>
            <el-button
              type="primary"
              @click="create"
              style="border-radius:5px;"
              v-if="userBtn['/portal/dictCategory/create']"
            >{{userBtn['/portal/dictCategory/create']}}</el-button>
          </el-button-group>
        </el-col>

        <el-col :span="10">
          <el-input
            placeholder="请输入名称"
            v-model="query.name"
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
            <el-table-column type="index" width="50"></el-table-column>
            <el-table-column prop="name" label="名称" width="500" sortable></el-table-column>
            <el-table-column prop="categoryId" label="编码" width="100" sortable></el-table-column>

            <el-table-column prop="opt" label="操作" align="right">
              <template slot-scope="props">
                <el-button
                  type="text"
                  @click="update(props.row)"
                  v-if="userBtn['/portal/dictCategory/update']"
                >编辑</el-button>
                <span class="strightline" v-if="userBtn['/portal/dictCategory/update']">|</span>

                <el-button
                  type="text"
                  @click="deleteById(props.row)"
                  v-if="userBtn['/portal/dictCategory/deleteById']"
                >删除</el-button>
                <span class="strightline" v-if="userBtn['/portal/dictCategory/deleteById']">|</span>
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
  </div>
</template>

<script>
import {
  Categorypage,
  getById,
  deleteById,
  deleteByIds
} from "@/api/sys/dictCategory";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      dict: {},
      currentPage: 1, //初始页
      pagesize: 10, //  每页的数据
      rows: [],
      total: 0,
      query: { name: "" },
      loading: false,
      o: {}
    };
  },
  computed: {
    ...mapGetters(["userBtn"])
  },
  created() {
    this.handlePageList();
  },
  methods: {
    handleQuery: function() {
      this.currentPage = 1;
      this.handlePageList();
    },
    // 初始页currentPage、初始每页数据数pagesize和数据data
    handleSizeChange: function(size) {
      this.pagesize = size;
      window.console.log(this.pagesize); //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
      window.console.log(this.currentPage); //点击第几页
      this.handlePageList();
    },
    handlePageList() {
      this.query = Object.assign(this.query, {
        pageNum: this.currentPage,
        pageSize: this.pagesize,
        totalRecord: this.total
      });
      window.console.log(this.query);
      this.loading = true;
      Categorypage(this.query).then(response => {
        this.loading = false;
        this.rows = response.rows;
        this.total = response.total;
      });
    },
    toggleChange(name) {
      this.currentPage = 1;
      this.query["name"] = name;
      this.handlePageList();
    },
    create() {
      this.$router.push({ path: "/sys/dict/dictionaryType/add" });
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
          deleteById(data.categoryId).then(response => {
            this.loading = false;
            this.handlePageList();
          });
        })
        .catch(() => {});
    },
    update(data) {
      window.console.log(data);
      this.$router.push({
        path: "/sys/dict/dictionaryType/edit",
        query: { id: data.categoryId }
      });
    }
  }
};
</script>




