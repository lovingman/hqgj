<template>
  <div class="container">
    <div class="table">
      <div class="handle-box">
        <el-row>
          <el-col :span="12" v-if="userBtn['/hqgj/baseOrganization/create']">
            <el-button
              @click="create"
              icon="el-icon-plus"
              style="float: left;border-radius:5px;"
              type="primary"
            >创建</el-button>
          </el-col>
          <el-col :span="12" v-else="userBtn['/hqgj/baseOrganization/create']">&nbsp;</el-col>
          <!--<el-col :span="10">-->
          <!--<el-dropdown @command="handleCommand" style="line-height: 35px;" trigger="click">-->
          <!--<el-button>-->
          <!--批量操作<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
          <!--</el-button>-->
          <!--&lt;!&ndash;<span style="color: black" >批量操作<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></span>&ndash;&gt;-->
          <!--<el-dropdown-menu slot="dropdown">-->
          <!--<el-dropdown-item command="importXls">批量导入</el-dropdown-item>-->
          <!--<el-dropdown-item command="exportXls">批量导出</el-dropdown-item>-->
          <!--<el-dropdown-item command="deleteIds">批量删除</el-dropdown-item>-->
          <!--</el-dropdown-menu>-->
          <!--</el-dropdown>-->
          <!--</el-col>-->
          <el-col :span="6">
            <el-select
              style="float: right"
              v-model="typeObj"
              clearable
              placeholder="全部类型"
              @change="handleStatus"
            >
              <el-option
                :key="item.code"
                :label="item.name"
                :value="item.code"
                v-for="item in dict"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="6" class="selectSearch">
            <el-input
              class="input-with-select"
              clearable
              placeholder="请输入机构名称"
              v-model="query.orgName"
            >
              <el-button :loading="loading" @click="search" icon="el-icon-search" slot="append"></el-button>
            </el-input>
          </el-col>
        </el-row>
      </div>
      <el-table
        :data="list"
        @selection-change="handleSelectionChange"
        class="table"
        max-height="475"
        ref="multipleTable"
        v-loading="loading"
      >
        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
        <el-table-column label="机构名称" :show-overflow-tooltip="true" prop="orgName" width="250"></el-table-column>
        <el-table-column label="机构类型" prop="type" width="120">
          <template slot-scope="scope">
            <div type="text" v-if="scope.row.type=='1'">律师事务所</div>
            <div type="text" v-if="scope.row.type=='2'">会计事务所</div>
            <div type="text" v-if="scope.row.type=='3'">培训机构</div>
          </template>
        </el-table-column>
        <el-table-column label="联系方式" prop="contactPersonTel" width="150"></el-table-column>
        <el-table-column label="地址" :show-overflow-tooltip="true" prop="completeAddress"></el-table-column>
        <el-table-column align="right" fixed="right" header-align="center" label="操作" width="200">
          <template slot-scope="scope">
            <el-button @click="person(scope.$index,scope.row)" v-if="userBtn['/hqgj/baseOrganizationMember/page']" height="40" type="text">成员管理</el-button>
            <span class="strightline" v-if="userBtn['/hqgj/baseOrganizationMember/page']">|</span>
            <el-button @click="edit(scope.$index,scope.row)" v-if="userBtn['/hqgj/baseOrganization/update']" height="40" type="text">编辑</el-button>
            <span class="strightline" v-if="userBtn['/hqgj/baseOrganization/update']">|</span>
            <el-button @click="handleDele(scope.$index,scope.row)" v-if="userBtn['/hqgj/baseOrganization/deleteById']" type="text">删除</el-button>
            <span class="strightline" v-if="userBtn['/hqgj/baseOrganization/deleteById']">|</span>
            <el-button @click="preview(scope.$index,scope.row)" type="text">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        :current-page="currentPage"
        :page-size="pagesize"
        :total="total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        background
        layout="total, sizes, prev, pager, next, jumper"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import {
  getList,
  deleteById,
  deleteByIds,
  exportXls,
  personPage
} from "@/api/hqgj/service";
import { getAreaTree, getDict } from "@/api/sys";
import {mapGetters} from "vuex";

