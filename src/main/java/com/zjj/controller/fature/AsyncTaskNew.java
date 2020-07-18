package com.zjj.controller.fature;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 13:42
 */
@Component
public class AsyncTaskNew {
      
    @Async
    public Future<String> task1() throws InterruptedException{
        long currentTimeMillis = System.currentTimeMillis();  
        Thread.sleep(1000);  
        long currentTimeMillis1 = System.currentTimeMillis();  
        System.out.println("task1任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");  
        return new AsyncResult<String>("task1执行完毕");
    }  
      
    @Async  
    public Future<String> task2() throws InterruptedException{  
        long currentTimeMillis = System.currentTimeMillis();  
        Thread.sleep(2000);  
        long currentTimeMillis1 = System.currentTimeMillis();  
        System.out.println("task2任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");  
        return new AsyncResult<String>("task2执行完毕");  
    }  
    @Async  
    public Future<String> task3() throws InterruptedException{  
        long currentTimeMillis = System.currentTimeMillis();  
        Thread.sleep(3000);  
        long currentTimeMillis1 = System.currentTimeMillis();  
        System.out.println("task3任务耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");  
        return new AsyncResult<String>("task3执行完毕");  
    }  
}  