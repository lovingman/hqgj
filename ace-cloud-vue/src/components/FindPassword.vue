<template>
  <div class="register">
    <div class="register-wrap">
      <div class="header">
        <img src="./../assets/images/platform-logo@2x.png">
        <span>找回密码</span>
      </div>
      <template>
        <el-tabs class="tabs-wrap" v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="身份验证" name="first">
            <div class="same-layout">
              <el-form
                class="demo-ruleForm"
                :model="ruleForm"
                :rules="rules"
                ref="ruleForm"
                label-position="left"
                label-width="0px"
              >
                <el-form-item class="demo" prop="phoneNumber">
                  <el-input
                    type="text"
                    v-model="ruleForm.phoneNumber"
                    clearable
                    placeholder="请输入手机号码"
                    prefix-icon="iconfont icon-tz-shouji "
                  ></el-input>
                </el-form-item>
                <el-form-item class="tuXingMa" prop="tuXingMa">
                  <el-input
                    type="password"
                    v-model="ruleForm.tuXingMa"
                    clearable
                    placeholder="请输入图形码"
                    prefix-icon="iconfont icon-tz-tuxingma"
                    @keyup.enter.native="handleSubmit2"
                  ></el-input>
                  <span class="char-code">sJKAk3</span>
                </el-form-item>
                <el-form-item class="tuXingMa" prop="yzCode">
                  <el-input
                    type="text"
                    v-model="ruleForm.yzCode"
                    clearable
                    placeholder="请输入验证码"
                    prefix-icon="iconfont icon-tz-yanzhengma"
                   
                  ></el-input>
                  <el-button class="btn-yz-code" type="danger">获取验证码</el-button>
                </el-form-item>
              </el-form>
              <div class="step-wrap">
                <div class="left">
                  已有账号，
                  <el-button type="text" @click="linkLogin">登录</el-button>
                </div>
                <div class="right">
                  <el-button type="danger" @click="identityStep">下一步</el-button>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="密码重置" name="second">
            <div class="same-layout">
              <el-form
                class="demo-ruleForm"
                :model="accountPassword"
                :rules="accountPasswordRules"
                ref="accountPassword"
                label-position="left"
                label-width="0px"
              >
                <el-form-item class="demo" prop="password">
                  <el-input
                    type="text"
                    v-model="accountPassword.password"
                    clearable
                    placeholder="请输入密码"
                    prefix-icon="iconfont icon-tz-mima "
                  ></el-input>
                </el-form-item>
                <el-form-item class="tuXingMa" prop="passwordAgain">
                  <el-input
                    type="password"
                    v-model="accountPassword.passwordAgain"
                    clearable
                    placeholder="请再次输入密码"
                    prefix-icon="iconfont icon-tz-mima"
                    
                  ></el-input>
                </el-form-item>
               
                <div class="step-wrap">
                    <div class="left">
                    已有账号，
                    <el-button type="text" @click="linkLogin">登录</el-button>
                    </div>
                    <div class="right">
                    <el-button type="danger" @click="passwordResetComfirm">确&nbsp;定</el-button>
                    </div>
                </div>
              </el-form>
              
            </div>
          </el-tab-pane>
          <el-tab-pane label="重置成功" name="third">
            <div class="same-layout">
              <div class="tip">
                <span>重置成功！</span>
                <!-- <div class="middle">
                  <span>您注册的手机号为：18888888888</span>
                  <span>您注册的账号为：18888@qq.com</span>
                </div> -->
                <div class="down">
                  <el-button type="danger" @click="linkLogin">立即登录</el-button>
                </div>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </template>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      activeName: "first",
      ruleForm: {     //手机验证表单
        phoneNumber: "",    //手机码号
        tuXingMa: "",    //图形码
        yzCode: ""   // 验证码
      },
      rules: {  //手机验证规则
        phoneNumber: [
          { required: true, message: "请输入手机号码", trigger: "blur" }
        ],
        tuXingMa: [
          { required: true, message: "请输入图形码", trigger: "blur" }
        ],
        yzCode: [{ required: true, message: "请输入验证码", trigger: "blur" }]
      },
      accountPassword:{  //账号密码表单
         password:'',   //新密码
         passwordAgain:''  //新密码再次输入
      },
      accountPasswordRules:{  //账号密码验证规则
    
        password: [
          { required: true, message: "请输入密码", trigger: "blur" }
        ],
        passwordAgain: [{ required: true, message: "请再次输入密码", trigger: "blur" }]
      }
    };
  },
  methods: {
    handleClick(tab, event) {
      window.console.log(tab, event);
    },
    //登录
    linkLogin() {
      this.$router.push({ path: "/" });
    },
    //身份验证下一步
    identityStep() {
      //验证TODO
      this.activeName = "second";
    },
    // 密码重置确定
    passwordResetComfirm() {
      //验证TODO
      this.activeName = "third";
    }
  },
  /**
   * 组件实例刚被创建，组件属性计算之前，如data属性等
   */
  beforeCreate() {},
  /**
   * 组件实例创建完成，属性已绑定，但DOM还未生成，el属性还不存在
   */
  created() {},
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
.register {
  width: 100%;
  background-color: #fff;
}
.register-wrap {
  width: 1200px;
  margin: 0 auto;
  padding-top: 57px;
}
.register-wrap /deep/ .el-button--danger {
  background-color: #df3737;
  color: #fffefe;
}
.register-wrap /deep/ .el-button--danger:hover {
  background-color: rgba(223, 55, 55, 0.8);
  color: #fffefe;
}
.register-wrap /deep/ .el-button--danger:active {
  background-color: rgba(223, 55, 55, 1);
}
.header {
  display: flex;
  align-items: center;
  margin-bottom: 60px;
  >img{
    width: 300px;
  }
  span {
    color: #666666;
    font-size: 20px;
    margin-left: 40px;
    height: 50px;
    line-height: 50px;
    padding-left: 33px;
    border-left: 1px solid #cfcfcf;
  }
}

