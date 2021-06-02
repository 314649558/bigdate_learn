package com.bigdata.flink.java.citic.rtbi.alert;

import com.bigdata.flink.java.citic.rtbi.alert.entity.Alert;
import com.bigdata.flink.java.citic.rtbi.alert.entity.AlertInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
public abstract class BaseAlertTask implements AlertTask {

    @Autowired
    public RestTemplate restTemplate;
    @Override
    public int alert(Alert alert) throws Exception {

        /**
         * 获取告警示例信息
         */
        List<AlertInstance> alertInstances = check(alert);


        /**
         * 上次发现的告警示例，实际项目中这个可存在mysql数据库中，从数据库中获取
         */
        Map<String,AlertInstance> instanceMap=new HashMap<>();

        List<AlertInstance> warning=new ArrayList<>();
        List<AlertInstance> danger=new ArrayList<>();
        List<AlertInstance> lastChecked=new ArrayList<>();

        for(AlertInstance instance:alertInstances){
            String name=instance.getName();
            AlertInstance entity=instanceMap.remove(instance);


            if(entity==null){
                entity=instance;
                entity.setAlert(alert);
                entity.setName(name);
                entity.setState(AlertInstance.State.CREATED);
                entity.setStartedDate(new Date());
            }else{
                //未修复的告警，更新检查时间
                entity.setLastCheckedDate(new Date());
            }


            //新增或状态发生变化
            if(entity.getId()==null || entity.getLevel()!=instance.getLevel()){
                if(instance.getLevel()==Alert.Level.DANGER){
                    danger.add(entity);
                }else{
                    warning.add(entity);
                }
            }else{
                lastChecked.add(entity);
            }

            entity.setLevel(instance.getLevel());
            entity.setMessage(instance.getMessage());
        }

        //已经修复的告警
        Collection<AlertInstance> repaired = instanceMap.values();

        for(AlertInstance instance: repaired){
            instance.setState(AlertInstance.State.REPAIRED);
            instance.setStoppedDate(new Date());
        }


        //发送告警信息
        sendSMS(alert,warning,danger,repaired);

        //保存检查结果
        List<AlertInstance> instances=new ArrayList<>();
        instances.addAll(warning);
        instances.addAll(danger);
        instances.addAll(lastChecked);
        instances.addAll(repaired);

        //TODO 调用接口将instances数据保存到mysql
        return warning.size()+danger.size()+lastChecked.size();
    }


    private void sendSMS(Alert alert, List<AlertInstance> warning, List<AlertInstance> danger, Collection<AlertInstance> repaired) {
        //TODO 调用短信接口发送告警信息
    }


    /**
     * 有子类实现是否需要告警
     *    查询不同组件的告警rest api 并将数据写入到List集合中
     * @param alert
     * @return
     */
    public abstract List<AlertInstance> check(Alert alert);
}
