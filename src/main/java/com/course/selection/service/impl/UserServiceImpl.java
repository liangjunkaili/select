package com.course.selection.service.impl;

import com.course.selection.bean.User;
import com.course.selection.dao.GoodsDao;
import com.course.selection.dao.UserDao;
import com.course.selection.dto.Result;
import com.course.selection.dto.UserDto;
import com.course.selection.enums.ResultEnum;
import com.course.selection.service.UserService;
import com.course.selection.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author L.W
 */
@Service
@Log4j2
public class UserServiceImpl implements UserService {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    @Autowired
    private ObjectMapper objectMapper;
    //    @Autowired
//    private TokenService tokenService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private GoodsDao goodsDao;
    private static final String ERR_MSG = "errmsg";

    @Override
    public Result login(String code, String encryptedData, String iv, String channel, String ip, Integer shareId) throws IOException {
        if (shareId != null) {
            channel = "分享";
        } else {
//            channel = AppUtil.getChannel(channel);
            channel = "Other";
        }
        String wxUser = HttpUtil.get(WXConfiguration.LOGIN_API, loginMap(code));
        if (!StringUtil.isNullOrEmpty(wxUser)) {
            Map<String, Object> userMap = objectMapper.readValue(wxUser, Map.class);
            String sessionKey = (String) userMap.get("session_key");
            String openId = (String) userMap.get("openid");
            if (userMap.get(ERR_MSG) != null || sessionKey == null || openId == null) {
                log.error("微信登陆出错-->userMap:{}", userMap);
            }
            //查询该用户是否存在
            User user = userDao.findOneByOpenId(openId);
            log.info("iv:{}", iv);
            if (iv != null && iv != "") {
                //解密数据
                String userData = AESUtil.aesDecryptByBytes(encryptedData.replaceAll(StringUtil.BLANK_TEXT, StringUtil.PLUS),
                        sessionKey.replaceAll(StringUtil.BLANK_TEXT, StringUtil.PLUS),
                        iv.replaceAll(StringUtil.BLANK_TEXT, StringUtil.PLUS));
                if (StringUtil.isNullOrEmpty(userData)) {
                    log.error("用户数据解密出错，encryptedData{},sessionKey{},iv{}", encryptedData, sessionKey, iv);
                    return ResultUtil.error(ResultEnum.ENCRYPT_ERR);
                }
                userMap.putAll(objectMapper.readValue(userData, Map.class));
            }
            if (user == null) {
                //数据库插入一条新数据
                user = buildUser(userMap, channel, ip, shareId);
                log.info("user:{}", user);
                userDao.insert(user);
            } else {
                log.info("avatarUrl:{}", String.valueOf(userMap.get("avatarUrl")));
                log.info("nickName:{}", String.valueOf(userMap.get("nickName")));
                log.info("gender:{}", String.valueOf(userMap.get("gender")));
                user.setAvatar(String.valueOf(userMap.get("avatarUrl")));
                user.setNickname(String.valueOf(userMap.get("nickName")));
                user.setGender((Integer) userMap.get("gender"));
                //跟新头像 昵称
                userDao.update(user.getAvatar(), user.getNickname(), user.getGender(), user.getUid());
            }
            //生成用户token
//            String token = tokenService.generateToken(user.getUid());

            //返回用户信息
            UserDto userDTO = UserDto.builder()
                    .avatar(user.getAvatar())
//                    .token(token)
                    .nickName(user.getNickname())
                    .uid(user.getUid())
                    .income(user.getIncome())
                    .poster(user.getPoster())
                    .openId(user.getOpenId())
                    .build();
            return ResultUtil.success(userDTO);
        }
        return null;
    }

    @Override
    public List<User> queryUsers(Map<String, Object> param) {
        return userDao.queryUsers(param);
    }

    @Override
    public UserDto insert(String nickname, Integer sex, String province, String city, String country, String headimgurl, String openid, JSONArray privilege,String unionid,String referrer) {
        //查询该用户是否存在
        User user = userDao.findOneByOpenId(openid);
        if(user==null) {
            user = User.builder()
                    .avatar(headimgurl)
                    .channel(null)
                    .gender(sex)
                    .ip(null)
                    .nickname(nickname)
                    .openId(openid)
                    .unionId(unionid)
                    .regTime(LocalDateTime.now())
                    .city(city)
                    .province(province)
                    .country(country)
                    .income(0)
                    .orders(0)
                    .poster("null")
                    .referrer(referrer)
                    .build();
            userDao.insert(user);
        }else{
            //跟新头像 昵称
            user.setAvatar(headimgurl);
            user.setNickname(nickname);
            user.setGender(sex);
            userDao.update(user.getAvatar(), user.getNickname(), user.getGender(), user.getUid());
        }
        UserDto userDTO = UserDto.builder()
                .avatar(user.getAvatar())
//                    .token(token)
                .nickName(user.getNickname())
                .uid(user.getUid())
                .income(user.getIncome())
                .poster(user.getPoster())
                .openId(user.getOpenId())
                .build();
        return userDTO;
    }

    @Override
    public boolean checkSignature(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        List<String> params = new ArrayList<String>(3);
        params.add(WXConfiguration.token);
        params.add(timestamp);
        params.add(nonce);
        params.sort(Comparator.naturalOrder());
        String temp = encode(params.get(0) + params.get(1) + params.get(2));
        if (temp.equals(signature)) {
            log.info("signature verification true");
            return true;
        }
        log.info("signature verification false");
        return false;
    }

    @Override
    public int findSize() {
        return userDao.findSize();
    }

    private static String encode(String str) {
        try {
            if (str != null) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                messageDigest.update(str.getBytes("UTF-8"));
                return getFormattedText(messageDigest.digest());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    private User buildUser(Map<String, Object> userMap, String channel, String ip, Integer shareId) {
        return User.builder()
                .avatar(String.valueOf(userMap.get("avatarUrl")))
                .channel(channel)
                .gender((Integer) userMap.get("gender"))
                .ip(ip)
                .nickname((String) userMap.get("nickName"))
                .openId((String) userMap.get("openid"))
                .unionId((String) userMap.get("unionId"))
                .regTime(LocalDateTime.now())
                .city((String) userMap.get("city"))
                .province((String) userMap.get("province"))
                .country((String) userMap.get("country"))
                .income(0)
                .orders(0)
                .poster("null")
                .build();
    }

    private Map<String, String> loginMap(String code) {
        Map<String, String> loginMap = new HashMap<>(4);
        loginMap.put("appid", WXConfiguration.APPID);
        loginMap.put("secret", WXConfiguration.APPSECRET);
        loginMap.put("js_code", code);
        loginMap.put("grant_type", "authorization_code");
        log.info("loginMap:{}", loginMap);
        return loginMap;
    }


}
