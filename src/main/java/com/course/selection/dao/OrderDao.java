package com.course.selection.dao;

import com.course.selection.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao {
    List<Order> findByUid(@Param("uid") Integer uid);

    List<Order> findOrders(Map<String,Object> param);

    Order findByOid(@Param("oid") Integer oid);

    void insert(@Param("order") Order order);

    void paySuccess(@Param("oid") Integer oid);

    void testSuccess(@Param("oid") Integer oid);
}
