(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-190e18e2"],{"248f":function(t,e,r){},6797:function(t,e,r){"use strict";r.d(e,"d",function(){return n}),r.d(e,"e",function(){return o}),r.d(e,"b",function(){return s}),r.d(e,"c",function(){return i}),r.d(e,"a",function(){return l});var a=r("b775");function n(t){return Object(a["a"])({url:"/zcpa-portal/system/getAreaTree",method:"get",params:t})}function o(t){return Object(a["a"])({url:"/zcpa-portal/dict/getByCategoryIds",method:"get",params:{categoryIds:t}})}function s(t,e){var r=new FormData;return r.append("file",t.file),Object(a["a"])({method:"post",url:e,headers:{"Content-Type":"multipart/form-data"},data:r})}function i(t,e){return Object(a["a"])({url:e,method:"get",params:t,responseType:"blob"})}function l(t,e){return Object(a["a"])({url:t,method:"post",data:e,transformRequest:[function(t){var e="";for(var r in t)e+=encodeURIComponent(r)+"="+encodeURIComponent(t[r])+"&";return e}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}},8930:function(t,e,r){"use strict";r.d(e,"h",function(){return n}),r.d(e,"b",function(){return o}),r.d(e,"i",function(){return s}),r.d(e,"c",function(){return i}),r.d(e,"e",function(){return l}),r.d(e,"a",function(){return u}),r.d(e,"d",function(){return c}),r.d(e,"f",function(){return d}),r.d(e,"g",function(){return p});var a=r("b775");function n(t){return Object(a["a"])({url:"/zcpa-portal/users/page",method:"get",params:t})}function o(t){return Object(a["a"])({url:"/zcpa-portal/users/create",method:"post",data:t,transformRequest:[function(t){var e="";for(var r in t)e+=encodeURIComponent(r)+"="+encodeURIComponent(t[r])+"&";return e}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function s(t){return Object(a["a"])({url:"/zcpa-portal/users/update",method:"post",data:t,transformRequest:[function(t){var e="";for(var r in t)e+=encodeURIComponent(r)+"="+encodeURIComponent(t[r])+"&";return e}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function i(t){return Object(a["a"])({url:"/zcpa-portal/users/deleteById",method:"get",params:{id:t}})}function l(t){return Object(a["a"])({url:"/zcpa-portal/users/getById",method:"get",params:{id:t}})}function u(t){return Object(a["a"])({url:"/zcpa-portal/users/assignRoles",method:"post",data:t,transformRequest:[function(t){var e="";for(var r in t)e+=encodeURIComponent(r)+"="+encodeURIComponent(t[r])+"&";return e}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function c(){return Object(a["a"])({url:"/zcpa-portal/users/getAllRoles",method:"get",params:{}})}function d(t){return Object(a["a"])({url:"/zcpa-portal/users/getMyRoles",method:"get",params:{userId:t}})}function p(t){return Object(a["a"])({url:"/zcpa-portal/users/initPwd",method:"post",data:t,transformRequest:[function(t){var e="";for(var r in t)e+=encodeURIComponent(r)+"="+encodeURIComponent(t[r])+"&";return e}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}},"94fb":function(t,e,r){"use strict";r.d(e,"e",function(){return n}),r.d(e,"a",function(){return o}),r.d(e,"f",function(){return s}),r.d(e,"b",function(){return i}),r.d(e,"c",function(){return l}),r.d(e,"d",function(){return u});var a=r("b775");function n(t){return Object(a["a"])({url:"/zcpa-portal/corp/page",method:"get",params:t})}function o(t){return Object(a["a"])({url:"/zcpa-portal/corp/create",method:"post",data:t,transformRequest:[function(t){var e="";for(var r in t)e+=encodeURIComponent(r)+"="+encodeURIComponent(t[r])+"&";return e}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function s(t){return Object(a["a"])({url:"/zcpa-portal/corp/update",method:"post",data:t,transformRequest:[function(t){var e="";for(var r in t)e+=encodeURIComponent(r)+"="+encodeURIComponent(t[r])+"&";return e}],headers:{"Content-Type":"application/x-www-form-urlencoded"}})}function i(t){return Object(a["a"])({url:"/zcpa-portal/corp/deleteById",method:"get",params:{id:t}})}function l(t){return Object(a["a"])({url:"/zcpa-portal/corp/getById",method:"get",params:{id:t}})}function u(t){return Object(a["a"])({url:"/zcpa-portal/corp/getList",method:"get",params:{name:t}})}},a39e:function(t,e,r){"use strict";var a=r("248f"),n=r.n(a);n.a},c2a2:function(t,e,r){"use strict";r.r(e);var a=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{staticClass:"app-container"},[r("el-card",{attrs:{shadow:"never"}},[r("el-row",{attrs:{gutter:20}},[r("el-col",{attrs:{span:2}},[r("el-button-group",[t.userBtn["/portal/user/create"]?r("el-button",{attrs:{type:"primary"},on:{click:t.create}},[t._v(t._s(t.userBtn["/portal/user/create"]))]):t._e()],1)],1),r("el-col",{attrs:{span:5}},[r("el-radio-group",{attrs:{size:"medium"},on:{change:t.toggleTab},model:{value:t.query.status,callback:function(e){t.$set(t.query,"status",e)},expression:"query.status"}},t._l(t.statusData,function(e){return r("el-radio-button",{key:e.id,attrs:{label:e.id}},[t._v(t._s(e.name))])}),1)],1),r("el-col",{attrs:{span:5}},[r("el-cascader",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择行政区划",options:t.areaCodeOptions,props:t.areaCodeProps,clearable:"","change-on-select":"",filterable:""},model:{value:t.areaCodes,callback:function(e){t.areaCodes=e},expression:"areaCodes"}})],1),r("el-col",{attrs:{span:12}},[r("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请输入用户名称"},on:{change:t.toggleChange},model:{value:t.query.userName,callback:function(e){t.$set(t.query,"userName",e)},expression:"query.userName"}},[r("el-select",{staticClass:"type",attrs:{slot:"prepend",clearable:"",filterable:"",remote:"","reserve-keyword":"",placeholder:"请选择所在组织","remote-method":t.corpQuery,loading:t.loading},slot:"prepend",model:{value:t.query.corpId,callback:function(e){t.$set(t.query,"corpId",e)},expression:"query.corpId"}},t._l(t.corps,function(t){return r("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}),1),r("el-button",{attrs:{slot:"append",icon:"el-icon-search",loading:t.loading},on:{click:t.handleQuery},slot:"append"})],1)],1)],1),r("el-row",[r("el-col",{attrs:{span:24}},[r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"filterTable",staticStyle:{width:"100%"},attrs:{data:t.rows,"element-loading-text":"加载中","element-loading-spinner":"el-icon-loading"}},[r("el-table-column",{attrs:{type:"index",width:"60"}}),r("el-table-column",{attrs:{prop:"name",label:"姓名",width:"100",sortable:""}}),r("el-table-column",{attrs:{prop:"account",label:"账号",width:"230",sortable:""}}),r("el-table-column",{attrs:{prop:"corpName",label:"单位",width:"250",sortable:""}}),r("el-table-column",{attrs:{prop:"createTime",label:"创建时间",width:"180",sortable:""}}),r("el-table-column",{attrs:{prop:"opt",label:"操作",align:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[t.userBtn["/portal/user/assignRoles"]?r("el-button",{attrs:{type:"text"},on:{click:function(r){return t.assignRoles(e.row)}}},[t._v(t._s(t.userBtn["/portal/user/assignRoles"]))]):t._e(),t.userBtn["/portal/user/assignRoles"]?r("span",{staticClass:"strightline"},[t._v("|")]):t._e(),t.userBtn["/portal/user/initPwd"]?r("el-button",{attrs:{type:"text"},on:{click:function(r){return t.initPwd(e.row)}}},[t._v(t._s(t.userBtn["/portal/user/initPwd"]))]):t._e(),t.userBtn["/portal/user/initPwd"]?r("span",{staticClass:"strightline"},[t._v("|")]):t._e(),t.userBtn["/portal/user/update"]?r("el-button",{attrs:{type:"text"},on:{click:function(r){return t.update(e.row)}}},[t._v("编辑")]):t._e(),t.userBtn["/portal/user/update"]?r("span",{staticClass:"strightline"},[t._v("|")]):t._e(),t.userBtn["/portal/user/deleteById"]?r("el-button",{attrs:{type:"text"},on:{click:function(r){return t.deleteById(e.row)}}},[t._v("删除")]):t._e(),t.userBtn["/portal/user/deleteById"]?r("span",{staticClass:"strightline"},[t._v("|")]):t._e(),r("el-button",{attrs:{type:"text"},on:{click:function(r){return t.preview(e.row)}}},[t._v("详细")])]}}])})],1)],1)],1),r("el-row",{attrs:{gutter:20}},[r("el-col",{attrs:{span:4}},[[r("div",{staticClass:"page-total"},[t._v("\n            总记录数\n            "),r("span",{staticClass:"page-total-num"},[t._v(t._s(t.total))])])]],2),r("el-col",{attrs:{span:20}},[r("el-pagination",{attrs:{"current-page":t.currentPage,"page-size":t.pagesize,total:t.total,background:"",layout:"prev, pager, next"},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)],1),r("el-dialog",{attrs:{title:"详细",visible:t.dialogPreviewVisible,width:"80%"},on:{"update:visible":function(e){t.dialogPreviewVisible=e}}},[r("el-container",[r("el-header",{staticStyle:{height:"1.5rem"}}),r("el-main",{directives:[{name:"loading",rawName:"v-loading",value:t.previewLoading,expression:"previewLoading"}],attrs:{"element-loading-text":"加载中","element-loading-spinner":"el-icon-loading"}},[r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:24}},[r("div",{staticClass:"section_title"},[t._v("基本信息")])])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("姓名")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.name))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("身份证号")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.idCard))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("组织")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.corpName))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("所属行政区划")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.areaName))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:244}},[r("div",{staticClass:"section_title"},[t._v("联系信息")])])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("手机号")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.mobile))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("邮箱")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.email))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:244}},[r("div",{staticClass:"section_title"},[t._v("账号信息")])])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("账号")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.account))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("锁定标识")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.locked))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("最后锁定时间")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.lockedTime))])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:244}},[r("div",{staticClass:"section_title"},[t._v("创建信息")])])],1),r("el-row",{attrs:{gutter:40}},[r("el-col",{attrs:{span:4}},[t._v("创建时间")]),r("el-col",{attrs:{span:20}},[t._v(t._s(t.o.createTime))])],1)],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"}),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogPreviewVisible=!1}}},[t._v("关闭")])],1)],1),r("el-dialog",{attrs:{title:"密码重置",visible:t.dialogInitPwdVisible,width:"50%"},on:{"update:visible":function(e){t.dialogInitPwdVisible=e}}},[r("el-container",{directives:[{name:"loading",rawName:"v-loading",value:t.initPwdLoading,expression:"initPwdLoading"}],attrs:{"element-loading-text":"请求中","element-loading-spinner":"el-icon-loading"}},[r("el-header",{staticStyle:{height:"1.5rem"}},[r("div",{staticClass:"section_title"},[t._v(t._s(t.form.name))])]),r("el-main",[r("el-form",{ref:"ruleForm",staticStyle:{width:"80%"},attrs:{rules:t.rules,model:t.form,"label-width":"80px"}},[r("el-form-item",{attrs:{label:"新密码",prop:"password"}},[r("el-input",{attrs:{"show-password":""},model:{value:t.form.password,callback:function(e){t.$set(t.form,"password",e)},expression:"form.password"}})],1)],1)],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(e){t.dialogInitPwdVisible=!1}}},[t._v("取消")]),r("el-button",{attrs:{type:"primary",loading:t.initPwdLoading},on:{click:function(e){return t.submitForm("ruleForm")}}},[t._v("保存")])],1)],1)],1)},n=[],o=(r("7f7f"),r("ac4d"),r("8a81"),r("ac6a"),r("db72")),s=r("8930"),i=r("6797"),l=r("94fb"),u=r("2f62"),c={data:function(){return{dict:{},corps:[],areaCodeOptions:[],areaCodeProps:{value:"id",label:"text"},statusData:[{id:"",name:"全部"},{id:"1",name:"正常"},{id:"2",name:"注销"}],currentPage:1,pagesize:10,rows:[],total:0,query:{name:"",corpId:"",areaCode:"",status:"1"},loading:!1,previewLoading:!1,dialogPreviewVisible:!1,dialogInitPwdVisible:!1,initPwdLoading:!1,o:{},areaCodes:[],form:{userId:"",password:"",name:""},rules:{password:[{required:!0,message:"请输入新密码长度在6位以上",trigger:"blur"},{min:6,max:30,message:"长度在 6 到 30 个字符",trigger:"blur"}]}}},computed:Object(o["a"])({},Object(u["b"])(["userBtn"])),created:function(){this.handlePageList(),this.dictQuery(),this.AreaCodeQuery(),this.corpQuery()},methods:{corpQuery:function(t){var e=this;Object(l["d"])(t).then(function(t){e.corps=t.data})},AreaCodeQuery:function(){var t=this,e=this.$store.getters.userProp;Object(i["d"])({pid:e.areaCode,type:1,hasSelf:"true"}).then(function(e){t.areaCodeOptions=e.data})},dictQuery:function(){var t=this;Object(i["e"])("73").then(function(e){t.dict=e.data})},handleSizeChange:function(t){this.pagesize=t,window.console.log(this.pagesize)},handleQuery:function(){this.currentPage=1,this.handlePageList()},handleCurrentChange:function(t){this.currentPage=t,window.console.log(this.currentPage),this.handlePageList()},handlePageList:function(){var t=this;this.query.areaCode="";var e=!0,r=!1,a=void 0;try{for(var n,o=this.areaCodes[Symbol.iterator]();!(e=(n=o.next()).done);e=!0){var i=n.value;this.query.areaCode=i}}catch(l){r=!0,a=l}finally{try{e||null==o.return||o.return()}finally{if(r)throw a}}this.query=Object.assign(this.query,{pageNum:this.currentPage,pageSize:this.pagesize,totalRecord:this.total}),window.console.log(this.query),this.loading=!0,Object(s["h"])(this.query).then(function(e){t.loading=!1,t.rows=e.rows,t.total=e.total})},toggleChange:function(t){this.query["name"]=t,this.currentPage=1,this.handlePageList()},toggleTab:function(t){this.query["status"]=t,this.currentPage=1,this.handlePageList()},create:function(){this.$router.push({path:"/sys/user/add"})},deleteById:function(t){var e=this;window.console.log(t),this.$confirm("删除"+t.name+", 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"info",center:!0}).then(function(){e.loading=!0,Object(s["c"])(t.userId).then(function(t){e.loading=!1,e.handlePageList()})}).catch(function(){})},update:function(t){window.console.log(t),this.$router.push({path:"/sys/user/edit",query:{id:t.userId}})},preview:function(t){var e=this;this.dialogPreviewVisible=!0,this.previewLoading=!0,Object(s["e"])(t.userId).then(function(t){e.previewLoading=!1,e.o=t.data}),this.selectuserResByuserId(t.userId)},assignRoles:function(t){this.$router.push({path:"/sys/user/assignRoles",query:{id:t.userId}})},initPwd:function(t){this.dialogInitPwdVisible=!0,this.form.userId=t.userId,this.form.name=t.name},submitForm:function(t){var e=this;this.$refs[t].validate(function(t){if(!t)return!1;e.initPwdLoading=!0,Object(s["g"])(e.form).then(function(t){e.initPwdLoading=!1,e.$confirm(t.message,"提示",{confirmButtonText:"确定",type:"success",center:!0}).then(function(){e.dialogInitPwdVisible=!1})}).catch(function(t){e.initPwdLoading=!1})})}}},d=c,p=(r("a39e"),r("2877")),f=Object(p["a"])(d,a,n,!1,null,null,null);e["default"]=f.exports}}]);
//# sourceMappingURL=chunk-190e18e2.2e71ca0b.js.map