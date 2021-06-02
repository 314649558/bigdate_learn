package com.bigdata.flink.java.citic.rtbi.alert;

import com.bigdata.flink.java.citic.rtbi.alert.entity.Alert;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
public interface AlertTask {
    int alert(Alert alert) throws Exception;

}
