(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0e2a79ca"],{"0187":function(e,t,n){"use strict";n.d(t,"f",function(){return r}),n.d(t,"a",function(){return o}),n.d(t,"h",function(){return i}),n.d(t,"b",function(){return c}),n.d(t,"c",function(){return s}),n.d(t,"e",function(){return l}),n.d(t,"d",function(){return u}),n.d(t,"g",function(){return d});var a=n("b775");function r(e){return Object(a["a"])({url:"/zcpa-portal/role/page",method:"get",params:e})}function o(e){return Object(a["a"])({url:"/zcpa-portal/role/create",params:e})}function i(e){return Object(a["a"])({url:"/zcpa-portal/role/update",params:e})}function c(e){return Object(a["a"])({url:"/zcpa-portal/role/deleteById",params:{id:e}})}function s(e){return Object(a["a"])({url:"/zcpa-portal/role/getById",method:"get",params:{id:e}})}function l(e){return Object(a["a"])({url:"/zcpa-portal/role/insertRoleRes",params:e})}function u(){return Object(a["a"])({url:"/zcpa-portal/resources/getResTreeList",method:"get",params:{}})}function d(e){return Object(a["a"])({url:"/zcpa-portal/role/selectRoleResByRoleId",method:"get",params:{roleId:e}})}},"7ca4":function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("el-card",{attrs:{shadow:"never"}},[n("el-container",[n("el-header",{staticStyle:{height:"1.5rem"}},[n("div",{staticClass:"section_title"},[e._v(e._s(e.o.roleName)+"的权限")])]),n("el-main",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{"element-loading-text":"请求中","element-loading-spinner":"el-icon-loading"}},[n("el-tree",{ref:"tree",attrs:{data:e.data,"show-checkbox":"","expand-on-click-node":!1,"check-on-click-node":!0,"default-checked-keys":e.defaultCheckedKeys,"default-expand-all":"","node-key":"id","highlight-current":"",props:e.defaultProps}})],1),n("el-footer",[n("div",{staticClass:"buttons"},[n("el-button",{on:{click:e.back}},[e._v("取消")]),n("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm()}}},[e._v("保存")])],1)])],1)],1)],1)},r=[],o=n("db72"),i=n("0187"),c=n("2f62"),s={data:function(){return{id:"",defaultProps:{label:"text"},loading:!1,data:[],defaultCheckedKeys:[],o:{},rules:{}}},computed:Object(o["a"])({},Object(c["b"])(["userProp"])),created:function(){this.load(),this.getResTreeList(),this.selectRoleResByRoleId()},methods:{load:function(){var e=this;this.loading=!0,this.id=this.$route.query.id,Object(i["c"])(this.id).then(function(t){e.loading=!1,e.o=t.data})},getResTreeList:function(){var e=this;this.loading=!0,Object(i["d"])().then(function(t){e.loading=!1,e.data=t})},selectRoleResByRoleId:function(){var e=this;this.loading=!0,this.id=this.$route.query.id,Object(i["g"])(this.id).then(function(t){e.loading=!1,e.defaultCheckedKeys=t.data})},submitForm:function(e){var t=this,n=this.$refs.tree.getCheckedKeys(),a=this.$refs.tree.getHalfCheckedKeys(),r=n.concat(a);this.loading=!0,this.id=this.$route.query.id,Object(i["e"])({roleId:this.id,resId:r.join(",")}).then(function(e){t.loading=!1,t.$confirm(e.message+" 是否返回?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"success",center:!0}).then(function(){t.back()}).catch(function(){})}).catch(function(e){t.loading=!1})},back:function(){this.$router.push({path:"/sys/role"})}}},l=s,u=n("2877"),d=Object(u["a"])(l,a,r,!1,null,null,null);t["default"]=d.exports}}]);
//# sourceMappingURL=chunk-0e2a79ca.e20f86a6.js.map