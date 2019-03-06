package com.lv.example.springboot.manager;

import com.lv.example.springboot.service.TestService;
import com.lv.example.springboot.util.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/***
 *@Title ${TODO}
 *@author shunlv
 *@Date 2018/12/11 8:09 PM
 */

public class TaskManager {
    private static final Logger loger = LoggerFactory.getLogger(TaskManager.class);


    private TestService testService;
//    @Autowired
//    private TaskActuator taskActuator;

    public TaskManager(TestService testService){
        this.testService = testService;
    }

    public List<Task> getTask() throws InterruptedException {
        loger.info("start submitCommit");
        List<Task> list = new ArrayList<Task>();
        for (int i = 0; i < 100; i++){
            Task task = new Task(testService);
            list.add(task);

        }

        return list;

    }
}
