<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <!-- <el-button type="primary" style="float:left;">数据导出</el-button> -->
        <el-col class="selectSearch" :span="24">
          <el-col :span="9">
            <el-col :span="11">
              <el-date-picker v-model="query.startTime" format="yyyy-MM-dd" value-format="yyyy-MM-dd" type="date"
                placeholder="选择开始时间"></el-date-picker>
            </el-col>
            <el-col :span="2" style=" text-align: center; line-height: 36px;">
              <span class="text">至</span>
            </el-col>
            <el-col :span="11">
              <el-date-picker v-model="query.endTime" format="yyyy-MM-dd" value-format="yyyy-MM-dd" type="date"
                placeholder="选择结束时间"></el-date-picker>
            </el-col>
          </el-col>
          <el-col :span="3" :offset="1">
            <el-select v-model="query.type" clearable placeholder="请选择类型">
              <el-option v-for="item in typeArr" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-col>
          <el-col :span="3" :offset="1">
            <el-select v-model="query.status" clearable placeholder="请选择状态">
              <el-option v-for="item in statusArr" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-col>
          <el-col :span="6" :offset="1">
            <el-input placeholder="请输入订单号" v-model.trim="query.orderNo" clearable class="input-with-select">
              <el-button slot="append" icon="el-icon-search" :loading="loading" @click="search"></el-button>
            </el-input>
          </el-col>
        </el-col>
      </el-row>
    </div>
    <div class="table-box">
      <el-table :data="tableData" max-height="475" style="width: 100%" v-loading="loading" element-loading-text="加载中"
        element-loading-spinner="el-icon-loading">
        <el-table-column type="index" width="60" label="序号"></el-table-column>
        <el-table-column prop="orderNo" sortable label="订单号" min-width="190" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.orderNo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="type" sortable label="类型" width="100">
          <template slot-scope="scope">
            <div type="text" v-if="scope.row.type=='1'">代理记账</div>
            <div type="text" v-if="scope.row.type=='2'">财税管理</div>
            <div type="text" v-if="scope.row.type=='3'">专家问诊</div>
          </template>
        </el-table-column>
        <el-table-column prop="orgName" sortable label="服务机构" min-width="160" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.orgName }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="companyName" sortable label="企业名称" min-width="200" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{ scope.row.companyName }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="createDate" sortable label="下单时间" width="180"></el-table-column>
        <el-table-column prop="status" sortable label="状态" width="100">
          <template slot-scope="scope">
            <div type="text" class="brown" v-if="scope.row.status=='0' || scope.row.status=='2' ">待完成</div>
            <div type="text" class="gray" v-if="scope.row.status=='1'">已取消</div>
            <div type="text" class="green" v-if="scope.row.status>'30'">已完成</div>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="100" align="right" header-align="center">
          <template slot-scope="scope">
            <el-button type="text" v-if="scope.row.status=='0'" @click="complete(scope.row)">完成</el-button>
            <el-button type="text" @click="seeClcik(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleTableSize" @current-change="handleTableCurrent" :current-page="tablePage"
        :page-size="tableSize" :total="total" background layout="total,sizes,prev, pager, next ,jumper"></el-pagination>
    </div>
  </div>
</template>

<script>
  import {
    orderPage,
    orderStatus
  } from "@/api/hqgj/handheld";
  export default {
    name: "order",
    data() {
      return {
        total: 0, //tablepage总数
        tablePage: 1, //第几页参数
        tableSize: 10, //每页参数
        // timeArr: [], //时间数组
        loading: false, //loadign加载状态
        query: {
          orderNo: "", //订单号
          type: "", //类型
          status: "", //状态
          startTime: "", //开始日期
          endTime: "" //结束日期
        },
        //类型容器
        typeArr: [{
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
        statusArr: [{
            id: " ",
            name: "全部"
          },
          {
            id: "0",
            name: "待完成"
          },
          {
            id: "1",
            name: "已取消"
          },
          {
            id: "31,32",
            name: "已完成"
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
        this.tablePage = current;
        this.getList();
      },
      //请求page
      getList() {
        this.loading = true;
        this.query = Object.assign(this.query, {
          pageNum: this.tablePage,
          pageSize: this.tableSize,
          totalRecord: this.total
        });
        // if (this.timeArr) {
        //   this.query.startTime = this.timeArr.length > 0 ? this.timeArr[0] : ""; //开始时间
        //   this.query.endTime = this.timeArr.length > 0 ? this.timeArr[1] : ""; //结束时间
        // } else {
        //   this.query.startTime = "";
        //   this.query.endTime = "";
        // }
        orderPage(this.query).then(res => {
          if (res.status == 1) {
            this.tableData = res.rows;
            this.total = res.total;
            this.loading = false;
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
        let statusType = "31";
        let rowId = row.id;
        this.$confirm("确定是否完成该项服务?", "提示", {
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
      },
      //订单管理查看
      seeClcik(data) {
        this.$router.push({
          path: "/hqgj/ServicePack/Handheld/orderSee",
          query: {
            id: data.id
          }
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

      /deep/ .el-range-editor.el-input__inner {
        width: 100%;
      }

      .selectSearch {
        float: right;

        /deep/ .el-date-editor.el-input {
          width: 100%;
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

    .blue {
      color: #1890ff;
    }

    .red {
      color: #ff5a5a;
    }
  }
</style>