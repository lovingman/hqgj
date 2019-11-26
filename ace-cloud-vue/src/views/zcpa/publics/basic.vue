<template>
  <div class="declare">
    <el-dialog :title="titles" :visible.sync="addVisible" @close="eliminateDialog('addsForm')">
      <el-form
        ref="addsForm"
        :model="addsForm"
        :rules="addsRules"
        label-width="140px"
        class="addForm"
      >
        <div class="form-item-box">
          <div class="left">
            <el-col :span="24">
              <el-form-item label="项目名称" prop="projectName">
                <el-input
                  class="input-right"
                  type="text"
                  placeholder="请输入项目名称, 限制50个字以内"
                  v-model="addsForm.projectName"
                  maxlength="50"
                  clearable
                  show-word-limit
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="isLevel">
              <el-form-item label="项目级别" prop="projectLevel">
                <el-select
                  v-model="addsForm.projectLevel"
                  :disabled="disableds"
                  clearable
                  placeholder="请选择项目级别"
                >
                  <el-option
                    v-for="item in projectLevelArr"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="项目类型" prop="category">
                <el-select
                  v-model="addsForm.category"
                  clearable
                  :disabled="disableds"
                  placeholder="请选择项目类型"
                  @change="changeProjectType"
                >
                  <el-option
                    v-for="item in projectTypeArr"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="isShow">
              <el-form-item label="需对接志愿者数" prop="peopleNumber">
                <el-input v-model="addsForm.peopleNumber" placeholder="请输入需对接志愿者数" clearable>
                  <template slot="append">人</template>
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="项目积分" prop="jiFen">
                <el-input
                  v-model="addsForm.jiFen"
                  :disabled="disableds"
                  placeholder="请输入积分，只能是正整数"
                  clearable
                >
                  <template
                    slot="prepend"
                    style="background-color: rgb(255,255,255);width:120px;"
                  >活动积分</template>
                  <template slot="append">积分/次</template>
                </el-input>
              </el-form-item>
              <el-tooltip placement="top">
                <div slot="content">19活动为每月17号至21号</div>
                <el-form-item content="19活动为每月17号至21号" placement="top-start" prop="nineteenJiFen">
                  <el-input
                    v-model="addsForm.nineteenJiFen"
                    placeholder="请输入积分，只能是正整数"
                    :disabled="disableds"
                    clearable
                  >
                    <template
                      slot="prepend"
                      style="background-color: #ffffff!important;width:120px;"
                    >19活动积分</template>
                    <template slot="append">积分/月</template>
                  </el-input>
                </el-form-item>
              </el-tooltip>
            </el-col>
            <el-col :span="24" v-if="isAreaCode">
              <el-form-item label="行政区划" prop="areaCodeArr">
                <el-cascader
                  placeholder="请选择行政区划"
                  :options="cityCountyOptions"
                  :props="zxProps"
                  :disabled="disabledAreaCode"
                  v-model="addsForm.areaCodeArr"
                  :change-on-select="true"
                  @change="cascaderExecutiveLevel"
                  clearable
                  filterable
                ></el-cascader>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="开始时间" prop="startTime">
                <el-date-picker
                  v-model="addsForm.startTime"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  :picker-options="addstartPicker"
                  clearable
                  placeholder="请选择开始日期"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="结束时间" prop="endTime">
                <el-date-picker
                  v-model="addsForm.endTime"
                  clearable
                  format="yyyy-MM-dd"
                  :picker-options="addendPicker"
                  value-format="yyyy-MM-dd"
                  placeholder="请选择结束日期"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="24" v-if="isEdit">
              <el-form-item label="项目状态" prop="projectState">
                <el-select
                  v-model="addsForm.projectState"
                  :disabled="disableds"
                  clearable
                  placeholder="请选择项目状态"
                >
                  <el-option
                    v-for="item in statusValueArr"
                    :key="item.code"
                    :label="item.name"
                    :value="item.code"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="项目介绍" prop="summary">
                <el-input
                  class="textarea-bottom"
                  type="textarea"
                  placeholder="请输入项目介绍内容，字数不可超过1000字"
                  maxlength="1000"
                  show-word-limit
                  rows="5"
                  clearable
                  v-model="addsForm.summary"
                ></el-input>
              </el-form-item>
            </el-col>
          </div>
          <div class="right">
            <div class="uploadImgs">
              <el-form-item
                class="same-picture"
                :class="{isError:hideError}"
                label="请上传项目封面"
                prop="imgArr"
              >
                <el-upload
                  :class="{hide:hideUpload}"
                  action="fakeaction"
                  list-type="picture-card"
                  :limit="limitImgCount"
                  :file-list="addsForm.imgArr"
                  :http-request="uploadServerEdit"
                  :on-success="unitPictrueUploadSuccess"
                  :on-preview="unitPictureCardPreview"
                  :before-upload="beforeAvatarUpload"
                  :on-remove="unitPictrueRemove"
                >
                  <i class="el-icon-plus"></i>
                </el-upload>
                <div class="tips" style="font-size:12px;">仅支持JPG、PNG格式，不超过5M</div>
                <el-dialog class="imgShowDemo" :visible.sync="dialogUnitVisible" append-to-body>
                  <img width="100%" :src="unitImageUrl" />
                </el-dialog>
              </el-form-item>
            </div>
          </div>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="canle('addsForm')">取 消</el-button>
        <el-button type="primary" @click="totalSubmit('addsForm')">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getAreaTree, fileUpImg } from "@/api/sys";
