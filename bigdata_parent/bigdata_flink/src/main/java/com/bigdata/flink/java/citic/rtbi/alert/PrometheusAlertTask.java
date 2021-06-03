package com.bigdata.flink.java.citic.rtbi.alert;

import com.bigdata.flink.java.citic.rtbi.alert.entity.Alert;
import com.bigdata.flink.java.citic.rtbi.alert.entity.StreamInstance;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanhailong
 * @date 2021/6/3.
 */
public class PrometheusAlertTask implements MeterBinder, AlertTask {

    private DistributionSummary totalDelaySummary;
    private DistributionSummary schedulingDelaySummary;
    private DistributionSummary processingDelaySummary;

    private Map<String ,Long> map=new HashMap<>();


    @Override
    public int alert(Alert alert) throws Exception {

        //TODO from databases
        List<StreamInstance> list=new ArrayList<>();

        for(StreamInstance streamInstance:list){
            if(streamInstance.getTotalDelay()!=null && streamInstance.getSchedulingDelay()!=null&&streamInstance.getProcessingDelay()!=null){
                totalDelaySummary.record(streamInstance.getTotalDelay());
                schedulingDelaySummary.record(streamInstance.getSchedulingDelay());
                processingDelaySummary.record(streamInstance.getProcessingDelay());
            }
        }

        long warning=10;  //从数据库获取告警值
        long danger=20;  //从数据库获取危险值

        map.put("warning",warning);
        map.put("danger",danger);

        //获取指标，并更新数据库
        map.put("requestCountPerSecond",1L);//指标值来源于数据库
        //TODO 其他指标值添加到MAP

        //TODO 更新数据库

        return 0;
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {

        totalDelaySummary=meterRegistry.summary("stream_delay","tag_name","totalDelay");
        schedulingDelaySummary=meterRegistry.summary("stream_delay","tag_name","schedulingDelay");
        processingDelaySummary=meterRegistry.summary("stream_delay","tag_name","processingDelay");

        System.out.println("实时流Summary注册成功......");

        map.put("warning",0L);


        //指标减少计数器
        Gauge.builder("alert_count",map,x->x.get("warning"))
                .tag("level","warning")
                .description("警告告警数量")
                .register(meterRegistry);

        map.put("danger",0L);
        Gauge.builder("alert_count",map,x->x.get("danger"))
                .tag("level","danger")
                .description("危险告警数量")
                .register(meterRegistry);


        map.put("requestCountPerSecond",0L);
        Gauge.builder("sys_suage",map,x->x.get("requestCountPerSecond"))
                .tag("level","requestCountPerSecond")
                .description("每秒请求数量")
                .register(meterRegistry);


        map.put("hadoopUsed",0L);
        Gauge.builder("sys_suage",map,x->x.get("hadoopUsed"))
                .tag("level","hadoopUsed")
                .description("HDFS已使用空间")
                .register(meterRegistry);


        map.put("hadoopTotal",0L);
        Gauge.builder("sys_suage",map,x->x.get("hadoopTotal"))
                .tag("level","hadoopTotal")
                .description("HDFS总空间")
                .register(meterRegistry);


        map.put("yarnTotal",0L);
        Gauge.builder("sys_suage",map,x->x.get("yarnTotal"))
                .tag("level","yarnTotal")
                .description("YARN总资源")
                .register(meterRegistry);


        map.put("redisUsed",0L);
        Gauge.builder("sys_suage",map,x->x.get("redisUsed"))
                .tag("level","redisUsed")
                .description("redis已使用内存")
                .register(meterRegistry);


        map.put("redisTotal",0L);
        Gauge.builder("sys_suage",map,x->x.get("redisTotal"))
                .tag("level","redisTotal")
                .description("redis总内存")
                .register(meterRegistry);


        map.put("redisCount",0L);
        Gauge.builder("sys_suage",map,x->x.get("redisCount"))
                .tag("level","redisCount")
                .description("redis总数")
                .register(meterRegistry);


        map.put("serverCount",0L);
        Gauge.builder("sys_suage",map,x->x.get("serverCount"))
                .tag("level","serverCount")
                .description("Server总数")
                .register(meterRegistry);

        System.out.println("Gauge指标计数器注册成功");
    }
}
