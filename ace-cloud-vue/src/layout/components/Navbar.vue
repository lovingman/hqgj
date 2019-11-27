<template>
  <div class="navbar">
    <div class="title">
      <el-row style="margin-left: 20px;">
        <el-col :span="4" style="margin-top: 5px">
          <img src="@/assets/images/huiqiicon.png" alt="">
        </el-col>
        <el-col :span="12">
          <div class="title-nm">
            <span>慧企管家服务平台</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <hamburger id="hamburger-container" :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />

    <div class="right-menu">
      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <div class="head-img">
            <img src="@/assets/images/huiqiicon.png" alt />
          </div>
         <div class="user-name text-center">{{ name }}</div>
          <i class="el-icon-caret-bottom" style="float:left;" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/profile/index">
            <el-dropdown-item>个人中心</el-dropdown-item>
          </router-link>

          <el-dropdown-item>
            <span style="display:block;" @click="logout">安全退出</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import Search from '@/components/HeaderSearch'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    Search
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'name',
      'device'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  position: relative;
  height: 45px;
  overflow: hidden;
  background: #fff;
  /*padding-right: 150px;*/
  width: 100%;
  /*box-shadow: 0 1px 4px rgba(0,21,41,.08);*/

  .title{
    background-color:rgb(0, 65, 136);
    width: 250px;
    float: left;
    height: 45px;
    padding: 0;
    text-align: center
  }
  .title-nm{
    color: white;
    line-height: 45px;
  }
  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    line-height: 3.15rem;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        display: flex;
        align-items: center;
        position: relative;

        .head-img {
          width: 30px;
          height: 30px;
          border-radius: 50%;
          overflow: hidden;
          margin-right: 15px;

          img {
            object-fit: cover;
            width: 100%;
          }
        }
        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -14px;
          top: 18px;
          font-size: 12px;
        }
      }
    }
  }
}
 .user-name {

   }
.navbar .right-menu .right-menu-item.hover-effect[data-v-d16d6306]:hover {
    background: rgba(0, 0, 0, 0);
}

</style>
