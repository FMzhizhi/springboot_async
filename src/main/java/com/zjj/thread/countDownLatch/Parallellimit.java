package com.zjj.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parallellimit {

    //    Java通过Executors提供四种线程池，分别为：
//    newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
//    newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//    newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
//    newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            System.out.println("i:"+i);
            CountRunnable runnable = new CountRunnable(cdl);
            pool.execute(runnable);
        }
    }
}

//模拟并发
class CountRunnable implements Runnable {
    private CountDownLatch countDownLatch;

    public CountRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            synchronized (countDownLatch) {
                /*** 每次减少一个容量*/
                countDownLatch.countDown();
                System.out.println(countDownLatch.getCount()+"让出cpu执行行权，但我没有释放锁啊");
                //Thread.sleep(500);

                System.out.println("thread counts = "+ (countDownLatch.getCount()));
            }
            countDownLatch.await();
            System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}