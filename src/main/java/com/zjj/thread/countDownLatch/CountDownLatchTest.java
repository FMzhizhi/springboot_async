package com.zjj.thread.countDownLatch;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 14:41
 */
public class CountDownLatchTest {

     /*     countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
            是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，
            计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
    */



    /* //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
     public void await() throws InterruptedException { };
     //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
     public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
     //将count值减1
     public void countDown() { };*/

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行…………");
        Instant strart = Instant.now();
        //第一个子线程执行
        ExecutorService es = Executors.newFixedThreadPool(8);

        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
       // es1.shutdown();

        //第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                latch.countDown();
            }
        });
        es.shutdown();
        System.out.println("等待两个线程执行完毕…………");

        try {
            latch.await();
            Instant end = Instant.now();
            long millis = Duration.between(strart, end).toMillis();
            System.out.println("花费时间"+millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }
}
