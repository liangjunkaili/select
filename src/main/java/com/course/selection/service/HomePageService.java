package com.course.selection.service;

import com.course.selection.bean.HomePage;
import com.course.selection.dto.Result;

import java.util.List;

public interface HomePageService {
    Result updateHomePage(int id, int state, int weight, String url, String img);
    List<HomePage> queryHomePage();
}
