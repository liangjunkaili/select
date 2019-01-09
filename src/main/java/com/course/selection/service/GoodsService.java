package com.course.selection.service;

import com.course.selection.bean.Goods;
import com.course.selection.dto.Result;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Description TODO
 * @Author junliang
 * @Date 2018/12/17 7:35 AM
 * @Version 1.0
 **/
public interface GoodsService {
    List<Goods> queryGoods();

    Result index();


    void addGoods(Goods goods);

    Result getGoodById(Integer id, Integer uid);

    Result getGoodsByAdmin(Integer state, Integer currentPage, Integer pageSize);

    Result upperShelf(Integer gid);

    Result lowerShelf(Integer gid);
}
