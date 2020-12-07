package com.bigdata.spark.lanucher

import org.apache.spark.launcher.{SparkAppHandle, SparkLauncher}

/**
  * Created by Administrator on 2020/12/6.
  */
object MySparkStart {
  def main(args: Array[String]): Unit = {
    val handle = new SparkLauncher()
      .setAppName("hello_world")
      .setSparkHome("/usr/hdp/3.0.1.0/spark2")
      .setMaster("yarn")
      .setConf("spark.driver.memory", "2g")
      .setConf("spark.executor.memory", "1g")
      .setConf("spark.execute.cores", "3")
      .setAppResource("/home/hailong/lanucher.jar")
      .setMainClass("com.hailong.test.Test")
      .addAppArgs(args(0))
      .setDeployMode("cluster")
      .startApplication(new SparkAppHandle.Listener {

        override def infoChanged(handle: SparkAppHandle): Unit = {
            print("--infoChanged-->:"+handle.getAppId+"        state-->:"+handle.getState.toString)
        }

        override def stateChanged(handle: SparkAppHandle): Unit = {
          print("--infoChanged-->:"+handle.getAppId+"        state-->:"+handle.getState.toString)
        }
      })
    while (!"FINISHED".equalsIgnoreCase(handle.getState.toString)){
      print(handle.getAppId)
      Thread.sleep(1000)
    }
  }
}
