package com.bigdata.flink.java.citic.rtbi.alert.entity;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class StreamInstance {

    private Integer id;


    /**
     * 进程@主机名
     */
    private String name;

    /**
     * YARN APPID
     */
    private String appid;

    /**
     * 告警级别
     */
    private Alert.Level level;

    /**
     * 状态信息描述
     */
    private String message;

    /**
     * 告警状态
     */
    private App.State state;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss",timezone = "GMT+8")
    private Date startedDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss",timezone = "GMT+8")
    private Date stoppedDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastCheckedDate;


    private Long totalDelay;
    private Long schedulingDelay;
    private Long processingDelay;

    public Long getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDelay(Long totalDelay) {
        this.totalDelay = totalDelay;
    }

    public Long getSchedulingDelay() {
        return schedulingDelay;
    }

    public void setSchedulingDelay(Long schedulingDelay) {
        this.schedulingDelay = schedulingDelay;
    }

    public Long getProcessingDelay() {
        return processingDelay;
    }

    public void setProcessingDelay(Long processingDelay) {
        this.processingDelay = processingDelay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Alert.Level getLevel() {
        return level;
    }

    public void setLevel(Alert.Level level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public App.State getState() {
        return state;
    }

    public void setState(App.State state) {
        this.state = state;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getStoppedDate() {
        return stoppedDate;
    }

    public void setStoppedDate(Date stoppedDate) {
        this.stoppedDate = stoppedDate;
    }

    public Date getLastCheckedDate() {
        return lastCheckedDate;
    }

    public void setLastCheckedDate(Date lastCheckedDate) {
        this.lastCheckedDate = lastCheckedDate;
    }
}
