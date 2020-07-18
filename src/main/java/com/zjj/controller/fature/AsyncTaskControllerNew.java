package com.zjj.controller.fature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 13:42
 */
@RequestMapping("/asy")
@RestController
public class AsyncTaskControllerNew {
      
    @Autowired
    private AsyncTaskNew asyncTask;
      
    @GetMapping
    public String doTask() throws InterruptedException{  
        long currentTimeMillis = System.currentTimeMillis();  
        Future<String> task1 = asyncTask.task1();
        Future<String> task2 = asyncTask.task2();  
        Future<String> task3 = asyncTask.task3();  
        String result = null;  
        for (;;) {  
            if(task1.isDone() && task2.isDone() && task3.isDone()) {  
                // 三个任务都调用完成，退出循环等待  
                break;  
            }  
            Thread.sleep(1000);  
        }  
        long currentTimeMillis1 = System.currentTimeMillis();  
        result = "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";  
        return result;  
    }  
}  