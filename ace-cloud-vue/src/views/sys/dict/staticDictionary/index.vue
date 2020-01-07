<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-row :gutter="20">
        <el-col :span="2">
          <el-button-group>
            <el-button type="primary" style="border-radius:5px;" @click="create">创建</el-button>
          </el-button-group>
        </el-col>
        <el-col :span="10">
          <el-dropdown style="line-height:2rem;" @command="handleCommand">
            <span class="el-dropdown-link">
              Excel
              <i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item
                command="importXls"
                icon="el-icon-thirdupload"
                v-if="userBtn['/portal/dict/importXls']"
              >{{userBtn['/portal/dict/importXls']}}</el-dropdown-item>
              <el-dropdown-item
                command="exportXls"
                icon="el-icon-thirddownload"
                v-if="userBtn['/portal/dict/exportXls']"
              >{{userBtn['/portal/dict/exportXls']}}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>

        <el-col :span="12">
          <el-input
            placeholder="请输入名称"
            v-model="query.name"
            @change="toggleChange"
            class="input-with-select"
          >
            <el-select
              v-model="query.categoryId"
              class="type"
              clearable
              slot="prepend"
              placeholder="请选择字典类型"
            >
              <el-option
                v-for="item in list"
                :key="item.categoryId"
                :label="item.name"
                :value="item.categoryId"
              ></el-option>
            </el-select>
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
            <el-table-column type="index" align="center" width="50"></el-table-column>

            <el-table-column prop="name" label="名称" width="200" sortable></el-table-column>
            <el-table-column prop="code" label="编号" sortable></el-table-column>

            <el-table-column prop="opt" label="操作" align="right">
              <template slot-scope="props">
                <el-button
                  type="text"
                  @click="update(props.row)"
                  v-if="userBtn['/portal/dict/update']"
                >编辑</el-button>
                <span class="strightline" v-if="userBtn['/portal/dict/update']">|</span>

                <el-button
                  type="text"
                  @click="deleteById(props.row)"
                  v-if="userBtn['/portal/dict/deleteById']"
                >删除</el-button>
                <span class="strightline" v-if="userBtn['/portal/dict/deleteById']">|</span>
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

    <el-dialog title="数据导入" :visible.sync="dialogUploadVisible" width="45rem">
      <el-container>
        <el-header style="height:1.5rem">
          <div class="section_title">只能上传xls/xlsx文件，且不超过10M</div>
        </el-header>
        <el-main>
          <el-upload
            accept=".xls, .xlsx"
            :on-success="handleUploadSuccess"
            :on-error="handleUploadError"
            drag
            action="/portal/dict/importXls"
            :http-request="myUpload"
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>

            <div class="el-upload__tip" slot="tip">
              <a style="color:red" href="/portal/download/downloadExcel?name=dict">模板下载</a>
            </div>
          </el-upload>
        </el-main>
      </el-container>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogUploadVisible = false">关闭</el-button>
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
  getList
} from "@/api/sys/dict";
import { Categorypage} from "@/api/sys/dictCategory";
import { mapGetters } from "vuex";
import { fileUpload, filedown } from "@/api/sys";
export default {
  data() {
    return {
      list: [],
      currentPage: 1, //初始页
      pagesize: 10, //  每页的数据
      rows: [],
      fenye:{
        currentPage: 1, // 列表显示初始页
        pagesize: 100, //  列表显示每页的数据
        total: 0,
      },
      total: 0,
      query: { name: "", categoryId: "" },
      loading: false,
      dialogUploadVisible: false,
      o: {}
    };
  },
  computed: {
    ...mapGetters(["userBtn"])
  },
  created() {
    this.handlePageList();
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
        window.console.log(this.list);
        this.list = response.rows;
      });
    },
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
      page(this.query).then(response => {
        this.loading = false;
        this.rows = response.rows;
        this.total = response.total;
      });
    },
    toggleTab(status) {
      this.query["status"] = status;
      this.handlePageList();
    },
    toggleChange(name) {
      this.query["name"] = name;
      this.handlePageList();
    },
    create() {
      this.$router.push({ path: "/sys/dict/staticDictionary/add" });
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
          deleteById(data.id).then(response => {
            this.loading = false;
            this.handlePageList();
          });
        })
        .catch(() => {});
    },
    update(data) {
      window.console.log(data);
      this.$router.push({
        path: "/sys/dict/staticDictionary/edit",
        query: { id: data.id }
      });
    },
    preview(data) {
      window.console.log(data);
      this.dialogPreviewVisible = true;
      this.previewLoading = true;
      getById(data.id).then(response => {
        this.previewLoading = false;
        this.o = response.data;
      });
    },
    importXls() {
      this.dialogUploadVisible = true;
    },
    handleCommand(command) {
      if (command == "importXls") {
        this.importXls();
      }
      if (command == "exportXls") {
        filedown({}, "/portal/dict/exportXls").then(response => {
          this.download(response);
        });
      }
    },
    handleUploadSuccess(response, file, fileList) {
      if (response.status == "1") {
        this.$message({
          message: "导入成功",
          type: "success"
        });
        this.currentPage = 1;
        this.handlePageList();
      } else {
        this.$message.error("导入失败 :" + response.message);
      }
    },
    handleUploadError(err, file, fileList) {
      this.$message.error("导入失败 :" + err);
    },
    myUpload(content) {
      this.loading = true;
      fileUpload(content, content.action).then(response => {
        if (response.status == "1") {
          this.$message({
            message: "导入成功",
            type: "success"
          });
          this.currentPage = 1;
          this.handlePageList();
        } else {
          this.$message.error("导入失败 :" + response.message);
        }
      });
    },
    download(data) {
      if (!data) {
        return;
      }
      let blob = new Blob([data]);
      let fileName = "字典.xlsx";
      if ("download" in document.createElement("a")) {
        // 不是IE浏览器
        let url = window.URL.createObjectURL(blob);
        let link = document.createElement("a");
        link.style.display = "none";
        link.href = url;
        link.setAttribute("download", fileName);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link); // 下载完成移除元素
        window.URL.revokeObjectURL(url); // 释放掉blob对象
      } else {
        // IE 10+
        window.navigator.msSaveBlob(blob, fileName);
      }
      this.loading = false;
    }
  }
};
</script>
<style>
.type {
  width: 15rem;
}
</style>



