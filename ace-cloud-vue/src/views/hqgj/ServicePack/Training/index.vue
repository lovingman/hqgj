<template>
  <el-table-column class="main-box">
    <div class="header">
      <el-row>
        <el-button type="primary" style="float:left;" @click="create">创建</el-button>
        <el-col class="selectSearch" :span="10">
          <el-col :span="7" style="margin-right:20px;">
            <el-select v-model="query.stauts" placeholder="请选择">
              <el-option
                v-for="item in stautsArr"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="16">
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
        <el-table-column prop="title" sortable label="培训标题"></el-table-column>
        <el-table-column prop="train" sortable label="培训人数"></el-table-column>
        <el-table-column prop="report" sortable label="报名人数"></el-table-column>
        <el-table-column prop="start" sortable label="开始时间"></el-table-column>
        <el-table-column prop="end" sortable label="截止时间"></el-table-column>
        <el-table-column prop="state" sortable label="状态"></el-table-column>
        <el-table-column label="操作" fixed="right" width="280" align="right" header-align="center">
          <template>
            <el-button type="text">发布</el-button>
            <el-button type="text">审核</el-button>
            <el-button type="text" @click="registrationClick">报名管理</el-button>
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
  </el-table-column>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      total: 0, //tablepage总数
      tablePage: 1, //第几页参数
      tableSize: 10, //每页参数
      query: {
        name: "", //搜索
        status: "" //状态
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
      tableData: [
        {
          title: "常德市中小企业文化培训讲座",
          train: "12",
          report: "10",
          start: "2019-05-01",
          end: "2019-05-31",
          state: "进行中"
        },
        {
          title: "常德市中小企业文化培训讲座2",
          train: "12",
          report: "10",
          start: "2019-05-01",
          end: "2019-05-31",
          state: "进行中"
        },
        {
          title: "常德市中小企业文化培训讲座3",
          train: "12",
          report: "10",
          start: "2019-05-01",
          end: "2019-05-31",
          state: "进行中"
        },
        {
          title: "常德市中小企业文化培训讲座4",
          train: "12",
          report: "10",
          start: "2019-05-01",
          end: "2019-05-31",
          state: "进行中"
        }
      ]
    };
  },
  methods: {
    //选择tableSize事件
    handleTableSize() {},
    //选择tablePage事件
    handleTableCurrent() {},
    //创建
    create() {
      this.$router.push({ path: "/hqgj/ServicePack/Training/add" });
    },
    //报名管理
    registrationClick() {
      this.$router.push({ path: "/hqgj/ServicePack/Training/registration" });
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

