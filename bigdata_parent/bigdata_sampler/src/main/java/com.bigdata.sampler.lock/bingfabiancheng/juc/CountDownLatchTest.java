package com.bigdata.sampler.lock.bingfabiancheng.juc;

import utils.ThreadUtils;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2021/1/27.
 */
public class CountDownLatchTest {

    private static final ExecutorService service= Executors.newFixedThreadPool(3);


    public static void main(String[] args)  {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(2);
            System.out.println("-----------------");
            WorkerCount workerCount1 = new WorkerCount(countDownLatch, "work1");
            WorkerCount workerCount2 = new WorkerCount(countDownLatch, "work2");

           /* new Thread(workerCount1).start();
            System.out.println("mid-------------------");
            new Thread(workerCount2).start();*/

            Future<Boolean> submit1 = service.submit(workerCount1);
            Future<Boolean> submit2 = service.submit(workerCount2);

            System.out.println("提交完成");

            submit1.get();
            submit2.get();

            //countDownLatch.await();
            System.out.println("任务结束咯");
        }catch (Exception e){
            System.out.println("fffffff");
        }
    }

}

class WorkerCount implements Callable<Boolean>{

    private CountDownLatch countDownLatch;
    private String workername;

    public WorkerCount(CountDownLatch countDownLatch,String workername){
        this.countDownLatch=countDownLatch;
        this.workername=workername;
    }

    @Override
    public Boolean call() {
        Random random = new Random();
        System.out.println("begin:"+workername);
        long time=random.nextInt(10)*1000;
        System.out.println("stop time:"+time);
        ThreadUtils.sleep(time);
        System.out.println("end:"+workername);
        //countDownLatch.countDown();
        return true;
    }
}
