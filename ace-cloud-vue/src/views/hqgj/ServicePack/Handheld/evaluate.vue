<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-col class="selectSearch" :span="12">
          <el-col :span="12">
            <el-date-picker
              v-model="timeArr"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
            ></el-date-picker>
          </el-col>
          <el-col :span="11" :offset="1">
            <el-input
              placeholder="请输入内容"
              v-model="query.evaluateContent"
              clearable
              class="input-with-select"
            >
              <el-button slot="append" icon="el-icon-search" :loading="loading" @click="search"></el-button>
            </el-input>
          </el-col>
        </el-col>
      </el-row>
    </div>
    <div class="table-box">
      <el-table
        :data="tableData"
        style="width: 100%"
        v-loading="loading"
        element-loading-text="加载中"
        element-loading-spinner="el-icon-loading"
      >
        <el-table-column type="index" width="80" label="序号"></el-table-column>
        <el-table-column prop="createUserName" sortable label="姓名" width="160"></el-table-column>
        <el-table-column prop="evaluateContent" sortable label="评价内容"></el-table-column>
        <el-table-column prop="evaluateGrade" sortable label="服务评分" width="200">
          <template slot-scope="scope">
            <el-rate
              v-model="scope.row.evaluateGrade"
              :allow-half="true"
              disabled
              text-color="#ff9900"
            ></el-rate>
          </template>
        </el-table-column>
        <el-table-column prop="orgName" sortable label="服务机构"></el-table-column>
        <el-table-column prop="createDate" sortable label="评价时间" width="200"></el-table-column>
        <el-table-column label="操作" fixed="right" width="140" align="right" header-align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="deleteId(scope.row)">删除</el-button>
            <el-button type="text" @click="seeClick(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleTableSize"
        @current-change="handleTableCurrent"
        :current-page="tablePage"
        :page-size="tableSize"
        :total="total"
        background
        layout="total,sizes,prev, pager, next ,jumper"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import { evaluatePage, evaluateDelete } from "@/api/hqgj/handheld";
export default {
  name: "evaluate",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      timeArr: [], //时间数组
      loading: false, //加载状态
      query: {
        evaluateContent: "" //搜索
      },
      stautsArr: [
        {
          value: "选项1",
          label: "全部"
        },
        {
          value: "选项2",
          label: "待审核"
        },
        {
          value: "选项2",
          label: "未通过"
        },
        {
          value: "选项2",
          label: "进行中"
        },
        {
          value: "选项2",
          label: "已结束"
        }
      ], //状态容器
      //订单数据
      tableData: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //请求page
    getList() {
      this.loading = true;
      this.query = Object.assign(this.query, {
        pageNum: this.tablePage,
        pageSize: this.tableSize,
        totalRecord: this.total
      });
      if (this.timeArr) {
        this.query.startTime = this.timeArr.length > 0 ? this.timeArr[0] : ""; //开始时间
        this.query.endTime = this.timeArr.length > 0 ? this.timeArr[1] : ""; //结束时间
      } else {
        this.query.startTime = "";
        this.query.endTime = "";
      }
      evaluatePage(this.query).then(res => {
        if (res.status == 1) {
          this.tableData = res.rows;
          this.total = res.total;
          this.loading = false;
        }
      });
    },
    //删除
    deleteId(row) {
      let ids = row.id;
      this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          evaluateDelete(ids).then(res => {
            if (res.status == 1) {
              this.currentPage = 1;
              this.$message.success("删除成功");
              this.getList();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    search() {
      this.tablePage = 1;
      this.getList();
    },
    //选择tableSize事件
    handleTableSize(size) {
      this.tableSize = size;
      this.getList();
    },
    //选择tablePage事件
    handleTableCurrent(current) {
      this.tablePage = current;
      this.getList();
    },
    //详情跳转
    seeClick(data) {
      this.$router.push({
        path: "/hqgj/ServicePack/Handheld/evaluateSee",
        query: { id: data.id }
      });
    }
  }
};
</script>

<style lang="less" scoped>
.main-box {
  background-color: #fff;
  .header {
    padding: 30px 30px 10px 30px;
    /deep/ .el-button--medium {
      border-radius: 4px;
      height: 36px;
    }
    /deep/ .el-select {
      width: 100%;
    }
    /deep/ .el-date-editor--datetimerange.el-input__inner {
      width: 100%;
    }
    .selectSearch {
      float: right;
    }
  }
  .table-box {
    padding: 0 30px 30px 30px;
    /deep/ .el-table th:last-child {
      text-align: right;
      padding-right: 10px;
    }
  }
}
</style>