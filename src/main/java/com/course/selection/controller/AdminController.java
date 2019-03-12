package com.course.selection.controller;


import com.course.selection.bean.*;
import com.course.selection.dao.ConfigDao;
import com.course.selection.dao.ExplainingVideoDao;
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
    @Autowired
    private ConfigDao configDao;
    @Autowired
    private ExplainingVideoDao explainingVideoDao;
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

    /**
     * 上传图片接口
     * @param file
     * @return
     */
    @PostMapping("uploadImg")
    public Result uploadImg(
            @RequestParam(value = "file",required = false) MultipartFile file
    ){
        String img = null;
        if(file!=null) {
            try {
                img = CosUtil.getImgUrl(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResultUtil.success(img);
    }

    /**
     * 获取全局变量
     * @return
     * quan1  默认成分比例
     * quan2  预约专家总部电话
     * bzzx   帮助中心
     * jycp  基因测评
     * cpz 线下测评站
     */
    @PostMapping("getConfig")
    public Result getConfig(
    ){
        Config config = configDao.findAll();
        return ResultUtil.success(config);
    }
    @PostMapping("updateConfig")
    public Result updateConfig(
            @RequestParam(value = "quan1",required = false) String quan1,//默认成分比例
            @RequestParam(value = "quan2",required = false) String quan2,//预约专家总部电话
            @RequestParam(value = "bzzx",required = false) String bzzx,//帮助中心
            @RequestParam(value = "jycp",required = false) String jycp,//专业基因测评
            @RequestParam(value = "cpz",required = false) String cpz//线下测评站
    ){
        Config config = Config.builder()
                .bzzx(bzzx)
                .cpz(cpz)
                .jycp(jycp)
                .quan1(quan1)
                .quan2(quan2)
                .build();
        configDao.update(config);
        return ResultUtil.success(config);
    }

    @PostMapping("getVideo")
    public Result getVideo(
    ){
        List<ExplainingVideo> videoList = explainingVideoDao.findAll();
        return ResultUtil.success(videoList);
    }
    @PostMapping("updateVideo")
    public Result updateVideo(
            @RequestParam(value = "id",required = false) Integer id,
            @RequestParam(value = "video",required = false) String video,
            @RequestParam(value = "title",required = false) String title
    ){
        ExplainingVideo explainingVideo = ExplainingVideo.builder()
                .id(id)
                .video(video)
                .title(title)
                .build();
        explainingVideoDao.update(explainingVideo);
        return ResultUtil.success(explainingVideo);
    }

    @PostMapping("addGoods")
    @ApiOperation(value = "添加商品",notes = "添加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title",value = "标题",required = true,dataType = "String"),
            @ApiImplicitParam(name = "detail",value = "详情",required = true,dataType = "String")
    })
    public Result addGoods(
            @RequestParam(value = "title") String title,//标题
            @RequestParam(value = "detail") String detail,//详情
            @RequestParam(value = "file1",required = false) String file1,//轮播图1
            @RequestParam(value = "file2",required = false) String file2,//轮播图2
            @RequestParam(value = "file3",required = false) String file3,//轮播图3
            @RequestParam(value = "file4",required = false) String file4,//主图
            @RequestParam(value = "file5",required = false) String file5,//邀请卡
            @RequestParam(value = "oprice") Integer oprice,//原价
            @RequestParam(value = "price") Integer price,//现价
            @RequestParam(value = "label") String label,//标签
            @RequestParam(value = "intro") String intro,//简介
            @RequestParam(value = "bamount") Integer bamount,//返现金额
            @RequestParam(value = "flag") Integer flag,//是否有优惠券
            @RequestParam(value = "weight") Integer weight,//轮播图的权重
            @RequestParam(value = "num") Integer num,//商品数量
            @RequestParam(value = "iprice") Integer iprice,//递增价
            @RequestParam(value = "attribute") Integer attribute,//有就1没有就0 属性
            @RequestParam(value = "service") Integer service,//有就1没有就0  服务
            @RequestParam(value = "recommend") String recommend //相关推荐
    ) {
        String img1 = file1;
        String img2 = file2;
        String img3 = file3;
        String img = file4;
        String icard = file5;
//        try {
//            if(file1!=null) {
//                img1 = CosUtil.getImgUrl(file1);
//            }
//            if(file2!=null) {
//                img2 = CosUtil.getImgUrl(file2);
//            }
//            if(file3!=null) {
//                img3 = CosUtil.getImgUrl(file3);
//            }
//            if(file4!=null) {
//                img = CosUtil.getImgUrl(file4);
//            }
//            if(file5!=null) {
//                icard = CosUtil.getImgUrl(file5);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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

    @GetMapping("updateHomePage")
    @ApiOperation("首页轮播图上下架")
    public Result getHomePage(@RequestParam(value = "id" ,required = false) Integer id,
                              @RequestParam(value = "state" ,required = false) Integer state) {
        return ResultUtil.success(homePageService.updateHomePage(id,state,0,null,null));
    }

    @PostMapping("getAllOrders")
    @ApiOperation("后台-我的订单列表")
    public Result getAllOrders(@RequestParam(value = "gid" ,required = false) Integer gid,
                               @RequestParam(value = "uid" ,required = false) Integer uid,
                               @RequestParam(value = "state" ,required = false,defaultValue = "-1") Integer state,
                               @RequestParam("pageIndex") Integer pageIndex,
                               @RequestParam("pageSize") Integer pageSize) {
        return orderService.getAllOrders(gid, uid, state,(pageIndex-1)*pageSize,pageSize);
    }

    @PostMapping("updateOrder")
    @ApiOperation("确认付款")
    public Result getAllOrders(@RequestParam(value = "oid" ,required = false) Integer oid) {
        orderService.paySuccess(oid);
        return ResultUtil.success();
    }

    @PostMapping("submitApplyFor")
    @ApiOperation("后台-提交返现申请")
    public Result submitApplyFor(@RequestParam(value = "reason" ) String reason,
                                 @RequestParam(value = "phone" ) String phone,
                                 @RequestParam(value = "money" ) Integer money,
                                 @RequestParam(value = "detail" ) String detail,
                                 @RequestParam(value = "certificate" ) String certificate,
                                 @RequestParam(value = "remark" ) String remark) {
        String url = certificate;
//        try {
//            url = CosUtil.getImgUrl(certificate);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return applyForService.addApplyFor(url,reason,remark,phone,money,detail);
    }
    @GetMapping("getApplyFor")
    @ApiOperation("返回申请列表")
    public Result getApplyFor(@RequestParam(value = "state" ,required = false) Integer state,
                              @RequestParam(value = "reason" ,required = false) String reason,
                              @RequestParam("pageIndex") Integer pageIndex,
                              @RequestParam("pageSize") Integer pageSize) {
        return applyForService.queryApplyFor((pageIndex-1)*pageSize,pageSize,state,reason);
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
                           @RequestParam(value = "nickname",required = false) String nickname,
                           @RequestParam(value = "channel",required = false) String channel,
                           @RequestParam(value = "referrer",required = false) String referrer,
                           @RequestParam(value = "phone",required = false) String phone){
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("nickname",nickname);
        map.put("channel",channel);
        map.put("referrer",referrer);
        map.put("phone",phone);
        List<User> userList = userService.queryUsers(map);
        int size = userService.findSize();
        int pages = 0;
        if (userList.size() != 0) {
            pages = size/pageSize;
        }
        pages += 1;
        Map<String,Object> obj = new HashMap<>();
        obj.put("userList", userList);
        obj.put("pageIndex", pageIndex);
        obj.put("pages", pages);
        return ResultUtil.success(obj);
    }
    @GetMapping("getOrderPeopleList")
    @ApiOperation("返回测评用户列表")
    public Result getOrderPeopleList(@RequestParam("pageIndex") Integer pageIndex,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam(value = "oid",required = false) Integer oid,
                                     @RequestParam(value = "name",required = false) String name,
                                     @RequestParam(value = "birthday_time",required = false) String birthday_time,
                                     @RequestParam(value = "address",required = false) String address,
                                     @RequestParam(value = "state",required = false) Integer state,
                                     @RequestParam(value = "phone",required = false) String phone){
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("oid",oid);
        map.put("name",name);
        map.put("birthday_time",birthday_time);
        map.put("address",address);
        map.put("state",state);
        map.put("phone",phone);
        List<OrderPeopleList> orderPeopleLists = orderPeopleListService.findOrderPeopleList(map);
        int size = orderPeopleListService.findAll();
        int pages = 0;
        if (orderPeopleLists.size() != 0) {
            pages = size/pageSize;
        }
        pages += 1;
        Map<String,Object> obj = new HashMap<>();
        obj.put("orderPeopleLists", orderPeopleLists);
        obj.put("pageIndex", pageIndex);
        obj.put("pages", pages);
        return ResultUtil.success(obj);
    }


    @PostMapping("updateOrderPeopleList")
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
//        orderPeopleListService.updatePeople2(oid,title,rurl);
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
