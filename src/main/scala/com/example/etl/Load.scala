package com.example.etl

import org.apache.spark.sql.{DataFrame, SaveMode}

object Load {
  def toParquetPartitioned(df: DataFrame, outPath: String, partitions: List[String]): Unit = {
    df.write
      .mode(SaveMode.Overwrite)
      .partitionBy(partitions:_*)
      .parquet(outPath)
  }
}
