package com.course.selection.dao;

import com.course.selection.bean.Function;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FunctionDao {
    List<Function> findByRole(@Param("roleid") String roleid);
}
