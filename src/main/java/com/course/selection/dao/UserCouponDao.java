package com.course.selection.dao;

import com.course.selection.bean.UserCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCouponDao {
    List<UserCoupon> findByUid(@Param("uid") Integer uid);

    void insert(@Param("userCoupon") UserCoupon userCoupon);

    void update(@Param("uid") Integer uid, @Param("cid") Integer cid);
}
