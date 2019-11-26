<template>
  <div class="platform-index">
    <div class="header-up">
      <div class="public-width flex">
        <img src="./../../assets/images/platform-logo@2x.png">
        <ul class="right link-ul">
          <li @click="linkLogin">登录/注册</li>
          <li>移动客户端</li>
          <li>设为主页</li>
          <li>加入收藏</li>
        </ul>
      </div>
    </div>
    <div class="header-nav">
      <div class="public-width">
        <template>
          <el-tabs
            v-model="activeName"
            type="card"
            :class="{noClick:noClickTabs}"
            @tab-click="handleClick"
          >
            <el-tab-pane label="首    页" name="first">
              <ul class="pt-ul">
                <li class="basic" @click="linkBasicFlatform">
                  <span>基础信息平台</span>
                  <img src="./../../assets/images/basic-info-pt@2x.png">
                </li>
                <li class="zhyw">
                  <span>综合业务平台</span>
                  <img src="./../../assets/images/zhyw-pt@2x.png">
                </li>
                <li class="rcpt">
                  <span>日常办公平台</span>
                  <img src="./../../assets/images/rcbg-pt@2x.png">
                </li>
                <li class="fxjc">
                  <span>分析决策平台</span>
                  <img src="./../../assets/images/fxjc-pt@2x.png">
                </li>
              </ul>
              <div class="dangxuan-banner">
                <img src="./../../assets/images/platform-dangxuan@2x.png">
              </div>
              <div class="footer">
                <p>
                  版权所有 中共常德市委统一战线工作部 备案序号：湘ICP备05001915号-1
                  <br>主办单位：中共常德市委统一战线工作部
                  <br>单位地址：湖南省常德市建设东路88号 办公电话：0736-7786306
                  <br>
                </p>
              </div>
            </el-tab-pane>
            <el-tab-pane label="资讯中心" name="second">资讯中心</el-tab-pane>
            <el-tab-pane label="专题专栏" name="third">专题专栏</el-tab-pane>
            <el-tab-pane label="理论园地" name="fourth">理论园地</el-tab-pane>
            <el-tab-pane label="统战文化" name="fifth">统战文化</el-tab-pane>
            <el-tab-pane label="互动交流" name="sixth">互动交流</el-tab-pane>
          </el-tabs>
        </template>
      </div>
    </div>
    <div class="main"></div>
  </div>
</template>

<script>
export default {
  name: "PlatformIndex", //平台首页
  data() {
    return {
      activeName: "first",
      noClickTabs: true // 是否可以点击选项卡
    };
  },
  methods: {
    // 选项卡点击事件
    handleClick(tab, event) {
      window.console.log(tab, event);
    },
    //链接基础信息平台
    linkBasicFlatform() {
      var isLogin = this.globalData.isLogin;
      if (isLogin) {
        this.$router.push({ path: "/Index" });
      } else {
				this.$router.push({ path: "/Login" });
        this.$message({
          message: "请先登录",
          type: "warning"
				});
					
      }
    },
    //链接登录注册
    linkLogin() {
      this.$router.push({ path: "/Login" });
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
<style  lang="less" scoped>
.el-header,
.el-container,
.el-aside,
.el-main,
.el-footer {
  padding: 0;
}
.platform-index {
  height: 100%;
  background-color: #ffffff;
}
.public-width {
  width: 1200px;
  margin: 0 auto;
}
.flex {
  display: flex;
  height: 100%;
  img {
    align-self: flex-end;
    width: 420px;
    // height: 97px;
    margin-bottom: 37px;
  }
  .right {
    flex: 1;
    display: inline-flex;
    justify-content: flex-end;
  }
}
.header-up {
  width: 100%;
  height: 160px;
  background: url("./../../assets/images/platform-header-bg@2x.png") no-repeat
    left center/cover;
}
.link-ul {
  padding-top: 21px;
  li {
    color: #514e4e;
    font-size: 16px;
    cursor: pointer;
  }
  li + li {
    margin-left: 33px;
  }
}
.header-nav {
  width: 100%;
  height: 60px;
  background-color: #df3737;
  /deep/ .el-tabs--card > .el-tabs__header {
    border-bottom: none;
  }
  /deep/ .el-tabs__item {
    color: #fff;
    height: 60px;
    line-height: 60px;
    text-align: center;
    min-width: 154px;
  }
  /deep/ .el-tabs--card > .el-tabs__header .el-tabs__nav {
    border: none;
  }
  /deep/ .el-tabs--card > .el-tabs__header .el-tabs__item.is-active {
    border-bottom: none;
    background: rgba(255, 255, 255, 0.2);
  }
  /deep/ .el-tabs--card > .el-tabs__header .el-tabs__item {
    border-bottom: none;
    border-left: none;
  }
  /deep/ .el-tabs__header {
    margin-bottom: 20px;
  }
}
.pt-ul {
  display: flex;
  align-items: center;
  li {
    width: 285px;
    height: 359px;
    display: flex;
    flex-flow: column;
    justify-content: flex-end;
    border-radius: 4px;
    padding: 0 9px 10px 0;
    cursor: pointer;
    span {
      display: block;
      margin-bottom: 39px;
      align-self: center;
      font-size: 36px;
      color: #ffffff;
      line-height: 40px;
    }
    img {
      width: 133px;
      height: 154px;
      align-self: flex-end;
    }
  }
  li + li {
    margin-left: 29px;
  }
  li.basic {
    background: linear-gradient(
      -45deg,
      rgba(223, 55, 55, 0.99),
      rgba(233, 118, 95, 0.99)
    );
  }
  li.zhyw {
    background: linear-gradient(
      -45deg,
      rgba(233, 87, 23, 0.99),
      rgba(233, 132, 51, 0.99)
    );
  }
  li.rcpt {
    background: linear-gradient(
      -45deg,
      rgba(92, 222, 230, 0.99),
      rgba(102, 166, 255, 0.99)
    );
  }
  li.fxjc {
    background: linear-gradient(
      -45deg,
      rgba(74, 78, 255, 1),
      rgba(77, 142, 251, 1)
    );
  }
}
.dangxuan-banner {
  margin-top: 20px;
  img {
    width: 1200px;
    height: 154px;
  }
}
.main {
  height: 746px;
  background: url("./../../assets/images/changcheng-bg@2x.png") no-repeat center
    center/cover;
}
.footer {
  margin-top: 53px;
  p {
    color: #666666;
    font-size: 14px;
    text-align: center;
    font-weight: 400;
    line-height: 24px;
  }
}
.noClick /deep/ .el-tabs__item {
  pointer-events: none;
  cursor: default;
}
</style>