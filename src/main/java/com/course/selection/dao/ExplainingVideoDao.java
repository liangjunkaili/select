package com.course.selection.dao;

import com.course.selection.bean.ExplainingVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExplainingVideoDao {
    List<ExplainingVideo> findAll();

    void update(@Param("explainingVideo") ExplainingVideo explainingVideo);
}
