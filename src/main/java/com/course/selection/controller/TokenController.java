package com.course.selection.controller;

import com.course.selection.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Log4j2
public class TokenController {
    @Autowired
    private UserService userService;

    @ApiOperation("校验token")
    @PostMapping("checkToken")
    public String checkToken(HttpServletRequest request, @RequestBody String message) throws IOException {
        log.info("客服接入");
        if (userService.checkSignature(request)) {
//            customService.sendCustomMessage(message);
            return "success";
        }
        return "success";
    }
}
