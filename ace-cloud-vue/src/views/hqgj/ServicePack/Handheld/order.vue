<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-button type="primary" style="float:left;">数据导出</el-button>
        <el-col class="selectSearch" :span="18">
          <el-col :span="10">
            <el-date-picker
              v-model="timeArr"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
            ></el-date-picker>
          </el-col>
          <el-col :span="3" :offset="1">
            <el-select v-model="query.type" clearable placeholder="请选择类型">
              <el-option v-for="item in typeArr" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-col>
          <el-col :span="3" :offset="1">
            <el-select v-model="query.status" clearable placeholder="请选择状态">
              <el-option
                v-for="item in statusArr"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="5" :offset="1">
            <el-input
              placeholder="请输入订单号"
              v-model="query.orderNo"
              clearable
              class="input-with-select"
            >
              <el-button slot="append" @click="search">搜索</el-button>
            </el-input>
          </el-col>
        </el-col>
      </el-row>
    </div>
    <div class="table-box">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="80"></el-table-column>
        <el-table-column prop="orderNo" sortable label="订单号"></el-table-column>
        <el-table-column prop="type" sortable label="类型">
          <template slot-scope="scope">
            <div type="text" v-if="scope.row.type=='1'">代理记账</div>
            <div type="text" v-if="scope.row.type=='2'">财税管理</div>
            <div type="text" v-if="scope.row.type=='3'">专家问诊</div>
          </template>
        </el-table-column>
        <el-table-column prop="orgName" sortable label="服务机构"></el-table-column>
        <el-table-column prop="companyName" sortable label="企业名称"></el-table-column>
        <el-table-column prop="createDate" sortable label="下单时间"></el-table-column>
        <el-table-column prop="status" sortable label="状态">
          <template slot-scope="scope">
            <div type="text" class="brown" v-if="scope.row.status=='0'">待完成</div>
            <div type="text" class="green" v-if="scope.row.status=='1'">已完成</div>
            <div type="text" class="gray" v-if="scope.row.status=='2'">已取消</div>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="240" align="right" header-align="center">
          <template slot-scope="scope">
            <el-button type="text" v-if="scope.row.status=='0'" @click="complete(scope.row)">完成</el-button>
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
import { orderPage, orderStatus } from "@/api/hqgj/handheld";
export default {
  name: "order",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      timeArr: [], //时间数组
      query: {
        orderNo: "", //订单号
        type: "", //类型
        status: "" //状态
      },
      //类型容器
      typeArr: [
        {
          id: " ",
          name: "全部"
        },
        {
          id: "1",
          name: "代理记账"
        },
        {
          id: "2",
          name: "财税管家"
        },
        {
          id: "3",
          name: "专家问诊"
        }
      ],
      //状态容器
      statusArr: [
        {
          id: " ",
          name: "全部"
        },
        {
          id: "0",
          name: "待完成"
        },
        {
          id: "1",
          name: "已完成"
        },
        {
          id: "2",
          name: "已取消"
        }
      ],
      //订单数据
      tableData: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //选择tableSize事件
    handleTableSize(size) {
      this.tableSize = size;
      this.getList();
    },
    //选择tablePage事件
    handleTableCurrent(current) {
      this.tablePage = size;
      this.getList();
    },
    //请求page
    getList() {
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
      orderPage(this.query).then(res => {
        if (res.status == 1) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    //搜索请求
    search() {
      this.tablePage = 1;
      this.getList();
    },
    //完成操作
    complete(row) {
      let statusType = 1;
      let rowId = row.id;
      this.$confirm("确定是否完成改项服务?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          orderStatus(rowId, statusType).then(res => {
            if (res.status == 1) {
              this.$message.success("订单完成");
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
    /deep/ .el-date-editor--datetimerange.el-input__inner {
      width: 100%;
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
  .brown {
    color: #ffac2f;
  }
  .green {
    color: #00a854;
  }
  .gray {
    color: #5a5a5a;
  }
}
</style>