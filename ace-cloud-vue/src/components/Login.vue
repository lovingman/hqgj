<template>

  <div class="login">
    <div class="loginHeader">
      <img src="../assets/images/platform-logo@2x.png">
    </div>
    <div class="loginBg">
      <div class="loginMain">
        <div class="rightBox">
          <!-- 二维码切换 -->
          <div class="ewm" @click="handleSwtich">
            <i v-show="ewmImg">
              <img src="../assets/images/ewm1.png">
            </i>
            <i v-show="!ewmImg">
              <img src="../assets/images/ewm2.png">
            </i>
          </div>
          <!-- 账号短信登陆方式 -->
          <div v-show="isShow">
            <ul>
              <li :class="{active:curr==0}" @click="curr=0">
                <span>账号登录</span>
              </li>
              <li :class="{active:curr==1}" @click="curr=1">
                <span>短信登录</span>
              </li>
            </ul>
            <div class="loginForm">
              <div class="accountNumber box" v-show="curr==0">
                <el-form
                  class="demo-ruleForm login-container"
                  :model="ruleForm"
                  :rules="rules"
                  ref="ruleForm"
                  label-position="left"
                  label-width="0px"
                >
                  <el-form-item class="demo" prop="account">
                    <el-input type="text" v-model="ruleForm.account" clearable placeholder="用户名">
                      <i slot="prefix" class="iconfont icon-tz-yonghu"></i>
                    </el-input>
                  </el-form-item>
                  <el-form-item class="demo" prop="checkPass">
                    <el-input
                      type="password"
                      v-model="ruleForm.checkPass"
                      clearable
                      placeholder="密 码"
                      @keyup.enter.native="accountSubimt('ruleForm')"
                    >
                      <i slot="prefix" class="iconfont icon-tz-mima"></i>
                    </el-input>
                  </el-form-item>
                  <el-button
                    class="submit"
                    type="primary"
                    style="width:100%;"
                    @click="accountSubimt('ruleForm')"
                  >登&nbsp;&nbsp;录</el-button>
                </el-form>
                <div class="footerTest">
                  <router-link class="fll" tag="span" to="/FindPassword">忘记密码</router-link>
                  <router-link class="frr" tag="span" to="/Register">立即注册</router-link>
                </div>
              </div>
              <div class="message box" v-show="curr==1">
                <el-form
                  class="message-ruleForm login-container"
                  :model="ruleFormMessgae"
                  :rules="rulesMessage"
                  ref="ruleFormMessgae"
                  label-position="left"
                  label-width="0px"
                >
                  <el-form-item class="demo" prop="phone">
                    <el-input
                      type="text"
                      v-model="ruleFormMessgae.phone"
                      clearable
                      placeholder="请输入手机号码"
                    >
                      <i slot="prefix" class="iconfont icon-tz-shouji"></i>
                    </el-input>
                  </el-form-item>
                  <el-form-item class="demo" prop="verificationCode">
                    <el-input
                      class="codeInput"
                      type="text"
                      v-model="ruleFormMessgae.verificationCode"
                      placeholder="请输入验证码"
                    >
                      <i slot="prefix" class="iconfont icon-tz-yanzhengma"></i>
                    </el-input>
                    <div class="getCode" @click="handleCode">获取验证码</div>
                  </el-form-item>
                  <el-button
                    class="submit"
                    type="primary"
                    style="width:100%;"
                    @click="messageSubmit('ruleFormMessgae')"
                  >登&nbsp;&nbsp;录</el-button>
                </el-form>
                <div class="footerTest">
                  <a href="#" class="frr">立即注册</a>
                </div>
              </div>
            </div>
          </div>
          <!-- 扫描二维码登陆 -->
          <div v-show="!isShow">
            <div class="ewmContent">
              <div class="title">
                <h3>扫描二维码登录</h3>
              </div>
              <div class="ewmCode">
                <img src="../assets/images/ewmcode.png">
              </div>
              <div class="ewmFoter">
                没有账号,
                <router-link to="/Register">立即注册</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="loginFoter">
    <Footer></Footer>
    </div>
  </div>
</template>

