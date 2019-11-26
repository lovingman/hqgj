<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-row :gutter="20">
        <el-col :span="2">
          <el-button-group>
            <el-button
              type="primary"
              @click="create"
              v-if="userBtn['/portal/corp/create']"
            >{{userBtn['/portal/corp/create']}}</el-button>
          </el-button-group>
        </el-col>
        <!--<el-col :span="2">-->
          <!--<el-dropdown style="line-height:2rem;" @command="handleCommand">-->
            <!--<span class="el-dropdown-link">-->
              <!--Excel-->
              <!--<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
            <!--</span>-->
            <!--<el-dropdown-menu slot="dropdown">-->
              <!--<el-dropdown-item-->
                <!--command="importXls"-->
                <!--icon="el-icon-thirdupload"-->
                <!--v-if="userBtn['/portal/corp/importXls']"-->
              <!--&gt;{{userBtn['/portal/corp/importXls']}}</el-dropdown-item>-->
              <!--<el-dropdown-item-->
                <!--command="exportXls"-->
                <!--icon="el-icon-thirddownload"-->
                <!--v-if="userBtn['/portal/corp/exportXls']"-->
              <!--&gt;{{userBtn['/portal/corp/exportXls']}}</el-dropdown-item>-->
            <!--</el-dropdown-menu>-->
          <!--</el-dropdown>-->
        <!--</el-col>-->

        <el-col :span="14">
          <el-cascader
            placeholder="请选择行政区划"
            v-model="areaCodes"
            :options="areaCodeOptions"
            :props="areaCodeProps"
            clearable
            change-on-select
            filterable
            style="width:60%;float:right;"
          />
        </el-col>

        <el-col :span="8">
          <el-input
            placeholder="请输入单位名称"
            v-model="query.name"
            @change="toggleChange"
            class="input-with-select"
            style="width:90%;float: right"
          >
            <!--<el-select-->
              <!--v-model="query.type"-->
              <!--class="type"-->
              <!--clearable-->
              <!--slot="prepend"-->
              <!--placeholder="请选择单位类型"-->
            <!--&gt;-->
              <!--<el-option-->
                <!--v-for="item in dict['48']"-->
                <!--:key="item.code"-->
                <!--:label="item.name"-->
                <!--:value="item.code"-->
              <!--&gt;</el-option>-->
            <!--</el-select>-->
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
            <el-table-column prop="name" label="名称" width="500" sortable></el-table-column>
            <el-table-column prop="areaName" label="行政区划" width="200" sortable></el-table-column>

            <el-table-column prop="opt" label="操作" align="right">
              <template slot-scope="props">
                <el-button
                  type="text"
                  @click="update(props.row)"
                  v-if="userBtn['/portal/corp/update']"
                >编辑</el-button>
                <span class="strightline" v-if="userBtn['/portal/corp/update']">|</span>

                <el-button
                  type="text"
                  @click="deleteById(props.row)"
                  v-if="userBtn['/portal/corp/deleteById']"
                >删除</el-button>
                <span class="strightline" v-if="userBtn['/portal/corp/deleteById']">|</span>

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
            <el-col :span="4">名称</el-col>
            <el-col :span="20">{{o.name}}</el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">编码</el-col>
            <el-col :span="20">{{o.id}}</el-col>
          </el-row>
          <!--<el-row :gutter="40">-->
            <!--<el-col :span="4">类型</el-col>-->
            <!--<el-col :span="20">{{o.typeName}}</el-col>-->
          <!--</el-row>-->

          <el-row :gutter="40">
            <el-col :span="4">行政区划</el-col>
            <el-col :span="20">{{o.areaName}}</el-col>
          </el-row>
        </el-main>
      </el-container>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPreviewVisible = false">关闭</el-button>
      </div>
    </el-dialog>

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
            action="/portal/corp/importXls"
            :http-request="myUpload"
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>

            <div class="el-upload__tip" slot="tip">
              <a style="color:red" href="/portal/download/downloadExcel?name=corp">模板下载</a>
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
import { page, getById, deleteById, deleteByIds } from "@/api/sys/corp";
import { mapGetters } from "vuex";
import { fileUpload, filedown, getAreaTree, getDict } from "@/api/sys";
export default {
  data() {
    return {
      dict: {},
      areaCodeOptions: [],
      areaCodeProps: {
        value: "id",
        label: "text"
      },
      statusPosition: "1",
      statusData: [
        { id: "", name: "全部" },
        { id: "1", name: "正常" },
        { id: "2", name: "注销" }
      ],
      currentPage: 1, //初始页
      pagesize: 10, //  每页的数据
      rows: [],
      total: 0,
      query: { name: "", type: "", areaCode: "" },
      loading: false,
      previewLoading: false,
      dialogPreviewVisible: false,
      dialogUploadVisible: false,
      o: {},
      areaCodes: []
    };
  },
  computed: {
    ...mapGetters(["userBtn"])
  },
  created() {
    this.handlePageList();
    this.AreaCodeQuery();
    this.dictQuery();
  },
  methods: {
    AreaCodeQuery() {
      const userProp = this.$store.getters.userProp;
      getAreaTree({ pid: userProp.areaCode, type: 3, hasSelf: "true" }).then(
        response => {
          this.areaCodeOptions = response.data;
        }
      );
    },
    dictQuery() {
      getDict("48").then(response => {
        this.dict = response.data;
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
      this.query.areaCode = "";
      for (let e of this.areaCodes) {
        this.query.areaCode = e;
      }
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
      this.currentPage = 1;
      this.query["status"] = status;
      this.handlePageList();
    },
    toggleChange(name) {
      this.currentPage = 1;
      this.query["name"] = name;
      this.handlePageList();
    },
    create() {
      this.$router.push({ path: "/sys/corp/add" });
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
      this.$router.push({ path: "/sys/corp/edit", query: { id: data.id } });
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
        filedown({}, "/portal/corp/exportXls").then(response => {
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
      let fileName = "单位名册"  + ".xlsx";
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



