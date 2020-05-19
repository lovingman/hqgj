<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <keep-alive :include="cachedViews">
        <router-view :key="key" v-if="hackReset" @refreshSubcomponents="refreshSubcomponents" />
      </keep-alive>
    </transition>
  </section>
</template>

<script>
export default {
  name: "AppMain",
  data() {
    return {
      hackReset: true //默认显示
    };
  },
  methods: {
    // 重新渲染子组件
    refreshSubcomponents() {
      this.hackReset = false;
      this.$nextTick(() => {
        this.hackReset = true;
      });
    }
  },
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews;
    },
    key() {
      return this.$route.path;
    }
  }
};
</script>

<style lang="scss" scoped>
.app-main {
  /* 50= navbar  50  */
  height: calc(100% - 104px);
  width: 100%;
  position: relative;
  overflow-y: scroll;
  background-color: #f5f7fa;
  padding-top: 1rem;
  padding-left: 1rem;
  padding-right: 1rem;
}

.fixed-header + .app-main {
  padding-top: 4.1rem;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    min-height: calc(100vh - 0rem);
  }

  .fixed-header + .app-main {
    padding-top: 7.29rem;
  }
}

</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