export default {
  props: {
    submitType: {
      type: String
    },
    title: {
      type: String
    },
    dictArrayObj: {
      type: Object
    },
    byIdData: {
      type: Object
    },
    areaCodeOptions: {
      type: Array
    },
    cityCountyOptions: {
      type: Array
    }
  },
  watch: {
    //类型
    submitType: {
      handler(newName, oldName) {
        if (newName) {
          this.submitType = newName;
        }
      },
      immediate: true, //立刻
      deep: true
    },
    //区域化数据
    cityCountyOptions: {
      handler(newName, oldName) {},
      immediate: true, //立刻
      deep: true
    },
    //标题
    title: {
      handler(newName, oldName) {
        if (newName) {
          this.titles = newName;
        }
      },
      immediate: true, //立刻
      deep: true
    },
    //字典
    dictArrayObj: {
      handler(newName, oldName) {
        this.initDictData(newName);
      },
      immediate: true, //立刻
      deep: true
    },
    //编辑时获取数据进行渲染
    byIdData: {
      handler(newName, oldName) {
        if (JSON.stringify(newName) != "{}") {
          this.initEditForm(newName);
        }
      },
      immediate: true, //立刻
      deep: true
    }
  },
  name: "basic", //基本信息
  data() {
    return {
      showWordLimit: true,
      addVisible: false, //弹出框是否显示隐藏
      titles: "", //标题
      limitImgCount: 1, //限制文件上传数量
      hideUpload: false, //隐藏上传图片按钮
      hideError: false, //隐藏报错信息
      dialogUnitVisible: false, //dialog预览图片属性
      unitImageUrl: "", //显示预览的图片
      isShow: true, //需对接志愿者数是否显示
      disableds: true, //禁止属性
      isEdit: false, //编辑是否放开form表单操作
      isLevel: false, //项目级别是否放开
      isAreaCode: false, //行政区划是否放开
      disabledAreaCode: true, //行政区划禁止属性
      addstartPicker: this.addStartPickers(), //开始时间限制
      addendPicker: this.addEndPickers(), //结束时间限制
      projectLevelArr: [], //项目等级数组
      // cityCountyOptions: [], //行政数组
      addsForm: {
        projectName: "", //项目名称
        category: "", //项目类型code
        projectLevel: "", //项目等级
        startTime: "", //开始时间
        endTime: "", //结束时间
        summary: "", //项目介绍
        jiFen: "", //项目积分
        nineteenJiFen: "", //19积分
        peopleNumber: "", //志愿者人数
        areaCode: "", //行政区划code
        areaCodeArr: [], //显示行政区划
        maxLengthValue: "", //最长的aredCode值
        projectState: "", //数据状态
        imgArr: [], //前端显示参数
        fmUrl: "" //后台参数
      },
      addsRules: {
        projectName: [
          { required: true, message: "请输入项目名称", trigger: "blur" }
        ],
        category: [
          { required: true, message: "请选择项目类型", trigger: "change" }
        ],
        areaCodeArr: [
          { required: true, message: "请选择行政区划", trigger: "change" }
        ],
        projectLevel: [
          { required: true, message: "请选择项目等级", trigger: "change" }
        ],
        jiFen: [
          { required: true, message: "请输入活动积分", trigger: "blur" },
          { validator: this.globalMethods.checkIntegerP, trigger: "blur" }
        ],
        nineteenJiFen: [
          { required: true, message: "请输入19活动积分", trigger: "blur" },
          { validator: this.globalMethods.checkIntegerP, trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "请选择开始时间", trigger: "change" }
        ],
        endTime: [
          { required: true, message: "请选择结束时间", trigger: "change" }
        ],
        projectState: [
          { required: true, message: "请选择项目状态", trigger: "change" }
        ],
        summary: [
          {
            required: true,
            message: "请输入项目介绍内容，字数不可超过1000字",
            trigger: "blur"
          },
          {
            validator: this.globalMethods.checkTextarea,
            trigger: "blur"
          }
        ],
        peopleNumber: [
          { required: true, message: "请输入需对接志愿者数", trigger: "blur" },
          { validator: this.globalMethods.checkIntegerP, trigger: "blur" }
        ],
        imgArr: [{ required: true, message: "请上传封面图片" }]
      },
      zxProps: {
        value: "id",
        label: "text"
      }
    };
  },
  created() {},
  methods: {
    //初始化编辑表单数据
    initEditForm(byIdData) {
      if (byIdData) {
        this.addsForm = {
          id: byIdData.id, //基本信息ID
          projectName: byIdData.projectName, //项目名称
          category: byIdData.category, //项目类型code
          projectLevel: byIdData.projectLevel, //项目等级
          peopleNumber: byIdData.peopleNumber, //请输入需对接志愿者数
          jiFen: byIdData.jiFen, //项目积分
          nineteenJiFen: byIdData.nineteenJiFen, //19积分
          startTime: byIdData.startTime, //开始时间
          endTime: byIdData.endTime, //结束时间
          SupstartTime: byIdData.SupstartTime, //自定义子项目开始时间
          SupendTime: byIdData.SupendTime, //自定义子项目结束时间
          summary: byIdData.summary, //项目介绍
          areaCode: byIdData.areaCode, //行政区划code
          areaCodeArr: byIdData.areaCodeArr, //行政区划code
          projectState: byIdData.projectState, //项目状态
          fmUrl: byIdData.fmUrl //相册封面
        };
      }
      if (byIdData.fmUrl != null && byIdData.fmUrl != "") {
        var tempImgArr = byIdData.fmUrl.split(","); //以逗号打撒
        var imgArr = [];
        if (tempImgArr.length > 0) {
          tempImgArr.forEach((item, i) => {
            imgArr.push({ url: item });
          });
        }
        this.addsForm.imgArr = imgArr; //前端展示用array
        this.addsForm.fmUrl = byIdData.fmUrl; //赋值后端字段string
        var limitImgCount = this.limitImgCount;
        if (imgArr.length > limitImgCount || imgArr.length == limitImgCount) {
          this.hideUpload = true; // 隐藏上传按钮
        }
      }
    },
    //总提交
    totalSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$emit("totalSubmit");
        } else {
          return false;
        }
      });
    },
    //关闭弹框重置表单
    eliminateDialog(type) {
      if (type) {
        this.$refs[type].resetFields();
      }
    },
    //取消弹框重置表单
    canle(formName) {
      this.$nextTick(() => {
        this.$refs[formName].resetFields();
      });
      this.addVisible = false;
    },
    //开始时间限制
    addStartPickers() {
      const self = this;
      return {
        disabledDate(time) {
          if (self.addsForm.SupstartTime) {
            return (
              time.getTime() <
                new Date(self.addsForm.SupstartTime).getTime() - 8.64e7 ||
              time.getTime() > new Date(self.addsForm.SupendTime).getTime() ||
              time.getTime() > new Date(self.addsForm.endTime).getTime()
            );
          } else {
            return time.getTime() > new Date(self.addsForm.endTime).getTime();
          }
        }
      };
    },
    //结束时间限制
    addEndPickers() {
      const self = this;
      return {
        disabledDate(time) {
          if (self.addsForm.SupendTime) {
            return (
              time.getTime() <
                new Date(self.addsForm.SupstartTime).getTime() - 8.64e7 ||
              time.getTime() > new Date(self.addsForm.SupendTime).getTime() ||
              new Date(self.addsForm.startTime).getTime() - 8.64e7 >
                time.getTime()
            );
          } else {
            return (
              new Date(self.addsForm.startTime).getTime() - 8.64e7 >
              time.getTime()
            );
          }
        }
      };
    },
    //相册上传
    unitPictrueUploadSuccess(response, file, fileList) {
      if (response.status == 1) {
        var imgArr = this.addsForm.imgArr;
        var tempArr = [];
        imgArr.push({
          url: response.data
        }); // push response.data进去 里面含3个字段
        if (imgArr.length > 0) {
          imgArr.forEach(item => {
            tempArr.push(item.url);
          });
        }
        this.addsForm.imgArr = imgArr; //更新data数据
        this.addsForm.fmUrl = tempArr.join(",");
        if (imgArr.length >= this.limitImgCount) {
          this.hideUpload = true; //隐藏上传按钮
          this.hideError = true; //隐藏报错信息
        }
      }
    },
    //编辑图片上传服务器
    uploadServerEdit(param) {
      var that = this;
      // 获取文件对象
      let file = param.file;
      // 创建一个HTML5的FileReader对象
      var reader = new FileReader();
      //创建一个img对象
      var img = new Image();
      let filename = param.filename;
      if (file) {
        reader.readAsDataURL(file);
      }
      var newUrl = null;
      reader.onload = e => {
        let base64Str = reader.result.split(",")[1];
        img.src = e.target.result;
        // base64地址图片加载完毕后执行
        img.onload = function() {
          // 缩放图片需要的canvas（也可以在DOM中直接定义canvas标签，这样就能把压缩完的图片不转base64也能直接显示出来）
          var canvas = document.createElement("canvas");
          var context = canvas.getContext("2d");
          // 图片原始尺寸
          var originWidth = this.width;
          var originHeight = this.height;
          // 最大尺寸限制，可通过设置宽高来实现图片压缩程度
          var maxWidth = that.globalData.albumWidth * 2,
            maxHeight = that.globalData.albumHeight * 2;
          // 目标尺寸
          var targetWidth = originWidth,
            targetHeight = originHeight;
          // 图片尺寸超过最大尺寸的限制
          if (originWidth > maxWidth || originHeight > maxHeight) {
            if (originWidth / originHeight > maxWidth / maxHeight) {
              // 更改宽度，按照宽度限定尺寸
              targetWidth = maxWidth;
              targetHeight = Math.round(
                maxWidth * (originHeight / originWidth)
              );
            } else {
              targetHeight = maxHeight;
              targetWidth = Math.round(
                maxHeight * (originWidth / originHeight)
              );
            }
          }
          //对图片进行缩放
          canvas.width = targetWidth;
          canvas.height = targetHeight;
          // 清除画布
          context.clearRect(0, 0, targetWidth, targetHeight);
          // 图片压缩
          context.drawImage(img, 0, 0, targetWidth, targetHeight);
          /*第一个参数是创建的img对象；第二三个参数是左上角坐标，后面两个是画布区域宽高*/
          //压缩后的base64文件
          newUrl = canvas.toDataURL("image/jpeg", 0.7);
          // 根据后台需求数据格式
          const formData = new FormData();
          // 文件对象
          let obj = {};
          if (that.submitType == "edit") {
            that.actionUrls = "/zcpa/actProject/updateCoverUrl";
            obj.projectId = that.byIdData.id;
            obj.coverUrl = newUrl;
          } else {
            that.actionUrls = "/zcpa-portal/www/uploadFileBase";
            obj.file = newUrl;
          }
          that.imageUpload(obj);
        };
      };
    },
    //图片上传
    imageUpload(obj) {
      fileUpImg(this.actionUrls, obj).then(response => {
        if (response.status == 1) {
          var imgArr = this.addsForm.imgArr;
          var tempArr = [];
          imgArr.push({ url: response.data }); // push response.data进去 里面含3个字段
          if (imgArr.length > 0) {
            imgArr.forEach(item => {
              tempArr.push(item.url);
            });
          }
          this.addsForm.imgArr = imgArr; //更新data数据
          this.addsForm.fmUrl = tempArr.join(",");
          if (imgArr.length >= this.limitImgCount) {
            this.hideUpload = true; //隐藏上传按钮
            this.hideError = true; //隐藏报错信息
          }
        } else {
          this.$message({
            message: response.message,
            type: "warning"
          });
        }
      });
    },
    //限制用户上传的图片格式和大小
    beforeAvatarUpload(file) {
      var isRightType = /^image\/(jpeg|png|jpg)$/.test(file.type);
      var imgSize = file.size / 1024 / 1024 < 5;
      if (!isRightType) {
        this.$message.warning("上传头像图片只能是 jpg、jpeg、png 格式!");
      }
      if (!imgSize) {
        this.$message.warning("上传头像图片大小不能超过 5MB!");
      }
      return isRightType && imgSize;
    },
    // 移除图片之前
    unitPictrueBeforeRemove(file, fileList) {
      console.log(file);
      console.log(fileList);
    },
    // 移除图片
    unitPictrueRemove(file, fileList) {
      console.log(file);
      console.log(fileList);
      var imgArrTemp = this.addsForm.imgArr;
      if (file.status == "success") {
        imgArrTemp.forEach((item, i) => {
          var url = file.url; // 接口返回值没有逗号，加一个逗号
          if (item.url == url) {
            imgArrTemp.splice(i, 1);
          }
        });
        this.addsForm.imgArr = imgArrTemp; //更新data imgArr 数据
        var tempArr = [];
        if (imgArrTemp.length > 0) {
          imgArrTemp.forEach((item, i) => {
            tempArr.push(item.url);
          });
        }
        this.addsForm.fmUrl = tempArr.join(","); //更新data pictureStr 数据
        if (imgArrTemp.length < this.limitImgCount) {
          this.hideUpload = false; //显示上传按钮
          this.hideError = false; //隐藏报错信息
        }
      }
    },
    // 图片预览
    unitPictureCardPreview(file) {
      this.unitImageUrl = file.url;
      this.dialogUnitVisible = true;
    },
    //项目类型
    changeProjectType(value) {
      var obj = {};
      obj = this.projectTypeArr.find(item => {
        //找到匹配的数据
        return item.code == value;
      });
      if (value) {
        this.addsForm.category = value; //code
        this.addsForm.categoryType = obj.name; //赋值汉字
        if (value == 1) {
          this.isShow = false;
        } else {
          this.isShow = true;
        }
      }
    },
    //行政级别级联选择器
    cascaderExecutiveLevel(value) {
      var tempTextArr = [];
      // 根据选中的code值区查询相应的中文
      var tepmTreeArr = this.globalMethods.getCascaderObj(
        value,
        this.cityCountyOptions
      );
      if (tepmTreeArr.length > 0) {
        tepmTreeArr.forEach((item, i) => {
          tempTextArr.push(item.text);
        });
      }
      var maxLengthValue = this.globalMethods.findLongestWord([].concat(value));
      this.addsForm.areaCode = maxLengthValue; //code
      console.log(this.addsForm.areaCode);
    },
    //初始化字典数据
    initDictData() {
      this.projectTypeArr = this.dictArrayObj.projectValue; //47项目类型
      this.projectLevelArr = this.dictArrayObj.ProjectLevelValue; //48项目等级
      this.statusValueArr = this.dictArrayObj.statusValue; //50数据状态
    }
  }
};
</script>