<script>
import Footer from "./common/Footer";
export default {
  name: "Login",
  components: {
    Footer
  },
  data() {
    return {
      logining: false,
      isShow: true,
      ewmImg: true,
      curr: 0,
      ruleForm: {
        //账号验证
        account: "",
        checkPass: ""
      },
      rules: {
        //账号验证条件
        account: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        checkPass: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      ruleFormMessgae: {
        //短信验证
        phone: "",
        verificationCode: ""
      },
      rulesMessage: {
        //短信验证条件
        phone: [{ required: true, message: "请输入手机号码", trigger: "blur" }],
        verificationCode: [
          { required: true, message: "请输入验证码", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    accountSubimt(formName) {
      //手机验证判断是否为空
      this.$refs[formName].validate(valid => {
        if (valid) {
          //如果验证内容通过进入首页
          this.$http
            .post(
              this.globalData.api + "/security/login",
              {
                username: this.ruleForm.account,
                password: this.ruleForm.checkPass
              },
              { emulateJSON: true }
            )
            .then(res => {
              // window.console.log(res);
              if (res.body.status == 1) {
                this.globalData.isLogin = true; //设置登录状态
                this.$router.push({ path: "/Index" });
              } else {
                window.console.log(res);
                this.$notify.error({
                  title: "错误",
                  message: res.body.message
                });
              }
            })
            .catch(res => {
              //捕捉异常
              window.console.log(res);
            });
        } else {
          return false;
        }
      });
    },
    messageSubmit(formName) {
      //短信验证判断是否为空
      this.$refs[formName].validate(valid => {
        if (valid) {
          //如果验证内容通过进入首页
          // this.$router.push({ path: "/Index" });
        } else {
          return false;
        }
      });
    },
    //二维码点击切换
    handleSwtich() {
      this.ewmImg = !this.ewmImg;
      this.isShow = !this.isShow;
    },
    //点击获取验证码
    handleCode: function() {
      //点击获取验证码事件
      alert(1);
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

<style scoped lang="less">
.login{
  height: 100%;
  position: relative;
  overflow: hidden;
}
.loginHeader{
  position: absolute;
  left: 0;
  top: 0;
}
.loginHeader img {
  padding: 20px 75px;
  width: 370px;
}
.loginBg {
  margin-top: 120px;
  width: 100%;
  float: left;
  height:calc(~"100% - 230px");
  background-image: url(./../assets/images/loginbg.png);
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  .loginMain {
    width: 1200px;
    margin: 0 auto;
  }
  .rightBox {
    width: 450px;
    height: 554px;
    background-color: #fff;
    border-radius: 10px;
    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    float: right;
    margin: 63px 0 0 0;
    padding: 76px 64px 0 64px;
    position: relative;
    ul li {
      width: 50%;
      float: left;
      text-align: center;
      font-size: 20px;
      line-height: 36px;
    }
    ul li span {
      padding: 0 14px;
      display: inline-block;
      cursor: pointer;
    }
    ul li.active span {
      color: #f00;
      border-bottom: 2px solid #f00;
    }
    .loginForm {
      width: 100%;
      float: left;
      padding-top: 60px;
    }
    .ewm {
      position: absolute;
      right: 14px;
      top: 14px;
      cursor: pointer;
    }
  }
}
.el-button--primary /deep/ {
  background-color: #df3737;
  height: 44px;
  border-color: #df3737;
  font-size: 20px;
}
.el-button--primary:hover /deep/ {
  background-color: #ed0101;
  border-color: #ed0101;
}
.el-input--medium /deep/ .el-input__inner {
  height: 40px;
  line-height: 40px;
  border-radius: 0;
  border: none;
  border-bottom: 1px solid #cfcace;
  padding-left: 40px;
  font-size: 16px;
}
.el-form-item /deep/ {
  margin-bottom: 40px;
}
.el-form-item.is-error /deep/ .el-input__inner {
  border-bottom-color: #f56c6c;
}
.el-form-item.is-success /deep/ .el-input__inner {
  border-bottom-color: #67c23a;
}
.el-input /deep/ .el-input__prefix {
  line-height: 40px;
}
.codeInput /deep/ .el-input__inner {
  padding-right: 150px;
}
.getCode {
  position: absolute;
  right: 0;
  top: 0;
  width: 120px;
  height: 32px;
  background-color: #df3737;
  color: #fff;
  border-radius: 4px;
  text-align: center;
  line-height: 32px;
  cursor: pointer;
}
.getCode:hover {
  background-color: #ed0101;
}
.footerTest {
  padding-top: 66px;
  .fll {
    font-size: 16px;
    color: #999999;
    text-align: left;
    text-decoration: none;
    cursor: pointer;
  }
  .frr {
    color: #df3737;
    cursor: pointer;
    text-decoration: none;
  }
}
.el-input {
  width: 100%;
  border: none;
}
.ewmContent {
  text-align: center;
  .title h3 {
    display: inline-block;
    font-size: 20px;
    border-bottom: 2px solid #df3737;
    color: #df3737;
    font-weight: normal;
    padding-bottom: 6px;
    margin: 0;
  }
  .ewmCode {
    margin-top: 45px;
    width: 250px;
    height: 250px;
    display: inline-block;
    overflow: hidden;
  }
  .ewmFoter {
    padding-top: 45px;
    text-align: right;
    font-size: 16px;
    color: #999;
    a {
      color: #df3737;
      text-decoration: none;
    }
  }
}
  .loginFoter{
    position: absolute;
    bottom: 10px;
    width: 100%;
    text-align: center;
  }
</style>
