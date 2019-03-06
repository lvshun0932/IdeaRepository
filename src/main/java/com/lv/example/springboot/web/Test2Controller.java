package com.lv.example.springboot.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
@Api(description = "test2")
public class Test2Controller {
    private static final Logger loger = LoggerFactory.getLogger(Test2Controller.class);

    @ApiOperation(value="hello",notes = "test2 hello")
    @GetMapping("/hello")
    public String hello() {
        loger.info("hello world");
        return "hello test2";
    }
}
