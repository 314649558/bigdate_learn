package com.bigdata.flink.scala.shangguigu

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows
import org.apache.flink.streaming.api.windowing.time._

/**
  * @author yuanhailong 
  * @date 2021/3/30.
  */
object WindowTest {
  def main(args: Array[String]): Unit = {
    val dataList=List("hello ","hailong")

    val env=StreamExecutionEnvironment.getExecutionEnvironment

    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)



    val ds=env.fromCollection(dataList)


    /*ds.map(word=>(word,1)
        .keyBy(_._1)

          .timeWindow(Time.days(1))
*/

  }
}


case class Senser()
