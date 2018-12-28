package com.course.selection.dto;

import com.course.selection.bean.Coupons;
import com.course.selection.special.Attribute;
import com.course.selection.special.Service;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@ApiModel("生成订单是我返回的数据")
public class OrderGoodsDto {
    private Integer gid;
    private String img;
    private String title;
    private String intro;
    private Attribute attribute;
    private Service service;
    private Coupons coupons;
}
