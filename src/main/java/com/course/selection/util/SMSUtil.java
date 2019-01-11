package com.course.selection.util;

import net.sf.json.JSONObject;

import java.util.Random;

public class SMSUtil {
    public static String validateCode(){
        String validateCode = "";
        Random random = new Random();
        for(int i=0;i<4;i++){
            validateCode+=random.nextInt(10);
        }
        return validateCode;
    }
    public static String sendSMS(String phone,String nationcode,String min){
        String validateCode = validateCode();
        String sig = EncryptUtil.Encrypt(ConstantUtil.APPKEY+phone,"MD5");
        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();
        obj1.put("nationcode", nationcode);
        obj1.put("phone", phone);
        obj.put("tel", obj1);
        obj.put("type", "0");
        if("86".equals(nationcode)){
            obj.put("msg", validateCode+"为您的登录验证码，请于"+min+"分钟内填写。如非本人操作，请忽略本短信 。 ");
        }else{
            obj.put("msg", "Welcome to MagicABC,confirmation code is "+validateCode);
        }
        obj.put("sig", sig);
        String str = HttpRequest.sendPost(ConstantUtil.SMS_URL,obj.toString());
        System.out.println(str);
        //之后对于返回的json信息入库，留作数据分析，暂时先不开发
        return validateCode;
    }

    public static void main(String[] args) {
        sendSMS("15600806167","86","五");
    }
}
