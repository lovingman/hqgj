<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-button type="primary" style="float:left;" @click="create">创建</el-button>
        <el-col class="selectSearch" :span="10">
          <el-col :span="7">
            <el-select v-model="query.status" @change="toggleSelect" clearable placeholder="请选择">
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
        <el-table-column prop="title" sortable label="培训标题"></el-table-column>
        <el-table-column prop="cultivatePersonNumber" sortable width="120" label="培训人数"></el-table-column>
        <el-table-column prop="enrollPersopnNumber" sortable width="120" label="报名人数"></el-table-column>
        <el-table-column prop="startDate" sortable width="180" label="开始时间"></el-table-column>
        <el-table-column prop="endDate" sortable width="180" label="截止时间"></el-table-column>
        <el-table-column prop="status" sortable width="100" label="状态">
          <template slot-scope="scope">
            <div type="text" class="brown" v-if="scope.row.status=='0'">待审核</div>
            <div type="text" class="green" v-if="scope.row.status=='1'">进行中</div>
            <div type="text" class="red" v-if="scope.row.status=='2'">未通过</div>
            <div type="text" class="gray" v-if="scope.row.status=='3'">已结束</div>
            <div type="text" class="blue" v-if="scope.row.status=='4'">待发布</div>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="240" align="right" header-align="center">
          <template slot-scope="scope">
            <el-button type="text" @click="release(scope.row)" v-if="scope.row.status =='4'">发布</el-button>
            <el-button type="text" @click="examine(scope.row)" v-if="scope.row.status =='0'">审核</el-button>
            <el-button
              type="text"
              @click="registrationClick(scope.row)"
              v-if="scope.row.status =='1' || scope.row.status =='3' "
            >报名管理</el-button>
            <el-button
              type="text"
              v-if="scope.row.status !='3' && scope.row.status !='2'"
              @click="update(scope.row)"
            >编辑</el-button>
            <el-button type="text" @click="deleteById(scope.row)">删除</el-button>
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
    <!-- 审核弹出框 -->
    <el-dialog title="审核" class="examine" :visible.sync="examineVisible">
      <el-form ref="form" :model="updateState" label-width="100%">
        <p style="line-height:30px; text-align: center; font-size: 20px;padding-bottom:20px;">服务是否通过</p>
        <div style="text-align: center">
          <el-radio-group v-model="updateState.status" style="text-align: center">
            <el-radio :label="4">通过</el-radio>
            <el-radio :label="2">不通过</el-radio>
          </el-radio-group>
          <el-row v-if="updateState.status == 2" style="padding:0 20px; margin-top:30px;">
            <el-input placeholder="请输入不通过的原因" v-model="updateState.reason"></el-input>
          </el-row>
        </div>
      </el-form>
      <span class="dialog-footer">
        <el-button @click="examineVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveExamine">确 定</el-button>
      </span>
    </el-dialog>
    <!-- end -->
  </div>
</template>

<script>
import { page, deleteByIds, updateStatus } from "@/api/hqgj/training";
export default {
  name: "index",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      examineVisible: false, //是否显示审核弹框
      loading: false, //loading加载状态
      //搜索
      query: {
        title: "", //输入框
        status: "" //状态
      },
      //审核
      updateState: {
        status: 4,
        reason: "", //审核原因
        id: "" //当前ID
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
          name: "进行中"
        },
        {
          id: "2",
          name: "未通过"
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
      this.loading = true;
      this.query = Object.assign(this.query, {
        pageNum: this.tablePage,
        pageSize: this.tableSize,
        totalRecord: this.total
      });
      page(this.query).then(res => {
        if (res.status == 1) {
          this.tableData = res.rows;
          this.total = res.total;
          this.loading = false;
        }
      });
    },
    //选择状态请求
    toggleSelect(value) {
      this.tablePage = 1;
      this.query["status"] = value;
      this.getList();
    },
    //搜索请求
    search: function() {
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
    //创建
    create() {
      this.$router.push({ path: "/hqgj/ServicePack/Training/add" });
    },
    //报名管理
    registrationClick(data) {
      this.$router.push({
        path: "/hqgj/ServicePack/Training/registration",
        query: { id: data.id }
      });
    },
    //详情
    seeClick(data) {
      this.$router.push({
        path: "/hqgj/ServicePack/Training/see",
        query: { id: data.id }
      });
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
    },
    //审核弹窗
    examine(row) {
      this.examineId = row.id;
      this.examineVisible = true;
      this.updateState.status = 4; //默认状态
    },
    //确定审核
    saveExamine() {
      var obj = {};
      obj.id = this.examineId;
      obj.status = this.updateState.status;
      obj.reason = this.updateState.reason;
      if (this.updateState.status == 2) {
        updateStatus(obj).then(res => {
          if (res.status == 1) {
            this.$message.success("提交成功");
            this.examineVisible = false;
            this.getList();
          }
        });
      } else {
        updateStatus(obj).then(res => {
          if (res.status == 1) {
            this.$message.success("审核成功");
            this.examineVisible = false;
            this.getList();
          }
        });
      }
    },
    //确定是否发布
    release(row) {
      var obj = {};
      obj.id = row.id;
      obj.status = 1; //传递1代表发布成功
      this.$confirm("确定是否要发布该服务?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          updateStatus(obj).then(res => {
            if (res.status == 1) {
              this.$message.success("发布成功");
              this.getList();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作"
          });
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
    }
  }
  .table-box {
    padding: 0 30px 30px 30px;
    /deep/ .el-table th:last-child {
      text-align: right;
      padding-right: 10px;
    }
    .brown {
      color: #cc6600;
    }
    .green {
      color: #4da64d;
    }
    .blue {
      color: #1890ff;
    }
    .red {
      color: #ff5a5a;
    }
    .gray {
      color: #5a5a5a;
    }
  }
  .examine {
    .dialog-footer {
      justify-content: center;
      margin-top: 40px;
      width: 100%;
      display: flex;
    }
  }
}
</style>>

