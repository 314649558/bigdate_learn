package com.bigdata.flink.java.citic.rtbi.alert;

import com.bigdata.flink.java.citic.rtbi.alert.entity.Alert;
import com.bigdata.flink.java.citic.rtbi.alert.entity.AlertInstance;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.master.RegionState;
import org.apache.hadoop.mapreduce.ClusterMetrics;

import javax.swing.plaf.synth.Region;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class HBaseRitAlertTask extends BaseAlertTask {

    //实际中需要初始化，这里只是为了写模板代码
    private Connection connection;

    @Override
    public List<AlertInstance> check(Alert alert) {

        List<AlertInstance> list=new ArrayList<>();
        List<RegionState> regionStatesInTransition = getRegionStatesInTransition();
        for(RegionState regionState:regionStatesInTransition){
            String name = regionState.getRegion().getRegionNameAsString();

            long ritDuration=regionState.getRitDuration();

            if(ritDuration >=alert.getWarning()){
                String message="RIT状态持续时间超过["+ritDuration+"]毫秒，请检查集群状态";
                AlertInstance alertInstance=new AlertInstance();
                alertInstance.setMessage(message);
                alertInstance.setName(name);
                list.add(alertInstance);
            }
        }
        return list;
    }


    public List<RegionState> getRegionStatesInTransition(){
        ClusterMetrics clusterMetrics = getClusterMetrics();
        return new ArrayList<RegionState>();
    }


    public ClusterMetrics getClusterMetrics(){
        //实际需要从集群中获取
        return new ClusterMetrics();
    }







}
