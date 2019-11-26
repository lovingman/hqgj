<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-date-picker
            style="width:100%"
            v-model="value"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-col>

        <el-col :span="12">
          <el-input
            placeholder="请输入操作人姓名或模块名称"
            v-model="query.operationObj"
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
            <el-table-column prop="operationObj" label="模块" width="200" sortable></el-table-column>
            <el-table-column prop="operationType" label="类型" width="250" sortable></el-table-column>
            <el-table-column prop="runTime" label="执行时长" width="150" sortable></el-table-column>
            <el-table-column prop="userName" label="操作人" width="100" sortable></el-table-column>
            <el-table-column prop="createTime" label="操作时间" width="200" sortable></el-table-column>
            <el-table-column prop="opt" label="操作" align="right">
              <template slot-scope="props">
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
            <el-col :span="4">模块</el-col>
            <el-col :span="20">{{o.operationObj}}</el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">类型</el-col>
            <el-col :span="20">{{o.operationType}}</el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">操作人</el-col>
            <el-col :span="20">{{o.userName}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">IP</el-col>
            <el-col :span="20">{{o.operationIp}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">执行时长(毫秒)</el-col>
            <el-col :span="20">{{o.runTime}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">操作时间</el-col>
            <el-col :span="20">{{o.createTime}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">描述</el-col>
            <el-col :span="20">{{o.describ}}</el-col>
          </el-row>
          <el-row :gutter="40">
            <el-col :span="4">执行方法</el-col>
            <el-col :span="20">{{o.method}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">参数</el-col>
            <el-col :span="20">{{o.args}}</el-col>
          </el-row>

          <el-row :gutter="40">
            <el-col :span="4">返回</el-col>
            <el-col :span="20">{{o.returnValue}}</el-col>
          </el-row>
        </el-main>
      </el-container>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPreviewVisible = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { page, getById } from "@/api/sys/logs";
import { mapGetters } from "vuex";
import { formatDateTime } from "@/utils/date-util";
export default {
  data() {
    return {
      currentPage: 1, //初始页
      pagesize: 10, //  每页的数据
      rows: [],
      total: 0,
      value: [],
      query: { operationObj: "", startDate: "", endDate: "" },
      loading: false,
      previewLoading: false,
      dialogPreviewVisible: false,
      o: {},
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      }
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
      //window.console.log(this.pagesize); //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
      //window.console.log(this.currentPage); //点击第几页
      this.handlePageList();
    },
    handlePageList() {
      this.query["startDate"] = "";
      this.query["endDate"] = "";
      if (this.value && this.value.length > 1) {
        this.query["startDate"] = formatDateTime(this.value[0]);
        this.query["endDate"] = formatDateTime(this.value[1]);
      }
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
    toggleChange(operationObj) {
      this.currentPage = 1;
      this.query["operationObj"] = operationObj;
      this.handlePageList();
    },
    preview(data) {
      //window.console.log(data);
      this.dialogPreviewVisible = true;
      this.previewLoading = true;
      getById(data.id).then(response => {
        this.previewLoading = false;
        this.o = response.data;
      });
    }
  }
};
</script>



