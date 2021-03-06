package com.course.selection.controller;

import com.course.selection.dto.Result;
import com.course.selection.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AdminService adminService;

    @PostMapping("findAccount")
    public Result findAccount(
            @RequestParam(value = "type",required = false) String type
    ){
        return adminService.findAdminByType(type);
    }

    @PostMapping("deleteAccount")
    public Result deleteAccount(
            @RequestParam(value = "account",required = false) String account
    ){
        return adminService.deleteByAccount(account);
    }

    @PostMapping("addAccount")
    public Result addAccount(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "role",required = false) String role,
            @RequestParam(value = "account",required = false) String account,
            @RequestParam(value = "password",required = false) String password
    ){
        return adminService.addAccount(name,role,account,password);
    }

    /**
     * 登陆
     * @param account
     * @param password
     * @return
     */
    @PostMapping("login")
    public Result login(
            @RequestParam(value = "account",required = false) String account,
            @RequestParam(value = "password",required = false) String password
            ){
        return adminService.login(account, password);
    }
    @PostMapping("findByAccount")
    public Result findByAccount(
            @RequestParam(value = "account",required = false) String account
    ){
        return adminService.findByAccount(account);
    }

    @PostMapping("updateAccount")
    public Result updateAccount(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "role",required = false) String role,
            @RequestParam(value = "account",required = false) String account,
            @RequestParam(value = "password",required = false) String password
    ){
        return adminService.update(name,role,account,password);
    }
}
