package com.bigdata.flink.java.citic.rtbi.alert;

import com.bigdata.flink.java.citic.rtbi.alert.entity.Alert;
import com.bigdata.flink.java.citic.rtbi.alert.entity.AlertInstance;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.List;
import java.util.Properties;

/**
 * REDIS命理数检查
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class RedisOpsAlertTask extends BaseAlertTask {

    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public List<AlertInstance> check(Alert alert) {

        RedisClusterConnection clusterConnection = redisConnectionFactory.getClusterConnection();

        for (RedisClusterNode node:clusterConnection.clusterGetNodes()){
            if(!node.isMarkedAsFail()){
                Properties pro = clusterConnection.info("stats");
                long ops = Long.parseLong(pro.getProperty("instantaneous_ops_per_sec"));

                //大于预警值则告警
                if(ops>alert.getWarning()){
                    //TODO 添加到返回对象中
                }

            }
        }

        return null;
    }
}
