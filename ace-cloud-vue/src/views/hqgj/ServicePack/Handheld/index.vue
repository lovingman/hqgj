<template>
  <el-table-column class="main-box">
    <div class="header">
      <el-row>
        <el-col :span="10">
          <el-button type="primary" style="float:left;" @click="create">创建</el-button>
        </el-col>
        <el-col class="selectSearch" :span="14">
          <el-col :span="5">
            <el-select v-model="query.service" placeholder="请选择">
              <el-option
                v-for="item in stautsArr"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="5" :offset="1">
            <el-select v-model="query.stauts" placeholder="请选择">
              <el-option
                v-for="item in stautsArr"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="12" :offset="1">
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
        <el-table-column prop="name" sortable label="服务名称"></el-table-column>
        <el-table-column prop="type" sortable label="类型"></el-table-column>
        <el-table-column prop="service" sortable label="服务机构"></el-table-column>
        <el-table-column prop="creatTime" sortable label="创建时间"></el-table-column>
        <el-table-column prop="state" sortable label="状态"></el-table-column>
        <el-table-column label="操作" fixed="right" width="240" align="right" header-align="center">
          <template>
            <el-button type="text">审核</el-button>
            <el-button type="text">上线</el-button>
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
          value: "选项3",
          label: "审核通过"
        },
        {
          value: "选项4",
          label: "未通过"
        },
        {
          value: "选项5",
          label: "已上线"
        },
        {
          value: "选项6",
          label: "已下线"
        }
      ], //状态容器
      tableData: [
        {
          name: "一站式代理计财，为企业省事、省心、省钱",
          type: "代理计财",
          service: "常德武陵区会计事务所",
          creatTime: "2019-05-01",
          state: "已上线"
        },
        {
          name: "一站式代理计财，为企业省事、省心、省钱",
          type: "代理计财",
          service: "常德武陵区会计事务所",
          creatTime: "2019-05-01",
          state: "已上线"
        },
        {
          name: "一站式代理计财，为企业省事、省心、省钱",
          type: "代理计财",
          service: "常德武陵区会计事务所",
          creatTime: "2019-05-01",
          state: "已上线"
        },
        {
          name: "一站式代理计财，为企业省事、省心、省钱",
          type: "代理计财",
          service: "常德武陵区会计事务所",
          creatTime: "2019-05-01",
          state: "已上线"
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
      this.$router.push({ path: "/hqgj/ServicePack/Handheld/add" });
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

