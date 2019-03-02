package com.course.selection.service.impl;

import com.course.selection.bean.Order;
import com.course.selection.bean.OrderPeopleList;
import com.course.selection.dao.OrderDao;
import com.course.selection.dao.OrderPeopleListDao;
import com.course.selection.dto.PeopleDto;
import com.course.selection.dto.Result;
import com.course.selection.service.OrderPeopleListService;
import com.course.selection.service.OrderService;
import com.course.selection.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OrderPeopleListServiceImpl implements OrderPeopleListService {
    @Autowired
    private OrderPeopleListDao orderPeopleListDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderService orderService;

    @Override
    public Result getMyPoster(Integer uid) {
        List<OrderPeopleList> orderPeopleLists = orderPeopleListDao.findByUid(uid);
        return ResultUtil.success(orderPeopleLists);
    }

    @Override
    public Result addPeople(Integer oid, String name, Integer gender, String phone, String birthdayTime, String address) {
        Order order = orderDao.findByOid(oid);
        OrderPeopleList orderPeopleList = OrderPeopleList.builder()
                .address(address)
                .birthdayTime(birthdayTime)
                .gender(gender)
                .name(name)
                .oid(oid)
                .phone(phone)
                .state(0)
                .title(order.getTitle())
                .build();
        orderPeopleListDao.insert(orderPeopleList);
        return ResultUtil.success(orderPeopleList);
    }

    @Override
    public Result getPeople(Integer oid) {
        List<OrderPeopleList> orderPeopleLists = orderPeopleListDao.findByOid(oid);
        Order order = orderDao.findByOid(oid);
        PeopleDto peopleDto = PeopleDto.builder().num(order.getNum()).orderPeopleLists(orderPeopleLists).build();
        return ResultUtil.success(peopleDto);
    }

    @Override
    public Result getPeopleById(Integer id) {
        OrderPeopleList orderPeopleList = orderPeopleListDao.findById(id);
        Order order = orderDao.findByOid(orderPeopleList.getOid());
        if(order.getGid()==7){
            orderPeopleList.setCode("XT-01");
        }else if(order.getGid()==8){
            if("小学版".equals(order.getType1())){
                orderPeopleList.setCode("XY-14");
            }else{
                orderPeopleList.setCode("XY-11");
            }
        }else if(order.getGid()==9){
            orderPeopleList.setCode("XY-15");
        }else if(order.getGid()==10){
            orderPeopleList.setCode("XY-19");
        }else if(order.getGid()==11){
            orderPeopleList.setCode("XY-18");
        }
        return ResultUtil.success(orderPeopleList);
    }

    @Override
    public Result updatePeople(Integer id, String name, Integer gender, String phone, String birthdayTime, String address) {
        OrderPeopleList byId = orderPeopleListDao.findById(id);
        OrderPeopleList orderPeopleList = OrderPeopleList.builder()
                .address(address)
                .birthdayTime(birthdayTime)
                .gender(gender)
                .name(name)
                .phone(phone)
                .state(byId.getState())
                .id(id)
                .build();
        orderPeopleListDao.update(orderPeopleList);

        return ResultUtil.success(orderPeopleList);
    }

    @Override
    public Result test(Integer id) {
        OrderPeopleList orderPeopleList = orderPeopleListDao.findById(id);
        orderPeopleList.setState(1);
        orderPeopleListDao.update(orderPeopleList);
        /**
         * 查看同一订单下是否都完成测评
         */
        Boolean testSuccess = orderService.testSuccess(orderPeopleList.getOid());
        return ResultUtil.success(testSuccess);
    }

    @Override
    public List<OrderPeopleList> findOrderPeopleList(Map<String, Object> param) {
        return orderPeopleListDao.findOrderPeopleList(param);
    }

    @Override
    public void updatePeople2(Integer oid, String title, String rurl) {
        OrderPeopleList byId = orderPeopleListDao.findById(oid);
        OrderPeopleList orderPeopleList = OrderPeopleList.builder()
                .title(title)
                .rurl(rurl)
                .id(oid)
                .build();
        orderPeopleListDao.update(orderPeopleList);

//        return ResultUtil.success(orderPeopleList);
    }

    @Override
    public Integer findAll() {
        return orderPeopleListDao.findSize();
    }


}
