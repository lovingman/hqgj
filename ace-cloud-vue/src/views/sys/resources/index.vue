<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-row :gutter="20">
        <el-col :span="2">
          <el-button-group>
            <el-button type="primary" @click="create">创建</el-button>
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
                v-if="userBtn['/portal/resources/importXls']"
              >{{userBtn['/portal/resources/importXls']}}</el-dropdown-item>
              <el-dropdown-item
                command="exportXls"
                icon="el-icon-thirddownload"
                v-if="userBtn['/portal/resources/exportXls']"
              >{{userBtn['/portal/resources/exportXls']}}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>

        <el-col :span="12"></el-col>
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
            row-key="id"
            :tree-props="{children: 'children', hasChildren: '!leaf',label:'text'}"
          >
            <el-table-column prop="text" label="名称" width="300" sortable></el-table-column>
            <el-table-column prop="id" label="编号" width="150" sortable></el-table-column>
            <el-table-column prop="href" label="地址" width="200" sortable></el-table-column>
            <el-table-column prop="icon" label="图标" width="150" sortable></el-table-column>
            <el-table-column prop="src" label="类型" width="100" sortable :formatter="formateType"></el-table-column>
            <el-table-column prop="opt" label="操作" align="right">
              <template slot-scope="props">
                <el-button
                  type="text"
                  @click="create(props.row)"
                  v-if="userBtn['/portal/resources/create']"
                >创建</el-button>
                <span class="strightline" v-if="userBtn['/portal/resources/create']">|</span>
                <el-button
                  type="text"
                  @click="update(props.row)"
                  v-if="userBtn['/portal/resources/update']"
                >编辑</el-button>
                <span class="strightline" v-if="userBtn['/portal/resources/update']">|</span>

                <el-button
                  type="text"
                  @click="deleteById(props.row)"
                  v-if="userBtn['/portal/resources/deleteById']"
                >删除</el-button>
                <span class="strightline" v-if="userBtn['/portal/resources/deleteById']">|</span>

                <el-button type="text" @click="preview(props.row)">排序</el-button>
              </template>
            </el-table-column>
          </el-table>
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
            action="/portal/resources/importXls"
            :http-request="myUpload"
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>

            <div class="el-upload__tip" slot="tip">
              <a style="color:red" href="/portal/download/downloadExcel?name=resources">模板下载</a>
            </div>
          </el-upload>
        </el-main>
      </el-container>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogUploadVisible = false">关闭</el-button>
      </div>
    </el-dialog>
    <el-dialog title="排序" :visible.sync="dialogPreviewVisible" width="80%">
      <el-container>
        <el-header style="height:1.5rem">
          <div class="section_title">资源列表</div>
        </el-header>
        <el-main
          v-loading="previewLoading"
          element-loading-text="加载中"
          element-loading-spinner="el-icon-loading"
        >
          <div class="root">
            <SlickList lockAxis="y" v-model="items">
              <SlickItem
                class="list-item"
                v-for="(item, index) in items"
                :index="index"
                :key="index"
              >{{item.name}}</SlickItem>
            </SlickList>
          </div>
        </el-main>
      </el-container>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPreviewVisible = false">关闭</el-button>
        <el-button type="primary" @click="saveSorts()">保存</el-button>
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
  getTree,
  getListByPid,
  updateSequence
} from "@/api/sys/resource";
import { mapGetters } from "vuex";
import { fileUpload, filedown } from "@/api/sys";
import { SlickList, SlickItem } from "vue-slicksort";
export default {
  components: {
    SlickItem,
    SlickList
  },
  data() {
    return {
      rows: [],
      total: 0,
      query: { name: "", categoryId: "" },
      loading: false,
      dialogUploadVisible: false,
      previewLoading: false,
      dialogPreviewVisible: false,
      o: {},
      items: []
    };
  },
  computed: {
    ...mapGetters(["userBtn"])
  },
  created() {
    this.handlePageList();
  },
  methods: {
    formateType(row, column, cellValue, index) {
      switch (cellValue) {
        case "1":
          return "目录";
        case "2":
          return "模块";
        case "3":
          return "按钮";
        default:
          return "隐藏";
      }
    },
    saveSorts() {
      window.console.log(this.items);
      let sortList = [];
      let i = 1;
      for (let e of this.items) {
        sortList.push({ sequence: i, resourcesId: e.id, name: e.name });
        i++;
      }
      this.previewLoading = true;
      updateSequence({
        jsons: JSON.stringify(sortList)
      }).then(response => {
        this.previewLoading = false;
        this.$confirm(response.message + ", 是否关闭?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "success",
          center: true
        })
          .then(() => {
            this.dialogPreviewVisible = false;
          })
          .catch(() => {});
      });
    },
    handlePageList() {
      this.loading = true;
      getTree().then(response => {
        this.loading = false;
        this.rows = response;
      });
    },
    create(data) {
      this.$router.push({ path: "/sys/resources/add", query: { id: data.id } });
    },
    deleteById(data) {
      window.console.log(data);
      this.$confirm("删除" + data.text + ", 是否继续?", "提示", {
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
        path: "/sys/resources/edit",
        query: { id: data.id }
      });
    },
    preview(data) {
      window.console.log(data);
      this.dialogPreviewVisible = true;
      this.previewLoading = true;
      getListByPid(data.id).then(response => {
        this.previewLoading = false;
        this.items = response.data;
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
        filedown({}, "/portal/resources/exportXls").then(response => {
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
      let fileName = "资源.xlsx";
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
<style type="text/scss" lang="scss" scoped>
.root {
  display: flex;
  height: 100%;
}

.list-item {
  display: flex;
  cursor: pointer;
  align-items: center;
  width: 100%;
  padding: 20px;
  background-color: #fff;
  border-bottom: 1px solid #efefef;
  box-sizing: border-box;
  user-select: none;
  color: #333;
  font-weight: 400;
}
</style>







