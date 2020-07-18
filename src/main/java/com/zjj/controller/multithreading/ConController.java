package com.zjj.controller.multithreading;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.*;
import com.zjj.service.task.ConCallable;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/23 13:42
 */
@RestController
@RequestMapping("con")
public class ConController {
    private final static Logger logger = LoggerFactory.getLogger(Logger.class);

    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public String test1() {
        try {
            //10万条数据
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();

            for (long i = 1; i <= 50000000L; i++) {
                list.add("test:" + i);
            }

            Instant start = Instant.now();

            //每条线程处理的数据尺寸
            int size = 250;
            int count = list.size() / size;
            if (count * size != list.size()) {
                count++;
            }
            int countNum = 0;
            //countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
            final CountDownLatch countDownLatch = new CountDownLatch(count);
            ExecutorService executorService = Executors.newFixedThreadPool(8);
            ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
            while (countNum < list.size()) {
                countNum += size;
                ConCallable callable = new ConCallable();
                //截取list的数据，分给不同线程处理
                callable.setList(ImmutableList.copyOf(list.subList(countNum - size, countNum < list.size() ? countNum : list.size())));
                ListenableFuture listenableFuture = listeningExecutorService.submit(callable);
                Futures.addCallback(listenableFuture, new FutureCallback<List<String>>() {
                    //成功
                    @Override
                    public void onSuccess(List<String> list1) {
                        // 每次减少一个容量，计数器为0的时候才执行
                        countDownLatch.countDown();
                        list2.addAll(list1);


                    }

                    //失败
                    @Override
                    public void onFailure(Throwable throwable) {
                        countDownLatch.countDown();
                        System.out.println("failure");
                        logger.info("处理出错：", throwable);

                    }
                });
                //Thread.sleep(3000);
            }
            ////和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
            Instant end = Instant.now();
            System.out.println("花费时间"+Duration.between(start,end).toMillis());
            countDownLatch.await(30, TimeUnit.MINUTES);
            logger.info("符合条件的返回数据个数为：" + list2.size());
            logger.info("回调函数：" + list2.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "正在处理......";

    }



    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public String test2() {
        try {
            //10万条数据
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();

            for (long i = 1; i <= 50000000L; i++) {
                list.add("test:" + i);
            }


            final CountDownLatch countDownLatch = new CountDownLatch(1);
            long start = System.currentTimeMillis();
           // Instant start = Instant.now();
            for(int i = 0;i < list.size();i++){

                if(list.get(i).contains("4599")){
                    list2.add(list.get(i));
                }
            }

            countDownLatch.countDown();
            long end = System.currentTimeMillis();
            countDownLatch.await();
           // Instant end = Instant.now();
            System.out.println("花费时间"+(end-start));
            logger.info("符合条件的返回数据个数为：" + list2.size());
            logger.info("回调函数：" + list2.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "正在处理......";

    }



    @RequestMapping(value = "test3", method = RequestMethod.GET)
    public String test3() {

        //10万条数据
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (int i = 1; i <= 30000000; i++) {
            list.add("test:" + i);
        }
        Instant start = Instant.now();
        List<String> collect = list.stream().collect(Collectors.toList());
        Instant end = Instant.now();
        System.out.println("花费时间"+ Duration.between(start,end).toMillis());
        System.out.println(collect.size());
        //花费时间10191   6679
        //15651093
        return "正在出路。。。。。。";
    }


    @RequestMapping(value = "test4", method = RequestMethod.GET)
    public String test4() {

        //10万条数据
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for (int i = 1; i <= 30000000; i++) {
            list.add("test:" + i);
        }
        Instant start = Instant.now();
        List<String> collect = list.stream().parallel().collect(Collectors.toList());
        Instant end = Instant.now();//6551
        System.out.println("单线程的花费时间"+ Duration.between(start,end).toMillis());

        System.out.println(collect.size());
        return "正在出路。。。。。。";


    }


}
