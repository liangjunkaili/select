package com.course.selection.service;

import com.course.selection.dto.Result;
import org.springframework.stereotype.Service;

public interface AdminService {
    Result findAdminByType(String type);

    Result addAccount(String name, String role, String account, String password);

    Result findByAccount(String account);

    Result update(String name, String role, String account, String password);

    Result login(String account, String password);

    Result deleteByAccount(String account);
}
