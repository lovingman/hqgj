<template>
  <div>
    <el-container class="login-container">
      <el-header style="margin-bottom:20px;">
        <el-row :gutter="10">
          <el-col :span="2" class="margin:auto">
            <!-- <div class="logos">
              <img :src="logoimg" class="logo-img" />
            </div>-->
          </el-col>
          <el-col :span="24">
            <div class="sys-name">常德市武陵区慧企管家服务平台</div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-card style="width:60rem;margin:auto;">
          <el-row :gutter="10">
            <el-col :span="12" style="margin:auto;">
              <div class="login-img-container">
                <img :src="loginimg" class="login-img" />
              </div>
            </el-col>
            <el-col :span="12" style="margin:auto">
              <el-form ref="loginForm" :model="loginForm" class="login-form" autocomplete="on" label-position="left"
                v-loading="loading" element-loading-text="请求中" element-loading-spinner="el-icon-loading">
                <div class="title-container">
                  <h3 class="title">登录</h3>
                </div>

                <el-form-item prop="username">
                  <span class="svg-container">
                    <svg-icon icon-class="user" />
                  </span>
                  <el-input ref="username" v-model="loginForm.username" placeholder="Username" name="username"
                    type="text" tabindex="1" autocomplete="on" />
                </el-form-item>

                <el-form-item prop="password">
                  <span class="svg-container">
                    <svg-icon icon-class="password" />
                  </span>
                  <el-input :key="passwordType" ref="password" v-model="loginForm.password" :type="passwordType"
                    placeholder="Password" name="password" tabindex="2" autocomplete="on" @keyup.native="checkCapslock"
                    @blur="capsTooltip = false" @keyup.enter.native="handleLogin" />
                  <span class="show-pwd">
                    <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
                  </span>
                </el-form-item>

                <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;"
                  @click.native.prevent="handleLogin">登录</el-button>
              </el-form>
            </el-col>
          </el-row>
        </el-card>
      </el-main>
      <el-footer style="height:10vh">
        <div class="footer">Copyright © 2015-2019 All Rights Reserved. 湘ICP备15020529号</div>
      </el-footer>
    </el-container>
    <el-dialog title="提示" :show-close="false" :modal="false" :visible.sync="dialogVisible" width="30%">
      <span>账户信息未同步</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="back">返 回</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import {
    removeToken
  } from '@/utils/auth'
  export default {
    name: "Login",
    data() {
      return {
        loginimg: require("@/assets/images/login.png"),
        logoimg: require("@/assets/images/logo.png"),
        loginForm: {
          username: "",
          password: ""
        },
        passwordType: "password",
        capsTooltip: false,
        loading: false,
        showDialog: false,
        redirect: undefined,
        otherQuery: {},
        dialogVisible: false
      };
    },
    watch: {
      $route: {
        handler: function (route) {
          const query = route.query;
          if (query) {
            this.loginForm.username = 'UN-VERIFICATION-ACCOUNT|' + query.username;
            this.loginForm.password = query.password;
            this.handleLogin();
          }
        },
        immediate: true
      }
    },
    created() {
      // window.addEventListener('storage', this.afterQRScan)
    },
    mounted() {
      if (this.loginForm.username === "") {
        this.$refs.username.focus();
      } else if (this.loginForm.password === "") {
        this.$refs.password.focus();
      }
    },
    destroyed() {
      // window.removeEventListener('storage', this.afterQRScan)
    },
    methods: {
      handleLogin() {
        this.loading = true;
        removeToken();
        this.$store
          .dispatch("user/login", this.loginForm)
          .then(() => {
            this.$router.push({
              path: this.redirect || "/",
              query: this.otherQuery
            });
            //this.$router.push({path: '/'})
            this.loading = false;
          })
          .catch(() => {
            this.loading = false;
            this.dialogVisible = true;
          });
      },
      back() {
        window.history.go(-1);
        this.dialogVisible = false;
      },
      getOtherQuery(query) {
        return Object.keys(query).reduce((acc, cur) => {
          if (cur !== "redirect") {
            acc[cur] = query[cur];
          }
          return acc;
        }, {});
      }
      // afterQRScan() {
      //   if (e.key === 'x-admin-oauth-code') {
      //     const code = getQueryObject(e.newValue)
      //     const codeMap = {
      //       wechat: 'code',
      //       tencent: 'code'
      //     }
      //     const type = codeMap[this.auth_type]
      //     const codeName = code[type]
      //     if (codeName) {
      //       this.$store.dispatch('LoginByThirdparty', codeName).then(() => {
      //         this.$router.push({ path: this.redirect || '/' })
      //       })
      //     } else {
      //       alert('第三方登录失败')
      //     }
      //   }
      // }
    }
  };
</script>

<style lang="scss">
  @import "@/styles/variables.scss";

  $bg: #f5f6f9;
  $light_gray: #1c1e23;
  $cursor: rgb(17, 15, 15);

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    display: none;

    .el-input {
      display: inline-block;
      height: 40px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 40px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: #f5f6f9;
      border-radius: 5px;
      color: #454545;
    }
  }

  $bg: #f5f6f9;
  $dark_gray: #889aa4;
  $light_gray: #666d7e;

  .login-container {
    min-height: 100%;
    width: 100%;
    background-color: $bg;
    overflow: hidden;
    text-align: center;

    .login-form {
      position: relative;
      width: 520px;
      max-width: 100%;
      padding: 60px 35px 120px;
      margin: 0 auto;
      overflow: hidden;
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 50px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .sys-name {
      width: 60rem;
      margin: 20px auto auto;
      text-align: left;
      line-height: 0;
      height: 50px;
      line-height: 50px;
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }

    .thirdparty-button {
      position: absolute;
      right: 0;
      bottom: 6px;
    }

    @media only screen and (max-width: 470px) {
      .thirdparty-button {
        display: none;
      }
    }
  }

  .footer {
    text-align: center;
    font-size: 0.8rem;
    color: #999;
    line-height: 4rem;
  }

  .login-img-container {
    min-height: 100%;
    width: 100%;
    background-color: #fff;
    overflow: hidden;
    text-align: center;
    padding: 60px 35px;
  }

  .login-img {
    max-width: 20rem;
    max-height: 20rem;
  }
</style>