<style lang="less" scoped>
.declare {
  /deep/ .el-dialog__footer {
    text-align: right;
    padding-right: 60px;
  }
}
.addForm {
  /deep/ .el-row {
    margin-bottom: 0;
  }
  /deep/ .el-form-item {
    margin-bottom: 24px;
  }
  /deep/.el-input-group__prepend {
    width: 120px;
  }
  /deep/ .el-select {
    width: 100%;
  }
  /deep/ .el-form-item__label {
    line-height: 40px;
  }
  /deep/ .el-date-editor.el-input {
    width: 100%;
  }
  /deep/ .picture {
    /deep/ .el-form-item__error {
      display: none;
    }
  }
  /deep/ .input-right .el-input__inner {
    padding-right: 90px;
  }
  /deep/ .el-textarea .el-input__count {
    background: transparent;
    bottom: -30px;
  }
  .form-item-box {
    width: 100%;
    display: flex;
    .left {
      flex: 1;
    }
    .right {
      float: right;
      width: 350px;
      padding-left: 50px;
    }
  }
  .uploadImgs {
    text-align: center;
    .hide {
      /deep/ .el-upload--picture-card {
        display: none;
      }
    }
    /deep/ .el-form-item__error {
      margin-left: 60px;
    }
    /deep/ .el-form-item__label {
      width: 100% !important;
      display: block;
      text-align: center;
    }
    /deep/ .el-form-item__content {
      margin-left: 0 !important;
    }
  }
  .uploadImgs /deep/ .el-upload--picture-card {
    width: 200px;
    height: 200px;
  }
  .uploadImgs /deep/ .el-upload-list--picture-card .el-upload-list__item {
    width: 200px;
    height: 200px;
  }

  .uploadImgs
    /deep/
    .el-upload-list--picture-card
    .el-upload-list__item-thumbnail {
    object-fit: cover;
    width: 100%;
    height: 100%;
  }

  .uploadImgs /deep/ .el-upload--picture-card i {
    line-height: 200px;
  }

  .imgShowDemo /deep/ .el-dialog {
    width: 520px;
    height: 520px !important;
    overflow: hidden;
    margin-top: 5% !important;
  }
  .imgShowDemo /deep/ .el-dialog__header {
    height: 50px;
  }
  .imgShowDemo /deep/ .el-dialog__body {
    width: 520px;
    height: 480px;
    margin: 0 auto;
  }
  .imgShowDemo /deep/ .el-dialog .el-dialog__body img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}

.isError {
  /deep/ .el-form-item__error {
    display: none;
  }
}
</style>
