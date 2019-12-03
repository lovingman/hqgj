<template>
  <div class="main-box">
    <div class="header">
      <el-row>
        <el-button type="primary" style="float:left;" @click="create">创建</el-button>
        <el-col class="selectSearch" :span="10">
          <el-input placeholder="请输入名称" v-model="query.name" clearable class="input-with-select">
            <el-button slot="append">搜索</el-button>
          </el-input>
        </el-col>
      </el-row>
    </div>
    <div class="table-box">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column type="index" width="80" label="序号" align="center"></el-table-column>
        <el-table-column prop="name" sortable label="名称"></el-table-column>
        <el-table-column prop="time" sortable label="上传时间"></el-table-column>
        <el-table-column label="操作" fixed="right" width="240" align="right" header-align="center">
          <template>
            <el-button type="text">下载</el-button>
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
    </div>
  </div>
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
        name: "" //搜索
      },
      tableData: [
        {
          name: "我去",
          time: "2019-05-01"
        },
        {
          name: "我去2",
          time: "2019-05-11"
        },
        {
          name: "我去3",
          time: "2019-05-12"
        },
        {
          name: "我去4",
          time: "2019-05-22"
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
      this.$router.push({ path: "/hqgj/Policies/legalService/add" });
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

