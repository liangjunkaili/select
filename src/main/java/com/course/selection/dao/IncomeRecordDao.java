package com.course.selection.dao;

import com.course.selection.bean.IncomeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface IncomeRecordDao {
    int addIncomeRecord(Map<String,Object> param);
    List<IncomeRecord> queryIncomeRecords(@Param("uid") Integer uid);
}
