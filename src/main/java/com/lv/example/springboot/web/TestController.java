package com.lv.example.springboot.web;

import com.lv.example.springboot.annotation.AuthIgnore;
import com.lv.example.springboot.entity.UserEntity;
import com.lv.example.springboot.service.TestService;
import com.lv.example.springboot.util.ExcelParam;
import com.lv.example.springboot.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
@Api(description = "test")
public class TestController {
    private static final Logger loger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

//    @Autowired
//    private TaskActuator taskActuator;
//
//    @Autowired
//    private ExecutorService getVisiableThreadPool;

//    @Autowired
//    private TaskManager taskManager;

    @ApiOperation(value="hello",notes = "test hello")
    @GetMapping("/hello")
    public String hello() {
        loger.info("hello world");
        return "hello test";
    }

    @ApiOperation(value = "uploadFile",notes = "test upload")
    @PostMapping(value = "/uploadFile", produces = { "application/json;charset=UTF-8" })
    public void uploadFile(@RequestParam(value = "uploadFile") MultipartFile uploadFile
    ,@RequestParam(value = "token") String token){
       String name = uploadFile.getOriginalFilename();
       loger.info("upload name = " + name + ",token = " + token);
    }


    @ApiOperation(value = "downloadFile",notes = "下载")
    @GetMapping(value = "/downloadFile",produces = {"application/json;charset=UTF-8"})
    public void downloadFile(@RequestParam(value = "token") String token, HttpServletResponse response){
        String[] header = {"日期", "有效订单数", "有效订单总金额", "优惠券支付", "赠送蜜币支付", "活动抵扣","订单退款", "订单实际收入",
                "车辆数" ,"每车每日管理费","支付金额","最终收益"};
        List<String[]> dataList = new ArrayList<>();
        loger.info("downloadfile init");
        ExcelParam param = new ExcelParam.Builder("对账报表").headers(header).data(dataList).build();
        try {
            String[] array = {"日期", "有效订单数", "有效订单总金额", "活动成本", "订单退款","订单实际收入", "运营管理费", "合计"};
            ExcelUtil.exportFranchiserBook(param, response, "加盟商对账数据", array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/submit")
    @AuthIgnore
    public boolean submitCommit(){
        loger.info("start submitCommit");
        testService.executeAsync();
        loger.info("end submitCommit");
        return true;
    }
//
//    @GetMapping("/submit2")
//    @AuthIgnore
//    public boolean submit2() throws InterruptedException {
//        loger.info("start submitCommit");
//        List<Task> list = new ArrayList<Task>();
//        for (int i = 0; i < 100; i++){
//            Task task = new Task(testService);
//            list.add(task);
//
//        }
//        getVisiableThreadPool.invokeAll(list);
//        //loger.info("end submitCommit，getThreadPool = " + getThreadPool.);
//        getVisiableThreadPool.shutdown();
//        return true;
//    }


    @GetMapping("/submit3")
    @AuthIgnore
    public boolean submit3() throws InterruptedException {
//        TaskManager taskManager = new TaskManager();
//        taskManager.doTask();
        return true;
    }

//    @GetMapping("/submit4")
//    @AuthIgnore
//    public boolean submit4() throws InterruptedException {
//        TaskManager taskManager = new TaskManager(testService);
//        List<Task> list = taskManager.getTask();
//        taskActuator.invokeAll(list);
//        return true;
//    }
//
//    @GetMapping("/submit5")
//    @AuthIgnore
//    public boolean submit5() throws InterruptedException {
//        TaskManager taskManager = new TaskManager(testService);
//        List<Task> list = taskManager.getTask();
//        taskActuator.invokeAll(list);
//        return true;
//    }

    @GetMapping("/getList")
    @AuthIgnore
    public List<UserEntity> getList(){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername("xiaoming");
        userEntity.setToken("100100");

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId(2);
        userEntity1.setUsername("xiaoming1");
        userEntity1.setToken("100200");

        List<UserEntity> list = new ArrayList<>();
        list.add(userEntity);
        list.add(userEntity1);
        return  list;
    }

}
