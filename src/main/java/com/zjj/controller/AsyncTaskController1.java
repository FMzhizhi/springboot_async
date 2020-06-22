package com.zjj.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/22 23:38
 */
@RestController
@RequestMapping("/async1")
public class AsyncTaskController1 {

    //spring对@Transactional注解时也有类似问题，spring扫描时具有@Transactional注解方法的类时，
    // 是生成一个代理类，由代理类去开启关闭事务，而在同一个类中，方法调用是在类体内执行的，
    // spring无法截获这个方法调用。
    @RequestMapping("/task1")
    public String doTask() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        this.task1();
        this.task2();
        this.task3();
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms";
    }

    @Async
    public void task1() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task1任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }

    @Async
    public void task2() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task2任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }

    @Async
    public void task3() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task3任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }
}
