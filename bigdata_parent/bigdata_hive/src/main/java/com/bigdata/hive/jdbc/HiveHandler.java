package com.bigdata.hive.jdbc;

import org.apache.hive.jdbc.HiveConnection;
import org.apache.hive.jdbc.HiveQueryResultSet;
import org.apache.hive.jdbc.HiveStatement;

import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2020/12/16.
 */
public final class HiveHandler {
    private List<HiveListener> listeners;

    private volatile int state;

    private boolean isQuery;

    private String executeSQL;

    private HiveContext hiveContext=new HiveContext();

    private static final long DEFAULT_LIMITS=100000;

    HiveStatement statement=null;


    private HiveHandler(){}

    private static HiveHandler newBuilder(){
        return new HiveHandler();
    }

    public HiveHandler setQuery(boolean query) {
        isQuery = query;
        return this;
    }

    public HiveHandler setExecuteSQL(String executeSQL) {
        this.executeSQL = executeSQL;
        return this;
    }

    public HiveHandler addListener(HiveListener listener){
        if(listeners==null){
            listeners=new CopyOnWriteArrayList<>();
        }
        listeners.add(listener);
        return this;
    }

    public void startExecute(){
        hiveContext.setStartDate(new Date());
        executeQuery0();
    }

    public void startAsnyExecute(){
        hiveContext.setStartDate(new Date());
        new Thread(()->{
            executeQuery0();
        }).start();

    }


    private void executeQuery0(){
        fireEvent();

        HiveConnection hiveConn=HiveJDBCTemplate.getConnection();
        HiveQueryResultSet resultSet=null;
        try{
            statement=(HiveStatement) hiveConn.createStatement();
            resultSet= (HiveQueryResultSet) statement.executeQuery(executeSQL);
            List<Map<String,Object>> resultList=new ArrayList<>();
            int columnCount=resultSet.getMetaData().getColumnCount();
            while (resultSet.next()){
                Map<String,Object> map=new LinkedHashMap<>();
                for(int i=1;i<=columnCount;i++){
                    String label=resultSet.getMetaData().getColumnLabel(i);
                    map.put(label,resultSet.getObject(label));
                }
            }
        }catch (Exception e){
            state=2;
            hiveContext.setThrowable(e);
        }
    }



    private void fireEvent(){
        if(listeners==null || listeners.size()==0){
            return;
        }


        listeners.stream().forEach(listener ->{
            new Thread(()->{
               for(;;){
                   try{
                       if(state==1){
                           hiveContext.setFinishedDate(new Date());
                           listener.completed(hiveContext);
                           break;
                       }else if (state==2){
                           hiveContext.setFinishedDate(new Date());
                           listener.exceptionCaught(hiveContext);
                           break;
                       }else{
                           List<String> queryLog= statement.getQueryLog();
                           if(hiveContext.getExecuteLogs()==null){
                               hiveContext.setExecuteLogs(queryLog);
                           }else{
                               hiveContext.getExecuteLogs().addAll(queryLog);
                           }
                           listener.monitor(hiveContext);
                       }
                       Thread.sleep(1000);
                   }catch (Exception e){
                       break;
                   }
               }
            });
        });

    }



}
