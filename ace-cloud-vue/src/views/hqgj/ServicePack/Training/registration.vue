<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-col class="selectSearch" :span="10">
          <el-col :span="7">
            <el-select v-model="query.isSign" placeholder="请选择">
              <el-option
                v-for="item in stautsArr"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="16" :offset="1">
            <el-input
              placeholder="请输入企业名称或姓名"
              v-model="query.companyName"
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
        <el-table-column prop="companyName" sortable label="企业名称"></el-table-column>
        <el-table-column prop="enrollName" sortable label="姓名"></el-table-column>
        <el-table-column prop="enrollMobile" sortable label="手机号码"></el-table-column>
        <el-table-column prop="isSign" sortable label="是否签到">
          <template slot-scope="scope">
            <div type="text" v-if="scope.row.isSign=='n'">否</div>
            <div type="text" v-if="scope.row.isSign=='y'">是</div>
          </template>
        </el-table-column>
        <el-table-column prop="createDate" sortable label="报名时间"></el-table-column>
        <el-table-column
          label="操作"
          fixed="right"
          width="240"
          align="right"
          header-align="center"
          class="right-txt"
        >
          <template slot-scope="scope">
            <el-button type="text" @click="deleteById(scope.row)">删除</el-button>
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
import { registrationPage, registrationDeleteByIds } from "@/api/hqgj/training";
export default {
  name: "registration",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      loading: false, //加载状态
      //搜索
      query: {
        serveCultivateId: "", //id
        companyName: "", //名称
        isSign: "" //签到
      },
      stautsArr: [
        {
          id: " ",
          name: "全部"
        },
        {
          id: "y",
          name: "已签到"
        },
        {
          id: "n",
          name: "未签到"
        }
      ], //状态容器
      //报名列表容器
      tableData: []
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      this.query.serveCultivateId = this.$route.query.id;
      registrationPage(this.query).then(res => {
        if (res.status == 1) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    //page列表
    getList() {
      this.query = Object.assign(this.query, {
        pageNum: this.tablePage,
        pageSize: this.tableSize,
        totalRecord: this.total
      });
      registrationPage(this.query).then(res => {
        if (res.status == 1) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    //搜搜
    search() {
      this.tablePage = 1;
      this.getList();
    },
    //删除
    deleteById(row) {
      this.ids = row.id;
      this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          registrationDeleteByIds(this.ids).then(res => {
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
    //选择tableSize事件
    handleTableSize(size) {
      this.tableSize = size;
      this.getList();
    },
    //选择tablePage事件
    handleTableCurrent(current) {
      this.tablePage = current;
      this.getList();
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
    .selectSearch {
      float: right;
      /deep/ .el-button--medium {
        color: #fff;
        background-color: #007cff;
        border-color: #007cff;
        border-radius: 0;
      }
    }
  }
  .table-box {
    padding: 0 30px 30px 30px;
    /deep/ .el-table th:last-child > .cell {
      text-align: right;
      padding-right: 10px;
    }
  }
}
</style>>

