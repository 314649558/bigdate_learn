package com.bigdata.sampler.java8action.ch11;

import utils.ThreadUtils;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2021/2/24.
 */
public class CompleteableFutureDemo2 {

    public static void main(String[] args) {

        CompletableFuture<Boolean> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("f1 start");
            ThreadUtils.sleep(new Random().nextInt(10)*1000);
            System.out.println("f1 end");
            return true;
        });




        CompletableFuture<Boolean> f2= CompletableFuture.supplyAsync(() -> {
            System.out.println("f2 start");
            ThreadUtils.sleep(new Random().nextInt(10)*1000);
            System.out.println("f2 end");
            return true;
        });

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(f1, f2);

        voidCompletableFuture.thenRun(()->{
            System.out.println("xxxxxxxx");
        });
        voidCompletableFuture.join();
    }
}

