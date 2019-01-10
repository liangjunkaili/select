package com.course.selection.service;

import com.course.selection.bean.BroadCast;

import java.util.List;

public interface BroadCastService {
    int addBroadCast(String title,String address);
    int updateBroadCast(String title,String address,Integer id,Integer state);
    List<BroadCast> queryBroadCasts();
}
