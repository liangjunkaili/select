package com.course.selection.controller;

import com.course.selection.dto.Result;
import com.course.selection.service.OrderPeopleListService;
import com.course.selection.util.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public Result urlOk(@RequestParam(value = "id", required = false) Integer id,
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
        String password = "123456ab";
        String stamp = DateUtil.localDateTimeFormat(localDateTime,"MMddHHmmss");
        String secret = EncryptUtil.Encrypt(password+stamp,"MD5");
        String param = "?companyId=141421&stamp="+stamp+"&secret="+secret+"&reportNo="+reportNo;
        String res = HttpRequest.sendGet(ConstantUtil.OTHER_STATUS+param);
        return ResultUtil.success(res);
    }
}
