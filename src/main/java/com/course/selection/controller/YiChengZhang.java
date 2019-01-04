package com.course.selection.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("urlOk")
    public void urlOk(HttpServletRequest request){
        Enumeration<String> stringEnumeration = request.getParameterNames();
        while (stringEnumeration.hasMoreElements()){
            String param = stringEnumeration.nextElement();
            log.info(param);
            log.info(request.getParameter(param));

        }
        log.info("第三方成功回调");
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
}
