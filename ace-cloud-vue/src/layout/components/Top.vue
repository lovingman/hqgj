<template>
    <div class="top">
        <div class="undertone">
            <img src="../../assets/img/icon_2.jpg" alt="">
            <span>常德市智慧平安平台</span>
            <!--<div class="region">【<span>常德市</span>】</div>-->
            <div class="head">
                <div class="line_1">
                    <!--<el-button type="text" icon="iconfont icon-calendar" style="font-size: 13px;color: white;">平台概况</el-button>-->
                    <!--<el-button type="text" icon="iconfont icon-daqia1" style="font-size: 13px;color: white;">打卡</el-button>-->
                    <!--<el-button type="text" icon="iconfont icon-menu-home-normal" style="font-size: 13px;color: white;">首页</el-button>-->
                    <!--<el-button type="text" icon="iconfont icon-xiaoxi" style="font-size: 13px;color: white;">消息</el-button>-->
                    <!--<el-button type="text" icon="iconfont icon-Settings" style="font-size: 13px;color: white;">设置</el-button>-->
                    <el-button type="text" icon="iconfont icon-tuichu" @click="logout" style="font-size: 13px;color: white;">退出</el-button>
                </div>
                <!--<div class="line_2">-->
                    <!--<img src="../../assets/img/Apartment-Building.png" alt="" style="width: 20px;height: 20px;margin-top:10px;float:left;">-->
                    <!--<el-button type="text" style="font-size: 16px;color: white;margin-right: 15px;float:left;">数字网格系统</el-button>-->
                    <!--<img src="../../assets/img/Documents.png" alt="" style="width: 20px;height: 20px;margin-top:10px;float:left;">-->
                    <!--<el-button type="text" style="font-size: 16px;color: white;margin-right: 15px;float:left;">专项工作系统</el-button>-->
                    <!--<img src="../../assets/img/Suitcase.png" alt="" style="width: 20px;height: 20px;margin-top:10px;float:left;">-->
                    <!--<el-button type="text" style="font-size: 16px;color: white;float:left;">日常管理系统</el-button>-->
                <!--</div>-->
            </div>
        </div>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex'
    export default {
        data() {
            return {
                name: 'top'
            }
        },
        computed: {
            ...mapGetters([
                'sidebar',
                // 'name',
                'device'
            ])
        },
        methods: {
            async logout() {
                await this.$store.dispatch('user/logout');
                this.$router.push(`/login?redirect=${this.$route.fullPath}`)
            }
        }
    }
</script>

<style scoped>
    .top{
        width: 100%;
        height: 76px;
        display:flex;
    }
    .undertone{
        display: inline-flex;
        width:100%;
        height: 76px;
        background: url(../../assets/img/icon_1.jpg);

    }

    .undertone img{
        margin-top: 12px;
        margin-left: 40px;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        overflow: hidden;
        align-items: center;
    }

    .undertone span{
        margin-top: 12px;
        margin-left: 10px;
        font-size: 35px;
        color: #ffffff;
        letter-spacing:6px;

    }

    .region{
        margin-left: 80px;
        margin-top: 30px;
        color: #ffffff;
    }

    .region span{
        margin-left: 0px;
        letter-spacing:0px;
        margin-top: 0px;
        font-size: 16px;
    }
    .head{
        text-align: right;
        margin-left: auto;
        margin-right: 10px;
    }
    .head a{
        color: #ffffff;
        text-decoration: none;
    }
    .line_2 img{
        margin: 0;
    }
</style>
