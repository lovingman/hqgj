(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-22811c36"],{6797:function(e,t,r){"use strict";r.d(t,"d",function(){return o}),r.d(t,"e",function(){return a}),r.d(t,"b",function(){return i}),r.d(t,"c",function(){return c}),r.d(t,"a",function(){return u});var n=r("b775");function o(e){return Object(n["a"])({url:"/zcpa-portal/system/getAreaTree",method:"get",params:e})}function a(e){return Object(n["a"])({url:"/zcpa-portal/dict/getByCategoryIds",method:"get",params:{categoryIds:e}})}function i(e,t){var r=new FormData;return r.append("file",e.file),Object(n["a"])({method:"post",url:t,headers:{"Content-Type":"multipart/form-data"},data:r})}function c(e,t){return Object(n["a"])({url:t,method:"get",params:e,responseType:"blob"})}function u(e,t){return Object(n["a"])({url:e,method:"post",data:t,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}},8930:function(e,t,r){"use strict";r.d(t,"h",function(){return o}),r.d(t,"b",function(){return a}),r.d(t,"i",function(){return i}),r.d(t,"c",function(){return c}),r.d(t,"e",function(){return u}),r.d(t,"a",function(){return s}),r.d(t,"d",function(){return l}),r.d(t,"f",function(){return d}),r.d(t,"g",function(){return m});var n=r("b775");function o(e){return Object(n["a"])({url:"/zcpa-portal/users/page",method:"get",params:e})}function a(e){return Object(n["a"])({url:"/zcpa-portal/users/create",method:"post",data:e,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function i(e){return Object(n["a"])({url:"/zcpa-portal/users/update",method:"post",data:e,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function c(e){return Object(n["a"])({url:"/zcpa-portal/users/deleteById",method:"get",params:{id:e}})}function u(e){return Object(n["a"])({url:"/zcpa-portal/users/getById",method:"get",params:{id:e}})}function s(e){return Object(n["a"])({url:"/zcpa-portal/users/assignRoles",method:"post",data:e,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function l(){return Object(n["a"])({url:"/zcpa-portal/users/getAllRoles",method:"get",params:{}})}function d(e){return Object(n["a"])({url:"/zcpa-portal/users/getMyRoles",method:"get",params:{userId:e}})}function m(e){return Object(n["a"])({url:"/zcpa-portal/users/initPwd",method:"post",data:e,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}},"94fb":function(e,t,r){"use strict";r.d(t,"e",function(){return o}),r.d(t,"a",function(){return a}),r.d(t,"f",function(){return i}),r.d(t,"b",function(){return c}),r.d(t,"c",function(){return u}),r.d(t,"d",function(){return s});var n=r("b775");function o(e){return Object(n["a"])({url:"/zcpa-portal/corp/page",method:"get",params:e})}function a(e){return Object(n["a"])({url:"/zcpa-portal/corp/create",method:"post",data:e,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function i(e){return Object(n["a"])({url:"/zcpa-portal/corp/update",method:"post",data:e,transformRequest:[function(e){var t="";for(var r in e)t+=encodeURIComponent(r)+"="+encodeURIComponent(e[r])+"&";return t}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function c(e){return Object(n["a"])({url:"/zcpa-portal/corp/deleteById",method:"get",params:{id:e}})}function u(e){return Object(n["a"])({url:"/zcpa-portal/corp/getById",method:"get",params:{id:e}})}function s(e){return Object(n["a"])({url:"/zcpa-portal/corp/getList",method:"get",params:{name:e}})}},f2c0:function(e,t,r){"use strict";r.r(t);var n=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-card",{attrs:{shadow:"never"}},[r("el-container",[r("el-header",{staticStyle:{height:"1.5rem"}}),r("el-main",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{"element-loading-text":"请求中","element-loading-spinner":"el-icon-loading"}},[r("el-form",{ref:"ruleForm",staticStyle:{width:"50%"},attrs:{rules:e.rules,model:e.form,"label-width":"80px"}},[r("div",{staticClass:"el-form-item el-form-item--medium"},[r("div",{staticClass:"section_title"},[e._v("基本信息")])]),r("el-form-item",{attrs:{label:"姓名",prop:"name"}},[r("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),r("el-form-item",{attrs:{label:"性别",prop:"sex"}},[r("el-radio-group",{model:{value:e.form.sex,callback:function(t){e.$set(e.form,"sex",t)},expression:"form.sex"}},e._l(e.dict["4"],function(t){return r("el-radio",{key:t.code,attrs:{label:t.code}},[e._v(e._s(t.name))])}),1)],1),r("el-form-item",{attrs:{label:"身份证号",prop:"idCard"}},[r("el-input",{model:{value:e.form.idCard,callback:function(t){e.$set(e.form,"idCard",t)},expression:"form.idCard"}})],1),r("el-form-item",{attrs:{label:"组织",prop:"corpId"}},[r("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择所在组织",filterable:"",remote:"","reserve-keyword":"","remote-method":e.corpQuery,loading:e.loading},model:{value:e.form.corpId,callback:function(t){e.$set(e.form,"corpId",t)},expression:"form.corpId"}},e._l(e.corps,function(e){return r("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),r("div",{staticClass:"el-form-item el-form-item--medium"},[r("div",{staticClass:"section_title"},[e._v("联系方式 ")])]),r("el-form-item",{attrs:{label:"手机号",prop:"mobile"}},[r("el-input",{model:{value:e.form.mobile,callback:function(t){e.$set(e.form,"mobile",t)},expression:"form.mobile"}})],1),r("el-form-item",{attrs:{label:"邮箱",prop:"email"}},[r("el-input",{model:{value:e.form.email,callback:function(t){e.$set(e.form,"email",t)},expression:"form.email"}})],1),r("el-form-item",{attrs:{label:"状态",prop:"status"}},[r("el-switch",{attrs:{name:"status","active-text":"正常","inactive-text":"注销","active-value":"1","inactive-value":"2"},model:{value:e.form.status,callback:function(t){e.$set(e.form,"status",t)},expression:"form.status"}})],1),r("el-form-item",[r("el-button",{on:{click:e.back}},[e._v("取消")]),r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("ruleForm")}}},[e._v("保存")])],1)],1)],1)],1)],1)],1)},o=[],a=r("db72"),i=r("8930"),c=r("6797"),u=r("2f62"),s=r("94fb"),l={data:function(){return{dict:{},corps:[],loading:!1,form:{userId:"",name:"",corpId:"",deptId:"",account:"",password:"",mobile:"",email:"",sex:"",idCard:"",birthday:"",status:"1"},rules:{name:[{required:!0,message:"请输入姓名",trigger:"blur"},{min:2,max:50,message:"长度在 2 到 20 个字符",trigger:"blur"}],corpId:[{required:!0,message:"请选择所属单位",trigger:"change"}],account:[{required:!0,message:"请输入账号",trigger:"blur"},{min:3,max:30,message:"长度在 3 到 30 个字符",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:6,max:100,message:"长度在 6 到 100 个字符",trigger:"blur"}]},statusData:[{code:"1",name:"正常"},{code:"2",name:"注销"}]}},computed:Object(a["a"])({},Object(u["b"])(["userProp"])),created:function(){this.dictQuery(),this.corpQuery(),this.load()},methods:{load:function(){var e=this;this.loading=!0,this.id=this.$route.query.id,Object(i["e"])(this.id).then(function(t){e.loading=!1,e.form=t.data,e.corps.push({value:t.data.corpId,label:t.data.corpName})})},dictQuery:function(){var e=this;Object(c["e"])("4").then(function(t){e.dict=t.data})},corpQuery:function(e){var t=this;Object(s["d"])(e).then(function(e){t.corps=e.data})},submitForm:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return!1;t.loading=!0,Object(i["i"])({jsons:JSON.stringify(t.form)}).then(function(e){t.loading=!1,t.$confirm(e.message+" 是否返回?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"success",center:!0}).then(function(){t.back()}).catch(function(){})}).catch(function(e){t.loading=!1})})},back:function(){this.$router.push({path:"/sys/user"})}}},d=l,m=r("2877"),p=Object(m["a"])(d,n,o,!1,null,null,null);t["default"]=p.exports}}]);
//# sourceMappingURL=chunk-22811c36.1432157e.js.map