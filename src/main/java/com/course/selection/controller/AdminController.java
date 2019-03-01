package com.course.selection.controller;


import com.course.selection.bean.BroadCast;
import com.course.selection.bean.Goods;
import com.course.selection.bean.OrderPeopleList;
import com.course.selection.bean.User;
import com.course.selection.dto.Result;
import com.course.selection.service.*;
import com.course.selection.util.CosUtil;
import com.course.selection.util.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private IncomeRecordService incomeRecordService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ReasonService reasonService;
    @Autowired
    private HomePageService homePageService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ApplyForService applyForService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderPeopleListService orderPeopleListService;
    @Autowired
    private BroadCastService broadCastService;
    public static final String IMG_URL = "https://qinmi-1258355325.cos.ap-beijing.myqcloud.com/";

    @PostMapping("addIncomeRecord")
    public Result addIncomeRecord(
            @RequestParam(value = "uid", required = true) Integer uid,
            @RequestParam(value = "price", required = true) Integer price,
//            @RequestParam(value = "regTime",required = true) String regTime,
            @RequestParam(value = "other", required = true) String other,
            @RequestParam(value = "type", required = true) Integer type,
            @RequestParam(value = "img", required = true) MultipartFile file

    ) {
//        long timestemp = new Date().getTime();
        log.info(uid + "|" + price + "|" + other + "|" + type);
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("price", price);
        map.put("regTime", LocalDateTime.now());
        map.put("other", other);
        map.put("type", type);
        try {
            map.put("img", CosUtil.getImgUrl(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = incomeRecordService.addIncomeRecord(map);
        return ResultUtil.success();
    }

    @PostMapping("addGoods")
    @ApiOperation(value = "添加商品",notes = "添加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "标题",required = true,dataType = "String"),
            @ApiImplicitParam(name = "detail",value = "详情",required = true,dataType = "String"),
            @ApiImplicitParam(name = "file1",value = "轮播图1",required = false,dataType = "File")
    })
    public Result addGoods(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "detail") String detail,
            @RequestParam(value = "file1",required = false) MultipartFile file1,//轮播图1
            @RequestParam(value = "file2",required = false) MultipartFile file2,//轮播图2
            @RequestParam(value = "file3",required = false) MultipartFile file3,//轮播图3
            @RequestParam(value = "file4",required = false) MultipartFile file4,//主图
            @RequestParam(value = "file5",required = false) MultipartFile file5,//邀请卡
            @RequestParam(value = "oprice") Integer oprice,
            @RequestParam(value = "price") Integer price,
            @RequestParam(value = "label") String label,
            @RequestParam(value = "intro") String intro,
            @RequestParam(value = "bamount") Integer bamount,
            @RequestParam(value = "flag") Integer flag,
            @RequestParam(value = "weight") Integer weight,
            @RequestParam(value = "num") Integer num,
            @RequestParam(value = "iprice") Integer iprice,
            @RequestParam(value = "attribute") Integer attribute,//有就1没有就0
            @RequestParam(value = "service") Integer service,//有就1没有就0
            @RequestParam(value = "recommend") String recommend
    ) {
        String img1 = null;
        String img2 = null;
        String img3 = null;
        String img = null;
        String icard = null;
        try {
            if(file1!=null) {
                img1 = CosUtil.getImgUrl(file1);
            }
            if(file2!=null) {
                img2 = CosUtil.getImgUrl(file2);
            }
            if(file3!=null) {
                img3 = CosUtil.getImgUrl(file3);
            }
            if(file4!=null) {
                img = CosUtil.getImgUrl(file4);
            }
            if(file5!=null) {
                icard = CosUtil.getImgUrl(file5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (img3 == null) {
            img1 = img1 + "|" + img2 ;
        } else if (img2 == null) {
        }else {
            img1 = img1 + "|" + img2 + "|" + img3;
        }
            Goods goods = Goods.builder()
                    .bamount(bamount)
                    .detail(detail)
                    .flag(flag)
                    .icard(icard)
                    .img(img)
                    .img1(img1)
                    .img2(null)
                    .img3(null)
                    .intro(intro)
                    .label(label)
                    .num(num)
                    .oprice(oprice)
                    .price(price)
                    .weight(weight)
                    .title(title)
                    .iprice(iprice)
                    .attribute(attribute)
                    .service(service)
                    .state(1)
                    .recommend(recommend)
                    .build();
            goodsService.addGoods(goods);
            return ResultUtil.success();
        }


    @PostMapping("addReason")
    @ApiOperation("添加返现理由")
    public Result addReason(@RequestParam("reason") String reason) {
        return reasonService.addReason(reason);
    }

    /**
     *
     * @param state 商品状态
     * @param currentPage    当前页数
     * @param pageSize   每页显示的总记录数
     * @return
     */
    @GetMapping("getGoods")
    @ApiOperation("获取商品信息")
    public Result getGoods(
            @RequestParam("state") Integer state,
            @RequestParam("currentPage") Integer currentPage,
            @RequestParam("pageSize") Integer pageSize
    ){
        return goodsService.getGoodsByAdmin(state,currentPage,pageSize);
    }

    @GetMapping("upperShelf")
    @ApiOperation("上架")
    public Result upperShelf(
            @RequestParam("gid") Integer gid
    ){
        return goodsService.upperShelf(gid);
    }
    @GetMapping("lowerShelf")
    @ApiOperation("下架")
    public Result lowerShelf(
            @RequestParam("gid") Integer gid
    ){
        return goodsService.lowerShelf(gid);
    }

    @GetMapping("getReason")
    @ApiOperation("获取返现理由")
    public Result getReason() {
        return reasonService.getReason();
    }

    @PostMapping("upOrDownLine")
    public Result upOrDownLine(@RequestParam(value = "id") int id,
                         @RequestParam(value = "img",required = false) String img,
                         @RequestParam(value = "state") int state,
                         @RequestParam(value = "weight",required = false) int weight,
                         @RequestParam(value = "url",required = false) String url){
        return homePageService.updateHomePage(id,state,weight,url,img);
    }

    @GetMapping("getHomePage")
    @ApiOperation("获取首页轮播图")
    public Result getHomePage() {
        return ResultUtil.success(homePageService.queryHomePage());
    }

    @PostMapping("getAllOrders")
    @ApiOperation("后台-我的订单列表")
    public Result getAllOrders(@RequestParam(value = "gid" ,required = false) Integer gid,
                               @RequestParam(value = "uid" ,required = false) Integer uid,
                               @RequestParam(value = "state" ,required = false,defaultValue = "-1") Integer state,
                               @RequestParam("pageIndex") Integer pageIndex,
                               @RequestParam("pageSize") Integer pageSize) {
        return orderService.getAllOrders(gid, uid, state,pageIndex,pageSize);
    }

    @PostMapping("submitApplyFor")
    @ApiOperation("后台-提交返现申请")
    public Result submitApplyFor(@RequestParam(value = "reason" ) String reason,
                                 @RequestParam(value = "phone" ) String phone,
                                 @RequestParam(value = "money" ) Integer money,
                                 @RequestParam(value = "detail" ) String detail,
                                 @RequestParam(value = "certificate" ) MultipartFile certificate,
                                 @RequestParam(value = "remark" ) String remark) {
        String url = "";
        try {
            url = CosUtil.getImgUrl(certificate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return applyForService.addApplyFor(url,reason,remark,phone,money,detail);
    }
    @GetMapping("getApplyFor")
    @ApiOperation("返回申请列表")
    public Result getApplyFor(@RequestParam(value = "state" ,required = false) Integer state,
                              @RequestParam(value = "reason" ,required = false) String reason,
                              @RequestParam("pageIndex") Integer pageIndex,
                              @RequestParam("pageSize") Integer pageSiz) {
        return applyForService.queryApplyFor(pageIndex,pageSiz,state,reason);
    }
    @PostMapping("updateApplyFor")
    @ApiOperation("审批")
    public Result updateApplyFor(@RequestParam(value = "state") Integer state,
                              @RequestParam(value = "id") Integer id) {
        return applyForService.updateApplyFor(state,id);
    }
    @GetMapping("getUsers")
    @ApiOperation("返回注册用户列表")
    public Result getUsers(@RequestParam("pageIndex") Integer pageIndex,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam(value = "nickname",required = false) Integer nickname,
                           @RequestParam(value = "channel",required = false) Integer channel,
                           @RequestParam(value = "referrer",required = false) Integer referrer,
                           @RequestParam(value = "phone",required = false) Integer phone){
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("nickname",nickname);
        map.put("channel",channel);
        map.put("referrer",referrer);
        map.put("phone",phone);
        List<User> userList = userService.queryUsers(map);
        return ResultUtil.success(userList);
    }
    @GetMapping("getOrderPeopleList")
    @ApiOperation("返回测评用户列表")
    public Result getOrderPeopleList(@RequestParam("pageIndex") Integer pageIndex,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam(value = "oid",required = false) Integer oid,
                                     @RequestParam(value = "name",required = false) Integer name,
                                     @RequestParam(value = "birthday_time",required = false) Integer birthday_time,
                                     @RequestParam(value = "address",required = false) Integer address,
                                     @RequestParam(value = "state",required = false) Integer state,
                                     @RequestParam(value = "phone",required = false) Integer phone){
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        map.put("oid",oid);
        map.put("name",name);
        map.put("birthday_time",birthday_time);
        map.put("address",address);
        map.put("state",state);
        map.put("phone",phone);
        List<OrderPeopleList> orderPeopleLists = orderPeopleListService.findOrderPeopleList(map);
        return ResultUtil.success(orderPeopleLists);
    }


    @GetMapping("updateOrderPeopleList")
    @ApiOperation("编辑测评用户列表")
    public Result updateOrderPeopleList(

                                     @RequestParam(value = "oid",required = false) Integer oid,
                                     @RequestParam(value = "name",required = false) String name,
                                     @RequestParam(value = "sex",required = false) Integer sex,
                                     @RequestParam(value = "phone",required = false) String phone,
                                     @RequestParam(value = "birthday_time",required = false) String birthday_time,
                                     @RequestParam(value = "address",required = false) String address,
                                     @RequestParam(value = "title",required = false) String title,
                                     @RequestParam(value = "rurl",required = false) String rurl//报告地址
    ){
        orderPeopleListService.updatePeople(oid,name,sex,phone,birthday_time,address);
        orderPeopleListService.updatePeople2(oid,title,rurl);
        return ResultUtil.success();
    }
    @PostMapping("updateBroadCast")
    @ApiOperation("编辑广播")
    public Result updateBroadCast(@RequestParam(value = "state") Integer state,
                                  @RequestParam(value = "title" ) String title,
                                  @RequestParam(value = "address" ) String address,
                                 @RequestParam(value = "id") Integer id) {
        broadCastService.updateBroadCast(title,address,id,state);
        return ResultUtil.success();
    }
    @PostMapping("addBroadCast")
    @ApiOperation("后台-提交返现申请")
    public Result addBroadCast(@RequestParam(value = "title" ) String title,
                               @RequestParam(value = "address" ) String address) {
        broadCastService.addBroadCast(title,address);
        return ResultUtil.success();
    }
    @GetMapping("getBroadCasts")
    @ApiOperation("返回广播列表")
    public Result getBroadCasts() {
        List<BroadCast> broadCasts = broadCastService.queryBroadCasts();
        return ResultUtil.success(broadCasts);
    }
}
