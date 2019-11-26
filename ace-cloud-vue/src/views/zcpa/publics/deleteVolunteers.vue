<template>
  <div class="volunteer">
    <el-dialog :title="titleName" :visible.sync="delzyzVisible">
      <template>
        <div class="zyz">
          <el-table
            ref="multipleTable"
            :data="deleteVolunteerArr"
            style="width: 100%"
            @selection-change="handleDeleteCheck"
          >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="name" label="姓名" width="120"></el-table-column>
            <el-table-column prop="idCard" label="身份证" width="220"></el-table-column>
            <el-table-column prop="mobile" label="联系方式" width="200"></el-table-column>
            <el-table-column prop="userType" label="志愿者类型"></el-table-column>
          </el-table>
        </div>
      </template>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delzyzVisible = false">取 消</el-button>
        <el-button type="primary" @click="deleteVolunteers">删除</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String
    }
  },
  watch: {
    //标题
    title: {
      handler(newName, oldName) {
        if (newName) {
          this.titleName = newName;
        }
      },
      immediate: true, //立刻
      deep: true
    }
  },
  name: "deleteVolunteer",
  data() {
    return {
      titleName: "", //标题
      delzyzVisible: false, //是否显示
      deleteVolunteerArr: [] //删除志愿者弹出框table
    };
  },
  methods: {
    //删除志愿者
    deleteVolunteers() {
      this.$emit("deleteVolunteers");
    },
    //选中框事件
    handleDeleteCheck(val) {
      this.deleteCheck = val;
      
    }
  }
};
</script>

<style>
</style>