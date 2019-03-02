package com.course.selection.service;

import com.course.selection.bean.OrderPeopleList;
import com.course.selection.dto.Result;

import java.util.List;
import java.util.Map;

public interface OrderPeopleListService {
    Result getMyPoster(Integer uid);

    Result addPeople( Integer oid, String name, Integer gender, String phone, String birthdayTime, String address);

    Result getPeople(Integer oid);
    Result getPeopleById(Integer id);

    Result updatePeople(Integer id, String name, Integer gender, String phone, String birthdayTime, String address);

    /**
     * 完成测评
     * @param id
     * @return
     */
    Result test(Integer id);
    List<OrderPeopleList> findOrderPeopleList(Map<String,Object> param);

    void updatePeople2(Integer oid, String title, String rurl);

    Integer findAll();
}
