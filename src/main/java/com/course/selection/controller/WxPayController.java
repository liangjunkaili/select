package com.course.selection.controller;

import com.course.selection.service.OrderService;
import com.course.selection.util.*;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
}
