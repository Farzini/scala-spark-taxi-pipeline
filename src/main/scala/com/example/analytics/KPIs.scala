package com.example.analytics

import org.apache.spark.sql.{DataFrame, functions => F}

object KPIs {
  def compute(df: DataFrame): DataFrame = {
    df
      .withColumn("hour", F.hour(F.col("tpep_pickup_datetime")))
      .groupBy("year","month","hour")
      .agg(
        F.count("*").alias("rides"),
        F.round(F.avg("trip_distance"),2).alias("avg_km"),
        F.round(F.avg("tip_rate")*100,2).alias("avg_tip_pct")
      )
      .orderBy("year","month","hour")
  }
}
