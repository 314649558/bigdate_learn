package com.bigdata.spark

import org.apache.commons.lang3.StringUtils
import org.apache.spark.launcher.{SparkAppHandle, SparkLauncher}

/**
  * Created by Administrator on 2020/12/16.
  */
object SparkLanucherTest {
  def main(args: Array[String]): Unit = {

    if(args==null || args.length==0){
      print("至少携带一个参数")
      return
    }

    val sparkAppHandle = new SparkLauncher()
      .setAppName("test_hailong")
      .setSparkHome("/usr/hdp/spark_home")
      .setMaster("yarn")
      .setDeployMode("cluster")
      .setConf("spark.driver.memory", "2g")
      .setConf("spark.executro.memory", "8g")
      .setConf("spark.executor.cores", "8")
      .setAppResource("/appdata/execute.jar")
      .setMainClass("com.hailong.MainClass")
      .addAppArgs(args(0))
      .startApplication(new SparkAppHandle.Listener() {
        override def infoChanged(handle: SparkAppHandle): Unit = {
          print(handle.getAppId + "        " + handle.getState.toString)
        }

        override def stateChanged(handle: SparkAppHandle): Unit = {
          print(handle.getAppId + "        " + handle.getState.toString)
        }
      })


      while(!StringUtils.equalsAnyIgnoreCase("FINISHED",sparkAppHandle.getState.toString)){
        print("monitor-->:"+sparkAppHandle.getState.toString)
      }


  }
}
