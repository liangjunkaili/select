package com.course.selection.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyFor {
    private int id;
    private int money;
    private String reason;
    private String phone;
    private String certificate;
    private String detail;
    private String remark;
    private int state;
    private String date;
}
