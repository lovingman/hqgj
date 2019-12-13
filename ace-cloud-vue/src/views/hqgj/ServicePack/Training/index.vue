<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-button type="primary" style="float:left;" @click="create">创建</el-button>
        <el-col class="selectSearch" :span="10">
          <el-col :span="7">
            <el-select v-model="query.status" placeholder="请选择">
              <el-option
                v-for="item in stautsArr"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="16" :offset="1">
            <el-input placeholder="请输入名称" v-model="query.title" clearable class="input-with-select">
              <el-button slot="append" @click="search">搜索</el-button>
            </el-input>
          </el-col>
        </el-col>
      </el-row>
    </div>
    <div class="table-box">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="80"></el-table-column>
        <el-table-column prop="title" sortable label="培训标题"></el-table-column>
        <el-table-column prop="cultivatePersonNumber" sortable label="培训人数"></el-table-column>
        <el-table-column prop="enrollPersopnNumber" sortable label="报名人数"></el-table-column>
        <el-table-column prop="startDate" sortable label="开始时间"></el-table-column>
        <el-table-column prop="endDate" sortable label="截止时间"></el-table-column>
        <el-table-column prop="status" sortable label="状态">
          <template slot-scope="scope">
            <div type="text" class="orange" v-if="scope.row.status=='0'">待审核</div>
            <div type="text" class="green" v-if="scope.row.status=='1'">进行中</div>
            <div type="text" class="red" v-if="scope.row.status=='2'">未通过</div>
            <div type="text" class="gray" v-if="scope.row.status=='3'">已结束</div>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="280" align="right" header-align="center">
          <template slot-scope="scope">
            <el-button type="text">发布</el-button>
            <el-button type="text">审核</el-button>
            <el-button type="text" @click="registrationClick(scope)">报名管理</el-button>
            <el-button type="text" @click="update(scope.row)">编辑</el-button>
            <el-button type="text" @click="deleteById(scope.row)">删除</el-button>
            <el-button type="text">详情</el-button>
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
import { page, deleteByIds } from "@/api/hqgj/training";
export default {
  name: "index",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      query: {
        title: "", //搜索
        status: "" //状态
      },
      stautsArr: [
        {
          id: " ",
          name: "全部"
        },
        {
          id: "0",
          name: "待审核"
        },
        {
          id: "1",
          name: "未通过"
        },
        {
          id: "2",
          name: "进行中"
        },
        {
          id: "3",
          name: "已结束"
        }
      ], //状态容器
      //表格数据
      tableData: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //请求page
    getList() {
      this.query = Object.assign(this.query, {
        pageNum: this.tablePage,
        pageSize: this.tableSize,
        totalRecord: this.total
      });
      page(this.query).then(res => {
        console.log(this.query);
        if (res.status == 1) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    //搜索请求
    search: function() {
      this.tablePage = 1;
      this.getList();
    },
    //选择tableSize事件
    handleTableSize(size) {},
    //选择tablePage事件
    handleTableCurrent(current) {},
    //创建
    create() {
      this.$router.push({ path: "/hqgj/ServicePack/Training/add" });
    },
    //报名管理
    registrationClick() {
      this.$router.push({ path: "/hqgj/ServicePack/Training/registration" });
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
          deleteByIds(this.ids).then(res => {
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
    //编辑
    update(data) {
      this.$router.push({
        path: "/hqgj/ServicePack/Training/edit",
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
    /deep/ .el-table th:last-child {
      text-align: right;
      padding-right: 10px;
    }
  }
}
</style>>

