package com.bigdata.hive.jdbc;

/**
 * Created by Administrator on 2020/12/16.
 */
public interface HiveListener {
    void exceptionCaught(HiveContext hiveContext);

    void completed(HiveContext hiveContext);

    void monitor(HiveContext hiveContext);
}
