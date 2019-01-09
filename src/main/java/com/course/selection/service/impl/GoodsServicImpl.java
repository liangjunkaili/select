package com.course.selection.service.impl;

import com.course.selection.bean.*;
import com.course.selection.dao.CouponsDao;
import com.course.selection.dao.GoodsDao;
import com.course.selection.dao.MessageDao;
import com.course.selection.dao.UserCouponDao;
import com.course.selection.dto.*;
import com.course.selection.service.GoodsService;
import com.course.selection.service.HomePageService;
import com.course.selection.special.SUtil;
import com.course.selection.util.PageBean;
import com.course.selection.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GoodsServicImpl
 * @Description TODO
 * @Author junliang
 * @Date 2018/12/17 7:36 AM
 * @Version 1.0
 **/
@Service
@Log4j2
public class GoodsServicImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private CouponsDao couponsDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private HomePageService homePageService;

    @Override
    public List<Goods> queryGoods() {
        return goodsDao.queryGoods();
    }

    @Override
    public Result index() {
        //查询首页信息
        List<Goods> goods = goodsDao.queryGoods();
        List<HomePage> homePages = homePageService.queryHomePage();
        List<GoodDto> goodDtos = new ArrayList<>();
        List<ImgDto> imgDtos = new ArrayList<>();
        for (HomePage homePage:homePages){
            ImgDto imgDto = ImgDto.builder()
                    .url(homePage.getUrl())
                    .img(homePage.getImg())
                    .build();
            imgDtos.add(imgDto);
        }
        for (Goods good : goods) {
            String[] split = good.getLabel().split("\\|");

            GoodDto goodDto = GoodDto.builder()
                    .img(good.getImg())
                    .intro(good.getIntro())
                    .label(split)
                    .price(good.getPrice())
                    .title(good.getTitle())
                    .id(good.getId())
                    .build();
            goodDtos.add(goodDto);
        }
        IndexDto indexDto = IndexDto.builder()
                .goodDtos(goodDtos)
                .imgDtos(imgDtos)
                .build();
        return ResultUtil.success(indexDto);
    }

    @Override
    public void addGoods(Goods goods) {
        goodsDao.addGoods(goods);
    }

    @Override
    public Result getGoodById(Integer id, Integer uid) {
        List<Goods> goods = goodsDao.queryGoods();
        List<GoodDto> goodDtos = new ArrayList<>();
        int i = 0;
        Goods good = null;
        for (Goods goods1 : goods
        ) {
            if (goods1.getId() == id) {
                good = goods1;
            } else {
                if (i <= 2) {
                    goodDtos.add(GoodDto.builder()
                            .price(goods1.getPrice())
                            .id(goods1.getId())
                            .img(goods1.getImg())
                            .intro(goods1.getIntro())
                            .label(goods1.getLabel().split("\\|"))
                            .title(goods1.getTitle())
                            .build());
                }
                i++;
            }
        }
        Coupons coupons = couponsDao.findById(good.getFlag());
        List<Message> messages = messageDao.findByGoodId(good.getId());
        List<UserCoupon> myCoupons = userCouponDao.findByUid(uid);
        boolean myCp = false;
        for (UserCoupon userCoupon : myCoupons
        ) {
            if (coupons!=null){
                if (userCoupon.getCpid() == coupons.getId()) {
                    myCp = true;
                    coupons.setVaildTimeStr(userCoupon.getInvalidDate());
                }
            }

        }
        String[] split = good.getLabel().split("\\|");
        GoodsDto goodsDto = GoodsDto.builder()
                .bamount(good.getBamount())
                .detail(good.getDetail())
                .flag(good.getFlag())
                .icard(good.getIcard())
                .img(good.getImg())
                .img1(good.getImg1().split("\\|"))
                .img2(good.getImg2())
                .img3(good.getImg3())
                .intro(good.getIntro())
                .id(good.getId())
                .label(split)
                .num(good.getNum())
                .weight(good.getWeight())
                .title(good.getTitle())
                .oprice(good.getOprice())
                .price(good.getPrice())
                .iprice(good.getIprice())
//                .attribute(SUtil.toListA())
//                .service(SUtil.toListS())
                .build();
        if(good.getAttribute()!=0){
            goodsDto.setAttribute(SUtil.attributes.get(good.getAttribute()));
        }
        if(good.getService()!=0){
            goodsDto.setService(SUtil.toListS());
        }
//        if (good.getId()==7){
//            goodsDto.setAttribute(SUtil.toListA());
//            goodsDto.setService(SUtil.toListS());
//        }
        GoodDetails goodDetails = GoodDetails.builder()
                .goods(goodsDto)
                .coupons(coupons)
                .message(messages)
                .myCp(myCp)
                .list(goodDtos)
                .build();
        return ResultUtil.success(goodDetails);
    }

    @Override
    public Result getGoodsByAdmin(Integer state, Integer currentPage, Integer pageSize) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(currentPage, pageSize);

        List<Goods> goods = null;
        if (state == 0) {
            goods = goodsDao.queryGoods();
        }else{
            goods = goodsDao.queryGoodsA(state);
        }
        int countNums = goods.size();        //总记录数
        PageBean<Goods> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(goods);
        return ResultUtil.success(goods);
    }

    @Override
    public Result upperShelf(Integer gid) {
        Goods goods = goodsDao.queryGoodsById(gid);
        goods.setState(2);
        goodsDao.update(goods);
        return ResultUtil.success();
    }

    @Override
    public Result lowerShelf(Integer gid) {
        Goods goods = goodsDao.queryGoodsById(gid);
        goods.setState(3);
        goodsDao.update(goods);
        return ResultUtil.success();
    }
}
