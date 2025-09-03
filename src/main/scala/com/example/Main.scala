package com.example

import org.apache.spark.sql.SparkSession
import com.example.config.AppConfig
import com.example.etl.{Extract, Transform, Load}
import com.example.quality.Checks
import com.example.analytics.KPIs

object Main {
  def main(args: Array[String]): Unit = {
    val cfg = AppConfig.load()
    val spark = SparkSession.builder()
      .appName(cfg.spark.app_name)
      .master(cfg.spark.master)
      .getOrCreate()

    try {
      val dfRaw   = Extract.fromCsv(spark, cfg.app.input_path, cfg.app.sample)
      val dfClean = Transform.cleanTaxi(dfRaw)
      Checks.runBasic(dfClean)
      val dfEnr   = Transform.enrichTaxi(dfClean)
      Load.toParquetPartitioned(dfEnr, cfg.app.output_path, cfg.app.partitions)
      val kpis = KPIs.compute(dfEnr)
      kpis.show(50, truncate = false)
    } finally {
      spark.stop()
    }
  }
}
