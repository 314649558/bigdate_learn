package com.bigdata.flink.java.citic.rtbi.alert.entity;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class App {
    enum Deploy{
        SPARK,STRUCTURED,FLINK
    }

    enum State{
        CREATE,
        STARTED,
        RUNNING,
        CANCELLED,
        STOPPING,
        STOPPED,
        FAILED,
        FINISHED,
        KILLED,
        APPROVED,
        EXIPRED
    }

    enum Storege{
        REDIS,HBASE
    }

    enum StartupMode{
        CONTINUE,LASTEST,EARLIEST
    }
}
