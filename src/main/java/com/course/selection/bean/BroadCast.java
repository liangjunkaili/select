package com.course.selection.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BroadCast {
    private int id;
    private int state;
    private String date;
    private String title;
    private String address;
}
