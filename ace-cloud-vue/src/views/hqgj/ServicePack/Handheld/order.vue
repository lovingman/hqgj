<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-button type="primary" style="float:left;" @click="create">数据导出</el-button>
        <el-col class="selectSearch" :span="18">
          <el-col :span="10">
            <el-date-picker
              v-model="query.times"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-col>
          <el-col :span="3" :offset="1">
            <el-select v-model="query.stauts" placeholder="请选择类型">
              <el-option
                v-for="item in stautsArr"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="3" :offset="1">
            <el-select v-model="query.state" placeholder="请选择状态">
              <el-option
                v-for="item in stateArr"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="5" :offset="1">
            <el-input placeholder="请输入订单号" v-model="query.name" clearable class="input-with-select">
              <el-button slot="append">搜索</el-button>
            </el-input>
          </el-col>
        </el-col>
      </el-row>
    </div>
    <el-table-column class="table-box">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="80"></el-table-column>
        <el-table-column prop="oddNumbers" sortable label="订单号"></el-table-column>
        <el-table-column prop="type" sortable label="类型"></el-table-column>
        <el-table-column prop="mechanism" sortable label="机构名称"></el-table-column>
        <el-table-column prop="name" sortable label="企业名称"></el-table-column>
        <el-table-column prop="time" sortable label="下单时间"></el-table-column>
        <el-table-column prop="state" sortable label="状态"></el-table-column>
        <el-table-column label="操作" fixed="right" width="240" align="right" header-align="center">
          <template>
            <el-button type="text">完成</el-button>
            <el-button type="text">编辑</el-button>
            <el-button type="text">删除</el-button>
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
    </el-table-column>
  </div>
</template>

<script>
export default {
  name: "order",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      query: {
        name: "", //搜索
        status: "", //类型
        state: "", //状态
        times: "" //时间
      },
      //类型容器
      stautsArr: [
        {
          value: "选项1",
          label: "全部"
        },
        {
          value: "选项2",
          label: "代理计账"
        },
        {
          value: "选项3",
          label: "财税管家"
        },
        {
          value: "选项4",
          label: "专家问诊"
        }
      ],
      //状态容器
      stateArr: [
        {
          value: "选项1",
          label: "全部"
        },
        {
          value: "选项2",
          label: "待完成"
        },
        {
          value: "选项3",
          label: "已完成"
        },
        {
          value: "选项4",
          label: "已取消"
        }
      ],
      //订单数据
      tableData: [
        {
          oddNumbers: "12312312312",
          name: "一站式代理计财，为企业",
          type: "代理计财",
          mechanism: "常德市正信会计",

          time: "2019-11-21 09:31:08",
          state: "进行中"
        },
        {
          oddNumbers: "12312312312",
          name: "一站式代理计财，为企业",
          type: "代理计财",
          mechanism: "常德市正信会计",

          time: "2019-11-21 09:31:08",
          state: "进行中"
        },
        {
          oddNumbers: "12312312312",
          name: "一站式代理计财，为企业",
          type: "代理计财",
          mechanism: "常德市正信会计",

          time: "2019-11-21 09:31:08",
          state: "进行中"
        },
        {
          oddNumbers: "12312312312",
          name: "一站式代理计财，为企业",
          type: "代理计财",
          mechanism: "常德市正信会计",

          time: "2019-11-21 09:31:08",
          state: "进行中"
        }
      ]
    };
  },
  methods: {
    //选择tableSize事件
    handleTableSize() {},
    //选择tablePage事件
    handleTableCurrent() {}
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
}
</style>