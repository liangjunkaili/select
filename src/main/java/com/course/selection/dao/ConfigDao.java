package com.course.selection.dao;

import com.course.selection.bean.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConfigDao {
    Config findAll();

    void update(@Param("config") Config config);
}
