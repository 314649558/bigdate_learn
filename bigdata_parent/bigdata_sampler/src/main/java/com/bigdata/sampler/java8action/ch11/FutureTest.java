package com.bigdata.sampler.java8action.ch11;

import utils.ThreadUtils;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2021/2/24.
 */
public class FutureTest {

    private static  final ExecutorService service=Executors.newFixedThreadPool(2);


    public static void main(String[] args) throws Exception {

        Future<Boolean> worker1 = service.submit(new Worker("worker1"));
        Future<Boolean> worker2 = service.submit(new Worker("worker2"));

        Boolean a1 = worker1.get();
        System.out.println(1);

        Boolean a2 = worker2.get();
        System.out.println(2);
        System.out.println("-------------:"+a1);
        System.out.println("----------:"+a2);

        service.shutdown();
    }

}

class Worker implements Callable<Boolean>{
    private String workname;

    public Worker(String workname){
        this.workname=workname;
    }

    @Override
    public Boolean call() throws Exception {

        int i = new Random().nextInt(10);
        System.out.println(workname+" start:"+i);
        ThreadUtils.sleep(new Random().nextInt(10)*1000);
        System.out.println(workname+" end");
        if(i>5){
            return false;
        }
        return true;
    }
}
