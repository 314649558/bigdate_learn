package com.bigdata.hive.jdbc;

import org.apache.hive.jdbc.HiveConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/12/16.
 */
public final class HiveJDBCTemplate {
    private static HiveDataSource hiveDataSource;

    public static HiveConnection getConnection(){
        try{
            if(hiveDataSource==null){
                hiveDataSource=new HiveDataSource();
            }
            return (HiveConnection) hiveDataSource.getPooledConnection().getConnection();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static List<String> getAllSchema(){
        List<String> list=new ArrayList<>();
        Connection connection=getConnection();

        try{
            ResultSet resultSet=connection.getMetaData().getSchemas();
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }

        }catch (Exception e){

        }
        return list;
    }

    public static List<String> getTableNameSchema(String schema){
        List<String> list=new ArrayList<>();
        Connection connection=getConnection();
        ResultSet resultSet=null;
        try{
            resultSet=connection.getMetaData().getTables("",schema,null,new String[]{"TABLE"});
            while(resultSet.next()){
                list.add(resultSet.getString("TABLE_NAME"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return list;
    }


    public static Map<String,Object> getColumns(String schema, String table){
        Connection connection=getConnection();
        ResultSet resultSet=null;
        Map<String,Object> map=new HashMap<>();
        try{
            resultSet=connection.getMetaData().getColumns(null,schema,table,null) ;
            while(resultSet.next()){
                String columnName=resultSet.getString("COLUMN_NAME");
                String remarks=resultSet.getString("REMARKS");
                String type=resultSet.getString("TYPE_NAME");
                map.put("COLUMN_NAME",columnName);
                map.put("TYPE",type);
                map.put("COMMENT",remarks);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

        }

        return map;
    }


}
