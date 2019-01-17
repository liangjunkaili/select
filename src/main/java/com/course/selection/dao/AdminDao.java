package com.course.selection.dao;

import com.course.selection.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminDao {
    List<Admin> findAdminByType(@Param("role") String role);
    void insert(@Param("admin") Admin admin);
    void update(@Param("admin") Admin admin);

    Admin findByAccount(@Param("account") String account);
}