.el-tabs /deep/ .el-tabs__nav-scroll {
  padding: 0 400px;
}
.el-tabs /deep/ .el-tabs__item {
  font-size: 20px;
  color: #666666;
  width: 164px;
  text-align: center;
  height: inherit;
  line-height: inherit;
  padding-bottom: 16px;
  outline: none;
}
.el-tabs /deep/ .el-tabs__item.is-active {
  color: #df3737;
}
.el-tabs /deep/ .el-tabs__active-bar {
  background-color: #df3737;
}
.el-tabs /deep/ .el-tabs__nav-wrap::after {
  background-color: #cfcfcf;
}
.same-layout {
  width: 394px;
  margin: 0 auto;
  min-height: 200px;
  margin-top: 80px;
}
.step-wrap {
  display: flex;
  align-items: flex-end;
  margin-top: 50px;
}
.step-wrap > .left {
  flex: 1;
  font-size: 16px;
  color: #999999;
  .el-button {
    color: #df3737;
  }
}
.step-wrap > .right {
  .el-button {
    flex: 0 0 196px;
    width: 196px;
    height: 54px;
    font-size: 20px;
  }
}
.same-layout .tip {
  text-align: center;
  span {
    color: #df3737;
    font-size: 20px;
  }
  .down {
    .el-button {
      width: 196px;
      height: 54px;
      margin-top: 44px;
      font-size: 20px;
    }
  }
  .middle {
    span {
      display: block;
      margin-top: 18px;
      font-size: 16px;
      color: #666666;
    }
  }
}
//elementui 样式修改
.el-form /deep/ .el-form-item {
  margin-bottom: 40px;
}
.el-input /deep/ .el-input__inner {
  border: none;
  border-radius: 0;
  font-size: 16px;
  padding-left: 40px;
  padding-bottom: 16px;
  border-bottom: 1px solid #cfcfcf;
}
.tuXingMa /deep/ .el-input__inner {
  padding-right: 146px;
}
.el-input /deep/ .el-input__prefix {
  left: -2px;
}
.el-input /deep/ .iconfont {
  font-size: 24px;
}
.el-form-item.is-error /deep/ .el-input__inner {
  border-bottom-color: #f56c6c;
}
.el-form-item /deep/ .el-form-item__content {
  position: relative;
  .char-code {
    position: absolute;
    top: -30px;
    right: 0;
    z-index: 10;
    width: 146px;
    height: 63px;
    line-height: 63px;
    border-radius: 4px;
    color: #999999;
    text-align: center;
    font-size: 24px;
    background-color: #f3f3f3;
  }
  .btn-yz-code {
    position: absolute;
    top: 0;
    right: 0;
    z-index: 10;
    width: 146px;
    height: 38px;
    font-size: 16px;
  }
  .icon-tz-wenhao{
      position: absolute;
      top:0;
      right: 0;
      z-index: 10;
      
  }
}
</style>