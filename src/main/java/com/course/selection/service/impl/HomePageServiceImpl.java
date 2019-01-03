package com.course.selection.service.impl;

import com.course.selection.bean.HomePage;
import com.course.selection.dao.HomePageDao;
import com.course.selection.dto.Result;
import com.course.selection.service.HomePageService;
import com.course.selection.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName HomePageServiceImpl
 * @Description TODO
 * @Author junliang
 * @Date 2019/1/3 4:00 PM
 * @Version 1.0
 **/
@Service("homePageService")
public class HomePageServiceImpl implements HomePageService{
    @Autowired
    private HomePageDao homePageDao;
    @Override
    public Result updateHomePage(int id, int state, int weight, String url, String img) {
        HomePage homePage = HomePage.builder()
                .id(id)
                .img(img)
                .url(url)
                .state(state)
                .weight(weight)
                .build();
        homePageDao.updateHomePage(homePage);
        return ResultUtil.success();
    }

    @Override
    public List<HomePage> queryHomePage() {
        return homePageDao.queryHomePage();
    }
}
