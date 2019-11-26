<template>
    <div class="header">
        <div class="pajs">众创平安</div>
        <!--<div class="title">-->
            <!--<p>当前辖区:</p>-->
            <!--<span>常德市</span>-->
            <!--<a href="#"></a>-->
        <!--</div>-->
    </div>
</template>

<script>
    export default {
        data() {
            return {
                collapse: false,
            }
        },
    }
</script>

<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 50px;
        font-size: 22px;
        color: #000000;
    }

    .header .pajs {
        text-align: center;
        float: left;
        font-weight: bold;
        font-size: 25px;
        width: 250px;
        line-height: 50px;
    }

    .title {
        font-size: 20px;
        display: inline-flex;
    }

    .title p {
        margin-top: 0;
        line-height: 50px;
    }

    .title span {
        text-align: center;
        line-height: 50px;
        font-weight: bold;
        width: 100px;
    }

    .title a {
        width: 0;
        height: 0;
        border-left: 7px solid transparent;
        border-right: 7px solid transparent;
        border-top: 7px solid #44495a;
        margin-top: 23px;
    }

    .user-avator img {
        display: block;
        width: 40px;
        height: 40px;
        border-radius: 50%;
    }

</style>
