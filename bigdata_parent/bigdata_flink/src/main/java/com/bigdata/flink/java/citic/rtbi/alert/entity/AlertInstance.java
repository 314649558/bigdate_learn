package com.bigdata.flink.java.citic.rtbi.alert.entity;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author yuanhailong
 * @date 2021/6/2.
 */
public class AlertInstance {

    private Integer id;

    private Alert alert;

    /**
     * 告警名称，可能是应用名称，服务名称，或节点名称等
     */
    private String name;

    /**
     * 告警级别
     */
    private Alert.Level level;

    /**
     * 告警详情
     */
    private String message;

    /**
     * 告警状态
     */
    private State state;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss",timezone = "GMT+8")
    private Date startedDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss",timezone = "GMT+8")
    private Date stoppedDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastCheckedDate;



    public enum State{
        CREATED,REPAIRED
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
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
