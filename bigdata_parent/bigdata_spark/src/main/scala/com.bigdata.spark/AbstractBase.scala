package com.bigdata.spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2020/11/9.
  */
class AbstractBase {

  def getSparkSession(implicit appName:String): SparkSession ={
    val sparkConf=new SparkConf()
    sparkConf.set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
    //解决oracle 连接后长时间没响应Oracle服务器主动关闭连接的异常
    sparkConf.set("spark.executor.extraJavaOptions","-Djava.security.egd=file:/dev/../dev/urandom")
    if(System.getProperty("os.name").startsWith("linux")){
      SparkSession.builder()
        .appName(appName)
        .config(sparkConf)
        .getOrCreate()
    }else{
      SparkSession.builder()
        .appName(appName)
        .config(sparkConf)
        .master("local[2]")
        .getOrCreate()
    }
  }

}
