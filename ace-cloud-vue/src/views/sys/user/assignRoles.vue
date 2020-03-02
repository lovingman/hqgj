<template>
  <div class="app-container">
    <el-card shadow="never">
      <el-container>
        <el-header style="height:1.5rem">
          <div class="section_title">{{o.name}}的角色</div>
        </el-header>
        <el-main
          v-loading="loading"
          element-loading-text="请求中"
          element-loading-spinner="el-icon-loading"
        >

         <el-transfer :titles="titles" v-model="value" :data="data"></el-transfer>


        </el-main>
        <el-footer>
            <div class="buttons">
            <el-button @click="back" style="border-radius:5px;">取消</el-button>
                        <el-button type="primary"  @click="submitForm()" style="border-radius:5px;">保存</el-button>
            </div>
        </el-footer>
      </el-container>
    </el-card>
  </div>
</template>

<script>
import { getAllRoles,assignRoles,getMyRoles,getById,deleteassignRoles} from "@/api/sys/user";
import { mapGetters } from 'vuex';
export default {
  data() {
    return {
      id:"",
      loading: false,
      titles:["待分配","已分配"],
      data:[],
      o: {
      },
      value:[]
    };
  },
   computed: {
    ...mapGetters([
      'userProp'
    ])
  },
  created() {
    this.load();
    this.getAllRoles();
    this.getMyRoles(this.$route.query.id);
  },
  methods: {
    load(){
        this.loading = true;
        this.id = this.$route.query.id;
        getById(this.id)
        .then(response => {
          this.loading = false;
          this.o=response.data;
        })
    },
    getAllRoles(){
        this.loading = true;
        getAllRoles()
        .then(response => {
          this.loading = false;
          for(var e of response.data){
              this.data.push({key:e.roleId,label:e.roleName});
          }
        })
    },
     getMyRoles(userId){
        this.loading = true;
        getMyRoles(userId)
        .then(response => {
          this.loading = false;
          for(var e of response.data){
              this.value.push(e.roleId);
          }
        })
    },
    submitForm(formName) {
        this.loading = true;
        if(this.value.length>0){
            assignRoles({userId:this.id,roleId:this.value.join(",")})
                .then(response => {
                    this.loading = false;
                    this.$confirm(response.message + " 是否返回?", "提示", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "success",
                        center: true
                    })
                        .then(() => {
                            this.back();
                        })
                        .catch(() => {});
                })
                .catch(error => {
                    this.loading = false;

                });
        }else{
            deleteassignRoles({userId:this.id})
            .then(response => {
                this.loading = false;
                this.$confirm(response.message + " 是否返回?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "success",
                    center: true
                })
                    .then(() => {
                        this.back();
                    })
                    .catch(() => {});
            })
            .catch(error => {
                this.loading = false;

            });

        }

    },
    back() {
      this.$router.push({ path: "/sys/user" });
    }
  }
};
</script>
<style>
</style>



