package com.lv.example.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/***
 *@Title ${TODO}
 *@author shunlv
 *@Date 2018/12/11 6:44 PM
 */
public class VisiableThreadPoolExecutor extends ThreadPoolExecutor {

    private static final Logger logger = LoggerFactory.getLogger(VisiableThreadPoolExecutor.class);


    public VisiableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public VisiableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public VisiableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public VisiableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    private void showThreadPoolInfo(String prefix){
        logger.info("{},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]"
                ,prefix
                ,getTaskCount()
                ,getCompletedTaskCount()
                ,getActiveCount()
                ,getQueue().size());
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
        showThreadPoolInfo("log");
        return super.invokeAll(tasks);
    }

    @Override
    public void shutdown() {
        showThreadPoolInfo("log");
        super.shutdown();
        showThreadPoolInfo("end");
    }

    @Override
    public void execute(Runnable command) {
        showThreadPoolInfo("execute");
        super.execute(command);
    }
}
