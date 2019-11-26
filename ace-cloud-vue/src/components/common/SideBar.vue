<template>
  <div class="sidebar">
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
          :default-active="currentMenu"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          router
          unique-opened
          active-text-color="#ffd04b"
        >
          <template v-for="(item, i) in menuArr">
            <template v-if="item.children != null && item.children.length!=0 ">
              <el-submenu :index="item.href" :key="i" class="first-menu">
                <template slot="title">
                  <i :class="item.icon"></i>
                  <span slot="title">{{ item.text }}</span>
                </template>
                <template v-for="(subItem, j) in item.children">
                  <el-submenu class="second-menu"                    
                  v-if="subItem.children != null && subItem.children.length!=0"
                    :index="subItem.href"
                    :key="j"
                  >
                    <template slot="title">{{subItem.text}}</template>
                    <!-- 三级菜单 -->
                    <el-menu-item class="third-menu"
                      v-for="(threeItem,k) in subItem.children"
                      :key="k"
                      :route="{path:threeItem.href}"
                      :index="threeItem.href"
                    >{{ threeItem.text }}</el-menu-item>
                  </el-submenu>
                  <el-menu-item
                    v-else
                    :index="subItem.href"
                    :route="{path:subItem.href}"
                    :key="subItem.index"
                  >{{ subItem.text }}</el-menu-item>
                </template>
              </el-submenu>
            </template>
            <!-- 没有二级菜单 -->
            <template v-else-if="item.id!='01'">
              <el-menu-item :index="item.href" :route="{path:item.href}" :key="i">
                <i :class="item.icon"></i>
                <span slot="title">{{ item.text }}</span>
              </el-menu-item>
            </template>
          </template>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Sidebar",
  data() {
    return {
      currentMenu:'/tzdw/IndustryCommerceAssociation',
      menuArr: [] //菜单数据
    };
  },
  methods: {
    //页面刷新 得到当前url
    getUrl () {
      this.currentMenu = this.$route.path;
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    // 得到左侧菜单树数据
    getSysTreeMenuData() {
      this.$http
        .get(this.globalData.api + "/portal/system/getSysTreeMenu", {
          emulateJSON: true
        })
        .then(res => {
          // console.log(res);
          if (res.body.status == 1) {
            // this.menuArr = res.body.data;
          } else {
          }
        })
        .catch(res => {
          //捕捉异常
          console.log(res);
        });
    }
  },
  /**
   * 组件实例刚被创建，组件属性计算之前，如data属性等
   */
  beforeCreate() {},
  /**
   * 组件实例创建完成，属性已绑定，但DOM还未生成，el属性还不存在
   */
  created() {
    this.getUrl();
    this.getSysTreeMenuData();
  },
  /**
   * 模板编译/挂载之前
   */
  beforeMount() {},
  /**
   * 模板编译/挂载之后(不保证组件已在document中)
   */
  mounted() {},
  /**
   * 组件更新之前
   */
  beforeUpdate() {},
  /**
   * 组件更新之后
   */
  updated() {},
  /**
   * 组件销毁前调用
   */
  beforeDestroy() {},
  /**
   * 组件销毁后调用
   */
  destroyed() {}
};
</script>
<style  scoped lang="less">

.menu-ul > li {
  padding: 10px 0;
  cursor: pointer;
}
.el-menu-item-group /deep/ .el-menu-item-group__title {
  display: none;
}
.sidebar {
  .second-menu.is-active  {
    /deep/ .el-submenu__title {
      position: relative;
      color: #df3737;
      i {
        color: #df3737;
      }
    }
    // /deep/  .el-submenu__title::before {
    //   content: "";
    //   position: absolute;
    //   top: 21px;
    //   left: 0;
    //   z-index: 10;
    //   width: 2px;
    //   height: 16px;
    //   background-color: #df3737;
    // }
  }
  
  .el-menu {
    border-right: none;
    /deep/ .el-submenu__icon-arrow {
      font-weight: bold;
    }
  }
  /deep/ .el-submenu {
    /deep/ .el-menu{
      // max-height: 340px;
      // overflow-y: auto;
      // overflow-x: hidden;
      .el-menu-item{
        font-size: 12px;
      }
    }
  }
  .el-menu-item {
    color: #666666;
  }
  .el-menu /deep/ .el-submenu__title {
    border-bottom: 1px solid #ebeef5;
    color: #666666;
  }
  .el-menu /deep/ .el-menu-item {
    border-bottom: 1px solid #ebeef5;
  }
  .el-menu-item-group /deep/ .el-menu-item {
    border-bottom: none;
  }
  .el-menu-item.is-active{
    background-color: #df3737;
    color: #ffffff !important;
  }
}
.sidebar  .el-menu  /deep/  .first-menu.is-active > .el-submenu__title {
    position: relative;
    color: #df3737 !important;
    i {
      color: #df3737 !important;
    }
  }
.sidebar .el-menu  /deep/  .first-menu.is-active > .el-submenu__title::before {
      content: "";
      position: absolute;
      top: 21px;
      left: 0;
      z-index: 10;
      width: 2px;
      height: 16px;
      background-color: #df3737 ;
  }
</style>
