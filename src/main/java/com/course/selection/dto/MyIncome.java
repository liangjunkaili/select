package com.course.selection.dto;

import com.course.selection.bean.IncomeRecord;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class MyIncome {
    private Integer totalPrice;
    private Integer num;
    private List<IncomeRecord> records;
}
