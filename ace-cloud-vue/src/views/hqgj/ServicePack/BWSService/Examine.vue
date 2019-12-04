<template>
    <div class="container">
        <el-tabs @tab-click="handleClick" v-model="activeName">
            <el-tab-pane label="资料清单" name="1">
                <el-table
                        :data="list"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" type="selection" width="55"></el-table-column>
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="名称" prop="name" sortable='custom'>
                    </el-table-column>
                    <el-table-column label="提交时间" prop="creatdata" width="300">
                    </el-table-column>
                    <el-table-column label="附件数" prop="number" width="250">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button @click="" type="text">下载</el-button>
                            <span class="strightline">|</span>
                            <el-button @click="preview" type="text">预览</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-dropdown trigger="click" style="margin-top: 20px">
                    <el-button>
                        压缩包下载<i class="el-icon-arrow-down el-icon--right"></i>
                    </el-button>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>rar下载</el-dropdown-item>
                        <el-dropdown-item>zip下载</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </el-tab-pane>
            <el-tab-pane label="申请人信息" name="2">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">申请人信息</span>
                </div>
                <el-divider></el-divider>
                <el-form :rules="rules" class="demo-ruleForm" label-width="150px" ref="ruleForm">
                    <el-row class="elrow">
                        <el-col :span="8">
                            <el-form-item label="姓名:" prop="name">
                                <span>付小小</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="联系电话:" prop="name">
                                <span>18100000000</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="身份证号:" prop="name">
                                <span>431224199600556596</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row class="elrow">
                        <el-form-item label="申请日期:" prop="name">
                            <span>2019-11-12 10:22:20</span>
                        </el-form-item>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="基本信息" name="3">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">基本信息</span>
                    <el-button @click="" style="float: right;margin-right: 25px;padding-top: 1px" type="text">审核
                    </el-button>
                </div>
                <el-divider></el-divider>
                <el-form :rules="rules" class="demo-ruleForm" label-width="150px" ref="ruleForm">
                    <el-row class="elrow">
                        <el-col :span="12">
                            <el-form-item label="企业名称:" prop="name">
                                <span>湖南华彩伟业网络科技有限公司</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="备用名:" prop="name">
                                <span>湖南常德华彩伟业网络科技有限公司</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row class="elrow">
                        <el-col :span="12">
                            <el-form-item label="注册资金:" prop="name">
                                <span>1000万人民币</span>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="企业地址:" prop="name">
                                <span>常德市武陵区移动互联网创业园A02栋第三层303号</span>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row class="elrow">
                        <el-form-item label="经营范围:" prop="name">
                                <span>基础软件开发，支撑软件开发，应用软件开发，信息系统集成服务，信息技术咨询服务，信息处理和存储支持服务；架线工程服务，智能化安装工程服务；计算机、软件及辅助设备、电子产品（不含电子出版物）、灯具、五金产品、首饰、工艺美术品（不含文物、象牙及其制品）的销售。（以公司登记机关核准为准）（依法须经批准的项目，经相关部门批准后方可开展经营活动）
