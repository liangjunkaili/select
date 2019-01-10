package com.course.selection.dao;

import com.course.selection.bean.OrderPeopleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderPeopleListDao {
    void insert(@Param("orderPeopleList") OrderPeopleList orderPeopleList);

    List<OrderPeopleList> findByUid(@Param("uid") Integer uid);

    List<OrderPeopleList> findByOid(@Param("oid") Integer oid);

    List<OrderPeopleList> findOrderPeopleList(Map<String,Object> param);

    void update(@Param("orderPeopleList") OrderPeopleList orderPeopleList);

    OrderPeopleList findById(@Param("id") Integer id);
}
