package com.course.selection.util;

import java.util.concurrent.*;

/**
 * 线程池调优：（首要问题是找到瓶颈）
 * 最大线程数、最小线程数、超时时间、队列的类型（BlockingQueue）、创建线程的工厂、拒绝策略
 * 要看处理的任务是什么类型：计算（CPU）密集型、I/O密集型
 * 合理设置线程的空闲时间（keepAliveTime），防止频繁的创建新的线程
 * 如何检测自己的代码是否是同步？
 */
public class ThreadPoolUtil {
    public static final Integer MAXPOOLSIZE = Runtime.getRuntime().availableProcessors();
    public static final Executor EXECUTOR1 = Executors.newCachedThreadPool();
    public static final Executor EXECUTOR2 = Executors.newSingleThreadExecutor();
    public static final Executor EXECUTOR3 = Executors.newFixedThreadPool(MAXPOOLSIZE);
    public static final Executor EXECUTOR4 = Executors.newSingleThreadScheduledExecutor();
    public static final Executor EXECUTOR5 = Executors.newScheduledThreadPool(MAXPOOLSIZE);
    public static final Executor EXECUTOR6 = Executors.newWorkStealingPool();
    public static final Executor USERDEFINED_EXECUTOR = new ThreadPoolExecutor(MAXPOOLSIZE, MAXPOOLSIZE, 1000,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), (Runnable r) ->{
        Thread t = new Thread();
        t.setDaemon(true);
        return t;
    }, (Runnable r, ThreadPoolExecutor executor) -> {
        //此处抛出指定异常，在应用层捕捉改异常，返回友好的信息，可根据具体的业务处理
        throw new RejectedExecutionException("Task " + r.toString()+" rejected from " +executor.toString());
    });
}
