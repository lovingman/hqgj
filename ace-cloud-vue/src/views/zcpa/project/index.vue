<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-row :gutter="24" style="padding:0; margin-right:-4px;">
          <el-col :span="2" v-if="this.areaCode == '4307'">
            <el-button
              type="primary"
              class="handle-del mr11"
              @click="handleAdd(null,'add')"
              v-if="userBtn['/zcpa/project/create']"
            >{{userBtn['/zcpa/project/create']}}</el-button>
          </el-col>
          <el-col :offset="2" :span="3" v-if="this.areaCode == '4307'">
            <el-cascader
              placeholder="请选择行政区划"
              v-model="xzZoning"
              :options="cityCountyOptions"
              :props="areaCodeProps"
              clearable
              change-on-select
              filterable
              class="handle-select mr11"
            />
          </el-col>
          <el-col :span="7" v-if="this.areaCode != '4307'">&nbsp;</el-col>
          <el-col :span="3">
            <el-select
              v-model="query.projectLevel"
              clearable
              placeholder="项目级别"
              class="handle-select mr11"
            >
              <el-option
                v-for="item in projectLevelArr"
                :key="item.code"
                :label="item.name"
                :value="item.code"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="3">
            <el-select
              v-model="query.category"
              clearable
              placeholder="项目类型"
              class="handle-select mr11"
            >
              <el-option
                v-for="item in projectTypeArr"
                :key="item.code"
                :label="item.name"
                :value="item.code"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="3">
            <el-select
              v-model="query.projectStateType"
              clearable
              placeholder="项目状态"
              class="handle-select mr11"
              @change="changeState"
            >
              <el-option
                v-for="item in statusValueArr"
                :key="item.code"
                :label="item.name"
                :value="item.code"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="3">
            <el-date-picker
              v-model="query.startTime"
              type="date"
              :picker-options="startTime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="开始时间"
              class="handle-input mr10"
            ></el-date-picker>
          </el-col>
          <el-col :span="3">
            <el-date-picker
              v-model="query.endTime"
              type="date"
              :picker-options="endTime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="结束时间"
              class="handle-input mr10"
            ></el-date-picker>
          </el-col>
          <el-col :span="2" style="padding:0;float:right;text-align: right;">
            <el-button
              style="margin-right:12px; "
              type="primary"
              icon="el-icon-search"
              @click="search"
            >搜索</el-button>
          </el-col>
        </el-row>
      </div>
      <el-table :data="rows" border max-height="475" class="table-main" ref="multipleTable">
        <el-table-column type="index" width="55" label="序号" align="center"></el-table-column>
        <el-table-column prop="projectName" label="项目名称" width="400"></el-table-column>
        <el-table-column prop="categoryType" label="项目类型" width="150"></el-table-column>
        <el-table-column prop="projectLevel" label="项目级别" sortable width="150">
          <template slot-scope="scope">
            <div type="text" v-if="scope.row.projectLevel=='0'">母项目</div>
            <div type="text" v-if="scope.row.projectLevel=='1'">子项目</div>
          </template>
        </el-table-column>
        <el-table-column prop="areaName" label="行政区划" sortable width="150"></el-table-column>
        <el-table-column
          type="date"
          value-format="yyyy-MM-dd"
          prop="startTime"
          label="开始时间"
          sortable
          width="180"
        ></el-table-column>
        <el-table-column prop="endTime" label="结束时间" sortable width="180"></el-table-column>
        <el-table-column prop="projectState" label="状态" sortable align="center" width="100">
          <template slot-scope="scope">
            <div type="text" class="orange" v-if="scope.row.projectState=='1'">待审核</div>
            <div type="text" class="green" v-if="scope.row.projectState=='2'">进行中</div>
            <div type="text" class="red" v-if="scope.row.projectState=='3'">未通过</div>
            <div type="text" class="gray" v-if="scope.row.projectState=='4'">已结束</div>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" width="240" align="right" header-align="center">
          <template slot-scope="scope">
            <el-button
              type="text"
              v-if="userBtn['/zcpa/project/docking'] && scope.row.projectState !='1' && scope.row.projectState !='3'"
              @click="handleDocking(scope.row)"
            >{{userBtn['/zcpa/project/docking']}}</el-button>
            <span
              class="strightline"
              v-if="userBtn['/zcpa/project/docking'] && scope.row.projectState !='1'  && scope.row.projectState !='3'"
            >|</span>
            <el-button
              type="text"
              v-if="userBtn['/zcpa/project/declare'] && scope.row.projectLevel=='0' && scope.row.projectState=='2'"
              @click="handleAdd(scope.row,'declare')"
            >{{userBtn['/zcpa/project/declare']}}</el-button>
            <span
              v-if="userBtn['/zcpa/project/declare'] && scope.row.projectLevel=='0' && scope.row.projectState=='2'"
              class="strightline"
            >|</span>
            <el-button
              type="text"
              v-if="userBtn['/zcpa/project/check'] &&scope.row.projectState=='1' && scope.row.userAreaCode =='4307'"
              @click="handleExamine(scope.$index, scope.row)"
            >{{userBtn['/zcpa/project/check']}}</el-button>
            <span
              class="strightline"
              v-if="userBtn['/zcpa/project/check'] && scope.row.projectState=='1' && scope.row.userAreaCode =='4307' "
            >|</span>
            <el-button
              type="text"
              v-if="userBtn['/zcpa/project/update'] && scope.row.areaCode.includes(scope.row.userAreaCode) && scope.row.projectLevel=='0' && scope.row.projectState=='2' || scope.row.projectLevel=='1' && scope.row.projectState!='4'"
              @click="handleAdd(scope.row,'edit')"
            >{{userBtn['/zcpa/project/update']}}</el-button>
            <span
              v-if="userBtn['/zcpa/project/update'] && scope.row.areaCode.includes(scope.row.userAreaCode) && scope.row.projectLevel=='0' && scope.row.projectState=='2' || scope.row.projectLevel=='1' && scope.row.projectState!='4'"
              class="strightline"
            >|</span>

            <el-button
              type="text"
              v-if="userBtn['/zcpa/project/delect']"
              @click="handleDelete(scope.$index, scope.row)"
            >{{userBtn['/zcpa/project/delect']}}</el-button>
            <span class="strightline" v-if="userBtn['/zcpa/project/delect']">|</span>
            <el-button type="text" @click="handleLook(scope.$index,scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        :total="total"
        background
        layout="total,sizes,prev, pager, next ,jumper"
      ></el-pagination>
    </div>
    <!-- 审核弹出框 -->
    <el-dialog title="审核" class="examine" :visible.sync="examineVisible">
      <el-form ref="form" :model="updateState" label-width="100%">
        <p style="line-height: 50px; text-align: center; font-size: 20px">该志愿服务项目是否通过</p>
        <div style="text-align: center">
          <el-radio-group v-model="updateState.projectState" style="text-align: center">
            <el-radio :label="2">通过</el-radio>
            <el-radio :label="3">不通过</el-radio>
          </el-radio-group>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="examineVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveExamine">确 定</el-button>
      </span>
    </el-dialog>
    <!--增加、编辑、审核弹出框组件-->
    <basic
      ref="basicData"
      :dictArrayObj="dictArrayObj"
      :submitType="submitType"
      :cityCountyOptions="cityCountyOptions"
      :title="titleName"
      :byIdData="byIdData"
      v-on:totalSubmit="totalSubmit"
    ></basic>
    <!-- end -->
    <!-- 情暖家庭母子对接弹出 -->
    <el-dialog title="情暖家庭对接" :visible.sync="familyVisible" @close="emptyForm('familyForm')">
      <el-form
        :model="familyForm"
        :inline="true"
        ref="familyForm"
        label-width="140px"
        class="editForm"
      >
        <el-tabs type="card" v-model="familyName" @tab-click="handleTabClick">
          <el-tab-pane label="被服务对象对接" name="first">
            <el-row>
              <el-col :span="8" v-if="this.projectState != '4'">
                <el-form-item prop="areaCode">
                  <el-cascader
                    placeholder="请选择行政区划"
                    v-model="familyForm.areaCode"
                    :options="areaCodeOptions"
                    :props="areaCodeProps"
                    :disabled="disabledInput"
                    @change="changeAreaCode"
                    clearable
                    change-on-select
                    filterable
                    class="handle-select"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12" v-if="this.projectState != '4'">
                <el-form-item label="新增被服务对象" style="font-size: 20px" prop="personName">
                  <el-input v-model="familyForm.personName" clearable placeholder="请输入姓名或身份证号">
                    <el-button
                      slot="append"
                      v-if="userBtn['/zcpa/project/screen']"
                      :disabled="disabledState"
                      @click="querybfw"
                    >{{userBtn['/zcpa/project/screen']}}</el-button>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2" v-if="this.projectState != '4'">
                <el-button
                  type="primary"
                  v-if="this.projectState != '4'"
                  @click="handledelbfw()"
                  style="margin-top: 4px;"
                >删除</el-button>
              </el-col>
              <el-table
                v-loading="loading"
                element-loading-text="加载中"
                element-loading-spinner="el-icon-loading"
                :data="bfwForm|filtersTwo"
                border
                class="table"
                ref="multipleTable"
                @selection-change="handleSelectionBox"
              >
                <el-table-column type="selection" width="45" align="center"></el-table-column>
                <el-table-column prop="volunteerVo" label="志愿者" width="400" class="zyz">
                  <template slot-scope="scope">
                    <el-button
                      type="text"
                      v-if="index < 4"
                      v-for="(item,index) in scope.row.volunteerVo"
                      :key="index"
                      :disabled="disabledState"
                      @click="handleAddzyz(scope.$index, scope.row,item)"
                      :icon="item.volunteerId?'':'el-icon-circle-plus-outline'"
                      style="font-size: 15px; color: black;min-width: 55px"
                    >{{item.volunteerId?item.name:item.aidTargetName}}</el-button>
                    <i style="border-left: 1px solid black;margin-left: 10px;margin-right: 10px"></i>
                    <el-button
                      type="text"
                      @click="handleVolunteer(scope.$index, scope.row)"
                      :disabled="disabledState"
                      icon="el-icon-delete"
                      style="font-size: 15px;color: black;"
                    ></el-button>
                  </template>
                </el-table-column>
                <el-table-column prop="targetName" label="被服务对象" width="100"></el-table-column>
                <el-table-column prop="psex" label="性别" width="100"></el-table-column>
                <el-table-column prop="pidCard" label="身份证号" width="200"></el-table-column>
                <el-table-column prop="pcateogry" label="类型" width="110"></el-table-column>
                <el-table-column prop="phone" label="联系方式" width="200"></el-table-column>
                <el-table-column prop="detailAddr" label="现居地址" width="200"></el-table-column>
              </el-table>
              <el-pagination
                @size-change="handleService"
                @current-change="handleServiceCurrent"
                :current-page="handleServicePage"
                :page-size="serviceSzie"
                :total="serviceTotal"
                background
                layout="total,sizes,prev, pager, next ,jumper"
              ></el-pagination>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="已对接被服务对象" name="second">
            <el-col :span="8" v-if="this.projectState != '4'">
              <el-form-item>
                <el-cascader
                  placeholder="请选择行政区划"
                  v-model="familyForm.serviceAreaCode"
                  :options="areaCodeOptions"
                  :props="areaCodeProps"
                  :disabled="disabledInput"
                  @visible-change="cascaderServiceAreaCode"
                  clearable
                  change-on-select
                  filterable
                  class="handle-select mr12"
                />
              </el-form-item>
            </el-col>
            <el-table
              :data="ydjbfwForm|filtersOne"
              border
              class="table"
              ref="multipleTable"
              @selection-change="handleBfwCheck"
            >
              <el-table-column type="selection" width="45" align="center"></el-table-column>
              <el-table-column prop="actFamilyVolunteer" label="操作" sortable width="320">
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    v-if="index<4"
                    v-for="(item,index) in scope.row.volunteerVo"
                    :key="index"
                    style="font-size: 15px;color: black;width: 55px"
                  >{{item.name}}</el-button>
                  <i style="border-left: 1px solid black;margin-left: 10px;margin-right: 10px"></i>
                  <el-button
                    type="text"
                    @click="handleVolunteer(scope.$index, scope.row)"
                    icon="el-icon-delete"
                    :disabled="disabledState"
                    style="font-size: 15px;color: black;text-align: right"
                  ></el-button>
                </template>
              </el-table-column>
              <el-table-column prop="targetName" label="被服务对象" width="100"></el-table-column>
              <el-table-column prop="psex" label="性别" width="100"></el-table-column>
              <el-table-column prop="pidCard" label="身份证号" width="220"></el-table-column>
              <el-table-column prop="pcateogry" label="类型" width="120"></el-table-column>
              <el-table-column prop="phone" label="联系方式" sortable width="200"></el-table-column>
              <el-table-column prop="detailAddr" label="现居地址" sortable width="200"></el-table-column>
            </el-table>
            <el-pagination
              @size-change="dockingClick"
              @current-change="dockingClickCurrent"
              :current-page="handleDockingPage"
              :page-size="dockingSize"
              :total="dockingTotal"
              background
              layout="total,sizes,prev, pager, next ,jumper"
            ></el-pagination>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelArr('familyForm')">取消</el-button>
      </span>
    </el-dialog>
    <!-- end -->
    <!--添加被服务对象-->
    <el-dialog title="本辖区被服务对象" :visible.sync="addbfwVisible" left>
      <div class="table-height">
        <el-table
          :data="person"
          style="width: 100%;"
          border
          class="table"
          ref="multipleTable"
          @selection-change="handleAddBfw"
        >
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column prop="personName" label="姓名" style="width: 50%;" border="true"></el-table-column>
          <el-table-column prop="idCard" label="身份证" style="width: 50%;"></el-table-column>
          <el-table-column prop="phone" label="联系方式" style="width: 50%;"></el-table-column>
          <el-table-column prop="cateogry" label="类型" style="width: 50%;"></el-table-column>
        </el-table>
      </div>
      <el-pagination
        @size-change="handleSizeFamily"
        @current-change="handleCurrentFamily"
        :current-page="familyCurrentPage"
        :page-size="familyPagesize"
        :total="familyTotal"
        background
        layout="total,sizes,prev, pager, next ,jumper"
      ></el-pagination>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addbfwVisible = false">取消</el-button>
        <el-button type="primary" @click="addbfw">提交</el-button>
      </span>
    </el-dialog>
    <!-- end -->
    <!--增加志愿者弹出框组件-->
    <volunteer
      ref="volunteerDialog"
      :volunteercode="volunteercode"
      :category="category"
      :zyzaidTargetType="zyzaidTargetType"
      :dictArrayObj="dictArrayObj"
      v-on:saveAddVolunteer="saveAddVolunteer"
    ></volunteer>
    <!-- 护安校园母子对接弹出框 -->
    <el-dialog title="护安校园对接" :visible.sync="campusVisible" @close="emptyForm('campusForm')">
      <el-form
        :model="campusForm"
        :inline="true"
        ref="campusForm"
        label-width="140px"
        class="editForm"
      >
        <el-tabs type="card" v-model="campusName" @tab-click="handleActiveClick">
          <el-tab-pane label="被服务对象对接" name="first">
            <el-row style="margin-bottom:0;" v-if="this.projectState != '4'">
              <el-col :span="8">
                <el-form-item style="width:100%" prop="areaCode">
                  <el-cascader
                    placeholder="请选择行政区划"
                    v-model="campusForm.areaCode"
                    :options="areaCodeOptions"
                    :props="areaCodeProps"
                    @change="changeCampus"
                    clearable
                    change-on-select
                    filterable
                    class="handle-select"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="新增服务学校" style="font-size: 20px" prop="schoolName">
                  <el-input v-model="campusForm.schoolName" clearable placeholder="学校名称">
                    <el-button
                      slot="append"
                      v-if="userBtn['/zcpa/project/screen']"
                      @click="queryschool"
                    >{{userBtn['/zcpa/project/screen']}}</el-button>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="2">
                <el-button
                  type="primary"
                  v-if="this.projectState != '4'"
                  @click="handledelCampus()"
                  style="margin-top: 4px;"
                >删除</el-button>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="2">
                <el-button
                  type="primary"
                  v-if="userBtn['/zcpa/project/push'] && this.projectState != '4'"
                  @click="activityPush()"
                  style="margin-top: 4px;"
                >{{userBtn['/zcpa/project/push']}}</el-button>
              </el-col>
            </el-row>
            <el-table
              :data="schoolForm"
              border
              class="table"
              ref="multipleTable"
              @selection-change="handleSchoolBox"
            >
              <el-table-column type="selection" width="45" align="center"></el-table-column>
              <el-table-column prop="targetName" label="校园名称" width="150"></el-table-column>
              <el-table-column prop="actProjectVolunteer" label="志愿者" width="400" class="zyz">
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    @click="handleSeeschoolzyz(scope.$index, scope.row)"
                    style="font-size: 15px;"
                  >查看</el-button>
                  <el-button
                    type="text"
                    v-if="index<3"
                    class="volunteer-number"
                    v-for="(item,index) in scope.row.volunteerVo"
                    :key="index"
                    style="font-size: 15px; color: black;width: 55px"
                  >{{ item.name}}</el-button>
                  <div
                    v-if="scope.row.volunteerVo.length > 3"
                    class="tips"
                    style="display: inline-block;padding: 0 10px 0 0;position: relative; top:-3px;"
                  >...</div>
                  <el-button
                    type="text"
                    :disabled="disabledState"
                    @click="handleAddzyz(scope.$index, scope.row)"
                    icon="el-icon-circle-plus-outline"
                    style="font-size: 15px;color: black;"
                  >管理</el-button>
                  <i style="border-left: 1px solid black;margin-left: 10px;margin-right: 10px"></i>
                  <el-button
                    type="text"
                    :disabled="disabledState"
                    @click="handleVolunteer(scope.$index, scope.row)"
                    icon="el-icon-delete"
                    style="font-size: 15px;color: black;"
                  ></el-button>
                </template>
              </el-table-column>
              <el-table-column prop="detailAddr" label="地址" width="200"></el-table-column>
              <el-table-column prop="phone" label="联系方式"></el-table-column>
            </el-table>
            <el-pagination
              @size-change="handleService"
              @current-change="handleServiceCurrent"
              :current-page="handleServicePage"
              :page-size="serviceSzie"
              :total="serviceTotal"
              background
              layout="total,sizes,prev, pager, next ,jumper"
            ></el-pagination>
          </el-tab-pane>
          <el-tab-pane label="活动推送记录" name="second">
            <el-row>
              <el-col :span="3">
                <el-button
                  type="primary"
                  v-if="this.projectState != '4'"
                  @click="batchDelete(null)"
                >批量删除</el-button>
              </el-col>
            </el-row>
            <div class="record-box">
              <template>
                <el-table
                  :data="recordData"
                  style="width: 100%"
                  @selection-change="handeleActiveCheck"
                >
                  <el-table-column type="selection" width="55"></el-table-column>
                  <el-table-column prop="title" label="活动主题" width="180"></el-table-column>
                  <el-table-column prop="startTime" label="开始时间" width="180"></el-table-column>
                  <el-table-column prop="endTime" label="结束时间" width="180"></el-table-column>
                  <el-table-column prop="areaCode" label="活动地点" width="150"></el-table-column>
                  <el-table-column prop="contactTel" label="联系方式" width="120"></el-table-column>
                  <el-table-column prop="volunteerNum" label="志愿者数" width="120"></el-table-column>
                  <el-table-column prop="createDate" label="消息推送时间" width="180"></el-table-column>
                  <el-table-column prop="statusName" label="推送状态" width="120"></el-table-column>
                  <el-table-column fixed="right" label="操作" width="140">
                    <template slot-scope="scope">
                      <el-button type="text" @click="seeCurrent(scope.row)" size="small">查看</el-button>
                      <el-button
                        type="text"
                        v-if="isDelete"
                        @click="batchDelete(scope.row)"
                        size="small"
                      >删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <el-pagination
                  @size-change="handleRecord"
                  @current-change="handleRecordCurrent"
                  :current-page="handleRecordPage"
                  :page-size="RecordSzie"
                  :total="RecordTotal"
                  background
                  layout="total,sizes,prev, pager, next ,jumper"
                ></el-pagination>
              </template>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelArr('campusForm')">取消</el-button>
      </span>
    </el-dialog>
    <!-- end -->
    <!-- 活动推送弹框 -->
    <active ref="activeData" v-on:submitForm="submitForm"></active>
    <!-- end -->
    <!--添加服务学校-->
    <el-dialog title="本辖区服务学校" :visible.sync="addschoolVisible" left>
      <div class="table-height">
        <el-table
          :data="addschool"
          style="width: 100%;"
          border
          class="table"
          ref="multipleTable"
          @selection-change="handleAddSchool"
        >
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column prop="schoolName" label="校园名称" style="width: 50%;" border="true"></el-table-column>
          <el-table-column prop="detailAddr" label="地址" style="width: 50%;"></el-table-column>
          <el-table-column prop="phone" label="联系方式" style="width: 50%;"></el-table-column>
        </el-table>
      </div>
      <el-pagination
        @size-change="handleSizeSchool"
        @current-change="handleCurrentSchool"
        :current-page="schoolCurrentPage"
        :page-size="schoolPagesize"
        :total="schoolTotal"
        background
        layout="total,sizes,prev, pager, next ,jumper"
      ></el-pagination>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addschoolVisible = false">取消</el-button>
        <el-button type="primary" @click="addfwschool">提交</el-button>
      </span>
    </el-dialog>
    <!-- end -->
    <!--护安校园查看志愿者-->
    <el-dialog title="已对接志愿者" :visible.sync="SeeschoolVisible" left>
      <div class="ydjizyzy">
        <el-table
          :data="SeezyzData"
          style="width: 100%;"
          border
          class="table"
          ref="multipleTable"
          @selection-change="handleSeeVolunteere"
        >
          <el-table-column type="index" width="55" label="序号" align="center"></el-table-column>
          <el-table-column prop="name" label="姓名" style="width: 50%;" border="true"></el-table-column>
          <el-table-column prop="idCard" label="身份证" style="width: 50%;"></el-table-column>
          <el-table-column prop="mobile" label="联系方式" style="width: 50%;"></el-table-column>
          <el-table-column prop="userName" label="志愿者类型" style="width: 50%;"></el-table-column>
        </el-table>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="SeeschoolVisible = false">关闭</el-button>
      </span>
    </el-dialog>
    <!-- end -->
    <!-- 其他活动对接弹出框 -->
    <el-dialog :visible.sync="otherVisible" :title="otherTitle" @close="emptyForm('otherForm')">
      <el-form
        :model="otherForm"
        :inline="true"
        ref="otherForm"
        label-width="140px"
        class="editForm"
      >
        <el-tabs type="card" v-model="otherName" @tab-click="handleActiveClick">
          <el-tab-pane label="已对接志愿者" name="first">
            <el-row v-if="this.projectState != '4'">
              <el-col :span="3">
                <el-button
                  type="primary"
                  v-if="this.projectState != '4'"
                  @click="handleAddzyz()"
                >添加志愿者</el-button>
              </el-col>
              <el-col :span="4">
                <el-button
                  type="primary"
                  v-if="this.projectState != '4'"
                  @click="deleteVolunteer()"
                >删除已对接志愿者</el-button>
              </el-col>
            </el-row>
            <el-table
              :data="otherTabData"
              border
              class="table"
              ref="multipleTable"
              @selection-change="handleOtherBox"
            >
              <el-table-column type="selection" width="45" align="center"></el-table-column>
              <el-table-column prop="name" label="姓名" width="120"></el-table-column>
              <el-table-column prop="sex" label="性别" width="80"></el-table-column>
              <el-table-column prop="idCard" label="身份证号" width="200"></el-table-column>
              <el-table-column prop="mobile" label="手机号码" width="180"></el-table-column>
              <el-table-column prop="userType" label="类型" width="160"></el-table-column>
              <el-table-column prop="createDate" label="参与对接时间"></el-table-column>
            </el-table>
            <el-pagination
              @size-change="handleOtherSize"
              @current-change="handleOtherCurrent"
              :current-page="handleOtherPage"
              :page-size="otherSize"
              :total="otherTotal"
              background
              layout="total,sizes,prev, pager, next ,jumper"
            ></el-pagination>
          </el-tab-pane>
          <el-tab-pane label="活动推送记录" name="second">
            <el-row v-if="this.projectState != '4'">
              <el-col :span="3">
                <el-button
                  type="primary"
                  v-if="userBtn['/zcpa/project/push'] && this.projectState != '4'"
                  @click="activityPush()"
                >{{userBtn['/zcpa/project/push']}}</el-button>
              </el-col>
              <el-col :span="3">
                <el-button
                  type="primary"
                  v-if="this.projectState != '4'"
                  @click="batchDelete(null)"
                >批量删除</el-button>
              </el-col>
            </el-row>
            <div class="record-box">
              <template>
                <el-table
                  :data="recordData"
                  style="width: 100%"
                  @selection-change="handeleActiveCheck"
                >
                  <el-table-column type="selection" width="55"></el-table-column>
                  <el-table-column prop="title" label="活动主题" width="180"></el-table-column>
                  <el-table-column prop="startTime" label="开始时间" width="180"></el-table-column>
                  <el-table-column prop="endTime" label="结束时间" width="180"></el-table-column>
                  <el-table-column prop="areaCode" label="活动地点" width="150"></el-table-column>
                  <el-table-column prop="contactTel" label="联系方式" width="120"></el-table-column>
                  <el-table-column prop="volunteerNum" label="志愿者数" width="120"></el-table-column>
                  <el-table-column prop="createDate" label="消息推送时间" width="180"></el-table-column>
                  <el-table-column prop="statusName" label="推送状态" width="120"></el-table-column>
                  <el-table-column fixed="right" label="操作" width="140">
                    <template slot-scope="scope">
                      <el-button type="text" @click="seeCurrent(scope.row)" size="small">查看</el-button>
                      <el-button
                        type="text"
                        v-if="isDelete"
                        @click="batchDelete(scope.row)"
                        size="small"
                      >删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <el-pagination
                  @size-change="handleRecord"
                  @current-change="handleRecordCurrent"
                  :current-page="handleRecordPage"
                  :page-size="RecordSzie"
                  :total="RecordTotal"
                  background
                  layout="total,sizes,prev, pager, next ,jumper"
                ></el-pagination>
              </template>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelArr('otherForm')">取消</el-button>
      </span>
    </el-dialog>
    <!-- end -->
    <!--查看弹出框-->
    <see ref="seeData" :seeId="seeId" :seeRow="seeRow" :dictArrayObj="dictArrayObj"></see>
    <!-- end -->
    <!--删除志愿者弹出框组件-->
    <deleteVolunteers ref="volunteerData" :title="titles" v-on:deleteVolunteers="deleteVolunteers"></deleteVolunteers>
    <!-- end -->
    <!-- 活动查看弹出组件 -->
    <seeActive
      ref="seeDatas"
      :seeByDada="seeByDada"
      :dictArrayObj="dictArrayObj"
      :category="category"
      :projectId="projectId"
      :areaCode="areaCode"
      :targetId="targetId"
    ></seeActive>
    <!-- end -->
  </div>
