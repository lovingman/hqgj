<template>
  <el-table-column class="main-box">
    <div class="header">
      <el-row>
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
        <el-table-column prop="enterprise" sortable label="企业名称"></el-table-column>
        <el-table-column prop="name" sortable label="姓名"></el-table-column>
        <el-table-column prop="phone" sortable label="手机号码"></el-table-column>
        <el-table-column prop="whether" sortable label="是否签到"></el-table-column>
        <el-table-column prop="time" sortable label="报名时间"></el-table-column>
        <el-table-column
          label="操作"
          fixed="right"
          width="240"
          align="right"
          header-align="center"
          class="right-txt"
        >
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
  </el-table-column>
</template>

<script>
export default {
  name: "registration",
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
          label: "已签到"
        },
        {
          value: "选项3",
          label: "未签到"
        }
      ], //状态容器
      tableData: [
        {
          enterprise: "湖南华彩伟业网络科技有限公司",
          name: "阿尔斯",
          phone: "17688876666",
          whether: "是",
          time: "2019-11-21 09:31:08"
        },
        {
          enterprise: "湖南华彩伟业网络科技有限公司2",
          name: "阿尔斯",
          phone: "17688876666",
          whether: "是",
          time: "2019-11-21 09:31:08"
        },
        {
          enterprise: "湖南华彩伟业网络科技有限公司3",
          name: "阿尔斯",
          phone: "17688876666",
          whether: "是",
          time: "2019-11-21 09:31:08"
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
    /deep/ .el-table th:last-child > .cell {
      text-align: right;
      padding-right: 10px;
    }
  }
}
</style>>

