package com.course.selection.service.impl;

import com.course.selection.bean.Admin;
import com.course.selection.bean.Function;
import com.course.selection.bean.Role;
import com.course.selection.dao.AdminDao;
import com.course.selection.dao.FunctionDao;
import com.course.selection.dao.RoleDao;
import com.course.selection.dto.AccountDto;
import com.course.selection.dto.Result;
import com.course.selection.enums.ResultEnum;
import com.course.selection.service.AdminService;
import com.course.selection.util.MD5Util;
import com.course.selection.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private FunctionDao functionDao;

    @Override
    public Result findAdminByType(String type) {
        List<Admin> admins = adminDao.findAdminByType(type);
        List<Role> roles = roleDao.findAll();
        for (Admin admin : admins
        ) {
            for (Role role : roles
            ) {
                if (admin.getRole().equals(role.getType())) {
                    admin.setRole(role.getName());
                }
            }
        }
        AccountDto accountDto = AccountDto.builder()
                .admins(admins)
                .roles(roles)
                .build();
        return ResultUtil.success(accountDto);
    }

    @Override
    public Result addAccount(String name, String role, String account, String password) {
        String passwd = null;
        try {
            passwd = MD5Util.md5(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Admin admin = Admin.builder()
                .account(account)
                .name(name)
                .role(role)
                .password(passwd)
                .build();
        adminDao.insert(admin);
        return ResultUtil.success();
    }

    @Override
    public Result findByAccount(String account) {
        Admin admin = adminDao.findByAccount(account);
        return ResultUtil.success(admin);
    }

    @Override
    public Result update(String name, String role, String account, String password) {
        Admin admin = adminDao.findByAccount(account);
        if(password!=null&&password!="") {
            try {
                admin.setPassword(MD5Util.md5(password));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(name!=null&&name!="") {
            admin.setName(name);
        }
        if(role!=null&&role!="") {
            admin.setRole(role);
        }
        adminDao.update(admin);
        return ResultUtil.success();
    }

    @Override
    public Result login(String account, String password) {
        String passwd = null;
        try {
            passwd = MD5Util.md5(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Admin admin = adminDao.findByAccount(account);
        if(admin.getPassword().equals(passwd)){
            List<Function> functions = functionDao.findByRole(admin.getRole());
            return ResultUtil.success(functions);
        }
        return ResultUtil.error(ResultEnum.PASSWORD_ERROR);
    }


}
