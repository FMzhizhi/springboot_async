package com.zjj.controller.async;

import com.zjj.service.task.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 13:42
 */
@RequestMapping("/async")
@RestController
public class AsyncTaskController {  
      
    @Autowired
    private AsyncTask asyncTask;
      
    @RequestMapping("/task")
    public String doTask() throws InterruptedException{  
        long currentTimeMillis = System.currentTimeMillis();  
        asyncTask.task1();  
        asyncTask.task2();  
        asyncTask.task3();  
        long currentTimeMillis1 = System.currentTimeMillis();  
        return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";  
          
    }  
}  
