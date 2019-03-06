package com.lv.example.springboot.web;

import com.lv.example.springboot.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(description = "api接口")
public class ApiController {
    private static final Logger loger = LoggerFactory.getLogger(ApiController.class);

    @ApiOperation(value="/hello", notes = "hello world")
    @GetMapping("/hello")
    public String hello() {
        loger.info("hello world");
        return "hello world";
    }

    @ApiOperation(value="/setuser", notes = "setuser")
    @PostMapping(value = "/setuser",produces = {"application/json;charset=UTF-8"})
    public void setUser(@RequestBody UserEntity userEntity)
    {
        loger.info("setuser init,userEntity = " + userEntity.getUsername());
    }
}
