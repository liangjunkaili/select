package com.course.selection.bean;

import lombok.*;

/**
 * @ClassName HomePage
 * @Description TODO
 * @Author junliang
 * @Date 2019/1/3 3:34 PM
 * @Version 1.0
 **/
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HomePage {
    private Integer id;
    private String img;
    private String url;
    private Integer state;
    private Integer weight;
}
