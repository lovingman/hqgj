<template>
    <div :class="classObj" class="app-wrapper">
        <div @click="handleClickOutside" class="drawer-bg" v-if="device==='mobile'&&sidebar.opened"/>
        <div :class="{title:isCollapse}" style=" background-color:rgb(0, 65, 136);display: block;position: absolute;transition: width 0.28s;width: 240px;height: 45px;z-index: 1001;overflow: hidden;">
            <!--<el-row style="margin-left: 20px;">-->
                <!--<el-col :span="4" style="margin-top: 5px">-->

            <div class="undertone">
                <img alt="" src="@/assets/images/hqgjlogo.png" style="width: 33px;height: 33px">
                <span style=" color: white">慧企管家服务平台</span>
            </div>

                <!--</el-col>-->
                <!--<el-col :span="14">-->
                    <!--<div class="title-nm">-->
                       <!---->
                    <!--</div>-->
                <!--</el-col>-->
            <!--</el-row>-->
        </div>
        <sidebar class="sidebar-container"/>
        <div :class="{hasTagsView:needTagsView}" class="main-container">
            <div class="navbar">
                <navbar/>
                <tags-view v-if="needTagsView"/>
            </div>
            <app-main/>
            <right-panel v-if="showSettings">
                <settings/>
            </right-panel>
        </div>
    </div>
</template>

<script>
    import RightPanel from "@/components/RightPanel";
    import {AppMain, Navbar, Settings, Sidebar, TagsView} from "./components";
    import ResizeMixin from "./mixin/ResizeHandler";
    import {mapState} from "vuex";

    export default {
        name: "Layout",
        components: {
            AppMain,
            Navbar,
            RightPanel,
            Settings,
            Sidebar,
            TagsView
        },
        mixins: [ResizeMixin],
        computed: {
            ...mapState({
                sidebar: state => state.app.sidebar,
                device: state => state.app.device,
                showSettings: state => state.settings.showSettings,
                needTagsView: state => state.settings.tagsView,
                fixedHeader: state => state.settings.fixedHeader
            }),
            isCollapse() {
                return !this.sidebar.opened
            },
            classObj() {
                return {
                    hideSidebar: !this.sidebar.opened,
                    openSidebar: this.sidebar.opened,
                    withoutAnimation: this.sidebar.withoutAnimation,
                    mobile: this.device === "mobile"
                };
            }
        },
        methods: {
            handleClickOutside() {
                this.$store.dispatch("app/closeSideBar", {withoutAnimation: false});
            }
        }
    };
</script>

<style lang="scss" scoped>
    @import "~@/styles/mixin.scss";
    @import "~@/styles/variables.scss";

    .app-wrapper {
        @include clearfix;
        width: 100%;
        height: 100%;
        overflow: hidden;
        position: relative;

        &.mobile.openSidebar {
            position: fixed;
            top: 0;
        }
    }

    .app-main {
        height: calc(100% - 100px);
        overflow-x: hidden;
        overflow-y: auto;

        /deep/ .el-pagination {
            margin-top: 20px;
        }
    }

    .title {
        width: 54px!important;
        .undertone span{
            display: none;

        }

    }
    .undertone{
        display: inline-flex;
        line-height: 45px;
    }
    .undertone img{
        margin-left: 14px;
        margin-top: 8px;
    }

    .undertone span{
        color: #ffffff;

    }

    .drawer-bg {
        background: #000;
        opacity: 0.3;
        width: 100%;
        top: 0;
        height: 100%;
        position: absolute;
        z-index: 999;
    }

    .fixed-header {
        position: fixed;
        top: 0;
        right: 0;
        z-index: 9;
        width: calc(100% - #{$sideBarWidth});
        transition: width 0.28s;
    }

    .hideSidebar .fixed-header {
        width: calc(100% - 54px);
    }

    .mobile .fixed-header {
        width: 100%;
    }
</style>
