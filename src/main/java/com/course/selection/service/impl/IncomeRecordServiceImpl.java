package com.course.selection.service.impl;


import com.course.selection.bean.IncomeRecord;
import com.course.selection.dao.IncomeRecordDao;
import com.course.selection.dto.MyIncome;
import com.course.selection.dto.Result;
import com.course.selection.service.IncomeRecordService;
import com.course.selection.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IncomeRecordServiceImpl
 * @Description TODO
 * @Author junliang
 * @Date 2018/12/22 7:37 AM
 * @Version 1.0
 **/
@Service("incomeRecordService")
public class IncomeRecordServiceImpl implements IncomeRecordService
{
    @Autowired
    private IncomeRecordDao incomeRecordDao;
    @Override
    public int addIncomeRecord(Map<String, Object> param) {
        return incomeRecordDao.addIncomeRecord(param);
    }


    @Override
    public Result getMyIncome(Integer uid) {
        List<IncomeRecord> incomeRecords = incomeRecordDao.queryIncomeRecords(uid);
        int price = 0;
        int num = 0;
        for (IncomeRecord incomeRecord : incomeRecords) {
            price += incomeRecord.getPrice();
        }
        num = incomeRecords.size();
        MyIncome myIncome = MyIncome.builder()
                .num(num)
                .totalPrice(price)
                .records(incomeRecords)
                .build();
        return ResultUtil.success(myIncome);
    }
}
