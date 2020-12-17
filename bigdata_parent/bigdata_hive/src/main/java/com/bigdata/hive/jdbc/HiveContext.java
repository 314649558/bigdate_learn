package com.bigdata.hive.jdbc;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/12/16.
 */
public class HiveContext {

    private Date startDate;
    private Date finishedDate;
    private String applicationId;
    private Throwable throwable;
    private List<String> executeLogs;
    private List<Map<String ,Object>> resultSet;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public List<String> getExecuteLogs() {
        return executeLogs;
    }

    public void setExecuteLogs(List<String> executeLogs) {
        this.executeLogs = executeLogs;
    }

    public List<Map<String, Object>> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<Map<String, Object>> resultSet) {
        this.resultSet = resultSet;
    }
}
