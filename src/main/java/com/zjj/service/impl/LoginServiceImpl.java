package com.zjj.service.impl;

import com.zjj.service.interfaces.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 10:55
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Override
    public void getTest2() {
        LoginServiceImpl building = new LoginServiceImpl();
        synchronized (building){
            try {
                for (int i = 1;i <= 100;i++){
                    log.info(Thread.currentThread().getName()+"----------同步：>"+i);
                    building.wait(200);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Async
    @Override
    public String getTest1(){
        LoginServiceImpl building = new LoginServiceImpl();
        synchronized (building){
            try {
                for (int i = 1;i <= 100;i++){
                    log.info(Thread.currentThread().getName()+"----------异步：>"+i);
                    building.wait(200);
                }
                return "执行异步任务完毕";
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return Thread.currentThread().getName()+"执行完毕";
    }

}
