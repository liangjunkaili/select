package com.course.selection.service;

import com.course.selection.bean.User;
import com.course.selection.dto.Result;
import com.course.selection.dto.UserDto;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {

    Result login(String code, String encryptedData, String iv, String channel, String ip, Integer uid) throws IOException;
    List<User> queryUsers(Map<String,Object> param);

    UserDto insert(String nickname, Integer sex, String province, String city, String country, String headimgurl, String openid, JSONArray privilege, String unionid);

    boolean checkSignature(HttpServletRequest request);

//    Result login(String code, String ip) throws Exception;
}
