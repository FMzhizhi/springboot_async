package com.zjj.service.task;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 13:42
 */
public class RunnableTask1 implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(getClass());
 
    @Override
    public void run(){
        RunnableTask1 building = new RunnableTask1();
        synchronized (building){
            try {
                for (int i = 1;i <= 100;i++){
                    System.out.println(Thread.currentThread().getName()+"----------异步：>"+i);
                    building.wait(200);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}