package com.course.selection.util;

import java.util.concurrent.*;

public class MyTask implements Runnable{
    @Override
    public void run() {
        //do stuff
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = (ForkJoinPool) ThreadPoolUtil.EXECUTOR6;
        pool.execute(new ForkJoinTask1(0,100));
        Future<Integer> future = pool.submit(new ForkJoinTask2(0,1000000));
        System.out.println(future.get());
    }
}

class ForkJoinTask1 extends RecursiveAction{
    private static final int THRESHOLD = 20;//每个任务执行的阈值
    private int start;
    private int end;

    public ForkJoinTask1(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        if (end - start<THRESHOLD){
            for (int i=start;i<=end;i++){
                System.out.println("执行fork join task"+i);
            }
        }else {
            int mid = (start+end) >>> 1;
            ForkJoinTask1 left = new ForkJoinTask1(start,mid);
            left.fork();
            ForkJoinTask1 right = new ForkJoinTask1(mid+1,end);
            right.fork();
        }
    }
}

class ForkJoinTask2 extends RecursiveTask<Integer>{
    private static final int THRESHOLD = 20;//每个任务执行的阈值
    private int start;
    private int end;

    public ForkJoinTask2(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start<THRESHOLD){
            for (int i=start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else {
            int mid = (start+end) >>> 1;
            ForkJoinTask2 left = new ForkJoinTask2(start,mid);
            left.fork();
            ForkJoinTask2 right = new ForkJoinTask2(mid+1,end);
            right.fork();
            return left.join()+ right.join();
        }
    }
}