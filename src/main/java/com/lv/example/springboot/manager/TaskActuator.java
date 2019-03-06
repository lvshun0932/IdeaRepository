package com.lv.example.springboot.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 *@Title ${TODO}
 *@author shunlv
 *@Date 2018/12/12 11:52 AM
 */
public class TaskActuator {

    private static final Logger loger = LoggerFactory.getLogger(TaskActuator.class);

//
//    @Qualifier("visiableThreadPool")
//    @Autowired
//    private ExecutorService getVisiableThreadPool;
//
//    public List invokeAll(List<Task> tasks) throws InterruptedException {
//        loger.info("task执行器批量执行任务");
//       List reList = getVisiableThreadPool.invokeAll(tasks);
//       //getVisiableThreadPool.shutdown();
//       return reList;
//    }
//
//    @Async("visiableThreadPool")
//    public void doTask(Runnable command){
//        loger.info("task执行器单个执行任务");
//        getVisiableThreadPool.execute(command);
//    }

}
