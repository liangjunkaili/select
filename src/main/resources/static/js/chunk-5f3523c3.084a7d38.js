(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5f3523c3"],{"3c35":function(t,e){(function(e){t.exports=e}).call(this,{})},"3c3f":function(t,e,s){"use strict";var i=s("f76f"),r=s.n(i);r.a},"7f7f":function(t,e,s){var i=s("86cc").f,r=Function.prototype,o=/^\s*function ([^ (]*)/,a="name";a in r||s("9e1e")&&i(r,a,{configurable:!0,get:function(){try{return(""+this).match(o)[1]}catch(t){return""}}})},8237:function(module,exports,__webpack_require__){(function(process,global){var __WEBPACK_AMD_DEFINE_RESULT__;
/**
 * [js-md5]{@link https://github.com/emn178/js-md5}
 *
 * @namespace md5
 * @version 0.7.3
 * @author Chen, Yi-Cyuan [emn178@gmail.com]
 * @copyright Chen, Yi-Cyuan 2014-2017
 * @license MIT
 */
/**
 * [js-md5]{@link https://github.com/emn178/js-md5}
 *
 * @namespace md5
 * @version 0.7.3
 * @author Chen, Yi-Cyuan [emn178@gmail.com]
 * @copyright Chen, Yi-Cyuan 2014-2017
 * @license MIT
 */
(function(){"use strict";var ERROR="input is invalid type",WINDOW="object"===typeof window,root=WINDOW?window:{};root.JS_MD5_NO_WINDOW&&(WINDOW=!1);var WEB_WORKER=!WINDOW&&"object"===typeof self,NODE_JS=!root.JS_MD5_NO_NODE_JS&&"object"===typeof process&&process.versions&&process.versions.node;NODE_JS?root=global:WEB_WORKER&&(root=self);var COMMON_JS=!root.JS_MD5_NO_COMMON_JS&&"object"===typeof module&&module.exports,AMD=__webpack_require__("3c35"),ARRAY_BUFFER=!root.JS_MD5_NO_ARRAY_BUFFER&&"undefined"!==typeof ArrayBuffer,HEX_CHARS="0123456789abcdef".split(""),EXTRA=[128,32768,8388608,-2147483648],SHIFT=[0,8,16,24],OUTPUT_TYPES=["hex","array","digest","buffer","arrayBuffer","base64"],BASE64_ENCODE_CHAR="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".split(""),blocks=[],buffer8;if(ARRAY_BUFFER){var buffer=new ArrayBuffer(68);buffer8=new Uint8Array(buffer),blocks=new Uint32Array(buffer)}!root.JS_MD5_NO_NODE_JS&&Array.isArray||(Array.isArray=function(t){return"[object Array]"===Object.prototype.toString.call(t)}),!ARRAY_BUFFER||!root.JS_MD5_NO_ARRAY_BUFFER_IS_VIEW&&ArrayBuffer.isView||(ArrayBuffer.isView=function(t){return"object"===typeof t&&t.buffer&&t.buffer.constructor===ArrayBuffer});var createOutputMethod=function(t){return function(e){return new Md5(!0).update(e)[t]()}},createMethod=function(){var t=createOutputMethod("hex");NODE_JS&&(t=nodeWrap(t)),t.create=function(){return new Md5},t.update=function(e){return t.create().update(e)};for(var e=0;e<OUTPUT_TYPES.length;++e){var s=OUTPUT_TYPES[e];t[s]=createOutputMethod(s)}return t},nodeWrap=function(method){var crypto=eval("require('crypto')"),Buffer=eval("require('buffer').Buffer"),nodeMethod=function(t){if("string"===typeof t)return crypto.createHash("md5").update(t,"utf8").digest("hex");if(null===t||void 0===t)throw ERROR;return t.constructor===ArrayBuffer&&(t=new Uint8Array(t)),Array.isArray(t)||ArrayBuffer.isView(t)||t.constructor===Buffer?crypto.createHash("md5").update(new Buffer(t)).digest("hex"):method(t)};return nodeMethod};function Md5(t){if(t)blocks[0]=blocks[16]=blocks[1]=blocks[2]=blocks[3]=blocks[4]=blocks[5]=blocks[6]=blocks[7]=blocks[8]=blocks[9]=blocks[10]=blocks[11]=blocks[12]=blocks[13]=blocks[14]=blocks[15]=0,this.blocks=blocks,this.buffer8=buffer8;else if(ARRAY_BUFFER){var e=new ArrayBuffer(68);this.buffer8=new Uint8Array(e),this.blocks=new Uint32Array(e)}else this.blocks=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];this.h0=this.h1=this.h2=this.h3=this.start=this.bytes=this.hBytes=0,this.finalized=this.hashed=!1,this.first=!0}Md5.prototype.update=function(t){if(!this.finalized){var e,s=typeof t;if("string"!==s){if("object"!==s)throw ERROR;if(null===t)throw ERROR;if(ARRAY_BUFFER&&t.constructor===ArrayBuffer)t=new Uint8Array(t);else if(!Array.isArray(t)&&(!ARRAY_BUFFER||!ArrayBuffer.isView(t)))throw ERROR;e=!0}var i,r,o=0,a=t.length,n=this.blocks,h=this.buffer8;while(o<a){if(this.hashed&&(this.hashed=!1,n[0]=n[16],n[16]=n[1]=n[2]=n[3]=n[4]=n[5]=n[6]=n[7]=n[8]=n[9]=n[10]=n[11]=n[12]=n[13]=n[14]=n[15]=0),e)if(ARRAY_BUFFER)for(r=this.start;o<a&&r<64;++o)h[r++]=t[o];else for(r=this.start;o<a&&r<64;++o)n[r>>2]|=t[o]<<SHIFT[3&r++];else if(ARRAY_BUFFER)for(r=this.start;o<a&&r<64;++o)i=t.charCodeAt(o),i<128?h[r++]=i:i<2048?(h[r++]=192|i>>6,h[r++]=128|63&i):i<55296||i>=57344?(h[r++]=224|i>>12,h[r++]=128|i>>6&63,h[r++]=128|63&i):(i=65536+((1023&i)<<10|1023&t.charCodeAt(++o)),h[r++]=240|i>>18,h[r++]=128|i>>12&63,h[r++]=128|i>>6&63,h[r++]=128|63&i);else for(r=this.start;o<a&&r<64;++o)i=t.charCodeAt(o),i<128?n[r>>2]|=i<<SHIFT[3&r++]:i<2048?(n[r>>2]|=(192|i>>6)<<SHIFT[3&r++],n[r>>2]|=(128|63&i)<<SHIFT[3&r++]):i<55296||i>=57344?(n[r>>2]|=(224|i>>12)<<SHIFT[3&r++],n[r>>2]|=(128|i>>6&63)<<SHIFT[3&r++],n[r>>2]|=(128|63&i)<<SHIFT[3&r++]):(i=65536+((1023&i)<<10|1023&t.charCodeAt(++o)),n[r>>2]|=(240|i>>18)<<SHIFT[3&r++],n[r>>2]|=(128|i>>12&63)<<SHIFT[3&r++],n[r>>2]|=(128|i>>6&63)<<SHIFT[3&r++],n[r>>2]|=(128|63&i)<<SHIFT[3&r++]);this.lastByteIndex=r,this.bytes+=r-this.start,r>=64?(this.start=r-64,this.hash(),this.hashed=!0):this.start=r}return this.bytes>4294967295&&(this.hBytes+=this.bytes/4294967296<<0,this.bytes=this.bytes%4294967296),this}},Md5.prototype.finalize=function(){if(!this.finalized){this.finalized=!0;var t=this.blocks,e=this.lastByteIndex;t[e>>2]|=EXTRA[3&e],e>=56&&(this.hashed||this.hash(),t[0]=t[16],t[16]=t[1]=t[2]=t[3]=t[4]=t[5]=t[6]=t[7]=t[8]=t[9]=t[10]=t[11]=t[12]=t[13]=t[14]=t[15]=0),t[14]=this.bytes<<3,t[15]=this.hBytes<<3|this.bytes>>>29,this.hash()}},Md5.prototype.hash=function(){var t,e,s,i,r,o,a=this.blocks;this.first?(t=a[0]-680876937,t=(t<<7|t>>>25)-271733879<<0,i=(-1732584194^2004318071&t)+a[1]-117830708,i=(i<<12|i>>>20)+t<<0,s=(-271733879^i&(-271733879^t))+a[2]-1126478375,s=(s<<17|s>>>15)+i<<0,e=(t^s&(i^t))+a[3]-1316259209,e=(e<<22|e>>>10)+s<<0):(t=this.h0,e=this.h1,s=this.h2,i=this.h3,t+=(i^e&(s^i))+a[0]-680876936,t=(t<<7|t>>>25)+e<<0,i+=(s^t&(e^s))+a[1]-389564586,i=(i<<12|i>>>20)+t<<0,s+=(e^i&(t^e))+a[2]+606105819,s=(s<<17|s>>>15)+i<<0,e+=(t^s&(i^t))+a[3]-1044525330,e=(e<<22|e>>>10)+s<<0),t+=(i^e&(s^i))+a[4]-176418897,t=(t<<7|t>>>25)+e<<0,i+=(s^t&(e^s))+a[5]+1200080426,i=(i<<12|i>>>20)+t<<0,s+=(e^i&(t^e))+a[6]-1473231341,s=(s<<17|s>>>15)+i<<0,e+=(t^s&(i^t))+a[7]-45705983,e=(e<<22|e>>>10)+s<<0,t+=(i^e&(s^i))+a[8]+1770035416,t=(t<<7|t>>>25)+e<<0,i+=(s^t&(e^s))+a[9]-1958414417,i=(i<<12|i>>>20)+t<<0,s+=(e^i&(t^e))+a[10]-42063,s=(s<<17|s>>>15)+i<<0,e+=(t^s&(i^t))+a[11]-1990404162,e=(e<<22|e>>>10)+s<<0,t+=(i^e&(s^i))+a[12]+1804603682,t=(t<<7|t>>>25)+e<<0,i+=(s^t&(e^s))+a[13]-40341101,i=(i<<12|i>>>20)+t<<0,s+=(e^i&(t^e))+a[14]-1502002290,s=(s<<17|s>>>15)+i<<0,e+=(t^s&(i^t))+a[15]+1236535329,e=(e<<22|e>>>10)+s<<0,t+=(s^i&(e^s))+a[1]-165796510,t=(t<<5|t>>>27)+e<<0,i+=(e^s&(t^e))+a[6]-1069501632,i=(i<<9|i>>>23)+t<<0,s+=(t^e&(i^t))+a[11]+643717713,s=(s<<14|s>>>18)+i<<0,e+=(i^t&(s^i))+a[0]-373897302,e=(e<<20|e>>>12)+s<<0,t+=(s^i&(e^s))+a[5]-701558691,t=(t<<5|t>>>27)+e<<0,i+=(e^s&(t^e))+a[10]+38016083,i=(i<<9|i>>>23)+t<<0,s+=(t^e&(i^t))+a[15]-660478335,s=(s<<14|s>>>18)+i<<0,e+=(i^t&(s^i))+a[4]-405537848,e=(e<<20|e>>>12)+s<<0,t+=(s^i&(e^s))+a[9]+568446438,t=(t<<5|t>>>27)+e<<0,i+=(e^s&(t^e))+a[14]-1019803690,i=(i<<9|i>>>23)+t<<0,s+=(t^e&(i^t))+a[3]-187363961,s=(s<<14|s>>>18)+i<<0,e+=(i^t&(s^i))+a[8]+1163531501,e=(e<<20|e>>>12)+s<<0,t+=(s^i&(e^s))+a[13]-1444681467,t=(t<<5|t>>>27)+e<<0,i+=(e^s&(t^e))+a[2]-51403784,i=(i<<9|i>>>23)+t<<0,s+=(t^e&(i^t))+a[7]+1735328473,s=(s<<14|s>>>18)+i<<0,e+=(i^t&(s^i))+a[12]-1926607734,e=(e<<20|e>>>12)+s<<0,r=e^s,t+=(r^i)+a[5]-378558,t=(t<<4|t>>>28)+e<<0,i+=(r^t)+a[8]-2022574463,i=(i<<11|i>>>21)+t<<0,o=i^t,s+=(o^e)+a[11]+1839030562,s=(s<<16|s>>>16)+i<<0,e+=(o^s)+a[14]-35309556,e=(e<<23|e>>>9)+s<<0,r=e^s,t+=(r^i)+a[1]-1530992060,t=(t<<4|t>>>28)+e<<0,i+=(r^t)+a[4]+1272893353,i=(i<<11|i>>>21)+t<<0,o=i^t,s+=(o^e)+a[7]-155497632,s=(s<<16|s>>>16)+i<<0,e+=(o^s)+a[10]-1094730640,e=(e<<23|e>>>9)+s<<0,r=e^s,t+=(r^i)+a[13]+681279174,t=(t<<4|t>>>28)+e<<0,i+=(r^t)+a[0]-358537222,i=(i<<11|i>>>21)+t<<0,o=i^t,s+=(o^e)+a[3]-722521979,s=(s<<16|s>>>16)+i<<0,e+=(o^s)+a[6]+76029189,e=(e<<23|e>>>9)+s<<0,r=e^s,t+=(r^i)+a[9]-640364487,t=(t<<4|t>>>28)+e<<0,i+=(r^t)+a[12]-421815835,i=(i<<11|i>>>21)+t<<0,o=i^t,s+=(o^e)+a[15]+530742520,s=(s<<16|s>>>16)+i<<0,e+=(o^s)+a[2]-995338651,e=(e<<23|e>>>9)+s<<0,t+=(s^(e|~i))+a[0]-198630844,t=(t<<6|t>>>26)+e<<0,i+=(e^(t|~s))+a[7]+1126891415,i=(i<<10|i>>>22)+t<<0,s+=(t^(i|~e))+a[14]-1416354905,s=(s<<15|s>>>17)+i<<0,e+=(i^(s|~t))+a[5]-57434055,e=(e<<21|e>>>11)+s<<0,t+=(s^(e|~i))+a[12]+1700485571,t=(t<<6|t>>>26)+e<<0,i+=(e^(t|~s))+a[3]-1894986606,i=(i<<10|i>>>22)+t<<0,s+=(t^(i|~e))+a[10]-1051523,s=(s<<15|s>>>17)+i<<0,e+=(i^(s|~t))+a[1]-2054922799,e=(e<<21|e>>>11)+s<<0,t+=(s^(e|~i))+a[8]+1873313359,t=(t<<6|t>>>26)+e<<0,i+=(e^(t|~s))+a[15]-30611744,i=(i<<10|i>>>22)+t<<0,s+=(t^(i|~e))+a[6]-1560198380,s=(s<<15|s>>>17)+i<<0,e+=(i^(s|~t))+a[13]+1309151649,e=(e<<21|e>>>11)+s<<0,t+=(s^(e|~i))+a[4]-145523070,t=(t<<6|t>>>26)+e<<0,i+=(e^(t|~s))+a[11]-1120210379,i=(i<<10|i>>>22)+t<<0,s+=(t^(i|~e))+a[2]+718787259,s=(s<<15|s>>>17)+i<<0,e+=(i^(s|~t))+a[9]-343485551,e=(e<<21|e>>>11)+s<<0,this.first?(this.h0=t+1732584193<<0,this.h1=e-271733879<<0,this.h2=s-1732584194<<0,this.h3=i+271733878<<0,this.first=!1):(this.h0=this.h0+t<<0,this.h1=this.h1+e<<0,this.h2=this.h2+s<<0,this.h3=this.h3+i<<0)},Md5.prototype.hex=function(){this.finalize();var t=this.h0,e=this.h1,s=this.h2,i=this.h3;return HEX_CHARS[t>>4&15]+HEX_CHARS[15&t]+HEX_CHARS[t>>12&15]+HEX_CHARS[t>>8&15]+HEX_CHARS[t>>20&15]+HEX_CHARS[t>>16&15]+HEX_CHARS[t>>28&15]+HEX_CHARS[t>>24&15]+HEX_CHARS[e>>4&15]+HEX_CHARS[15&e]+HEX_CHARS[e>>12&15]+HEX_CHARS[e>>8&15]+HEX_CHARS[e>>20&15]+HEX_CHARS[e>>16&15]+HEX_CHARS[e>>28&15]+HEX_CHARS[e>>24&15]+HEX_CHARS[s>>4&15]+HEX_CHARS[15&s]+HEX_CHARS[s>>12&15]+HEX_CHARS[s>>8&15]+HEX_CHARS[s>>20&15]+HEX_CHARS[s>>16&15]+HEX_CHARS[s>>28&15]+HEX_CHARS[s>>24&15]+HEX_CHARS[i>>4&15]+HEX_CHARS[15&i]+HEX_CHARS[i>>12&15]+HEX_CHARS[i>>8&15]+HEX_CHARS[i>>20&15]+HEX_CHARS[i>>16&15]+HEX_CHARS[i>>28&15]+HEX_CHARS[i>>24&15]},Md5.prototype.toString=Md5.prototype.hex,Md5.prototype.digest=function(){this.finalize();var t=this.h0,e=this.h1,s=this.h2,i=this.h3;return[255&t,t>>8&255,t>>16&255,t>>24&255,255&e,e>>8&255,e>>16&255,e>>24&255,255&s,s>>8&255,s>>16&255,s>>24&255,255&i,i>>8&255,i>>16&255,i>>24&255]},Md5.prototype.array=Md5.prototype.digest,Md5.prototype.arrayBuffer=function(){this.finalize();var t=new ArrayBuffer(16),e=new Uint32Array(t);return e[0]=this.h0,e[1]=this.h1,e[2]=this.h2,e[3]=this.h3,t},Md5.prototype.buffer=Md5.prototype.arrayBuffer,Md5.prototype.base64=function(){for(var t,e,s,i="",r=this.array(),o=0;o<15;)t=r[o++],e=r[o++],s=r[o++],i+=BASE64_ENCODE_CHAR[t>>>2]+BASE64_ENCODE_CHAR[63&(t<<4|e>>>4)]+BASE64_ENCODE_CHAR[63&(e<<2|s>>>6)]+BASE64_ENCODE_CHAR[63&s];return t=r[o],i+=BASE64_ENCODE_CHAR[t>>>2]+BASE64_ENCODE_CHAR[t<<4&63]+"==",i};var exports=createMethod();COMMON_JS?module.exports=exports:(root.md5=exports,AMD&&(__WEBPACK_AMD_DEFINE_RESULT__=function(){return exports}.call(exports,__webpack_require__,exports,module),void 0===__WEBPACK_AMD_DEFINE_RESULT__||(module.exports=__WEBPACK_AMD_DEFINE_RESULT__)))})()}).call(this,__webpack_require__("4362"),__webpack_require__("c8ba"))},c40e:function(t,e,s){"use strict";s.d(e,"d",function(){return r}),s.d(e,"b",function(){return o}),s.d(e,"a",function(){return a}),s.d(e,"c",function(){return n}),s.d(e,"e",function(){return h}),s.d(e,"f",function(){return c});var i=s("b775");function r(){return Object(i["a"])({url:"/goods/index",method:"get",params:""})}function o(t){return Object(i["a"])({url:"/goods/getGoodById",method:"get",params:t})}function a(t){return Object(i["a"])({url:"/goods/receiveCoupons",method:"post",data:t})}function n(t){return Object(i["a"])({url:"/user/orderGoods",method:"post",data:t})}function h(t){return Object(i["a"])({url:"/getSignWxPay",method:"post",data:t})}function c(t){return Object(i["a"])({url:"/message/leaveMessage",method:"post",data:t})}},effa:function(t,e,s){"use strict";s.r(e);var i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"page"},[t._l(t.list,function(e,i){return s("div",{key:i,staticClass:"test-item"},[s("div",{staticClass:"title"},[s("div",{staticClass:"title-txt"},[t._v(t._s(e.title))]),0==e.state?s("div",{staticClass:"title-right red"},[t._v("等待测评")]):t._e(),1==e.state?s("div",{staticClass:"title-right gray"},[t._v("测评完成")]):t._e()]),s("div",{staticClass:"item-con"},[s("div",{staticClass:"box1"},[s("div",{staticClass:"box2"},[s("div",{staticClass:"b-left"},[t._v("姓名：")]),s("div",{staticClass:"b-right"},[t._v(t._s(e.name))])]),s("div",{staticClass:"box3"},[s("div",{staticClass:"b-left"},[t._v("性别：")]),s("div",{staticClass:"b-right"},[t._v(t._s(1==e.gender?"男":"女"))])])]),s("div",{staticClass:"box1"},[s("div",{staticClass:"box2"},[s("div",{staticClass:"b-left"},[t._v("出生日期：")]),s("div",{staticClass:"b-right"},[t._v(t._s(e.birthdayTime))])]),s("div",{staticClass:"box3"},[s("div",{staticClass:"b-left"},[t._v("电话：")]),s("div",{staticClass:"b-right"},[t._v(t._s(e.phone))])])]),s("div",{staticClass:"box1"},[s("div",{staticClass:"box2"},[s("div",{staticClass:"b-left",staticStyle:{}},[t._v("邮寄地址：")]),s("div",{staticClass:"b-right"},[t._v(t._s(e.address))])])])]),s("div",{staticClass:"btn-group"},[1==e.state?s("div",{staticClass:"btn btn1",on:{click:function(s){t.report(e.id)}}},[t._v("报告")]):t._e(),1==e.state?s("div",{staticClass:"btn btn1",on:{click:function(e){t.showModal()}}},[t._v("讲解")]):t._e()])])}),t.showModalStatus?s("div",{staticClass:"commodity_screen",on:{click:t.hideModal}}):t._e(),t.showModalStatus?s("div",{staticClass:"commodity_attr_box"},[s("div",{staticClass:"modal-title"}),s("div",{staticClass:"modal-body"},[s("div",[s("video",{attrs:{id:"myvideo",src:"http://babylife.qiniudn.com/FtRVyPQHHocjVYjeJSrcwDkApTLQ",height:"270",width:"100%"},on:{click:t.playClick}})])])]):t._e()],2)},r=[],o=(s("7f7f"),s("cadf"),s("551c"),s("097d"),s("c24f")),a=(s("c40e"),s("8237")),n=s.n(a),h={name:"evalu",data:function(){return{oid:0,id:0,list:[],num:1,name:"",sex:1,birthday:"",phone:"",addr:"",minDate:new Date(1900,1,1),maxDate:new Date,currentDate:new Date,showModalStatus:!1,showModalStatus1:!1,isShowPicker:!1,testurl:"https://www.yichengzhang.cn/evaluation/paper",reporturl:"https://www.yichengzhang.cn/evaluation/report",psd:"123456ab",stamp:"",companyId:141421,secret:"",examCode:"XY-15",reportNo:"2",urlOk:"https://dis.ucharmedu.com/ui/my/testback/success",urlNg:"https://dis.ucharmedu.com/ui/my/testback/fail"}},mounted:function(){this.getList()},methods:{getList:function(){var t=this,e=new URLSearchParams;e.append("uid",localStorage.getItem("uid")),Object(o["g"])(e).then(function(e){var s=e.data;t.list=s.data})},playClick:function(){this._dom=document.getElementById("myvideo"),this._dom.play()},showModal:function(){this.showModalStatus=!0},showModal1:function(){this.showModalStatus1=!0},hideModal:function(){this.showModalStatus=!1,this.showModalStatus1=!1},show:function(){this.isShowPicker=!0},formatter:function(t,e){return"year"===t?"".concat(e,"年"):"month"===t?"".concat(e,"月"):"day"===t?"".concat(e,"日"):e},confirm:function(t){this.birthday=this.forDateTime(t),this.cancel()},cancel:function(){this.isShowPicker=!1},forDateTime:function(t){var e=t.getFullYear(),s=t.getMonth()+1;s=s<10?"0"+s:s;var i=t.getDate();i=i<10?"0"+i:i;t.getHours();var r=t.getMinutes();return r=r<10?"0"+r:r,e+"-"+s+"-"+i},sexset:function(t){this.sex=t},add:function(){var t=this;t.id=0,t.name="",t.sex=1,t.phone="",t.birthday="",t.showModal()},edit:function(t){var e=this;this.id=t;var s=new URLSearchParams;s.append("id",this.id),getOnePeople(s).then(function(t){var s=t.data.data;e.name=s.name,e.birthday=s.birthdayTime,e.phone=s.phone,e.addr=s.address,e.sex=s.gender,e.showModal()})},sub:function(){var t=this,e=this;this.btnClicked=!1;var s=new URLSearchParams;s.append("oid",this.oid);var i=/^[1][3,4,5,7,8][0-9]{9}$/;return i.test(e.phone)?this.name&&this.birthday&&this.addr?(s.append("id",this.id),s.append("name",this.name),s.append("phone",this.phone),s.append("birthdayTime",this.birthday),s.append("address",this.addr),s.append("gender",this.sex),void addPeople(s).then(function(e){t.btnClicked=!0;e.data;t.$vtoast({message:"操作成功！",type:"",position:"bottom",duration:"2000"}),t.hideModal(),t.getList()})):(this.$vtoast({message:"请完善资料！",type:"",position:"bottom",duration:"2000"}),!1):(e.$vtoast({message:"手机号码不正确！",type:"",position:"bottom",duration:"2000"}),!1)},getDate:function(){var t=new Date,e=t.getMonth()+1,s=t.getDate(),i=t.getHours(),r=t.getMinutes(),o=t.getSeconds();e>=1&&e<=9&&(e="0"+e),s>=0&&s<=9&&(s="0"+s),i<10&&(i="0"+i),r<10&&(r="0"+r),o<10&&(o="0"+o);var a=""+e+s+i+r+o;return a},getTestUrl:function(){var t=this.getDate(),e=this.psd+t,s=n()(e);this.stamp=t,this.secret=s;var i=this.testurl+"?companyId="+this.companyId+"&stamp="+this.stamp+"&secret="+this.secret+"&examCode="+this.examCode+"&reportNo="+this.reportNo+"&urlOk="+this.urlOk+"&urlNg="+this.urlNg+"&name="+this.name+"&mobile="+this.phone+"&sex="+this.sex+"&birthday="+this.birthday;return console.log(i),i},getReportUrl:function(){var t=this.getDate(),e=this.psd+t,s=n()(e);this.stamp=t,this.secret=s;var i=this.reporturl+"?companyId="+this.companyId+"&stamp="+this.stamp+"&secret="+this.secret+"&reportNo="+this.reportNo;return console.log(i),i},beforeTest:function(t,e){"天赋优势测评"==e?(this.id=t,this.showModal1()):this.test(t)},test:function(t){var e=this;0==t&&(t=this.id),localStorage.setItem("pid",t);var s=new URLSearchParams;s.append("id",t),getOnePeople(s).then(function(s){var i=s.data.data;e.reportNo=t,e.name=encodeURIComponent(i.name),e.examCode=i.code,e.birthday=i.birthdayTime,e.phone=i.phone,e.sex=1==i.gender?1:0,e.urlOk=encodeURIComponent(e.urlOk),e.urlNg=encodeURIComponent(e.urlNg);var r=e.getTestUrl();e.$vtoast({message:"测评大约要30-40分钟，期间不要中断，谢谢",type:"",position:"bottom",duration:"3000"}),setTimeout(function(){return window.location.href=r,!0},3e3)})},report:function(t){this.reportNo=2;var e=this.getReportUrl();return window.location.href=e,!0}}},c=h,d=(s("3c3f"),s("2877")),u=Object(d["a"])(c,i,r,!1,null,"5b148236",null);u.options.__file="report.vue";e["default"]=u.exports},f76f:function(t,e,s){}}]);