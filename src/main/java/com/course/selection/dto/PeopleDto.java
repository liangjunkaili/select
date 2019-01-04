package com.course.selection.dto;

import com.course.selection.bean.OrderPeopleList;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName PeopleDto
 * @Description TODO
 * @Author junliang
 * @Date 2019/1/3 8:54 PM
 * @Version 1.0
 **/
@Data
@ToString
@Builder
public class PeopleDto {
    private List<OrderPeopleList> orderPeopleLists;
    private int num;
}
