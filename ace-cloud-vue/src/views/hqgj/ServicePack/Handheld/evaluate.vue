<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-col class="selectSearch" :span="12">
          <el-col :span="12">
            <el-date-picker
              v-model="query.times"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-col>
          <el-col :span="11" :offset="1">
            <el-input placeholder="请输入名称" v-model="query.name" clearable class="input-with-select">
              <el-button slot="append">搜索</el-button>
            </el-input>
          </el-col>
        </el-col>
      </el-row>
    </div>
    <el-table-column class="table-box">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="selection" width="80"></el-table-column>
        <el-table-column prop="name" sortable label="姓名"></el-table-column>
        <el-table-column prop="content" sortable label="评价内容"></el-table-column>
        <el-table-column prop="evaValue" sortable label="服务评分">
          <template slot-scope="scope">
            <el-rate v-model="scope.row.evaValue" :allow-half="true" disabled text-color="#ff9900"></el-rate>
          </template>
        </el-table-column>
        <el-table-column prop="mechanism" sortable label="服务机构"></el-table-column>
        <el-table-column prop="times" sortable label="评价时间"></el-table-column>
        <el-table-column label="操作" fixed="right" width="240" align="right" header-align="center">
          <template>
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
  name: "evaluate",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      query: {
        name: "", //搜索
        times: "" //时间
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
      tableData: [
        {
          name: "12312312312",
          content: "为企业节省各类成本，提高效率，非常推荐",
          evaValue: 4,
          mechanism: "常德市正信会计",
          times: "2019-11-21 09:31:08"
        },
        {
          name: "12312312312",
          content: "为企业节省各类成本，提高效率，非常推荐",
          evaValue: 4,
          mechanism: "常德市正信会计",
          times: "2019-11-21 09:31:08"
        },
        {
          name: "12312312312",
          content: "为企业节省各类成本，提高效率，非常推荐",
          evaValue: 4,
          mechanism: "常德市正信会计",
          times: "2019-11-21 09:31:08"
        },
        {
          name: "12312312312",
          content: "为企业节省各类成本，提高效率，非常推荐",
          evaValue: 4,
          mechanism: "常德市正信会计",
          times: "2019-11-21 09:31:08"
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