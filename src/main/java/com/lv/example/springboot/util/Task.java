package com.lv.example.springboot.util;

import com.lv.example.springboot.service.TestSerbviceImpl;
import com.lv.example.springboot.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/***
 *@Title ${TODO}
 *@author shunlv
 *@Date 2018/12/11 12:51 PM
 */
public class Task implements Callable<List<Map<String,Object>>>{
    private static final Logger logger = LoggerFactory.getLogger(TestSerbviceImpl.class);

    private TestService testService;

    public Task() {}

    public Task(TestService testService){
        this.testService = testService;
    }


        @Override
        public List<Map<String,Object>> call() throws Exception {
            logger.info("线程名字==========" + Thread.currentThread().getName());

            List<Map<String,Object>> list = new ArrayList<>();
            testService.test();
            return list;
        }

}

