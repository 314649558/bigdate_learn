package com.bigdata.spark.delta

import com.bigdata.spark.AbstractBase

/**
  * Created by Administrator on 2020/11/9.
  */
object DeltaQuickStart extends AbstractBase{

  def main(args: Array[String]): Unit = {

    val path="D:\\tmp\\delta-table"

    implicit val appName=this.getClass.getSimpleName
    val sparkSession = getSparkSession

    val data = sparkSession.range(0,5)

    data.write.format("delta").save(path)

  }


}
