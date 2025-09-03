package com.example.etl

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object Extract {
  def fromCsv(spark: SparkSession, path: String, sample: Boolean): DataFrame = {
    val df = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(path)
    if (sample) df.limit(10000) else df
  }
}
