package com.course.selection.dao;

import com.course.selection.bean.BroadCast;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BroadCastDao {
    int addBroadCast(@Param("broadCast")BroadCast broadCast);
    int updateBroadCast(@Param("broadCast")BroadCast broadCast);
    List<BroadCast> queryBroadCasts();
}
