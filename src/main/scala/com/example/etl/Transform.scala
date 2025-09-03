package com.example.etl

import org.apache.spark.sql.{DataFrame, functions => F}

object Transform {
  def cleanTaxi(df: DataFrame): DataFrame = {
    df
      .withColumn("tpep_pickup_datetime", F.to_timestamp(F.col("tpep_pickup_datetime")))
      .withColumn("tpep_dropoff_datetime", F.to_timestamp(F.col("tpep_dropoff_datetime")))
      .withColumn("trip_distance", F.col("trip_distance").cast("double"))
      .withColumn("fare_amount", F.col("fare_amount").cast("double"))
      .withColumn("tip_amount", F.col("tip_amount").cast("double"))
      .filter(F.col("trip_distance") > 0 && F.col("fare_amount") > 0)
      .withColumn("year",  F.year(F.col("tpep_pickup_datetime")))
      .withColumn("month", F.month(F.col("tpep_pickup_datetime")))
  }

  def enrichTaxi(df: DataFrame): DataFrame = {
    df.withColumn(
        "trip_minutes",
        (F.col("tpep_dropoff_datetime").cast("long") - F.col("tpep_pickup_datetime").cast("long"))/60.0
      )
      .withColumn(
        "tip_rate",
        F.when(F.col("fare_amount") > 0.0, F.col("tip_amount")/F.col("fare_amount")).otherwise(F.lit(null))
      )
  }
}
