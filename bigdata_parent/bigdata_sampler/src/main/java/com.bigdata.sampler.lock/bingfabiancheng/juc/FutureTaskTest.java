package com.bigdata.sampler.lock.bingfabiancheng.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2021/1/27.
 */
public class FutureTaskTest {
    public static void main(String[] args) throws Exception {


        ExecutorService executorService= Executors.newFixedThreadPool(1);

        Task task=new Task();

        Future<Boolean> future = executorService.submit(task);

        boolean done = future.isDone();
        System.out.println(done);


        Thread.sleep(12000);

        new Thread(()->{

            boolean cancel = future.cancel(true);

            System.out.println("取消任务:"+cancel);
        }).start();


        Boolean aBoolean = future.get();
        System.out.println(aBoolean);


        boolean done1 = future.isDone();
        System.out.println(done1);
        executorService.shutdown();
    }
}


class Task implements Callable<Boolean>{

    @Override
    public Boolean call() throws Exception {
        for(int i=0;i<10;i++){
            System.out.println(i);
            Thread.sleep(1000);
        }
        return true;
    }
}