export default {
  name: "index",
  data() {
    return {
      loading: false,
      currentPage: 1, //初始页
      pagesize: 10, //  每页的数据
      total: 0,
      multipleSelection: [], //选中行数据
      dict: [], //服务机构类型
      list: [],
      typeObj: "",
      query: {
        orgName: "",
        type: ""
      },
      query2: {
        orgId: ""
      },
      exportDatas: {
        orgName: "",
        type: ""
      }
    };
  },
  computed: {
    ...mapGetters(["userBtn"])
  },
  created() {
    this.getlist();
    this.dictQuery();
  },
  methods: {
    handleQuery: function() {
      this.currentPage = 1;
      this.getlist();
    },
    handleSizeChange: function(size) {
      this.pagesize = size;
      //每页下拉显示数据
      this.getlist();
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
      //点击第几页
      this.getlist();
    },
    //获取列表数据
    getlist() {
      this.loading = true;
      this.query = Object.assign(this.query, {
        pageNum: this.currentPage,
        pageSize: this.pagesize,
        totalRecord: this.total
      });
      getList(this.query).then(response => {
        this.total = response.total;
        this.list = response.rows;
        this.loading = false;
      });
    },
    search() {
      this.handleQuery();
    },
    //服务机构类型搜索框数据
    handleStatus() {
      if (this.typeObj == 0) {
        this.query.type = "";
      } else {
        this.query.type = this.typeObj;
      }
    },
    //删除
    handleDele(index, data) {
      this.query2.orgId = data.id;
      personPage(this.query2).then(response => {
        if (response.total != 0) {
          this.$confirm("该机构下有成员信息，是否一并删除?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
            .then(() => {
              this.id = data.id;
              deleteById(this.id).then(response => {
                this.$message.success("删除成功");
                this.getlist();
              });
            })
            .catch(() => {});
        } else {
          this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
            .then(() => {
              this.id = data.id;
              deleteById(this.id).then(response => {
                this.$message.success("删除成功");
                this.getlist();
              });
            })
            .catch(() => {});
        }
      });
    },
    //批量操作
    handleCommand(command) {
      //导入
      if (command == "importXls") {
        console.log(456);
      }
      //导出
      if (command == "exportXls") {
        this.exportDatas.orgName = this.query.orgName;
        this.exportDatas.type = this.query.type;
        exportXls(this.exportDatas)
          .then(response => {
            const blob = new Blob([response], {
              type: "application/vnd.ms-excel"
            });
            const fileName = "服务机构信息.xlsx";
            if (window.navigator && window.navigator.msSaveOrOpenBlob) {
              navigator.msSaveBlob(blob, fileName);
            } else {
              const a = document.createElement("a");
              a.href = URL.createObjectURL(blob);
              a.download = fileName;
              a.style.display = "none";
              document.body.appendChild(a);
              a.click();
              URL.revokeObjectURL(a.href);
              document.body.removeChild(a);
            }
          })
          .catch(error => {
            this.$message.error(error);
          });
      }
      //删除
      if (command == "deleteIds") {
        if (this.multipleSelection.length) {
          this.ids = this.multipleSelection.map(item => item.id).join(",");
          this.$confirm("此操作将永久删除选中数据, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
            .then(() => {
              deleteByIds(this.ids).then(response => {
                this.$message.success("删除成功");
                this.multipleSelection = [];
                this.getlist();
              });
            })
            .catch(() => {});
        } else {
          this.$message({
            message: `未选中数据`,
            type: "warning"
          });
        }
      }
    },
    //获取选中行数据
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //获取项目类型数据
    dictQuery() {
      getDict("53").then(response => {
        this.dict = response.data["53"];
      });
    },
    person(index, data) {
      this.$router.push({
        path: "/hqgj/BasicData/service/Member",
        query: { id: data.id }
      });
    },
    create() {
      this.$router.push({ path: "/hqgj/BasicData/service/create" });
    },
    edit(index, data) {
      this.$router.push({
        path: "/hqgj/BasicData/service/edit",
        query: { id: data.id }
      });
    },
    preview(index, data) {
      this.$router.push({
        path: "/hqgj/BasicData/service/details",
        query: { id: data.id }
      });
    }
  }
};
</script>

<style scoped>
.container {
  padding: 20px;
  background-color: #fff;
}
.input-with-select {
  float: right;
  width: 350px;
}
</style>
