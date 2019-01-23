package com.course.selection.controller;

import com.course.selection.dto.Result;
import com.course.selection.dto.UserDto;
import com.course.selection.service.OrderService;
import com.course.selection.service.UserService;
import com.course.selection.util.*;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付
 */
@RestController
@Log4j2
public class WxPayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(WxPayController.class);

    @RequestMapping("/wxpay_success")
    public String wxpay_success(HttpServletRequest request) {
        log.info("成功支付，调起回调函数");
        String result = "";
        String xmlStr = JsonUtil.inputToString(request);
        try {
            Map<String, String> map = XmlUtil.xmlStrToMap(xmlStr);
            String return_code = map.get("return_code");
            String oid = map.get("attach");
            //处理业务
            if (return_code.equals("SUCCESS")) {
                log.info("开始更改订单状态");
                Boolean success = orderService.paySuccess(Integer.valueOf(oid));
                if (success) {
                log.info("订单状态更改成功");
                    result = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                } else {
                    result = "<xml><return_code><![CDATA[FAIL]]></return_code></xml>";
                }
            } else {
                result = "<xml><return_code><![CDATA[FAIL]]></return_code></xml>";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    public String wxpay_complete() {
        return "";
    }

    public String wxpay_fail() {
        return "";
    }

    /**
     * @param request total_fee attach body openid
     * @return timeStamp appId nonceStr package signType
     */
    @RequestMapping("/getSignWxPay")
    public JSONObject getSignWxPay_jssdk(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String ip = HttpRequest.getIpAddr(request);
        String total_fee = request.getParameter("total_fee");
        String attach = request.getParameter("attach");
        String body = request.getParameter("body");
        String openid = request.getParameter("openid");
        Map<String, String> map = new HashMap<>();
        map.put("spbill_create_ip", ip);
        map.put("total_fee", total_fee);
        map.put("attach", attach);
        map.put("body", body);
        map.put("openid", openid);
        //map.put("receipt","Y");//传入 Y 时，支付成功消息和支付详情页将出现开票入口
        try {
            String prepay_id = WxPayUtil.get_prepay_id(map);
            String packAge = "prepay_id=" + prepay_id;
            Long timeStamp = DateUtil.getTimestamp();
            String nonceStr = StringUtil.getGlobalId();
            Map<String, String> param = new HashMap<>();
            param.put("appId", WXConfiguration.APPID);
            param.put("timeStamp", timeStamp + "");
            param.put("nonceStr", nonceStr);
            param.put("package", packAge);
            param.put("signType", "MD5");
            String sign = EncryptUtil.getSign(param);
            jsonObject.put("timeStamp", timeStamp);
            jsonObject.put("nonceStr", nonceStr);
            jsonObject.put("package", packAge);
            jsonObject.put("signType", "MD5");
            jsonObject.put("paySign", sign);
            jsonObject.put("appId", WXConfiguration.APPID);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonObject;
    }

    @RequestMapping("getWXAcode")
    public String getWXAcode(){
        String accessToken = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path","pages/home/detail/detail");
        InputStream result = HttpRequest.doWXPost(WXConfiguration.WXACODE.replace("ACCESS_TOKEN",accessToken),jsonObject);
        String url = "";
        try {
            url = CosUtil.uploadImage0("wxcode.jpg",result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
    @RequestMapping("/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //a-zA-Z0-9的参数值，最多128字节
        String state = request.getParameter("state");
        String callbackUrl = WXConfiguration.MYSERVER+"authorize_callback";
        //snsapi_base 不弹出授权页面，直接跳转，只能获取用户openid
//        response.sendRedirect(WXConfiguration.authorize.replace("REDIRECT_URI",callbackUrl).replace("STATE",state).replace("SCOPE",WXConfiguration.scope_snsapi_base));
        //snsapi_userinfo 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
        response.sendRedirect(WXConfiguration.authorize.replace("REDIRECT_URI",callbackUrl).replace("STATE",state).replace("SCOPE",WXConfiguration.scope_snsapi_userinfo));
    }

    @RequestMapping("/authorize_callback")

//    public Result authorize_callback(HttpServletRequest request){

    public void authorize_callback(HttpServletRequest request,HttpServletResponse response){

        String code = request.getParameter("code");
        log.info("code:{}",code);
        String result = HttpRequest.sendGet(WXConfiguration.web_access_token.replace("CODE",code));
        JSONObject jsonObject = JSONObject.fromObject(result);
        log.info("jsonObject:{}",jsonObject);
        String access_token = jsonObject.getString(ConstantUtil.access_token);
        String openid = jsonObject.getString("openid");//snsapi_base式的网页授权流程即到此为止
        String refresh_token = jsonObject.getString("refresh_token");

        //检验授权凭证（access_token）是否有效
        String isInvalid = HttpRequest.sendGet(WXConfiguration.isInvalid.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid));
        JSONObject jsonObject4 = JSONObject.fromObject(isInvalid);
        if (jsonObject4.getInt("errcode") != 0){
            String result2 = HttpRequest.sendGet(WXConfiguration.refresh_token.replace("REFRESH_TOKEN",refresh_token));
            JSONObject jsonObject2 = JSONObject.fromObject(result2);
            access_token = jsonObject2.getString(ConstantUtil.access_token);
        }
        String result3 = HttpRequest.sendGet(WXConfiguration.sns_userInfo.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid));
        JSONObject jsonObject3 = JSONObject.fromObject(result3);
        String nickname = jsonObject3.getString("nickname");
        Integer sex = jsonObject3.getInt("sex");
        String province = jsonObject3.getString("province");
        String city = jsonObject3.getString("city");
        String country = jsonObject3.getString("country");
        String headimgurl = jsonObject3.getString("headimgurl");
        JSONArray privilege = jsonObject3.getJSONArray("privilege");

        //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
//        String unionid = jsonObject3.getString("unionid");

//        String unionid = null;
//        log.info("jsonObject3:{}",jsonObject3);
//        return userService.insert(nickname,sex,province,city,country,headimgurl,openid,privilege,unionid);

        UserDto user = userService.insert(nickname, sex, province, city, country, headimgurl, openid, privilege, "");
        try {
            response.setHeader("avatar",user.getAvatar());
            response.setHeader("nickname",user.getNickName());
            response.setHeader("openId",user.getOpenId());
            response.setHeader("poster",user.getPoster());
            response.setHeader("income",user.getIncome().toString());
            response.setHeader("uid",user.getUid().toString());
            String urlr = "";
            String url = "https://dis.ucharmedu.com/?avatar="+user.getAvatar()
                    +"&nickname=" +user.getNickName()
                    +"&openId=" +user.getOpenId()
                    +"&poster=" +user.getPoster()
                    +"&income=" +user.getIncome().toString()
                    +"&uid=" +user.getUid().toString();
            int index = url.indexOf("?");
            urlr = url.substring(0,index+1);
            String temp = url.substring(index+1);
                //URLEncode转码会将& ： / = 等一些特殊字符转码,(但是这个字符  只有在作为参数值  时需要转码;例如url中的&具有参数连接的作用，此时就不能被转码)
                String encode = URLEncoder.encode(temp, "utf-8");
                System.out.println(encode);
                encode = encode.replace("%3D",  "=");
                encode = encode.replace("%2F", "/");
                encode = encode.replace("+", "%20");
                encode = encode.replace("%26", "&");
                urlr += encode;
                log.info("urlr");
            response.sendRedirect(urlr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/init")
    public void init_get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String[] arr = new String[]{WXConfiguration.token,timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer content = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }
        String sign = EncryptUtil.Encrypt(content.toString(),"sha1");
        PrintWriter out = response.getWriter();
        if(sign.equals(signature)){
            out.print(echostr);
        }
    }
}
