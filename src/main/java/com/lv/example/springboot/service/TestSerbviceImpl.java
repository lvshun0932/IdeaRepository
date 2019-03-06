package com.lv.example.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/***
 *@Title ${TODO}
 *@author shunlv
 *@Date 2018/12/11 12:52 AM
 */
@Service
public class TestSerbviceImpl implements TestService {

    private static final Logger loger = LoggerFactory.getLogger(TestSerbviceImpl.class);



    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        loger.info("start executeAsync");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loger.info("end executeAsync");
    }

    @Override
    public void test() {
        loger.info("start test");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loger.info("end test");
    }
}
