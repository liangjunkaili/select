package com.course.selection.dao;

import com.course.selection.bean.ApplyFor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface ApplyForDao {
    int addApplyFor(@Param("applyFor")ApplyFor applyFor);
    int updateApplyFor(@Param("applyFor")ApplyFor applyFor);
    List<ApplyFor> queryApplyFor(Map<String,Object> param);
}
