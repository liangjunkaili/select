package com.course.selection.util;

import java.util.concurrent.*;

public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
//        System.out.println("beforeExecute thread name:"+r.toString()+t.getId());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
//        System.out.println("afterExecute"+Thread.currentThread().getId()+"==="+this.getPoolSize());
    }

    @Override
    protected void terminated() {
        System.out.println("terminated----");
    }
}
