package com.course.selection.util;

import net.sf.json.JSONObject;

import java.util.Random;

public class SMSUtil {
    public static String validateCode(){
        StringBuilder validateCode = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<4;i++){
            validateCode.append(random.nextInt(10));
        }
        return validateCode.toString();
    }
    public static String sendSMS(String phone,Integer type){
        String sig = EncryptUtil.Encrypt(ConstantUtil.APPKEY+phone,"MD5");
        JSONObject obj = new JSONObject();
        JSONObject obj1 = new JSONObject();
        obj1.put("nationcode", "86");
        obj1.put("phone", phone);
        obj.put("tel", obj1);
        obj.put("type", "0");
        if(type==1){
            obj.put("msg", "祝贺您，您的天赋优势分析报告，已经生成！请登录“好好选课”公众号查看。回T退订。");
        }else{
            obj.put("msg", "很遗憾，您的天赋优势测评采样照片不够清晰完整，请您重新采样，再登录“好好选课”公众号上传照片。");
        }
        obj.put("sig", sig);
        String str = HttpRequest.sendPost(ConstantUtil.SMS_URL,obj.toString());
        System.out.println(str);
        //之后对于返回的json信息入库，留作数据分析，暂时先不开发
        return str;
    }

    public static void main(String[] args) {
        sendSMS("15652346678",1);
        sendSMS("15652346678",2);
//        System.out.println(validateCode());
    }
}
