(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b1af1198"],{"5d1a":function(t,s,i){t.exports=i.p+"img/coupon.ba8d9915.png"},6041:function(t,s,i){"use strict";i.r(s);var a=function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",[t.list.length>0?a("div",{staticClass:"coupon-box"},t._l(t.list,function(s){return a("div",{staticClass:"c-item",attrs:{vkey:"index"}},[a("img",{attrs:{src:i("5d1a")}}),a("div",{staticClass:"item-txt"},[a("div",{staticClass:"txt-left"},[a("div",{staticClass:"txt1"},[t._v("￥"),a("span",[t._v(t._s(s.price))]),t._v(t._s(s.type))]),a("div",{staticClass:"txt2"},[t._v(t._s(s.reason))]),a("div",{staticClass:"txt3"},[t._v("有效期:"+t._s(s.invalidDate))])]),t._m(0,!0)])])}),0):a("div",{staticClass:"nomsg"},[t._v("暂时没有优惠券哦～")])])},n=[function(){var t=this,s=t.$createElement,i=t._self._c||s;return i("div",{staticClass:"txt-right"},[i("div",{staticClass:"r-btn1 ",attrs:{bindtap:"tohome"}},[t._v("立即使用")])])}],e=i("c24f"),c={name:"mycoupon",data:function(){return{list:[]}},mounted:function(){this.getList()},methods:{getList:function(){var t=this,s={uid:localStorage.getItem("uid")};Object(e["b"])(s).then(function(s){var i=s.data;t.list=i.data})}}},o=c,l=(i("90bb"),i("2877")),u=Object(l["a"])(o,a,n,!1,null,null,null);u.options.__file="coupon.vue";s["default"]=u.exports},"90bb":function(t,s,i){"use strict";var a=i("ed97"),n=i.n(a);n.a},ed97:function(t,s,i){}}]);