</template>

<script>
import {
  getList,
  create,
  getById,
  updateState,
  deleteById,
  update,
  page,
  bfwcreate,
  schoolcreate,
  zyzupdate,
  batchUpdate,
  bfwdeleteByIds,
  actSchoolpage,
  actSchoolcreate,
  schooldeleteByIds,
  selectByProjectId,
  schoolbfwdeleteByIds,
  familyCampusById,
  otherById,
  serviceObject,
  activeCreate,
  activePage,
  getActive,
  deleteActive,
  seePersonnel
} from "@/api/zcpa/actProject";
import { getAreaCode } from "@/api/zcpa/baseVolunteer";
import basic from "../publics/basic.vue";
import deleteVolunteers from "../publics/deleteVolunteers.vue";
import see from "../publics/see.vue";
import volunteer from "../publics/volunteer.vue";
import active from "../publics/active.vue";
import seeActive from "../publics/seeActive.vue";
import { getAreaTree } from "@/api/sys";
import { mapGetters } from "vuex";

export default {
  name: "basetable",
  components: {
    basic,
    deleteVolunteers,
    see,
    volunteer,
    active,
    seeActive
  },
  data() {
    return {
      areaCode: "", //用户的areaCode值
      submitType: "", //类型
      byIdData: {}, //编辑数据
      seeByDada: {}, //活动查看数据
      xzZoning: [], //搜索行政区划
      campusAreaCode: [], //校园行政区划
      currentPage: 1, //初始页
      pagesize: 10, //  每页的数据
      total: 0, //总条数
      schoolTotal: 0, //辖区服务学校
      schoolPagesize: 10, //辖区服务学校
      schoolCurrentPage: 1, //辖区服务学校
      familyTotal: 0, //家庭分页
      familyPagesize: 10, //家庭分页
      familyCurrentPage: 1, //家庭分页
      previeVisible: false, //查看图片弹框
      schoolTargetID: "", //校园主键ID
      projectState: "", //全局状态
      disabledState: false, //状态
      isTips: false, //...是否显示
      volunteerArr: "", //志愿者长度
      rows: [], //table容器
      needNumll: "", //志愿者人数
      bfwForm: [], //被服务table容器
      ydjbfwForm: [], //已对接被服务table容器
      schoolForm: [], //护安校园table容器
      cityCountyOptions: [], //2级行政数组
      disabledInput: false, //禁止选择行政区划
      zyzaidTargetType: "",
      isDelete: true, //活动推送删除是否显示
      category: "", //对接类型
      projectId: "", //当前主键ID
      targetId: "", //护安校园推送ID
      realNumber: "", //志愿者数量
      recordData: [], //活动推送tabData
      isShow: false, //是否显示
      titleName: "", //审核和创建,编辑标题
      titles: "", //删除志愿者标题
      familyVisible: false, //情暖家庭对接弹框
      familyName: "first", //情暖家庭tabs显示
      handleServicePage: 1, //情缘家庭,护安校园page
      serviceSzie: 10, //情缘家庭，护安校园page
      serviceTotal: 0, //情缘家庭，护安校园page
      handleDockingPage: 1, //已对接服务page
      dockingSize: 10, //已对接服务page
      dockingTotal: 0, //已对接服务page
      handleOtherPage: 1, //其他对接page
      otherSize: 10, //其他对接page
      otherTotal: 0, //其他对接page
      handleRecordPage: 1, //活动推送Page
      RecordSzie: 10, //活动推送Page
      RecordTotal: 0, //活动推送Page
      volunteercode: "", //传递areacode值去添加志愿者子组件
      familyForm: {
        personName: "", //personName
        areaCode: "", //areacode值
        areaCodeType: "", //areacodename值
        maxLengthValue: "", //最长的areacode值
        serviceAreaCode: "", //已对接areacode值
        serviceAreaCodeType: "", //areacodename值
        maxLengthCode: "" //最长的areacode值
      },
      campusVisible: false, //护安校园对接弹框
      campusName: "first", //护安校园tabs显示
      campusForm: {
        areaCode: "", //areacodecode值
        areaCodeType: "", //areacodename值
        schoolName: "",
        maxLengthValue: "" //最长的areacode值
      },
      //其他对接弹出框
      otherVisible: false, //其他活动
      otherTitle: "", //其他标题
      otherName: "first", //其他table显示
      otherTabData: [], //其他表格
      otherForm: {}, //其他活动对接
      loading: false, //loading状态
      person: [], //被服务对象排查table容器
      addschool: [], //添加服务学校table容器
      volunteer: [], //志愿者table容器
      SeezyzData: [], //护安校园查看志愿者table容器
      projectTypeArr: [], //项目类型
      examineVisible: false, //审核弹窗
      addbfwVisible: false, //添加被服务对象弹窗
      addschoolVisible: false, //添加服务学校弹窗
      SeeschoolVisible: false, //护安校园查看志愿者弹窗
      areaCodeOptions: [], //行政区划容器
      areaCodeProps: {
        value: "id",
        label: "text"
      },
      delbfwzyz: [], //情暖家庭删除志愿者弹出框table
      delschoolzyz: [], //护安校园删除志愿者弹出框table
      school: {
        areaCodeValue: "", //areacodecode值
        areaCodeType: "", //areacodename值
        schoolName: "", //姓名和统一社会信用代码
        maxLengthValue: "" //最长的areacode值
      },
      bfw: {
        areaCodeValue: "", //areacodecode值
        areaCodeType: "", //areacodename值
        personName: "",
        maxLengthValue: "" //最长的areacode值
      },
      query: {
        //搜索值
        startTime: "",
        endTime: "",
        region: "",
        areaCode: "",
        category: "",
        projectLevel: "",
        projectState: "",
        projectStateType: ""
      },
      // 开始时间
      startTime: {
        disabledDate: time => {
          if (this.query.endTime) {
            return time.getTime() > new Date(this.query.endTime).getTime();
          }
        }
      },
      // 结束时间
      endTime: {
        disabledDate: time => {
          if (this.query.startTime) {
            return (
              time.getTime() < new Date(this.query.startTime).getTime() - 8.64e7
            );
          }
        }
      },
      updateState: {
        //审核值
        projectState: 2
      },
      hackReset: true, //重置属性
      bfwcreate: {
        actFamilyTarget: []
      },
      schoolcreate: {
        actProjectTarget: [
          {
            areaCode: "",
            targetId: ""
          }
        ],
        projectId: ""
      },
      zyz: {
        name: "",
        userType: ""
      },
      zyzupdate: {
        id: "",
        areaCode: "",
        targetId: "",
        volunteerId: "",
        projectId: "",
        aidTargetType: ""
      },
      zyzcreate: {
        actProjectVolunteer: [
          {
            areaCode: "",
            volunteerId: ""
          }
        ],
        targetId: "",
        projectId: ""
      },
      otherzyzcreate: {
        actProjectVolunteer: [
          {
            areaCode: "",
            volunteerId: ""
          }
        ],
        projectId: ""
      },
      idx: "",
      id: "", //基本信息ID
      seeId: "", //查看ID
      seeRow: {} //查看row
    };
  },
  created() {
    this.getinformation();
    // this.AreaCodeQuery();
    this.RegionCode();
    this.initDictData();
    this.InitializationCode();
  },

  methods: {
    //获取账号信息
    getinformation() {
      this.loading = false;
      getAreaCode().then(response => {
        if (response.status == 1) {
          this.loading = true;
          this.areaCode = response.data.areaCode;
          this.areaName = response.data.areaName;
          this.volunteercode = response.data.areaCode;
          this.getData();
          this.AreaCodeQuery(this.areaCode);
        }
      });
    },
    handleQuery: function() {
      this.currentPage = 1;
      this.getData();
    },
    handleSizeChange: function(size) {
      this.pagesize = size;
      this.getData();
      //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage) {
      this.currentPage = currentPage;
      //点击第几页
      this.getData();
    },
    // 获取列表的数据
    getData() {
      this.query = Object.assign(this.query, {
        areaCode: this.areaCode,
        region: this.areaCodeObj,
        pageNum: this.currentPage,
        pageSize: this.pagesize,
        totalRecord: this.total
      });
      this.loading = true;
      getList(this.query).then(response => {
        if (response.status == 1) {
          this.rows = this.formatDataList(response.rows);
          this.total = response.total;
          this.rows = this.UserAreaCode(response.rows);
        }
      });
    },
    formatDataList(data) {
      var projectType = this.projectTypeArr;
      for (var i = 0; i < data.length; i++) {
        for (var y = 0; y < projectType.length; y++) {
          if (data[i].category == projectType[y].code) {
            data[i].categoryType = projectType[y].name;
          }
        }
      }
      return data;
    },
    //获取账号区划
    UserAreaCode(data) {
      for (var i = 0; i < data.length; i++) {
        data[i].userAreaCode = this.areaCode;
      }
      return data;
    },
    //搜索
    search() {
      if (this.areaCode == "4307") {
        let areaCodes = this.xzZoning;
        if (areaCodes.length > 0) {
          for (let e of this.xzZoning) {
            this.areaCodeObj = e;
          }
        } else {
          this.areaCodeObj = "";
        }
      }
      this.handleQuery();
    },
    //搜索状态
    changeState(value) {
      var obj = {};
      obj = this.statusValueArr.find(item => {
        //找到匹配的数据
        return item.code == value;
      });
      if (value) {
        if (value == 0) {
          this.query.projectState = ""; //code
          this.query.projectStateType = "全部"; //code
        } else {
          this.query.projectState = value; //code
        }
      }
    },
    //新增，编辑，申报提交方法
    totalSubmit() {
      this.addsForm = this.$refs.basicData.addsForm; //接受子组件传递过来的值
      if (this.submitType == "add") {
        this.addsForm.projectLevel = 0;
        create(this.addsForm).then(response => {
          if (response.status == 1) {
            this.$message.success("新增成功");
            this.$emit("refreshSubcomponents");
          }
        });
      }
      if (this.submitType == "edit") {
        if (this.addsForm.peopleNumber < this.realNumber) {
          this.$message({
            message:
              "已添加了" +
              this.realNumber +
              "个志愿者,需对接志愿者数不能少于当前添加志愿者人数",
            type: "error"
          });
          return false;
        } else {
          update(this.addsForm).then(response => {
            if (response.status == 1) {
              this.$message.success("修改成功");
              this.$emit("refreshSubcomponents");
            }
          });
        }
      }
      if (this.submitType == "declare") {
        create(this.addsForm).then(response => {
          if (response.status == 1) {
            this.$message.success("新增子项目成功");
            this.$emit("refreshSubcomponents");
          }
        });
      }
    },
    //辖区服务学校分页
    handleSizeSchool: function(size) {
      this.schoolPagesize = size;
      this.getSchoolPage();
    },
    handleCurrentSchool: function(currentPage) {
      this.schoolCurrentPage = currentPage;
      this.getSchoolPage();
    },
    getSchoolPage() {
      var obj = {};
      obj.pageNum = this.schoolCurrentPage;
      obj.pageSize = this.schoolPagesize;
      obj.totalRecord = this.schoolTotal;
      obj.areaCode = this.areaCode;
      actSchoolpage(obj).then(response => {
        if (response.status == 1) {
          this.schoolTotal = response.total;
          this.addschool = response.rows;
        }
      });
    },
    //家庭分页
    handleSizeFamily: function(size) {
      this.familyPagesize = size;
      this.getFamilyPage();
    },
    handleCurrentFamily: function(currentPage) {
      this.familyCurrentPage = currentPage;
      this.getFamilyPage();
    },
    getFamilyPage() {
      var obj = {};
      obj.pageNum = this.familyCurrentPage;
      obj.pageSize = this.familyPagesize;
      obj.totalRecord = this.familyTotal;
      obj.areaCode = this.familyForm.maxLengthValue;
      page(obj).then(response => {
        if (response.status == 1) {
          this.schoolTotal = response.total;
          this.person = response.rows;
        }
      });
    },
    //对接操作
    handleDocking(row) {
      //1情暖家庭 2护安校园 3和事佬  4法安沅澧 5心防工程 6巡逻守护
      this.loading = true;
      this.projectId = row.id;
      this.category = row.category;
      this.projectState = row.projectState;
      var areaCodesObj = [];
      var arr = [4, 6, 9, 12];
      if (this.projectState == "4") {
        this.isDelete = false;
      }
      for (var i = 0; i < 4; i++) {
        areaCodesObj[i] = this.areaCode.substring(0, arr[i]);
      }
      for (let e of areaCodesObj) {
        this.areaCodeArr = e;
      }
      if (row.category == 1) {
        this.familyVisible = true;
        this.familyName = "first";
        if (row.projectState == "4") {
          this.disabledState = true;
        } else {
          this.disabledState = false;
        }
        this.familyForm.areaCode = this.areaCode;
        this.familyForm.serviceAreaCode = this.areaCode;
        this.familyForm.maxLengthValue = this.areaCodeArr;
        this.getServicePage();
      }
      if (row.category == 2) {
        this.campusVisible = true;
        this.campusName = "first";
        if (row.projectState == "4") {
          this.disabledState = true;
        } else {
          this.disabledState = false;
        }
        this.campusForm.areaCode = this.areaCode;
        this.campusForm.maxLengthValue = this.areaCodeArr;
        this.getServicePage();
      }
      if (
        row.category == 3 ||
        row.category == 4 ||
        row.category == 5 ||
        row.category == 6
      ) {
        this.otherVisible = true;
        this.otherTitle = "和事佬对接";
        this.otherName = "first";
        this.getOtherPage();
      }
      if (row.category == 4) {
        this.otherTitle = "法安沅澧对接";
      }
      if (row.category == 5) {
        this.otherTitle = "心防工程对接";
      }
      if (row.category == 6) {
        this.otherTitle = "巡逻守护对接";
      }
    },
    //其他活动page
    getOtherPage() {
      var obj = {};
      obj.pageNum = this.handleOtherPage;
      obj.pageSize = this.otherSize;
      obj.totalRecord = this.otherTotal;
      obj.projectId = this.projectId;
      obj.areaCode = this.areaCode;
      otherById(obj).then(response => {
        if (response.status == 1) {
          this.loading = false;
          this.otherTabData = response.rows;
          this.otherTotal = response.total;
          this.realNumber = response.total;
          this.InitializationCode();
        }
      });
    },
    //情缘家庭，护安校园page
    getServicePage() {
      var obj = {};
      obj.pageNum = this.handleServicePage;
      obj.pageSize = this.serviceSzie;
      obj.totalRecord = this.serviceTotal;
      obj.projectId = this.projectId;
      obj.category = this.category;
      obj.areaCode = this.areaCode;
      familyCampusById(obj).then(response => {
        if (response.status == 1) {
          this.loading = false;
          if (this.category == 1) {
            this.bfwForm = this.initVolunteer(response.rows);
            this.serviceTotal = response.total;
            this.InitializationCode();
          }
          if (this.category == 2) {
            this.schoolForm = response.rows;
            this.serviceTotal = response.total;
          }
        }
      });
    },
    //判断志愿者是否被删除
    initVolunteer(data) {
      for (var j = 0; j < data.length; j++) {
        for (var i = 0; i < data[j].volunteerVo.length; i++) {
          if (data[j].volunteerVo[i].name == null) {
            data[j].volunteerVo[i].volunteerId = "";
          }
        }
      }
      return data;
    },
    //活动推送page
    handleActiveClick(tab, event) {
      if (tab.name == "second") {
        if (this.category == "2") {
          this.activeList();
        } else {
          this.activeList();
        }
      }
    },
    //情缘家庭被服务对象page
    handleService: function(size) {
      this.serviceSzie = size;
      this.getServicePage();
    },
    //情缘家庭被服务对象page
    handleServiceCurrent: function(currentPage) {
      this.handleServicePage = currentPage;
      this.getServicePage();
    },
    //其他对接page
    handleOtherSize: function(size) {
      this.otherSize = size;
      this.getOtherPage();
    },
    handleOtherCurrent: function(currentPage) {
      this.handleOtherPage = currentPage;
      this.getOtherPage();
    },
    //活动推送page
    handleRecord: function(size) {
      this.RecordSzie = size;
      this.activeList();
    },
    handleRecordCurrent: function(currentPage) {
      this.handleRecordPage = currentPage;
      this.activeList();
    },
    //已对接被服务对象查询
    dockingService() {
      var obj = {};
      obj.projectId = this.projectId;
      obj.areaCode = this.areaCode;
      obj.pageNum = this.handleDockingPage;
      obj.pageSize = this.dockingSize;
      obj.totalRecord = this.dockingTotal;
      serviceObject(obj).then(response => {
        if (response.status == 1) {
          this.ydjbfwForm = response.rows;
          this.dockingTotal = response.total;
          this.InitializationCode();
        }
      });
    },
    //已对接服务page
    dockingClick: function(size) {
      this.dockingSize = size;
      this.dockingService();
    },
    dockingClickCurrent: function(currentPage) {
      this.handleDockingPage = currentPage;
      this.dockingService();
    },
    //情缘家庭tab切换
    handleTabClick(tab, event) {
      this.tabName = tab.name;
      if (tab.name == "second") {
        this.dockingService();
      }
    },
    //删除其他志愿者
    deleteVolunteer() {
      if (this.otherBox && this.otherBox.length) {
        var tempArr = this.otherBox;
        var strArr = this.otherBox.map(item => item.id).join(",");
        if (tempArr.length > 0) {
          this.$confirm("确认删除选中的数据, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            schooldeleteByIds(strArr).then(response => {
              if (response.status == 1) {
                this.handleOtherPage = 1;
                this.$message.success("删除成功");
                this.$refs.volunteerDialog.getVolunteerData();
                this.getOtherPage();
                this.getNeedNum();
              }
            });
          });
        }
      } else {
        this.$message({
          message: "请勾选删除的数据！",
          type: "warning"
        });
      }
    },
    //被服务对象排查
    querybfw() {
      var query = {};
      query.areaCode = this.familyForm.maxLengthValue;
      query.personName = this.familyForm.personName;
      page(query).then(response => {
        if (response.status == 1) {
          this.person = response.rows;
          this.addbfwVisible = true;
          this.familyTotal = response.total;
        }
      });
    },
    //添加被服务对象
    addbfw() {
      if (this.addBfwCheck && this.addBfwCheck.length) {
        var FamilyTarget = [];
        for (var i = 0; i < this.addBfwCheck.length; i++) {
          var obj = {};
          obj.areaCode = this.addBfwCheck[i].areaCode;
          obj.personId = this.addBfwCheck[i].id;
          obj.personName = this.addBfwCheck[i].personName;
          FamilyTarget.push(obj);
        }
        this.bfwcreate.actFamilyTarget = FamilyTarget;
        this.bfwcreate.projectId = this.projectId;
        this.bfwcreate.areaCode = this.familyForm.maxLengthValue;
        if (this.addBfwCheck.length > 0) {
          bfwcreate(this.bfwcreate).then(response => {
            if (response.status == 1) {
              this.$message.success(`新增服务对象成功`);
              this.addbfwVisible = false;
              this.getServicePage();
            }
          });
        }
      } else {
        this.$message.success(`操作成功`);
        this.addbfwVisible = false;
      }
    },
    //被服务人员新增志愿者弹窗
    handleAddzyz(index, row, item) {
      this.$refs.volunteerDialog.volunteerVisible = true;
      this.idx = index;
      if (this.category == 1) {
        this.zyzupdate.targetId = row.id;
        this.zyzAreaCode = row.areaCode;
        this.zyzupdate.id = item.id;
        this.zyzaidTargetType = item.aidTargetType;
        this.$refs.volunteerDialog.zyz.userType = parseInt(item.aidTargetType);
      }
      if (this.category == 2) {
        this.schoolTargetID = row.id;
        this.volunteerArr = row.volunteerVo.length;
      }
      this.getNeedNum();
    },
    //确定情暖家庭志愿者新增
    saveAddVolunteer() {
      this.volunteerObj = this.$refs.volunteerDialog.volunteerArr;
      if (this.category == 1) {
        if (this.volunteerObj && this.volunteerObj.length) {
          var volunteerArr = this.volunteerObj;
          if (volunteerArr.length > 0) {
            if (volunteerArr.length == 1) {
              this.zyzupdate.areaCode = this.volunteerObj[0].areaCode;
              this.zyzupdate.volunteerId = this.volunteerObj[0].id;
              this.zyzupdate.aidTargetType = this.volunteerObj[0].userType;
              this.zyzupdate.projectId = this.projectId;
              zyzupdate(this.zyzupdate).then(response => {
                if (response.status == 1) {
                  this.$refs.volunteerDialog.volunteerVisible = false;
                  this.$message.success(`新增志愿者成功`);
                  this.$refs.volunteerDialog.getVolunteerData();
                  this.getServicePage();
                }
              });
            } else {
              this.$message({
                type: "error",
                message: "当前操作,只能添加一个志愿者"
              });
            }
          }
        } else {
          var obj = {};
          obj.areaCode = this.zyzAreaCode;
          obj.targetId = this.zyzupdate.targetId;
          obj.id = this.zyzupdate.id;
          obj.aidTargetType = this.$refs.volunteerDialog.zyz.userType;
          obj.projectId = this.projectId;
          zyzupdate(obj).then(response => {
            if (response.status == 1) {
              this.$refs.volunteerDialog.volunteerVisible = false;
              this.$message.success(`设定志愿者类型成功`);
              this.$refs.volunteerDialog.getVolunteerData();
              this.getServicePage();
            }
          });
        }
      } else if (this.category == 2) {
        if (this.volunteerObj) {
          var actProjectVolunteer = [];
          for (var i = 0; i < this.volunteerObj.length; i++) {
            var obj = {};
            obj.areaCode = this.volunteerObj[i].areaCode;
            obj.volunteerId = this.volunteerObj[i].id;
            actProjectVolunteer.push(obj);
          }
          this.zyzcreate.actProjectVolunteer = actProjectVolunteer;
          this.zyzcreate.projectId = this.projectId;
          this.zyzcreate.targetId = this.schoolTargetID;
          if (this.volunteerObj.length > this.needNumll) {
            if (this.needNumll == 0) {
              this.$message({
                message: "志愿者已满",
                type: "error"
              });
            } else {
              this.$message({
                message: "还能添加" + this.needNumll + "个志愿者",
                type: "error"
              });
            }
          } else {
            actSchoolcreate(this.zyzcreate).then(response => {
              if (response.status == 1) {
                this.$message.success(`新增志愿者成功`);
                this.$refs.volunteerDialog.volunteerVisible = false;
                this.$refs.volunteerDialog.getVolunteerData();
                this.getServicePage();
              }
            });
          }
        } else {
          this.$message.success(`操作成功`);
          this.$refs.volunteerDialog.volunteerVisible = false;
        }
      } else {
        if (this.volunteerObj && this.volunteerObj.length) {
          var actProjectVolunteer = [];
          for (var i = 0; i < this.volunteerObj.length; i++) {
            var obj = {};
            obj.areaCode = this.volunteerObj[i].areaCode;
            obj.volunteerId = this.volunteerObj[i].id;
            actProjectVolunteer.push(obj);
          }
          this.otherzyzcreate.actProjectVolunteer = actProjectVolunteer;
          this.otherzyzcreate.projectId = this.projectId;
          if (this.volunteerObj.length > this.needNumll) {
            if (this.needNumll == 0) {
              this.$message({
                message: "志愿者已满",
                type: "error"
              });
            } else {
              this.$message({
                message: "还能添加" + this.needNumll + "个志愿者",
                type: "error"
              });
            }
          } else {
            actSchoolcreate(this.otherzyzcreate).then(response => {
              if (response.status == 1) {
                this.$message.success("新增志愿者成功");
                this.loading = false;
                this.$refs.volunteerDialog.volunteerVisible = false;
                this.$refs.volunteerDialog.getVolunteerData();
                this.getOtherPage();
              }
            });
          }
        } else {
          this.$message.success("操作成功");
          this.$refs.volunteerDialog.volunteerVisible = false;
        }
      }
    },
    //删除志愿者弹窗
    handleVolunteer(index, row) {
      if (this.category == "1") {
        this.titles = "删除已对接情暖家庭志愿者";
      } else if (this.category == "2") {
        this.titles = "删除已对接护安校园志愿者";
      }
      this.$refs.volunteerData.delzyzVisible = true;
      this.$refs.volunteerData.deleteVolunteerArr = row.volunteerVo;
      this.deleteVolunteerArrs = this.$refs.volunteerData.deleteVolunteerArr;
      this.formatData(row.volunteerVo);
      if (this.deleteVolunteerArrs) {
        for (var i = 0; i < this.deleteVolunteerArrs.length; i++) {
          for (var y = 0; y < this.peopleValueArr.length; y++) {
            if (
              this.deleteVolunteerArrs[i].userType ==
              this.peopleValueArr[y].code
            ) {
              this.deleteVolunteerArrs[i].userType = this.peopleValueArr[
                y
              ].name;
            }
          }
        }
      }
    },
    formatData(data) {
      var item = [];
      for (var i = 0; i < data.length; i++) {
        if (data[i].volunteerId) {
          item.push(data[i]);
        }
      }
      this.$refs.volunteerData.deleteVolunteerArr = item;
    },
    //确定删除志愿者
    deleteVolunteers() {
      this.deleteCheck = this.$refs.volunteerData.deleteCheck;
      if (this.deleteCheck && this.deleteCheck.length) {
        var tempArr = this.deleteCheck;
        this.ids = this.deleteCheck.map(item => item.id).join(",");
        if (tempArr.length > 0) {
          this.$confirm("确认删除选中的数据, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            if (this.category == "1") {
              batchUpdate(this.ids).then(response => {
                if (response.status == 1) {
                  this.$message.success("删除成功");
                  this.$refs.volunteerDialog.getVolunteerData();
                  this.getServicePage();
                  this.dockingService();
                }
              });
            }
            if (this.category == "2") {
              schooldeleteByIds(this.ids).then(response => {
                if (response.status == 1) {
                  this.$message.success("删除成功");
                  this.$refs.volunteerDialog.getVolunteerData();
                  this.getServicePage();
                }
              });
            }
            if (this.category != "1" || this.category != "2") {
              schooldeleteByIds(this.ids).then(response => {
                if (response.status == 1) {
                  this.$message.success("删除成功");
                  this.getOtherPage();
                }
              });
            }
            this.$refs.volunteerData.delzyzVisible = false;
          });
        }
      } else {
        this.$message({
          message: "请勾选删除的数据！",
          type: "warning"
        });
      }
    },
    //删除被服务人员
    handledelbfw() {
      if (this.selectBox && this.selectBox.length) {
        var tempArr = this.selectBox;
        var strArr = this.selectBox.map(item => item.id).join(",");
        if (tempArr.length > 0) {
          this.$confirm("确认删除选中的数据, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            bfwdeleteByIds(strArr).then(response => {
              if (response.status == 1) {
                this.handleServicePage = 1;
                this.$message.success(`删除成功`);
                this.getServicePage();
              }
            });
          });
        }
      } else {
        this.$message({
          message: "请勾选删除的数据！",
          type: "warning"
        });
      }
    },
    //删除服务校园
    handledelCampus() {
      if (this.schoolBox && this.schoolBox.length) {
        var tempArr = this.schoolBox;
        var strArr = this.schoolBox.map(item => item.id).join(",");
        if (tempArr.length > 0) {
          this.$confirm("确认删除选中的数据, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() => {
            schoolbfwdeleteByIds(strArr).then(response => {
              if (response.status == 1) {
                this.handleServicePage = 1;
                this.$message.success(`删除成功`);
                this.getServicePage();
              }
            });
          });
        }
      } else {
        this.$message({
          message: "请勾选删除的数据！",
          type: "warning"
        });
      }
    },
    //添加服务校园
    addfwschool() {
      if (this.addSchollCheck && this.addSchollCheck.length) {
        var campusArr = [];
        for (var i = 0; i < this.addSchollCheck.length; i++) {
          var obj = {};
          obj.areaCode = this.addSchollCheck[i].areaCode;
          obj.targetId = this.addSchollCheck[i].id;
          campusArr.push(obj);
        }
        this.schoolcreate.actProjectTarget = campusArr;
        this.schoolcreate.projectId = this.projectId;
        if (this.addSchollCheck.length > 0) {
          schoolcreate(this.schoolcreate).then(response => {
            if (response.status == 1) {
              this.$message.success(`新增服务学校成功`);
              this.addschoolVisible = false;
              this.getServicePage();
            }
          });
        }
      } else {
        this.$message.success(`操作成功`);
        this.addschoolVisible = false;
      }
    },
    //获取志愿者人数限制
    getNeedNum() {
      getById(this.projectId).then(response => {
        if (response.status == 1) {
          this.needNumll = response.data.needNum;
        }
      });
    },
    //查看服务学校志愿者
    handleSeeschoolzyz(index, row) {
      this.targetId = row.id;
      selectByProjectId(this.projectId, this.targetId).then(response => {
        this.SeezyzData = response;
        if (this.SeezyzData) {
          for (var i = 0; i < this.SeezyzData.length; i++) {
            for (var y = 0; y < this.peopleValueArr.length; y++) {
              if (this.SeezyzData[i].userType == this.peopleValueArr[y].code) {
                this.SeezyzData[i].userType = this.peopleValueArr[y].code;
                this.SeezyzData[i].userName = this.peopleValueArr[y].name;
              }
            }
          }
        }
      });
      this.SeeschoolVisible = true;
    },
    //新增服务学校排查
    queryschool() {
      var obj = {};
      obj.schoolName = this.campusForm.schoolName;
      obj.areaCode = this.campusForm.maxLengthValue;
      actSchoolpage(obj).then(response => {
        if (response.status == 1) {
          this.addschool = response.rows;
          this.schoolTotal = response.total;
        }
      });
      this.addschoolVisible = true;
    },
    //活动推送
    activityPush() {
      if (this.category == 2) {
        if (this.schoolBox && this.schoolBox.length) {
          this.$refs.activeData.activityVisible = true;
        } else {
          this.$message({
            message: "请先勾选学校！",
            type: "warning"
          });
        }
      } else {
        this.$refs.activeData.activityVisible = true;
      }
    },
    //活动提交
    submitForm() {
      this.activitysForm = this.$refs.activeData.activitysForm;
      this.activitysForm.projectCategory = this.category;
      this.activitysForm.projectId = this.projectId;
      this.activitysForm.currentAreaCode = this.areaCode;
      if (this.category == 2) {
        var idArr = this.schoolBox.map(item => item.id).join(",");
        this.activitysForm.targetId = idArr;
        activeCreate(this.activitysForm).then(response => {
          if (response.status == 1) {
            this.$message.success(`推送成功`);
            this.$refs.activeData.activityVisible = false;
            this.activeList();
          }
        });
      } else {
        activeCreate(this.activitysForm).then(response => {
          if (response.status == 1) {
            this.$message.success(`推送成功`);
            this.$refs.activeData.activityVisible = false;
            this.activeList();
          }
        });
      }
    },
    //活动列表请求
    activeList() {
      var obj = {};
      obj.projectId = this.projectId;
      obj.category = this.category;
      obj.pageNum = this.handleRecordPage;
      obj.pageSize = this.RecordSzie;
      obj.totalRecord = this.RecordTotal;
      obj.currentAreaCode = this.areaCode;
      activePage(obj).then(response => {
        if (response.status == 1) {
          this.recordData = response.rows;
          this.RecordTotal = response.total;
          if (this.recordData) {
            for (var i = 0; i < this.recordData.length; i++) {
              if (this.recordData[i].status == "1") {
                this.recordData[i].statusName = "成功";
              }
              if (this.recordData[i].status == "2") {
                this.recordData[i].statusName = "失败";
              }
            }
          }
        }
      });
    },
    //删除当前活动列表
    batchDelete(row) {
      if (row) {
        var str = row.id;
        //单个删除
        this.$confirm("确认删除选中的数据, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            deleteActive(str).then(response => {
              if (response.status == 1) {
                this.$message.success(`删除成功`);
                this.activeList();
              }
            });
          })
          .catch(() => {
            //捕捉异常
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
      } else {
        var tempArr = this.activeCheck;
        if (tempArr && tempArr.length > 0) {
          this.$confirm("确认删除选中的数据, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
            .then(() => {
              var strArr = this.activeCheck.map(item => item.id).join(",");
              deleteActive(strArr).then(response => {
                if (response.status == 1) {
                  this.$message.success(`删除成功`);
                  this.activeList();
                }
              });
            })
            .catch(() => {
              this.$message({
                type: "info",
                message: "已取消删除"
              });
            });
        } else {
          this.$message({
            message: "请勾选删除的数据！",
            type: "warning"
          });
        }
      }
    },
    //活动查看
    seeCurrent(row) {
      var seeId = row.id;
      this.targetId = row.targetId;
      getActive(seeId).then(response => {
        if (response.status == 1) {
          this.seeByDada = response.data;
          if (this.$refs.seeDatas.see.areaName == "") {
            this.$refs.seeDatas.see.areaName = this.areaName;
          }
          this.$refs.seeDatas.recordVisible = true;
          this.$refs.seeDatas.initPersonnel(); //调用子组件方法
        }
      });
    },
    //关闭对接清空表单
    emptyForm(type) {
      if (type) {
        this.$refs[type].resetFields();
        if (this.category == 1) {
          this.serviceSzie = 10;
          this.handleServicePage = 1;
        }
        if (this.category == 2) {
          this.serviceSzie = 10;
          this.handleServicePage = 1;
          this.handleRecordPage = 1;
          this.RecordSzie = 10;
        }
        if (this.category != "1" || this.category != "2") {
          this.handleRecordPage = 1;
          this.RecordSzie = 10;
        }
      }
    },
    //取消清空对接表单
    cancelArr(formName) {
      this.$nextTick(() => {
        this.$refs[formName].resetFields();
      });
      if (this.category == 1) {
        this.serviceSzie = 10;
        this.handleServicePage = 1;
      }
      if (this.category == 2) {
        this.serviceSzie = 10;
        this.handleServicePage = 1;
        this.handleRecordPage = 1;
        this.RecordSzie = 10;
      }
      if (this.category != "1" || this.category != "2") {
        this.handleRecordPage = 1;
        this.RecordSzie = 10;
      }
      if (formName == "familyForm") {
        this.familyVisible = false;
      }
      if (formName == "campusForm") {
        this.campusVisible = false;
      }
      if (formName == "otherForm") {
        this.otherVisible = false;
      }
    },
    //初始化code找中文
    InitializationCode() {
      if (this.bfwForm) {
        for (let i = 0; i < this.bfwForm.length; i++) {
          for (let y = 0; y < this.genderArr.length; y++) {
            if (this.bfwForm[i].psex == this.genderArr[y].code) {
              this.bfwForm[i].psex = this.genderArr[y].name;
            }
          }
          for (let x = 0; x < this.serviceObjectArr.length; x++) {
            if (this.bfwForm[i].pcateogry == this.serviceObjectArr[x].code) {
              this.bfwForm[i].pcateogry = this.serviceObjectArr[x].name;
            }
          }
        }
      }
      if (this.ydjbfwForm) {
        for (let i = 0; i < this.ydjbfwForm.length; i++) {
          for (let y = 0; y < this.genderArr.length; y++) {
            if (this.ydjbfwForm[i].psex == this.genderArr[y].code) {
              this.ydjbfwForm[i].psex = this.genderArr[y].name;
            }
          }
        }
      }
      if (this.otherTabData) {
        for (let i = 0; i < this.otherTabData.length; i++) {
          for (let y = 0; y < this.genderArr.length; y++) {
            if (this.otherTabData[i].sex == this.genderArr[y].code) {
              this.otherTabData[i].sex = this.genderArr[y].name;
            }
          }
          for (let x = 0; x < this.peopleValueArr.length; x++) {
            if (this.otherTabData[i].userType == this.peopleValueArr[x].code) {
              this.otherTabData[i].userType = this.peopleValueArr[x].name;
            }
          }
        }
      }
    },
    //志愿者排查
    queryzyz() {
      this.getVolunteerData();
    },
    //获取行政区划数据
    AreaCodeQuery(data) {
      getAreaTree({ pid: data, type: 1, hasSelf: "true" }).then(response => {
        this.areaCodeOptions = response.data;
      });
    },
    RegionCode() {
      const userProp = this.$store.getters.userProp;
      getAreaTree({ pid: userProp.areaCode, type: 3, hasSelf: "true" }).then(
        response => {
          this.cityCountyOptions = response.data;
        }
      );
    },
    //查看详情
    handleLook(index, row) {
      this.$refs.seeData.lookVisible = true;
      this.seeId = row.id;
      this.seeRow = row;
    },
    //删除活动弹窗
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.idx = index;
          this.id = row.id;
          deleteById(this.id).then(response => {
            if (response.status == 1) {
              this.currentPage = 1;
              this.$message.success("删除成功");
              this.getData();
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    //被服务对象志愿者table选中
    handleSelectionBox(val) {
      this.selectBox = val;
    },
    //添加被服务
    handleAddBfw(val) {
      this.addBfwCheck = val;
    },
    //已对接选中框
    handleBfwCheck(val) {
      this.bfwCheck = val;
    },
    //护安校园选中
    handleSchoolBox(val) {
      this.schoolBox = val;
    },
    //其他活动选中
    handleOtherBox(val) {
      this.otherBox = val;
    },
    //添加服务学校
    handleAddSchool(val) {
      this.addSchollCheck = val;
    },
    //批量删除活动
    handeleActiveCheck(val) {
      this.activeCheck = val;
    },
    //护安校园志愿者查看选中
    handleSeeVolunteere(val) {
      this.seeCheck = val;
    },
    //护安校园删除志愿者选中
    handleDeleteVolunteere(val) {
      this.deleteCheck = val;
    },
    //确定审核
    saveExamine() {
      updateState(this.id, this.updateState.projectState).then(response => {
        if (response.status == 1) {
          this.$message.success("审核成功");
          this.examineVisible = false;
          this.getData();
        }
      });
    },

    //审核弹窗
    handleExamine(index, row) {
      this.idx = index;
      this.id = row.id;
      this.examineVisible = true;
    },
    //新增,编辑,申报弹窗
    handleAdd(row, word) {
      if (word == "add") {
        this.submitType = word; //类型赋值
        this.titleName = "创建";
        this.$refs.basicData.addVisible = true; //显示弹框表单
        this.$refs.basicData.isEdit = false; //编辑是否放开隐藏的form表单
        this.$refs.basicData.disableds = false; //编辑是否放开禁止属性
        this.$refs.basicData.hideUpload = false; //新增的时候显示上传按钮
        this.$refs.basicData.isLevel = false; //新增的时候项目级别隐藏
        this.$refs.basicData.isAreaCode = false; //新增的时候行政区划隐藏
        this.$refs.basicData.addsForm.SupstartTime = "";
        this.$refs.basicData.addsForm.SupendTime = "";
        this.$refs.basicData.addsForm.areaCode = this.areaCode;
      }
      if (word == "edit") {
        this.titleName = "编辑";
        this.submitType = word;
        this.id = row.id;
        this.category = row.category;
        this.$refs.basicData.addVisible = true;
        this.$refs.basicData.isEdit = true;
        this.$refs.basicData.isLevel = true;
        this.$refs.basicData.isAreaCode = true;
        this.$refs.basicData.disableds = true;
        this.$refs.basicData.disabledAreaCode = true;
        if (row.category == "1") {
          this.$refs.basicData.isShow = false;
        } else {
          this.$refs.basicData.isShow = true;
        }
        if (row.category == "2") {
          var obj = {};
          obj.projectId = this.id;
          obj.category = this.category;
          familyCampusById(obj).then(response => {
            if (response.status == 1) {
              this.realNumber = 0;
              for (let i = 0; i < response.rows.length; i++) {
                this.realNumber += response.rows[i].volunteerVo.length;
              }
            }
          });
        }
        if (
          row.category == "3" ||
          row.category == "4" ||
          row.category == "5" ||
          row.category == "6"
        ) {
          this.projectId = row.id;
          this.getOtherPage();
        }
        getById(this.id).then(response => {
          if (response.status == 1) {
            this.byIdData = response.data; //接受数据传递给子组件
            this.byIdData.areaCodeArr = [];
            var arr = [4, 6];
            for (var i = 0; i < 2; i++) {
              this.byIdData.areaCodeArr[i] = this.byIdData.areaCode.substring(
                0,
                arr[i]
              );
            }
          }
        });
      }
      if (word == "declare") {
        var ids = row.id;
        var SupstartTime = row.startTime;
        var SupendTime = row.endTime;
        this.submitType = word;
        this.titleName = "申报";
        this.$refs.basicData.hideUpload = false;
        this.$refs.basicData.addVisible = true;
        this.$refs.basicData.isEdit = false;
        this.$refs.basicData.isLevel = true;
        this.$refs.basicData.isAreaCode = true;
        this.$refs.basicData.disableds = true;
        if (this.areaCode == "4307") {
          this.$refs.basicData.disabledAreaCode = false;
        } else {
          this.$refs.basicData.disabledAreaCode = true;
        }
        if (row.category == "1") {
          this.$refs.basicData.isShow = false;
        } else {
          this.$refs.basicData.isShow = true;
        }
        getById(ids).then(response => {
          if (response.status == 1) {
            var declareData = response.data;
            this.$refs.basicData.addsForm.areaCode = this.areaCode;
            this.$refs.basicData.addsForm.areaCodeArr = [];
            var arr = [4, 6];
            for (var i = 0; i < 2; i++) {
              this.$refs.basicData.addsForm.areaCodeArr[
                i
              ] = this.areaCode.substring(0, arr[i]);
            }
            this.$refs.basicData.addsForm.projectLevel = "1";
            this.$refs.basicData.addsForm.pid = ids;
            this.$refs.basicData.addsForm.category = declareData.category;
            this.$refs.basicData.addsForm.jiFen = declareData.jiFen;
            this.$refs.basicData.addsForm.SupstartTime = SupstartTime;
            this.$refs.basicData.addsForm.SupendTime = SupendTime;
            this.$refs.basicData.addsForm.nineteenJiFen =
              declareData.nineteenJiFen;
          }
        });
      }
    },
    // 重新渲染子组件 传递方法给子组件
    refreshSubcomponents() {
      this.$emit("refreshSubcomponents");
    },
    //初始化字典数据
    initDictData() {
      this.genderArr = this.dictArrayObj.genderValue; //4性别值
      this.projectTypeArr = this.dictArrayObj.projectValue; //47项目类型
      this.projectLevelArr = this.dictArrayObj.ProjectLevelValue; //48项目等级
      this.peopleValueArr = this.dictArrayObj.peopleValue; //49人物身份
      this.statusValueArr = this.dictArrayObj.statusValue; //50数据状态
      this.serviceObjectArr = this.dictArrayObj.serviceObjectValue; //51服务对象类型
    },
    //查找最长的areaCode值
    initUnitForm(areaCodeArr, areaCodeOptions) {
      if (areaCodeArr && areaCodeOptions) {
        //查找最长的字符串
        var maxLengthAreaCode = this.globalMethods.findLongestWord(
          [].concat(areaCodeArr)
        );
        //找到文字级联
        var tepmTreeArr = this.globalMethods.getCascaderObj(
          areaCodeArr,
          areaCodeOptions
        );
        //文字
        // var tempTextArr = [];
        // if (tepmTreeArr.length > 0) {
        //   tepmTreeArr.forEach((item, i) => {
        //     tempTextArr.push(item.text);
        //   });
        // }
        //情缘家庭
        // this.familyForm.areaCodeType = tempTextArr;
        this.familyForm.areaCode = areaCodeArr;
        this.familyForm.maxLengthValue = maxLengthAreaCode; //最长的值
        //已对接服务对象areaCode
        // this.familyForm.serviceAreaCode = tempTextArr;
        this.familyForm.serviceAreaCode = areaCodeArr;
        this.familyForm.maxLengthCode = maxLengthAreaCode; //最长的值

        //校园
        // this.campusForm.areaCodeType = tempTextArr;
        this.campusForm.areaCode = areaCodeArr;
        this.campusForm.maxLengthValue = maxLengthAreaCode; //最长的值
      }
    },
    //情暖家庭行政区划事件
    changeAreaCode(value) {
      // var tempTextArr = [];
      // // 根据选中的code值区查询相应的中文
      // var tepmTreeArr = this.globalMethods.getCascaderObj(
      //   value,
      //   this.areaCodeOptions
      // );
      var maxLengthValue = this.globalMethods.findLongestWord([].concat(value));
      this.familyForm.areaCodeValue = value; //code
      this.familyForm.maxLengthValue = maxLengthValue; //最长的值
    },
    //情暖家庭已对接行政区划事件
    cascaderServiceAreaCode(value) {
      if (!value) {
        var maxLengthValue = this.globalMethods.findLongestWord(
          [].concat(this.familyForm.serviceAreaCode)
        );
        this.areaCode = maxLengthValue;
        this.dockingService();
      }
    },
    //护安校园行政区划事件
    changeCampus(value) {
      // var tempTextArr = [];
      // // 根据选中的code值区查询相应的中文
      // var tepmTreeArr = this.globalMethods.getCascaderObj(
      //   value,
      //   this.areaCodeOptions
      // );
      // if (tepmTreeArr.length > 0) {
      //   tepmTreeArr.forEach((item, i) => {
      //     tempTextArr.push(item.text);
      //   });
      // }
      var maxLengthValue = this.globalMethods.findLongestWord([].concat(value));
      this.campusForm.areaCodeValue = value; //code
      // this.campusForm.areaCodeType = tempTextArr; //赋值汉字
      this.campusForm.maxLengthValue = maxLengthValue; //最长的值
    }
  },
  filters: {
    filtersOne(data) {
      var item = [];
      if (data) {
        for (var i = 0; i < data.length; i++) {
          var c = data[i].volunteerVo;
          if (
            c[0].volunteerId &&
            c[1].volunteerId &&
            c[2].volunteerId &&
            c[3].volunteerId
          ) {
            item.push(data[i]);
          }
        }
      }
      return item;
    },
    filtersTwo(data) {
      var item = [];
      if (data) {
        for (var i = 0; i < data.length; i++) {
          var c = data[i].volunteerVo;
          if (
            c[0].volunteerId &&
            c[1].volunteerId &&
            c[2].volunteerId &&
            c[3].volunteerId
          ) {
          } else {
            item.push(data[i]);
          }
        }
      }
      return item;
    }
  },
  computed: {
    ...mapGetters(["dictArrayObj"]),
    ...mapGetters(["userProp"]),
    ...mapGetters(["userBtn"])
  }
};
</script>

<style scoped lang="less">
.container {
  padding: 20px;
  background-color: #fff;
}
.handle-box {
  margin-bottom: 20px;
  /deep/ .el-cascader--medium {
    width: 100%;
  }
  /deep/ .el-select {
    width: 100%;
  }
  /deep/ .el-date-editor {
    width: 100%;
  }
}

.table {
  width: 100%;
  font-size: 14px;
  /deep/ .el-cascader--medium {
    width: 100%;
  }
}

.orange {
  color: #f0760b;
}

.red {
  color: red;
}

.green {
  color: green;
}

.gray {
  color: gray;
}

.table-main {
  /deep/ .el-table__header th {
    background-color: #007cff;
  }
  /deep/ .el-table__header th > .cell {
    color: #fff;
  }
  .strightline {
    color: #eee;
  }
}

.table-main /deep/ .sort-caret.ascending {
  border-bottom-color: #fff;
}
.table-main /deep/ .ascending .sort-caret.ascending {
  border-bottom-color: #92c0f1;
}
.table-main /deep/ .sort-caret.descending {
  border-top-color: #fff;
}
.table-main /deep/ .descending .sort-caret.descending {
  border-top-color: #92c0f1;
}
.table-main
  /deep/
  .has-gutter
  .el-checkbox__input.is-checked
  .el-checkbox__inner {
  border-color: #045396;
  background-color: #045396;
}

.table {
  /deep/ .examine /deep/.el-dialog {
    width: 500px !important;
  }
  /deep/ .el-dialog__header {
    background-color: #b405ec;
    padding: 10px 20px;
    .el-dialog__title {
      color: #fff;
    }
    .el-dialog__headerbtn .el-dialog__close {
      color: #fff;
    }
    .el-dialog__headerbtn {
      top: 12px;
    }
  }
  /deep/ .el-dialog {
    margin-top: 3% !important;
    height: 80%;
    width: 1000px !important;
  }
  /deep/ .el-dialog__body {
    height: calc(~"100% - 120px");
    overflow-y: auto;
  }
  /deep/ .examine .el-dialog {
    margin-top: 15vh !important;
    height: auto !important;
  }
}

.editForm {
  /deep/ .el-select {
    width: 100%;
  }
  /deep/ .el-date-editor.el-input {
    width: 100%;
  }
  /deep/ .el-form-item {
    width: 100%;
  }
  /deep/ .el-input {
    width: 300px;
  }
  /deep/ .el-pagination .el-input {
    width: auto;
  }
  /deep/ .el-pagination .el-select .el-input {
    width: 120px;
  }
}

.activitysBox {
  /deep/ .el-form-item {
    width: 100%;
  }
  /deep/ .el-form-item__content {
    width: calc(~"100% - 160px");
  }
  /deep/ .el-date-editor.el-input {
    width: 100%;
  }
}

.record-box {
  width: 100%;
  min-height: 100px;
  border: 1px solid #eee;
}

.imgShowDemo {
  /deep/ .el-dialog {
    height: 80%;
    margin-top: 5% !important;
    width: 1200px !important;
  }
  /deep/ .el-dialog__body {
    height: calc(~"100% - 80px");
    overflow-y: auto;
  }
  img {
    max-width: 100%;
  }
}
.table-height {
  height: 520px;
  overflow-y: auto;
}
</style>
