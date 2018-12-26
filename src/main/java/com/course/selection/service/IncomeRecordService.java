package com.course.selection.service;

import com.course.selection.dto.Result;

import java.util.Map;

public interface IncomeRecordService {
    int addIncomeRecord(Map<String, Object> param);

    Result getMyIncome(Integer uid);
}
