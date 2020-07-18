package com.zjj.service.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 13:42
 */
public class ConCallable implements Callable {

    private List<String> list;

    @Override
    public Object call() throws Exception {
//        Thread.sleep(500);
//        System.out.println(Thread.currentThread().getName()+"===list"+list);
        List<String> listRe = new ArrayList<>();
        for(int i = 0;i < list.size();i++){

            if(list.get(i).contains("4599")){
                listRe.add(list.get(i));
            }
        }
        return listRe;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

}
