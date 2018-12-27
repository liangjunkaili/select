package com.course.selection.service.impl;

import com.course.selection.bean.Coupons;
import com.course.selection.bean.UserCoupon;
import com.course.selection.dao.CouponsDao;
import com.course.selection.dao.UserCouponDao;
import com.course.selection.dto.Result;
import com.course.selection.service.CouponsService;
import com.course.selection.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CouponsServiceImpl implements CouponsService {
    @Autowired
    private CouponsDao couponsDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Override
    public void getMyCoupons(Integer uid) {

    }

    @Override
    public Result receiveCoupons(Integer uid, Integer cid) {
        Coupons coupons = couponsDao.findById(cid);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime invalidDate = localDateTime.plusDays(coupons.getVaildTime());
        UserCoupon userCoupon = UserCoupon.builder()
                .cpid(cid)
                .uid(uid)
                .invalidDate(invalidDate)
                .state(0)
                .build();
        userCouponDao.insert(userCoupon);
        return ResultUtil.success();
    }
}
