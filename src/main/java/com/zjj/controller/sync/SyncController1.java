package com.zjj.controller.sync;

import com.zjj.service.interfaces.LoginService;
import com.zjj.service.task.RunnableTask1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 10:51
 */
@RequestMapping("/test1")
@RestController
@Slf4j
public class SyncController1 {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "test2",method = RequestMethod.GET)
    public String test2(){
        loginService.getTest2();
        log.info(Thread.currentThread().getName()+"==========主线程名");
        return "同步,正在解析......";
    }

    @RequestMapping(value = "test3",method = RequestMethod.GET)
    public String test3(){
        ExecutorService service = Executors.newFixedThreadPool(5);
        RunnableTask1 task1 = new RunnableTask1();
        service.execute(task1);
        log.info("=========》当前线程名："+Thread.currentThread().getName());
        return "异步,正在解析......";
    }


}
