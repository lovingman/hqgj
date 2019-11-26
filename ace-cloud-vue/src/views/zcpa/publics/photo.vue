<template>
  <div class="uploadImgs">
    <el-form-item class="same-picture" :class="{picture:hides}" label="请上传项目封面" prop="imgArr">
      <el-upload
        :class="{hide:hideUpload}"
        action="fakeaction"
        list-type="picture-card"
        :limit="limitImgCount"
        :file-list="imgObj.imgArr"
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
</template>

<script>
import { fileUpImg } from "@/api/sys";
export default {
  name: "photo",
  props: {
    submitType: {
      type: String
    },
    form: {
      type: Object
    }
  },
  watch: {
    submitType: {
      handler(newName, oldName) {
        if (newName) {
          this.submitType = newName;
        }
      },
      immediate: true, //立刻
      deep: true
    },
    //form数据
    "form.fmUrl": {
      handler(newName, oldName) {
        console.log(newName);
        if (newName) {
          console.log(newName);
          this.initEditForm({ fmUrl: newName });
        }
      },
      immediate: false, //立刻
      deep: true
    },
    "imgObj.imgArr": {
      handler(newName, oldName) {
        var img = newName[0];
        if (img && img.url) {
          this.form.fmUrl = img.url;
        } else {
          this.form.fmUrl = "";
        }
      },
      immediate: false, //立刻
      deep: true
    }
  },
  data() {
    return {
      limitImgCount: 1, //限制文件上传数量
      hideUpload: false, //隐藏上传图片按钮
      dialogUnitVisible: false, //dialog预览图片属性
      unitImageUrl: "", //显示预览的图片
      hides: false, //隐藏报错信息
      imgObj: {
        imgArr: [], //前端显示参数
        fmUrl: "" //后台参数
      }
    };
  },
  created() {
    this.initEditForm(this.form.fmUrl);
  },
  methods: {
    //初始化编辑表单数据
    initEditForm(data) {
      var fmUrl = data.fmUrl;
      //图片格式化
      if (fmUrl != null && fmUrl != "") {
        var tempImgArr = fmUrl.split(","); //以逗号打撒
        var imgArr = [];
        if (tempImgArr.length > 0) {
          tempImgArr.forEach((item, i) => {
            imgArr.push({ url: item });
          });
        }
        this.imgObj.imgArr = imgArr; //前端展示用array
        this.imgObj.fmUrl = fmUrl; //赋值后端字段string
        var limitImgCount = this.limitImgCount;
        if (imgArr.length > limitImgCount || imgArr.length == limitImgCount) {
          this.hideUpload = true; // 隐藏上传按钮
        }
      }
    },
    //相册上传
    unitPictrueUploadSuccess(response, file, fileList) {
      if (response.status == 1) {
        var imgArr = this.imgObj.imgArr;
        var tempArr = [];
        imgArr.push({
          url: response.data
        }); // push response.data进去 里面含3个字段
        if (imgArr.length > 0) {
          imgArr.forEach(item => {
            tempArr.push(item.url);
          });
        }
        this.imgObj.imgArr = imgArr; //更新data数据
        this.imgObj.pictureStr = tempArr.join(",");
        if (imgArr.length >= this.limitImgCount) {
          this.hideUpload = true; //隐藏上传按钮
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
            obj.projectId = that.form.id;
            obj.coverUrl = newUrl;
          } else {
            that.actionUrls = "/portal/www/uploadFileBase";
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
          var imgArr = this.imgObj.imgArr;
          var tempArr = [];
          imgArr.push({ url: response.data }); // push response.data进去 里面含3个字段
          if (imgArr.length > 0) {
            imgArr.forEach(item => {
              tempArr.push(item.url);
            });
          }
          this.imgObj.imgArr = imgArr; //更新data数据
          this.imgObj.fmUrl = tempArr.join(",");
          if (imgArr.length >= this.limitImgCount) {
            this.hideUpload = true; //隐藏上传按钮
            this.hides = true;
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
      var imgArrTemp = this.imgObj.imgArr;
      if (file.status == "success") {
        imgArrTemp.forEach((item, i) => {
          var url = file.url; // 接口返回值没有逗号，加一个逗号
          if (item.url == url) {
            imgArrTemp.splice(i, 1);
          }
        });
        this.imgObj.imgArr = imgArrTemp; //更新data imgArr 数据
        var tempArr = [];
        if (imgArrTemp.length > 0) {
          imgArrTemp.forEach((item, i) => {
            tempArr.push(item.url);
          });
        }
        this.imgObj.fmUrl = tempArr.join(","); //更新data pictureStr 数据
        if (imgArrTemp.length < this.limitImgCount) {
          this.hideUpload = false; //显示上传按钮
        }
      }
    },
    // 图片预览
    unitPictureCardPreview(file) {
      this.unitImageUrl = file.url;
      this.dialogUnitVisible = true;
    }
  }
};
</script>

<style lang="less" scoped>
.uploadImgs {
  .hide {
    height: 220px;
    overflow: hidden;
    /deep/ .el-upload--picture-card {
      display: none;
    }
    /deep/ .el-upload-list--picture-card {
      height: 220px;
      overflow: hidden;
    }
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
</style>