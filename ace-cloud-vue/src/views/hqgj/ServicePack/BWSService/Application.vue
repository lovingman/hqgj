<template>
    <div class="container">
        <div class="button">
            <el-button @click="backLook" style="margin-bottom: 20px">退出预览</el-button>
            <!--<el-button @click="getPdf" style="margin-bottom: 20px">下载</el-button>-->
            <el-button style="float: right;margin-bottom: 20px" v-print="'#Apply'">打印</el-button>
        </div>
        <div id="Apply">
            <div style="height: 30px"><br></div>
            <div class="table">
                <table cellpadding="0" cellspacing="0" style="border-collapse:collapse; float:left; margin:0pt 9pt">
                    <tr style="height:63.45pt">
                        <td colspan="12"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:18pt; font-weight:bold"> </span>
                                <span style="font-family:宋体; font-size:18pt; font-weight:bold">武陵区中小企业公共服务平台</span></p>
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:18pt; font-weight:bold">“创业服务一条龙”</span>
                                <span style="font-family:宋体; font-size:18pt; font-weight:bold">免费服务申请表</span></p>
                        </td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:36.55pt">
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">申请人：</span></p></td>
                        <td colspan="6"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:justify"><span
                                    style="font-family:宋体; font-size:10.5pt"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF">{{list.applyPersonName}}</span>        /身份证号： <span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF">{{list.idCard}}</span></span>
                            </p></td>
                        <td colspan="4"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:justify"><span
                                    style="font-family:宋体; font-size:10.5pt">申请日期： </span>
                                <span style="font-family:宋体; font-size:10.5pt">   </span>
                                <span style="font-family:宋体; font-size:10.5pt;color:#1890FF"> {{list.createDate}}</span>
                            </p>
                            <p style="margin:0pt; text-align:justify"><span
                                    style="font-family:宋体; font-size:10.5pt">编    号：</span><span
                                    style="font-family:宋体; font-size:10.5pt">NO：</span></p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:32.85pt">
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">申请免费</span></p>
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">服务项目</span></p></td>
                        <td colspan="10"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <div style="margin:0pt; text-align:justify"
                            >
                                <el-checkbox-group v-model="option">
                                    <el-checkbox
                                            v-for="item in dict"
                                            :label="item.code"
                                            :key="item.name">
                                        {{item.name}}
                                    </el-checkbox>
                                    <!--<el-checkbox label="1">工商注册代办服务（含公章一套）</el-checkbox>-->
                                    <!--<el-checkbox label="2">银行基本户开户</el-checkbox>-->
                                    <!--<el-checkbox label="3">银行开户费、小额账户管理费</el-checkbox>-->
                                </el-checkbox-group>
                            </div>
                        </td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:25.6pt">
                        <td colspan="12"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">一、</span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">工商</span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">注册</span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">免费代办</span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">服务基本信息确认单</span></p>
                        </td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:22.95pt">
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">公司名称</span></p></td>
                        <td colspan="4"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF;float: left">{{list.companyName}}</span>
                            </p>
                        </td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">备用名</span></p></td>
                        <td colspan="3"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:justify"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF">{{list.readyName}}</span>
                            </p>
                        </td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">注册资金</span>
                            </p>
                        </td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF">{{list.regBonus}}</span>
                            </p>
                        </td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:32.95pt">
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">法人代表</span></p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center" v-for="item in list.businessDetailList"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF"
                                    v-if="item.type == 1">{{item.name}}</span></p>
                        </td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">联系电话</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center" v-for="item in list.businessDetailList"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF"
                                    v-if="item.type == 1">{{item.mobile}}</span></p>
                        </td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">监</span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">  </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">事</span></p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"
                               v-for="item in list.businessDetailList"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF"
                                    v-if="item.type == 2">{{item.name}}</span></p>
                        </td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">联系电话</span></p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"
                               v-for="item in list.businessDetailList"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF"
                                    v-if="item.type == 2">{{item.mobile}}</span></p>
                        </td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:32.85pt">
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">股东及股份比例</span></p>
                        </td>
                        <td colspan="4"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"
                               v-for="item in list.businessDetailList"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF"
                                    v-if="item.type == 3">{{item.name}}:{{item.sharesProportion}}</span></p>
                        </td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">特殊说明</span></p></td>
                        <td colspan="5"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"
                               v-for="item in list.businessAppendList"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold;color:#1890FF"
                                    v-if="item.type == 5">{{item.content}}</span></p>
                        </td>
                        <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:middle"><p
                                style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                style="font-family:'Times New Roman'; font-size:10.5pt">&#xa0;</span></p></td>
                    </tr>
                    <tr style="height:41.05pt">
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">经营范围</span></p></td>
                        <td colspan="10"
                            style="border-bottom-color:#000000;border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt;color:#1890FF;float: left">{{list.manageExtent}}</span>
                            </p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:32.85pt">
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">注册地址</span></p></td>
                        <td colspan="10"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt">
                            <span
                                    style="font-family:宋体; font-size:10.5pt;color:#1890FF">{{list.companyAddress}}</span>
                            </p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:25.6pt">
                        <td colspan="12"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:justify; text-indent:20.6pt"><span
                                    style="font-family:宋体; font-size:10.5pt">以上内容最终以工商部门核定通过为准。 </span><span
                                    style="font-family:宋体; font-size:10.5pt">           申请人签名确认：</span></p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:27.3pt">
                        <td colspan="12"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">二、确定其他创业意向</span></p>
                        </td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:48.55pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">确定培训</span></p>
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">意向</span></p></td>
                        <td colspan="11"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <div style="margin:0pt; orphans:0; text-align:justify; widows:0"
                            >
                                <el-checkbox-group v-model="option2">
                                    <el-checkbox
                                            v-for="item in dict2"
                                            :label="item.code"
                                            :key="item.name">
                                        {{item.name}}
                                    </el-checkbox>
                                    <!--<el-checkbox label="1">是否愿意参加创业培训</el-checkbox>-->
                                    <!--<el-checkbox label="2">是否愿意加入创业孵化基地</el-checkbox>-->
                                </el-checkbox-group>
                            </div>
                            <p style="margin:0pt; orphans:0; text-align:justify; text-indent:315pt; widows:0">
                                <span style="font-family:宋体; font-size:10.5pt">    </span>
                                <span style="font-family:宋体; font-size:10.5pt">申请人签名确认：</span>
                            </p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:28.65pt">
                        <td colspan="12"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">三、银行开户费、小额账户管理费免费确认单</span>
                            </p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:48.55pt">
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">选择银行</span></p></td>
                        <td colspan="10"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:justify; text-indent:10.5pt; widows:0"><span
                                    style="font-family:宋体; font-size:10.5pt">银行开户费、小额账户管理费免费</span><span
                                    style="font-family:宋体; font-size:10.5pt">服务，暂限以下合作银行：</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
                                <el-checkbox-group v-model="option3">
                                    <el-checkbox
                                            v-for="item in dict3"
                                            :label="item.code"
                                            :key="item.name">
                                        {{item.name}}
                                    </el-checkbox>
                                    <!--<el-checkbox label="1">兴业银行各分行</el-checkbox>-->
                                    <!--<el-checkbox label="2">交通银行德景园分行</el-checkbox>-->
                                    <!--<el-checkbox label="3">建设银行紫陵路分行</el-checkbox>-->
                                    <!--<el-checkbox label="4">长沙银行柳叶大道总行</el-checkbox>-->
                                </el-checkbox-group>
                                <!--<span-->
                                <!--style="font-family:宋体; font-size:10.5pt">□兴业银行各分行  □交通银行德景园分行  □建设银行紫陵路分行 □ 长沙银行柳叶大道总行 </span>-->
                            </p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal"> </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal">                                            </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal"> </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal">                  </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal">申请人签名确认：</span></p>
                        </td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:20.95pt">
                        <td colspan="12"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">四、</span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">代理记账（收费项目）推荐服务确认单</span>
                            </p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:64.25pt">
                        <td colspan="3"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">是否选择</span></p>
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">代理记账服务</span></p>
                        </td>
                        <td colspan="9"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:bottom">
                            <p style="margin:0pt">
                                <el-checkbox-group v-model="option4">
                                    <el-checkbox
                                            v-for="item in dict4"
                                            :label="item.code"
                                            :key="item.name">
                                        {{item.name}}
                                    </el-checkbox>
                                    <!--<el-checkbox label="1">选择武陵区中小企业公共服务平台推荐的代理记账机构，合作条款及收费详见合同；</el-checkbox>-->
                                    <!--<el-checkbox label="2">有关会计及报税工作自行负责，如产生税务申报逾期等相关责任自行承担。</el-checkbox>-->
                                </el-checkbox-group>
                            </p>
                            <p style="margin:0pt; text-indent:241.5pt"><span
                                    style="font-family:宋体; font-size:10.5pt">             </span><span
                                    style="font-family:宋体; font-size:10.5pt">申请人签名确认：</span></p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:56.8pt">
                        <td colspan="3"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">公共服务</span></p>
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">平台注册</span></p></td>
                        <td colspan="9"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:justify"><span
                                    style="font-family:宋体; font-size:10.5pt">委托服务机构开展公共服务平台注册：</span><span
                                    style="font-family:宋体; font-size:10.5pt">   </span></p>
                            <p style="margin:0pt; text-align:justify; text-indent:21pt"><span
                                    style="font-family:宋体; font-size:10.5pt">注册号：</span><span
                                    style="font-family:宋体; font-size:10.5pt">                 密码：        （企业登陆www.             后自行修改密码）</span>
                            </p>
                            <p style="margin:0pt; text-align:justify; text-indent:241.5pt"><span
                                    style="font-family:宋体; font-size:10.5pt">             </span><span
                                    style="font-family:宋体; font-size:10.5pt">申请人签名确认</span><span
                                    style="font-family:宋体; font-size:10.5pt">：</span></p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:48.55pt">
                        <td colspan="3"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">服务机构与</span></p>
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">经办人签章</span></p></td>
                        <td colspan="9"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:justify"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
                            <p style="margin:0pt; text-align:justify"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
                            <p style="margin:0pt; text-align:justify"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">                                             </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal"> </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal">      </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal"> </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal">      </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal">年    月    日</span>
                            </p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:49.05pt">
                        <td colspan="3"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt; text-align:center"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:bold">武陵区中小企业公共服务平台</span>
                            </p></td>
                        <td colspan="9"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                            <p style="margin:0pt"><span style="font-family:宋体; font-size:10.5pt"> </span><span
                                    style="font-family:宋体; font-size:10.5pt">                                                           </span>
                            </p>
                            <p style="margin:0pt"><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal">                                                           </span><span
                                    style="font-family:宋体; font-size:10.5pt; font-weight:normal">年    月    日</span>
                            </p></td>
                        <td style="vertical-align:top"></td>
                    </tr>
                    <tr style="height:0pt">
                        <td style="width:66.9pt; border:none"></td>
                        <td style="width:5.25pt; border:none"></td>
                        <td style="width:20.35pt; border:none"></td>
                        <td style="width:37.15pt; border:none"></td>
                        <td style="width:52.05pt; border:none"></td>
                        <td style="width:51.35pt; border:none"></td>
                        <td style="width:52.1pt; border:none"></td>
                        <td style="width:43.5pt; border:none"></td>
                        <td style="width:6pt; border:none"></td>
                        <td style="width:61.85pt; border:none"></td>
                        <td style="width:58pt; border:none"></td>
                        <td style="width:95.2pt; border:none"></td>
                        <td style="width:11.3pt; border:none"></td>
                    </tr>
                </table>
                <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
                    <span style="font-family:'Times New Roman'; font-size:10.5pt">&#xa0;</span>
                </p>
            </div>
        </div>

    </div>