基础软件开发，支撑软件开发，应用软件开发，信息系统集成服务，信息技术咨询服务，信息处理和存储支持服务；架线工程服务，智能化安装工程服务；计算机、软件及辅助设备、电子产品（不含电子出版物）、灯具、五金产品、首饰、工艺美术品（不含文物、象牙及其制品）的销售。（以公司登记机关核准为准）（依法须经批准的项目，经相关部门批准后方可开展经营活动）</span>
                        </el-form-item>
                    </el-row>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="法人、监事" name="4">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">法人、监事</span>
                </div>
                <el-divider></el-divider>
                <el-table
                        :data="list2"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="姓名" prop="name" sortable='custom' width="120">
                    </el-table-column>
                    <el-table-column label="职位" prop="position" width="150">
                    </el-table-column>
                    <el-table-column label="联系电话" prop="number" width="150">
                    </el-table-column>
                    <el-table-column label="身份证附件" prop="file">
                        <!--<template slot-scope="scope">-->
                        <!--<viewer  :images="scope.list2.file">-->
                        <!--<img v-for="attach in scope.list2.file" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>-->
                        <!--</viewer>-->
                        <!--</template>-->
                    </el-table-column>
                    <el-table-column label="状态" prop="status" width="80">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                        <template slot-scope="scope">
                            <el-button @click="preview" type="text">审核</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="股东" name="5">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">股东</span>
                </div>
                <el-divider></el-divider>
                <el-table
                        :data="list2"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="股东姓名" prop="name" sortable='custom' width="120">
                    </el-table-column>
                    <el-table-column label="股份比例" prop="position" width="150">
                    </el-table-column>
                    <el-table-column label="身份证附件" prop="file">
                        <!--<template slot-scope="scope">-->
                        <!--<viewer  :images="scope.list2.file">-->
                        <!--<img v-for="attach in scope.list2.file" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>-->
                        <!--</viewer>-->
                        <!--</template>-->
                    </el-table-column>
                    <el-table-column label="状态" prop="status" width="80">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                        <template slot-scope="scope">
                            <el-button @click="preview" type="text">审核</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="margin-top: 20px">
                    <span style="font-weight:bold">特殊说明:</span>
                    <div>
                        <span style="font-size:small">无</span>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane label="财务" name="6">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">财务</span>
                </div>
                <el-divider></el-divider>
                <el-table
                        :data="list2"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="姓名" prop="name" sortable='custom' width="120">
                    </el-table-column>
                    <el-table-column label="联系电话" prop="position" width="150">
                    </el-table-column>
                    <el-table-column label="身份证附件" prop="file">
                        <!--<template slot-scope="scope">-->
                        <!--<viewer  :images="scope.list2.file">-->
                        <!--<img v-for="attach in scope.list2.file" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>-->
                        <!--</viewer>-->
                        <!--</template>-->
                    </el-table-column>
                    <el-table-column label="状态" prop="status" width="80">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                        <template slot-scope="scope">
                            <el-button @click="preview" type="text">审核</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="房屋证明" name="7">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">房屋证明</span>
                </div>
                <el-divider></el-divider>
                <el-table
                        :data="list2"
                        @selection-change="handleSelectionChange"
                        @sort-change="handleSort"
                        border
                        class="table"
                        max-height="475"
                        ref="multipleTable"
                        v-loading="loading">
                    <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
                    <el-table-column label="名称" prop="name" sortable='custom' width="250">
                    </el-table-column>
                    <el-table-column label="附件" prop="file">
                        <!--<template slot-scope="scope">-->
                        <!--<viewer  :images="scope.list2.file">-->
                        <!--<img v-for="attach in scope.list2.file" :src="attach.fileurl" :key="attach.fileurl" style="line-height: 48px" class="head_pic"/>-->
                        <!--</viewer>-->
                        <!--</template>-->
                    </el-table-column>
                    <el-table-column label="状态" prop="status" width="80">
                    </el-table-column>
                    <el-table-column align="right" fixed="right" header-align="center" label="操作" width="60">
                        <template slot-scope="scope">
                            <el-button @click="preview" type="text">审核</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="其他" name="8">
                <div style="border-left:thick solid #007cff;margin-left: 10px;margin-top: 9px">
                    <span style="margin-left: 20px;font-weight:bold;">其他</span>
                </div>
                <el-divider></el-divider>
                <el-form label-width="250px" ref="form">
                    <el-form-item label="申请免费服务项目:">
                        <span>工商注册代办服务（含公章一套）、银行基本户开户代办服务 、银行开户费、小额账户管理费</span>
                    </el-form-item>
                    <el-form-item label="确定培训意向:">
                        <span>是否愿意参加创业培训、是否愿意加入创业孵化基地</span>
                    </el-form-item>
                    <el-form-item label="开户银行:">
                        <span>兴业银行各分行</span>
                    </el-form-item>
                    <el-form-item label="是否选择代理记账服务:">
                        <span>选择武陵区中小企业公共服务平台推荐的代理记账机构，合作条款及收费详见合同；</span>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
        </el-tabs>
        <el-dialog :visible.sync="lookVisible" title="预览" width="60%">
            <div>
                <el-button style="margin-bottom: 20px">推出预览</el-button>
                    <table cellpadding="0" cellspacing="0" style="border-collapse:collapse; float:left; margin:0pt 9pt">
                        <tr style="height:63.45pt">
                            <td colspan="12"
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:18pt; font-weight:bold"> 
</span><span style="font-family:宋体; font-size:18pt; font-weight:bold">武陵区中小企业公共服务平台</span></p>
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
                                        style="font-family:宋体; font-size:10.5pt">         /身份证号：</span></p></td>
                            <td colspan="4"
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:justify"><span
                                        style="font-family:宋体; font-size:10.5pt">申请日期： </span><span
                                        style="font-family:宋体; font-size:10.5pt">   </span><span
                                        style="font-family:宋体; font-size:10.5pt"> 年   月   日</span></p>
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
                                <p style="margin:0pt; text-align:justify"><span
                                        style="font-family:宋体; font-size:10.5pt">□ 工商</span><span
                                        style="font-family:宋体; font-size:10.5pt">注册</span><span
                                        style="font-family:宋体; font-size:10.5pt">代办服务（含公章一套）□ 银行基本户开户</span><span
                                        style="font-family:宋体; font-size:10.5pt">代办服务</span><span
                                        style="font-family:宋体; font-size:10.5pt"> </span><span
                                        style="font-family:宋体; font-size:10.5pt">□ </span><span
                                        style="font-family:宋体; font-size:10.5pt">银行开户费、小额账户管理费</span></p></td>
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
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
                            </td>
                            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">备用名</span></p></td>
                            <td colspan="3"
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:justify"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
                            </td>
                            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">注册资金</span></p></td>
                            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
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
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
                            </td>
                            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">联系电话</span></p></td>
                            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
                            </td>
                            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">监</span><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">  </span><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">事</span></p></td>
                            <td colspan="2"
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
                            </td>
                            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">联系电话</span></p></td>
                            <td colspan="2"
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
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
                                <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
                            </td>
                            <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">特殊说明</span></p></td>
                            <td colspan="5"
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">&#xa0;</span></p>
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
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:bottom">
                                <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt">&#xa0;</span></p></td>
                            <td style="vertical-align:top"></td>
                        </tr>
                        <tr style="height:32.85pt">
                            <td colspan="2"
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt; text-align:center"><span
                                        style="font-family:宋体; font-size:10.5pt; font-weight:bold">注册地址</span></p></td>
                            <td colspan="10"
                                style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.4pt; padding-right:5.03pt; vertical-align:middle">
                                <p style="margin:0pt"><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">   </span><span
                                        style="font-family:宋体; font-size:10.5pt">县（市、区）</span><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">     </span><span
                                        style="font-family:宋体; font-size:10.5pt">乡镇（办事处）</span><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">     </span><span
                                        style="font-family:宋体; font-size:10.5pt">村（居委会、社区）</span><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">     </span><span
                                        style="font-family:宋体; font-size:10.5pt">组</span><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">     </span><span
                                        style="font-family:宋体; font-size:10.5pt">（街、道、路）</span><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">    </span><span
                                        style="font-family:宋体; font-size:10.5pt">号（</span><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">　　　　</span><span
                                        style="font-family:宋体; font-size:10.5pt">小区</span><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">   </span><span
                                        style="font-family:宋体; font-size:10.5pt">栋</span><span
                                        style="font-family:宋体; font-size:10.5pt; text-decoration:underline">     </span><span
                                        style="font-family:宋体; font-size:10.5pt">楼）。</span></p></td>
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
                                <p style="margin:0pt; orphans:0; text-align:justify; text-indent:315pt; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt">&#xa0;</span></p>
                                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt"> □</span><span
                                        style="font-family:宋体; font-size:10.5pt">是否愿意参加创业培训</span><span
                                        style="font-family:宋体; font-size:10.5pt">    　　</span><span
                                        style="font-family:宋体; font-size:10.5pt">　</span><span
                                        style="font-family:宋体; font-size:10.5pt">□</span><span
                                        style="font-family:宋体; font-size:10.5pt">是否愿意加入创业孵化基地</span><span
                                        style="font-family:宋体; font-size:10.5pt">                                        </span><span
                                        style="font-family:宋体; font-size:10.5pt"> </span></p>
                                <p style="margin:0pt; orphans:0; text-align:justify; text-indent:315pt; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt">    </span><span
                                        style="font-family:宋体; font-size:10.5pt">申请人签名确认：</span></p></td>
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
                                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                        style="font-family:宋体; font-size:10.5pt">□兴业银行各分行  □交通银行德景园分行  □建设银行紫陵路分行 □ 长沙银行柳叶大道总行 </span>
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
                                <p style="margin:0pt"><span style="font-family:宋体; font-size:10.5pt">□选择</span><span
                                        style="font-family:宋体; font-size:10.5pt">武陵区中小企业公共服务平台推荐的代理记账</span><span
                                        style="font-family:宋体; font-size:10.5pt">机构</span><span
                                        style="font-family:宋体; font-size:10.5pt">，</span><span
                                        style="font-family:宋体; font-size:10.5pt">合作条款及收费</span><span
                                        style="font-family:宋体; font-size:10.5pt">详见合同；</span></p>
                                <p style="margin:0pt"><span style="font-family:宋体; font-size:10.5pt">□</span><span
                                        style="font-family:宋体; font-size:10.5pt">有关会计及报税工作自行负责</span><span
                                        style="font-family:宋体; font-size:10.5pt">，</span><span
                                        style="font-family:宋体; font-size:10.5pt">如产生税务申报逾期等相关</span><span
                                        style="font-family:宋体; font-size:10.5pt">责任</span><span
                                        style="font-family:宋体; font-size:10.5pt">自行承担。</span><span
                                        style="font-family:宋体; font-size:10.5pt">                    </span></p>
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
                        <span style="font-family:'Times New Roman'; font-size:10.5pt">&#xa0;</span></p>
            </div>

    </el-dialog>
    </div>

</template>

<script>
    export default {
        name: "Examine",
        data() {
            return {
                activeName: '1',
                value: '',
                value2: '',
                list: [
                    {
                        name: "武陵区中小型企业公共服务平台“创业服务一条龙”免费申请服务表",
                        creatdata: "2017-12-2",
                        number: "1",
                    }
                ],
                list2: [
                    {
                        name: "王琦",
                        position: "法人代表",
                        number: "12345678901",
                        status: "1",
                    }
                ],
                lookVisible: false
            };
        },
        created() {

        },
        methods: {
            preview() {
                this.lookVisible = true;
            }
        }

    }
</script>

<style scoped>
    .container {
        padding: 20px;
        background-color: #fff;
    }

    .elrow {
        margin-bottom: 0;
    }
</style>
