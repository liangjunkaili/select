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
    public static final ExecutorService EXECUTOR1 = Executors.newCachedThreadPool();
    public static final ExecutorService EXECUTOR2 = Executors.newSingleThreadExecutor();
    public static final ExecutorService EXECUTOR3 = Executors.newFixedThreadPool(MAXPOOLSIZE);
    public static final ExecutorService EXECUTOR4 = Executors.newSingleThreadScheduledExecutor();
    public static final ExecutorService EXECUTOR5 = Executors.newScheduledThreadPool(MAXPOOLSIZE);
    public static final ExecutorService EXECUTOR6 = Executors.newWorkStealingPool();
    public static final ExecutorService USERDEFINED_EXECUTOR = new ThreadPoolExecutor(MAXPOOLSIZE, MAXPOOLSIZE,
            60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
            new MyThreadFactory(),new MyRejected());
}
