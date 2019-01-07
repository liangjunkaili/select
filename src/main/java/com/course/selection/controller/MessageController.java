package com.course.selection.controller;

import com.course.selection.dto.Result;
import com.course.selection.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@Api
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("leaveMessage")
    @ApiOperation("留言")
    public Result leaveMessage(
            @RequestParam(value = "uid",required = true) Integer uid,
            @RequestParam(value = "gid",required = true) Integer gid,
            @RequestParam(value = "oid",required = true) Integer oid,
            @RequestParam(value = "message",required = true) String message
    ){
        return messageService.leaveMessage(uid, gid,oid, message);
    }

    @PostMapping("reply")
    @ApiOperation("回复留言")
    public Result reply(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "nick") String nick,
            @RequestParam(value = "reply") String reply
    ){
        return messageService.reply(id,reply,nick);
    }

    @PostMapping("getMessage")
    @ApiOperation("获取留言")
    public Result getMessage(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "nick") String nick,
            @RequestParam(value = "reply") String reply
    ){
        return messageService.reply(id,reply,nick);
    }

}
