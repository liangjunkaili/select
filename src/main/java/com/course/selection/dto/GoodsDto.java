package com.course.selection.dto;

import com.course.selection.special.Attribute;
import com.course.selection.special.Service;
import lombok.*;

import java.util.List;


@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto {
    private int id;
    /**
     * 主图
     */
    private String img;
    /**
     * 轮播图
     */
    private String[] img1;
    private String img2;
    private String img3;
    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String intro;
    /**
     * 标签
     */
    private String[] label;
    /**
     * 详情
     */
    private String detail;
    /**
     * 邀请卡
     */
    private String icard;
    /**
     * 返现金额
     */
    private Integer bamount;
    /**
     * 单位为分，都为整数
     */
    private int price;
    private int oprice;
    /**
     * 优惠券
     */
    private int flag;
    /**
     *  商品数量
     */
    private int num;
    /**
     * 轮播图的权重
     */
    private int weight;
    /**
     * 递增价
     */
    private int iprice;
    /**
     * 属性
     */
    private List<Attribute> attribute;
    /**
     * 服务
     */
    private List<Service>  service;
    private Integer  state;
}
