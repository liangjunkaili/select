package com.course.selection.dto;

import com.course.selection.bean.Order;
import com.course.selection.bean.OrderPeopleList;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
@ApiModel("后台-订单列表")
public class OrderDetailDto {
    private Order order;
    private List<OrderPeopleList> orderPeopleListList;
}
