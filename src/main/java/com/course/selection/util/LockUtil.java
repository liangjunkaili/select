package com.course.selection.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步的目的是保证线程安全
 * 同步的手段：synchronized（monitorenter/monitorexit）、Lock（ReentrantLock）、ReadWriteLock（ReentrantReadWriteLock）
 * 同步的代价：可伸缩性的影响、锁定对象的开销
 * 避免同步：ThreadLocal、CAS
 * 串行代码的比例
 * volatile（可以理解为轻量级的锁）：保证变量对所有线程的可见性(新值能立即同步到主内存，每次使用前立即从主内存刷新)、
 *      禁止指令重排序优化
 * java内存模型（缓存一致性问题）：主内存、工作内存、java线程
 * lock：作用于主内存的变量，把一个变量标识为一条线程独占的状态
 * unlock：作用于主内存的变量，把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定
 * read：作用于主内存的变量，把一个变量的值从主内存传输到线程的工作内存中，以便随后的load使用
 * load：作用于工作内存的变量，把read操作从主内存中得到的变量值放入工作内存的变量副本中
 * use：作用于工作内存的变量，把工作内存中一个变量的值传递给执行引擎，每当虚拟机遇到一个需要使用到变量的值的字节码指令时
 *      将会执行这个操作
 * assign：作用于工作内存的变量，把一个从执行引擎接收到的值赋给工作内存的变量，每当虚拟机遇到一个给变量赋值的字节码指令
 *      时会执行这个操作
 * store：作用于工作内存的变量，把工作内存中一个变量的值传到主内存中，以便随后的write操作使用
 * write：作用于主内存的变量，把store操作从工作内存中得到的变量的值放入主内存的变量中
 * 原子性、可见性（volatile、synchronized、final）、有序性
 */
public class LockUtil {
    //volatile的使用场景
    volatile boolean shutdownRequested;
    public void shutdown(){
        shutdownRequested = true;
    }
    public void doWork(){
        while (!shutdownRequested){
            //do stuff
        }
    }
    final ReentrantLock lock;
    private Condition condition;
    public LockUtil(){
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }
    public void doSomthing() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            //do stuff
            System.out.println("1111111111111111");
            condition.await();
            System.out.println("3333333333333333");
        }finally {
            System.out.println("leaf doSomthing");
            lock.unlock();
        }
    }
    public void doElse() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            //do stuff
            System.out.println("2222222222");
            condition.signal();
        }finally {
            System.out.println("leaf doElse");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockUtil lockUtil = new LockUtil();
        System.out.println(ThreadPoolUtil.MAXPOOLSIZE);
        try{
            Future f = ThreadPoolUtil.USERDEFINED_EXECUTOR.submit(() -> {
                try {
                    lockUtil.doSomthing();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Future f1 = ThreadPoolUtil.USERDEFINED_EXECUTOR.submit(() -> {
                lockUtil.doElse();
            });
            try {
                System.out.println(f.get());
                System.out.println(f1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }catch (RejectedExecutionException e){
            System.out.println("RejectedExecutionException");
        }
    }
}
