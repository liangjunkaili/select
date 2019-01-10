package com.course.selection.service;

import com.course.selection.dto.Result;

public interface ApplyForService {
    Result addApplyFor(String certificate, String reason, String remark, String phone, Integer money, String detail);
    Result updateApplyFor(Integer state,Integer id);
    Result queryApplyFor(Integer pageIndex,Integer pageSize,Integer state,String reason);
}
