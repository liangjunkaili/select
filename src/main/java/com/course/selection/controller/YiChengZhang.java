package com.course.selection.controller;

import com.course.selection.dto.Result;
import com.course.selection.service.OrderPeopleListService;
import com.course.selection.util.*;
import com.google.zxing.WriterException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;

/**
 * @ClassName YiChengZhang
 * @Description TODO
 * @Author junliang
 * @Date 2019/1/4 2:45 PM
 * @Version 1.0
 **/
@RestController
@Log4j2
public class YiChengZhang {

    @Autowired
    private OrderPeopleListService orderPeopleListService;
    @RequestMapping("urlOk")
    @ApiOperation(value = "第三方成功回调接口")
    @ApiImplicitParam(name = "id",value = "orderPeopleListID",required = true,dataType = "int")
    public Result urlOk(@RequestParam(value = "id") Integer id,
                        HttpServletRequest request){
        log.info("第三方成功回调"+id);
        return orderPeopleListService.test(id);
    }
    @RequestMapping("urlNg")
    public void urlNg(HttpServletRequest request){
        Enumeration<String> stringEnumeration = request.getParameterNames();
        while (stringEnumeration.hasMoreElements()){
            String param = stringEnumeration.nextElement();
            log.info(param);
            log.info(request.getParameter(param));

        }
        log.info("第三方失败回调");
    }
    @RequestMapping("status")
    public Result status(@RequestParam(value = "reportNo") Integer reportNo){
        LocalDateTime localDateTime = LocalDateTime.now();
        String password = ConstantUtil.PASSWORD;
        String stamp = DateUtil.localDateTimeFormat(localDateTime,"MMddHHmmss");
        String secret = EncryptUtil.Encrypt(password+stamp,"MD5");
        String param = "?companyId="+ConstantUtil.COMPANYID+"&stamp="+stamp+"&secret="+secret+"&reportNo="+reportNo;
        String res = HttpRequest.sendGet(ConstantUtil.OTHER_STATUS+param);
        return ResultUtil.success(JsonUtil.getMapFromJson(res));
    }
    @RequestMapping(value = "make")
    public void readZxing(HttpServletResponse response, @RequestParam(value = "gid") Integer gid,
                          @RequestParam(value = "uid") Integer uid) throws WriterException, IOException {
        String content = "http://dis.ucharmedu.com/authorize?state="+uid+"&gid="+gid;
        ZxingUtil.createZxing(response, 200, 200, 0, "L", "gif", content);
    }
}