</template>

<script>
    // import previewInfo
    import html2canvas from 'html2canvas';
    import JsPDF from 'jspdf';
    import {previewInfo} from "@/api/hqgj/BWSService";
    import {getDict} from "@/api/sys";

    export default {
        name: "Application",
        data() {
            return {
                dict:[],
                dict2:[],
                dict3:[],
                dict4:[],
                list: {},
                option: [],
                option2: [],
                option3: [],
                option4: [],
                title: "申请表"
            };
        },
        created() {
            this.getdata();
            this.dictQuery();
        },
        methods: {
            getdata() {
                this.id = this.$route.query.id;
                previewInfo(this.id).then(response => {
                    this.list = response.data;
                    console.log(this.list.businessAppendList)
                    this.changeArr(this.list.businessAppendList);
                    // console.log(this.option4);
                    // console.log(this.option3);
                    // console.log(this.option2);
                    if(this.$route.query.name=='download'){
                        clearTimeout(this.timer); //清除延迟执行
                        this.timer = setTimeout(() => {
                            //设置延迟执行
                            this.getPdf();
                        }, 2000);
                    }
                })
            },
            //获取企业注册步骤数据
            dictQuery() {
                getDict("57")
                    .then(response => {
                        this.dict = response.data['57'];
                        console.log(this.dict);
                    })
                getDict("58")
                    .then(response => {
                        this.dict2 = response.data['58'];
                        console.log(this.dict1);
                    })
                getDict("59")
                    .then(response => {
                        this.dict3 = response.data['59'];
                    })
                getDict("60")
                    .then(response => {
                        this.dict4 = response.data['60'];
                    })
            },
            //服务字符串转换数组
            changeArr(data) {
                for (var i = 0; i < data.length; i++) {
                    if (this.list.businessAppendList[i].type == "1") {
                        this.option=data[i].option.split(',')
                    }
                    if (this.list.businessAppendList[i].type == "2") {
                        this.option2=data[i].option.split(',')
                    }
                    if (this.list.businessAppendList[i].type == "3") {
                        this.option3=data[i].option.split(',')
                    }
                    if (this.list.businessAppendList[i].type == "4") {
                        this.option4=data[i].option.split(',')
                    }
                }
            },
            getPdf() {
                var title = this.title;
                html2canvas(document.querySelector('#Apply'), {
                    allowTaint: true
                }).then(function (canvas) {
                        let contentWidth = canvas.width;
                        let contentHeight = canvas.height;
                        //一页pdf显示html页面生成的canvas高度;
                        let pageHeight = contentWidth / 592.28 * 841.89;
                        //未生成pdf的html页面高度
                        let leftHeight = contentHeight;
                        //页面偏移
                        let position = 0;
                        //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
                        let imgWidth = 595.28;
                        let imgHeight = 592.28 / contentWidth * contentHeight;
                        let pageData = canvas.toDataURL('image/jpeg', 1.0);
                        let PDF = new JsPDF('', 'pt', 'a4');
                        //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
                        //当内容未超过pdf一页显示的范围，无需分页
                        if (leftHeight < pageHeight) {
                            PDF.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight)
                        } else {
                            while (leftHeight > 0) {
                                PDF.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight);
                                leftHeight -= pageHeight;
                                position -= 841.89;
                                if (leftHeight > 0) {
                                    PDF.addPage()
                                }
                            }
                        }
                        PDF.save(title + '.pdf')
                    }
                )
            },
            backLook() {
                window.close();
            }
        }
    }
</script>

<style scoped>
    .container {
        width: 1000px;
        height: 1200px;
        padding: 20px;
        background-color: #fff;
        margin: 0 auto;
    }
</style>
