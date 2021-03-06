package com.course.selection.bean;

import lombok.*;

import java.time.LocalDate;

/**
 * @ClassName UserCoupon
 * @Description TODO
 * @Author junliang
 * @Date 2018/12/20 9:17 AM
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserCoupon {
    private Integer id;
    private Integer uid;
    private Integer cpid;
    private LocalDate invalidDate;
    private Integer state;
}
