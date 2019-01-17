package com.course.selection.dao;

import com.course.selection.bean.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
    List<Role> findAll();
}
