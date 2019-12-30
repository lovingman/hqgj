<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-col :span="10">
          <el-button type="primary" style="float:left;" @click="create">创建</el-button>
        </el-col>
        <el-col class="selectSearch" :span="14">
          <el-col :span="5">
            <el-select v-model="query.orgId" clearable placeholder="请选择服务机构">
              <el-option
                v-for="item in mechanismArr"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="5" :offset="1">
            <el-select v-model="query.status" clearable placeholder="请选择状态">
              <el-option
                v-for="item in stautsArr"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="12" :offset="1">
            <el-input
              placeholder="请输入服务机构名称"
              v-model="query.orgName"
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
        <el-table-column type="index" sortable label="序号" width="80"></el-table-column>
        <el-table-column prop="type" sortable label="类型" width="120">
          <template slot-scope="scope">
            <div type="text" v-if="scope.row.type=='1'">代理记账</div>
            <div type="text" v-if="scope.row.type=='2'">财税管理</div>
            <div type="text" v-if="scope.row.type=='3'">专家问诊</div>
          </template>
        </el-table-column>
        <el-table-column prop="orgName" sortable label="服务机构" width="120"></el-table-column>
        <el-table-column prop="contactPersonTel" sortable label="联系方式" width="180"></el-table-column>
        <el-table-column prop="createDate" sortable label="创建时间" width="220"></el-table-column>
        <el-table-column prop="status" sortable label="状态">
          <template slot-scope="scope">
            <div type="text" class="brown" v-if="scope.row.status=='0'">待审核</div>
            <div type="text" class="blue" v-if="scope.row.status=='1'">审核通过</div>
            <div type="text" class="red" v-if="scope.row.status=='2'">未通过</div>
            <div type="text" class="green" v-if="scope.row.status=='3'">已上线</div>
            <div type="text" class="gray" v-if="scope.row.status=='4'">已下线</div>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="240" align="right" header-align="center">
          <template slot-scope="scope">
            <el-button type="text" v-if="scope.row.status =='0'" @click="examine(scope.row)">审核</el-button>
            <el-button type="text" v-if="scope.row.status =='1'" @click="online(scope.row)">上线</el-button>
            <el-button type="text" v-if="scope.row.status =='3'" @click="offline(scope.row)">下线</el-button>
            <el-button type="text" v-if="scope.row.status !='4'" @click="edit(scope.row)">编辑</el-button>
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
            <el-radio :label="1">通过</el-radio>
            <el-radio :label="2">不通过</el-radio>
          </el-radio-group>
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
import {
  page,
  orgChange,
  updateStatus,
  deleteByIds
} from "@/api/hqgj/handheld";
export default {
  name: "index",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      examineVisible: false, //审核弹出框是否显示
      loading: false, //加载状态
      query: {
        orgName: "", //搜索
        orgId: "", //服务机构
        status: "" //状态
      },
      //机构容器
      mechanismArr: [],
      //审核容器
      updateState: {
        status: 1
      },
      //状态容器
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
          name: "审核通过"
        },
        {
          id: "2",
          name: "未通过"
        },
        {
          id: "3",
          name: "已上线"
        },
        {
          id: "4",
          name: "已下线"
        }
      ],
      tableData: []
    };
  },
  created() {
    this.getList();
    this.getOrgArr();
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
      this.$router.push({ path: "/hqgj/ServicePack/Handheld/add" });
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
    //选择服务机构请求数据
    getOrgArr() {
      orgChange().then(res => {
        if (res.status == 1) {
          this.orgRows = res.rows;
          for (let i = 0; i < this.orgRows.length; i++) {
            //循环获取到的服务机构名称和ID，添加到服务机构下拉框容器中
            var obj = {};
            obj.name = this.orgRows[i].orgName;
            obj.id = this.orgRows[i].id;
            this.mechanismArr.push(obj);
          }
        }
      });
    },
    //搜索请求
    search() {
      this.tablePage = 1;
      this.getList();
    },
    // 编辑跳转传递ID
    edit(data) {
      this.$router.push({
        path: "/hqgj/ServicePack/Handheld/edit",
        query: { id: data.id }
      });
    },
    //详情跳转
    seeClick(data) {
      this.$router.push({
        path: "/hqgj/ServicePack/Handheld/see",
        query: { id: data.id }
      });
    },
    //下线操作
    offline(row) {
      this.offlineId = row.id;
      let statusType = 4; //传递4代表下线状态
      this.$confirm("确定是否要上线该服务?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          updateStatus(this.offlineId, statusType).then(res => {
            if (res.status == 1) {
              this.$message.success("成功下架改商品");
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
    },
    //审核弹窗
    examine(row) {
      this.examineId = row.id;
      this.examineVisible = true;
    },
    //确定审核
    saveExamine() {
      updateStatus(this.examineId, this.updateState.status).then(res => {
        if (res.status == 1) {
          this.$message.success("审核成功");
          this.examineVisible = false;
          this.getList();
        }
      });
    },
    //上线操作
    online(row) {
      this.onlineId = row.id;
      let statusType = 3; //传递3代表已上线状态
      this.$confirm("确定是否要上线该服务?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          updateStatus(this.onlineId, statusType).then(res => {
            if (res.status == 1) {
              this.$message.success("上线成功");
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
    /deep/ .el-select {
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
    .brown {
      color: #cc6600;
    }
    .green {
      color: #00a854;
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

