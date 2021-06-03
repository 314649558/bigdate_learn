package com.bigdata.flink.java.citic.rtbi.alert.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class HadoopConfig {

    @Value("${hadoop.yarn.url}")
    public String yarnUrl;
}
