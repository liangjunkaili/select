package com.course.selection.controller;


import com.course.selection.bean.Goods;
import com.course.selection.dto.Result;
import com.course.selection.service.*;
import com.course.selection.util.CosUtil;
import com.course.selection.util.ResultUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
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
    @ApiOperation("添加商品")
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
    public Result getAllOrders(@RequestParam(value = "gid" ,required = false) Integer gid,@RequestParam(value = "uid" ,required = false) Integer uid,
                               @RequestParam(value = "state" ,required = false,defaultValue = "-1") Integer state,@RequestParam("pageIndex") Integer pageIndex
                                ,@RequestParam("pageSize") Integer pageSize) {
        return orderService.getAllOrders(gid, uid, state,pageIndex,pageSize);
    }
}
