package com.bigdata.kafka.sourceparse.metrix.yammer.meter;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Meter;
import com.yammer.metrics.reporting.ConsoleReporter;

import java.util.concurrent.TimeUnit;

/**
 * Created by yuanhailong on 2018/12/19.
 */
public class LearnMeter {

    private Meter meter= Metrics.newMeter(LearnMeter.class,"meter-event","request", TimeUnit.SECONDS);

    public void handleRequest(){
        meter.mark();
    }


    public static void main(String[] args) throws Exception{
        ConsoleReporter.enable(1,TimeUnit.SECONDS);
        LearnMeter learnMeter=new LearnMeter();

        for(int times = 0; times < 200; times++){
            learnMeter.handleRequest();
            Thread.sleep(100);
        }
    }
}
