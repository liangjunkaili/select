package com.course.selection.util;

public class WXConfiguration {
    public static final String APPID = "wx0dd23ad0cfebd824";
    public static final String APPSECRET = "14c94e3c2e05cc9f9544702b9f01bbe6";
    public static final String MYSERVER = "https://dis.haohaoxuanke.com/";
    public static final String mch_id = "1517597181";
    public static final String sub_mch_id = "子商户号";

//    public static final String key="361662e6dc3a43bbb754ea6acabd751a";
    public static final String key="5ApCn4hSHDoM8HCsjWZJlMEXnAUd51sd";
    public static final String token="magicabc";
    //获取access_token
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
    //获取jsapi_ticket
    public static final String jsapi_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
    //网页授权
    public static final String scope_snsapi_base = "snsapi_base";
    public static final String scope_snsapi_userinfo = "snsapi_userinfo";
    public static final String authorize = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    public static final String web_access_token= "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code=CODE&grant_type=authorization_code";
    public static final String refresh_token= "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APPID+"&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    public static final String sns_userInfo= "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static final String isInvalid = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
    //菜单管理
    public static final String menu_create = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static final String menu_get= "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    public static final String menu_delete= "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    public static final String menu_addconditional= "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
    public static final String menu_delconditional= "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";
    public static final String menu_trymatch= "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN";
    public static final String get_current_selfmenu_info= "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
    //消息管理
    public static final String getcallbackip = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
    public static final String kfaccount_add = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
    public static final String kfaccount_update = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";
    public static final String kfaccount_del = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN";
    public static final String kfaccount_uploadheadimg = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT";
    public static final String getkflist = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
    public static final String message_custom_send = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    public static final String message_custom_typing = "https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN";
    public static final String media_uploadimg = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
    public static final String media_uploadnews = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    public static final String message_mass_sendall = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    public static final String message_mass_send = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
    public static final String message_mass_delete = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";
    public static final String message_mass_preview = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
    public static final String message_mass_get = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN";
    public static final String message_mass_speed_get = "https://api.weixin.qq.com/cgi-bin/message/mass/speed/get?access_token=ACCESS_TOKEN";
    public static final String message_mass_speed_set = "https://api.weixin.qq.com/cgi-bin/message/mass/speed/set?access_token=ACCESS_TOKEN";
    public static final String template_api_set_industry = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    public static final String template_get_industry = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    public static final String template_api_add_template = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
    public static final String template_get_all_private_template = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    public static final String template_del_private_template = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
    public static final String message_template_send = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    public static final String subscribemsg = "https://mp.weixin.qq.com/mp/subscribemsg?action=get_confirm&appid="+APPID+"&scene=SCENE&template_id=TEMPLATE_ID&redirect_url=REDIRECT_URL&reserved=RESERVED#wechat_redirect";
    public static final String message_template_subscribe = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=ACCESS_TOKEN";
    public static final String clear_quota = "https://api.weixin.qq.com/cgi-bin/clear_quota?access_token=ACCESS_TOKEN";
    public static final String get_current_autoreply_info = "https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN";
    //素材管理
    public static final String media_upload = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    public static final String media_get = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    public static final String media_get_jssdk = "https://api.weixin.qq.com/cgi-bin/media/get/jssdk?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    public static final String material_add_news = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
    public static final String material_add_material = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
    public static final String material_get_material = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
    public static final String material_del_material = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
    public static final String meterial_update_news = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
    public static final String meterial_get_materialcount = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
    public static final String meterial_batchget_material = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
    //微信卡券-获取api_ticket
    public static final String api_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";
    //用户管理
    public static final String user_info= "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static final String user_info_batchget= "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
    //公众号支付

//    public static final String key="";

    public static final String device_info = "WEB";//自定义参数,PC网页或公众号内支付可以传"WEB
    public static final String unifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String wxpay_success = MYSERVER+"wxpay_success";
    public static final String LOGIN_API = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String LOGIN_API_WEB = "https://api.weixin.qq.com/sns/oauth2";
    public static final String CUSTOMER_TOKEN = "shan1";
    public static final String SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String SEND_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/upload";

    //生成二维码
    public static final String WXACODE = "https://api.weixin.qq.com/wxa/getwxacode?access_token=ACCESS_TOKEN";
}
