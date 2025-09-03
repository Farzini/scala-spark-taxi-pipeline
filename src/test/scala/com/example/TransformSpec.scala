package com.example

import org.scalatest.funsuite.AnyFunSuite
import org.apache.spark.sql.SparkSession
import com.example.etl.Transform

class TransformSpec extends AnyFunSuite {
  val spark = SparkSession.builder().master("local[2]").appName("test").getOrCreate()
  import spark.implicits._

  test("cleanTaxi removes non-positive trips") {
    val df = Seq(
      ("2024-01-01 10:00:00","2024-01-01 10:10:00","1.2","10.0","2.0"),
      ("2024-01-01 11:00:00","2024-01-01 11:05:00","0.0","-1.0","0.0")
    ).toDF("tpep_pickup_datetime","tpep_dropoff_datetime","trip_distance","fare_amount","tip_amount")

    val out = Transform.cleanTaxi(df)
    assert(out.count() == 1)
  }
}
