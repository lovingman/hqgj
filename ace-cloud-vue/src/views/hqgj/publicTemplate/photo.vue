<template>
  <div class="photo">
    <el-row>
      <el-form
        :model="photoForm"
        :inline="true"
        ref="photoForm"
        :rules="photoRules"
        label-width="120px"
        class="innerOrgRuleForm"
      >
        <el-form-item label="封面：" prop="imgArr" :class="{isError:hideError}">
          <el-upload
            :class="{hide:hideUpload}"
            action="fakeaction"
            list-type="picture-card"
            :limit="limitImgCount"
            :file-list="photoForm.imgArr"
            :http-request="uploadServerEdit"
            :on-success="unitPictrueUploadSuccess"
            :on-preview="unitPictureCardPreview"
            :before-upload="beforeAvatarUpload"
            :on-remove="unitPictrueRemove"
          >
            <i class="el-icon-plus"></i>
            <div class="el-upload__tip" slot="tip">支持jpg.png，大小不超过10M</div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt />
          </el-dialog>
        </el-form-item>
      </el-form>
    </el-row>
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
    getData: {
      type: Object
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
    //byid数据
    getData: {
      handler(newName, oldName) {
        if (JSON.stringify(newName) != "{}") {
          this.initEditForm(newName);
        }
      },
      immediate: true, //立刻
      deep: false
    }
  },
  data() {
    return {
      hideError: false, //隐藏报错信息
      hideUpload: false, //隐藏上传按钮
      limitImgCount: 1, //限制文件上传数量
      dialogVisible: false, //预览弹窗是否显示
      dialogImageUrl: "", //是否显示预览图片
      photoForm: {
        imgArr: [], //图片数组
        fmUrl: "" //后台参数
      },
      photoRules: {
        imgArr: [{ required: true, message: "请上传封面照片" }]
      }
    };
  },
  methods: {
    initEditForm(getData) {
      console.log(getData);
      if (getData) {
        //图片格式化
        if (getData.fmUrl != null && getData.fmUrl != "") {
          var tempImgArr = getData.fmUrl.split(","); //以逗号打撒
          var imgArr = [];
          if (tempImgArr.length > 0) {
            tempImgArr.forEach((item, i) => {
              imgArr.push({ url: item });
            });
          }
          this.photoForm.imgArr = imgArr; //前端展示用array
          this.photoForm.fmUrl = getData.fmUrl; //赋值后端字段string
          var limitImgCount = this.limitImgCount;
          if (imgArr.length > limitImgCount || imgArr.length == limitImgCount) {
            this.hideUpload = true; // 隐藏上传按钮
          }
        }
      }
    },
    //相册上传
    unitPictrueUploadSuccess(response, file, fileList) {
      if (response.status == 1) {
        var imgArr = this.photoForm.imgArr;
        var tempArr = [];
        imgArr.push({
          url: response.data
        }); // push response.data进去 里面含3个字段
        if (imgArr.length > 0) {
          imgArr.forEach(item => {
            tempArr.push(item.url);
          });
        }
        this.photoForm.imgArr = imgArr; //更新data数据
        this.photoForm.fmUrl = tempArr.join(",");
        if (imgArr.length >= this.limitImgCount) {
          this.hideUpload = true; //隐藏上传按钮
          this.hideError = true; //隐藏报错信息
        }
      }
    },
    //限制上传格式和大小
    beforeAvatarUpload(file) {
      let isRightType = /^image\/(jpeg|png|jpg)$/.test(file.type);
      let isLt10M = file.size / 1024 / 1024 < 10;
      if (!isRightType) {
        this.$message.warning("上传头像图片只能是 jpg、jpeg、png 格式!");
      }
      if (!isLt10M) {
        this.$message.error("上传文件大小不能超过10MB哦!");
        return isRightType && isLt10M;
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
            that.actionUrls = "/hqgj/actProject/updateCoverUrl";
            obj.projectId = that.byIdData.id;
            obj.coverUrl = newUrl;
          } else {
            that.actionUrls = "/hqgj-portal/www/uploadFileBase";
            obj.file = newUrl;
          }
          that.imageUpload(obj);
        };
      };
    },
    //图片上传
    imageUpload(obj) {
      if (obj) {
        fileUpImg(this.actionUrls, obj).then(response => {
          if (response.status == 1) {
            var imgArr = this.photoForm.imgArr;
            var tempArr = [];
            imgArr.push({ url: response.data }); // push response.data进去 里面含3个字段
            if (imgArr.length > 0) {
              imgArr.forEach(item => {
                tempArr.push(item.url);
              });
            }
            this.photoForm.imgArr = imgArr; //更新data数据
            this.photoForm.fmUrl = tempArr.join(",");
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
      }
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
      var imgArrTemp = this.photoForm.imgArr;
      if (file.status == "success") {
        imgArrTemp.forEach((item, i) => {
          var url = file.url; // 接口返回值没有逗号，加一个逗号
          if (item.url == url) {
            imgArrTemp.splice(i, 1);
          }
        });
        this.photoForm.imgArr = imgArrTemp; //更新data imgArr 数据
        var tempArr = [];
        if (imgArrTemp.length > 0) {
          imgArrTemp.forEach((item, i) => {
            tempArr.push(item.url);
          });
        }
        this.photoForm.fmUrl = tempArr.join(","); //更新data pictureStr 数据
        if (imgArrTemp.length < this.limitImgCount) {
          this.hideUpload = false; //显示上传按钮
          this.hideError = false; //隐藏报错信息
        }
      }
    },
    // 图片预览
    unitPictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  }
};
</script>

<style lang="less" scoped>
.photo {
  /deep/ .el-form-item {
    width: 100%;
  }
  /deep/ .el-form-item__content {
    width: calc(~"100% - 120px");
  }
  .hide {
    /deep/ .el-upload--picture-card {
      display: none;
    }
  }
  .isError {
    /deep/ .el-form-item__error {
      display: none;
    }
  }
}
</style>