package com.bigdata.hive.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.util.Properties;

/**
 * Created by Administrator on 2020/12/16.
 */
public class HiveDataSource extends DruidDataSource{
    private static final String uri="jdbc:hive2://localhost1:port1,localhost2:port2;serviceDiscoveryMode=zookeeper;zooKeeperNamespace=hiveserver2";

    public HiveDataSource(){
        this(null);
    }


    public HiveDataSource(Properties properties){
        setUrl(uri);
        setDriverClassName("org.apache.hive.jdbc.HiveDriver");
        setUsername("hive");
        setPassword("hive");
        setTestWhileIdle(true);
        setValidationQuery("select 1");
        setMaxActive(2);
        setInitialSize(2);
        setRemoveAbandoned(true);
        setRemoveAbandonedTimeout(300);
        setConnectProperties(properties);
    }

}
