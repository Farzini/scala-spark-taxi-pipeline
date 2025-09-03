package com.example.quality

import org.apache.spark.sql.{DataFrame, functions => F}

object Checks {
  def runBasic(df: DataFrame): Unit = {
    val rows = df.count()
    require(rows > 0, "No rows after cleaning — check inputs.")
    val nulls = df.select(df.columns.map(c => F.sum(F.col(c).isNull.cast("int")).alias(c)):_*)
    nulls.show(false)
  }
}
