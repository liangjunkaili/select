package com.course.selection.service.impl;

import com.course.selection.bean.ApplyFor;
import com.course.selection.dao.ApplyForDao;
import com.course.selection.dto.Result;
import com.course.selection.service.ApplyForService;
import com.course.selection.util.DateUtil;
import com.course.selection.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("applyForService")
public class ApplyForServiceImpl implements ApplyForService {
    @Autowired
    private ApplyForDao applyForDao;
    @Override
    public Result addApplyFor(String certificate, String reason, String remark, String phone, Integer money, String detail) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = DateUtil.localDateTimeFormat(localDateTime,DateUtil.FORMAT_PATTERN1);
        ApplyFor applyFor = ApplyFor.builder()
                .certificate(certificate)
                .reason(reason)
                .remark(remark)
                .phone(phone)
                .money(money)
                .date(date)
                .state(1)
                .detail(detail).build();
        applyForDao.addApplyFor(applyFor);
        return ResultUtil.success();
    }

    @Override
    public Result updateApplyFor(Integer state, Integer id) {
        ApplyFor applyFor = ApplyFor.builder().state(state)
                .id(id).build();
        applyForDao.updateApplyFor(applyFor);
        return ResultUtil.success();
    }

    @Override
    public Result queryApplyFor(Integer pageIndex, Integer pageSize, Integer state, String reason) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("state",state);
        map.put("reason",reason);
        List<ApplyFor> applyFors = applyForDao.queryApplyFor(map);
        return ResultUtil.success(applyFors);
    }
}
