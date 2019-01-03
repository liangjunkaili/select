package com.course.selection.dao;

import com.course.selection.bean.HomePage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomePageDao {
    int updateHomePage(@Param("homepage")HomePage homePage);
    List<HomePage> queryHomePage();
}
