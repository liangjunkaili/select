package com.course.selection.service.impl;

import com.course.selection.bean.Goods;
import com.course.selection.bean.Order;
import com.course.selection.bean.OrderPeopleList;
import com.course.selection.dao.*;
import com.course.selection.dto.OrderDto;
import com.course.selection.dto.Result;
import com.course.selection.service.OrderService;
import com.course.selection.util.DateUtil;
import com.course.selection.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private OrderPeopleListDao orderPeopleListDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Override
    public Result getMyOrders(Integer uid) {
        List<Order> orders =  orderDao.findByUid(uid);
        List<OrderDto> dtos = new ArrayList<>();
        orders.forEach(order -> {
            OrderDto orderDto = OrderDto.builder()
                    .id(order.getOid())
                    .gid(order.getGid())
                    .img(order.getImg())
                    .title(order.getTitle())
                    .num(order.getNum())
                    .price(order.getPrice())
                    .state(order.getState())
                    .type1(order.getType1())
                    .type2(order.getType2())
                    .creattime(DateUtil.localDateTimeFormat(order.getCreattime(),DateUtil.FORMAT_PATTERN1))
                    .build();
            dtos.add(orderDto);
        });
        return ResultUtil.success(dtos);
    }

    @Override
    public Result orderGoods(Integer uid, Integer gid, Integer num, Integer price, String type1, String type2, Integer cid) {
        Goods goods = goodsDao.queryGoodsById(gid);
        goods.setNum(goods.getNum() + num);
        goodsDao.update(goods);
        Order order = Order.builder()
                .uid(uid)
                .gid(gid)
                .num(num)
                .type1(type1)
                .type2(type2)
                .price(price)
                .title(goods.getTitle())
                .img(goods.getImg())
                .state(0)
                .creattime(LocalDateTime.now())
                .build();
//        Coupons coupons = couponsDao.findById(cid);
//        OrderGoodsDto orderGoodsDto = OrderGoodsDto.builder()
//                .gid(goods.getId())
//                .img(goods.getImg())
//                .intro(goods.getIntro())
//                .attribute(SUtil.attributes.get(type1))
//                .service(SUtil.services.get(type2))
//                .coupons(coupons)
//                .build();
        if(cid!=null&&cid!=0) {
            userCouponDao.update(uid, cid);
        }
        orderDao.insert(order);
        return ResultUtil.success(order.getOid());
    }

    @Override
    public Boolean paySuccess(Integer oid) {
        try {
            orderDao.paySuccess(oid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean testSuccess(Integer oid) {
        List<OrderPeopleList> peoples = orderPeopleListDao.findByOid(oid);
        for (OrderPeopleList people : peoples) {
            if (people.getState() == 0) {
                return false;
            }
        }
        orderDao.testSuccess(oid);
        return true;
    }

}
