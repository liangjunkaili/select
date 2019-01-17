package com.course.selection.service;

import com.course.selection.bean.User;
import com.course.selection.dto.Result;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {

    Result login(String code, String encryptedData, String iv, String channel, String ip, Integer uid) throws IOException;
    List<User> queryUsers(Map<String,Object> param);

//    Result login(String code, String ip) throws Exception;
}
