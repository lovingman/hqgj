(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d221f8a"],{cd2e:function(t,e,a){"use strict";a.r(e);var r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-card",{attrs:{shadow:"never"}},[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:12}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"datetimerange","picker-options":t.pickerOptions,"range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:t.value,callback:function(e){t.value=e},expression:"value"}})],1),a("el-col",{attrs:{span:12}},[a("el-input",{staticClass:"input-with-select",attrs:{placeholder:"请输入操作人姓名或模块名称"},on:{change:t.toggleChange},model:{value:t.query.operationObj,callback:function(e){t.$set(t.query,"operationObj",e)},expression:"query.operationObj"}},[a("el-button",{attrs:{slot:"append",icon:"el-icon-search",loading:t.loading},on:{click:t.handleQuery},slot:"append"})],1)],1)],1),a("el-row",[a("el-col",{attrs:{span:24}},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"filterTable",staticStyle:{width:"100%"},attrs:{data:t.rows,"element-loading-text":"加载中","element-loading-spinner":"el-icon-loading"}},[a("el-table-column",{attrs:{type:"index",width:"100"}}),a("el-table-column",{attrs:{prop:"operationObj",label:"模块",width:"200",sortable:""}}),a("el-table-column",{attrs:{prop:"operationType",label:"类型",width:"250",sortable:""}}),a("el-table-column",{attrs:{prop:"runTime",label:"执行时长",width:"150",sortable:""}}),a("el-table-column",{attrs:{prop:"userName",label:"操作人",width:"100",sortable:""}}),a("el-table-column",{attrs:{prop:"createTime",label:"操作时间",width:"200",sortable:""}}),a("el-table-column",{attrs:{prop:"opt",label:"操作",align:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){return t.preview(e.row)}}},[t._v("详细")])]}}])})],1)],1)],1),a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:4}},[[a("div",{staticClass:"page-total"},[t._v("\n            总记录数\n            "),a("span",{staticClass:"page-total-num"},[t._v(t._s(t.total))])])]],2),a("el-col",{attrs:{span:20}},[a("el-pagination",{attrs:{"current-page":t.currentPage,"page-size":t.pagesize,total:t.total,background:"",layout:"prev, pager, next"},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)],1),a("el-dialog",{attrs:{title:"详细",visible:t.dialogPreviewVisible,width:"80%"},on:{"update:visible":function(e){t.dialogPreviewVisible=e}}},[a("el-container",[a("el-header",{staticStyle:{height:"1.5rem"}},[a("div",{staticClass:"section_title"},[t._v("基本信息")])]),a("el-main",{directives:[{name:"loading",rawName:"v-loading",value:t.previewLoading,expression:"previewLoading"}],attrs:{"element-loading-text":"加载中","element-loading-spinner":"el-icon-loading"}},[a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("模块")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.operationObj))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("类型")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.operationType))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("操作人")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.userName))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("IP")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.operationIp))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("执行时长(毫秒)")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.runTime))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("操作时间")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.createTime))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("描述")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.describ))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("执行方法")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.method))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("参数")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.args))])],1),a("el-row",{attrs:{gutter:40}},[a("el-col",{attrs:{span:4}},[t._v("返回")]),a("el-col",{attrs:{span:20}},[t._v(t._s(t.o.returnValue))])],1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogPreviewVisible=!1}}},[t._v("关闭")])],1)],1)],1)},l=[],n=a("db72"),o=a("b775");function s(t){return Object(o["a"])({url:"/zcpa-portal/logs/page",method:"get",params:t})}function i(t){return Object(o["a"])({url:"/zcpa-portal/logs/getById",method:"get",params:{id:t}})}var c=a("2f62");function u(t){if(!t)return null;var e=t.getFullYear()+"-",a=t.getMonth()+1<10?"0"+(t.getMonth()+1)+"-":t.getMonth()+1+"-",r=t.getDate()<10?"0"+t.getDate()+" ":t.getDate()+" ",l=t.getHours()<10?"0"+t.getHours()+":":t.getHours()+":",n=t.getMinutes()<10?"0"+t.getMinutes()+":":t.getMinutes()+":",o=t.getSeconds()<10?"0"+t.getSeconds():t.getSeconds();return e+a+r+l+n+o}var p={data:function(){return{currentPage:1,pagesize:10,rows:[],total:0,value:[],query:{operationObj:"",startDate:"",endDate:""},loading:!1,previewLoading:!1,dialogPreviewVisible:!1,o:{},pickerOptions:{shortcuts:[{text:"最近一周",onClick:function(t){var e=new Date,a=new Date;a.setTime(a.getTime()-6048e5),t.$emit("pick",[a,e])}},{text:"最近一个月",onClick:function(t){var e=new Date,a=new Date;a.setTime(a.getTime()-2592e6),t.$emit("pick",[a,e])}},{text:"最近三个月",onClick:function(t){var e=new Date,a=new Date;a.setTime(a.getTime()-7776e6),t.$emit("pick",[a,e])}}]}}},computed:Object(n["a"])({},Object(c["b"])(["userBtn"])),created:function(){this.handlePageList()},methods:{handleQuery:function(){this.currentPage=1,this.handlePageList()},handleSizeChange:function(t){this.pagesize=t},handleCurrentChange:function(t){this.currentPage=t,this.handlePageList()},handlePageList:function(){var t=this;this.query["startDate"]="",this.query["endDate"]="",this.value&&this.value.length>1&&(this.query["startDate"]=u(this.value[0]),this.query["endDate"]=u(this.value[1])),this.query=Object.assign(this.query,{pageNum:this.currentPage,pageSize:this.pagesize,totalRecord:this.total}),this.loading=!0,s(this.query).then(function(e){t.loading=!1,t.rows=e.rows,t.total=e.total})},toggleChange:function(t){this.currentPage=1,this.query["operationObj"]=t,this.handlePageList()},preview:function(t){var e=this;this.dialogPreviewVisible=!0,this.previewLoading=!0,i(t.id).then(function(t){e.previewLoading=!1,e.o=t.data})}}},g=p,d=a("2877"),h=Object(d["a"])(g,r,l,!1,null,null,null);e["default"]=h.exports}}]);
//# sourceMappingURL=chunk-2d221f8a.f14384e1.js.map