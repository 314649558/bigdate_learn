package com.bigdata.flink.scala.shangguigu

import org.apache.flink.streaming.api.scala._

/**
  * @author yuanhailong 
  * @date 2021/3/30.
  */


object SourceTest {

  def main(args: Array[String]): Unit = {

    val dataList=List("hello ","hailong")

    val env=StreamExecutionEnvironment.getExecutionEnvironment

    val ds=env.fromCollection(dataList)


    ds.print().setParallelism(1)

    env.execute("source test")



  }

}
