(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-09619579"],{6797:function(e,t,r){"use strict";r.d(t,"d",function(){return a}),r.d(t,"e",function(){return o}),r.d(t,"b",function(){return i}),r.d(t,"c",function(){return c}),r.d(t,"a",function(){return u});var n=r("b775");function a(e){return Object(n["a"])({url:"/zcpa-portal/system/getAreaTree",method:"get",params:e})}function o(e){return Object(n["a"])({url:"/zcpa-portal/dict/getByCategoryIds",method:"get",params:{categoryIds:e}})}function i(e,t){var r=new FormData;return r.append("file",e.file),Object(n["a"])({method:"post",url:t,headers:{"Content-Type":"multipart/form-data"},data:r})}function c(e,t){return Object(n["a"])({url:t,method:"get",params:e,responseType:"blob"})}function u(e,t){return Object(n["a"])({url:e,method:"post",data:t,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}},"924f":function(e,t,r){"use strict";r.r(t);var n=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-card",{attrs:{shadow:"never"}},[r("el-container",[r("el-header",{staticStyle:{height:"1.5rem"}},[r("div",{staticClass:"section_title"},[e._v("基本信息")])]),r("el-main",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{"element-loading-text":"请求中","element-loading-spinner":"el-icon-loading"}},[r("el-form",{ref:"ruleForm",staticStyle:{width:"50%"},attrs:{rules:e.rules,model:e.form,"label-width":"80px"}},[r("el-form-item",{attrs:{label:"名称",prop:"name"}},[r("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),r("el-form-item",{attrs:{label:"行政区划",prop:"areaCode"}},[r("el-cascader",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择行政区划",options:e.areaCodeOptions,props:e.areaCodeProps,clearable:"","change-on-select":"",filterable:""},model:{value:e.form.areaCode,callback:function(t){e.$set(e.form,"areaCode",t)},expression:"form.areaCode"}})],1),r("el-form-item",{attrs:{label:"归属单位",prop:"pid"}},[r("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",remote:"","reserve-keyword":"",placeholder:"请输入归属单位名称","remote-method":e.pidQuery,loading:e.loading},model:{value:e.form.pid,callback:function(t){e.$set(e.form,"pid",t)},expression:"form.pid"}},e._l(e.pids,function(e){return r("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),r("el-form-item",[r("el-button",{on:{click:e.back}},[e._v("取消")]),r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("ruleForm")}}},[e._v("保存")])],1)],1)],1)],1)],1)],1)},a=[],o=r("db72"),i=r("94fb"),c=r("6797"),u=r("2f62"),d={data:function(){return{dict:{},loading:!1,areaCodeOptions:[],areaCodeProps:{value:"id",label:"text"},pids:[],form:{id:"",name:"",areaCode:[],pid:""},rules:{name:[{required:!0,message:"请输入单位名称",trigger:"blur"},{min:2,max:50,message:"长度在 2 到 50 个字符",trigger:"blur"}],areaCode:[{required:!0,message:"请选择行政区划",trigger:"change"}],pid:[{required:!0,message:"请选择归属单位",trigger:"change"}]}}},computed:Object(o["a"])({},Object(u["b"])(["userProp"])),created:function(){this.AreaCodeQuery(),this.pidQuery()},methods:{AreaCodeQuery:function(){var e=this,t=this.$store.getters.userProp;Object(c["d"])({pid:t.areaCode,type:3,hasSelf:"true"}).then(function(t){e.areaCodeOptions=t.data})},submitForm:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return!1;t.loading=!0,t.form.areaCode=t.form.areaCode.pop(),Object(i["a"])({jsons:JSON.stringify(t.form)}).then(function(e){t.loading=!1,t.$confirm(e.message+" 是否返回?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"success",center:!0}).then(function(){t.back()}).catch(function(){})}).catch(function(){t.loading=!1})})},back:function(){this.$router.push({path:"/sys/corp"})},pidQuery:function(e){var t=this;Object(i["d"])(e).then(function(e){t.pids=e.data})}}},l=d,s=r("2877"),p=Object(s["a"])(l,n,a,!1,null,null,null);t["default"]=p.exports},"94fb":function(e,t,r){"use strict";r.d(t,"e",function(){return a}),r.d(t,"a",function(){return o}),r.d(t,"f",function(){return i}),r.d(t,"b",function(){return c}),r.d(t,"c",function(){return u}),r.d(t,"d",function(){return d});var n=r("b775");function a(e){return Object(n["a"])({url:"/zcpa-portal/corp/page",method:"get",params:e})}function o(e){return Object(n["a"])({url:"/zcpa-portal/corp/create",method:"post",data:e,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function i(e){return Object(n["a"])({url:"/zcpa-portal/corp/update",method:"post",data:e,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function c(e){return Object(n["a"])({url:"/zcpa-portal/corp/deleteById",method:"get",params:{id:e}})}function u(e){return Object(n["a"])({url:"/zcpa-portal/corp/getById",method:"get",params:{id:e}})}function d(e){return Object(n["a"])({url:"/zcpa-portal/corp/getList",method:"get",params:{name:e}})}}}]);
//# sourceMappingURL=chunk-09619579.fd3a8f67.js.map