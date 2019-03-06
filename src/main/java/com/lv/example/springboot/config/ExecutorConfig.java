//package com.lv.example.springboot.config;
//
//import com.google.common.util.concurrent.ThreadFactoryBuilder;
//import com.lv.example.springboot.util.VisiableThreadPoolExecutor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//
//import java.util.concurrent.*;
//
///***
// *@Title ${TODO}
// *@author shunlv
// *@Date 2018/12/11 12:43 AM
// */
//
//public class ExecutorConfig {
//    private static final Logger loger = LoggerFactory.getLogger(ExecutorConfig.class);
//
////    @Bean(name = "serviceExecutor")
////    public ThreadPoolTaskExecutor asyncServiceExecutor(){
////        loger.info("start asyncServiceExecutor");
////        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
////        executor.setCorePoolSize(5);
////        executor.setMaxPoolSize(5);
////        executor.setQueueCapacity(99999);
////        executor.setThreadNamePrefix("async-service-");
////        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
////        executor.initialize();
//////        return executor;
////
////        return executor;
////    }
////
////    @Bean(name = "threadPool")
////    public ExecutorService getThreadPool(){
////       //return Executors.newFixedThreadPool(20);
////        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("Muti-pool-%d").build();
////        ExecutorService executor = new ThreadPoolExecutor(5, Integer.MAX_VALUE,0L, TimeUnit.MILLISECONDS,
////                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
////        return executor;
////    }
//
//
//    @Bean(name = "visiableThreadPool")
//    public ExecutorService getVisiableThreadPool(){
//        //return Executors.newFixedThreadPool(20);
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("Muti-pool-%d").build();
//        ExecutorService executor = new VisiableThreadPoolExecutor(10, 100,1L, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
//        ((VisiableThreadPoolExecutor) executor).allowCoreThreadTimeOut(true);
//        return executor;
//    }
//}
