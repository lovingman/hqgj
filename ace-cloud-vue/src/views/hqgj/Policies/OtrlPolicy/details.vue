<template>
    <div class="container">
        <div class="title">
            <span>政策服务详情</span>
            <el-button @click="back" style="float:right;border-radius:5px;" type="primary">返回</el-button>
        </div>
        <div class="handle-box">
            <el-form :model="serviceForm" class="demo-ruleForm" label-width="100px">
                <el-form-item label="标题名称:" prop="title">
                    <span>{{serviceForm.title}}</span>
                </el-form-item>
                <el-form-item label="来源:" prop="source">
                    <span>{{serviceForm.source}}</span>
                </el-form-item>
                <el-form-item label="附件:" prop="basicAnnexes">
          <span v-for="item in serviceForm.basicAnnexes">
            {{item.fileName+"."+item.fileURL.substr(item.fileURL.lastIndexOf(".") + 1)}}
            <br/>
          </span>
                </el-form-item>
                <el-form-item label="创建时间:" prop="createDate">
                    <span>{{serviceForm.createDate}}</span>
                </el-form-item>
                <el-form-item label="内容:" prop="content" style="width: 95%">
                    <div class="ckeditor">
                        <div id="toolbar-container"></div>
                        <!-- 编辑器容器 -->
                        <div id="editor"></div>
                    </div>
                    <!--<span v-html="serviceForm.content" style="word-wrap: break-word;word-break: break-all;">{{serviceForm.content}}</span>-->
                </el-form-item>
                <!--<el-form-item>-->
                <!--<el-button @click="back" type="primary">返回</el-button>-->
                <!--</el-form-item>-->
            </el-form>
        </div>
    </div>
</template>

<script>
    import {getPolicyById} from "@/api/hqgj/Policies";
    import CKEditor from "@ckeditor/ckeditor5-build-decoupled-document";

    export default {
        name: "details",
        data() {
            return {
                //数据
                serviceForm: {
                    title: "",
                    source: "",
                    content: "",
                    basicAnnexes: []
                }
            };
        },
        created() {
            this.getDetails();
        },
        mounted() {
            this.initCKEditor();
        },
        methods: {
            getDetails() {
                this.id = this.$route.query.id;
                getPolicyById(this.id).then(response => {
                    this.serviceForm = response.data;
                    this.editor.setData(this.serviceForm.content);
                });
            },
            //富文本编辑器
            initCKEditor() {
                CKEditor.create(document.querySelector("#editor"))
                    .then(editor => {
                        editor.isReadOnly = true; //将编辑器设为只读
                        this.editor = editor;
                    })
                    .catch(error => {
                        console.error(error);
                    });
            },
            back() {
                this.$router.push({
                    path: "/hqgj/Policies/OtrlPolicy"
                });
            }
            // change(val) {
            //   console.log(val);
            // }
        }
    };
</script>

<style scoped>
    .container {
        background-color: #fff;
    }

    .title {
        line-height: 30px;
        font-size: 16px;
        font-weight: bold;
        padding: 20px 30px;
        border-bottom: 1px solid #eee;
    }

    .handle-box {
        padding-top: 40px;
        padding-bottom: 40px;
    }

    .ckeditor {
        width: 100%;
        border: 1px solid #ddd;
    }

    .ckeditor /deep/ .ck-editor__editable {
        height: 480px;
    }
</style>
